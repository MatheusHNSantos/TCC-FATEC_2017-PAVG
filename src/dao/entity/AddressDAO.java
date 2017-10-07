/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity;

import model.ModelInterface;
import dao.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.entity.Address;

/**
 *
 * @author Matheus Henrique
 */
public class AddressDAO {
    
    @Override
    public int create(ModelInterface model) throws ClassNotFoundException, SQLException {
        Address address = (Address)model;
        
        Connection conn = ConnectionFactory.getConnection();
        String sql = "INSERT INTO address (street, number, neighborhood, cep) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        
        stmt.setString(1, address.getStreet());
        stmt.setInt(2, address.getNumber());
        stmt.setString(3, address.getNeighborhood());
        stmt.setString(4, address.getCep());
        stmt.execute();
        
        ResultSet rs = stmt.getGeneratedKeys();
        int id = rs.next() ? rs.getInt(1) : 0;
        ConnectionFactory.closeConnection(conn,stmt, rs);
        return id;
    }

    @Override
    public ModelInterface read(ModelInterface model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ModelInterface> readAll(ModelInterface model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(ModelInterface model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(ModelInterface model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
