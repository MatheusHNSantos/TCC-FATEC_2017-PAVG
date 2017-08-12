package dao;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Usuario;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author felipemantoan
 */
public class UsuarioDAO extends CRUD{

    public boolean create(Usuario $usuario) {
        return false;
    }

    public boolean read(int id) {
        return false;
    }

    public boolean update() {
        return false;
    }

    public boolean delete() {
        return false;
    }

    public ArrayList readAll() {
        return new ArrayList();
    }
    
    public boolean doLogin(Usuario usuario) {
        
        try {
            Connection c = super.getConnection();

        }
        catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return false;
    }
}
