/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.apptitoso;

import controller.login.LoginController;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.stage.StageStyle;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.application.Application;

/**
 *
 * @author SAMSUNG
 */
public class Apptitoso extends Application {
   
    public void closeApplication() {
        Platform.exit();
    }
    
    
    @Override
    public void start(Stage PrimaryStage) {
        try {
            LoginController lc = new LoginController();
            Stage stage;
            stage = lc.createStageInstance();
            
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Login");
            stage.show();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(Apptitoso.class, args);
    }
}