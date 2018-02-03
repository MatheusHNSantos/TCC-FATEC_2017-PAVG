package model.entity.person.supplier;

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
public class Supplier extends Person {
	private String cnpj;

	public Supplier(int idPerson, String cnpj){
		super(idPerson);
		this.setCnpj(cnpj);
		this.Load();
	}

    public Supplier(int idPerson){
        super(idPerson);
        this.LoadByPerson();
    }

	public Supplier (){

    }

	private void Load(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			stmt = con.prepareStatement("SELECT * FROM supplier WHERE cnpj = ?");
			stmt.setString(1, this.getCnpj());
			rs = stmt.executeQuery();
			rs.next();
			this.setIdPerson(rs.getInt("id_person"));
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Leitura! - Load",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
	}

    private void LoadByPerson(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            stmt = con.prepareStatement("SELECT * FROM supplier WHERE id_person = ?");
            stmt.setInt(1, this.getIdPerson());
            rs = stmt.executeQuery();
            rs.next();
            this.setCnpj(rs.getString("cnpj"));
        } catch (SQLException ex) {
            FxDialogs.showException("Erro de Leitura! - LoadByPerson",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
        }
        finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

	public static ArrayList<Supplier> ReadAll(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Supplier> suppliersList = new ArrayList<>();
		try{
			stmt = con.prepareStatement("SELECT id_person, cnpj FROM supplier");
			rs = stmt.executeQuery();
			while(rs.next()){
				Supplier supplier = new Supplier(rs.getInt("id_person"),rs.getString("cnpj"));
				suppliersList.add(supplier);
			}
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Leitura! - ReadAll","class: Supplier"+ " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return suppliersList;
	}

	public void Save(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		try{
			super.Save();
            stmt = con.prepareStatement("UPDATE supplier SET cnpj = ? WHERE id_person= ?");
            stmt.setString(1, this.getCnpj());
            stmt.setInt(2, this.getIdPerson());
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
			stmt = con.prepareStatement("INSERT INTO supplier (id_person,cnpj) VALUES (?,?)");
			stmt.setInt(1, this.getIdPerson());
            stmt.setString(2, this.getCnpj());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Gravação! " ,getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con,stmt);
		}
	}

	public void Delete(){
		super.Delete();
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		try{
			stmt = con.prepareStatement("DELETE FROM supplier WHERE cnpj = ?");
			stmt.setString(1, this.getCnpj());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Exclusão!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con,stmt);
		}
	}

	public void setCnpj(String cnpj){
		this.cnpj = cnpj;
	}

	public String getCnpj(){
		return this.cnpj;
	}

} // END class supplier

