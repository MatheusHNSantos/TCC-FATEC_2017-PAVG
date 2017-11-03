/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.entity.person.user.User;
import model.entity.phone.Phone;

import java.io.IOException;

/**
 *
 * @author SAMSUNG
 */
public class Controller {
    
    private static final String sPathControl = "fxml/";
    
    public static Stage loader(Class<?> Class, StageStyle style, String path, String title) throws IOException {
        Stage stage = createStageInstance(sPathControl + path, Class);
        stage.initStyle(style);
        stage.setTitle(title);
        return stage;
    }

    public static Stage createStageInstance(String path, Class<?> Class) throws IOException {
        Parent root = FXMLLoader.load(Class.getClassLoader().getResource(path));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        return stage;
    }

    public void closeApplication() {
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

    public static void renameApplication(Event e, String title) {
        Stage stage = (Stage) ((Node) e.getTarget()).getScene().getWindow();
        stage.setTitle(title);
        stage.notify();
    }
}