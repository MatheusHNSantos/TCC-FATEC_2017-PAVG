/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity.person.employee;

import dao.entity.person.EmployeeDAO;
import model.entity.Entity;
import model.entity.person.Person;

import java.sql.SQLException;

/**
 *
 * @author felipemantoan
 */
public class Employee extends Person implements Entity{
    private int idEmployee = -1;
    protected String role;

    public int getId_employee() {
        return idEmployee;
    }

    public void setId_employee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public void save() throws SQLException, ClassNotFoundException {
        if (idEmployee == -1) {
            EmployeeDAO.create(this);
            this.setId_employee(EmployeeDAO.LAST_ID_INSERT);
        }
        else{
            EmployeeDAO.update(this);
        }
    }
}
