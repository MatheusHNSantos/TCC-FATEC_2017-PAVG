/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import controller.login.LoginController;
import dao.entity.address.AddressDAO;
import dao.entity.phone.PhoneDAO;
import javafx.stage.Stage;
import javafx.application.Application;
import model.entity.address.Address;
import model.entity.phone.Phone;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SAMSUNG
 */
public class Main extends Application {

    @Override
    public void start(Stage PrimaryStage) {
        try {
           LoginController.loader().show();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(app.Main.class, args);
    }
}