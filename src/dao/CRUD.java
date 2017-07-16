/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;

/**
 *
 * @author felipemantoan
 */
abstract class CRUD {
    
    public boolean create() {
     return false;   
    }
    
    public boolean read() {
        return false;
    }
    
    public ArrayList readAll() {
        return new ArrayList();
    }
    
    public boolean update() {
        return false;
    }
    
    public boolean delete() {
        return false;
    }
}
