/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author felipemantoan
 */
public class Costumer extends People implements ModelInterface{
    
    private String CPF;
    private String RG;

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
    
    public boolean validarCPF() {
        return false;
    }
    
    public boolean validarRG() {
        return false;
    }
}
