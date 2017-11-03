/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity.address;

import util.connection.ConnectionFactory;
import dao.entity.DAO;
import model.entity.address.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matheus Henrique
 */
public class AddressDAO implements DAO {

    public static int LAST_ID_INSERT = -1;

    /**
     *
     * @param address
     * @return
     */
    public boolean create(Address address) {
        
        Connection conn = ConnectionFactory.getConnection();
        String sql = "INSERT INTO address (street, number, neighborhood, cep) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, address.getStreet());
            stmt.setInt(2, address.getNumber());
            stmt.setString(3, address.getNeighborhood());
            stmt.setString(4, address.getCep());
            stmt.executeUpdate();

            rs = stmt.getGeneratedKeys();
            rs.next();
            LAST_ID_INSERT = rs.getInt(1);
            return true;
        }
        catch (SQLException sqlE) {
            Logger.getLogger(AddressDAO.class.getName()).log( Level.SEVERE, null, sqlE);
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }

        return false;
    }

    /**
     *
     * @param address
     * @return
     */
    public boolean update (Address address) {
        Connection conn = ConnectionFactory.getConnection();
        String sql = "UPDATE address SET street = ?, number = ?, neighborhood = ?,  cep = ? WHERE id_address = ?";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, address.getStreet());
            stmt.setInt(2, address.getNumber());
            stmt.setString(3, address.getNeighborhood());
            stmt.setString(4, address.getCep());
            stmt.setInt(5, address.getId());
            stmt.executeUpdate();
            return true;
        }
        catch (SQLException sqlE) {
            Logger.getLogger(AddressDAO.class.getName()).log( Level.SEVERE, null, sqlE);
        }
        finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }

        return false;
    }

    /**
     *
     * @param id
     * @return
     */
    public Address read(int id) {
        Connection conn = ConnectionFactory.getConnection();

        String sql = "SELECT * FROM address WHERE id_address = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Address address = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            rs.next();
            address = AddressDAO.createInstance(rs);
        }
        catch (SQLException sqlE) {
            Logger.getLogger(AddressDAO.class.getName()).log( Level.SEVERE, null, sqlE);
        }
        finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }

        return address;
    }

    /**
     *
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<Address> readAll() throws SQLException, ClassNotFoundException {

        ArrayList<Address> addresses = new ArrayList<>();

        Connection conn = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM address";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()) {
                addresses.add(AddressDAO.createInstance(rs));
            }
        }
        catch (SQLException sqlE) {
            Logger.getLogger(AddressDAO.class.getName()).log( Level.SEVERE, null, sqlE);
        }
        finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }

        return addresses;
    }

    /**
     * Método estático responsável por criar a instancia do Address(Model).
     *
     * @param result
     * @return Address
     * @throws SQLException
     */
    public static Address createInstance(ResultSet result) {
        return createInstance( result, false);
    }

    /**
     *
     * @param result
     * @param isSelfClose
     * @return
     */
    public static Address createInstance(ResultSet result, boolean isSelfClose) {

        Address address = null;

        try {
            address = new Address();
            address.setId(result.getInt("id_address"));
            address.setStreet(result.getString("street"));
            address.setNumber(result.getInt("number"));
            address.setNeighborhood(result.getString("neighborhood"));
            address.setCep(result.getString("cep"));

            if (isSelfClose) {
                result.close();
            }
        }
        catch (SQLException sqlE) {
            Logger.getLogger(AddressDAO.class.getName()).log( Level.SEVERE, null, sqlE);
        }

        return address;
    }
}
