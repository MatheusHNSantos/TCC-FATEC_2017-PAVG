/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity.product;

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
public class ProductTypeDAO {

    public boolean create(ProductType productType) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;


        try {
            stmt = con.prepareStatement("insert into product_type values(name_product_type) values(?)");
            stmt.setString(1, productType.getName());

            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar: " + ex.getMessage());
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);

        }

    }

    public ProductType read(ProductType productType) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;


        try {
            stmt = con.prepareStatement("select * from product_type where id_product_type = ? ");
            stmt.setInt(1, productType.getId());
            rs = stmt.executeQuery();

            productType.setId(rs.getInt("id_product_type"));
            productType.setName(rs.getString("name_product_type"));



        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao consultar: " + ex.getMessage());
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return productType;
    }


    public List<ProductType> readAll() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<ProductType> productTypeList = new ArrayList<>();

        try {
            stmt = con.prepareStatement("select * from product_type");
            rs = stmt.executeQuery();

            while (rs.next()) {
                ProductType product_type = new ProductType();
                product_type.setId(rs.getInt("id_product_type"));
                product_type.setName(rs.getString("name_product_type"));
                productTypeList.add(product_type);


            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao consultar: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return productTypeList;
    }


    public boolean delete(ProductType productType) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("delete from product_type where id_product_type = ?");
            stmt.setInt(1, productType.getId());

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
