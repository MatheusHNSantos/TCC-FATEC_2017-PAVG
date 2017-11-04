/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity.person.supplier;

import dao.entity.person.SupplierDAO;
import model.entity.person.Person;

import java.sql.SQLException;

/**
 *
 * @author felipemantoan
 */
public class Supplier extends Person {

    private String CNPJ;

    private SupplierDAO dao;

    public Supplier () {
        this.dao= new SupplierDAO();

    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }


    @Override
    public boolean save(){
        if (super.getId() == -1) {
            if (this.dao.create(this)) {
                this.setId( SupplierDAO.LAST_ID_INSERT );
                return true;
            }

            return false;
        }

        if (this.dao.update(this)) {
            return true;
        }

        return false;
    }
}
