/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity.person;

import dao.entity.address.AddressDAO;
import dao.entity.person.PersonDAO;
import model.entity.person.costumer.Costumer;
import util.connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Matheus Henrique
 */
public class CostumerDAO extends PersonDAO {

    public static int LAST_ID_INSERT = -1;

    public static boolean create(Costumer costumer) throws SQLException, ClassNotFoundException {
        PersonDAO.create(costumer);
        Connection conn = ConnectionFactory.getConnection();
        String sql = "INSERT INTO costumer (rg, cpf, id_person) VALUES (?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

        stmt.setString(1, costumer.getRG());
        stmt.setString(2, costumer.getCPF());
        stmt.setInt(3, PersonDAO.LAST_ID_INSERT);
        boolean affectedRows = stmt.execute();
        ConnectionFactory.closeConnection(conn, stmt);
        return affectedRows;
    }

    public static boolean update(Costumer costumer) throws SQLException, ClassNotFoundException {
        PersonDAO.update(costumer);

        Connection conn = ConnectionFactory.getConnection();
        String sql = "UPDATE costumer SET rg = ?, cpf = ? WHERE id_person = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, costumer.getRG());
        stmt.setString(2, costumer.getCPF());
        stmt.setInt(3, costumer.getId());

        int affectedRows = stmt.executeUpdate();
        ConnectionFactory.closeConnection(conn, stmt);

        if (affectedRows > 0) {
            return true;
        }

        return false;
    }

    public static Costumer load(int id) throws SQLException, ClassNotFoundException {
        Connection conn = ConnectionFactory.getConnection();

        String sql = "SELECT costumer.rg, costumer.cpf, " +
                "person.name_person, address.id_address, " +
                "address.street, address.number,address.cep, " +
                "address.neighborhood " +
                "FROM costumer" +
                " INNER JOIN person ON person.id_person = costumer.id_person " +
                "INNER JOIN address ON address.id_address = person.id_address " +
                "WHERE costumer.id_person = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        Costumer c = CostumerDAO.createInstance(rs);
        ConnectionFactory.closeConnection(conn, stmt, rs);
        return c;
    }
/*
    public static ArrayList<Employee> loadAll() throws SQLException, ClassNotFoundException {

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
        PreparedStatement stmt = conn.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {
            employees.add(EmployeeDAO.createInstance(rs));
        }
        return employees;
    }
*/
    public static Costumer createInstance(ResultSet result) throws SQLException {
        Costumer costumer = new Costumer ();

        costumer.setId(result.getInt("id_person"));
        costumer.setName(result.getString("name_person"));
        costumer.setAddress( AddressDAO.createInstance(result));
        costumer.setCPF( result.getString( "cpf" ) );
        costumer.setRG( result.getString( "rg" ) );
        return costumer;
    }

}
