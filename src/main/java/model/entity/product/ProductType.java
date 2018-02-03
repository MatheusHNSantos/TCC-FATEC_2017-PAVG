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
public class ProductType {
	private int idProductType;
	private String nameProductType;

	public ProductType(int idProductType){
		this.setIdProductType(idProductType);
		this.Load();
	}

	public ProductType(String nameProductType) {
		this.nameProductType = nameProductType;
		this.LoadByName();
	}

	public ProductType() {
	}

	private void Load(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			stmt = con.prepareStatement("SELECT * FROM product_type WHERE id_product_type = ?");
			stmt.setInt(1, this.getIdProductType());
			rs = stmt.executeQuery();
			rs.next();
			this.setNameProductType(rs.getString("name_product_type"));
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Leitura!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
	}

	private void LoadByName(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			stmt = con.prepareStatement("SELECT id_product_type FROM product_type WHERE name_product_type = ?");
			stmt.setString(1, this.getNameProductType());
			rs = stmt.executeQuery();
			rs.next();
			this.setIdProductType(rs.getInt("id_product_type"));
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Leitura!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
	}

	public static ArrayList<ProductType> ReadAll(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<ProductType> product_typesList = new ArrayList<>();
		try{
			stmt = con.prepareStatement("SELECT id_product_type FROM product_type");
			rs = stmt.executeQuery();
			while(rs.next()){
				int aux = rs.getInt("id_product_type");
				ProductType product_type = new ProductType(aux);
				product_typesList.add(product_type);
			}
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Leitura!","class: ProductType" + " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return product_typesList;
	}

    public static boolean isUsing(int idProductType){
	    if(idProductType == 0) return true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Integer> listId = new ArrayList<>();
        Boolean isUsing = false;
        try{
            stmt = con.prepareStatement("SELECT id_product FROM product where id_product_type = ?");
            stmt.setInt(1, idProductType);
            rs = stmt.executeQuery();
            while(rs.next()){
                listId.add(rs.getInt("id_product"));
            }
            if(listId.size() > 0) isUsing = true;

        } catch (SQLException ex) {
            FxDialogs.showException("Erro de Leitura!","class: ProductType" + " - " + ex.getMessage(),ex);
        }
        finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return isUsing;
    }

	public void Save(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		try{
			stmt = con.prepareStatement("UPDATE product_type SET name_product_type = ?  WHERE id_product_type= ?");
			stmt.setString(1, this.getNameProductType());
			stmt.setInt(2, this.getIdProductType());
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
			stmt = con.prepareStatement("INSERT INTO product_type (name_product_type) VALUES (?)");
			stmt.setString(1, this.getNameProductType());
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
			stmt = con.prepareStatement("DELETE FROM product_type WHERE id_product_type = ?");
			stmt.setInt(1, this.getIdProductType());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Exclusão!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con,stmt);
		}
	}

	public void setIdProductType(int idProductType){
		this.idProductType = idProductType;
	}

	public int getIdProductType(){
		return this.idProductType;
	}

	public void setNameProductType(String nameProductType){
		this.nameProductType = nameProductType;
	}

	public String getNameProductType(){
		return this.nameProductType;
	}

} // END class product_type

