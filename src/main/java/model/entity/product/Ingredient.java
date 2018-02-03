package model.entity.product;

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
public class Ingredient {
	private int idIngredient;
	private String nameIngredient;
	private boolean statusIngredient;
	private float price;

	public Ingredient(int idIngredient){
		this.setIdIngredient(idIngredient);
		this.Load();
	}

    public Ingredient() {
    }

    public Ingredient(String nameIngredient) {
		this.nameIngredient = nameIngredient;
	}

	private void Load(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			stmt = con.prepareStatement("SELECT * FROM ingredient WHERE id_ingredient = ?");
			stmt.setInt(1, this.getIdIngredient());
			rs = stmt.executeQuery();
			rs.next();
			this.setNameIngredient(rs.getString("name_ingredient"));
			this.setStatusIngredient(rs.getBoolean("status_ingredient"));
			this.setPrice(rs.getFloat("price"));
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Leitura!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
	}

	public static ArrayList<Ingredient> ReadAll(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Ingredient> ingredientsList = new ArrayList<>();
		try{
			stmt = con.prepareStatement("SELECT id_ingredient FROM ingredient");
			rs = stmt.executeQuery();
			while(rs.next()){
				Ingredient ingredient = new Ingredient(rs.getInt("id_ingredient"));
				ingredientsList.add(ingredient);
			}
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Leitura!","class: Ingredient"+ " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return ingredientsList;
	}

	public void Save(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		try{
			stmt = con.prepareStatement("UPDATE ingredient SET name_ingredient = ?, status_ingredient = ?, price = ?  WHERE id_ingredient= ?");
			stmt.setString(1, this.getNameIngredient());
			stmt.setBoolean(2, this.getStatusIngredient());
			stmt.setFloat(3, this.getPrice());
			stmt.setInt(4, this.getIdIngredient());
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
			stmt = con.prepareStatement("INSERT INTO ingredient (name_ingredient,status_ingredient,price) VALUES (?,?,?)");
			stmt.setString(1, this.getNameIngredient());
			stmt.setBoolean(2, this.getStatusIngredient());
			stmt.setFloat(3, this.getPrice());
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
			stmt = con.prepareStatement("DELETE FROM ingredient WHERE id_ingredient = ?");
			stmt.setInt(1, this.getIdIngredient());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Exclusão!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con,stmt);
		}
	}

	public void setIdIngredient(int idIngredient){
		this.idIngredient = idIngredient;
	}

	public int getIdIngredient(){
		return this.idIngredient;
	}

	public void setNameIngredient(String nameIngredient){
		this.nameIngredient = nameIngredient;
	}

	public String getNameIngredient(){
		return this.nameIngredient;
	}

	public void setStatusIngredient(boolean statusIngredient){
		this.statusIngredient = statusIngredient;
	}

	public boolean getStatusIngredient(){
		return this.statusIngredient;
	}

	public void setPrice(float price){
		this.price = price;
	}

	public float getPrice(){
		return this.price;
	}

} // END class ingredient

