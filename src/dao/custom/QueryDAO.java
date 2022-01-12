package dao.custom;

import View.tm.OrderTm;
import dao.SuperDAO;
import dto.CustomDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    ArrayList<OrderTm> getAllOrder() throws SQLException, ClassNotFoundException;
}
