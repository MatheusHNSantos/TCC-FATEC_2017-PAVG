/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felipemantoan
 */
public abstract class Person {

    protected int idPerson;
    protected String name;
    private int idAddress;
    private Address address;
    private List<Phone> listPhone = new ArrayList<>();

    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
   
    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public List<Phone> getListPhone() {
        return listPhone;
    }

    public void setListPhone(List<Phone> listPhone) {
        this.listPhone = listPhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
