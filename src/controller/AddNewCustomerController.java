package controller;

import View.tm.CustomerTm;
import bo.BoFactory;
import bo.custom.CustomerBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import dto.CustomerDTO;
import validation.ValidationUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

import static controller.MakeCustomerPlaceOrderController.customerDTOList;

public class AddNewCustomerController {
    private final CustomerBO customerBO = (CustomerBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.CUSTOMER);
    public AnchorPane addNewCustomerContext;
    public JFXTextField txtCustomerTittle;
    public JFXTextField CustomerName;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCity;
    public JFXTextField txtPostalCode;
    public JFXTextField txtProvince;
    public TableView<CustomerTm> lblCustomerDetails;
    public TableColumn colId;
    public TableColumn colTittle;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colCity;
    public TableColumn colProvince;
    public TableColumn colPostalCode;
    public TextField txtCustomerId;
    public JFXTextField txtScearchCustomer;
    public JFXButton btnAddCustomer;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    Pattern customerIDPattern = Pattern.compile("^(C)[0-9]{3,4}$");
    Pattern titlePattern = Pattern.compile("^(Miss|Mrs|Mr)(.)?$");
    Pattern namePattern = Pattern.compile("^[A-z ]{2,}$");
    Pattern addressPattern = Pattern.compile("^[A-z ]{3,30}([0-9]{1,2})?$");
    Pattern cityPattern = Pattern.compile("^[A-z ]{3,30}([0-9]{1,2})?$");
    Pattern provincePattern = Pattern.compile("^[A-z ]{2,}$");
    Pattern postalCodePattern =Pattern.compile("^[1-9][0-9]*$");

    static ArrayList<CustomerDTO> customerDTOArrayList = customerDTOList;

    public void initialize() throws SQLException, ClassNotFoundException {
        
        storeValidation();

        colId.setCellValueFactory(new PropertyValueFactory<>("custId"));
        colTittle.setCellValueFactory(new PropertyValueFactory<>("custTittle"));
        colName.setCellValueFactory(new PropertyValueFactory<>("custName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("custAddress"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        setItemsToTable(customerBO.getAll());

        setCustomerId();
    }

    private void storeValidation() {
        map.put(txtCustomerTittle, titlePattern);
        map.put(CustomerName, namePattern);
        map.put(txtCustomerAddress, addressPattern);
        map.put(txtCity, cityPattern);
        map.put(txtProvince, provincePattern);
        map.put(txtPostalCode, postalCodePattern);
    }

    public void closeStage(AnchorPane makeCustomerOrdersContext) {
        this.addNewCustomerContext = addNewCustomerContext;
    }

    private void setCustomerId() throws SQLException, ClassNotFoundException {
        txtCustomerId.setText(customerBO.getCustomerIds());
    }

    private void setItemsToTable(ArrayList<CustomerTm> customer) {
        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();
        customer.forEach(e -> {
            obList.add(
                    new CustomerTm(e.getCustId(),e.getCustTittle(),e.getCustName(),e.getCustAddress(),e.getCity(),e.getProvince(),e.getPostalCode()));
        });
        lblCustomerDetails.setItems(obList);
    }

    public void selectCustomer(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String custId = txtScearchCustomer.getText();

        CustomerDTO customerDTO= customerBO.searchCustomer(custId);
        if (customerDTO==null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(customerDTO);
        }

    }

    private void setData(CustomerDTO c) {
        txtCustomerId.setText(c.getCustId());
        txtCustomerTittle.setText(c.getCustTittle());
        CustomerName.setText(c.getCustName());
        txtCustomerAddress.setText(c.getCustAddress());
        txtCity.setText(c.getCity());
        txtProvince.setText(c.getProvince());
        txtPostalCode.setText(c.getPostalCode());
    }

    public void addOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtCustomerId.getText();
        String tittle = txtCustomerTittle.getText();
        String name = CustomerName.getText();
        String address = txtCustomerAddress.getText();
        String city = txtCity.getText();
        String province = txtProvince.getText();
        String postalCode = txtPostalCode.getText();

        CustomerDTO customerDTO = new CustomerDTO(id, tittle, name, address, city, province, postalCode);

        if (customerBO.addCustomer(customerDTO)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
            setItemsToTable(customerBO.getAll());
            setCustomerId();
            clearText();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();

        }
    }

    private void clearText() {
        CustomerName.setText("");
        txtCustomerAddress.setText("");
        txtCustomerTittle.setText("");
        txtCity.setText("");
        txtProvince.setText("");
        txtPostalCode.setText("");
    }

    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id= txtCustomerId.getText();
        String tittle = txtCustomerTittle.getText();
        String name = CustomerName.getText();
        String address = txtCustomerAddress.getText();
        String city = txtCity.getText();
        String province = txtProvince.getText();
        String postalCode = txtPostalCode.getText();

        CustomerDTO customerDTO = new CustomerDTO(id,tittle,name,address,city,province,postalCode);

            if (customerBO.updateCustomer(customerDTO)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
                setItemsToTable(customerBO.getAll());
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();

            }
    }

    public void DeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (customerBO.deleteCustomer(txtCustomerId.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
            setItemsToTable(customerBO.getAll());
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void searchOnAction(ActionEvent actionEvent) {
    }

    public void textFieldsKeyReleased(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnAddCustomer);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            }
        }
    }
}
