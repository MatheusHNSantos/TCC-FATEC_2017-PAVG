package model.entity.address;

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
public class Address {
	private int idAddress;
	private String street;
	private int number;
	private String neighborhood;
	private String cep;

    public static int LAST_ID_INSERT = -1;

	public Address(int idAddress){
		this.setIdAddress(idAddress);
		this.Load();
	}

	public Address(){
	}

	private void Load(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			stmt = con.prepareStatement("SELECT * FROM address WHERE id_address = ?");
			stmt.setInt(1, this.getIdAddress());
			rs = stmt.executeQuery();
			rs.next();
			this.setStreet(rs.getString("street"));
			this.setNumber(rs.getInt("number"));
			this.setNeighborhood(rs.getString("neighborhood"));
			this.setCep(rs.getString("cep"));
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Leitura!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
	}

	public static ArrayList<Address> ReadAll(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Address> addresssList = new ArrayList<>();
		try{
			stmt = con.prepareStatement("SELECT id_address FROM address");
			rs = stmt.executeQuery();
			while(rs.next()){
				Address address = new Address(rs.getInt("id_address"));
				addresssList.add(address);
			}
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Leitura!","class: Address" +  " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return addresssList;
	}

	public void Save(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		try{
			stmt = con.prepareStatement("UPDATE address SET street = ?, number = ?, neighborhood = ?, cep = ?  WHERE id_address= ?");
			stmt.setString(1, this.getStreet());
			stmt.setInt(2, this.getNumber());
			stmt.setString(3, this.getNeighborhood());
			stmt.setString(4, this.getCep());
			stmt.setInt(5, this.getIdAddress());
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
			stmt = con.prepareStatement("INSERT INTO address (street,number,neighborhood,cep) VALUES (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, this.getStreet());
			stmt.setInt(2, this.getNumber());
			stmt.setString(3, this.getNeighborhood());
			stmt.setString(4, this.getCep());
			stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            LAST_ID_INSERT = rs.next() ? rs.getInt(1) : -1;
            this.setIdAddress(LAST_ID_INSERT);
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
			stmt = con.prepareStatement("DELETE FROM address WHERE id_address = ?");
			stmt.setInt(1, this.getIdAddress());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Exclusão!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con,stmt);
		}
	}

	public void setIdAddress(int idAddress){
		this.idAddress = idAddress;
	}

	public int getIdAddress(){
		return this.idAddress;
	}

	public void setStreet(String street){
		this.street = street;
	}

	public String getStreet(){
		return this.street;
	}

	public void setNumber(int number){
		this.number = number;
	}

	public int getNumber(){
		return this.number;
	}

	public void setNeighborhood(String neighborhood){
		this.neighborhood = neighborhood;
	}

	public String getNeighborhood(){
		return this.neighborhood;
	}

	public void setCep(String cep){
		this.cep = cep;
	}

	public String getCep(){
		return this.cep;
	}

} // END class address

