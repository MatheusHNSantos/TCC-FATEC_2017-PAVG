package controller.dashboard;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import controller.Controller;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.entity.product.Ingredient;
import model.entity.product.Product;
import model.entity.product.ProductType;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static java.lang.String.valueOf;

public class EditIngredientsController implements Initializable {

    private static final String path = "editIngredients.fxml";
    private static final String title = "Editar Ingredientes";

    @FXML    private TableView<Ingredient> tview_ingredients; //Tabela de funcionarios
    @FXML    private TableColumn<Ingredient, String> columnIngredientName;
    @FXML    private TableColumn<Ingredient, Ingredient> columnIngredientPrice;
    @FXML    private TableColumn<Ingredient, Ingredient> columnIngredientStatus;
    @FXML    private JFXButton btn_saveIngredient; //Botão Salvar
    @FXML    private JFXButton btn_cancelIngredient; //Botão Cancelar
    @FXML    private JFXButton btn_newSIngredient; //Botão Criar
    @FXML    private JFXButton btn_editIngredient; //Botão Editar
    @FXML    private JFXButton btn_searchIngredient; //Botão Pesquisar
    @FXML    private JFXTextField txt_searchIngredient; //TextField de campo para pesquisa de Ingredientes
    @FXML    private JFXTextField txt_nameIngredient; //TextField de Nome do Ingredientes
    @FXML    private JFXTextField txt_priceIngredient; //TextField Preço final do Ingredientes
    @FXML    private Group group_dataIngredient; //Agrupamento de TextField atributos Ingredientes
    @FXML    private Group group_dataIngredientBtnSaveCancel; //Salvar e Cancelar
    @FXML    private Group group_dataIngredientBtnEditAdd; //Alterar e Adicionar
    @FXML    private JFXToggleButton tbtn_statusIngredient; //Seletor de Status do Ingredientes
    @FXML    private JFXCheckBox cbox_showInactives; //checkbox mostrar inativos

    private String actionIngredient = "";
    private boolean viewInactive = true;

    private ObservableList<Ingredient> dataObervableIngredient = FXCollections.observableArrayList();
    private ArrayList<Ingredient> listIngredient = new ArrayList<>();
    private int idIngredientSelected = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        btn_editIngredient.setDisable(true);

        btn_editIngredient.setOnMouseClicked(this::handlerButtonActionEditIngredient);
        btn_newSIngredient.setOnMouseClicked(this::handlerButtonActionAddIngredient);
        btn_cancelIngredient.setOnMouseClicked(this::handlerButtonActionCancelIngredient);
        btn_saveIngredient.setOnMouseClicked(this::handlerButtonActionSaveIngredient);
        tbtn_statusIngredient.setOnMouseClicked(this::handlerButtonActionStatusIngredient);
        cbox_showInactives.setOnMouseClicked(this::handlerButtonActionCB);


