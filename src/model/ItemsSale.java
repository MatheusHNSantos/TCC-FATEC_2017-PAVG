/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Matheus Henrique
 */
public class ItemsSale implements ModelInterface{
    private int idItemsSale;
    private int idSale;
    private int idProduct;

    public int getIdItemsSale() {
        return idItemsSale;
    }

    public void setIdItemsSale(int idItemsSale) {
        this.idItemsSale = idItemsSale;
    }

    public int getIdSale() {
        return idSale;
    }

    public void setIdSale(int idSale) {
        this.idSale = idSale;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }
    
}
