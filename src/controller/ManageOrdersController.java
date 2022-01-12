package controller;

import View.tm.OrderTm;
import bo.BoFactory;
import bo.custom.CustomerBO;
import bo.custom.OrderDetailBO;
import dao.custom.QueryDAO;
import dao.custom.impl.QueryDAOImpl;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class ManageOrdersController {
    //private final OrderDetailBO orderDetailBO = (OrderDetailBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.CUSTOMER);
    public TableView tblOrderList;
    public TableColumn colOrderId;
    public TableColumn colCustomerId;
    public TableColumn colCustomerAddress;
    public TableColumn colCity;
    public TableColumn colCustomerName;
    public TableColumn colProvince;
    public TableColumn colPostalCode;
    public TableColumn colOrderDate;
    public TableColumn colOrderTime;
    public Label lblDate;
    public Label lblTime;
    public AnchorPane manageOrderContext;


    public void initialize(){

        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("customerCity"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("customerProvince"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));

        loadDateAndTime();

        try {
            loadAllData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        tblOrderList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
            try {
                OrderTm temp= (OrderTm) newValue;
                System.out.println(temp.getOrderId());
                 loadDetailUi(temp.getOrderId());
            } catch (IOException | SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
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

    private void loadAllData() throws SQLException, ClassNotFoundException {
        ObservableList<OrderTm> tmList = FXCollections.observableArrayList();
        QueryDAO queryDAO = new QueryDAOImpl();
        for (OrderTm temp:queryDAO.getAllOrder()
        ) {
            tmList.add(
                    new OrderTm(temp.getCustomerId(),
                            temp.getCustomerName(),
                            temp.getCustomerAddress(),
                            temp.getCustomerCity(),
                            temp.getCustomerProvince(),
                            temp.getCustomerPostalCode(),
                            temp.getOrderId(),
                            temp.getOrderDate()
            ));

        }
        tblOrderList.setItems(tmList);
    }

    private void loadDetailUi(String orderId) throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/ManageOrderDetail.fxml"));
        Parent load = loader.load();
        ManageOrderDetailController controller = loader.getController();
        controller.loadAllData(orderId);
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.show();
        stage.centerOnScreen();
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) manageOrderContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/DashBoardCashier.fxml"))));
        window.centerOnScreen();
    }
}
