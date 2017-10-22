/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity.person;

import dao.entity.DAO;
import model.entity.person.Person;


/**
 *
 * @author felipemantoan
 */
abstract class PersonDAO implements DAO{

    public static int LAST_ID_INSERT = -1;

    public boolean create(Person person){
        return false;
    }

    public boolean update(Person person){
        return false;
    }
}
