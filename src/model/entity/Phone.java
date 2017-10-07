/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

/**
 *
 * @author Matheus Henrique
 */
public class Phone {
    private int id;
    private String phone;
    private int idPerson;

    public int getId_person() {
        return idPerson;
    }

    public void setId_person(int idPerson) {
        this.idPerson = idPerson;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    
}
