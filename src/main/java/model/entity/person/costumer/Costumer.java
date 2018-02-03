package model.entity.person.costumer;

import model.entity.person.Person;
import util.connection.ConnectionFactory;
import util.dialogs.FxDialogs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
* 
* @author Matheus Henrique
*/
public class Costumer extends Person {
	private String rg;
	private String cpf;

	public Costumer(int idPerson, String cpf){
        super(idPerson);
		this.setCpf(cpf);
		this.LoadDefault();
	}

	public Costumer(int idPerson){
        super(idPerson);
        this.Load();
	}

	public Costumer(){

    }

	private void Load(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			stmt = con.prepareStatement("SELECT * FROM costumer WHERE id_person = ?");
			stmt.setInt(1, this.getIdPerson());
			rs = stmt.executeQuery();
			rs.next();
			this.setRg(rs.getString("rg"));
			this.setCpf(rs.getString("cpf"));
        } catch (SQLException ex) {
			FxDialogs.showException("Erro de Leitura! - Load",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
	}

    private void LoadDefault(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            stmt = con.prepareStatement("SELECT * FROM costumer WHERE cpf = ?");
            stmt.setString(1, this.getCpf());
            rs = stmt.executeQuery();
            rs.next();
            this.setRg(rs.getString("rg"));
            this.setIdPerson(rs.getInt("id_person"));
        } catch (SQLException ex) {
            FxDialogs.showException("Erro de Leitura! - LoadDefault",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
        }
        finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public static ArrayList<Costumer> ReadAll(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Costumer> costumersList = new ArrayList<>();
        try{
            stmt = con.prepareStatement("SELECT id_person FROM costumer");
            rs = stmt.executeQuery();
            while(rs.next()){
                Costumer costumer = new Costumer(rs.getInt("id_person"));
                costumersList.add(costumer);
            }
        } catch (SQLException ex) {
            FxDialogs.showException("Erro de Leitura! - ReadAll","class: Costumer" + " - " + ex.getMessage(),ex);
        }
        finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return costumersList;
    }

	public static ArrayList<Costumer> ReadAllDefault(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Costumer> costumersList = new ArrayList<>();
		try{
			stmt = con.prepareStatement("SELECT id_person, cpf FROM costumer");
			rs = stmt.executeQuery();
			while(rs.next()){
				Costumer costumer = new Costumer(rs.getInt("id_person"), rs.getString("cpf"));
				costumersList.add(costumer);
			}
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Leitura! ReadAll","class: Costumer" + " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return costumersList;
	}

	public void Save(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		try{
            super.Save();
			stmt = con.prepareStatement("UPDATE costumer SET rg = ?, cpf = ?  WHERE id_person= ?");
			stmt.setString(1, this.getRg());
			stmt.setString(2, this.getCpf());
			stmt.setInt(3, this.getIdPerson());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Atualização!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con,stmt);
		}
	}

    public void SaveOlder(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try{
            super.Save();
            stmt = con.prepareStatement("UPDATE costumer SET rg = ?, id_person = ?  WHERE cpf= ?");
            stmt.setString(1, this.getRg());
            stmt.setInt(2, this.getIdPerson());
            stmt.setString(3, this.getCpf());
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
		    super.Create();
			stmt = con.prepareStatement("INSERT INTO costumer (rg,id_person) VALUES (?,?)");
			stmt.setString(1, this.getRg());
			stmt.setInt(2, this.getIdPerson());
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
		    super.Delete();
			stmt = con.prepareStatement("DELETE FROM costumer WHERE cpf = ?");
			stmt.setString(1, this.getCpf());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Exclusão!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con,stmt);
		}
	}

	public void setRg(String rg){
		this.rg = rg;
	}

	public String getRg(){
		return this.rg;
	}

	public void setCpf(String cpf){
		this.cpf = cpf;
	}

	public String getCpf(){
		return this.cpf;
	}

	public boolean validateCpf() {
		return false;
	}

	public boolean validateRg() {
		return false;
	}

} // END class costumer

