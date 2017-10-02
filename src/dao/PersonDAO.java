/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.ModelInterface;

/**
 *
 * @author felipemantoan
 */
abstract class PersonDAO implements CRUDInterface{
    
    @Override
    public boolean create(ModelInterface model) {
        return false;
    }

    @Override
    public ModelInterface read(ModelInterface model) {
        return model;
    }

    @Override
    public List<ModelInterface> readAll(ModelInterface model) {
        return new ArrayList();
    }

    @Override
    public boolean update(ModelInterface model) {
        return false;
    }

    @Override
    public boolean delete(ModelInterface model) {
        return false;
    }
    
}
