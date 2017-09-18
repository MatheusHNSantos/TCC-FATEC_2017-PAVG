/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.controller;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author SAMSUNG
 */
public class Controller extends Application {
    
    public Stage createStageInstance(Stage stage, String path) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(path));
        stage.setScene(new Scene(root));
        return stage;
    }
   
    public void closeApplication() {
        Platform.exit();
    }
    
    
    @Override
    public void start(Stage PrimaryStage) {
        try {
            this.createStageInstance(PrimaryStage, "controller/login/Login.fxml");
            PrimaryStage.show();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(Controller.class, args);
    }

}