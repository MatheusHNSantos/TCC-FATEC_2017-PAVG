/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity.person;

/**
 *
 * @author Matheus Henrique
 */
public class CostumerDAO extends PersonDAO {

    public static int LAST_ID_INSERT = -1;

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
