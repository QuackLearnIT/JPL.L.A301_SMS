package fa.training.main;

import fa.training.dao.*;
import fa.training.entities.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static fa.training.utils.DBUtils.getConnection;

public class SaleManagement {
    public static void main(String[] args) {
        CustomerDAO customerDAO = new CustomerDAOImpl();
        OrderDAO orderDAO = new OrderDAOImpl();
        LineItemDAO lineItemDAO = new LineItemDAOImpl();

        try (Connection con = getConnection()) {
            Scanner sc = new Scanner(System.in);
            menu();
            while (true) {
                System.out.print("Your choice: ");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("=====Get all customers=====");
                        List<Customer> customerList = customerDAO.getAllCustomer();
                        customerList.forEach(System.out::println);
                        break;
                    case 2:
                        System.out.println("=====Get all orders by customer's ID=====");
                        System.out.println("Enter customer ID: ");
                        int customerId = sc.nextInt();
                        List<Order> orderList = customerDAO.getAllOrdersByCustomerId(customerId);
                        orderList.forEach(System.out::println);
                        break;
                    case 3:
                        System.out.println("=====Get all items by order's ID=====");
                        System.out.println("Enter order ID: ");
                        int orderId = sc.nextInt();
                        List<LineItem> lineItemList = orderDAO.getAllItemsByOrderId(orderId);
                        lineItemList.forEach(System.out::println);
                        break;
                    case 4:
                        System.out.println("=====Compute order total=====");
                        System.out.println("Enter order ID to compute: ");
                        int orderIdTocompute = sc.nextInt();
                        System.out.println(orderDAO.computeOrderTotal(orderIdTocompute));
                        break;
                    case 5:
                        System.out.println("=====Add customer=====");

                        System.out.println("Enter new customer ID: ");
                        int addCustomerId = sc.nextInt();

                        sc = new Scanner(System.in);
                        System.out.println("Enter new customer name: ");
                        String addCustomerName = sc.nextLine();

                        Customer newCustomer = new Customer(addCustomerId, addCustomerName);
                        customerDAO.addCustomer(newCustomer);

                        System.out.println("=====Add success=====");
                        break;
                    case 6:
                        System.out.println("=====Delete customer=====");
                        System.out.println("Enter customer ID: ");
                        int deleteCustomerId = sc.nextInt();
                        customerDAO.deleteCustomer(deleteCustomerId);
                        System.out.println("=====Delete success=====");
                        break;
                    case 7:
                        System.out.println("=====Update Customer=====");

                        sc = new Scanner(System.in);
                        System.out.println("Enter customer name you want to set new: ");
                        String updateCustomerName = sc.nextLine();

                        sc = new Scanner(System.in);
                        System.out.println("Enter customer id to update: ");
                        int updateCustomerId = sc.nextInt();

                        Customer updateCustomer = new Customer(updateCustomerId, updateCustomerName);
                        customerDAO.updateCustomer(updateCustomer);

                        System.out.println("=====Update success=====");
                        break;
                    case 8:
                        System.out.println("=====Add order=====");

                        sc = new Scanner(System.in);
                        System.out.println("Enter new order ID: ");
                        int addOrderId = sc.nextInt();

                        sc = new Scanner(System.in);
                        System.out.println("Enter new date: ");
                        Date addOrderDate = Date.valueOf(sc.nextLine());

                        sc = new Scanner(System.in);
                        System.out.println("Enter employee ID: ");
                        int addEmployeeId = sc.nextInt();

                        sc = new Scanner(System.in);
                        System.out.println("Enter customer ID: ");
                        int addNewCustomerId = sc.nextInt();

                        sc = new Scanner(System.in);
                        System.out.println("Enter new total: ");
                        Double addTotal = sc.nextDouble();

                        Order addOrder = new Order(addOrderId, addOrderDate, addEmployeeId, addNewCustomerId, addTotal);
                        orderDAO.addOrder(addOrder);

                        System.out.println("=====Add success=====");
                        break;
                    case 9:
                        System.out.println("=====Add LineItem=====");

                        sc = new Scanner(System.in);
                        System.out.println("Enter order ID: ");
                        int addOrderID = sc.nextInt();

                        sc = new Scanner(System.in);
                        System.out.println("Enter product ID: ");
                        int addProductID = sc.nextInt();

                        sc = new Scanner(System.in);
                        System.out.println("Enter quantity: ");
                        int addQuantity = sc.nextInt();

                        sc = new Scanner(System.in);
                        System.out.println("Enter price: ");
                        Double addPrice = sc.nextDouble();

                        LineItem addLineItem = new LineItem(addOrderID, addProductID, addQuantity, addPrice);
                        lineItemDAO.addLineItem(addLineItem);

                        System.out.println("=====Add success=====");
                        break;
                    case 10:
                        System.out.println("=====Update order=====");

                        sc = new Scanner(System.in);
                        System.out.println("Enter order ID you want to update total: ");
                        int updatedOrder = sc.nextInt();

                        System.out.println("Update total: " + orderDAO.updateOrderTotal(updatedOrder));
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
