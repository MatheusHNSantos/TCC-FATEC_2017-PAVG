/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dashboard;


import com.jfoenix.controls.*;
import controller.Controller;
import controller.login.LoginController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;
import model.entity.DataModelTest;
import model.entity.address.Address;
import model.entity.person.Person;
import model.entity.person.costumer.Costumer;
import model.entity.person.employee.Employee;
import model.entity.person.supplier.Supplier;
import model.entity.person.user.User;
import model.entity.phone.Phone;
import model.entity.product.Ingredient;
import model.entity.product.Product;
import model.entity.product.ProductIngredient;
import model.entity.product.ProductType;
import model.entity.sale.Sale;
import util.dialogs.FxDialogs;
import util.exception.UserException;
import util.validation.ValidationFields;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.String.valueOf;


/**
 * FXML Controller class
 * JtablePaneAdm
 * @author Matheus Henrique
 */
public class DashboardController implements Initializable, FinishSaleController.OnClickFinishButton {

    @Override
    public void finishButton(int decision) {

        //do something here in this case im moving the tab
        SingleSelectionModel<Tab> selectionModel = paneTab.getSelectionModel();
        selectionModel.select(0);
    }

    private static final String path = "dashboard.fxml";
    private static final String title = "PAVG Apetitoso - v1.0.1117";

    private static User user;

    //region general objects
    @FXML    private JFXTabPane paneTab;
    @FXML    private Tab welcomeTab;
    @FXML    private Tab salesTab;
    @FXML    private Tab usersTab;
    @FXML    private JFXTabPane tablePaneAdm;

    @FXML    private Label label_date;
    @FXML    private Label label_user;
    @FXML    private Label label_time;

    @FXML    private Tab tabCustomer;
    @FXML    private Tab tabOrder;
    //endregion

    //region Tab "Inicio" Objects
    /**
     * tab "Inicio" Objects
     */
    @FXML public Hyperlink hl_finish;
    @FXML public Hyperlink hl_logout;
    //endregion

    //region Tab "Pedido" Objects
    /** tab "Pedido" Objects */

    //region @FXML Objects
    @FXML    private JFXButton btn_finishSale;
    @FXML    private Hyperlink btn_historicCostumer;

    @FXML    private Hyperlink hyperlinkVerifyRegister;

    @FXML    private JFXButton btn_newOrder;
    @FXML    private JFXButton btn_clearOrderProducts;
    @FXML    private JFXButton btn_removeProductOnOrder;
    @FXML    private JFXButton btn_addProductOnOrder;
    @FXML    private JFXToggleButton toggleButton_gerarCupomFiscal;
    @FXML    private Label lbl_codeOfOrder;
    @FXML    private TextField tfield_name;
    @FXML    private TextField tfield_telephone;
    @FXML    private TextField tfield_adress;
    @FXML    private ImageView img_selectionedProduct;
    @FXML    private TextArea tarea_orderObservations;
    @FXML    private TableView tview_products;
    @FXML    private Label lbl_total;
    @FXML    public  Label lbl_score;
    @FXML    private JFXListView listView_categories;
    @FXML    private JFXListView listView_products;
    @FXML    private Hyperlink hl_historicCostumer;
    @FXML    private TableView<Product> tViewOrderProducts;
    @FXML    private TableColumn<Product, String> columnOrderProductName;
    @FXML    private TableColumn<Product, String> columnOrderProductPrice;
    //endregion

    //region Normal objects
    private float totalPrice = 0;
    public ObservableList<Product> dataObervableProducts;

    public ArrayList<ProductType> productTypeListHandler;
    public ObservableList<String> dataObservableProductType;

    public ObservableList<String> dataObservableProductsFinded;
    public ArrayList<Product> productsFinded;

    private Person person;
    //endregion

    //endregion

    //region Tab "Clientes" Objects
    /**
     * tab "Clientes" Objects
     */
    //region @FXML Objects
    @FXML    private TableView<Costumer> tview_costumer; //Tabela de cliente
    @FXML    private JFXComboBox<?> cbox_typeSearchCostumer; //Combo Box de tipo de Pesquisa de cliente
    @FXML    private JFXTextField txt_searchCostumer; //TextField de campo para pesquisa de cliente
    @FXML    private JFXButton btn_searchCostumer; //Botão Pesquisar
    //---
    @FXML    private JFXToggleButton tbtn_statusCostumer; //Seletor de Status do cliente
    @FXML    private Group group_dataCostumer; //Agrupamento de TextField atributos cliente
    @FXML    private Group group_dataCostumerBtnSaveCancel; //Salvar e Cancelar
    @FXML    private Group group_costumerBtnEditNew; //Editar e Adicionar
    @FXML    private Label lbl_pointsCostumer; //TextField de campo para nome cliente
    @FXML    private JFXTextField txt_nameCostumer; //TextField de campo para nome cliente
    @FXML    private JFXTextField txt_rgCostumer; //TextField de campo para RG cliente
    @FXML    private JFXTextField txt_cpfCostumer; //TextField de campo para CPF cliente
    @FXML    private JFXTextField txt_cepCostumer; //TextField de campo para CEP cliente
    @FXML    private JFXTextField txt_bairroCostumer; //TextField de campo para bairro cliente
    @FXML    private JFXTextField txt_streetCostumer; //TextField de campo para rua cliente
    @FXML    private JFXTextField txt_numberCostumer; //TextField de campo para numero cliente
    @FXML    private JFXTextField txt_phone1Costumer; //TextField de campo para telefone1 cliente
    @FXML    private JFXTextField txt_phone2Costumer; //TextField de campo para telefone2 cliente
    @FXML    private JFXButton btn_saveCostumer; //Botão Salvar
    @FXML    private JFXButton btn_cancelCostumer; //Botão Cancelar
    @FXML    private JFXButton btn_newCostumer; //Botão Criar
    @FXML    private JFXButton btn_editCostumer; //Botão Editar
    @FXML    private TableColumn<Costumer, String> columnCostumerName;
    @FXML    private TableColumn<Costumer, Costumer> columnCostumerPhone1;
    @FXML    private TableColumn<Costumer, Costumer> columnCostumerPhone2;
    @FXML    private TableColumn<Costumer, Costumer> columnCostumerCep;
    @FXML    private TableColumn<Costumer, String> columnCostumerCpf;

    //endregion

    //region Normal objects
    private String actionCostumer = "";
    private boolean comeBack = false;

    public ObservableList<Costumer> dataObervableCostumer;
    public ArrayList<Costumer> listCostumer;


    private int idCostumerSelected = 0;
    //endregion

    //endregion

    //region Tab "Administração" Objects
    /**
     * tab "Administração" Objects
     */

    //region Tab "Faturamento" Objects
    /**
     * tab "Faturamento" Objects
     */
    @FXML    private JFXComboBox<?> cbox_scopeBarChart; //Combo Box de intervalo
    @FXML    private JFXComboBox<?> cbox_scopePieChart; //Combo Box de intervalo
    @FXML    private PieChart pc_pieChart;
    @FXML    private BarChart bar_barChart;
    //endregion

    //region Tab "Vendas" Object
    /** tab "Vendas" Objects  */

    //region @FXML Objects
    @FXML    private JFXButton btn_showSaleDetail; //Detalhes da venda
    @FXML    private JFXButton btn_searchSale; //Botão Pesquisar
    @FXML    private JFXButton btn_filterSale; //Botão Filtrar Venda
    @FXML    private JFXDatePicker datePicker_sale; //Date Picker Venda
    @FXML    private JFXComboBox<?> cbox_typeSearchSale; //Combo Box de tipo de Pesquisa de Vendas
    @FXML    private JFXTextField txt_searchSale; //TextField de campo para pesquisa de Vendas
    @FXML    private TableView<Sale> tViewSale;
    @FXML    private TableColumn<Sale, String> columnSaleID;
    @FXML    private TableColumn<Sale, String> columnSaleCostumer;
    @FXML    private TableColumn<Sale, String> columnSalePrice;
    //endregion

    //region Normal Objects
    public ArrayList<Sale> dataModelSale;
    public ObservableList<Sale> dataObervableSale;
    //endregion

    //endregion

    //region Tab "Fornecedores" Objects
    /**
     * tab "Fornecedores" Objects
     */

    //region @FXML Objects
    @FXML    private TableView<Supplier> tview_supplier; //Tabela de fornecedores
    @FXML    private JFXComboBox<?> cbox_typeSearchSupplier; //Combo Box de tipo de Pesquisa de fornecedores
    @FXML    private JFXTextField txt_searchSupplier; //TextField de campo para pesquisa de fornecedores
    @FXML    private JFXButton btn_searchSupplier; //Botão Pesquisar
    //---
    @FXML    private JFXToggleButton tbtn_statusSupplier; //Seletor de Status do fornecedores
    @FXML    private Group group_dataSupplier; //Agrupamento de TextField atributos fornecedores
    @FXML    private Group group_supplierBtnSaveCancel; //Salvar e Cancelar
    @FXML    private Group group_supplierBtnEditNew; //Editar e Adicionar
    @FXML    private JFXTextField txt_nameSupplier; //TextField de campo para nome fornecedores
    @FXML    private JFXTextField txt_cnpjSupplier; //TextField de campo para CNPJ fornecedores
    @FXML    private JFXTextField txt_cepSupplier; //TextField de campo para CEP fornecedores
    @FXML    private JFXTextField txt_bairroSupplier; //TextField de campo para bairro fornecedores
    @FXML    private JFXTextField txt_streetSupplier; //TextField de campo para rua fornecedores
    @FXML    private JFXTextField txt_numberSupplier; //TextField de campo para numero fornecedores
    @FXML    private JFXTextField txt_phone1Supplier; //TextField de campo para telefone1 fornecedores
    @FXML    private JFXTextField txt_phone2Supplier; //TextField de campo para telefone2 fornecedores
    @FXML    private JFXButton btn_saveSupplier; //Botão Salvar
    @FXML    private JFXButton btn_cancelSupplier; //Botão Cancelar
    @FXML    private JFXButton btn_newSupplier; //Botão Criar
    @FXML    private JFXButton btn_editSupplier; //Botão Editar

    @FXML    private TableColumn<Supplier, String> columnSupplierName;
    @FXML    private TableColumn<Supplier, Supplier> columnSupplierPhone1;
    @FXML    private TableColumn<Supplier, Supplier> columnSupplierPhone2;
    @FXML    private TableColumn<Supplier, Supplier> columnSupplierCep;
    @FXML    private TableColumn<Supplier, String> columnSupplierCnpj;
    //endregion

