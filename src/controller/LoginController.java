
package controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author felipemantoan
 */
public class LoginController extends Application implements Initializable{

    
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
    private AnchorPane frm_login;
    @FXML
    private Label lblWarning;
    @FXML
    JFXButton btn_entrar;
    
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn_entrar.setOnMouseClicked(this::handlerButtonAction);
    }

    @FXML
    private void handlerButtonAction(MouseEvent event) {
        System.exit(0);
    }
}
