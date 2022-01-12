package bo.custom;

import View.tm.CustomerTm;
import bo.SuperBO;
import dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    String getCustomerIds() throws SQLException, ClassNotFoundException;

    CustomerDTO searchCustomer(String custId) throws SQLException, ClassNotFoundException;

    boolean addCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;

    ArrayList<CustomerTm> getAll() throws SQLException, ClassNotFoundException;

   int customers() throws SQLException, ClassNotFoundException;
}