        //region TableView Ingredients
        columnIngredientName.setCellValueFactory(new PropertyValueFactory<>("nameIngredient"));
        columnIngredientPrice.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue()));
        columnIngredientPrice.setCellFactory(column -> new TableCell<Ingredient, Ingredient>() {

            private VBox graphic ;
            private Label labelPrice ;

            // Anonymous constructor:
            {
                graphic = new VBox();
                labelPrice = createLabel();
                graphic.getChildren().addAll(labelPrice);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }

            @Override
            public void updateItem(Ingredient ingredient, boolean empty) {
                if (ingredient == null) {
                    setGraphic(null);
                } else {

                    labelPrice.setText("R$ " + ingredient.getPrice());
                    setGraphic(graphic);
                }
            }
        });
        columnIngredientStatus.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue()));
        columnIngredientStatus.setCellFactory(column -> new TableCell<Ingredient, Ingredient>() {

            private VBox graphic ;
            private Label labelStatus ;

            // Anonymous constructor:
            {
                graphic = new VBox();
                labelStatus = createLabel();
                graphic.getChildren().addAll(labelStatus);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }

            @Override
            public void updateItem(Ingredient ingredient, boolean empty) {
                if (ingredient == null) {
                    setGraphic(null);
                } else {
                    boolean status = ingredient.getStatusIngredient();
                    if(status)
                        labelStatus.setText("Ativo");
                    else
                        labelStatus.setText("Inativo");


                    setGraphic(graphic);
                }
            }
        });

        listIngredient = Ingredient.ReadAll();
        dataObervableIngredient.addAll(listIngredient);
        tview_ingredients.setItems(dataObervableIngredient);
        //endregion

        //region Ingredient Details
        showIngredientDetails(null);

        tview_ingredients.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showIngredientDetails(newValue));
        //endregion

    }

    private void resetTableViewIngredient(){
        listIngredient = Ingredient.ReadAll();
        dataObervableIngredient.clear();
        dataObervableIngredient.addAll(listIngredient);
    }

    private void showIngredientDetails(Ingredient ingredient) {
        setIngredientActiveButtons(true, false, "");
        tbtn_statusIngredient.setSelected(false);
        tbtn_statusIngredient.setText("Inativo");
        if (ingredient != null) {
            btn_editIngredient.setDisable(false);
            txt_nameIngredient.setText(ingredient.getNameIngredient());
            txt_priceIngredient.setText(valueOf(ingredient.getPrice()));


            idIngredientSelected  = ingredient.getIdIngredient();

            tbtn_statusIngredient.setSelected(ingredient.getStatusIngredient());
            if(ingredient.getStatusIngredient()) tbtn_statusIngredient.setText("Ativo");
            else tbtn_statusIngredient.setText("Inativo");

        } else {
            this.clearProductDetails();

        }
    }


    private void clearProductDetails(){
        txt_nameIngredient.setText("");
        txt_priceIngredient.setText("");
        tbtn_statusIngredient.setSelected(false);
        tbtn_statusIngredient.setText("Inativo");
        idIngredientSelected = 0;
        btn_editIngredient.setDisable(true);
    }

    private final Label createLabel() {
        Label label = new Label();
        VBox.setVgrow(label, Priority.ALWAYS);
        label.setMaxWidth(Double.MAX_VALUE);
        label.setAlignment(Pos.CENTER);
        return label ;
    }

    //region defaut methods
    private void handlerButtonActionCB(MouseEvent event) {
        viewInactive = !viewInactive;
    }
    private void setIngredientActiveButtons(Boolean bool_1, Boolean bool_2,String action){
        group_dataIngredient.setDisable(bool_1);
        group_dataIngredientBtnSaveCancel.setDisable(bool_1);
        group_dataIngredientBtnEditAdd.setDisable(bool_2);
        if(!action.equals("node"))
            actionIngredient = action;

    }
    private void handlerButtonActionEditIngredient(MouseEvent event) {
        setIngredientActiveButtons(false,true,"Editar");
    }
    private void handlerButtonActionAddIngredient(MouseEvent event) {
        setIngredientActiveButtons(false,true,"Adicionar");
        clearProductDetails();
        tbtn_statusIngredient.setSelected(true);
        tbtn_statusIngredient.setText("Ativo");
    }
    private void handlerButtonActionCancelIngredient(MouseEvent event) {

        if(actionIngredient.equals("Status")){
            if(tbtn_statusIngredient.isSelected()){
                tbtn_statusIngredient.setSelected(false);
            }else{
                tbtn_statusIngredient.setSelected(true);
            }
        }
        setIngredientActiveButtons(true,false,"");
    }
    private void handlerButtonActionSaveIngredient(MouseEvent event) {
        setIngredientActiveButtons(true,false,"node");
        switch (actionIngredient ){
            case "Editar":
                saveProduct();
                break;
            case "Adicionar":
                addProduct();
                break;
            case "Status":
                setStatusProduct();
                break;
            default:
                break;
        }
        actionIngredient = "";
    }
    private void handlerButtonActionStatusIngredient(MouseEvent event) {
        group_dataIngredientBtnSaveCancel.setDisable(false);
        group_dataIngredientBtnEditAdd.setDisable(true);
        if(!actionIngredient.equals("Editar"))
            if(!actionIngredient.equals("Adicionar"))
                actionIngredient = "Status";
    }
    //endregion

    private void saveProduct(){
        //Código banco aqui
        Ingredient ingredient = new Ingredient(idIngredientSelected);
        ingredient.setNameIngredient(txt_nameIngredient.getText());
        ingredient.setPrice(Float.parseFloat(txt_priceIngredient.getText()));
        ingredient.setStatusIngredient(tbtn_statusIngredient.isSelected());
        ingredient.Save();

        resetTableViewIngredient();

    }

    private void addProduct(){
        //Código banco aqui
        Ingredient ingredient = new Ingredient();
        ingredient.setNameIngredient(txt_nameIngredient.getText());
        ingredient.setPrice(Float.parseFloat(txt_priceIngredient.getText()));
        ingredient.setStatusIngredient(tbtn_statusIngredient.isSelected());
        ingredient.Create();

        resetTableViewIngredient();
    }

    private void setStatusProduct(){
        saveProduct();
    }


    public static Stage loader() throws IOException {

        Stage stage;
        stage = Controller.loader(DashboardController.class, StageStyle.DECORATED, path, title);
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        return stage;

    }
}
