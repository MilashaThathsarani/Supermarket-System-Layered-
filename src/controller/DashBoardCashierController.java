package controller;

import bo.BoFactory;
import bo.custom.CustomerBO;
import bo.custom.ItemBO;
import bo.custom.OrderDetailBO;
import bo.custom.PurchaseOrderBO;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class DashBoardCashierController {

    private final CustomerBO customerBO = (CustomerBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.CUSTOMER);
    private final ItemBO itemBO = (ItemBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.ITEM);
    private final PurchaseOrderBO purchaseOrderBO = (PurchaseOrderBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.PURCHASE_ORDER);
    private final OrderDetailBO orderDetailBO = (OrderDetailBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.ORDER_DETAIL);

    public AnchorPane cashierContext;
    public Label labelCustomer;
    public Label lblItem;
    public Label lblOrder;
    public Label lblOrderDetail;
    public Label lblDate;
    public Label lblTime;

    public void initialize(){
        loadDateAndTime();
        try {
            getCustomer();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            getItem();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            getOrder();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            getOrderDetail();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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

    private void getOrderDetail() throws SQLException, ClassNotFoundException {
        lblOrderDetail.setText(String.valueOf(orderDetailBO.orderDetails()));
    }

    private void getOrder() throws SQLException, ClassNotFoundException {
        lblOrder.setText(String.valueOf(purchaseOrderBO.orders()));
    }

    private void getItem() throws SQLException, ClassNotFoundException {
        lblItem.setText(String.valueOf(itemBO.items()));
    }

    private void getCustomer() throws SQLException, ClassNotFoundException {
        labelCustomer.setText(String.valueOf(customerBO.customers()));
    }

    private void loadUi(String filename) throws IOException {
        URL resource = getClass().getResource("../View/" + filename + ".fxml");
        Parent load = FXMLLoader.load(resource);
        cashierContext.getChildren().clear();
        cashierContext.getChildren().add(load);
    }


    public void manageOrderOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("ManageOrders");
    }

    public void makeCustomerOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("MakeCustomerOrders");
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) cashierContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/LogIn.fxml"))));
        window.centerOnScreen();
    }
}
