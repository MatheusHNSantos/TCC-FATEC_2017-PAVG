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
    private final String driver;
    private final String login;
    private final String pass;
    private final String database;
    private final String host;
    private final String port;
    private final String extras;
    private final String dns;
    
    public ConnectionFactory() {
        
        /** @driver
         * Atributo referente ao SGBD que no caso é o MySQL/MariaDB
         */
        this.driver = "mysql";
        
        /**
         * @login 
         * Normalmente é o nome do usuário que acessa o BD
         */
        this.login = "root";
        
        /**
         * @pass
         * Normalmente é o nome do usuário que acessa o BD
         */
        this.pass = "root";
        
        /**
         * @database
         * Deve ser o nome do banco de dados
         */
        this.database = "BancoTeste";
        
        /**
         * @host
         * Quando temos um SGBD instalado em ambiente local o padrão pode ser
         * localhost ou 127.0.0.1
         */
        this.host = "localhost";
        
        /**
         * @port
         * Por padrão a maioria dos SGBDs fornecem uma porta de acesso
         */
        this.port = "3306";
        
        /**
         * @extras
         * Por convenção deve-se passar junto ao dns de conexão
         * dois parâmetros semelhantes ao método GET/HTTP
         */
        this.extras = "?autoReconnect=true&useSSL=false";
        
        /**
         * @dns
         * É o dominio que a aplicação deve acessar para operar o BD
         */
        this.dns = "jdbc:" + this.driver + "://" + this.host + ":" + this.port +"/" + this.database + this.extras;
    }
    
    /**
     * 
     * @return Instância da classe Connection
     * @throws SQLException 
     */
    public Connection getConnection() throws SQLException{
        if (this.conn == null) {
            this.conn = DriverManager.getConnection(this.dns, this.login, this.pass);
        }

        return this.conn;
    }
}
