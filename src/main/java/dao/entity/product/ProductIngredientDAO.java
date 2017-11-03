/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity.product;

import model.entity.product.Ingredient;
import model.entity.product.ProductIngredient;
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
public class ProductIngredientDAO {
    public boolean create(ProductIngredient productIngredient) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;


        try {
            stmt = con.prepareStatement("insert into product_ingredient (id_product,id_ingredient) values(?,?)");
            stmt.setInt(1, productIngredient.getIdProduct());
            stmt.setInt(2, productIngredient.getIdIngredient());

            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            FxDialogs.showException("Erro de Gravação! " ,getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);

        }

    }

    public boolean create(int id_product, int id_ingredient) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;


        try {
            stmt = con.prepareStatement("insert into product_ingredient (id_product,id_ingredient) values(?,?)");
            stmt.setInt(1, id_product);
            stmt.setInt(2, id_ingredient);

            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            FxDialogs.showException("Erro de Gravação! " ,getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);

        }

    }

    public List<ProductIngredient> readAll() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<ProductIngredient> productIngredientList = new ArrayList<>();

        try {
            stmt = con.prepareStatement("select * from product_ingredient");
            rs = stmt.executeQuery();

            while (rs.next()) {
                ProductIngredient productIngredient = new ProductIngredient();
                productIngredient.setId(rs.getInt("id_product_ingredient"));
                productIngredient.setIdProduct(rs.getInt("id_product"));
                productIngredient.setIdIngredient(rs.getInt("id_ingredient"));
                productIngredientList.add(productIngredient);


            }

        } catch (SQLException ex) {
            FxDialogs.showException("Erro de Leitura!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return productIngredientList;
    }

    public List<Ingredient> readAllIngredients(int id_product) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Ingredient> IngredientList = new ArrayList<>();

        try {
            stmt = con.prepareStatement("select * from ingredient I, product_ingredient PI " +
                                           "where I.id_ingredient = PI.id_ingredient and PI.id_product = ?");
            stmt.setInt(1, id_product);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Ingredient ingredient = new Ingredient();
                ingredient.setId(rs.getInt("id_ingredient"));
                ingredient.setName(rs.getString("name_ingredient"));
                ingredient.setStatus(rs.getBoolean("status_ingredient"));
                ingredient.setPrice(rs.getFloat("price"));
                IngredientList.add(ingredient);


            }

        } catch (SQLException ex) {
            FxDialogs.showException("Erro de Leitura!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return IngredientList;
    }

    public boolean delete(ProductIngredient productIngredient) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("delete from product_ingredient where id_product_ingredient = ?");
            stmt.setInt(1, productIngredient.getId());

            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            FxDialogs.showException("Erro de Exclusão!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
            return false;
        }
        finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }

    public boolean delete(int id_product, int id_ingredient) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("delete from product_ingredient where id_product = ? and id_ingredient = ?");
            stmt.setInt(1, id_product);
            stmt.setInt(1, id_ingredient);
            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            FxDialogs.showException("Erro de Exclusão!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
            return false;
        }
        finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }
}
