/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity.person.costumer;

import dao.entity.person.CostumerDAO;
import model.entity.person.Person;

import java.sql.SQLException;

/**
 *
 * @author felipemantoan
 */
public class Costumer extends Person {
    
    private String CPF;
    private String RG;
    private CostumerDAO dao;

    public Costumer() {
        this.dao = new CostumerDAO();
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }
    
    public boolean validateCPF() {
        return false;
    }
    
    public boolean validateRG() {
        return false;
    }

    @Override
    public boolean save() {
        if (super.getId() == -1) {
            if (this.dao.create(this)) {
                super.setId( CostumerDAO.LAST_ID_INSERT );
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
