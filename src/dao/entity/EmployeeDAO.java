/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity;

import dao.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.ModelInterface;
import model.entity.Employee;

/**
 *
 * @author Matheus Henrique
 */
public class EmployeeDAO extends PersonDAO {
    
    @Override
    public boolean create(ModelInterface model) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        Employee employee = (Employee) model;
  
        try {
            stmt = con.prepareStatement("insert into employee values(role,id_person) values(?,?)");
            stmt.setString(1, employee.getRole());
            stmt.setInt(2, employee.getIdPerson());
           
            
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
    
    
}
