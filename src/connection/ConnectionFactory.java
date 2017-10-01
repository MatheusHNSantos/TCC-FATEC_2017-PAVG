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
    
 //MYSQL conexão ->
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE = "apetitoso";
    private static final String URL = "jdbc:mysql://localhost:3306/"+DATABASE;
    private static final String USER = "root";
    private static final String PASS = "";
    
    
    //SQLITE conexão ->
    /*private static final String DRIVER = "org.sqlite.JDBC";
    private static final String DATABASE = "CSdb.dat";
    private static final String URL = "jdbc:sqlite:data/"+DATABASE;*/

    //SQLite conexão ->
   /* public static void checkDatabase() throws Exception { 
        File database = new File("data\\"+DATABASE);
        
        if(!database.exists()){
            createNewDatabase(database);   
        }
    }*/

    public static Connection getConnection(){
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);  //MYSQL conexão 
            //return DriverManager.getConnection(URL); //SQLITE conexão
             
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
        
        closeConnection(con); //ativar para MYSQL conexão
        
        try {
            if(stmt != null){    
                 stmt.close();
            }
            
            //closeConnection(con); //ativar para SQLite conexão
            
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
    
    //SQLite conexão ->
    /*public static void createNewDatabase(File database) throws Exception {
        
        database.getParentFile().mkdirs(); //Cria o diretorio pai do arquivo caso não exista
        database.createNewFile(); //Cria o arquivo do banco 
        
         if (!database.exists()) {   //Caso o arquivo ainda não exista, após os comandos acima, dispara exceção
               throw new Exception("Erro ao gravar o arquivo de banco de dados.");
         } 
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt;
        stmt = null;

        try {
            
            //Execução dos comandos sql para configuração inicial do banco
            stmt = con.prepareStatement("CREATE TABLE IF NOT EXISTS User ("
                    + "id_usuario integer primary key autoincrement, "
                    + "login varchar(20) not null unique, "
                    + "password varchar(20) not null, "
                    + "nivel varchar(10) not null, "
                    + "foreign key(nivel)  references Nivel(nivel) "
                    + ");");
            stmt.executeUpdate();
            


            stmt = con.prepareStatement("CREATE TABLE IF NOT EXISTS Nivel ("
                    + "nivel varchar(10) primary key "
                    + ");");
            stmt.executeUpdate();
            
            
            //inserindo usuario padrão inicial
            stmt = con.prepareStatement("insert into User (login, password, nivel) values(?,?,?)");
            stmt.setString(1, "admin"); //Login
            stmt.setString(2, "admin"); //Password
            stmt.setString(3, "admin"); //Nível
            stmt.executeUpdate();
            
            //inserindo niveis de usuários
            stmt = con.prepareStatement("insert into Nivel (nivel) values(?),(?)");
            stmt.setString(1, "admin"); //Administrador
            stmt.setString(2, "user"); //Usuário
            stmt.executeUpdate();
            

            
        } catch (SQLException | HeadlessException ex) {
            throw new Exception("Erro na criação do banco de dados: " + ex.getMessage());
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }*/
}
