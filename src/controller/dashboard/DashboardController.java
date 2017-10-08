/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dashboard;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

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
