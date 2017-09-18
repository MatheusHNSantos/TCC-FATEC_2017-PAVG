/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author SAMSUNG
 */
public class MainController extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("controller/main/main.fxml"));
            primaryStage.setTitle("Login");
            primaryStage.initStyle(StageStyle.DECORATED);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
    }

    
    
}
