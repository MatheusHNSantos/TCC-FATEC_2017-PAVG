package model.entity.person;

import model.entity.address.Address;
import model.entity.phone.Phone;
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
public class Person {
	private int idPerson;
	private int idAddress;
	private String namePerson;
	private boolean status = true;

    private Address address;
	private ArrayList<Phone> listPhone = new ArrayList();

	public static int LAST_ID_INSERT = -1;

	public Person(int idPerson){
		this.setIdPerson(idPerson);
		this.Load();
	}

	public Person(){
	}

	private void Load(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			stmt = con.prepareStatement("SELECT * FROM person WHERE id_person = ?");
			stmt.setInt(1, this.getIdPerson());
			rs = stmt.executeQuery();
			rs.next();
			this.setIdAddress(rs.getInt("id_address"));
			this.setNamePerson(rs.getString("name_person"));
            this.setStatus(rs.getBoolean("status"));
			this.setListPhone(Phone.ReadAllPhonePerson(this.idPerson));
            address = new Address(this.idAddress);
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Leitura! - LoadPerson",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
	}

	/*public static ArrayList<Person> ReadAll(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Person> personsList = new ArrayList<>();
		try{
			stmt = con.prepareStatement("SELECT id_person FROM person");
			rs = stmt.executeQuery();
			while(rs.next()){
				Person person = new Person(rs.getInt("id_person"));
				personsList.add(person);
			}
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Leitura!","class: Person" + " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return personsList;
	}*/

	public void Save(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		try{
			stmt = con.prepareStatement("UPDATE person SET id_address = ?, name_person = ?, status = ?  WHERE id_person= ?");
			stmt.setInt(1, this.getIdAddress());
			stmt.setString(2, this.getNamePerson());
            stmt.setBoolean(3, this.getStatus());
			stmt.setInt(4, this.getIdPerson());
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
			stmt = con.prepareStatement("INSERT INTO person (id_address,name_person,status) VALUES (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, this.getIdAddress());
			stmt.setString(2, this.getNamePerson());
            stmt.setBoolean(3, this.getStatus());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			LAST_ID_INSERT = rs.next() ? rs.getInt(1) : -1;
			this.setIdPerson(LAST_ID_INSERT);
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
			stmt = con.prepareStatement("DELETE FROM person WHERE id_person = ?");
			stmt.setInt(1, this.getIdPerson());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Exclusão!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con,stmt);
		}
	}

	public void setIdPerson(int idPerson){
		this.idPerson = idPerson;
	}

	public int getIdPerson(){
		return this.idPerson;
	}

	public void setIdAddress(int idAddress){
		this.idAddress = idAddress;
	}

	public int getIdAddress(){
		return this.idAddress;
	}

	public void setNamePerson(String namePerson){
		this.namePerson = namePerson;
	}

	public String getNamePerson(){
		return this.namePerson;
	}

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public  ArrayList<Phone> getListPhone() {
        return listPhone;
    }

    public void setListPhone(ArrayList<Phone> listPhone) {
        this.listPhone = listPhone;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
} // END class person

