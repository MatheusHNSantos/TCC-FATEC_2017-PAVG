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
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;

/**
 *
 * @author SAMSUNG
 */
public class Controller {
    
    private static final String sPathControl = "fxml/";

    private static Class<? extends Window> controlledStage;
    
    public static Stage loader(Class<?> Class, StageStyle style, String path, String title) throws IOException {
        Stage stage = createStageInstance(sPathControl + path, Class);
        Controller ls = new Controller();
        stage = ls.icons(stage);
        stage.initStyle(style);
        stage.setTitle(title);

        controlledStage = stage.getClass();
        return stage;
    }

    public Stage icons(Stage stage){
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/icon.jpg")));
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

    public static Object getControlledStage(){
        return controlledStage.getClass();
    }
}