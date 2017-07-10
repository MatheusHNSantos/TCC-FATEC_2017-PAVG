package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author felipemantoan
 */
public abstract class ConnectionFactory{
    
    private Connection conn;
    private String driver;
    private String login;
    private String senha;
    private String banco;
    private String host;
    private String porta;
    private String extras;
    private final String dns;
    
    ConnectionFactory() {
        
        this.driver = "mysql";
        this.login = "root";
        this.senha = "root";
        this.banco = "BancoTeste";
        this.host = "localhost";
        this.porta = "3306";
        this.extras = "?autoReconnect=true&useSSL=false";
        this.dns = "jdbc:" + this.driver + "://" + this.host + ":" + this.porta +"/" + this.banco + this.extras;
    }
      
    public Connection getConnection() throws SQLException{
        if (this.conn == null) {
            this.conn = DriverManager.getConnection(this.dns, this.login, this.senha);
        }
        else {
            return this.conn;
        }
        
        return null;
    }
}
