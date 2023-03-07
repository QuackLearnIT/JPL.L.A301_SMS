package fa.training.dao;

import fa.training.entities.Customer;
import fa.training.entities.Order;

import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public List<Order> getAllOrdersByCustomerId(int customerId) {

        return null;
    }

    @Override
    public boolean addOrder(Order order) {
        return false;
    }

    @Override
    public boolean updateOrderTotal(int orderId) {
        return false;
    }
}
