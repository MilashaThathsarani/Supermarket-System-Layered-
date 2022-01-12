package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.OrderDAO;
import dto.OrdersDTO;
import entity.Orders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean add(Orders orders) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO `Orders` VALUES(?,?,?)", orders.getOrdersId(), orders.getcId(), orders.getOrderDate());
    //return saveOrderDetail(ordersDTO.getOrderId(), ordersDTO.getItems());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public boolean update(Orders orders) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public Orders search(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public ArrayList<Orders> getAll() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }


    @Override
    public int orders() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT COUNT(*) FROM Orders");
        if (rst.next()) {
            return rst.getInt(1);
        }else {
            return 0;
        }
    }

    @Override
    public String getOrderIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT ordersId FROM `Orders` ORDER BY ordersId DESC LIMIT 1");
        if (rst.next()){
            int tempId = Integer.
                    parseInt(rst.getString("ordersId").split("-")[1]);
            tempId=tempId+1;
            if (tempId<=9){
                return "O-00"+tempId;
            }else if(tempId<=99){
                return "O-0"+tempId;
            }else{
                return "O-"+tempId;
            }

        }else{
            return "O-001";
        }
    }

    @Override
    public boolean ifExistsOrderId(String ordersId) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT ordersId FROM `Orders` WHERE ordersId=?", ordersId);
        return rst.next();
    }

   /* @Override
    public boolean placeOrder(OrdersDTO order) throws SQLException, ClassNotFoundException {
        Connection con = null;
        try {
            con = DbConnection.getInstance().getConnection();
            PreparedStatement stm = con.
                    prepareStatement("INSERT INTO `Orders` VALUES(?,?,?)");

            stm.setObject(1, order.getOrderId());
            stm.setObject(2, order.getcId());
            stm.setObject(3, order.getOrderDate());

            if (stm.executeUpdate() > 0) {
                return saveOrderDetail(order.getOrderId(), order.getItems());

            } else {
                return false;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean saveOrderDetail(String orderId, ArrayList<itemDetailDTO> items) throws SQLException, ClassNotFoundException {
        for (itemDetailDTO temp : items
        ) {
            PreparedStatement stm = DbConnection.getInstance().
                    getConnection().
                    prepareStatement("INSERT INTO `Order Detail` VALUES(?,?,?,?)");
            stm.setObject(1, temp.getCode());
            stm.setObject(2, orderId);
            stm.setObject(3, temp.getOrderQty());
            stm.setObject(4, temp.getDisscount());

            if (stm.executeUpdate() > 0) {

            } else {
                return false;
            }
        }
        return true;

    }*/


   /* @Override
    public List<String> getAllOrderIDs() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Orders");
        List<String> ids= new ArrayList<>();
        while (rst.next()){
            ids.add(
                    rst.getString("ordersId")
            );
        }
        return ids;
    }*/
}


   /* private boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection()
                .prepareStatement
                        ("UPDATE ITEM SET qtyOnHand=(qtyOnHand-" + qty
                                + ") WHERE code='" + itemCode + "'");
        return stm.executeUpdate()>0;
    }*/
