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
public class Usuario implements Entity{
    
    private int id;
    private String login;
    private String senha;

    Usuario() {
        this.id = -1;
        this.login = "";
        this.senha = "";
    }
    
    Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) throws Exception {
        
        if (senha.equals("")) {
            throw new Exception("A senha não pode ser nula!");
        }
        
        this.senha = senha;
    }
    
    public boolean doLogin() {
        return false;
    }
    
    @Override
    public boolean save() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } 
}
