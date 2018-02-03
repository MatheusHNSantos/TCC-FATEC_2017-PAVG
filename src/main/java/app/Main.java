/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import controller.dashboard.DashboardController;
import controller.login.LoginController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.entity.person.Person;
import model.entity.phone.Phone;
import model.entity.product.Ingredient;
import model.entity.product.Product;
import model.entity.product.ProductIngredient;
import model.entity.sale.Sale;
import util.dialogs.FxDialogs;

import java.awt.geom.Rectangle2D;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SAMSUNG
 */
public class Main extends Application {

    public void closeApplication() {
        Platform.exit();
    }

    @Override
    public void start(Stage PrimaryStage) {
        try {

           //LoginController.loader().show();
           /*DashboardController ds = new DashboardController();
           ds.setUser(user);
           ds.loader().show();*/
           DashboardController.loader().show();

            /*Sale f = new Sale(1);
            Class<?> c = f.getClass();
            Field[] field = c.getDeclaredFields();
            for(Field fi : field) {
                fi.setAccessible(true);
                System.out.println(fi.get(f));
            }*/

           /* List<Sale> teste = new ArrayList();
            teste = Sale.ReadAll();*/

           /*Person person = new Person(1);
            ArrayList<Phone> teste = new ArrayList();
            teste = person.getListPhone();
            System.out.println(teste.get(0).getPhone());*/

        }
        catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            ex.printStackTrace();
           // FxDialogs.showException("Erro de Leitura!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);

        }


    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(app.Main.class, args);
    }
}