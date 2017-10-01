/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dashboard;

import com.jfoenix.controls.JFXButton;
import controller.controller.Controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SAMSUNG
 */
public class DashboardController extends Controller implements Initializable{
    
    @FXML
    JFXButton btnTest;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnTest.setOnMouseClicked(this::handlerClicked);
    }
    
    
    public void handlerClicked(MouseEvent event) {
        System.exit(0);
    }

    public Stage createStageInstance() throws IOException {        
        this.setFormPath("controller/dashboard/dashboard.fxml");
        return this.createStageInstance(DashboardController.class);
    }
    
    
}
