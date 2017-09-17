/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.controller;

import javafx.application.Application;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.stage.Stage;


/**
 *
 * @author SAMSUNG
 */
public abstract class Controller extends Application implements Initializable{
    
    public abstract void controllerUndecored(Parent root, Stage stage);
    
    public abstract void controllerDecored(Parent root, Stage stage);
    
    protected void instanceFXML(String path) {
    
    }
}