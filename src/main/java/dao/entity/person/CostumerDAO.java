/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity.person;

import dao.entity.address.AddressDAO;
import model.entity.person.costumer.Costumer;
import util.connection.ConnectionFactory;

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
public class CostumerDAO extends PersonDAO {

    public static int LAST_ID_INSERT = -1;

    /**
     *
     * @param costumer
     * @return
     */
    public static boolean create(Costumer costumer) {
        PersonDAO.create(costumer);
        Connection conn = ConnectionFactory.getConnection();
        String sql = "INSERT INTO costumer (rg, cpf, id_person) VALUES (?, ?, ?)";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, costumer.getRG());
            stmt.setString(2, costumer.getCPF());
            stmt.setInt(3, PersonDAO.LAST_ID_INSERT);
            stmt.execute();
            return true;
        }
        catch (SQLException sqlE) {
            Logger.getLogger(CostumerDAO.class.getName()).log( Level.SEVERE, null, sqlE);
        }
        finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }

        return false;
    }

    /**
     *
     * @param costumer
     * @return
     */
    public static boolean update(Costumer costumer) {
        PersonDAO.update(costumer);

        Connection conn = ConnectionFactory.getConnection();
        String sql = "UPDATE costumer SET rg = ?, cpf = ? WHERE id_person = ?";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, costumer.getRG());
            stmt.setString(2, costumer.getCPF());
            stmt.setInt(3, costumer.getId());
            stmt.executeUpdate();
            return true;
        }
        catch (SQLException sqlE) {
            Logger.getLogger(CostumerDAO.class.getName()).log( Level.SEVERE, null, sqlE);
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
    public static Costumer read(int id) {
        Connection conn = ConnectionFactory.getConnection();

        String sql = "SELECT costumer.rg, costumer.cpf, " +
                "person.name_person, person.id_person, address.id_address, " +
                "address.street, address.number,address.cep, " +
                "address.neighborhood " +
                "FROM costumer " +
                "INNER JOIN person ON person.id_person = costumer.id_person " +
                "INNER JOIN address ON address.id_address = person.id_address " +
                "WHERE costumer.id_person = ?";

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Costumer costumer = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            rs.next();
            costumer = CostumerDAO.createInstance(rs);
        }
        catch (SQLException sqlE) {
            Logger.getLogger(CostumerDAO.class.getName()).log( Level.SEVERE, null, sqlE);
        }
        finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }

        return costumer;
    }

    /**
     *
     * @return
     */
    public static ArrayList<Costumer> readAll () {

        ArrayList<Costumer> costumers = new ArrayList<>();

        Connection conn = ConnectionFactory.getConnection();
        String sql = "SELECT costumer.rg, costumer.cpf, " +
                "person.name_person, person.id_person ,address.id_address, " +
                "address.street, address.number, " +
                "address.cep, address.neighborhood " +
                "FROM costumer " +
                "INNER JOIN person ON person.id_person = costumer.id_person " +
                "INNER JOIN address ON address.id_address = person.id_address";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()) {
                costumers.add(CostumerDAO.createInstance(rs));
            }
        }
        catch (SQLException sqlE) {
            Logger.getLogger(CostumerDAO.class.getName()).log( Level.SEVERE, null, sqlE);
        }
        finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }

        return costumers;
    }

    public static Costumer createInstance(ResultSet result) throws SQLException {

        Costumer costumer = new Costumer ();

        try {
            costumer.setId(result.getInt("id_person"));
            costumer.setName(result.getString("name_person"));
            costumer.setAddress( AddressDAO.createInstance(result));
            costumer.setCPF( result.getString( "cpf" ) );
            costumer.setRG( result.getString( "rg" ) );
        }
        catch (SQLException sqlE) {
            Logger.getLogger(CostumerDAO.class.getName()).log( Level.SEVERE, null, sqlE);
        }

        return costumer;
    }
}
