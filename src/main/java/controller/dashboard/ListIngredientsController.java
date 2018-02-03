package controller.dashboard;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.entity.person.costumer.Costumer;
import model.entity.product.Ingredient;
import model.entity.product.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ListIngredientsController implements Initializable {

    private static final String path = "listIngredients.fxml";
    private static final String title = "Adicionar Ingredientes";

    @FXML    private TableView<Ingredient> tview_ingredient; //Tabela de ingredientes
    @FXML    private TableColumn<Ingredient, String> columnIngredientName;
    @FXML    private JFXTextField txt_searchIngredient; //TextField de campo para pesquisa de ingredientes
    @FXML    private JFXButton btn_newIngredient; //Botão Criar
    @FXML    private JFXButton btn_backIngredient; //Botão voltar
    @FXML    private JFXButton btn_searchIngredient; //Botão Pesquisar

    public ObservableList<Ingredient> dataObervableIngredient = FXCollections.observableArrayList();

    public ArrayList<Ingredient> listIngredient = new ArrayList<>();

    private int idIngredient = 0;

    private Ingredient ingredient;
    @Override
    public void initialize(URL url, ResourceBundle rb){
        btn_backIngredient.setOnMouseClicked(this::handlerButtonActionBack);
        btn_newIngredient.setOnMouseClicked(this::handlerButtonActionNew);

        columnIngredientName.setCellValueFactory(new PropertyValueFactory<>("nameIngredient"));

        listIngredient = Ingredient.ReadAll();
        dataObervableIngredient.addAll(listIngredient);
        tview_ingredient.setItems(dataObervableIngredient);


        tview_ingredient.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> setIdIngredient(newValue));
    }

    private void setIdIngredient(Ingredient ingredient){
        this.ingredient = ingredient;
    }

    private void handlerButtonActionBack(MouseEvent event) {
        Controller.closeApplication(event);
    }
    private void handlerButtonActionNew(MouseEvent event) {
        if(ingredient != null) DashboardController.addIngredient(ingredient);
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
