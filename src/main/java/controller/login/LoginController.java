package controller.login;

import com.jfoenix.controls.JFXButton;
import controller.Controller;
import controller.dashboard.DashboardController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.entity.person.user.User;
import util.dialogs.FxDialogs;
import util.exception.UserException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author LucasFsc
 */
public class LoginController implements Initializable{
    
    private static final String path = "login.fxml";
    private static final String title = "PAVG Apetitoso - Login";
    
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

    private int mandioca;

    public int getMandioca() {
        return mandioca;
    }

    public void setMandioca(int mandioca) {
        this.mandioca = mandioca;
    }

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

        btn_sair.setOnMouseClicked(this::handlerButtonActionSair);
        btn_entrar.setOnMouseClicked(this::handlerButtonActionEntrar);

        txt_login.setOnKeyPressed(this::handlerKeyPressedActionEnter);
        txt_senha.setOnKeyPressed(this::handlerKeyPressedActionEnter);
    }

    @FXML
    private void handlerKeyPressedActionEnter(KeyEvent event){

        if(event.getCode().toString().equals("ENTER")){
            executeLogin(event);
        }

    }

    @FXML
    private void handlerButtonActionEntrar(MouseEvent event) {
        /*MODULARIZAR METODOS NAO DEIXAR CODIGO CRITICO DENTRO DE BOTOES PORQUE MAIS METODOS PODEM UTILIZAR O MESMO
        NESTE CASO VOU COLOCAR EVENT KEYLISTENER PRO ENTER NOS CAMPOS AI CHAMA O MESMO METODO DE LOGIN POUPANDO CODIGO*/
        executeLogin(event);
    }

    @FXML
    private void handlerButtonActionSair(MouseEvent event) {

        /* ISSO PODE SER SUBSTITUIDO POR ISSO
        Stage stage = (Stage) btn_sair.getScene().getWindow(); //Obtendo a janela atual
        stage.close(); //Fechando o Stage
        */

        Controller.closeApplication(event);
    }

    private void executeLogin(Event event){

        //User user = null; -> SIMPLIFICAR
        User user;

        try {
            user = new User(txt_login.getText(), txt_senha.getText());

            if(user.doLogin()){
                user = new User(txt_login.getText());
                if(user.getStatus()) {
                    lblWarning.setText("");

                    //((Node) (event.getSource())).getScene().getWindow().hide();
                    //da pra trocar isso aqui por
                    Controller.hideApplication(event);
                    //Controller.closeApplication(event);

                    DashboardController.setUser(user);
                    DashboardController.loader().show();
                    /*DashboardController ds = new DashboardController();
                    ds.setUser(user);
                    ds.loader().show();*/
                }else{
                    FxDialogs.showError("Acesso Negado!","Login inativo!");
                    lblWarning.setText("Acesso Negado!");
                }

            }else{
                FxDialogs.showError("Acesso Negado!","Usuário ou senha incorretos");
                lblWarning.setText("Acesso Negado!");
            }
        } catch (UserException ex) {
            FxDialogs.showWarning(ex.getMessage(), "Tente novamente.");
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public static Stage loader() throws IOException {
        return Controller.loader(LoginController.class, StageStyle.UNDECORATED, path, title);
    }
}
