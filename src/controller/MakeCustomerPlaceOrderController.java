package controller;

import View.tm.CartTm;
import bo.BoFactory;
import bo.custom.PurchaseOrderBO;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import dto.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class MakeCustomerPlaceOrderController {
    private final PurchaseOrderBO purchaseOrderBO = (PurchaseOrderBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.PURCHASE_ORDER);
    public AnchorPane makeOrderContext;
    public ComboBox cmbCustomerId;
    public JFXTextField txtCustomerTittle;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCity;
    public JFXTextField txtProvince;
    public JFXTextField txtxPostalCode;
    public ComboBox cmbItemCode;
    public JFXTextField txtPackSize;
    public JFXTextField txtDescription;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtQty;
    public TableView<CartTm> tblList;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colTotal;
    public Label lblTotal;
    static ArrayList<CustomerDTO> customerDTOList = new ArrayList();
    public Label lblOrderId;
    public Label lblDate;
    public Label lblTime;
    public TextField txtOrderId;

    public void initialize() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        loadDateAndTime();

        try {
            setOrderId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            loadItemIds();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            loadCustomersIds();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbCustomerId.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    try {
                        setCustomerData((String) newValue);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setItemData((String) newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void setOrderId() throws SQLException, ClassNotFoundException {
        txtOrderId.setText(purchaseOrderBO.getOrderIds());
    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(
                    currentTime.getHour() + " : " + currentTime.getMinute() + " : " + currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }


    public void addNewCustomerOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/AddNewCustomer.fxml"));
        Parent parent = loader.load();
        AddNewCustomerController controller = loader.getController();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();

        controller.closeStage(makeOrderContext);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

    private void setItemData(String itemCode) throws SQLException, ClassNotFoundException {
        ItemDTO itemDTO = purchaseOrderBO.searchItems(itemCode);
        if (itemDTO == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            txtDescription.setText(itemDTO.getDescription());
            txtPackSize.setText(itemDTO.getPackSize());
            txtQtyOnHand.setText(String.valueOf(itemDTO.getQtyOnHand()));
            txtUnitPrice.setText(String.valueOf(itemDTO.getUnitPrice()));
        }
    }

    private void setCustomerData(String customerId) throws SQLException, ClassNotFoundException {
        CustomerDTO customerDTO = purchaseOrderBO.searchCustomers(customerId);
        if (customerDTO == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result");
        } else {
            txtCustomerTittle.setText(customerDTO.getCustTittle());
            txtCustomerName.setText(customerDTO.getCustName());
            txtCustomerAddress.setText(customerDTO.getCustAddress());
            txtCity.setText(customerDTO.getCity());
            txtProvince.setText(customerDTO.getProvince());
            txtxPostalCode.setText(customerDTO.getPostalCode());
        }
    }

    private void loadItemIds() throws SQLException, ClassNotFoundException {
        List<String> itemIds = purchaseOrderBO.getAllItemIds();
        cmbItemCode.getItems().addAll(itemIds);
    }

    private void loadCustomersIds() throws SQLException, ClassNotFoundException {
        List<String> customerIds = purchaseOrderBO.getAllCustomerIds();
        cmbCustomerId.getItems().addAll(customerIds);
    }

    ObservableList<CartTm> obList = FXCollections.observableArrayList();

    public void addToCartOnAction(ActionEvent actionEvent) {
        String description = txtDescription.getText();
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qtyForCustomer = Integer.parseInt(txtQty.getText());
        double total = qtyForCustomer * unitPrice;

        if (qtyOnHand < qtyForCustomer) {
            new Alert(Alert.AlertType.WARNING, "Invalid QTY").show();
            return;
        }

        CartTm tm = new CartTm(
                (String) cmbItemCode.getValue(),
                description,
                qtyForCustomer,
                unitPrice,
                total
        );

        int rowNumber = isExists(tm);

        if ( rowNumber==-1) {
            obList.add(tm);
        } else {
            CartTm temp = obList.get(rowNumber);
            CartTm newTm = new CartTm(
                    temp.getItemCode(),
                    temp.getDescription(),
                    temp.getQty()+qtyForCustomer,
                    unitPrice,
                    total+temp.getTotal()
            );

            obList.remove(rowNumber);
            obList.add(newTm);
        }

        tblList.setItems(obList);

        calculate();
    }

    private int isExists(CartTm tm) {

        for (int i = 0; i < obList.size(); i++) {
            if (tm.getItemCode().equals(obList.get(i).getItemCode())){
                return i;
            }
        }
        return -1;
    }
    void calculate() {
        double ttl = 0;
        for (CartTm tm : obList
        ) {
            ttl += tm.getTotal();
        }
        lblTotal.setText(ttl+"/=");
    }


    private void clearText() {
        txtCustomerName.setText("");
        txtCustomerAddress.setText("");
        txtCustomerTittle.setText("");
        txtCity.setText("");
        txtProvince.setText("");
        txtxPostalCode.setText("");
        txtDescription.setText("");
        txtQtyOnHand.setText("");
        txtPackSize.setText("");
        txtUnitPrice.setText("");
        txtQty.setText("");

        for (int i = 0; i < tblList.getItems().size(); i++) {
            tblList.getItems().clear();
        }
    }

    public void confirmOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, JRException {
        ArrayList<OrderDetailDTO> items= new ArrayList<>();
        for (CartTm tm:obList
        ) {
            items.add (new OrderDetailDTO(txtOrderId.getText(),tm.getItemCode(),tm.getQty(),tm.getUnitPrice()));
        }

        boolean b = saveOrder(txtOrderId.getText(), (String) cmbCustomerId.getValue(),lblDate.getText(),items);
        if (b) {
            new Alert(Alert.AlertType.INFORMATION, "Order has been placed successfully").show();
            showInvoice();
            setOrderId();
            clearText();
        } else {
            new Alert(Alert.AlertType.ERROR, "Order has not been placed successfully").show();
        }

    }

    private void showInvoice() throws JRException {
        JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/View/report/Bill.jrxml"));
        JasperReport compileReport = JasperCompileManager.compileReport(design);
        ObservableList<CartTm> products = tblList.getItems();
        JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, new JRBeanArrayDataSource(products.toArray()));
        JasperViewer.viewReport(jasperPrint, false);
    }

    public boolean saveOrder(String ordersId,String cId, String orderDate, List<OrderDetailDTO> items) {
        try {
            OrdersDTO ordersDTO = new OrdersDTO(ordersId, cId,orderDate,items);
            return purchaseOrderBO.purchaseOrder(ordersDTO);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) makeOrderContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/DashBoardCashier.fxml"))));
        window.centerOnScreen();
    }
}
