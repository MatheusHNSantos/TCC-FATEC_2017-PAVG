package controller.dashboard;

import com.jfoenix.controls.JFXButton;
import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HistoricCostumerController implements Initializable {

    private static final String path = "historicCostumer.fxml";
    private static final String title = "Histórico de Compras do Cliente";

    @FXML    private TableView<?> tview_salesHistoric; //Tabela de ingredientes
    @FXML    private TableView<?> tview_productsHistoric; //Tabela de ingredientes
    @FXML    private Label lbl_nameCostumerHistoric; //TextField de campo para função de funcionario
    @FXML    private JFXButton btn_closeHistoric; //Botão Editar

    @Override
    public void initialize(URL url, ResourceBundle rb){
        btn_closeHistoric.setOnMouseClicked(this::handlerButtonActionClose);
    }

    private void handlerButtonActionClose(MouseEvent event) {
        Controller.closeApplication(event);
    }

    public static Stage loader() throws IOException {

        Stage stage;
        stage = Controller.loader(DashboardController.class, StageStyle.DECORATED, path, title);
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        return stage;

    }
}
