/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import controller.dashboard.DashboardController;
import controller.login.LoginController;
import javafx.stage.Stage;
import javafx.application.Application;
import util.fxml.Loader;

/**
 *
 * @author SAMSUNG
 */
public class Main extends Application {
    
    @Override
    public void start(Stage PrimaryStage) {
        try {
           //LoginController.loader().show();
            DashboardController.loader().show();
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