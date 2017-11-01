/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity.product;

import model.entity.product.Ingredient;
import model.entity.product.Product;
import model.entity.product.ProductIngredient;
import model.entity.product.ProductType;
import util.connection.ConnectionFactory;

import javax.swing.*;
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
            stmt = con.prepareStatement("insert into product_ingredient values(id_product,id_ingredient) values(?,?)");
            stmt.setInt(1, productIngredient.getIdProduct());
            stmt.setInt(2, productIngredient.getIdIngredient());

            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar: " + ex.getMessage());
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
            JOptionPane.showMessageDialog(null, "Falha ao consultar: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return productIngredientList;
    }

    public List<Ingredient> readAll(Product product) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Ingredient> IngredientList = new ArrayList<>();

        try {
            stmt = con.prepareStatement("select * from ingredient I, product_ingredient PI " +
                                           "where I.id_ingredient = PI.id_ingredient and PI.id_product = ?");
            stmt.setInt(1, product.getId());
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
            JOptionPane.showMessageDialog(null, "Falha ao consultar: " + ex.getMessage());
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
            JOptionPane.showMessageDialog(null, "Erro ao Excluir: " + ex.getMessage());
            return false;
        }
        finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }
}
