/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity.address;

import dao.entity.address.AddressDAO;
import model.entity.Entity;

import java.sql.SQLException;

/**
 *
 * @author Matheus Henrique
 */
public class Address implements Entity {
    private int id = -1;
    private String street;
    private int number;
    private String neighborhood;
    private String cep;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public void save() throws SQLException, ClassNotFoundException {
        if (this.id > -1) {
            AddressDAO.update(this);
        }
        else{
            AddressDAO.create(this);
        }
    }
}
