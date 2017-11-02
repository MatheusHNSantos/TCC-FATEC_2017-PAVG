/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity.sale;

import model.entity.sale.ItemsSale;
import model.entity.sale.Sale;
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
public class SaleDAO {
    public boolean  create(Sale sale) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;


        try {
            stmt = con.prepareStatement("insert into sale (id_user,id_costumer,sale_time,,sale_date,sale_time_estimate,sale_total) values(?,?,?,?,?,?)");
            stmt.setInt(1, sale.getIdUser());
            stmt.setInt(2, sale.getIdCostumer());
            stmt.setTime(3, sale.getSaleTime());
            stmt.setDate(4, sale.getSaleDate());
            stmt.setTime(5, sale.getSaleTimeEstimate());
            stmt.setFloat(6, sale.getSaleTotal());
            stmt.executeUpdate();
            ItemsSaleDAO itemsSale = new ItemsSaleDAO();
            itemsSale.create(sale.getIdSale(),sale.getItemsSaleList());

            return true;
        } catch (SQLException ex) {
            FxDialogs.showException("Erro de Gravação! " ,getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);

        }

    }

    public Sale read(Sale sale){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("select * from sale where id_sale = ?");
            stmt.setInt(1, sale.getIdSale());
            rs = stmt.executeQuery();
            rs.next();
            sale.setIdSale(rs.getInt("id_sale"));
            sale.setIdUser(rs.getInt("id_user"));
            sale.setIdCostumer(rs.getInt("id_costumer"));
            sale.setSaleTime(rs.getTime("sale_time"));
            sale.setSaleDate(rs.getDate("sale_date"));
            sale.setSaleTimeEstimate(rs.getTime("sale_time_estimate"));
            sale.setSaleTotal(rs.getInt("sale_total"));
            ItemsSaleDAO items = new ItemsSaleDAO();
            sale.setItemsSaleList(items.readAll(sale.getIdSale()));



        } catch (SQLException ex) {
            FxDialogs.showException("Erro de Leitura!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
        }finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return sale;
    }

    public List<Sale> readAll() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Sale> salesList = new ArrayList<>();

        try {
            stmt = con.prepareStatement("select * from sale");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Sale sale = new Sale();
                sale.setIdSale(rs.getInt("id_sale"));
                sale.setIdUser(rs.getInt("id_user"));
                sale.setIdCostumer(rs.getInt("id_costumer"));
                sale.setSaleTime(rs.getTime("sale_time"));
                sale.setSaleDate(rs.getDate("sale_date"));
                sale.setSaleTimeEstimate(rs.getTime("sale_time_estimate"));
                sale.setSaleTotal(rs.getInt("sale_total"));
                salesList.add(sale);
            }

        } catch (SQLException ex) {
            FxDialogs.showException("Erro de Leitura!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return salesList;
    }

}
