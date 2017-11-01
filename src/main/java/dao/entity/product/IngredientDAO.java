/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity.product;

import model.entity.product.Ingredient;
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
public class IngredientDAO {

    public boolean create(Ingredient ingredient) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;


        try {
            stmt = con.prepareStatement("insert into ingredient values(name_ingredient,status_ingredient,price) values(?,?,?)");
            stmt.setString(1, ingredient.getName());
            stmt.setBoolean(2, ingredient.getStatus());
            stmt.setFloat(3, ingredient.getPrice());

            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar: " + ex.getMessage());
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);

        }

    }

    public Ingredient read(Ingredient ingredient) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;


        try {
            stmt = con.prepareStatement("select * from ingredient where id_ingredient = ? ");
            stmt.setInt(1, ingredient.getId());
            rs = stmt.executeQuery();

            ingredient.setId(rs.getInt("id_ingredient"));
            ingredient.setName(rs.getString("name_ingredient"));
            ingredient.setStatus(rs.getBoolean("status_ingredient"));
            ingredient.setPrice(rs.getFloat("price"));



        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao consultar: " + ex.getMessage());
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return ingredient;
    }


    public List<Ingredient> readAll() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Ingredient> ingredientList = new ArrayList<>();

        try {
            stmt = con.prepareStatement("select * from ingredient");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Ingredient ingredient = new Ingredient();
                ingredient.setId(rs.getInt("id_ingredient"));
                ingredient.setName(rs.getString("name_ingredient"));
                ingredient.setStatus(rs.getBoolean("status_ingredient"));
                ingredient.setPrice(rs.getFloat("price"));
                ingredientList.add(ingredient);


            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao consultar: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return ingredientList;
    }

    public boolean update(Ingredient ingredient) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("update ingredient set name_ingredient = ?, status_ingredient = ?, price = ? where id_ingredient = ?");
            stmt.setString(1, ingredient.getName());
            stmt.setBoolean(2, ingredient.getStatus());
            stmt.setFloat(3, ingredient.getPrice());
            stmt.setInt(4, ingredient.getId());

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

    public boolean delete(Ingredient ingredient) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("delete from ingredient where id_ingredient = ?");
            stmt.setInt(1, ingredient.getId());

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
