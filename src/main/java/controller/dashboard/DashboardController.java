/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dashboard;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.login.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.fxml.Loader;

/**
 * FXML BaseController class
 *
 * @author SAMSUNG
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * FXML Controller class
 *
 * @author SAMSUNG
 */
public class DashboardController implements Initializable {

    private static final String path = "fxml/dashboard.fxml";
    private static final String title = "Welcome!";

    @FXML
    private TabPane paneTab;

    @FXML
    private Tab welcomeTab;

    @FXML
    private Tab salesTab;

    @FXML
    private Tab usersTab;

    @FXML
    private TableView<DataModelTest> tableView;

    @FXML
    private TableColumn<DataModelTest, String> row1;

    @FXML
    private TableColumn<DataModelTest, String> row2;

    @FXML
    private TableColumn<DataModelTest, String> row3;


    //this ObservableList will be filled with database data, creating a method like:
    /**
     * public OberservaleList<? SpecificDataModel> getTableValues(){
     *
     *      //database con get data into ArrayList<? SpecificDataModel>
     *
     *      ArrayList<? SpecificDataModel> array;
     *      array.add(@param database data getting into object);
     *
     *      return new ObeservableList<? - SpecificDataModel> =
     *                              FXCollections.observaleArrayList(array);
     * }
     */

    private final ObservableList<DataModelTest> data
            = FXCollections.observableArrayList(
            new DataModelTest("1", "name1", "10"),
            new DataModelTest("2", "name2", "20"),
            new DataModelTest("3", "name3", "30")
    );



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /**
         * here goes the condition to indentify user type and close necessary
         * tabs on load to see the difference uncomment this lines below
         */

        //paneTab.getTabs().remove(salesTab);
        //paneTab.getTabs().remove(usersTab)


        //property named "id, nome, preco" indicates the row name location by model String value heap
        row1.setCellValueFactory(new PropertyValueFactory<>("id"));
        row2.setCellValueFactory(new PropertyValueFactory<>("nome"));
        row3.setCellValueFactory(new PropertyValueFactory<>("preco"));
        tableView.setItems(data);


    }


    public static Stage loader() throws IOException {
        return Loader.loader(DashboardController.class, StageStyle.DECORATED, "./dashboard.fxml", title);
    }

}



