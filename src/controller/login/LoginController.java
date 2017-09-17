package controller.login;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import controller.controller.Controller;

/**
 * FXML Controller class
 *
 * @author LucasFsc
 */
public class LoginController extends Controller {

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
    public void start(Stage stage) {
        try {
            this.window = stage;
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("controller/login/Login.fxml"));

            window.setTitle("Login");
            window.initStyle(StageStyle.UNDECORATED);
            window.setScene(new Scene(root));
            window.setOnCloseRequest(e -> this.closeApplication());
            window.show();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(LoginController.class, args);
    }

    /**
     * Initilize data
     */
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
        btn_sair.setOnMouseClicked(event -> this.closeApplication());
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
                openMain();
                this.closeApplication();
            }
        }
        //</editor-fold>

    }

    @FXML
    private void handlerButtonActionEntrar(MouseEvent event) {
        System.out.println(txt_login.getText() + " " + txt_senha.getText());

        if (checkLogin()) {
            lblWarning.setText("OK");
            openMain();
            this.closeApplication();
        } else {
            //JOptionPane.showMessageDialog(null, "errou");
            lblWarning.setText("Login ou Senha incorretos!");
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Controller methods">
    @Override
    public void controllerDecored(Parent root, Stage stage) {
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Main");
        stage.setScene(new Scene(root));
        stage.show();

    }
    
    @Override
    public void controllerUndecored(Parent root, Stage stage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    //</editor-fold>

    public void openMain() {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("controller/main/main.fxml"));
            controllerDecored(root, new Stage());
        } catch (Exception e) {
            System.out.println("tahas "+e);
        }
    }

    public boolean checkLogin() {
        if (txt_login.getText().equals("admin") && txt_senha.getText().equals("admin")) {
            return true;
        } else {
            return false;
        }
    }

}
