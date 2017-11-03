package controller.login;

import com.jfoenix.controls.JFXButton;
import controller.Controller;
import controller.dashboard.DashboardController;
import dao.entity.person.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    }

    @FXML
    private void handlerButtonActionEntrar(MouseEvent event) {
        User user = null;
        try {
            user = new User(txt_login.getText(), txt_senha.getText());

        UserDAO userDAO = new UserDAO();


        if(userDAO.doLogin(user)){
            lblWarning.setText("");
            //JOptionPane.showMessageDialog(null, "Acesso Liberado!");
            //FxDialogs.showInformation("Acesso Liberado!", "Seja bem vindo!");
            ((Node) (event.getSource())).getScene().getWindow().hide();
            DashboardController.loader().show();
            /*try {
                /*Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                URL url = getClass().getResource("/fxml/login.fxml");
                Parent root = FXMLLoader.load(url);
                Scene scene = new Scene(root);
                stage.setTitle("FXML Welcome");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }*/

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

    @FXML
    private void handlerButtonActionSair(MouseEvent event) {

        Stage stage = (Stage) btn_sair.getScene().getWindow(); //Obtendo a janela atual
        stage.close(); //Fechando o Stage

    }
    
    public static Stage loader() throws IOException {
        return Controller.loader(LoginController.class, StageStyle.UNDECORATED, path, title);
    }
}
