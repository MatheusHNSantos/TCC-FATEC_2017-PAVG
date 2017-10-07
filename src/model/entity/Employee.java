/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

/**
 *
 * @author felipemantoan
 */
public class Employee extends Person {
    private int idEmployee;
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
}
