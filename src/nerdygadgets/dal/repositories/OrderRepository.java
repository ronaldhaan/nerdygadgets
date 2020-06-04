package nerdygadgets.dal.repositories;

import java.sql.*;
import java.util.ArrayList;

import nerdygadgets.dal.Database;
import nerdygadgets.dal.entities.Order;
import nerdygadgets.shared.Utility;

public class OrderRepository extends Repository {

    public OrderRepository() {
        this(new Database());
    }

    public OrderRepository(Database database) {
        super(database);
    }

    /**
     * Get all orders from the database
     * 
     * @return ArrayList with all orders
     * @exception SQLException
     * @exception NullPointerException
     */
    public ArrayList<Order> getAll() {
        ArrayList<Order> orders = new ArrayList<>();
        try(Connection connection = getConnection().open()) {
            if(connection != null) {                    
                try (Statement statement = connection.createStatement()) {
                    String query = "SELECT OrderID, CustomerID, OrderDate " +
                                    "FROM `orders` " 
                                    + "WHERE OrderDate > \"2020-01-01\";";
                    try (ResultSet rs = statement.executeQuery(query)) {

                        while (rs.next()) {
                            orders.add(new Order(rs.getInt("OrderID"), rs.getInt("CustomerID"), rs.getDate("OrderDate")));
                        }
                    }
                }
            } else {
                throw new NullPointerException("connection");
            }
        } catch (Exception e) {
            Utility.handleUnexpectedException(e);
        }

        return orders;
    }

    /**
     * Get order by id from the database
     * @param id OrderID
     * @return Order object
     * @exception SQLException
     */
    public Order getOne(int id) {
        Order order = null; 
        try (Connection connection = getConnection().open()) {
            
            if(connection != null) {
                try(PreparedStatement statement = connection
                                                    .prepareStatement("SELECT OrderID, CustomerID, OrderDate, " + 
                                                                        "ExpectedDeliveryDate FROM orders WHERE OrderID = ?"))
                {
                    statement.setInt(1, id);
                    try (ResultSet rs = statement.executeQuery()) {
                        rs.next();
 
                        order = new Order(
                                rs.getInt("OrderID"),
                                rs.getInt("CustomerID"),
                                rs.getDate("OrderDate"),
                                rs.getDate("ExpectedDeliveryDate")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            Utility.handleUnexpectedException(e);
        }

        return order;
    }
    
    /**
     * Update order by id
     *
     * @param id Order id
     * @param expectedDeliveryDate Order expectedDeliveryDate
     * @exception Exception
     * @exception NullPointerException
     */
    public void update(int id, Date expectedDeliveryDate) {
        try(Connection connection = getConnection().open()) {
            if(connection != null) {
                try (PreparedStatement statement = connection.prepareStatement("UPDATE orders SET ExpectedDeliveryDate = ? WHERE OrderID = ?;")) {
                    statement.setDate(1, expectedDeliveryDate);
                    statement.setInt(2, id);
                    statement.executeUpdate();
                }
            } else {
                throw new NullPointerException("connection");
            }
        } catch (Exception e) {
            Utility.handleUnexpectedException(e);
        }
    }
}