/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.controller;

import com.sun.javaws.Main;
import controller.main.MainController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;

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


    public static String pathMain = "controller/main/main.fxml";

    public static void load(String locate) throws IOException {
        
        switch (locate) {
            case "main":
                loadCaller(pathMain, MainController.class, "main");
                
                break;
        }
    }

    public static void loadCaller(String path, Class<?> Class, String title) throws  IOException {
        Parent root = FXMLLoader.load(Class.getClassLoader().getResource(path));
        Stage stage = new Stage();
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    public static void closeApplication(Event e) {
        Stage stage = (Stage) ((Node) e.getTarget()).getScene().getWindow();
        stage.close();
    }

    public static void hideApplication(Event e) {
        Stage stage = (Stage) ((Node) e.getTarget()).getScene().getWindow();
        stage.hide();
    }

    public static void showApplication(Event e) {
        Stage stage = (Stage) ((Node) e.getTarget()).getScene().getWindow();
        stage.show();
    }

}