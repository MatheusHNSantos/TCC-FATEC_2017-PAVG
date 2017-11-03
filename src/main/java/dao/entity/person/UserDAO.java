package dao.entity.person;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import model.entity.person.user.User;
import util.connection.ConnectionFactory;
import util.dialogs.FxDialogs;
import util.exception.UserException;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matheus Henrique
 */
public class UserDAO {
    public boolean doLogin(User user) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean validacao = false;


        try {
            stmt = con.prepareStatement("select * from user where login = ? and password = ?");
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());

            rs = stmt.executeQuery();

            if (rs.next()){
                validacao  = true;
            }
            stmt.close();

        } catch (SQLException ex) {
            FxDialogs.showException("Falha ao verificar login!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
            validacao  = false;
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return validacao;
    }
    public boolean create(User user) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        

        try {
            stmt = con.prepareStatement("insert into user (login, password, id_employee) values(?,?,?)");
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, user.getIdEmployee());
            
            stmt.executeUpdate();
            
            return true;
        } catch (SQLException ex) {
            FxDialogs.showException("Erro de Gravação! " ,getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
            return false;
        }
        finally{
            ConnectionFactory.closeConnection(con,stmt);
            
        }
       
    }


    public User read(User user) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

                
        try {
            stmt = con.prepareStatement("select * from user where login = ? ");
            stmt.setString(1, user.getLogin());
            rs = stmt.executeQuery();
            rs.next();
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setIdEmployee(rs.getInt("id_emplyoee"));

                
            
        } catch (SQLException ex) {
            FxDialogs.showException("Erro de Leitura!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
        } catch (UserException ex) {
            FxDialogs.showException("Erro no usuário ou senha!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return user;
    }


     public List<User> readAll() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<User> usuarios = new ArrayList<>();
                
        try {
            stmt = con.prepareStatement("select * from user");
            rs = stmt.executeQuery();
            
            while(rs.next()) {
               
                String login = rs.getString("login");
                String password = rs.getString("password");
                User user = new User(login,password);
                user.setIdEmployee(rs.getInt("id_employee"));
                usuarios.add(user);
                
                
            }
            
        } catch (SQLException ex) {
            FxDialogs.showException("Erro de Leitura!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
        } catch (UserException ex) {
            FxDialogs.showException("Erro ao ler login e senha!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return usuarios;
       
    }


    public boolean update(User user) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
            try {
            stmt = con.prepareStatement("update Usuario set login = ?, password = ?, id_employee = ? where login = ?");
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, user.getIdEmployee());
            stmt.setInt(4, user.getIdEmployee());
            
            stmt.executeUpdate();
            
            return true;
        } catch (SQLException ex) {
                FxDialogs.showException("Erro de Atualização!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
            return false;
        }
        finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
      
    }


    public boolean delete(User user) {
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("delete from user where id_employee = ?");
            stmt.setInt(1, user.getIdEmployee());
            
            stmt.executeUpdate();
            
            return true;
        } catch (SQLException ex) {
            FxDialogs.showException("Erro de Exclusão!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
            return false;
        }
        finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }

}
