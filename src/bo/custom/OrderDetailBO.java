package bo.custom;

import View.tm.OrderDetailTm;
import bo.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailBO extends SuperBO {
    ArrayList<OrderDetailTm> getAllInvoiceDetails(String orderId) throws SQLException, ClassNotFoundException;

    int orderDetails() throws SQLException, ClassNotFoundException;
}
