/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity.product;

import model.entity.product.Product;
import util.connection.ConnectionFactory;
import util.dialogs.FxDialogs;

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
public class ProductDAO {

    public boolean create(Product product) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;


        try {
            stmt = con.prepareStatement("insert into product (name_product,final_price_product,weight_product,status_product,id_product_type) values(?,?,?,?,?)");
            stmt.setString(1, product.getName());
            stmt.setFloat(2, product.getFinalPrice());
            stmt.setFloat(3, product.getWeight());
            stmt.setBoolean(4, product.getStatus());
            stmt.setInt(5, product.getIdProductType());


            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            FxDialogs.showException("Erro de Gravação! " ,getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);

        }

    }

    public Product read(Product product) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;


        try {
            stmt = con.prepareStatement("select * from product where id_ingredient = ? ");
            stmt.setInt(1, product.getId());
            rs = stmt.executeQuery();
            rs.next();
            product.setId(rs.getInt("id_product"));
            product.setName(rs.getString("name_product"));
            product.setFinalPrice(rs.getFloat("final_price_product"));
            product.setWeight(rs.getFloat("weight_product"));
            product.setStatus(rs.getBoolean("status_product"));
            product.setIdProductType(rs.getInt("id_product_type"));
            ProductIngredientDAO ingredients = new ProductIngredientDAO();
            product.setListIngredients(ingredients.readAllIngredients(product.getId()));




        } catch (SQLException ex) {
            FxDialogs.showException("Erro de Leitura!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return product;
    }


    public List<Product> readAll() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Product> products = new ArrayList<>();

        try {
            stmt = con.prepareStatement("select * from product");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id_product"));
                product.setName(rs.getString("name_product"));
                product.setFinalPrice(rs.getFloat("final_price_product"));
                product.setWeight(rs.getFloat("weight_product"));
                product.setStatus(rs.getBoolean("status_product"));
                product.setIdProductType(rs.getInt("id_product_type"));
                ProductIngredientDAO ingredients = new ProductIngredientDAO();
                product.setListIngredients(ingredients.readAllIngredients(product.getId()));
                products.add(product);


            }

        } catch (SQLException ex) {
            FxDialogs.showException("Erro de Leitura!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return products;
    }

    public boolean update(Product product) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("update product set name_product = ?, final_price_product = ?, weight_product = ?, status_product = ?, id_product_type = ?  where id_product = ?");
            stmt.setString(1, product.getName());
            stmt.setFloat(2, product.getFinalPrice());
            stmt.setFloat(3, product.getWeight());
            stmt.setBoolean(4, product.getStatus());
            stmt.setInt(5, product.getIdProductType());
            stmt.setInt(6, product.getId());
            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            FxDialogs.showException("Erro de Atualização!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
            return false;
        }
        finally{
            ConnectionFactory.closeConnection(con,stmt);
        }

    }

    public boolean delete(Product product) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("delete from product where id_product = ?");
            stmt.setInt(1, product.getId());

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
