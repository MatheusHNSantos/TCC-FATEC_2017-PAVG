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
public class ProductIngredient {
	private int idProductIngredient;
	private int idProduct;
	private int idIngredient;

	public ProductIngredient(int idProductIngredient){
		this.setIdProductIngredient(idProductIngredient);
		this.Load();
	}

    public ProductIngredient() {
    }

    private void Load(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			stmt = con.prepareStatement("SELECT * FROM product_ingredient WHERE id_product_ingredient = ?");
			stmt.setInt(1, this.getIdProductIngredient());
			rs = stmt.executeQuery();
			rs.next();
			this.setIdProduct(rs.getInt("id_product"));
			this.setIdIngredient(rs.getInt("id_ingredient"));
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Leitura!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
	}

	public static void saveListProductIngredient(ArrayList<Ingredient> listIngredients, int idProduct){
		ProductIngredient newProductIngredient = new ProductIngredient();
		ArrayList<ProductIngredient> listProductIngredient = ProductIngredient.ReadAllByProduct(idProduct);
		int idIngredient = 0;
		boolean flag = false;
        for (int i = 0; i < listProductIngredient.size(); i ++){
            for (int j = 0; j <listIngredients.size(); j ++){
                if(listProductIngredient.get(i).getIdIngredient() == listIngredients.get(j).getIdIngredient()){
                    flag = true;
                    break;
                }else{
                    flag = false;
                }
            }
            if(!flag) listProductIngredient.get(i).Delete();
        }

        for (int i = 0; i < listIngredients.size(); i ++){
            for (int j = 0; j <listProductIngredient.size(); j ++){
                if(listIngredients.get(i).getIdIngredient() == listProductIngredient.get(j).getIdIngredient()){
                    flag = true;
                    break;
                }else{
                    flag = false;
                }
            }
            if(!flag){
                newProductIngredient.setIdProduct(idProduct);
                newProductIngredient.setIdIngredient(listIngredients.get(i).getIdIngredient());
                newProductIngredient.Create();
            }

        }



		/*or (Ingredient ingredient: listIngredients){
            idIngredient = ingredient.getIdIngredient();
            for (ProductIngredient productIngredient: listProductIngredient) {
                if(idIngredient == productIngredient.getIdIngredient()){

                }else{
                    productIngredient.Delete();
                }
            }
        }*/

	}

    public static ArrayList<ProductIngredient> ReadAllByProduct(int idProduct){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<ProductIngredient> product_ingredientsList = new ArrayList<>();
        try{
            stmt = con.prepareStatement("SELECT id_product_ingredient FROM product_ingredient where id_product = ?");
            stmt.setInt(1, idProduct);
            rs = stmt.executeQuery();
            while(rs.next()){
                ProductIngredient product_ingredient = new ProductIngredient(rs.getInt("id_product_ingredient"));
                product_ingredientsList.add(product_ingredient);
            }
        } catch (SQLException ex) {
            FxDialogs.showException("Erro de Leitura!","class: ProductIngredient" + " - " + ex.getMessage(),ex);
        }
        finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return product_ingredientsList;
    }

	public static ArrayList<ProductIngredient> ReadAll(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<ProductIngredient> product_ingredientsList = new ArrayList<>();
		try{
			stmt = con.prepareStatement("SELECT id_product_ingredient FROM product_ingredient");
			rs = stmt.executeQuery();
			while(rs.next()){
                ProductIngredient product_ingredient = new ProductIngredient(rs.getInt("id_product_ingredient"));
				product_ingredientsList.add(product_ingredient);
			}
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Leitura!","class: ProductIngredient" + " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return product_ingredientsList;
	}

    public static ArrayList<Ingredient> ReadAllIngredients(int id_product) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Ingredient> ingredientList = new ArrayList<>();

        try {
            stmt = con.prepareStatement("select id_ingredient from product_ingredient where id_product = ?");
            stmt.setInt(1, id_product);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Ingredient ingredient = new Ingredient(rs.getInt("id_ingredient"));
                ingredientList.add(ingredient);
            }

        } catch (SQLException ex) {
            FxDialogs.showException("Erro de Leitura3!","class: ProductIngredient" + " - " + ex.getMessage(),ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return ingredientList;
    }

	public void Save(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		try{
			stmt = con.prepareStatement("UPDATE product_ingredient SET id_product = ?, id_ingredient = ?  WHERE id_product_ingredient= ?");
			stmt.setInt(1, this.getIdProduct());
			stmt.setInt(2, this.getIdIngredient());
			stmt.setInt(3, this.getIdProductIngredient());
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
			stmt = con.prepareStatement("INSERT INTO product_ingredient (id_product,id_ingredient) VALUES (?,?)");
			stmt.setInt(1, this.getIdProduct());
			stmt.setInt(2, this.getIdIngredient());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Gravação! " ,getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con,stmt);
		}
	}

    public static boolean create(int id_product, int id_ingredient) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("insert into product_ingredient (id_product,id_ingredient) values(?,?)");
            stmt.setInt(1, id_product);
            stmt.setInt(2, id_ingredient);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            FxDialogs.showException("Erro de Gravação! " ,"class: ProductIngredient" + " - " + ex.getMessage(),ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);

        }

    }

	public void Delete(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		try{
			stmt = con.prepareStatement("DELETE FROM product_ingredient WHERE id_product_ingredient = ?");
			stmt.setInt(1, this.getIdProductIngredient());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Exclusão!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con,stmt);
		}
	}

    public static boolean delete(int id_product, int id_ingredient) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("delete from product_ingredient where id_product = ? and id_ingredient = ?");
            stmt.setInt(1, id_product);
            stmt.setInt(1, id_ingredient);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            FxDialogs.showException("Erro de Exclusão!","class: ProductIngredient" + " - " + ex.getMessage(),ex);
            return false;
        }
        finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }

	public void setIdProductIngredient(int idProductIngredient){
		this.idProductIngredient = idProductIngredient;
	}

	public int getIdProductIngredient(){
		return this.idProductIngredient;
	}

	public void setIdProduct(int idProduct){
		this.idProduct = idProduct;
	}

	public int getIdProduct(){
		return this.idProduct;
	}

	public void setIdIngredient(int idIngredient){
		this.idIngredient = idIngredient;
	}

	public int getIdIngredient(){
		return this.idIngredient;
	}

} // END class product_ingredient

