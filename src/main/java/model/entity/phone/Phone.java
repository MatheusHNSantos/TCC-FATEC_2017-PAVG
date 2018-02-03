package model.entity.phone;

import model.entity.address.Address;
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
public class Phone {
	private int idPhone;
	private String phone;
	private int idPerson;

	public Phone(int idPhone){
		this.setIdPhone(idPhone);
		this.Load();
	}

	public Phone(){

	}

    public static Person searchByTelephone(String telephone){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Person person = new Person();
        try{
            stmt = con.prepareStatement("select PE.name_person, PH.phone, A.id_address from person PE, phone PH, address A where A.id_address = PE.id_address and PE.id_person = PH.id_person and PH.phone = ?");
            stmt.setString(1, telephone);
            rs = stmt.executeQuery();
            rs.next();
            person.setNamePerson(rs.getString("name_person"));
            person.setAddress(new Address(rs.getInt("id_address")));
        } catch (SQLException ex) {
            FxDialogs.showException("Erro de Leitura!","class: Costumer" + " - " + ex.getMessage(),ex);
        }
        finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        //KEEP CALM
        return person;
    }

	private void Load(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			stmt = con.prepareStatement("SELECT * FROM phone WHERE id_phone = ?");
			stmt.setInt(1, this.getIdPhone());
			rs = stmt.executeQuery();
			rs.next();
			this.setPhone(rs.getString("phone"));
			this.setIdPerson(rs.getInt("id_person"));
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Leitura!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
	}

	public static ArrayList<Phone> ReadAll(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Phone> phonesList = new ArrayList<>();
		try{
			stmt = con.prepareStatement("SELECT id_phone FROM phone");
			rs = stmt.executeQuery();
			while(rs.next()){
				Phone phone = new Phone(rs.getInt("id_phone"));
				phonesList.add(phone);
			}
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Leitura!","Class: Phone" + " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return phonesList;
	}

	public static ArrayList<Phone> ReadAllPhonePerson(int idPerson){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Phone> phonesList = new ArrayList<>();
		try{
			stmt = con.prepareStatement("SELECT id_phone FROM phone where id_person = " + idPerson);
			rs = stmt.executeQuery();
			while(rs.next()){
				Phone phone = new Phone(rs.getInt("id_phone"));
				phonesList.add(phone);
			}
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Leitura!","Class: Phone" + " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return phonesList;
	}

	public void Save(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		try{
			stmt = con.prepareStatement("UPDATE phone SET phone = ?, id_person = ?  WHERE id_phone= ?");
			stmt.setString(1, this.getPhone());
			stmt.setInt(2, this.getIdPerson());
			stmt.setInt(3, this.getIdPhone());
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
			stmt = con.prepareStatement("INSERT INTO phone (phone,id_person) VALUES (?,?)");
			stmt.setString(1, this.getPhone());
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
			stmt = con.prepareStatement("DELETE FROM phone WHERE id_phone = ?");
			stmt.setInt(1, this.getIdPhone());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Exclusão!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con,stmt);
		}
	}

	public void setIdPhone(int idPhone){
		this.idPhone = idPhone;
	}

	public int getIdPhone(){
		return this.idPhone;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return this.phone;
	}

	public void setIdPerson(int idPerson){
		this.idPerson = idPerson;
	}

	public int getIdPerson(){
		return this.idPerson;
	}

} // END class phone

