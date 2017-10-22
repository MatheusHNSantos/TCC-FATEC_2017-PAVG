/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity.person;

import model.entity.person.Person;

/**
 *
 * @author Matheus Henrique
 */
public class EmployeeDAO extends PersonDAO {

    @Override
    public boolean create(){return false;}

    public boolean create(Person person){
        return false;
    }

    @Override
    public boolean update() {return false;}

    public boolean update(Person person){
        return false;
    }

    @Override
    public void load(){return;}

    @Override
    public boolean delete(){return false;}
}
