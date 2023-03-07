package fa.training.main;

import fa.training.dao.CustomerDAOImpl;
import fa.training.dao.LineItemDAOImpl;
import fa.training.dao.OrderDAOImpl;
import fa.training.entities.Customer;
import fa.training.entities.LineItem;
import fa.training.entities.Order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static fa.training.utils.DBUtils.getConnection;

public class SaleManagement {
    public static void main(String[] args) {
        CustomerDAOImpl customerDAO = new CustomerDAOImpl();
        OrderDAOImpl orderDAO = new OrderDAOImpl();
        LineItemDAOImpl lineItemDAO = new LineItemDAOImpl();

        try (Connection con = getConnection()) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery ("SELECT * FROM Customer");
            System.out.println("Connection succeeded!!");

            Scanner sc = new Scanner(System.in);

            while (true) {
                menu();
                System.out.print("Your choice: ");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        customerDAO.getAllCustomer();
                        break;
                    case 2:
                        int customerId = sc.nextInt();
                        orderDAO.getAllOrdersByCustomerId(customerId);
                        break;
                    case 3:
                        int orderId = sc.nextInt();
                        lineItemDAO.getAllItemsByOrderId(orderId);
                        break;
                    case 4:
                        int orderIdTocompute = sc.nextInt();
                        lineItemDAO.computeOrderTotal(orderIdTocompute);
                        break;
                    case 5:
                        Customer addCustomerId = new Customer();
                        customerDAO.addCustomer(addCustomerId);
                        break;
                    case 6:
                        int deleteCustomerId = sc.nextInt();
                        customerDAO.deleteCustomer(deleteCustomerId);
                        break;
                    case 7:
                        Customer updateCustomer = new Customer();
                        customerDAO.updateCustomer(updateCustomer);
                        break;
                    case 8:
                        Order addOrder = new Order();
                        orderDAO.addOrder(addOrder);
                        break;
                    case 9:
                        LineItem addLineItem = new LineItem();
                        lineItemDAO.addLineItem(addLineItem);
                        break;
                    case 10:
                        int updateOrder = sc.nextInt();
                        orderDAO.updateOrderTotal(updateOrder);
                        break;
                    case 11:
                        System.out.println("Exit!!");
                        System.exit(0);
                        break;
                    default:
                        break;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void menu() {
        System.out.println("=====Menu=====");
        System.out.println("1. List all customers in order table.");
        System.out.println("2. Get all orders by customer id.");
        System.out.println("3. List all lineitems for an order.");
        System.out.println("4. Compute order total.");
        System.out.println("5. Add customer to database.");
        System.out.println("6. Delete a customer from database.");
        System.out.println("7. Update a customer status in database.");
        System.out.println("8. Create an order into the database.");
        System.out.println("9. Create a lineitem into the database.");
        System.out.println("10. Update an order total into the database.");
        System.out.println("11. Exit");
    }
}
