package dao.entity;

public interface DAO {

    public static int LAST_ID_INSERT = -1;

    public boolean create();
    public boolean update();
    public void load();
    public boolean delete();

}
