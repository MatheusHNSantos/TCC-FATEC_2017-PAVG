/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity.product;

import dao.entity.product.IngredientDAO;
import model.entity.Entity;

/**
 *
 * @author Matheus Henrique
 */
public class Ingredient implements Entity {
    private int id;
    private String name;
    private boolean status;
    private float price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public boolean save() {
        if (IngredientDAO.create(this) ) {
            this.setId( IngredientDAO.LAST_ID_INSERT );
            return true;
        }
        return false;
    }
}
