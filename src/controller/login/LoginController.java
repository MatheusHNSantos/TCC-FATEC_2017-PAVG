package controller.login;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LucasFsc
 */
public class LoginController extends Controller implements Initializable{
    //<editor-fold defaultstate="collapsed" desc="Variables"> 
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
    // </editor-fold> 

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

        //<editor-fold defaultstate="collapsed" desc="Handlers"> 
        //create handler conversion methods (main actions - ignores fxml)
        txt_senha.setOnKeyReleased(this::handlerPasswordEnterPressed);
        btn_sair.setOnMouseClicked(this::handlerButtonActionSair);
        btn_entrar.setOnMouseClicked(this::handlerButtonActionEntrar);
        //</editor-fold>

    }

    //<editor-fold defaultstate="collapsed" desc="Handler methods"> 
    @FXML
    private void handlerPasswordEnterPressed(KeyEvent event) {
        
        //<editor-fold defaultstate="collapsed" desc="Shortcut enter key for password field"> 

        System.out.println(event.getCode());
        if (event.getCode() == KeyCode.ENTER) {
            if (checkLogin()) {
                System.out.println("Open main");
                //openMain();
                Controller.closeApplication(event);
            }else{
                lblWarning.setText("Login ou Senha incorretos!");
            }
        }
        //</editor-fold>

    }

    @FXML
    private void handlerButtonActionEntrar(MouseEvent event) {
        System.out.println(txt_login.getText() + " " + txt_senha.getText());

        if (checkLogin()) {
            lblWarning.setText("OK");
            //openMain();
            System.out.println("teste");
            Controller.closeApplication(event);
        } else {
            //JOptionPane.showMessageDialog(null, "errou");
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
    //</editor-fold>

    public boolean checkLogin() {
        if (txt_login.getText().equals("admin") && txt_senha.getText().equals("admin")) {
            return true;
        } else {
            return false;
        }
    }  

    public Stage createStageInstance() throws IOException {
                
        this.setFormPath("controller/login/login.fxml");
        return this.createStageInstance(LoginController.class);
    }

}
