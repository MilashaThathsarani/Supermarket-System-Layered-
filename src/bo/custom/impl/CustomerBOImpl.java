package bo.custom.impl;

import View.tm.CustomerTm;
import bo.custom.CustomerBO;
import dao.DAOFactory;
import dao.custom.CustomerDAO;
import dto.CustomerDTO;
import entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public String getCustomerIds() throws SQLException, ClassNotFoundException {
        return customerDAO.getCustomerIds();
    }

    @Override
    public CustomerDTO searchCustomer(String custId) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.search(custId);
        return new CustomerDTO(customer.getCustId(), customer.getCustTittle(), customer.getCustName(), customer.getCustAddress(), customer.getCity(), customer.getProvince(), customer.getPostalCode());
    }

    @Override
    public boolean addCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.add(new Customer(customerDTO.getCustId(), customerDTO.getCustTittle(), customerDTO.getCustName(), customerDTO.getCustAddress(), customerDTO.getCity(), customerDTO.getProvince(), customerDTO.getPostalCode()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(customerDTO.getCustId(), customerDTO.getCustTittle(), customerDTO.getCustName(), customerDTO.getCustAddress(), customerDTO.getCity(), customerDTO.getProvince(), customerDTO.getPostalCode()));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public ArrayList<CustomerTm> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> all = customerDAO.getAll();
        ArrayList<CustomerTm> allCustomers = new ArrayList<>();
        for (Customer customer : all) {
            allCustomers.add(new CustomerTm(customer.getCustId(),customer.getCustTittle(),customer.getCustName(),customer.getCustAddress(),customer.getCity(),customer.getProvince(),customer.getPostalCode()));
        }
        return allCustomers;
    }

    @Override
    public int customers() throws SQLException, ClassNotFoundException {
       return customerDAO.customers();
    }
}
