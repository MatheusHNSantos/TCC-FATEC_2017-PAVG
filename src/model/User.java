/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author felipemantoan
 */
public class User extends Employee implements ModelInterface{
    
    private String login;
    private String password;

    User() {
        this.id = -1;
        this.login = "";
        this.password = "";
    }
    
    User(String login, String password) {
        this.login = login;
        this.password = password;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) throws Exception {
        if (login.equals("")) {
            throw new Exception("O login não pode ser nulo!");
        }
        
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws Exception {
        
        if (password.equals("")) {
            throw new Exception("A senha não pode ser nula!");
        }
              
        this.password = password;
    }
}
