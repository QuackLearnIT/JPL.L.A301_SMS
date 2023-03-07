package fa.training.dao;

import fa.training.entities.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public List<Order> getAllOrdersByCustomerId(int customerId) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Order> orders = new ArrayList<>();
        try {
            String sql = "SELECT id, order_date, customer_id, employee_id, total " +
                    "FROM orders " +
                    "WHERE customer_id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, customerId);

            // Execute the statement and process the results
            rs = stmt.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt("id");
                Date orderDate = rs.getDate("order_date");
                int customerIdResult = rs.getInt("customer_id");
                int employeeId = rs.getInt("employee_id");
                double total = rs.getDouble("total");

                Order order = new Order(orderId, orderDate, customerIdResult, employeeId, total);
                orders.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the result set, statement, and connection
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return orders;
    }

    @Override
    public boolean addOrder(Order order) {
        Connection con = null;
        PreparedStatement stmt = null;
        boolean success = false;
        try {
            String sql = "INSERT INTO orders (order_date, customer_id, employee_id, total) " +
                    "VALUES (?, ?, ?, ?)";
            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setDate(1, new Date(order.getOrderDate().getTime()));
            stmt.setInt(2, order.getCustomerId());
            stmt.setInt(3, order.getEmployeeId());
            stmt.setDouble(4, order.getTotal());

            int numRowsAffected = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int orderId = rs.getInt(1);
                order.setOrderId(orderId);
            }
            con.commit();
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return success;
    }

    @Override
    public boolean updateOrderTotal(int orderId) {
        Connection con = null;
        PreparedStatement stmt = null;
        boolean success = false;
        try {
            String sql = "UPDATE orders SET total = ? WHERE id = ?";
            stmt = con.prepareStatement(sql);
            double newTotal = 0;
            stmt.setDouble(1, newTotal);
            stmt.setInt(2, orderId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 1) {
                success = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return success;
    }
}
