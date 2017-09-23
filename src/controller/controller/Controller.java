/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.controller;

import com.sun.javaws.Main;
import controller.login.LoginController;
import controller.dashboard.DashboardController;
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

    public static Stage createStageInstance(String path, Class<?> Class) throws IOException {
        Parent root = FXMLLoader.load(Class.getClassLoader().getResource(path));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        return stage;
    }
   
    public void closeApplication() {
        Platform.exit();
    }
    
    
    @Override
    public void start(Stage PrimaryStage) {
        try {
            load("login");
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
    public static String pathLogin = "controller/login/login.fxml";
    
    public static void load(String locate) throws IOException {
        
        switch (locate) {
            case "main":
                loadCaller(pathMain, DashboardController.class, "main");
                break;
            
            case "login":
                loadCaller(pathLogin, LoginController.class, "login");
                break;
        }
    }

    public static void loadCaller(String path, Class<?> Class, String title) throws  IOException {
        Stage stage = createStageInstance(path, Class);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle(title);
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