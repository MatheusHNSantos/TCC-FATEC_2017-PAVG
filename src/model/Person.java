/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felipemantoan
 */
public abstract class Person implements ModelInterface {

    protected int idPerson;
    protected String name;
    private int idAndress;
    private List<Phone> listPhone = new ArrayList<>();
   

    public int getIdAndress() {
        return idAndress;
    }

    public void setIdAndress(int idAndress) {
        this.idAndress = idAndress;
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
