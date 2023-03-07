package fa.training.main;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import fa.training.utils.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static fa.training.utils.DBUtils.getConnection;

public class SaleManagement {
    public static void main(String[] args) {

        try (Connection con = DBUtils.getConnection()) {
            Statement statement = con.createStatement();

            ResultSet rs = statement.executeQuery ("SELECT * FROM Customer");
            System.out.println("Connection succeeded");


            Scanner sc = new Scanner(System.in);

            while (true) {
                menu();
                int choice = sc.nextInt();
                System.out.print("Your choice: ");
                switch (choice) {
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                    case 6:

                        break;
                    case 7:

                        break;
                    case 8:

                        break;
                    case 9:

                        break;
                    case 10:

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
