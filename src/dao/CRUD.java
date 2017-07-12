/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Entity;
import java.util.ArrayList;

/**
 *
 * @author felipemantoan
 */
abstract class CRUD {
    
    public abstract boolean create();
    public abstract boolean read(int id);
    public abstract ArrayList readAll();
    public abstract boolean update(Entity entity, int id);
    public abstract boolean delete();
}
