package controller.login;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;

import controller.BaseController;
import controller.dashboard.DashboardController;
import dao.entity.person.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.entity.person.user.User;
import util.fxml.Loader;

import javax.swing.*;

/**
 * FXML BaseController class
 *
 * @author LucasFsc
 */
public class LoginController extends BaseController implements Initializable{
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


        btn_entrar.setOnMouseClicked(this::handlerButtonActionEntrar);
        btn_sair.setOnMouseClicked(this::handlerButtonActionSair);
    }
    
    @FXML
    private void handlerButtonActionEntrar(MouseEvent event) {
        User user = new User(txt_login.getText(), txt_senha.getText());
        UserDAO userDAO = new UserDAO();


        if(userDAO.doLogin(user)){
            System.out.println("Acesso Liberado!");
        }else{
            System.out.println("Acesso Negado!");
        }


    }

    @FXML
    private void handlerButtonActionSair(MouseEvent event) {

            Stage stage = (Stage) btn_sair.getScene().getWindow(); //Obtendo a janela atual
            stage.close(); //Fechando o Stage

    }
        
    public static Stage loader() throws IOException {
        return Loader.loader(LoginController.class, StageStyle.UNDECORATED, "login.fxml", "Meucu");
    }
}