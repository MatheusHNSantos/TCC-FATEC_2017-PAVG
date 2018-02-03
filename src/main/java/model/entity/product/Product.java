package model.entity.product;

import util.connection.ConnectionFactory;
import util.dialogs.FxDialogs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
* 
* @author Matheus Henrique
*/
public class Product {
	private int idProduct;
	private String nameProduct;
	private float finalPriceProduct;
	private float weightProduct;
	private boolean statusProduct;
	private int idProductType;

	private ProductType productType;
	private ArrayList<Ingredient> listIngredients = new ArrayList();

    public static int LAST_ID_INSERT = -1;

	public Product(int idProduct){
		this.setIdProduct(idProduct);
		this.Load();
	}

    public Product() {
    }

    public Product(String nameProduct, float finalPriceProduct){
		this.finalPriceProduct = finalPriceProduct;
		this.nameProduct = nameProduct;
	}

	private void Load(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			stmt = con.prepareStatement("SELECT * FROM product WHERE id_product = ?");
			stmt.setInt(1, this.getIdProduct());
			rs = stmt.executeQuery();
			rs.next();
			this.setNameProduct(rs.getString("name_product"));
			this.setFinalPriceProduct(rs.getFloat("final_price_product"));
			this.setWeightProduct(rs.getFloat("weight_product"));
			this.setStatusProduct(rs.getBoolean("status_product"));
			this.setIdProductType(rs.getInt("id_product_type"));
			this.setListIngredients(ProductIngredient.ReadAllIngredients(this.getIdProduct()));
			this.setProductType(new ProductType(this.getIdProductType()));
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Leitura!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
	}

	public static ArrayList<Product> readByCategory(int category){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Product> productsList = new ArrayList<>();
		try{
			stmt = con.prepareStatement("SELECT * FROM product where id_product_type = ?");
			stmt.setInt(1,category);
			rs = stmt.executeQuery();
			while(rs.next()){
				Product product = new Product(rs.getInt("id_product"));
				product.setListIngredients(ProductIngredient.ReadAllIngredients(product.getIdProduct()));
				product.setProductType(new ProductType(product.getIdProductType()));
				productsList.add(product);
			}
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Leitura!","class: Product" + " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return productsList;
	}

	public static ArrayList<Product> ReadAll(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Product> productsList = new ArrayList<>();
		try{
			stmt = con.prepareStatement("SELECT id_product FROM product");
			rs = stmt.executeQuery();
			while(rs.next()){
				Product product = new Product(rs.getInt("id_product"));
				product.setListIngredients(ProductIngredient.ReadAllIngredients(product.getIdProduct()));
				product.setProductType(new ProductType(product.getIdProductType()));
				productsList.add(product);
			}
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Leitura!","class: Product" + " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return productsList;
	}

	public void Save(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		try{
			stmt = con.prepareStatement("UPDATE product SET name_product = ?, final_price_product = ?, weight_product = ?, status_product = ?, id_product_type = ?  WHERE id_product= ?");
			stmt.setString(1, this.getNameProduct());
			stmt.setFloat(2, this.getFinalPriceProduct());
			stmt.setFloat(3, this.getWeightProduct());
			stmt.setBoolean(4, this.getStatusProduct());
			stmt.setInt(5, this.getIdProductType());
			stmt.setInt(6, this.getIdProduct());
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
			stmt = con.prepareStatement("INSERT INTO product (name_product,final_price_product,weight_product,status_product,id_product_type) VALUES (?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, this.getNameProduct());
			stmt.setFloat(2, this.getFinalPriceProduct());
			stmt.setFloat(3, this.getWeightProduct());
			stmt.setBoolean(4, this.getStatusProduct());
			stmt.setInt(5, this.getIdProductType());
			stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            LAST_ID_INSERT = rs.next() ? rs.getInt(1) : -1;
            this.setIdProduct(LAST_ID_INSERT);
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
			stmt = con.prepareStatement("DELETE FROM product WHERE id_product = ?");
			stmt.setInt(1, this.getIdProduct());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Exclusão!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con,stmt);
		}
	}

	public ArrayList<Ingredient> getListIngredients() {
		return listIngredients;
	}

	public void setListIngredients(ArrayList<Ingredient> listIngredients) {
		this.listIngredients = listIngredients;
	}

	public void setIdProduct(int idProduct){
		this.idProduct = idProduct;
	}

	public int getIdProduct(){
		return this.idProduct;
	}

	public void setNameProduct(String nameProduct){
		this.nameProduct = nameProduct;
	}

	public String getNameProduct(){
		return this.nameProduct;
	}

	public void setFinalPriceProduct(float finalPriceProduct){
		this.finalPriceProduct = finalPriceProduct;
	}

	public float getFinalPriceProduct(){
		return this.finalPriceProduct;
	}

	public void setWeightProduct(float weightProduct){
		this.weightProduct = weightProduct;
	}

	public float getWeightProduct(){
		return this.weightProduct;
	}

	public void setStatusProduct(boolean statusProduct){
		this.statusProduct = statusProduct;
	}

	public boolean getStatusProduct(){
		return this.statusProduct;
	}

	public void setIdProductType(int idProductType){
		this.idProductType = idProductType;
	}

	public int getIdProductType(){
		return this.idProductType;
	}

	public boolean isStatusProduct() {
		return statusProduct;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}


} // END class product

