package model.entity.sale;

import model.entity.product.Product;
import java.util.ArrayList;
import java.util.List;
import util.connection.ConnectionFactory;
import util.dialogs.FxDialogs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
* 
* @author Matheus Henrique
*/
public class Sale {
	private int idSale;
	private int idUser;
	private int idCostumer;
	private String saleTime;
	private String saleDate;
	private int saleTimeEstimate;
	private float saleTotal;
    private List<Product> productsList = new ArrayList();

	public Sale(int idSale){
		this.setIdSale(idSale);
		this.Load();
	}

	public Sale(int idSale, int idCostumer, float saleTotal){
		this.idSale = idSale;
		this.idCostumer = idCostumer;
		this.saleTotal = saleTotal;
	}

	private void Load(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			stmt = con.prepareStatement("SELECT * FROM sale WHERE id_sale = ?");
			stmt.setInt(1, this.getIdSale());
			rs = stmt.executeQuery();
			rs.next();
			this.setIdUser(rs.getInt("id_user"));
			this.setIdCostumer(rs.getInt("id_costumer"));
			this.setSaleTime(rs.getString("sale_time"));
			this.setSaleDate(rs.getString("sale_date"));
			this.setSaleTimeEstimate(rs.getInt("sale_time_estimate"));
			this.setSaleTotal(rs.getFloat("sale_total"));
			//this.setProductsList(ItemsSale.readAllProduct(this.getIdSale()));
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Leitura!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
	}

	public static ArrayList<Sale> ReadAll(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Sale> salesList = new ArrayList<>();
		try{
			stmt = con.prepareStatement("SELECT id_sale FROM sale");
			rs = stmt.executeQuery();
			while(rs.next()){
				Sale sale = new Sale(rs.getInt("id_sale"));
				//sale.setProductsList(ItemsSale.readAllProduct(sale.getIdSale()));
				salesList.add(sale);
			}
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Leitura!","class: Sale" + " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return salesList;
	}

	public void Save(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		try{
			stmt = con.prepareStatement("UPDATE sale SET id_user = ?, id_costumer = ?, sale_time = ?, sale_date = ?, sale_time_estimate = ?, sale_total = ?,  WHERE id_sale= ?");
			stmt.setInt(1, this.getIdUser());
			stmt.setInt(2, this.getIdCostumer());
			stmt.setString(3, this.getSaleTime());
			stmt.setString(4, this.getSaleDate());
			stmt.setInt(5, this.getSaleTimeEstimate());
			stmt.setFloat(6, this.getSaleTotal());
			stmt.setInt(7, this.getIdSale());
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
			stmt = con.prepareStatement("INSERT INTO sale (id_user,id_costumer,sale_time,sale_date,sale_time_estimate,sale_total) VALUES (?,?,?,?,?,?)");
			stmt.setInt(1, this.getIdUser());
			stmt.setInt(2, this.getIdCostumer());
			stmt.setString(3, this.getSaleTime());
			stmt.setString(4, this.getSaleDate());
			stmt.setInt(5, this.getSaleTimeEstimate());
			stmt.setFloat(6, this.getSaleTotal());
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
			stmt = con.prepareStatement("DELETE FROM sale WHERE id_sale = ?");
			stmt.setInt(1, this.getIdSale());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Exclusão!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con,stmt);
		}
	}

    public List<Product> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Product> productsList) {
        this.productsList = productsList;
    }

	public void setIdSale(int idSale){
		this.idSale = idSale;
	}

	public int getIdSale(){
		return this.idSale;
	}

	public void setIdUser(int idUser){
		this.idUser = idUser;
	}

	public int getIdUser(){
		return this.idUser;
	}

	public void setIdCostumer(int idCostumer){
		this.idCostumer = idCostumer;
	}

	public int getIdCostumer(){
		return this.idCostumer;
	}

	public void setSaleTime(String saleTime){
		this.saleTime = saleTime;
	}

	public String getSaleTime(){
		return this.saleTime;
	}

	public void setSaleDate(String saleDate){
		this.saleDate = saleDate;
	}

	public String getSaleDate(){
		return this.saleDate;
	}

	public void setSaleTimeEstimate(int saleTimeEstimate){
		this.saleTimeEstimate = saleTimeEstimate;
	}

	public int getSaleTimeEstimate(){
		return this.saleTimeEstimate;
	}

	public void setSaleTotal(float saleTotal){
		this.saleTotal = saleTotal;
	}

	public float getSaleTotal(){
		return this.saleTotal;
	}

} // END class sale

