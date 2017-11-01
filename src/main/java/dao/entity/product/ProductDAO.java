/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity.product;

import model.entity.product.Ingredient;
import model.entity.product.Product;
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
public class ProductDAO {

    public boolean create(Product product) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;


        try {
            stmt = con.prepareStatement("insert into product values(name_product,final_price_product,weight_product,status_product,id_product_type) values(?,?,?,?,?)");
            stmt.setString(1, product.getName());
            stmt.setFloat(2, product.getFinalPrice());
            stmt.setFloat(3, product.getWeight());
            stmt.setBoolean(4, product.getStatus());
            stmt.setInt(5, product.getIdProductType());


            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar: " + ex.getMessage());
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
            stmt = con.prepareStatement("select * from ingredient where id_ingredient = ? ");
            stmt.setInt(1, product.getId());
            rs = stmt.executeQuery();

            product.setId(rs.getInt("id_product"));
            product.setName(rs.getString("name_product"));
            product.setFinalPrice(rs.getFloat("final_price_product"));
            product.setWeight(rs.getFloat("weight_product"));
            product.setStatus(rs.getBoolean("status_product"));
            product.setIdProductType(rs.getInt("id_product_type"));




        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao consultar: " + ex.getMessage());
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
            stmt = con.prepareStatement("select * from ingredient");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id_product"));
                product.setName(rs.getString("name_product"));
                product.setFinalPrice(rs.getFloat("final_price_product"));
                product.setWeight(rs.getFloat("weight_product"));
                product.setStatus(rs.getBoolean("status_product"));
                product.setIdProductType(rs.getInt("id_product_type"));
                products.add(product);


            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao consultar: " + ex.getMessage());
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
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar: " + ex.getMessage());
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
            JOptionPane.showMessageDialog(null, "Erro ao Excluir: " + ex.getMessage());
            return false;
        }
        finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }
    
}
