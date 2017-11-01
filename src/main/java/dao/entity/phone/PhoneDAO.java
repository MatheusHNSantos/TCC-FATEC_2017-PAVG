/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity.phone;

import model.entity.phone.Phone;
import model.entity.product.ProductType;
import util.connection.ConnectionFactory;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Matheus Henrique
 */
public class PhoneDAO {
    public boolean create(Phone phone) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;


        try {
            stmt = con.prepareStatement("insert into phone values(phone,id_person) values(?,?)");
            stmt.setString(1, phone.getPhone());
            stmt.setInt(2, phone.getId_person());
            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar: " + ex.getMessage());
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);

        }

    }
    
}
