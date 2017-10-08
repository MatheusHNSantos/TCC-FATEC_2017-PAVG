package controller.login;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import controller.controller.Controller;
import java.io.IOException;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author LucasFsc
 */
public class LoginController implements Initializable{
    private Thread one;
    @FXML
    Button btn_sair;
    @FXML
    TextField txt_login;
    @FXML
    PasswordField txt_senha;
    @FXML
    Label lbl_login;
    @FXML
    Label lbl_senha;
    @FXML
    AnchorPane frm_login;
    @FXML
    Label lblWarning;
    @FXML
    JFXButton btn_entrar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        lblWarning.setText("");

        //Thread to wait css animation
        one = new Thread() {
            public void run() {
                try {
                    Thread.sleep(300);
                    System.exit(0);
                } catch (InterruptedException v) {
                    System.out.println(v);
                }
            }
        };
        txt_senha.setOnKeyReleased(this::handlerPasswordEnterPressed);
        btn_sair.setOnMouseClicked(this::handlerButtonActionSair);
        btn_entrar.setOnMouseClicked(this::handlerButtonActionEntrar);
    }

    @FXML
    private void handlerPasswordEnterPressed(KeyEvent event) {
        System.out.println(event.getCode());
        if (event.getCode() == KeyCode.ENTER) {
            if (checkLogin()) {
                System.out.println("Open main");
                
                Controller.closeApplication(event);
            }else{
                lblWarning.setText("Login ou Senha incorretos!");
            }
        }
    }

    @FXML
    private void handlerButtonActionEntrar(MouseEvent event) {
        System.out.println(txt_login.getText() + " " + txt_senha.getText());

        if (checkLogin()) {
            lblWarning.setText("OK");
            
        } else {
            lblWarning.setText("Login ou Senha incorretos!");
        }
    }

    @FXML
    private void handlerButtonActionSair(MouseEvent event) {

        try {
            one.start();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }
    
    public boolean checkLogin() {
        if (txt_login.getText().equals("admin") && txt_senha.getText().equals("admin")) {
            return true;
        } else {
            return false;
        }
    }
    
    public static Stage loader() throws IOException {
        return Controller.loader(LoginController.class, StageStyle.UNDECORATED, "login/login.fxml", "Meucu");
    }
}
