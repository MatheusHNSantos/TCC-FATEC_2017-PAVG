package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.JDBCType;
import java.sql.SQLException;
/**
 *
 * @author felipemantoan
 */
public abstract class ConnectionFactory implements Connection{
    
    ConnectionFactory() {
    }
}
