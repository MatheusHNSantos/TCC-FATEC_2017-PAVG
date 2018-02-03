package model.entity.person.user;

import util.connection.ConnectionFactory;
import util.dialogs.FxDialogs;
import util.exception.UserException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
* 
* @author Matheus Henrique
*/
public class User {
	private String login = "";
	private String password = "";
	private int idEmployee =0;
	private boolean status =false;
    private int level =0;

	public User(String login) throws UserException {
		this.setLogin(login);
		this.Load();
	}

	public User(String login, String password) throws UserException {
		if(login.isEmpty() && password.isEmpty()){
			throw new UserException("Digite um login e uma senha!") {};
		}else if (login.isEmpty()) {
			throw new UserException("Digite um login!") {};
		}else if (password.isEmpty()) {
			throw new UserException("Digite uma senha!") {};
		}else{
			this.login = login;
			this.password = password;
		}
	}

	public User(Integer idEmployee) {
		this.setIdEmployee(idEmployee);
		this.LoadByEmployee();
	}

	public User () {

    }

	private void Load(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			stmt = con.prepareStatement("SELECT * FROM user WHERE login = ?");
			stmt.setString(1, this.getLogin());
			rs = stmt.executeQuery();
			rs.next();
			this.setPassword(rs.getString("password"));
			this.setIdEmployee(rs.getInt("id_employee"));
            this.setStatus(rs.getBoolean("status"));
            this.setLevel(rs.getInt("level"));

		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Leitura!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
		} catch (UserException ex) {
            FxDialogs.showException("Erro no usuário ou senha!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
        } finally{
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
	}

	private void LoadByEmployee(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			stmt = con.prepareStatement("SELECT * FROM user WHERE id_employee = ?");
			stmt.setInt(1, this.getIdEmployee());
			rs = stmt.executeQuery();
			if(rs.next()) {
                this.setPassword(rs.getString("password"));
                this.setLogin(rs.getString("login"));
                this.setStatus(rs.getBoolean("status"));
                this.setLevel(rs.getInt("level"));
            }
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Leitura!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
		} catch (UserException ex) {
			FxDialogs.showException("Erro no usuário ou senha!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
		} finally{
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
	}

    public boolean doLogin() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean validacao = false;


        try {
            stmt = con.prepareStatement("select * from user where login = ? and password = ?");
            stmt.setString(1, this.getLogin());
            stmt.setString(2, this.getPassword());

            rs = stmt.executeQuery();

            if (rs.next()){
                validacao  = true;
            }
            stmt.close();

        } catch (SQLException ex) {
            FxDialogs.showException("Falha ao verificar login!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
            validacao  = false;
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return validacao;
    }

	public static ArrayList<User> ReadAll(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<User> usersList = new ArrayList<>();
		try{
			stmt = con.prepareStatement("SELECT login FROM user");
			rs = stmt.executeQuery();
			while(rs.next()){
				User user = new User(rs.getString("login"));
				usersList.add(user);
			}
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Leitura!","Class: User" + " - " + ex.getMessage(),ex);
		} catch (UserException ex) {
            FxDialogs.showException("Erro no usuário ou senha!","Class: User" + " - " + ex.getMessage(),ex);
        } finally{
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return usersList;
	}

	public void Save(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		try{
			stmt = con.prepareStatement("UPDATE user SET password = ?, id_employee = ?, status = ?, level = ? WHERE login= ?");
			stmt.setString(1, this.getPassword());
			stmt.setInt(2, this.getIdEmployee());
            stmt.setBoolean(3, this.getStatus());
            stmt.setInt(4, this.getLevel());
            stmt.setString(5, this.getLogin());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Atualização!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con,stmt);
		}
	}

    public void SaveByIdEmployee(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try{
            stmt = con.prepareStatement("UPDATE user SET password = ?, login = ?, status = ?, level = ? WHERE id_employee= ?");
            stmt.setString(1, this.getPassword());
            stmt.setString(2, this.getLogin());
            stmt.setBoolean(3, this.getStatus());
            stmt.setInt(4, this.getLevel());
            stmt.setInt(5, this.getIdEmployee());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            FxDialogs.showException("Erro de Atualização!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
        }
        finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }

	public void Create(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		try{
			stmt = con.prepareStatement("INSERT INTO user (login,password,id_employee,status,level) VALUES (?,?,?,?,?)");
            stmt.setString(1, this.getLogin());
			stmt.setString(2, this.getPassword());
			stmt.setInt(3, this.getIdEmployee());
            stmt.setBoolean(4, this.getStatus());
            stmt.setInt(5, this.getLevel());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Gravação! " ,getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con,stmt);
		}
	}

	public void Delete(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		try{
			stmt = con.prepareStatement("DELETE FROM user WHERE login = ?");
			stmt.setString(1, this.getLogin());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Exclusão!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con,stmt);
		}
	}

	public void setLogin(String login) throws UserException {
        if (login.isEmpty()) {
            throw new UserException("Login não pode ser nulo") {};
        }
		this.login = login;
	}

	public String getLogin(){
		return this.login;
	}

	public void setPassword(String password) throws UserException {
        if (password.isEmpty()) {
            throw new UserException("Password não pode ser nulo") {};
        }
		this.password = password;
	}

	public String getPassword(){
		return this.password;
	}

	public void setIdEmployee(int idEmployee){
		this.idEmployee = idEmployee;
	}

	public int getIdEmployee(){
		return this.idEmployee;
	}

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


} // END class user

