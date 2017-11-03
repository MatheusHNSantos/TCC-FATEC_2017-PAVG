/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity.sale;

import model.entity.product.Product;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matheus Henrique
 */
public class Sale {
    private int idSale;
    private int idUser;
    private int idCostumer;
    private float saleTotal;
    private java.sql.Time saleTime;
    private java.sql.Date saleDate;
    private java.sql.Time saleTimeEstimate;
    private List<Product> productsList = new ArrayList();

    public List<Product> getItemsSaleList() {
        return productsList;
    }

    public void setItemsSaleList(List<Product> productsList) {
        this.productsList = productsList;
    }

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

    public float getSaleTotal() {
        return saleTotal;
    }

    public void setSaleTotal(float saleTotal) {
        this.saleTotal = saleTotal;
    }

    public Time getSaleTimeEstimate() {
        return saleTimeEstimate;
    }

    public void setSaleTimeEstimate(Time saleTimeEstimate) {
        this.saleTimeEstimate = saleTimeEstimate;
    }
}
