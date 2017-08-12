package dao;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
public class UserDAO implements CRUDInterface{
   
    public boolean doLogin(User usuario) {
        return false;
    }

    @Override
    public boolean create(ModelInterface model) {
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
