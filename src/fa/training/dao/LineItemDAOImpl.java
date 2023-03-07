package fa.training.dao;

import fa.training.entities.LineItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LineItemDAOImpl implements LineItemDAO {

    @Override
    public List<LineItem> getAllItemsByOrderId(int orderId) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<LineItem> lineItems = new ArrayList<>();

        try {
            String sql = "SELECT * FROM line_items WHERE order_id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, orderId);

            // Execute the statement and retrieve the results
            rs = stmt.executeQuery();

            // Iterate over the results and create LineItem objects
            while (rs.next()) {
                int id = rs.getInt("id");
                int productId = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                LineItem lineItem = new LineItem(orderId, productId, quantity, price);
                lineItems.add(lineItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
        return lineItems;
    }

    @Override
    public Double computeOrderTotal(int orderId) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double totalPrice = 0.0;
        try {
            String sql = "SELECT dbo.ComputeOrderTotal(?) AS total_price";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, orderId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                totalPrice = rs.getDouble("total_price");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
        return totalPrice;
    }

    @Override
    public boolean addLineItem(LineItem item) {
        Connection con = null;
        PreparedStatement stmt = null;
        boolean success = false;
        try {
            String sql = "INSERT INTO lineitems (order_id, product_id, quantity, price) " +
                    "VALUES (?, ?, ?, ?)";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, item.getOrderId());
            stmt.setInt(2, item.getProductId());
            stmt.setInt(3, item.getQuantity());
            stmt.setDouble(4, item.getPrice());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the statement and connection
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
