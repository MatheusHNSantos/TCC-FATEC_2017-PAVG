/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author felipemantoan
 */
public class LoginController extends Application{

    Button btn_entrar;
    Button btn_sair;
    TextField txt_login;
    PasswordField txt_senha;
    Label lbl_login;
    Label lbl_senha;
    
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/Login.fxml"));

            stage.setTitle("FXML Welcome");
            stage.setScene(new Scene(root));
            stage.show();
            
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(LoginController.class, args);
    }
}
