/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dashboard;


import com.jfoenix.controls.JFXTabPane;
import controller.Controller;
import controller.login.LoginController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.entity.DataModelTest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;


/**
 * FXML Controller class
 * JtablePaneAdm
 * @author SAMSUNG
 */
public class DashboardController implements Initializable {

    private static final String path = "dashboard.fxml";
    private static final String title = "PAVG Apetitoso - v1.0.1117";

    @FXML
    private JFXTabPane paneTab;

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

    @FXML
    private JFXTabPane tablePaneAdm;

    @FXML
    private Label label_date;

    @FXML
    private Label label_user;

    @FXML
    private Label label_time;

    //private SimpleDateFormat formatador = new SimpleDateFormat("hh:mm:ss a");
    private SimpleDateFormat formatador = new SimpleDateFormat("hh:mm:ss");

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

        //Data
          Date dataSistema = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
       label_date.setText(formato.format(dataSistema));

        //Hora
       // LocalDateTime dt = LocalDateTime.now();
       // String dataAtual = dt.getDayOfMonth() + "/" + dt.getMonthValue() + "/" + dt.getYear();
     //   String horaAtual = dt.getHour() + ":" + dt.getMinute();
      //  label_date.setText(dataAtual);
    //    label_time.setText(horaAtual);
       //Timer timer = new Timer(1000, new hora());
      // timer.start();
       // Timer tm = new Timer();

        //tm.scheduleAtFixedRate(task,1000,1000);

        KeyFrame frame = new KeyFrame(Duration.millis(1000), e -> atualizaHoras2());
        Timeline timeline = new Timeline(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

    public static Stage loader() throws IOException {

        return Controller.loader(LoginController.class, StageStyle.DECORATED, path, title);

    }

    class hora implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Calendar now = Calendar.getInstance();
            label_time.setText(String.format("%1$tH:%1$tM:%1$tS", now));
            //lb_time.setText(String.format("%1$tH:%1$tM", now));
        }

    }


    TimerTask task = new TimerTask(){
        @Override
        public void run(){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Calendar now = Calendar.getInstance();
                    label_time.setText(String.format("%1$tH:%1$tM:%1$tS", now));
                }});
        }
    };

    private void atualizaHoras() {
        Date agora = new Date();
        label_time.setText(formatador.format(agora));
    }

    private void atualizaHoras2() {
        Calendar now = Calendar.getInstance();
        label_time.setText(String.format("%1$tH:%1$tM:%1$tS", now));
    }
}
