package bo.custom.impl;

import bo.custom.PurchaseOrderBO;
import dao.DAOFactory;
import dao.custom.*;
import db.DbConnection;
import dto.CustomerDTO;
import dto.ItemDTO;
import dto.OrderDetailDTO;
import dto.OrdersDTO;
import entity.Customer;
import entity.Item;
import entity.OrderDetail;
import entity.Orders;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PurchaseOrderBOImpl implements PurchaseOrderBO {

    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    private final OrderDetailDAO orderDetailsDAO = (OrderDetailDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);
    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.QUERYDAO);

    @Override
    public boolean purchaseOrder(OrdersDTO dto) throws SQLException, ClassNotFoundException {
        /*Transaction*/
        Connection connection = null;

        connection = DbConnection.getInstance().getConnection();
        boolean orderAvailable = orderDAO.ifExistsOrderId(dto.getOrderId());

        if (orderAvailable) {
            return false;
        }

        connection.setAutoCommit(false);
        Orders order = new Orders(dto.getOrderId(),dto.getcId(),dto.getOrderDate());
        boolean orderAdded = orderDAO.add(order);
        if (!orderAdded) {
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }

        for (OrderDetailDTO detail : dto.getItems()) {
            OrderDetail orderDetail = new OrderDetail(detail.getItemCode(),dto.getOrderId(), detail.getQty(), detail.getUnitPrice());
            boolean orderDetailsAdded = orderDetailsDAO.add(orderDetail);
            if (!orderDetailsAdded) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
            //if every thing ok
            connection.commit();
            connection.setAutoCommit(true);
            return true;

        }
        return orderAvailable;
    }

    @Override
    public String getOrderIds() throws SQLException, ClassNotFoundException {
        return orderDAO.getOrderIds();
    }

    @Override
    public ItemDTO searchItems(String itemCode) throws SQLException, ClassNotFoundException {
        Item item = itemDAO.search(itemCode);
        return new ItemDTO(itemCode,item.getDescription(),item.getPackSize(),item.getUnitPrice(),item.getQtyOnHand());
    }

    @Override
    public CustomerDTO searchCustomers(String customerId) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.search(customerId);
        return new CustomerDTO(customerId,customer.getCustTittle(),customer.getCustName(),customer.getCustAddress(),customer.getCity(),customer.getProvince(),customer.getPostalCode());
    }

    @Override
    public List<String> getAllItemIds() throws SQLException, ClassNotFoundException {
        return itemDAO.getAllItemIds();
    }

    @Override
    public List<String> getAllCustomerIds() throws SQLException, ClassNotFoundException {
       return customerDAO.getAllCustomerIds();
    }

    @Override
    public int orders() throws SQLException, ClassNotFoundException {
        return orderDAO.orders();
    }
}
