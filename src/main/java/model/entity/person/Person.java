/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity.person;

import model.entity.Entity;
import model.entity.address.Address;
import model.entity.phone.Phone;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felipemantoan
 */
public abstract class Person implements Entity{

    private int id = -1;
    private String name;

    private Address address;

    private ArrayList<Phone> listPhone = new ArrayList();

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Phone> getListPhone() {
        return listPhone;
    }

    public void setListPhone(ArrayList<Phone> listPhone) {
        this.listPhone = listPhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
