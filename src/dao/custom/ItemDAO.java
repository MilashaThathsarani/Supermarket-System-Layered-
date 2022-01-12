package dao.custom;

import dao.CrudDAO;
import dto.ItemDTO;
import entity.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemDAO extends CrudDAO<Item,String> {
    public int items() throws SQLException, ClassNotFoundException;
    public String getItemIds() throws SQLException,ClassNotFoundException;
    public List getAllItemIds() throws SQLException,ClassNotFoundException;
    }


