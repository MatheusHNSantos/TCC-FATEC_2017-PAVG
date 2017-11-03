/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity.person.supplier;

import model.entity.person.Person;

import java.sql.SQLException;

/**
 *
 * @author felipemantoan
 */
public class Supplier extends Person {
    private String CNPJ;

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }


    @Override
    public boolean save(){
        return false;
    }
}
