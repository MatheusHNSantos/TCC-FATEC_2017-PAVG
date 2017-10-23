package model.entity;

import java.sql.SQLException;

public interface Entity {

    public boolean isNew = false;

    public boolean save() throws SQLException, ClassNotFoundException;

    public boolean isNew();
}
