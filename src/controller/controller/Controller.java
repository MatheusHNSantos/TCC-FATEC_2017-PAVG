/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.controller;

import javafx.event.Event;
import javafx.scene.Node;
import java.io.IOException;
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
        Parent root;
        root = FXMLLoader.load(Class.getClassLoader().getResource(this.getFormPath()));
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