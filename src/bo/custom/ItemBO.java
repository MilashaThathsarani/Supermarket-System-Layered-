package bo.custom;

import View.tm.ItemTm;
import bo.SuperBO;
import dto.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    String getItemIds() throws SQLException, ClassNotFoundException;

    ItemDTO searchItem(String itemId) throws SQLException, ClassNotFoundException;

    boolean addItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    boolean deleteItem(String id) throws SQLException, ClassNotFoundException;

    ArrayList<ItemTm> getAll()throws SQLException, ClassNotFoundException;

    int items() throws SQLException, ClassNotFoundException;
}
