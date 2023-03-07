package fa.training.dao;

import fa.training.entities.LineItem;

import java.util.List;

public class LineItemDAOImpl implements LineItemDAO {

    @Override
    public List<LineItem> getAllItemsByOrderId(int orderId) {

        return null;
    }

    @Override
    public Double computeOrderTotal(int orderId) {
        return null;
    }

    @Override
    public boolean addLineItem(LineItem item) {
        return false;
    }
}