    //region Normal objects
    private String actionSupplier = "";

    public ObservableList<Supplier> dataObervableSupplier  = FXCollections.observableArrayList();
    public ArrayList<Supplier> listSupplier = new ArrayList<>();


    private int idSupplierSelected = 0;

    //endregion

    //endregion

    //region Tab "Funcionários/Usuários" Objects
    /**
     * tab "Funcionários/Usuários" Objects
     *
     *
     * btn_manageUsers -> "Gerenciar Usuários (Login)"
     */

    //region @FXML Objects
    @FXML    private Hyperlink btn_manageUsers;
    @FXML    private Hyperlink btn_editEmployee; //Editar Ingredientes
    @FXML    private JFXToggleButton select_loginStatus;
    @FXML    private JFXToggleButton select_typeStatusFunc; //select status func
    @FXML    private JFXComboBox<?> cbox_typeSearchEmployee; //Combo Box de tipo de Pesquisa de Funcionário
    @FXML    private Group group_dataFunc; //Agrupamento de TextField atributos do funcionario
    @FXML    private Group group_dataFunc_btn; //Salvar e cancelar
    @FXML    private Group group_editAddFunc; //Agrupamento Editar de Cadastrar funcionario
    @FXML    private JFXButton btn_searchEmployee; //Botão Pesquisar
    @FXML    private JFXButton btn_saveEmployee; //Botão Salvar
    @FXML    private JFXButton btn_cancelEmployee; //Botão Cancelar
    @FXML    private JFXButton btn_addEmployee; //Botão Cadastrar
    @FXML    private JFXTextField txt_searchEmployee; //TextField de campo para pesquisa
    @FXML    private JFXTextField txt_nameEmployee; //TextField de campo para nome funcionario
    @FXML    private JFXTextField txt_roleEmployee; //TextField de campo para função funcionario
    @FXML    private JFXTextField txt_cepEmployee; //TextField de campo para CEP funcionario
    @FXML    private JFXTextField txt_bairroEmployee; //TextField de campo para bairro funcionario
    @FXML    private JFXTextField txt_streetEmployee; //TextField de campo para rua funcionario
    @FXML    private JFXTextField txt_numberEmployee; //TextField de campo para numero funcionario
    @FXML    private JFXTextField txt_phone1Employee; //TextField de campo para telefone1 funcionario
    @FXML    private JFXTextField txt_phone2Employee; //TextField de campo para telefone2 funcionario
    @FXML    private TableView<Employee> tview_func; //Tabela de funcionarios
    @FXML    private TableColumn<Employee, String> columnEmployeeName;
    //@FXML    private TableColumn<Employee, String> columnEmployeePhone1;
    //@FXML    private TableColumn<Employee, String> columnEmployeePhone2;
    @FXML    private TableColumn<Employee, Employee> columnEmployeePhone1;
    @FXML    private TableColumn<Employee, Employee> columnEmployeePhone2;
    @FXML    private TableColumn<Employee, String> columnEmployeeRole;
    @FXML    private TableColumn<Employee, String> columnEmployeeStatus;
    //login
    @FXML    private TitledPane tp_login;
    @FXML    private Group group_dataUser; //Agrupamento de TextField atributos user
    @FXML    private Group group_dataUserBtnEditarCriar; //Editar e Criar
    @FXML    private JFXTextField txt_userLogin; //TextField de campo para usuario login
    @FXML    private JFXPasswordField txt_passLogin; //TextField de campo para senha login
    @FXML    private JFXComboBox<String> cbox_typeLevelLogin; //Combo Box de tipo de Nivel de usuario
    @FXML    private JFXButton btn_saveLogin; //Botão Salvar
    @FXML    private JFXButton btn_cancelLogin; //Botão Cancelar
    @FXML    private JFXButton btn_criarLogin; //Botão Criar
    @FXML    private JFXButton btn_editarLogin; //Botão Editar
    //endregion

    //region Normal objects
    private String actionEmployee = "";
    private String actionLogin = "";
    public ObservableList<Employee> dataObervableEmployee;
    public ArrayList<Employee> listEmployee;
    private int idEmployeeSelected = 0;
    private int idPersonEmployeeSelected = 0;

    public ObservableList<String> options;
    //endregion

    //endregion

    //region Tab "Produtos" Objects
    /** tab "Produtos" Objects**/

    //region @FXML Objects
    @FXML    private Hyperlink btn_editProductType; //Editar Categoria
    @FXML    private Hyperlink btn_editIngredients; //Editar Ingredientes
    @FXML    private Hyperlink btn_alterImageProduct; //Alterar Imagem do Produto
    @FXML    private Hyperlink btn_removeImageProduct; //Remover Imagem do Produto
    @FXML    private JFXButton btn_listIngredients; //Adicionar Ingredientes ao Produto
    @FXML    private JFXButton btn_dropIngredients; //Remover Ingredientes do Produto
    @FXML    private JFXButton btn_saveProduct; //Botão Salvar
    @FXML    private JFXButton btn_cancelProduct; //Botão Cancelar
    @FXML    private JFXButton btn_editProduct; //Botão Editar
    @FXML    private JFXButton btn_addProduct; //Botão Adicionar
    @FXML    private JFXButton btn_searchProduct; //Botão Pesquisar
    @FXML    private JFXToggleButton tbtn_statusProduct; //Seletor de Status do Produto
    @FXML    private JFXComboBox<String> cbox_categoryProduct; //Combo Box de escolha de Categoria
    @FXML    private JFXComboBox<String> cbox_typeSearchProduct; //Combo Box de tipo de Pesquisa de Produto
    @FXML    private JFXTextField txt_searchProduct; //TextField de campo para pesquisa
    @FXML    private JFXTextField txt_nameProduct; //TextField de Nome do Produto
    @FXML    private JFXTextField txt_finalPriceProduct; //TextField Preço final do produto
    @FXML    private JFXTextField txt_weightProduct; //TextField peso do produto
    @FXML    private ImageView imageview_product; //Imagem do produto
    @FXML    private TableView<Product> tview_product; //Tabela de produtos
    @FXML    private TableColumn<Product, String> columnProductName;
    @FXML    private TableColumn<Product, Product> columnProductFinalPrice;
    @FXML    private TableColumn<Product, String> columnProductWeight;
    @FXML    private TableColumn<Product, Product> columnProductType;
    @FXML    private TableColumn<Product, Product> columnProductStatus;
    @FXML    private TableView<Ingredient> tview_productIngredient; //Tabela de Ingredientes do produto
    @FXML    private TableColumn<Ingredient, String> columnIngredientName;
    @FXML    private TableColumn<Ingredient, Ingredient> columnIngredientPrice;
    @FXML    private TableColumn<Ingredient, Ingredient> columnIngredientStatus;
    @FXML    private Group group_dataProduct; //Agrupamento de TextField atributos do produto
    @FXML    private Group group_cancelSaveProduct; //Agrupamento botão Cancelar e Salvar
    @FXML    private Group group_addDropIngredients; //Agrupamento botão de Adição e Remoção de ingredientes no produto
    @FXML    private Group group_editAddProduct; //Agrupamento Editar de Adicionar produto
    //endregion

    //region Normal objects
    private String actionProduct = "";

    private ObservableList<Product> dataObervableProduct = FXCollections.observableArrayList();
    private ArrayList<Product> listProduct = new ArrayList<>();

    private static ObservableList<Ingredient> dataObervableIngredient = FXCollections.observableArrayList();
    private ArrayList<Ingredient> listIngredient = new ArrayList<>();

    private int idProductSelected = 0;
    private int idIngredientSelected = 0;
    public static boolean refreshCategory = false;
    //endregion


    //endregion

    //region Tab "Log" Objects
    /**
     * tab "Log" Objects
     */
    @FXML    private TableView<?> tview_log; //Tabela de logs
    @FXML    private JFXButton btn_filterLog; //Botão Filtrar Log
    @FXML    private JFXDatePicker datePicker_log; //Date Picker Log
    //endregion
    //endregion


    //region SimpleDataFormat
    /**
     * SimpleDateFormat -> captura data
     */
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

    //lixo

    //just a teste need delete after
    //@FXML    private TableView<DataModelTest> tableView;
    //@FXML    private TableColumn<DataModelTest, String> row1;
    //@FXML    private TableColumn<DataModelTest, String> row2;
    //@FXML    private TableColumn<DataModelTest, String> row3;


    //just a teste
    /* private final ObservableList<DataModelTest> data
            = FXCollections.observableArrayList(
                    new DataModelTest("1", "name1", "10"),
                    new DataModelTest("2", "name2", "20"),
                    new DataModelTest("3", "name3", "30")
            );


    public ArrayList<Sale> dataModelSale;
    public ObservableList<Sale> dataObervableSale;*/

    //endregion


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /**
         * here goes the condition to indentify user type and close necessary
         * tabs on load to see the difference uncomment this lines below
         */


        //region Setup Inicial
        tp_login.setVisible(false); //complementos
        //paneTab.getTabs().remove(salesTab);
        //paneTab.getTabs().remove(usersTab);

        //Data
        Date dataSistema = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        label_date.setText(formato.format(dataSistema));


