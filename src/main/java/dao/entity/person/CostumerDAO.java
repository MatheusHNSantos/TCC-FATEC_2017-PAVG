/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity.person;

import dao.entity.person.PersonDAO;

/**
 *
 * @author Matheus Henrique
 */
public class CostumerDAO extends PersonDAO {

    @Override
    public boolean create() {
        return false;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public void load() {

    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public void createInstance() {

    }
}
