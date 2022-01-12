package bo.custom.impl;

import View.tm.ItemTm;
import bo.custom.ItemBO;
import dao.DAOFactory;
import dao.custom.ItemDAO;
import dto.ItemDTO;
import entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {

    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public String getItemIds() throws SQLException, ClassNotFoundException {
        return itemDAO.getItemIds();
    }

    @Override
    public ItemDTO searchItem(String itemId) throws SQLException, ClassNotFoundException {
        Item item = itemDAO.search(itemId);
        return new ItemDTO(item.getItemCode(),item.getDescription(),item.getPackSize(),item.getUnitPrice(),item.getQtyOnHand());
    }

    @Override
    public boolean addItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.add(new Item(itemDTO.getItemCode(),itemDTO.getDescription(),itemDTO.getPackSize(),itemDTO.getUnitPrice(),itemDTO.getQtyOnHand()));
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(itemDTO.getItemCode(),itemDTO.getDescription(),itemDTO.getPackSize(),itemDTO.getUnitPrice(),itemDTO.getQtyOnHand()));
    };

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(id);
    }

    @Override
    public ArrayList<ItemTm> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Item> all = itemDAO.getAll();
        ArrayList<ItemTm> allItems = new ArrayList<>();
        for (Item item : all) {
            allItems.add(new ItemTm(item.getItemCode(),item.getDescription(),item.getPackSize(),item.getUnitPrice(),item.getQtyOnHand()));
        }
        return allItems;
    }

    @Override
    public int items() throws SQLException, ClassNotFoundException {
        return itemDAO.items();
    }
}
