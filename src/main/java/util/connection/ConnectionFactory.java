package util.connection;

import util.dialogs.FxDialogs;

import java.sql.*;

/**
 *
 * @author Matheus Henrique
 */
public abstract class ConnectionFactory{


        /**
     *
     * @return Instância da classe Connection
     * @throws SQLException
     */

    /** @DRIVER
     * É responsável por definir a biblioteca de conexão.
     */
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    /**
     * @DATABASE
     * Deve ser o nome do banco de dados
     */
    private static final String DATABASE = "apetitoso";


    /**
     * @EXTRAS
     * Seta timezone regional e SLL = false
     */
    private static final String EXTRAS = "?useTimezone=true&serverTimezone=UTC&useSSL=false";

    /**
     * @URL
     * É o dns aonde o banco esta alocado
     */
    private static final String URL = "jdbc:mysql://localhost:3306/" + DATABASE + EXTRAS;

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
     */
    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);

        } catch (ClassNotFoundException | SQLException ex) {
            FxDialogs.showException("Falha na conexão com o banco de dados!", ex.getMessage(),ex);
            throw new RuntimeException("Erro na conexão: " + ex.getMessage());

        }

    }


    /**
     * Este Método é responsável pelo fechamento da conexão
     * 
     * @param con
     */
    public static void closeConnection(Connection con){
        try {
            if (!con.isClosed()) {
                con.close();
            }
        } catch (SQLException ex) {
            FxDialogs.showException("Falha ao encerrar conexão com o banco de dados!",ex.getMessage(),ex);
            //Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Este Método é responsṕavel pelo fechamento da conexão e do Statement
     * 
     * @param con
     * @param stmt
     */
    public static void closeConnection(Connection con, PreparedStatement stmt){
        try {
            closeConnection(con);
            if (!stmt.isClosed()) {
                stmt.close();
            }
        } catch (SQLException ex) {
            FxDialogs.showException("Falha ao encerrar conexão com o banco de dados!",ex.getMessage(),ex);
            //Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Este Método é responsṕavel pelo fechamento da conexão, Statement e do resultado
     *
     * @param con
     * @param stmt
     * @param rs
     */
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
        try {
            closeConnection(con, stmt);
            if (!rs.isClosed()) {
                rs.close();
            }
        } catch (SQLException ex) {
            FxDialogs.showException("Falha ao encerrar conexão com o banco de dados!",ex.getMessage(),ex);
            //Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}
