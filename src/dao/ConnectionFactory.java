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
    private final String dns;
    
    ConnectionFactory() {
        
        this.driver = "mysql";
        this.login = "root";
        this.senha = "root";
        this.banco = "BancoTeste";
        this.host = "localhost";
        this.porta = "3306";
        this.dns = "jdbc:" + this.driver + "://" + this.host + ":" + this.porta +"/" + banco;
    }
    
    public void debug(){
        System.out.println(this.dns);
    }
    
    public Connection getConnection() throws SQLException, ClassNotFoundException{
        if (this.conn == null) {
            this.conn = DriverManager.getConnection(this.dns, this.login, this.senha);
        }
        else {
            return this.conn;
        }
        
        return null;
    }
}
