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
    private boolean isNew;

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

    @Override
    public boolean save() throws SQLException, ClassNotFoundException {
        if (this.getId() == -1) {
            boolean created = CostumerDAO.create(this);
            isNew = created;
            this.setId(CostumerDAO.LAST_ID_INSERT);
            return created;
        }

        return CostumerDAO.update(this);
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

}
