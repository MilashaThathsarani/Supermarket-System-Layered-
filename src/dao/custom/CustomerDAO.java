package dao.custom;

import dao.CrudDAO;
import entity.Customer;

import java.sql.SQLException;
import java.util.List;


public interface CustomerDAO extends CrudDAO<Customer,String> {
    public int customers() throws SQLException, ClassNotFoundException;
    public String getCustomerIds() throws SQLException,ClassNotFoundException;
    public List getAllCustomerIds() throws SQLException,ClassNotFoundException;
}
