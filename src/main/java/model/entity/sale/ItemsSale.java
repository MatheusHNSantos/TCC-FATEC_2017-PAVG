package model.entity.sale;

import model.entity.product.Product;
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
public class ItemsSale {
	private int idItemsSale;
	private int idSale;
	private int idProduct;

	public ItemsSale(int idItemsSale){
		this.setIdItemsSale(idItemsSale);
		this.Load();
	}

    public ItemsSale(int idItemsSale, int idSale, int idProduct){
        this.idItemsSale =idItemsSale;
        this.idSale = idSale;
        this.idProduct = idProduct;
    }

	private void Load(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			stmt = con.prepareStatement("SELECT * FROM items_sale WHERE id_items_sale = ?");
			stmt.setInt(1, this.getIdItemsSale());
			rs = stmt.executeQuery();
			rs.next();
			this.setIdSale(rs.getInt("id_sale"));
			this.setIdProduct(rs.getInt("id_product"));
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Leitura!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
	}

	public static ArrayList<ItemsSale> ReadAll(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<ItemsSale> items_salesList = new ArrayList<>();
		try{
			stmt = con.prepareStatement("SELECT id_items_sale FROM items_sale");
			rs = stmt.executeQuery();
			while(rs.next()){
				ItemsSale items_sale = new ItemsSale(rs.getInt("id_items_sale"));
				items_salesList.add(items_sale);
			}
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Leitura!","class: ItemsSale" + " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return items_salesList;
	}

	public static ArrayList<Product> readAllProduct(int idSale) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		ArrayList<Product> productList = new ArrayList<>();

		try {
			stmt = con.prepareStatement("select id_product from items_sale" +
					"where id_sale = ?");
			stmt.setInt(1, idSale);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Product product = new Product(rs.getInt("id_product"));
                productList.add(product);
			}

		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Leitura2!","class: ProductIngredient" + " - " + ex.getMessage(),ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}

		return productList;
	}

	public void Save(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		try{
			stmt = con.prepareStatement("UPDATE items_sale SET id_sale = ?, id_product = ?,  WHERE id_items_sale= ?");
			stmt.setInt(1, this.getIdSale());
			stmt.setInt(2, this.getIdProduct());
			stmt.setInt(3, this.getIdItemsSale());
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
			stmt = con.prepareStatement("INSERT INTO items_sale (id_sale,id_product) VALUES (?,?)");
			stmt.setInt(1, this.getIdSale());
			stmt.setInt(2, this.getIdProduct());
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
			stmt = con.prepareStatement("DELETE FROM items_sale WHERE id_items_sale = ?");
			stmt.setInt(1, this.getIdItemsSale());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Exclusão!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con,stmt);
		}
	}

	public void setIdItemsSale(int idItemsSale){
		this.idItemsSale = idItemsSale;
	}

	public int getIdItemsSale(){
		return this.idItemsSale;
	}

	public void setIdSale(int idSale){
		this.idSale = idSale;
	}

	public int getIdSale(){
		return this.idSale;
	}

	public void setIdProduct(int idProduct){
		this.idProduct = idProduct;
	}

	public int getIdProduct(){
		return this.idProduct;
	}

} // END class items_sale

