package fa.training.dao;

import fa.training.entities.Customer;
import fa.training.entities.LineItem;
import fa.training.entities.Order;

import java.util.List;

public interface OrderDAO {
    List<LineItem> getAllItemsByOrderId(int orderId);
    Double computeOrderTotal(int orderId);
    boolean addOrder(Order order);
    boolean updateOrderTotal(int orderId);
}
