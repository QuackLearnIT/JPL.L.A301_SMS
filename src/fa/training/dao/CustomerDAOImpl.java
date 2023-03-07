package fa.training.dao;

import fa.training.entities.Customer;
import fa.training.entities.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public List<Customer> getAllCustomer() {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Customer> customers = new ArrayList<Customer>();
        try {
            String sql = "SELECT customer_id, customer_name FROM Customer";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int customerId = rs.getInt("customer_id");
                String customerName = rs.getString("customer_name");
                Customer customer = new Customer(customerId, customerName);
                customers.add(customer);
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
        return customers;
    }

    @Override
    public boolean addCustomer(Customer customer) {
        Connection con = null;
        CallableStatement stmt = null;
        boolean success = false;
        try {
            String sql = "{call add_customer(?, ?)}";
            stmt = con.prepareCall(sql);
            stmt.setString(1, customer.getCustomerName());
            stmt.registerOutParameter(2, Types.INTEGER);
            stmt.execute();
            int customerId = stmt.getInt(2);
            if (customerId > 0) {
                customer.setCustomerId(customerId);
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

    @Override
    public boolean deleteCustomer(int customerId) {
        Connection con = null;
        CallableStatement stmt = null;
        boolean success = false;
        try {
            String sql = "{call delete_customer(?)}";
            stmt = con.prepareCall(sql);
            stmt.setInt(1, customerId);
            stmt.execute();
            int rowsAffected = stmt.getUpdateCount();
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

    @Override
    public boolean updateCustomer(Customer customer) {
        Connection con = null;
        CallableStatement stmt = null;
        boolean success = false;
        try {
            String sql = "{call update_customer(?,?)}";
            stmt = con.prepareCall(sql);
            stmt.setInt(1, customer.getCustomerId());
            stmt.setString(2, customer.getCustomerName());
            // Execute the stored procedure
            stmt.execute();
            // Check the result of the stored procedure call
            int rowsAffected = stmt.getUpdateCount();
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
