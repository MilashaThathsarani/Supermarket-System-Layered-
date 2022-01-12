package bo.custom;

import bo.SuperBO;
import dto.CustomerDTO;
import dto.ItemDTO;
import dto.OrderDetailDTO;
import dto.OrdersDTO;

import java.sql.SQLException;
import java.util.List;

public interface PurchaseOrderBO extends SuperBO {
    boolean purchaseOrder(OrdersDTO dto) throws SQLException, ClassNotFoundException;

    String getOrderIds()throws SQLException, ClassNotFoundException;

    ItemDTO searchItems(String itemCode)throws SQLException, ClassNotFoundException;

    CustomerDTO searchCustomers(String customerId) throws SQLException, ClassNotFoundException;

    List<String> getAllItemIds()throws SQLException, ClassNotFoundException;

    List<String> getAllCustomerIds()throws SQLException, ClassNotFoundException;

    int orders() throws SQLException, ClassNotFoundException;
}
