package connection;
import java.awt.HeadlessException;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matheus Henrique
 */
public abstract class ConnectionFactory{
    
    /*private Connection conn;
    private final String driver;
    private final String login;
    private final String pass;
    private final String database;
    private final String host;
    private final String port;
    private final String extras;
    private final String dns;
    
    public ConnectionFactory() {
        

        this.driver = "mysql";
        this.login = "root";
        this.pass = "root";
        this.database = "BancoTeste";
        this.host = "localhost";
        this.port = "3306";
        this.extras = "?autoReconnect=true&useSSL=false";
        this.dns = "jdbc:" + this.driver + "://" + this.host + ":" + this.port +"/" + this.database + this.extras;
    }*/
    
    
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE = "apetitoso";
    private static final String URL = "jdbc:mysql://localhost:3306/"+DATABASE;
    private static final String USER = "root";
    private static final String PASS = "";
    

    public static Connection getConnection(){
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS); 
             
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexão: ", ex);
        }   
    }
    
    public static void closeConnection(Connection con){
           try {
                if(con!=null){
                   con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt){
        
        closeConnection(con); 
        
        try {
            if(stmt != null){    
                 stmt.close();
            }
            
            
        } catch (SQLException ex) {
             Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs){
        
        closeConnection(con, stmt);
        
        try {
            if(rs != null){
                 rs.close();
           }
        } catch (SQLException ex) {
             Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }  
    } 
   
}
