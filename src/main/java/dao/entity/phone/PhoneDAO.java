/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity.phone;

import model.entity.person.user.User;
import model.entity.phone.Phone;
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
public class PhoneDAO {
    public boolean  create(Phone phone) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;


        try {
            stmt = con.prepareStatement("insert into phone (phone,id_person) values(?,?)");
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

    public Phone read(Phone phone){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("select * from phone where id_phone = ?");
            stmt.setInt(1, phone.getId());
            rs = stmt.executeQuery();
            rs.next();
            phone.setId(rs.getInt("id_phone"));
            phone.setPhone(rs.getString("phone"));
            phone.setId_person(rs.getInt("id_person"));


        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao consultar: " + ex.getMessage());
        }finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
       return phone;
    }

    public List<Phone> readAll() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Phone> phoneList = new ArrayList<>();

        try {
            stmt = con.prepareStatement("select * from phone");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Phone phone = new Phone();
                phone.setId(rs.getInt("id_phone"));
                phone.setPhone((rs.getString("phone")));
                phone.setId_person(rs.getInt("id_person"));
                phoneList.add(phone);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao consultar: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return phoneList;
    }

    public boolean update(Phone phone) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("update phone set phone = ?, id_person = ? where id_phone = ?");
            stmt.setString(1, phone.getPhone());
            stmt.setInt(2, phone.getId_person());
            stmt.setInt(3, phone.getId());

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

    public boolean delete(Phone phone) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("delete from phone where id_phone = ?");
            stmt.setInt(1, phone.getId());

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
