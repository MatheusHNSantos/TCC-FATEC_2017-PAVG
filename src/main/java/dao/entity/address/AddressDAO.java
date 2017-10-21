/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity.address;

import dao.connection.ConnectionFactory;
import dao.entity.DAO;
import model.entity.address.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Matheus Henrique
 */
public class AddressDAO implements DAO {

    public static int LAST_ID_INSERT = -1;

    @Override
    public boolean create() {
        return false;
    }

    public static boolean create(Address address) throws ClassNotFoundException, SQLException {
        
        Connection conn = ConnectionFactory.getConnection();
        String sql = "INSERT INTO address (street, number, neighborhood, cep) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        
        stmt.setString(1, address.getStreet());
        stmt.setInt(2, address.getNumber());
        stmt.setString(3, address.getNeighborhood());
        stmt.setString(4, address.getCep());
        stmt.execute();
        
        ResultSet rs = stmt.getGeneratedKeys();
        LAST_ID_INSERT = rs.next() ? rs.getInt(1) : -1;
        ConnectionFactory.closeConnection(conn, stmt, rs);
        return true;
    }


    @Override
    public boolean update() {
        return false;
    }

    public static boolean update (Address address) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionFactory.getConnection();
        String sql = "UPDATE address SET street = ?, number = ?, neighborhood = ?,  cep = ? WHERE id_address = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, address.getStreet());
        stmt.setInt(2, address.getNumber());
        stmt.setString(3, address.getNeighborhood());
        stmt.setString(4, address.getCep());
        stmt.setInt(5, address.getId());
        stmt.executeUpdate();
        return true;
    }

    public Address load(int id) {
        return new Address();
    }

    public ArrayList<Address> loadAll() {
        return new ArrayList<Address>();
    }

    @Override
    public void load() {}

    @Override
    public boolean delete() {return false;}
}
