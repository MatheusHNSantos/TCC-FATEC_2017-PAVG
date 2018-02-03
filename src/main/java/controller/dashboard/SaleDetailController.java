package controller.dashboard;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SaleDetailController implements Initializable {

    private static final String path = "saleDetail.fxml";
    private static final String title = "Detalhes da Venda";

    @FXML    private Label lbl_id; //TextField de campo para id venda
    @FXML    private Label lbl_costumer; //TextField de campo para cliente
    @FXML    private Label lbl_user; //TextField de campo para usuario
    @FXML    private Label lbl_date; //TextField de campo para data
    @FXML    private Label lbl_time; //TextField de campo para hora
    @FXML    private Label lbl_price; //TextField de campo para preço
    @FXML    private TableView<?> tview_saleDetail; //Tabela

    @Override
    public void initialize(URL url, ResourceBundle rb){

    }

    public static Stage loader() throws IOException {

        Stage stage;
        stage = Controller.loader(DashboardController.class, StageStyle.DECORATED, path, title);
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        return stage;

    }
}
