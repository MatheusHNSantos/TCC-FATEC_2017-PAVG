package model.entity;

import java.sql.SQLException;

public interface Entity {

    int id = -1;

    public void save() throws SQLException, ClassNotFoundException;
}
