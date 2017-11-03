/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity.sale;

import model.entity.product.Product;
import model.entity.sale.ItemsSale;
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
public class ItemsSaleDAO {
    public boolean create(List<ItemsSale> itemsSaleList) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;


        try {


            for (ItemsSale itemSale: itemsSaleList) {
                stmt = con.prepareStatement("insert into items_sale (id_sale,id_product) values(?,?)");
                stmt.setInt(1, itemSale.getIdSale());
                stmt.setInt(2, itemSale.getIdProduct());
                stmt.executeUpdate();
            }



            return true;
        } catch (SQLException ex) {
            FxDialogs.showException("Erro de Gravação! " ,getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);

        }

    }

    public boolean create(int id_sale, List<Product> products) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;


        try {


            for (Product product: products) {
                stmt = con.prepareStatement("insert into items_sale (id_sale,id_product) values(?,?)");
                stmt.setInt(1, id_sale);
                stmt.setInt(2, product.getId());
                stmt.executeUpdate();
            }



            return true;
        } catch (SQLException ex) {
            FxDialogs.showException("Erro de Gravação! " ,getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);

        }

    }

    public List<ItemsSale> readAll() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<ItemsSale> itemsSaleList = new ArrayList<>();

        try {
            stmt = con.prepareStatement("select * from product_type");
            rs = stmt.executeQuery();

            while (rs.next()) {
                ItemsSale itemSale = new ItemsSale();
                itemSale.setIdItemsSale(rs.getInt("id_items_sale"));
                itemSale.setIdSale(rs.getInt("id_sale"));
                itemSale.setIdProduct(rs.getInt("id_product"));
                itemsSaleList.add(itemSale);
           }

        } catch (SQLException ex) {
            FxDialogs.showException("Erro de Leitura!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return itemsSaleList;
    }

    public List<Product> readAll(int id_sale) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Product> productsList = new ArrayList<>();

        try {
            stmt = con.prepareStatement("select * from product P, items_sale IS " +
                    "where P.id_product = IS.id_product and IS.id_sale = ?");
            stmt.setInt(1, id_sale);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id_product"));
                product.setName(rs.getString("name_product"));
                product.setFinalPrice(rs.getFloat("final_price_product"));
                product.setWeight(rs.getFloat("weight_product"));
                product.setStatus(rs.getBoolean("status_product"));
                product.setIdProductType(rs.getInt("id_product_type"));
                productsList.add(product);
            }

        } catch (SQLException ex) {
            FxDialogs.showException("Erro de Leitura!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return productsList;
    }
}
