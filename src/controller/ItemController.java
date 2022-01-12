package controller;

import View.tm.CartTm;
import View.tm.ItemTm;
import bo.BoFactory;
import bo.custom.ItemBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.custom.impl.ItemDAOImpl;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import dto.ItemDTO;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import validation.ValidationUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class ItemController {
    private final ItemBO itemBO = (ItemBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.ITEM);
    public TextField txtItemCode;
    public JFXTextField txtDescription;
    public JFXTextField txtPackSize;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtUnitPrice;
    public TableView<ItemTm> tblItem;
    public TableColumn colItemCode;
    public TableColumn colDesccription;
    public TableColumn colPackSize;
    public TableColumn colQtyOnHand;
    public TableColumn colUnitPrice;
    public JFXButton btnAddItem;
    public Label lblDate;
    public Label lblTime;
    public AnchorPane itemContext;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    Pattern descriptionPattern = Pattern.compile("^[A-z ]{2,}$");
    Pattern packSizePattern = Pattern.compile("^[1-9][0-9]*([.][0-9]{2})?$");
    Pattern unitPricePattern = Pattern.compile("^[1-9][0-9]*([.][0-9]{2})?$");
    Pattern qtyOnHandPattern = Pattern.compile("^[1-9][0-9]*$");

    public void initialize() throws SQLException, ClassNotFoundException {
        
        loadDateAndTime();

        storeValidation();

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDesccription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

        setItemsToTable(itemBO.getAll());

        setItemId();
    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(
                    currentTime.getHour() + " : " + currentTime.getMinute() + " : " + currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    private void storeValidation() {
        //map.put(txtItemCode, itemCodePattern);
        map.put(txtDescription, descriptionPattern);
        map.put(txtPackSize, packSizePattern);
        map.put(txtUnitPrice, unitPricePattern);
        map.put(txtQtyOnHand, qtyOnHandPattern);

    }

    private void setItemsToTable(ArrayList<ItemTm> item) {
        ObservableList<ItemTm> obList = FXCollections.observableArrayList();
        item.forEach(e -> {
            obList.add(
                    new ItemTm(e.getItemCode(), e.getDescription(), e.getPackSize(), e.getQtyOnHand(), (int) e.getUnitPrice()));
        });
        tblItem.setItems(obList);
    }

    private void setItemId() throws SQLException, ClassNotFoundException {
        txtItemCode.setText(itemBO.getItemIds());
    }

    public void selectItem(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String itemId = txtItemCode.getText();

        ItemDTO itemDTO= itemBO.searchItem(itemId);
        if (itemDTO==null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(itemDTO);
        }
    }

    private void setData(ItemDTO itemDTO) {
        txtItemCode.setText(itemDTO.getItemCode());
        txtDescription.setText(itemDTO.getDescription());
        txtPackSize.setText(itemDTO.getPackSize());
        txtQtyOnHand.setText(String.valueOf(itemDTO.getQtyOnHand()));
        txtUnitPrice.setText(String.valueOf(itemDTO.getUnitPrice()));
    }

    public void addOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String code = txtItemCode.getText();
        String description = txtDescription.getText();
        String packSize = txtPackSize.getText();
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());

        ItemDTO itemDTO = new ItemDTO(code,description,packSize,unitPrice,qtyOnHand);

            if (itemBO.addItem(itemDTO)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
                setItemsToTable(itemBO.getAll());
                setItemId();
                clearText();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again..").show();
            }
    }

    private void clearText() {
        txtDescription.setText("");
        txtPackSize.setText("");
        txtQtyOnHand.setText("");
        txtUnitPrice.setText("");
    }

    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String code = txtItemCode.getText();
        String description = txtDescription.getText();
        String packSize = txtPackSize.getText();
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());

            ItemDTO itemDTO = new ItemDTO(code,description,packSize,unitPrice,qtyOnHand);

            if (itemBO.updateItem(itemDTO)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
                setItemsToTable(itemBO.getAll());
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();

            }
    }

    public void deleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (itemBO.deleteItem(txtItemCode.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
            setItemsToTable(itemBO.getAll());
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void textFieldsKeyReleased(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnAddItem);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            //} else if (response instanceof Boolean) {
                //new Alert(Alert.AlertType.INFORMATION, "Aded").showAndWait();
            }
        }
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) itemContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/DashBoardAdmin.fxml"))));
        window.centerOnScreen();
    }
}
