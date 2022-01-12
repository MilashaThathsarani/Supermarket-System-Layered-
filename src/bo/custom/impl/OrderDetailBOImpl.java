package bo.custom.impl;

import View.tm.OrderDetailTm;
import bo.custom.OrderDetailBO;
import dao.DAOFactory;
import dao.custom.OrderDetailDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailBOImpl implements OrderDetailBO {

    private final OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);

    @Override
    public ArrayList<OrderDetailTm> getAllInvoiceDetails(String orderId) throws SQLException, ClassNotFoundException {
        return orderDetailDAO.getAllInvoiceDetails(orderId);
    }

    @Override
    public int orderDetails() throws SQLException, ClassNotFoundException {
        return orderDetailDAO.orderDetails();
    }
}
