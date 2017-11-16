/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import controller.dashboard.DashboardController;
import controller.login.LoginController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 *
 * @author SAMSUNG
 */
public class Main extends Application {

    public void closeApplication() {
        Platform.exit();
    }

    @Override
    public void start(Stage PrimaryStage) {
        try {
            //DashboardController.loader().show();
            LoginController.loader().show();
        }
        catch (Exception e) {
            System.out.println(e.getCause());
        }


    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(app.Main.class, args);
    }
}
