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

/**
 *
 * @author SAMSUNG
 */
public class Controller {
    
    private String formPath;

    public String getFormPath() {
        return formPath;
    }

    public void setFormPath(String formPath) {
        this.formPath = formPath;
    }
        
    public Stage createStageInstance(Class<?> Class) throws IOException {
        System.out.println(Class.getClassLoader().getResource(this.formPath));
        Parent root;
        root = FXMLLoader.load(Class.getClassLoader().getResource(this.formPath));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        return stage;
    }
   
    public static void closeApplication() {
        Platform.exit();
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