package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Matheus Henrique
 */
public abstract class ConnectionFactory{
    
    /** @DRIVER
     * É responsável por definir a biblioteca de conexão.
     */
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    
    /**
     * @DATABASE
     * Deve ser o nome do banco de dados
     */
    private static final String DATABASE = "apetitoso";
    
    /**
     * @URL
     * É o dns aonde o banco esta alocado
     */
    private static final String URL = "jdbc:mysql://localhost:3306/" + DATABASE;
    
    /**
     * @USER
     * Normalmente é o nome do usuário que acessa o BD
     */
    private static final String USER = "root";
    
    /**
     * @PASS
     * É a senha do usuário banco de dados
     */
    private static final String PASS = "";
    
    /**
     * 
     * @return Instância da classe Connection
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USER, PASS); 
    }
    
    /**
     * Este Método é responsável pelo fechamento da conexão
     * 
     * @param con
     * @throws SQLException
     */
    public static void closeConnection(Connection con) throws SQLException{
        if (!con.isClosed()) {
          con.close();  
        } 
    }
    
    /**
     * Este Método é responsṕavel pelo fechamento da conexão e do Statement
     * 
     * @param con
     * @param stmt
     * @throws SQLException
     */
    public static void closeConnection(Connection con, PreparedStatement stmt) throws SQLException{
        closeConnection(con); 
        if (!stmt.isClosed()) {
            stmt.close();
        }
    }
    
    /**
     * Este Método é responsṕavel pelo fechamento da conexão, Statement e do resultado
     * 
     * @param con
     * @param stmt
     * @param rs
     * @throws SQLException
     */
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) throws SQLException{
        closeConnection(con, stmt);
        if (!rs.isClosed()) {
            rs.close();
        }
    } 
   
}
