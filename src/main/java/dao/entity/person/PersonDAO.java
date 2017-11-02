/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity.person;

import dao.entity.DAO;
import model.entity.person.Person;
import util.connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author felipemantoan
 */
abstract class PersonDAO implements DAO{

    public static int LAST_ID_INSERT = -1;

    public static boolean create(Person person) {

        Connection conn = ConnectionFactory.getConnection();
        String sql = "INSERT INTO person (id_address, name_person) VALUES (?, ?)";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, person.getAddress().getId());
            stmt.setString(2, person.getName());
            stmt.execute();
            rs = stmt.getGeneratedKeys();
            rs.next();
            LAST_ID_INSERT = rs.getInt(1);
            return true;
        }
        catch (SQLException sqlE) {
            Logger.getLogger(PersonDAO.class.getName()).log( Level.SEVERE, null, sqlE);
        }
        finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }

        return false;
    }

    public static boolean update(Person person) {

        Connection conn = ConnectionFactory.getConnection();
        String sql = "UPDATE person SET id_address = ?, name_person = ? WHERE id_person = ?";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, person.getAddress().getId());
            stmt.setString(2, person.getName());
            stmt.setInt(3, person.getId());

            stmt.execute();
            return true;
        }
        catch (SQLException sqlE) {
            Logger.getLogger(PersonDAO.class.getName()).log( Level.SEVERE, null, sqlE);
        }
        finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }

        return false;
    }
}
