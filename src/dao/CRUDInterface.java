/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.ModelInterface;

/**
 *
 * @author felipemantoan
 */
public interface CRUDInterface {
    
    /**
     * 
     * @param model
     * @return 
     */
    public boolean create(ModelInterface model);
    
    /**
     * 
     * @param model
     * @return 
     */
    public boolean read(ModelInterface model);
    
    /**
     * 
     * @param model
     * @return 
     */
    public ArrayList readAll(ModelInterface model);
    
    /**
     * 
     * @param model
     * @return 
     */
    public boolean update(ModelInterface model);
    
    /**
     * 
     * @param model
     * @return 
     */
    public boolean delete(ModelInterface model);
}
