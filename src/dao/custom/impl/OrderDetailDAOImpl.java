package dao.custom.impl;

import View.tm.OrderDetailTm;
import dao.CrudUtil;
import dao.custom.OrderDetailDAO;
import db.DbConnection;
import dto.OrderDetailDTO;
import entity.OrderDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public boolean add(OrderDetail orderDetail) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO `Order Detail` VALUES(?,?,?,?)",orderDetail.getCode(),orderDetail.getOrderId(),orderDetail.getOrderQty(),orderDetail.getDisscount());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public boolean update(OrderDetail orderDetail) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public OrderDetail search(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public ArrayList<OrderDetail> getAll() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public int orderDetails() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT COUNT(*) FROM `Order Detail`");
        if (rst.next()) {
            return rst.getInt(1);
        }else {
            return 0;
        }
    }

    @Override
    public ArrayList<OrderDetailTm> getAllInvoiceDetails(String orderId) throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetailTm> details = new ArrayList<>();
        ResultSet resultSet = CrudUtil.executeQuery("SELECT code,orderQty,disscount FROM `Order Detail` WHERE orderId=?",orderId);
        while (resultSet.next()){
            details.add(
                    new OrderDetailTm(
                            resultSet.getString("code"),
                            orderId,
                            resultSet.getInt("orderQty"),
                            resultSet.getDouble("disscount")
                    )

            );
        }
        return details;
    }
}
