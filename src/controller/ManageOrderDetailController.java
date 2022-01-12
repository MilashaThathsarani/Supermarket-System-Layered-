package controller;

import View.tm.OrderDetailTm;
import bo.BoFactory;
import bo.custom.OrderDetailBO;
import dao.custom.QueryDAO;
import dao.custom.impl.OrderDetailDAOImpl;
import dao.custom.impl.QueryDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class ManageOrderDetailController {
    private final OrderDetailBO orderDetailBO = (OrderDetailBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.ORDER_DETAIL);
    public Label lblOrderId;
    public TableView tblOrderDetail;
    public TableColumn colItemCode;
    public TableColumn colQty;
    public TableColumn colUnitPrice;

    public void initialize(){
        loadDateAndTime();

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("orderQty"));
    }

    private void loadDateAndTime() {
    }

    public void loadAllData(String orderId) throws SQLException, ClassNotFoundException {
        lblOrderId.setText(String.valueOf(orderId));
        //double total=0;
        ObservableList<OrderDetailTm> tmList = FXCollections.observableArrayList();
        for (OrderDetailTm tempD :orderDetailBO.getAllInvoiceDetails(orderId)
        ) {
            tmList.add(new OrderDetailTm(tempD.getCode(),tempD.getOrderId(),tempD.getOrderQty(),tempD.getUnitPrice()));
        }
        tblOrderDetail.setItems(tmList);
        // lblCost.setText("Rs.  "+(total));
    }
}
