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
    
    public boolean create(ModelInterface model);
    
    public boolean read(ModelInterface model);
    
    public ArrayList readAll(ModelInterface model);
    
    public boolean update(ModelInterface model);
    
    public boolean delete(ModelInterface model);
}