        KeyFrame frame = new KeyFrame(Duration.millis(1000), e -> atualizaHoras2());
        Timeline timeline = new Timeline(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        //Hora //future examples
        // LocalDateTime dt = LocalDateTime.now();
        //String dataAtual = dt.getDayOfMonth() + "/" + dt.getMonthValue() + "/" + dt.getYear();
        //String horaAtual = dt.getHour() + ":" + dt.getMinute();
        //label_date.setText(dataAtual);
        //label_time.setText(horaAtual);
        //Timer timer = new Timer(1000, new hora());
        //timer.start();
        //Timer tm = new Timer();

        //tm.scheduleAtFixedRate(task,1000,1000);


        //dataModelTests = new ArrayList<>();

        //label_user.setText(user.getLogin()); //ativar para usar o a tela de login

        //endregion

        //region Tab "Incio" Events
        /**
         * tab "Inicio" ButtonAction
         */
        hl_logout.setOnMouseClicked(this::handlerHyperlinkActionLogout);
        hl_finish.setOnMouseClicked(this::handlerHyperlinkActionFinish);
        //endregion

        //region Tab "Pedido"
        /**
         * tab "Pedido" ButtonAction
         */

        //region Tab "Pedido" Variables

        //region OLD WAY
        /*
        public ArrayList<Product> dataModelSProducts;

        dataModelSProducts = new ArrayList<>();
        dataModelSProducts.add(new Product("Rand test",50.3f));
        dataObervableProducts = FXCollections.observableArrayList(dataModelSProducts);

        //ESSE NAO VAI SER ADCICIONADO PORQUE ENTRA DEPOIS NA REFERENCIA
        dataModelSProducts.add(new Product("another one",55.3f));

        //TERIA QUE REDECLARAR O LOCAL DE MEMORIA DE NOVO
        dataObervableProducts = FXCollections.observableArrayList(dataModelSProducts);
        */

        //SAO DUAS FORMAS DE TRABALHAR UMA OBSERVAVEL SENDO UMA RECYCLER E UMA NAO OBSERVAVEL
        //endregion

        /*dataObervableProducts = FXCollections.observableArrayList();
        dataObervableProducts.add(new Product("is that another one", 10f));

        columnOrderProductName.setCellValueFactory(new PropertyValueFactory<>("nameProduct"));
        columnOrderProductPrice.setCellValueFactory(new PropertyValueFactory<>("finalPriceProduct"));
        tViewOrderProducts.setItems(dataObervableProducts);*/

        //endregion

        hl_historicCostumer.setDisable(true);

        //region Text Fields
        tfield_telephone.setOnKeyPressed(this::handlerKeyPressedActionEnterTelephone);
        //endregion

        //region TableView Products on Order
        dataObervableProducts = FXCollections.observableArrayList();
        columnOrderProductName.setCellValueFactory(new PropertyValueFactory<>("nameProduct"));
        columnOrderProductPrice.setCellValueFactory(new PropertyValueFactory<>("finalPriceProduct"));
        tViewOrderProducts.setItems(dataObervableProducts);
        //endregion



        //region ListView Categories
        productTypeListHandler = ProductType.ReadAll();
        dataObservableProductType = FXCollections.observableArrayList(Arrays.asList());
        productTypeListHandler.forEach(productType -> dataObservableProductType.add(productType.getNameProductType()));
        listView_categories.setItems(dataObservableProductType);
        //endregion

        //region ListView PorductsFinded
        dataObservableProductsFinded = FXCollections.observableArrayList(Arrays.asList());
        listView_products.setItems(dataObservableProductsFinded);
        //endregion


        //region Tab "Pedido" - Events
        btn_finishSale.setOnMouseClicked(this::handlerButtonActionFinishOrder);
        btn_newOrder.setOnMouseClicked(this::handlerButtonActionNewOrder);
        btn_clearOrderProducts.setOnMouseClicked(this::handlerButtonActionClearOrderProducts);
        btn_addProductOnOrder.setOnMouseClicked(this::handlerButtonActionAddProductOnOrder);
        btn_removeProductOnOrder.setOnMouseClicked(this::handlerButtonActionRemoveProductOnOrder);
        hl_historicCostumer.setOnMouseClicked(this::handlerButtonActionHistoricCostumer);
        hyperlinkVerifyRegister.setOnMouseClicked(this::handlerHyperlinkVerifyRegister);

        listView_categories.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dataObservableProductsFinded.clear();
                ProductType productType = productTypeListHandler.get(listView_categories.getSelectionModel().getSelectedIndex());
                productsFinded = Product.readByCategory(productType.getIdProductType());
                productsFinded.forEach(product -> dataObservableProductsFinded.add(product.getNameProduct()));
            }
        });
        //endregion
        //endregion

        //region Tab "Clientes" Events
        /**
         * tab "Clientes" ButtonAction
         */
        btn_editCostumer.setDisable(true);

        //region TableView
        dataObervableCostumer = FXCollections.observableArrayList();

        //region columns declarations

        //column name
        columnCostumerName.setCellValueFactory(new PropertyValueFactory<>("namePerson"));

        //column phone1
        columnCostumerPhone1.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue()));
        columnCostumerPhone1.setCellFactory(column -> new TableCell<Costumer, Costumer>() {

            private VBox graphic ;
            private Label labelPhone1 ;

            // Anonymous constructor:
            {
                graphic = new VBox();
                labelPhone1 = createLabel();
                graphic.getChildren().addAll(labelPhone1);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }

            @Override
            public void updateItem(Costumer costumer, boolean empty) {
                if (costumer == null) {
                    setGraphic(null);
                } else {
                    ArrayList<Phone> phoneList;
                    phoneList = costumer.getListPhone();
                    String phone  = "";
                    if(phoneList.size() > 0)
                        phone  = phoneList.get(0).getPhone();

                    labelPhone1.setText(phone);

                    setGraphic(graphic);
                }
            }
        });

        //column phone2
        columnCostumerPhone2.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue()));
        columnCostumerPhone2.setCellFactory(column -> new TableCell<Costumer, Costumer>() {

            private VBox graphic ;
            private Label labelPhone2 ;

            // Anonymous constructor:
            {
                graphic = new VBox();
                labelPhone2 = createLabel();
                graphic.getChildren().addAll(labelPhone2);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }

            @Override
            public void updateItem(Costumer costumer, boolean empty) {
                if (costumer == null) {
                    setGraphic(null);
                } else {
                    ArrayList<Phone> listPhone;
                    listPhone = costumer.getListPhone();
                    if(listPhone.size()==2) {
                        String phone = listPhone.get(1).getPhone();
                        labelPhone2.setText(phone);

                    }else{
                        labelPhone2.setText("");
                    }
                    setGraphic(graphic);
                }
            }
        });

        //column cep
        columnCostumerCep.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue()));
        columnCostumerCep.setCellFactory(column -> new TableCell<Costumer, Costumer>() {

            private VBox graphic ;
            private Label labelCep ;

            // Anonymous constructor:
            {
                graphic = new VBox();
                labelCep = createLabel();
                graphic.getChildren().addAll(labelCep);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }

            @Override
            public void updateItem(Costumer costumer, boolean empty) {
                if (costumer == null) {
                    setGraphic(null);
                } else {
                    labelCep.setText(costumer.getAddress().getCep());
                    setGraphic(graphic);
                }
            }
        });


        //column cnpj
        //columnCostumerCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        //endregion



        listCostumer = Costumer.ReadAll();
        dataObervableCostumer.addAll(listCostumer);
        tview_costumer.setItems(dataObervableCostumer);
        //endregion

        //region Supplier Details

        showCostumerDetails(null);

        tview_costumer.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showCostumerDetails(newValue));

        //tview_costumer.getSelectionModel().getSelectedItem().getIdPerson();

        //endregion

        //region Events
        btn_editCostumer.setOnMouseClicked(this::handlerButtonActionEditCostumer);
        btn_newCostumer.setOnMouseClicked(this::handlerButtonActionNewCostumer);
        btn_cancelCostumer.setOnMouseClicked(this::handlerButtonActionCancelCostumer);
        btn_saveCostumer.setOnMouseClicked(this::handlerButtonActionSaveCostumer);
        tbtn_statusCostumer.setOnMouseClicked(this::handlerButtonActionStatusCostumer);
        btn_searchCostumer.setOnMouseClicked(this::handlerButtonActionSearchCostumer);
        //endregion

        //endregion

        //region Tab "Administração" Events
        /**
         * tab "Administração" ButtonAction
         */

        //region Tab "Faturamento" Events
        /**
         * tab "Faturamento" ButtonAction
         */
        //endregion

        //region Tab "Venda"  Events
        /**
         * tab "Vendas" ButtonAction
         */
        btn_showSaleDetail.setOnMouseClicked(this::handlerButtonActionSaleDetail);
        //endregion

        //region Tab "Fornecedores" Events
        /**
         * tab "Fornecedores" ButtonAction
         */

        btn_editSupplier.setDisable(true);

        //region TableView
        dataObervableSupplier = FXCollections.observableArrayList();

        //region columns declarations

        //column name
        columnSupplierName.setCellValueFactory(new PropertyValueFactory<>("namePerson"));

        //column phone1
        columnSupplierPhone1.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue()));
        columnSupplierPhone1.setCellFactory(column -> new TableCell<Supplier, Supplier>() {

            private VBox graphic ;
            private Label labelPhone1 ;

            // Anonymous constructor:
            {
                graphic = new VBox();
                labelPhone1 = createLabel();
                graphic.getChildren().addAll(labelPhone1);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }

            @Override
            public void updateItem(Supplier supplier, boolean empty) {
                if (supplier == null) {
                    setGraphic(null);
                } else {
                    ArrayList<Phone> phoneList;
                    phoneList = supplier.getListPhone();
                    String phone  = phoneList.get(0).getPhone();

                    labelPhone1.setText(phone);

                    setGraphic(graphic);
                }
            }
        });

        //column phone2
        columnSupplierPhone2.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue()));
        columnSupplierPhone2.setCellFactory(column -> new TableCell<Supplier, Supplier>() {

            private VBox graphic ;
            private Label labelPhone2 ;

            // Anonymous constructor:
            {
                graphic = new VBox();
                labelPhone2 = createLabel();
                graphic.getChildren().addAll(labelPhone2);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }

            @Override
            public void updateItem(Supplier supplier, boolean empty) {
                if (supplier == null) {
                    setGraphic(null);
                } else {
                    ArrayList<Phone> listPhone;
                    listPhone = supplier.getListPhone();
                    if(listPhone.size()==2) {
                        String phone = listPhone.get(1).getPhone();
                        labelPhone2.setText(phone);

                    }else{
                        labelPhone2.setText("");
                    }
                    setGraphic(graphic);
                }
            }
        });

        //column cep
        columnSupplierCep.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue()));
        columnSupplierCep.setCellFactory(column -> new TableCell<Supplier, Supplier>() {

            private VBox graphic ;
            private Label labelCep ;

            // Anonymous constructor:
            {
                graphic = new VBox();
                labelCep = createLabel();
                graphic.getChildren().addAll(labelCep);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }

            @Override
            public void updateItem(Supplier supplier, boolean empty) {
                if (supplier == null) {
                    setGraphic(null);
                } else {
                    labelCep.setText(supplier.getAddress().getCep());
                    setGraphic(graphic);
                }
            }
        });


        //column cnpj
        columnSupplierCnpj.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
        //endregion



        listSupplier = Supplier.ReadAll();
        dataObervableSupplier.addAll(listSupplier);
        tview_supplier.setItems(dataObervableSupplier);
        //endregion

        //region Supplier Details

        showSupplierDetails(null);

        tview_supplier.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showSupplierDetails(newValue));
        //endregion


        //region Events
        btn_editSupplier.setOnMouseClicked(this::handlerButtonActionEditSupplier);
        btn_newSupplier.setOnMouseClicked(this::handlerButtonActionNewSupplier);
        btn_cancelSupplier.setOnMouseClicked(this::handlerButtonActionCancelSupplier);
        btn_saveSupplier.setOnMouseClicked(this::handlerButtonActionSaveSupplier);
        tbtn_statusSupplier.setOnMouseClicked(this::handlerButtonActionStatusSupplier);
        btn_searchSupplier.setOnMouseClicked(this::handlerButtonActionSearchSupplier);
        //endregion

        //endregion

        //region Tab "Funcionários/Usuários" Events
        /**
         * tab "Funcionários/Usuários" ButtonAction
         */

        btn_editEmployee.setDisable(true);

        //region TableView Employee
        dataObervableEmployee = FXCollections.observableArrayList();

        columnEmployeeName.setCellValueFactory(new PropertyValueFactory<>("namePerson"));
        /*columnEmployeePhone1.setCellFactory((Callback<TableColumn.CellDataFeatures<Employee, String>, ObservableValue<String>>) param -> {
            Employee person = param.getValue();
            ArrayList<Phone> teste = new ArrayList();
            teste = person.getListPhone();
            SimpleStringProperty booleanProp = new SimpleStringProperty(teste.get(0).getPhone());


            return booleanProp;
        });*/
        columnEmployeePhone1.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue()));
        columnEmployeePhone1.setCellFactory(column -> new TableCell<Employee, Employee>() {

            private VBox graphic ;
            private Label firstNameLabel ;
            private Label lastNameLabel ;
            private Label phoneLabel ;

            // Anonymous constructor:
            {
                graphic = new VBox();
                //firstNameLabel = createLabel("#66BB66");
                //lastNameLabel = createLabel("#79A8D8");
                //phoneLabel = createLabel("#FF8888");
                phoneLabel = createLabel();
                /*graphic.getChildren().addAll(firstNameLabel,
                        lastNameLabel, emailLabel);*/
                graphic.getChildren().addAll(phoneLabel);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }

            private final Label createLabel() {
                Label label = new Label();
                VBox.setVgrow(label, Priority.ALWAYS);
                label.setMaxWidth(Double.MAX_VALUE);
                //label.setStyle("-fx-background-color: "+color+" ;");
                label.setAlignment(Pos.CENTER);
                return label ;
            }

            @Override
            public void updateItem(Employee person, boolean empty) {
                if (person == null) {
                    setGraphic(null);
                } else {
                    ArrayList<Phone> teste;
                    teste = person.getListPhone();
                    String phone = "";
                    if(teste.size() > 0)
                        phone = teste.get(0).getPhone();

                    //firstNameLabel.setText(person.getNamePerson());
                    //lastNameLabel.setText(person.getRole());
                    phoneLabel.setText(phone);
                    setGraphic(graphic);
                }
            }
        });

        columnEmployeePhone2.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue()));
        columnEmployeePhone2.setCellFactory(column -> new TableCell<Employee, Employee>() {

            private VBox graphic ;
            private Label phoneLabel2 ;

            // Anonymous constructor:
            {
                graphic = new VBox();
                phoneLabel2 = createLabel();
                graphic.getChildren().addAll(phoneLabel2);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }

            private final Label createLabel() {
                Label label = new Label();
                VBox.setVgrow(label, Priority.ALWAYS);
                label.setMaxWidth(Double.MAX_VALUE);
                label.setAlignment(Pos.CENTER);
                return label ;
            }

            @Override
            public void updateItem(Employee person, boolean empty) {
                if (person == null) {
                    setGraphic(null);
                } else {
                    ArrayList<Phone> listPhone;
                    listPhone = person.getListPhone();
                    if(listPhone.size()==2) {
                        String phone = listPhone.get(1).getPhone();
                        phoneLabel2.setText(phone);

                    }else{
                        phoneLabel2.setText("");
                    }
                    setGraphic(graphic);
                }
            }
        });

        columnEmployeeRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        listEmployee = Employee.ReadAll();
        dataObervableEmployee.addAll(listEmployee);
        tview_func.setItems(dataObervableEmployee);
        //endregion


        cbox_typeLevelLogin.getItems().addAll(
                "",
                "Operador",
                "Administrador"
        );

        // Limpa os detalhes da pessoa.
        showEmployeeDetails(null);

        tview_func.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showEmployeeDetails(newValue));


        //region Events
        btn_manageUsers.setOnMouseClicked(this::handlerButtonActionManageUsers);
        select_loginStatus.setOnMouseClicked(this::handlerButtonSelectLoginStatus);
        btn_addEmployee.setOnMouseClicked(this::handlerButtonActionAddEmployee);
        btn_editEmployee.setOnMouseClicked(this::handlerButtonActionEditEmployee);
        btn_saveEmployee.setOnMouseClicked(this::handlerButtonActionSaveEmployee);
        btn_cancelEmployee.setOnMouseClicked(this::handlerButtonActionCancelEmployee);
        select_typeStatusFunc.setOnMouseClicked(this::handlerButtonActionStatusEmployee);

        //Login Events
        btn_saveLogin.setOnMouseClicked(this::handlerButtonActionSaveLogin);
        btn_editarLogin.setOnMouseClicked(this::handlerButtonActionEditLogin);
        btn_criarLogin.setOnMouseClicked(this::handlerButtonActionAddLogin);
        btn_cancelLogin.setOnMouseClicked(this::handlerButtonActionCancelLogin);

        //endregion
        //endregion

        //region Tab "Produtos" Events
        /**
         * tab "Produtos" ButtonAction
         */
        btn_editProduct.setDisable(true);
        //region TableView Products

        //region columns declarations
        columnProductName.setCellValueFactory(new PropertyValueFactory<>("nameProduct"));
        columnProductFinalPrice.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue()));
        columnProductFinalPrice.setCellFactory(column -> new TableCell<Product, Product>() {

            private VBox graphic ;
            private Label labelFinalPrice ;

            // Anonymous constructor:
            {
                graphic = new VBox();
                labelFinalPrice = createLabel();
                graphic.getChildren().addAll(labelFinalPrice);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }

            @Override
            public void updateItem(Product product, boolean empty) {
                if (product == null) {
                    setGraphic(null);
                } else {

                    labelFinalPrice.setText("R$ " + product.getFinalPriceProduct());
                    setGraphic(graphic);
                }
            }
        });

        columnProductWeight.setCellValueFactory(new PropertyValueFactory<>("weightProduct"));
        columnProductType.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue()));
        columnProductType.setCellFactory(column -> new TableCell<Product, Product>() {

            private VBox graphic ;
            private Label labelProductType ;

            // Anonymous constructor:
            {
                graphic = new VBox();
                labelProductType = createLabel();
                graphic.getChildren().addAll(labelProductType);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }

            @Override
            public void updateItem(Product product, boolean empty) {
                if (product == null) {
                    setGraphic(null);
                } else {

                    labelProductType.setText(product.getProductType().getNameProductType());
                    setGraphic(graphic);
                }
            }
        });

        columnProductStatus.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue()));
        columnProductStatus.setCellFactory(column -> new TableCell<Product, Product>() {

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
            public void updateItem(Product product, boolean empty) {
                if (product == null) {
                    setGraphic(null);
                } else {
                    boolean status = product.getStatusProduct();
                    if(status)
                        labelStatus.setText("Ativo");
                    else
                        labelStatus.setText("Inativo");


                    setGraphic(graphic);
                }
            }
        });

        //endregion

        listProduct = Product.ReadAll();
        dataObervableProduct.addAll(listProduct);
        tview_product.setItems(dataObervableProduct);
        //endregion

        cbox_categoryProduct.getItems().add("");
        for(ProductType productType: ProductType.ReadAll()){
            cbox_categoryProduct.getItems().add(productType.getNameProductType());
        }

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

        tview_productIngredient.setItems(dataObervableIngredient);
        //endregion


        //region Product Details
        showProductDetails(null);

        tview_product.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showProductDetails(newValue));
        //endregion







        //region Events
        btn_editProductType.setOnMouseClicked(this::handlerButtonActionEditProductType); //Editar Categoria
        btn_editIngredients.setOnMouseClicked(this::handlerButtonActionEditIngredients); //Editar Ingredientes
        btn_listIngredients.setOnMouseClicked(this::handlerButtonActionListIngredients); //Adicionar Ingredients
        btn_dropIngredients.setOnMouseClicked(this::handlerButtonActionDropIngredients);
        btn_editProduct.setOnMouseClicked(this::handlerButtonActionEditProduct); //Editar Produto
        btn_addProduct.setOnMouseClicked(this::handlerButtonActionAddProduct); //Adicionar Produto
        btn_cancelProduct.setOnMouseClicked(this::handlerButtonActionCancelProduct); //Cancelar
        btn_saveProduct.setOnMouseClicked(this::handlerButtonActionSaveProduct); //Salvar
        tbtn_statusProduct.setOnMouseClicked(this::handlerButtonActionStatusProduct); //Seletor de Status
        btn_searchProduct.setOnMouseClicked(this::handlerButtonActionSearchProduct);
        cbox_categoryProduct.setOnMouseClicked(this::handlerButtonActionCheckBoxProductType);
        //endregion

        //endregion


        //region Tab "Log" Events
        /**
         * tab "Log" ButtonAction
         */
        //endregion

        //endregion

    }



    //region Setup Inicial methods
    private final Label createLabel() {
        Label label = new Label();
        VBox.setVgrow(label, Priority.ALWAYS);
        label.setMaxWidth(Double.MAX_VALUE);
        label.setAlignment(Pos.CENTER);
        return label ;
    }
    //region date & time (include tests)
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
    //endregion

    public static void setUser(User newUser){
        user = newUser;
    }
    //endregion

    //region Tab "Inicio" methods
    /**
     * tab "Inicio" methods
     */
    private void handlerHyperlinkActionLogout(MouseEvent event){
        try {
            Controller.closeApplication(event);
            LoginController.loader().show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handlerHyperlinkActionFinish(MouseEvent event){
        try {
            Controller.closeApplication(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //endregion

    //region Tab "Pedido" methods
    /**
     * tab "Pedido" methods
     */
    private void handlerButtonActionRemoveProductOnOrder(MouseEvent event){

        //Tratamento contra não seleção da tabela
        if((tview_product.getSelectionModel().getSelectedIndex()+1)>=0){
            totalPrice = 0f;
            dataObervableProducts.remove(tview_product.getSelectionModel().getSelectedIndex()+1);
            dataObervableProducts.forEach(product -> totalPrice += product.getFinalPriceProduct());
            lbl_total.setText(valueOf(totalPrice));
        }

        //desabilitando botao remover quando tabela vazia
        if(dataObervableProducts.size()==0){
            btn_removeProductOnOrder.setDisable(true);
        }

    }

    private void handlerButtonActionFinishSale(MouseEvent event) {
        try {
            FinishSaleController.loader().show();
        } catch (IOException ex) {
            FxDialogs.showWarning(ex.getMessage(), "Tente novamente.");
        }
    }

    private void handlerButtonActionAddProductOnOrder(MouseEvent event) {

        //Tratamento contra nada selecionado causando index -1 no metodo
        if(listView_products.getSelectionModel().getSelectedIndex()>=0){
            totalPrice = 0f;
            dataObervableProducts.add(productsFinded.get(listView_products.getSelectionModel().getSelectedIndex()));
            dataObervableProducts.forEach(product -> totalPrice += product.getFinalPriceProduct());
            lbl_total.setText(valueOf(totalPrice));
        }

        //habilitando botao quando algo adicionado
        if(dataObervableProducts.size()>0){
            btn_removeProductOnOrder.setDisable(false);
        }
    }






    private void handlerButtonActionClearOrderProducts(MouseEvent event) {
        dataObervableProducts.clear();
    }

    private void handlerButtonActionNewOrder(MouseEvent event) {
        tfield_name.clear();
        tfield_adress.clear();
        tfield_telephone.clear();
        tarea_orderObservations.clear();
        dataObervableProducts.clear();
        hl_historicCostumer.setDisable(true);
    }

    private void handlerButtonActionFinishOrder(MouseEvent event) {
        try {

            //region UNSECURE METHOD

            //SETTING AS STATIC

            //FinishSaleController.setFinalPrice(lbl_total.getText());
            //FinishSaleController.loader().show();
            //endregion

            //region SECURE METHOD
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/finishSale.fxml"));
            fxmlLoader.load();

            FinishSaleController finishSaleController = fxmlLoader.getController();
            finishSaleController.setPrice(lbl_total.getText());

            FinishSaleController.dashboardController = this;

            Parent p = fxmlLoader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(p));
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/icon.jpg")));
            stage.setTitle("Finalizar pedido");
            stage.showAndWait();
            //endregion


        } catch (IOException ex) {
            FxDialogs.showWarning(ex.getMessage(), "Tente novamente.");
        }
    }

    private void handlerButtonActionHistoricCostumer(MouseEvent event) {
        try {
            HistoricCostumerController.loader().show();
        } catch (IOException ex) {
            FxDialogs.showWarning(ex.getMessage(), "Tente novamente.");
        }
    }


    private void handlerKeyPressedActionEnterTelephone(KeyEvent event) {

        if (event.getCode().toString().equals("ENTER")) {
            searchByTelephone();
        }

    }

    private void handlerHyperlinkVerifyRegister(MouseEvent event) {
        searchByTelephone();
    }

    //region default methods
    private void searchByTelephone() {


        person = Phone.searchByTelephone(tfield_telephone.getText());

        if (person.getAddress() == null) {

            if (FxDialogs.showConfirmYesNo("Cliente não cadastrado, deseja cadastrar?", "", FxDialogs.NO, FxDialogs.YES).equals(FxDialogs.YES)) {
                SingleSelectionModel<Tab> selectionModel = paneTab.getSelectionModel();
                selectionModel.select(tabCustomer);

                setCostumerActiveButtons(false, true, "Adicionar");
                txt_phone1Costumer.setText(tfield_telephone.getText());
                txt_nameCostumer.setText(tfield_name.getText());

                comeBack = true;


            }

        } else {
            tfield_name.setText(person.getNamePerson());
            tfield_adress.setText(person.getAddress().getStreet() + ", " + person.getAddress().getNeighborhood() + ", " + person.getAddress().getNumber());
            hl_historicCostumer.setDisable(false);
        }


    }
    //endregion
    //endregion

    //region Tab "Clientes" methods
    /**
     * tab "Clientes" methods
     */
    //region default methods
    /**
     *
     * @param bool_1 afeta a disponibilidade dos campos de inserção de dados e confirmação
     * @param bool_2 afeta a disponibilidade dos botões Editar e Adicionar
     * @param action seta o Action Employee
     */
    private void setCostumerActiveButtons(Boolean bool_1, Boolean bool_2,String action){
        group_dataCostumer.setDisable(bool_1);
        group_dataCostumerBtnSaveCancel.setDisable(bool_1);
        group_costumerBtnEditNew.setDisable(bool_2);
        if(!action.equals("node"))
            actionCostumer = action;

    }
    private void handlerButtonActionNewCostumer (MouseEvent event) {
        setCostumerActiveButtons(false,true,"Adicionar");
        clearCostumerDetails();
        tbtn_statusCostumer.setSelected(true);
        tbtn_statusCostumer.setText("Ativo");
    }
    private void handlerButtonActionEditCostumer (MouseEvent event) {
        setCostumerActiveButtons(false,true,"Editar");
    }
    private void handlerButtonActionStatusCostumer(MouseEvent event) {
        group_dataCostumerBtnSaveCancel.setDisable(false);
        group_costumerBtnEditNew.setDisable(true);
        if(!actionCostumer.equals("Editar"))
            if(!actionCostumer.equals("Adicionar"))
                actionCostumer = "Status";
    }
    private void handlerButtonActionCancelCostumer(MouseEvent event) {

        if(actionCostumer.equals("Status")){
            if(tbtn_statusCostumer.isSelected()){
                //System.out.println(tbtn_statusSupplier.isSelected());
                tbtn_statusCostumer.setSelected(false);

            }else{
                tbtn_statusCostumer.setSelected(true);
            }
        }
        setCostumerActiveButtons(true,false,"");
    }
    private void handlerButtonActionSaveCostumer(MouseEvent event) {
        setCostumerActiveButtons(true,false,"node");
        switch (actionCostumer ){
            case "Editar":
                saveCostumer();
                break;
            case "Adicionar":
                newCostumer();
                break;
            case "Status":
                setStatusCostumer();
                break;
            default:
                break;
        }
        actionCostumer = "";
    }
    private void handlerButtonActionSearchCostumer(MouseEvent event){
        resetTableViewCostumer();
    }
    //endregion

    private void clearCostumerDetails(){
        txt_nameCostumer.setText("");
        //txt_cpfCostumer.setText("");
        txt_cepCostumer.setText("");
        txt_bairroCostumer.setText("");
        txt_streetCostumer.setText("");
        txt_numberCostumer.setText("");
        txt_phone1Costumer.setText("");
        txt_phone2Costumer.setText("");
        tbtn_statusCostumer.setSelected(false);
        tbtn_statusCostumer.setText("Inativo");
        idCostumerSelected = 0;
        btn_editCostumer.setDisable(true);
    }

    private void resetTableViewCostumer(){
        listCostumer = Costumer.ReadAll();
        dataObervableCostumer.clear();
        dataObervableCostumer.addAll(listCostumer);
    }

    private void showCostumerDetails(Costumer costumer) {
        setCostumerActiveButtons(true, false, "");
        tbtn_statusCostumer.setSelected(false);
        if (costumer != null) {
            Address address = costumer.getAddress();
            ArrayList<Phone> listPhone = costumer.getListPhone();
            btn_editCostumer.setDisable(false);
            txt_nameCostumer.setText(costumer.getNamePerson());
            //txt_cpfCostumer.setText(costumer.getCpf());
            txt_cepCostumer.setText(address.getCep());
            txt_bairroCostumer.setText(address.getNeighborhood());
            txt_streetCostumer.setText(address.getStreet());
            txt_numberCostumer.setText(String.valueOf(address.getNumber()));

            if(listPhone.size()>0) {
                txt_phone1Costumer.setText(listPhone.get(0).getPhone());
            }if(listPhone.size()>1) {
                txt_phone2Costumer.setText(listPhone.get(1).getPhone());
            }else{
                txt_phone2Costumer.setText("");
            }

            idCostumerSelected  = costumer.getIdPerson();

            tbtn_statusCostumer.setSelected(costumer.getStatus());
            if(costumer.getStatus()) tbtn_statusCostumer.setText("Ativo");
            else tbtn_statusCostumer.setText("Inativo");

        } else {
            this.clearCostumerDetails();

        }
    }

    private void saveCostumer(){
        //region teste dialog
        //Código banco aqui
       /* if (FxDialogs.showConfirmYesNo( "Cliente não cadastrado, deseja cadastrar?","", FxDialogs.NO, FxDialogs.YES).equals(FxDialogs.YES)) //Teste Diálogo sim não
            System.out.println("focus na tab cliente");
        else
            System.out.println("muda boolean segue a vida");*/
        //endregion

        Costumer costumer = new Costumer(idCostumerSelected);
        Address address = costumer.getAddress();
        ArrayList<Phone> listPhone = new ArrayList();
        listPhone = costumer.getListPhone();

        Phone phone1 = new Phone();
        Phone phone2 = new Phone();

        if(listPhone.size()>0) {
            phone1 = listPhone.get(0);
        }if(listPhone.size()>1) {
            phone2 = listPhone.get(1);
        }else{

        }

        costumer.setNamePerson(txt_nameCostumer.getText());
        //costumer.setCpf(txt_cpfCostumer.getText());
        costumer.setStatus(tbtn_statusCostumer.isSelected());
        address.setCep(txt_cepCostumer.getText());
        address.setNeighborhood(txt_bairroCostumer.getText());
        address.setStreet(txt_streetCostumer.getText());
        address.setNumber(Integer.parseInt(txt_numberCostumer.getText()));
        phone1.setPhone(txt_phone1Costumer.getText());
        phone2.setPhone(txt_phone2Costumer.getText());

        costumer.Save();

        address.Save();


        if(listPhone.size()>0) {
            phone1.Save();
        }else if(!txt_phone1Costumer.getText().equals("")){
            phone1.setIdPerson(costumer.getIdPerson());
            phone1.Create();
        }

        if(listPhone.size()>1) {
            phone2.Save();
        }else if(!txt_phone2Costumer.getText().equals("")){
            phone2.setIdPerson(costumer.getIdPerson());
            phone2.Create();
        }

        resetTableViewCostumer();


    }

    private void newCostumer(){
        //Código banco aqui
        Costumer costumer = new Costumer();
        Address address = new Address();
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();
        costumer.setNamePerson(txt_nameCostumer.getText());
        //costumer.setCpf(txt_cpfCostumer.getText());
        costumer.setStatus(tbtn_statusCostumer.isSelected());
        address.setCep(txt_cepCostumer.getText());
        address.setNeighborhood(txt_bairroCostumer.getText());
        address.setStreet(txt_streetCostumer.getText());
        address.setNumber(Integer.parseInt(txt_numberCostumer.getText()));
        phone1.setPhone(txt_phone1Costumer.getText());
        phone2.setPhone(txt_phone2Costumer.getText());
        costumer.Create();
        address.Create();
        costumer.setIdAddress(address.getIdAddress());
        costumer.Save();
        phone1.setIdPerson(costumer.getIdPerson());
        phone1.Create();
        phone2.setIdPerson(costumer.getIdPerson());
        phone2.Create();

        resetTableViewCostumer();
    }

    private void setStatusCostumer(){
        //Código banco aqui
        Costumer costumer = new Costumer(idCostumerSelected);
        costumer.setStatus(tbtn_statusCostumer.isSelected());
        costumer.Save();

        resetTableViewCostumer();
    }
    //endregion

    //region Tab "Administração" methods
    /** tab "Administração" methods */

    //region Tab "Faturamento" methods
    /**
     * tab "Faturamento" methods
     */
    //endregion

    //region Tab "Vendas" methods
    /**
     * tab "Vendas" methods
     */
    private void handlerButtonActionSaleDetail(MouseEvent event) {
        try {
            SaleDetailController.loader().show();
        } catch (IOException ex) {
            FxDialogs.showWarning(ex.getMessage(), "Tente novamente.");
        }
    }
    //endregion

    //region Tab "Fornecedores" methods
    /**
     * tab "Fornecedores" methods
     */


    //region default methods
    /**
     *
     * @param bool_1 afeta a disponibilidade dos campos de inserção de dados e confirmação
     * @param bool_2 afeta a disponibilidade dos botões Editar e Adicionar
     * @param action seta o Action Employee
     */
    private void setSupplierActiveButtons(Boolean bool_1, Boolean bool_2,String action){
        group_dataSupplier.setDisable(bool_1);
        group_supplierBtnSaveCancel.setDisable(bool_1);
        group_supplierBtnEditNew.setDisable(bool_2);
        if(!action.equals("node"))
            actionSupplier = action;
    }
    private void handlerButtonActionNewSupplier (MouseEvent event) {
        setSupplierActiveButtons(false,true,"Adicionar");
        clearSupplierDetails();
        tbtn_statusSupplier.setSelected(true);
        tbtn_statusSupplier.setText("Ativo");

    }
    private void handlerButtonActionEditSupplier (MouseEvent event) {
        setSupplierActiveButtons(false,true,"Editar");
    }
    private void handlerButtonActionStatusSupplier(MouseEvent event) {
        group_supplierBtnSaveCancel.setDisable(false);
        group_supplierBtnEditNew.setDisable(true);
        if(!actionSupplier.equals("Editar"))
            if(!actionSupplier.equals("Adicionar"))
                actionSupplier = "Status";
    }
    private void handlerButtonActionCancelSupplier(MouseEvent event) {

        if(actionSupplier.equals("Status")){
            if(tbtn_statusSupplier.isSelected()){
                //System.out.println(tbtn_statusSupplier.isSelected());
                tbtn_statusSupplier.setSelected(false);

            }else{
                tbtn_statusSupplier.setSelected(true);
            }
        }
        setSupplierActiveButtons(true,false,"");
    }
    private void handlerButtonActionSaveSupplier(MouseEvent event) {
        setSupplierActiveButtons(true,false,"node");
        switch (actionSupplier ){
            case "Editar":
                saveSupplier();
                break;
            case "Adicionar":
                newSupplier();
                break;
            case "Status":
                setStatusSupplier();
                break;
            default:
                break;
        }
        actionSupplier = "";
    }
    private void handlerButtonActionSearchSupplier(MouseEvent event){
        listSupplier = Supplier.ReadAll();
        dataObervableSupplier.clear();
        dataObervableSupplier.addAll(listSupplier);
    }
    //endregion

    private void clearSupplierDetails(){
        txt_nameSupplier.setText("");
        txt_cnpjSupplier.setText("");
        txt_cepSupplier.setText("");
        txt_bairroSupplier.setText("");
        txt_streetSupplier.setText("");
        txt_numberSupplier.setText("");
        txt_phone1Supplier.setText("");
        txt_phone2Supplier.setText("");
        tbtn_statusSupplier.setSelected(false);
        tbtn_statusSupplier.setText("Inativo");
        idSupplierSelected = 0;
        btn_editSupplier.setDisable(true);
    }

    private void showSupplierDetails(Supplier supplier) {
        setSupplierActiveButtons(true, false, "");
        tbtn_statusSupplier.setSelected(false);
        if (supplier != null) {
            btn_editSupplier.setDisable(false);
            Address address = supplier.getAddress();
            ArrayList<Phone> listPhone = supplier.getListPhone();

            txt_nameSupplier.setText(supplier.getNamePerson());
            txt_cnpjSupplier.setText(supplier.getCnpj());

            txt_cepSupplier.setText(address.getCep());
            txt_bairroSupplier.setText(address.getNeighborhood());
            txt_streetSupplier.setText(address.getStreet());
            txt_numberSupplier.setText(String.valueOf(address.getNumber()));

            if(listPhone.size()>0) {
                txt_phone1Supplier.setText(listPhone.get(0).getPhone());
            }if(listPhone.size()>1) {
                txt_phone2Supplier.setText(listPhone.get(1).getPhone());
            }else{
                txt_phone2Supplier.setText("");
            }

            idSupplierSelected  = supplier.getIdPerson();

            tbtn_statusSupplier.setSelected(supplier.getStatus());
            if(supplier.getStatus()) tbtn_statusSupplier.setText("Ativo");
            else tbtn_statusSupplier.setText("Inativo");

        } else {
            this.clearSupplierDetails();

        }
    }

    private void saveSupplier(){
        Supplier supplier = new Supplier(idSupplierSelected);
        Address address = supplier.getAddress();
        ArrayList<Phone> listPhone = new ArrayList();
        listPhone = supplier.getListPhone();


        Phone phone1 = new Phone();
        Phone phone2 = new Phone();

        if(listPhone.size()>0) {
            phone1 = listPhone.get(0);
        }if(listPhone.size()>1) {
            phone2 = listPhone.get(1);
        }else{

        }

        supplier.setNamePerson(txt_nameSupplier.getText());
        supplier.setCnpj(txt_cnpjSupplier.getText());
        supplier.setStatus(tbtn_statusSupplier.isSelected());
        address.setCep(txt_cepSupplier.getText());
        address.setNeighborhood(txt_bairroSupplier.getText());
        address.setStreet(txt_streetSupplier.getText());
        address.setNumber(Integer.parseInt(txt_numberSupplier.getText()));
        phone1.setPhone(txt_phone1Supplier.getText());
        phone2.setPhone(txt_phone2Supplier.getText());

        supplier.Save();

        address.Save();


        if(listPhone.size()>0) {
            phone1.Save();
        }else if(!txt_phone1Supplier.getText().equals("")){
            phone1.setIdPerson(supplier.getIdPerson());
            phone1.Create();
        }

        if(listPhone.size()>1) {
            phone2.Save();
        }else if(!txt_phone2Supplier.getText().equals("")){
            phone2.setIdPerson(supplier.getIdPerson());
            phone2.Create();
        }

        this.resetTableViewSupplier();
    }

    private void newSupplier(){
        Supplier supplier = new Supplier();
        Address address = new Address();
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();
        supplier.setNamePerson(txt_nameSupplier.getText());
        supplier.setCnpj(txt_cnpjSupplier.getText());
        supplier.setStatus(tbtn_statusSupplier.isSelected());
        address.setCep(txt_cepSupplier.getText());
        address.setNeighborhood(txt_bairroSupplier.getText());
        address.setStreet(txt_streetSupplier.getText());
        address.setNumber(Integer.parseInt(txt_numberSupplier.getText()));
        phone1.setPhone(txt_phone1Supplier.getText());
        phone2.setPhone(txt_phone2Supplier.getText());
        supplier.Create();
        address.Create();
        supplier.setIdAddress(address.getIdAddress());
        supplier.Save();
        phone1.setIdPerson(supplier.getIdPerson());
        phone1.Create();
        phone2.setIdPerson(supplier.getIdPerson());
        phone2.Create();

        this.resetTableViewSupplier();

    }

    private void setStatusSupplier(){
        Supplier supplier = new Supplier(idSupplierSelected);
        supplier.setStatus(tbtn_statusSupplier.isSelected());
        supplier.Save();

        this.resetTableViewSupplier();

    }

    private void resetTableViewSupplier(){
        listSupplier = Supplier.ReadAll();
        dataObervableSupplier.clear();
        dataObervableSupplier.addAll(listSupplier);
    }

    //endregion

    //region Tab "Funcionários/Usuários" methods
    /** tab "Funcionários/Usuários" methods */
    //region teste
    private void showEmployeeDetails(Employee employee) {
        setEmployeeActiveButtons(true, false, "");
        if (employee != null) {
            btn_editEmployee.setDisable(false);
            txt_nameEmployee.setText(employee.getNamePerson());
            txt_roleEmployee.setText(employee.getRole());
            txt_cepEmployee.setText(employee.getAddress().getCep());
            txt_bairroEmployee.setText(employee.getAddress().getNeighborhood());
            txt_streetEmployee.setText(employee.getAddress().getStreet());
            txt_numberEmployee.setText(valueOf(employee.getAddress().getNumber()));
            ArrayList<Phone> listPhone;
            listPhone = employee.getListPhone();
            if(listPhone.size()>0) {
                txt_phone1Employee.setText(listPhone.get(0).getPhone());
            }if(listPhone.size()>1) {
                txt_phone2Employee.setText(listPhone.get(1).getPhone());
            }else{
                txt_phone2Employee.setText("");
            }
            idEmployeeSelected = employee.getIdEmployee();
            idPersonEmployeeSelected  = employee.getIdPerson();

            select_typeStatusFunc.setSelected(employee.getStatus());
            if(employee.getStatus()) select_typeStatusFunc.setText("Ativo");
            else select_typeStatusFunc.setText("Inativo");

            User user = new User(idEmployeeSelected);
            if(!user.getLogin().isEmpty()){
                btn_editarLogin.setDisable(false);
                btn_criarLogin.setDisable(true);
                txt_userLogin.setText(user.getLogin());
                txt_passLogin.setText(user.getPassword());

                if(user.getLevel() == 5) {
                    cbox_typeLevelLogin.setValue("Administrador");
                }else if(user.getLevel() == 4) {
                    cbox_typeLevelLogin.setValue("Operador");
                }else {
                    cbox_typeLevelLogin.setValue("");
                }



                select_loginStatus.setSelected(user.getStatus());
                if(user.getStatus()) select_loginStatus.setText("Ativo");
                else select_loginStatus.setText("Inativo");

                this.selectLoginStatusAction();

            }else{
                btn_editarLogin.setDisable(true);
                btn_criarLogin.setDisable(false);
                txt_userLogin.setText("");
                txt_passLogin.setText("");
                cbox_typeLevelLogin.setValue("");
                select_loginStatus.setSelected(false);
                select_loginStatus.setText("Inativo");
                this.selectLoginStatusAction();
            }



        } else {
            // Employee é null, remove todo o texto.
            this.clearEmployeeDetails();




        }
    }
    //endregion

    private void clearEmployeeDetails(){
        txt_nameEmployee.setText("");
        txt_roleEmployee.setText("");
        txt_cepEmployee.setText("");
        txt_bairroEmployee.setText("");
        txt_streetEmployee.setText("");
        txt_numberEmployee.setText("");
        txt_phone1Employee.setText("");
        txt_phone2Employee.setText("");
        txt_userLogin.setText("");
        txt_passLogin.setText("");
        btn_editarLogin.setDisable(true);
        btn_criarLogin.setDisable(false);
        cbox_typeLevelLogin.setValue("");
        select_loginStatus.setSelected(false);
        select_typeStatusFunc.setSelected(false);
        select_typeStatusFunc.setText("Inativo");
        this.selectLoginStatusAction();
        idEmployeeSelected = 0;
        btn_editEmployee.setDisable(true);
    }

    private void resetTableViewEmployee(){
        listEmployee = Employee.ReadAll();
        dataObervableEmployee.clear();
        dataObervableEmployee.addAll(listEmployee);
    }

    //region default methods
    private void handlerButtonActionManageUsers(MouseEvent event) {
        try {
            ManageUsersController.loader().show();
        } catch (IOException ex) {
            FxDialogs.showWarning(ex.getMessage(), "Tente novamente.");
        }
    }

    private void selectLoginStatusAction(){
        if(select_loginStatus.isSelected()){
            select_loginStatus.setText("Habilitado");
            tp_login.setVisible(true);
            tp_login.setDisable(false);
            //System.out.println("1  - "+select_loginStatus.isSelected());
        }else{
            select_loginStatus.setText("Desabilitado");
            tp_login.setVisible(false);
            tp_login.setDisable(true);
            //System.out.println("2   -"+select_loginStatus.isSelected());
        }
        if(!actionEmployee.equals("Editar"))
            if(!actionEmployee.equals("Adicionar"))
                actionEmployee = "Login";


    }
    private void handlerButtonSelectLoginStatus(MouseEvent event) {
        this.selectLoginStatusAction();
        group_dataFunc_btn.setDisable(false);
    }

    /**
     *
     * @param bool_1 afeta a disponibilidade dos campos de inserção de dados e confirmação
     * @param bool_2 afeta a disponibilidade dos botões Editar e Adicionar
     * @param action seta o Action Employee
     */
    private void setEmployeeActiveButtons(Boolean bool_1, Boolean bool_2,String action){
        group_dataFunc.setDisable(bool_1);
        group_dataFunc_btn.setDisable(bool_1);
        group_editAddFunc.setDisable(bool_2);
        if(!action.equals("node"))
            actionEmployee = action;

    }
    private void handlerButtonActionAddEmployee (MouseEvent event) {
        setEmployeeActiveButtons(false,true,"Adicionar");
        this.clearEmployeeDetails();
        select_typeStatusFunc.setSelected(true);
        select_typeStatusFunc.setText("Ativo");

    }
    private void handlerButtonActionEditEmployee (MouseEvent event) {
        setEmployeeActiveButtons(false,true,"Editar");
    }
    private void handlerButtonActionStatusEmployee(MouseEvent event) {
        group_dataFunc_btn.setDisable(false);
        group_editAddFunc.setDisable(true);
        if(!actionEmployee.equals("Editar"))
            if(!actionEmployee.equals("Adicionar"))
                actionEmployee = "Status";
    }
    private void handlerButtonActionCancelEmployee(MouseEvent event) {

        if(actionEmployee.equals("Status")){
            select_typeStatusFunc.setSelected(!select_typeStatusFunc.isSelected());
        }
        if(actionEmployee.equals("Login")){
            /*if(select_loginStatus.isSelected()){
                //System.out.println(tbtn_statusProduct.isSelected());
                select_loginStatus.setSelected(false);

            }else{
                select_loginStatus.setSelected(true);
            }*/
            select_loginStatus.setSelected(!select_loginStatus.isSelected());
        }
        setEmployeeActiveButtons(true,false,"");
    }
    private void handlerButtonActionSaveEmployee(MouseEvent event) {
        setEmployeeActiveButtons(true,false,"node");
        switch (actionEmployee ){
            case "Editar":
                saveEmployee();
                break;
            case "Adicionar":
                addEmployee();
                break;
            case "Status":
                setStatusEmployee();
                break;
            case "Login":
                try {
                    setStatusLogin();
                } catch (UserException ex) {
                    FxDialogs.showException("Erro ao salvar Usuário",ex.getClass() +  " - " + ex.getMessage(),ex);
                }
                break;
            default:
                break;
        }
        actionEmployee = "";
    }

    //Login methods
    private void setLoginActiveButtons(Boolean bool_1, Boolean bool_2,String action){
        group_dataUser.setDisable(bool_1);
        group_dataUserBtnEditarCriar.setDisable(bool_2);

        if(!action.equals("node"))
            actionLogin = action;

    }
    private void handlerButtonActionAddLogin (MouseEvent event) {
        setLoginActiveButtons(false,true,"Adicionar");
    }
    private void handlerButtonActionEditLogin (MouseEvent event) {
        setLoginActiveButtons(false,true,"Editar");
    }
    private void handlerButtonActionCancelLogin (MouseEvent event) {
        setLoginActiveButtons(true,false,"");
        User user = new User(idEmployeeSelected);
        if(!user.getLogin().isEmpty()) {
            btn_editarLogin.setDisable(false);
            btn_criarLogin.setDisable(true);
        }else{
            btn_editarLogin.setDisable(true);
            btn_criarLogin.setDisable(false);
        }
    }
    private void handlerButtonActionSaveLogin (MouseEvent event) {
        setLoginActiveButtons(true,false,"node");
        switch (actionLogin ){
            case "Editar":
                try{
                    saveLogin();
                } catch (UserException ex) {
                    FxDialogs.showException("Erro ao salvar Usuário",ex.getClass() +  " - " + ex.getMessage(),ex);
                }
                break;
            case "Adicionar":
                try{
                    addLogin();
                } catch (UserException ex) {
                    FxDialogs.showException("Erro ao criar Usuário",ex.getClass() +  " - " + ex.getMessage(),ex);
                }
                break;
            default:
                break;
        }
        actionLogin = "";
    }
    //endregion

    private void saveEmployee(){
        //Código banco aqui
        Employee employee = new Employee(idPersonEmployeeSelected,idEmployeeSelected);
        Address address = new Address(employee.getIdAddress());
        ArrayList<Phone> listPhone = new ArrayList();
        listPhone = employee.getListPhone();
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();

        if(listPhone.size()>0) {
            phone1 = listPhone.get(0);
        }if(listPhone.size()>1) {
            phone2 = listPhone.get(1);
        }else{

        }

        employee.setNamePerson(txt_nameEmployee.getText());
        employee.setRole(txt_roleEmployee.getText());
        employee.setStatus(select_typeStatusFunc.isSelected());
        address.setCep(txt_cepEmployee.getText());
        address.setNeighborhood(txt_bairroEmployee.getText());
        address.setStreet(txt_streetEmployee.getText());
        address.setNumber(Integer.parseInt(txt_numberEmployee.getText()));
        phone1.setPhone(txt_phone1Employee.getText());
        phone2.setPhone(txt_phone2Employee.getText());

        employee.Save();
        address.Save();


        if(listPhone.size()>0) {
            phone1.Save();
        }else if(!txt_phone1Employee.getText().equals("")){
            phone1.setIdPerson(employee.getIdPerson());
            phone1.Create();
        }

        if(listPhone.size()>1) {
            phone2.Save();
        }else if(!txt_phone2Employee.getText().equals("")){
            phone2.setIdPerson(employee.getIdPerson());
            phone2.Create();
        }


        this.resetTableViewEmployee();

    }

    private void addEmployee(){
        //Código banco aqui
        Employee employee = new Employee();
        Address address = new Address();
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();
        employee.setNamePerson(txt_nameEmployee.getText());
        employee.setRole(txt_roleEmployee.getText());
        employee.setStatus(select_typeStatusFunc.isSelected());
        address.setCep(txt_cepEmployee.getText());
        address.setNeighborhood(txt_bairroEmployee.getText());
        address.setStreet(txt_streetEmployee.getText());
        address.setNumber(Integer.parseInt(txt_numberEmployee.getText()));
        phone1.setPhone(txt_phone1Employee.getText());
        phone2.setPhone(txt_phone2Employee.getText());


        address.Create();

        employee.setIdAddress(address.getIdAddress());

        employee.Create();


        phone1.setIdPerson(employee.getIdPerson());
        phone1.Create();

        phone2.setIdPerson(employee.getIdPerson());
        phone2.Create();

        this.resetTableViewEmployee();


    }

    private void setStatusEmployee(){
        Employee employee = new Employee(idPersonEmployeeSelected,idEmployeeSelected);
        employee.setStatus(select_typeStatusFunc.isSelected());
        employee.Save();

        this.resetTableViewEmployee();

    }


    private void setStatusLogin() throws UserException {
        saveLogin();
    }

    //Login methods
    private void saveLogin() throws UserException {
        //Código banco aqui
        User user = new User(idEmployeeSelected);
        String level = cbox_typeLevelLogin.getSelectionModel().getSelectedItem();

        if(!user.getLogin().isEmpty() || !user.getPassword().isEmpty() || !level.equals("")){

            user.setLogin(txt_userLogin.getText());
            user.setPassword(txt_passLogin.getText());


            if(level.equals("Administrador")) {
                user.setLevel(5);
            }else if(level.equals("Operador")) {
                user.setLevel(4);
            }

            user.setStatus(select_loginStatus.isSelected());


            user.SaveByIdEmployee();

        }else{
            if(user.getLogin().isEmpty() && user.getPassword().isEmpty()){
                throw new UserException("Digite um login e uma senha!") {};
            }else if (user.getLogin().isEmpty()) {
                throw new UserException("Digite um login!") {};
            }else if (user.getPassword().isEmpty()) {
                throw new UserException("Digite uma senha!") {};

            }else if (level.equals("")){
                throw new UserException("Escolha um nivel de acesso!") {};
            }
        }
    }

    private void addLogin() throws UserException {
        //Código banco aqui
        User user = new User();
        user.setIdEmployee(idEmployeeSelected);
        user.setLogin(txt_userLogin.getText());
        user.setPassword(txt_passLogin.getText());
        String level = cbox_typeLevelLogin.getSelectionModel().getSelectedItem();
        if(level.equals("Administrador")) {
            user.setLevel(5);
        }else if(level.equals("Operador")) {
            user.setLevel(4);
        }

        user.setStatus(select_loginStatus.isSelected());

        if(!user.getLogin().isEmpty() || !user.getPassword().isEmpty() || !level.equals("")){
            user.Create();
        }else{
            if(user.getLogin().isEmpty() && user.getPassword().isEmpty()){
                throw new UserException("Digite um login e uma senha!") {};
            }else if (user.getLogin().isEmpty()) {
                throw new UserException("Digite um login!") {};
            }else if (user.getPassword().isEmpty()) {
                throw new UserException("Digite uma senha!") {};

            }else if (level.equals("")){
                throw new UserException("Escolha um nivel de acesso!") {};
            }
        }

    }


    //endregion

    //region Tab "Produtos" methods
    /**
     * tab "Produtos" methods
     *
     */

    //region default methods
    public static void addIngredient(Ingredient ingredient){
        boolean flag = true;
        for(Ingredient ingredient2 : dataObervableIngredient){
            if(ingredient.getIdIngredient() == ingredient2.getIdIngredient()){
                flag = false;
            }
        }
        if(flag) dataObervableIngredient.add(ingredient);
    }



    private void handlerButtonActionCheckBoxProductType(MouseEvent event){
        if(refreshCategory) {
            cbox_categoryProduct.getItems().clear();
            cbox_categoryProduct.getItems().add("");
            for (ProductType productType : ProductType.ReadAll()) {
                cbox_categoryProduct.getItems().add(productType.getNameProductType());
            }
            resetTableViewProduct();
            refreshCategory = false;
        }
    }

    private void handlerButtonActionEditProductType(MouseEvent event) {
        try {
            EditProductTypeController.loader().show();



        } catch (IOException ex) {
            FxDialogs.showWarning(ex.getMessage(), "Tente novamente.");
        }finally {

        }
    }
    private void handlerButtonActionEditIngredients(MouseEvent event) {
        try {
            EditIngredientsController.loader().show();
        } catch (IOException ex) {
            FxDialogs.showWarning(ex.getMessage(), "Tente novamente.");
        }
    }
    private void handlerButtonActionListIngredients(MouseEvent event) {
        try {
            ListIngredientsController.loader().show();
        } catch (IOException ex) {
            FxDialogs.showWarning(ex.getMessage(), "Tente novamente.");
        }
    }
    private void handlerButtonActionDropIngredients(MouseEvent event) {
        dataObervableIngredient.remove(tview_productIngredient.getSelectionModel().getSelectedItem());

    }

    /**
     *
     * @param bool_1 afeta a disponibilidade dos campos de inserção de dados e confirmação
     * @param bool_2 afeta a disponibilidade dos botões Editar, Adicionar e Excluir
     * @param action seta o Action Product
     */
    private void setProductActiveButtons(Boolean bool_1, Boolean bool_2,String action){
        group_dataProduct.setDisable(bool_1);
        group_addDropIngredients.setDisable(bool_1);
        group_cancelSaveProduct.setDisable(bool_1);
        group_editAddProduct.setDisable(bool_2);
        if(!action.equals("node"))
            actionProduct = action;

    }
    private void handlerButtonActionEditProduct(MouseEvent event) {
        setProductActiveButtons(false,true,"Editar");
    }
    private void handlerButtonActionAddProduct(MouseEvent event) {
        setProductActiveButtons(false,true,"Adicionar");
        clearProductDetails();
        tbtn_statusProduct.setSelected(true);
        tbtn_statusProduct.setText("Ativo");

    }
    private void handlerButtonActionCancelProduct(MouseEvent event) {

        if(actionProduct.equals("Status")){
            if(tbtn_statusProduct.isSelected()){
                //System.out.println(tbtn_statusProduct.isSelected());
                tbtn_statusProduct.setSelected(false);

            }else{
                tbtn_statusProduct.setSelected(true);
            }
        }
        setProductActiveButtons(true,false,"");
    }
    private void handlerButtonActionSaveProduct(MouseEvent event) {
        setProductActiveButtons(true,false,"node");
        switch (actionProduct ){
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
        actionProduct = "";
    }
    private void handlerButtonActionStatusProduct(MouseEvent event) {
        group_cancelSaveProduct.setDisable(false);
        group_editAddProduct.setDisable(true);
        if(!actionProduct.equals("Editar"))
            if(!actionProduct.equals("Adicionar"))
                actionProduct = "Status";
        if(tbtn_statusProduct.isSelected()) tbtn_statusProduct.setText("Ativo");
        else tbtn_statusProduct.setText("Inativo");
    }
    private void handlerButtonActionSearchProduct(MouseEvent event) {
        listProduct = Product.ReadAll();
        dataObervableProduct.clear();
        dataObervableProduct.addAll(listProduct);
    }
    //endregion

    private void clearProductDetails(){
        txt_nameProduct.setText("");
        txt_finalPriceProduct.setText("");
        txt_weightProduct.setText("");
        //imageview_product
        cbox_categoryProduct.setValue("");
        resetTableViewProductIngredient();
        tbtn_statusProduct.setSelected(false);
        tbtn_statusProduct.setText("Inativo");
        idProductSelected = 0;
        btn_editProduct.setDisable(true);
    }

    private void resetTableViewProduct(){
        listProduct = Product.ReadAll();
        dataObervableProduct.clear();
        dataObervableProduct.addAll(listProduct);
    }

    private void resetTableViewProductIngredient(){
        dataObervableIngredient.clear();
    }

    private void showProductDetails(Product product) {
        setProductActiveButtons(true, false, "");
        tbtn_statusProduct.setSelected(false);
        if (product != null) {
            btn_editProduct.setDisable(false);
            ProductType productType = product.getProductType();
            ArrayList<Ingredient> listIngredient = product.getListIngredients();

            txt_nameProduct.setText(product.getNameProduct());
            txt_finalPriceProduct.setText(valueOf(product.getFinalPriceProduct()));
            txt_weightProduct.setText(valueOf(product.getWeightProduct()));
            //imageview_product
            cbox_categoryProduct.setValue(productType.getNameProductType());

            idProductSelected  = product.getIdProduct();

            tbtn_statusProduct.setSelected(product.getStatusProduct());
            if(product.getStatusProduct()) tbtn_statusProduct.setText("Ativo");
            else tbtn_statusProduct.setText("Inativo");

            listIngredient = product.getListIngredients();
            dataObervableIngredient.clear();
            dataObervableIngredient.addAll(listIngredient);

        } else {
            this.clearProductDetails();

        }
    }

    private void saveProduct(){
        Product product = new Product(idProductSelected);
        ProductType productType;
        ArrayList<Ingredient> listIngredient = new ArrayList<>();


        product.setNameProduct(txt_nameProduct.getText());
        product.setFinalPriceProduct(Float.parseFloat(txt_finalPriceProduct.getText()));
        product.setWeightProduct(Float.parseFloat(txt_weightProduct.getText()));
        productType = new ProductType(cbox_categoryProduct.getSelectionModel().getSelectedItem());
        product.setIdProductType(productType.getIdProductType());
        listIngredient.addAll(dataObervableIngredient);
        product.setStatusProduct(tbtn_statusProduct.isSelected());
        product.Save();
        ProductIngredient.saveListProductIngredient(listIngredient,idProductSelected);

        resetTableViewProduct();
    }

    private void addProduct(){
        Product product = new Product();
        ProductType productType;
        ArrayList<Ingredient> listIngredient = new ArrayList<>();


        product.setNameProduct(txt_nameProduct.getText());
        product.setFinalPriceProduct(Float.parseFloat(txt_finalPriceProduct.getText()));
        product.setWeightProduct(Float.parseFloat(txt_weightProduct.getText()));
        productType = new ProductType(cbox_categoryProduct.getSelectionModel().getSelectedItem());
        product.setIdProductType(productType.getIdProductType());
        listIngredient.addAll(dataObervableIngredient);
        product.setStatusProduct(tbtn_statusProduct.isSelected());
        product.Create();
        ProductIngredient.saveListProductIngredient(listIngredient,product.getIdProduct());

        resetTableViewProduct();
    }

    private void setStatusProduct(){
        //Código banco aqui
        Product product = new Product(idProductSelected);
        product.setStatusProduct(tbtn_statusProduct.isSelected());
        product.Save();

        resetTableViewProduct();

    }

    //endregion

    //region Tab "Log" methods
    /**
     * tab "Log" methods
     *
     */
    //endregion

    //endregion



    //--------------------------------------------------

    public static Stage loader() throws IOException {

        return Controller.loader(LoginController.class, StageStyle.DECORATED, path, title);

    }


}
