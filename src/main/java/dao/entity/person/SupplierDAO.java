/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity.person;

import dao.entity.address.AddressDAO;
import dao.entity.person.PersonDAO;
import model.entity.person.supplier.Supplier;
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
public class SupplierDAO extends PersonDAO {

    public static int LAST_ID_INSERT = -1;

    public boolean create (Supplier supplier) {
        super.create(supplier);
        Connection conn = ConnectionFactory.getConnection();
        String sql = "INSERT INTO supplier (cnpj, id_person) VALUES (?, ?)";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, supplier.getCNPJ());
            stmt.setInt(2, PersonDAO.LAST_ID_INSERT);
            stmt.execute();
            return true;
        }
        catch (SQLException sqlE) {
            Logger.getLogger(SupplierDAO.class.getName()).log( Level.SEVERE, null, sqlE);
        }
        finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }

        return false;
    }

    public boolean update (Supplier supplier) {
        super.update(supplier);

        Connection conn = ConnectionFactory.getConnection();
        String sql = "UPDATE costumer SET cnpj = ? WHERE id_person = ?";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, supplier.getCNPJ());
            stmt.setInt(2, supplier.getId());
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

    public Supplier read(int id) {
        Connection conn = ConnectionFactory.getConnection();

        String sql = "SELECT supplier.cnpj, " +
                "person.name_person, person.id_person, address.id_address, " +
                "address.street, address.number,address.cep, " +
                "address.neighborhood " +
                "FROM supplier " +
                "INNER JOIN person ON person.id_person = costumer.id_person " +
                "INNER JOIN address ON address.id_address = person.id_address " +
                "WHERE costumer.id_person = ?";

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Supplier supplier = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            rs.next();
            supplier = SupplierDAO.createInstance(rs);
        }
        catch (SQLException sqlE) {
            Logger.getLogger(CostumerDAO.class.getName()).log( Level.SEVERE, null, sqlE);
        }
        finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }

        return supplier;
    }

    public ArrayList<Supplier> readAll () {

        ArrayList<Supplier> suppliers = new ArrayList<>();

        Connection conn = ConnectionFactory.getConnection();
        String sql = "SELECT supplier.cnpj, " +
                "person.name_person, person.id_person ,address.id_address, " +
                "address.street, address.number, " +
                "address.cep, address.neighborhood " +
                "FROM supplier " +
                "INNER JOIN person ON person.id_person = costumer.id_person " +
                "INNER JOIN address ON address.id_address = person.id_address";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()) {
                suppliers.add(SupplierDAO.createInstance(rs));
            }
        }
        catch (SQLException sqlE) {
            Logger.getLogger(SupplierDAO.class.getName()).log( Level.SEVERE, null, sqlE);
        }
        finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }

        return suppliers;
    }

    public static Supplier createInstance (ResultSet result) {

        Supplier supplier = new Supplier ();

        try {
            supplier.setId(result.getInt("id_person"));
            supplier.setName(result.getString("name_person"));
            supplier.setAddress( AddressDAO.createInstance(result));
            supplier.setCNPJ( result.getString( "cnpj" ) );
        }
        catch (SQLException sqlE) {
            Logger.getLogger(SupplierDAO.class.getName()).log( Level.SEVERE, null, sqlE);
        }

        return supplier;
    }
}
