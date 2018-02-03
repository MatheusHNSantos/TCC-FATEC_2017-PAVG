package controller.dashboard;

import com.jfoenix.controls.JFXButton;
import controller.Controller;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FinishSaleController implements Initializable {

    private static final String path = "finishSale.fxml";
    private static final String title = "Finalizar Pedido";

    public static DashboardController dashboardController;

    @FXML    JFXButton finishSaleOnDialog;

    public interface OnClickFinishButton{
        void finishButton(int decision);
    }

    OnClickFinishButton onClickFinishButton;

    @FXML private Label lbl_totalPriceOnFinishSaleDialog;

    @Override
    public void initialize(URL url, ResourceBundle rb){

        finishSaleOnDialog.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                onClickFinishButton = dashboardController;
                onClickFinishButton.finishButton(1);
            }
        });

    }

    public static Stage loader() throws IOException {
        return Controller.loader(FinishSaleController.class,StageStyle.DECORATED,path,title);
        /*Stage stage;
        stage = Controller.loader(DashboardController.class, StageStyle.DECORATED, path, title);
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        return stage;*/

    }

    public void setPrice(String price){
        this.lbl_totalPriceOnFinishSaleDialog.setText(price);
    }
}
