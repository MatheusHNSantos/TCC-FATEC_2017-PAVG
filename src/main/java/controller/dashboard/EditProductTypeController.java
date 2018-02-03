package controller.dashboard;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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

public class EditProductTypeController implements Initializable {

    private static final String path = "editProductType.fxml";
    private static final String title = "Editar Categorias";

    @FXML    private TableView<ProductType> tview_productType; //Tabela de tipos de ingredientes
    @FXML    private TableColumn<ProductType, String> columnProductTypeName;
    @FXML    private JFXTextField txt_nameProductType; //TextField de campo para pesquisa tipos de ingredientes
    @FXML    private JFXButton btn_saveProductType; //Botão Salvar
    @FXML    private JFXButton btn_cancelProductType; //Botão Cancelar
    @FXML    private JFXButton btn_newProductType; //Botão Criar
    @FXML    private JFXButton btn_dropProductType; //Botão Editar
    @FXML    private JFXButton btn_editProductType; //Botão Remover
    @FXML    private Group group_PTBtnSaveCancel; //Salvar e Cancelar
    @FXML    private Group group_PTBtnEditAdd; //Editar e Adicionar
    @FXML    private Hyperlink btn_removePhotoPT;
    @FXML    private Hyperlink btn_editPhotoPT; //Editar tipo de ingrediente
    @FXML    private ImageView imageview_PT; //foto do tipo da categoria

    private String actionPT = "";

    public ObservableList<ProductType> dataObervableProductType = FXCollections.observableArrayList();

    public ArrayList<ProductType> listProductType = new ArrayList<>();

    private int idProductTypeSelected = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        btn_editProductType.setDisable(true);

        btn_editProductType.setOnMouseClicked(this::handlerButtonActionEditProductType);
        btn_newProductType.setOnMouseClicked(this::handlerButtonActionNewProductType);
        btn_cancelProductType.setOnMouseClicked(this::handlerButtonActionCancelProductType);
        btn_saveProductType.setOnMouseClicked(this::handlerButtonActionSaveProductType);
        btn_dropProductType.setOnMouseClicked(this::handlerButtonActionDropProductType);

        columnProductTypeName.setCellValueFactory(new PropertyValueFactory<>("nameProductType"));

        listProductType = ProductType.ReadAll();
        dataObervableProductType.addAll(listProductType);
        tview_productType.setItems(dataObervableProductType);

        showProductTypeDetails(null);
        tview_productType.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showProductTypeDetails(newValue));


    }


    private void showProductTypeDetails(ProductType productType) {
        setProductTypeActiveButtons(true, false, "");

        if (productType != null) {

            btn_editProductType.setDisable(false);

            txt_nameProductType.setText(productType.getNameProductType());

            //imageview_product

            idProductTypeSelected = productType.getIdProductType();

            if(!ProductType.isUsing(idProductTypeSelected)){
                btn_dropProductType.setDisable(false);
                btn_dropProductType.setVisible(true);
            }else{
                btn_dropProductType.setDisable(true);
                btn_dropProductType.setVisible(false);
            }


        } else {
            txt_nameProductType.setText("");
            idProductTypeSelected = 0;
            btn_editProductType.setDisable(true);

        }
    }

    private void setProductTypeActiveButtons(Boolean bool_1, Boolean bool_2,String action){
        txt_nameProductType.setDisable(bool_1);
        group_PTBtnSaveCancel.setDisable(bool_1);
        group_PTBtnEditAdd.setDisable(bool_2);
        if(!action.equals("node"))
            actionPT = action;
    }
    private void handlerButtonActionDropProductType (MouseEvent event) {
        if(!ProductType.isUsing(idProductTypeSelected)){
            ProductType productType = new ProductType(idProductTypeSelected);
            productType.Delete();
            btn_dropProductType.setDisable(true);
            btn_dropProductType.setVisible(false);
            idProductTypeSelected = 0;
            DashboardController.refreshCategory = true;
        }
        resetTableViewProductType();
    }
    private void handlerButtonActionNewProductType (MouseEvent event) {
        setProductTypeActiveButtons(false,true,"Adicionar");
        txt_nameProductType.setText("");
        btn_dropProductType.setDisable(true);
        btn_dropProductType.setVisible(false);
        btn_editProductType.setDisable(true);

    }
    private void handlerButtonActionEditProductType (MouseEvent event) {
        setProductTypeActiveButtons(false,true,"Editar");
        btn_dropProductType.setDisable(true);
    }
    private void handlerButtonActionCancelProductType(MouseEvent event) {
        setProductTypeActiveButtons(true,false,"");
    }
    private void handlerButtonActionSaveProductType(MouseEvent event) {
        setProductTypeActiveButtons(true,false,"node");
        switch (actionPT ){
            case "Editar":
                saveProductType();
                break;
            case "Adicionar":
                newProductType();
                break;
            default:
                break;
        }
        actionPT = "";
    }

    private void resetTableViewProductType(){
        listProductType = ProductType.ReadAll();
        dataObervableProductType.clear();
        dataObervableProductType.addAll(listProductType);
    }

    private void saveProductType(){
        //Código banco aqui
        ProductType productType = new ProductType(idProductTypeSelected);
        productType.setNameProductType(txt_nameProductType.getText());
        productType.Save();
        resetTableViewProductType();
        DashboardController.refreshCategory = true;
    }

    private void newProductType(){
        //Código banco aqui
        ProductType productType = new ProductType();
        productType.setNameProductType(txt_nameProductType.getText());
        productType.Create();
        resetTableViewProductType();
        DashboardController.refreshCategory = true;
        txt_nameProductType.setText("");
    }

    public static Stage loader() throws IOException {
        Stage stage;
        stage = Controller.loader(DashboardController.class, StageStyle.DECORATED, path, title);
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        return stage;
    }
}
