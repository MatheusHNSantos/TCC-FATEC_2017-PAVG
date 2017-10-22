/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity.person;

import model.entity.person.Person;
import model.entity.person.employee.Employee;
import util.connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Matheus Henrique
 */
public class EmployeeDAO extends PersonDAO {
    public static int LAST_ID_INSERT = -1;

    @Override
    public boolean create(){return false;}

    public static boolean create(Employee employee) throws SQLException {
        PersonDAO.create(employee);
        Connection conn = ConnectionFactory.getConnection();
        String sql = "INSERT INTO employee (role, id_person) VALUES (?, ?)";

        PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

        stmt.setString(1, employee.getRole());
        stmt.setInt(2, PersonDAO.LAST_ID_INSERT);
        stmt.execute();

        ResultSet rs = stmt.getGeneratedKeys();
        LAST_ID_INSERT = rs.next() ? rs.getInt(1) : -1;
        ConnectionFactory.closeConnection(conn, stmt, rs);
        return true;
    }

    @Override
    public boolean update() {return false;}

    public static boolean update(Employee employee) throws SQLException {
        PersonDAO.update(employee);

        Connection conn = ConnectionFactory.getConnection();
        String sql = "UPDATE employee SET role = ? WHERE id_employee = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, employee.getRole());
        stmt.setInt(2, employee.getId_employee());

        stmt.executeUpdate();
        ConnectionFactory.closeConnection(conn, stmt);
        return true;
    }

    @Override
    public void load(){return;}

    @Override
    public boolean delete(){return false;}

    @Override
    public void createInstance() {

    }
}
