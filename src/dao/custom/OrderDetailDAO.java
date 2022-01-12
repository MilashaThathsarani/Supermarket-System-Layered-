package dao.custom;

import View.tm.OrderDetailTm;
import dao.CrudDAO;
import db.DbConnection;
import dto.OrderDetailDTO;
import entity.OrderDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailDAO extends CrudDAO<OrderDetail,String> {
    public int orderDetails() throws SQLException, ClassNotFoundException;
    public ArrayList<OrderDetailTm> getAllInvoiceDetails(String orderId) throws SQLException, ClassNotFoundException;

}
