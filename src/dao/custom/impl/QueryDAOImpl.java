package dao.custom.impl;

import View.tm.OrderTm;
import dao.CrudUtil;
import dao.custom.QueryDAO;
import db.DbConnection;
import dto.CustomDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public ArrayList<OrderTm> getAllOrder() throws SQLException, ClassNotFoundException {
        ArrayList<OrderTm> list = new ArrayList();
        ResultSet rst = CrudUtil.executeQuery("SELECT c.custId, c.custName,c.custAddress, c.city,c.province,c.postalCode, o.ordersId, o.orderDate FROM  Customer c JOIN Orders o ON o.cId=c.custId");
        while (rst.next()) {
            list.add(
                    new OrderTm(rst.getString("custId"),
                            rst.getString("custName"),
                            rst.getString("custAddress"),
                            rst.getString("city"),
                            rst.getString("province"),
                            rst.getString("postalCode"),
                            rst.getString("ordersId"),
                            rst.getString("orderDate")
                    ));
        }
        return list;
    }
}
