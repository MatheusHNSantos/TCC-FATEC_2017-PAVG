/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Matheus Henrique
 */
public class Sale implements ModelInterface{
    private int idSale;
    private int idUser;
    private int idCostumer;
    private java.sql.Time saleTime;
    private java.sql.Date saleDate;
    private int saleTimeEstimate;

    public int getIdSale() {
        return idSale;
    }

    public void setIdSale(int idSale) {
        this.idSale = idSale;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdCostumer() {
        return idCostumer;
    }

    public void setIdCostumer(int idCostumer) {
        this.idCostumer = idCostumer;
    }

    public Time getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(Time saleTime) {
        this.saleTime = saleTime;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public int getSaleTimeEstimate() {
        return saleTimeEstimate;
    }

    public void setSaleTimeEstimate(int saleTimeEstimate) {
        this.saleTimeEstimate = saleTimeEstimate;
    }
    
    
}
