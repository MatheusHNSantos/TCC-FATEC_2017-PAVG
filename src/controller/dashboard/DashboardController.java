/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dashboard;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import controller.controller.Controller;
import controller.login.LoginController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author SAMSUNG
 */
public class DashboardController implements Initializable{
    
    @FXML
    JFXButton btnTest;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnTest.setOnMouseClicked(this::handlerClicked);
    }
    
    
    public void handlerClicked(MouseEvent event) {
        System.exit(0);
    }

    
    
}
