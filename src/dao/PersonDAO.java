/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.ModelInterface;
import model.Person;

/**
 *
 * @author felipemantoan
 */
abstract class PersonDAO implements CRUDInterface{
    
    @Override
    public boolean create(ModelInterface model) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        Person person = (Person) model;
  
        try {
            stmt = con.prepareStatement("insert into person values(id_address,name_person) values(?,?)");
            stmt.setString(1, person.getName());
            stmt.setInt(2, person.getIdAddress());
           
            
            stmt.executeUpdate();
            
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar: " + ex.getMessage());
            return false;
        }
        finally{
            ConnectionFactory.closeConnection(con,stmt);
            
        }
    }

    @Override
    public ModelInterface read(ModelInterface model) {
        return model;
    }

    @Override
    public List<ModelInterface> readAll(ModelInterface model) {
        return new ArrayList();
    }

    @Override
    public boolean update(ModelInterface model) {
        return false;
    }

    @Override
    public boolean delete(ModelInterface model) {
        return false;
    }
    
}
