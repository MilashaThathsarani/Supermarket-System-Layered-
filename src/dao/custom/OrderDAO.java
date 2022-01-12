package dao.custom;

import dao.CrudDAO;
import dto.OrdersDTO;
import entity.Orders;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Orders,String> {
    public int orders() throws SQLException, ClassNotFoundException;
    public String getOrderIds() throws SQLException, ClassNotFoundException;
    public boolean ifExistsOrderId(String ordersId) throws SQLException, ClassNotFoundException;

}
