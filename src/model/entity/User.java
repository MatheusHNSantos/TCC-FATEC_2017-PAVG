/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import model.exception.UserException;



/**
 *
 * @author felipemantoan
 */
public class User {
    
    private String login;
    private String password;
    private int idEmployee;

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }
    
    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) throws UserException {
        if (login.isEmpty()) {
            throw new UserException("O login não pode ser nulo!");
        }
        
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws UserException {
        
        if (password.isEmpty()) {
            throw new UserException("A senha não pode ser nula!");
        }
              
        this.password = password;
    }
}
