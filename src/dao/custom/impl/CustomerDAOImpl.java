package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CustomerDAO;
import entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean add(Customer customer) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO Customer VALUES(?,?,?,?,?,?,?)", customer.getCustId(), customer.getCustTittle(), customer.getCustName(), customer.getCustAddress(), customer.getCity(), customer.getProvince(), customer.getPostalCode()
        );
    }

    @Override
    public boolean delete(String custId) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM Customer WHERE custId=?", custId);
    }

    @Override
    public boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Customer SET custTittle=?, custName=?,custAddress=?, city=?, province=?, postalCode=? WHERE custId=?", customer.getCustTittle(), customer.getCustName(), customer.getCustAddress(), customer.getCity(), customer.getProvince(), customer.getPostalCode(), customer.getCustId()
        );
    }

    @Override
    public Customer search(String custId) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Customer WHERE custId=?", custId);
        if (rst.next()){
            return new Customer(custId, rst.getString("custTittle"), rst.getString("custName"), rst.getString("custAddress"), rst.getString("city"), rst.getString("province"), rst.getString("postalCode")
            );
        }else {
            return null;
        }
    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customer = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Customer");
        while (rst.next()) {
            customer.add(new Customer(rst.getString("custId"), rst.getString("custTittle"), rst.getString("custName"), rst.getString("custAddress"), rst.getString("city"), rst.getString("province"), rst.getString("postalCode")));
        }
        return customer;
    }

    @Override
    public int customers() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT COUNT(*) FROM Customer");
        if (rst.next()) {
            return rst.getInt(1);
        }else {
            return 0;
        }
    }

    @Override
    public String getCustomerIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT custId FROM Customer ORDER BY custId DESC LIMIT 1");
        if (rst.next()) {
            int tempId = Integer.
                    parseInt(rst.getString("custId").split("-")[1]);
            tempId = tempId + 1;
            if (tempId <= 9) {
                return "C-00" + tempId;
            } else if (tempId <= 99) {
                return "C-0" + tempId;
            } else {
                return "C-" + tempId;
            }

        } else {
            return "C-001";
        }
    }

    @Override
    public List getAllCustomerIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Customer");
        List<String> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(
                    rst.getString("custId")
            );
        }
        return ids;
    }
}