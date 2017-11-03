/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity.person;

import dao.entity.address.AddressDAO;
import model.entity.person.employee.Employee;
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
public class EmployeeDAO extends PersonDAO {

    public static int LAST_ID_INSERT = -1;

    /**
     *
     * @param employee
     * @return
     */
    public boolean create(Employee employee) {
        super.create(employee);
        Connection conn = ConnectionFactory.getConnection();
        String sql = "INSERT INTO employee (role, id_person) VALUES (?, ?)";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, employee.getRole());
            stmt.setInt(2, PersonDAO.LAST_ID_INSERT);
            stmt.execute();
            rs = stmt.getGeneratedKeys();
            rs.next();
            LAST_ID_INSERT = rs.getInt(1);
            return true;
        }
        catch (SQLException sqlE) {
            Logger.getLogger(EmployeeDAO.class.getName()).log( Level.SEVERE, null, sqlE);
        }
        finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }

        return false;
    }

    /**
     *
     * @param employee
     * @return
     */
    public boolean update(Employee employee) {
        super.update(employee);

        Connection conn = ConnectionFactory.getConnection();
        String sql = "UPDATE employee SET role = ? WHERE id_employee = ?";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, employee.getRole());
            stmt.setInt(2, employee.getId_employee());
            stmt.executeUpdate();
        }
        catch (SQLException sqlE) {
            Logger.getLogger(EmployeeDAO.class.getName()).log( Level.SEVERE, null, sqlE);
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
    public Employee read(int id) {
        Connection conn = ConnectionFactory.getConnection();

        String sql = "SELECT " +
            "employee.id_employee, employee.role, " +
            "person.id_person, person.name_person, " +
            "address.id_address, address.street, " +
            "address.number, address.cep, address.neighborhood " +
            "FROM employee " +
            "INNER JOIN person ON person.id_person = employee.id_person " +
            "INNER JOIN address ON address.id_address = person.id_address " +
            "WHERE id_employee = ?";

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Employee employee = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            rs.next();
            employee = EmployeeDAO.createInstance(rs);
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        catch (SQLException sqlE) {
            Logger.getLogger(EmployeeDAO.class.getName()).log( Level.SEVERE, null, sqlE);
        }
        finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }

        return employee;
    }

    /**
     *
     * @return
     */
    public ArrayList<Employee> readAll() {

        ArrayList<Employee> employees = new ArrayList<>();

        Connection conn = ConnectionFactory.getConnection();
        String sql = "SELECT " +
                "employee.id_employee, employee.role, " +
                "person.id_person, person.name_person, " +
                "address.id_address, address.street, " +
                "address.number, address.cep, address.neighborhood " +
                "FROM employee " +
                "INNER JOIN person ON person.id_person = employee.id_person " +
                "INNER JOIN address ON address.id_address = person.id_address";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                employees.add(EmployeeDAO.createInstance(rs));
            }
        }
        catch (SQLException sqlE) {
            Logger.getLogger(EmployeeDAO.class.getName()).log( Level.SEVERE, null, sqlE);
        }
        finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }

        return employees;
    }

    /**
     *
     * @param result
     * @return
     */
    public static Employee createInstance (ResultSet result) {

        Employee employee = new Employee();

        try {
            employee.setId( result.getInt( "id_person" ) );
            employee.setName( result.getString( "name_person" ) );
            employee.setAddress( AddressDAO.createInstance( result ) );

            employee.setId_employee( result.getInt( "id_employee" ) );
            employee.setRole( result.getString( "role" ) );
        }
        catch (SQLException sqlE) {
            Logger.getLogger(EmployeeDAO.class.getName()).log( Level.SEVERE, null, sqlE);
        }

        return employee;
    }
}
