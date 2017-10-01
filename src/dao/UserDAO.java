package dao;


import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.ModelInterface;
import model.User;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author felipemantoan
 */
public class UserDAO extends PeopleDAO implements CRUDInterface{
    
    private ConnectionFactory factory;
   
    public boolean doLogin(User user) {
     
        /*try { //Ativar para SQlite
            ConnectionFactory.checkDatabase();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha ao verificar existencia de base de dados: " + ex.getMessage());
        }*/
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            boolean validacao = false;
            
            try {
                stmt = con.prepareStatement("select * from User where login = ? and password = ?");
                stmt.setString(1, user.getLogin());
                stmt.setString(2, user.getPassword());
                
                rs = stmt.executeQuery();
                
                if (rs.next()){
                    validacao  = true;
                }
                stmt.close();
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Falha ao verificar login: " + ex.getMessage());
                validacao  = false;
            }finally{
                ConnectionFactory.closeConnection(con, stmt, rs);
            }
            
            return validacao;
    } 

    @Override
    public boolean create(ModelInterface model) {
       String people = "";
       return false;
    }

    @Override
    public boolean read(ModelInterface model) {
        return false;
    }

    @Override
    public ArrayList readAll(ModelInterface model) {
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
