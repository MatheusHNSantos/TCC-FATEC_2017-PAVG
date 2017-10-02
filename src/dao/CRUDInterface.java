/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
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
    public ModelInterface read(ModelInterface model);
    
    /**
     * 
     * @param model
     * @return 
     */
    public List<ModelInterface> readAll(ModelInterface model);
    
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
