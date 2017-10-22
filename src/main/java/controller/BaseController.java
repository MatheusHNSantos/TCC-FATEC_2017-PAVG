/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 *
 * @author SAMSUNG
 */
public class BaseController {
    
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