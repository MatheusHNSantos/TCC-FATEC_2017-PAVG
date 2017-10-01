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
public class UserDAO extends PeopleDAO implements CRUDInterface{
    
    private ConnectionFactory factory;
   
    public boolean doLogin(User user) {
        return false;
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
