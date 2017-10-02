package dao;


import connection.ConnectionFactory;
import exceptionPackage.UserException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author Matheus Henrique
 */
public class UserDAO extends PersonDAO implements CRUDInterface{
    
    public boolean doLogin(ModelInterface model) {
     
        /*try { //Ativar para SQlite
            ConnectionFactory.checkDatabase();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha ao verificar existencia de base de dados: " + ex.getMessage());
        }*/
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            boolean validacao = false;
            
            User user = (User) model;
            
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
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        User user = (User) model;
  
        try {
            stmt = con.prepareStatement("insert into user values(login, password, id_employee) values(?,?,?)");
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, user.getId_employee());
            
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
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        User user = (User) model;
                
        try {
            stmt = con.prepareStatement("select * from user where login = ? ");
            stmt.setString(1, user.getLogin());
            rs = stmt.executeQuery();
   
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setId_employee(rs.getInt("id_emplyoee"));

                
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao consultar: " + ex.getMessage());
        } catch (UserException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return user;
    }

    @Override
     public List<ModelInterface> readAll(ModelInterface model) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<ModelInterface> usuarios = new ArrayList<>();
                
        try {
            stmt = con.prepareStatement("select * from user");
            rs = stmt.executeQuery();
            
            while(rs.next()) {
               
                String login = rs.getString("login");
                String password = rs.getString("password");
                User user = new User(login,password);
                user.setId_employee(rs.getInt("id_employee"));
                usuarios.add(user);
                
                
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao consultar: " + ex.getMessage());
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return usuarios;
       
    }

    @Override
    public boolean update(ModelInterface model) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        User user = (User) model;
        
        try {
            stmt = con.prepareStatement("update Usuario set login = ?, password = ?, id_employee = ? where login = ?");
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, user.getId_employee());
            stmt.setInt(4, user.getId_employee());
            
            stmt.executeUpdate();
            
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar: " + ex.getMessage());
            return false;
        }
        finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
      
    }

    @Override
    public boolean delete(ModelInterface model) {
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        User user = (User) model;
        
        try {
            stmt = con.prepareStatement("delete from user where id_employee = ?");
            stmt.setInt(1, user.getId_employee());
            
            stmt.executeUpdate();
            
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir: " + ex.getMessage());
            return false;
        }
        finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }
        
 }
