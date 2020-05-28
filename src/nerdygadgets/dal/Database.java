package nerdygadgets.dal;

import nerdygadgets.dal.entities.Customer;
import nerdygadgets.dal.entities.Order;
import nerdygadgets.dal.entities.Orderline;
import nerdygadgets.dal.entities.Stock;
import nerdygadgets.shared.Utility;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Database {
    private String url = "jdbc:mysql://localhost:3306/wideworldimporters?&serverTimezone=Europe/Amsterdam";
    private String username = "root";
    private String password = "";

    /**
     * Initializes a new instance of the Database class.
     * 
     * @exception ClassNotFoundException
     */
    public Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            Utility.handleUnexpectedException(e);
        }
    }

    /**
     * Get all orders from the database
     * 
     * @return ArrayList with all orders
     * @exception SQLException
     * @exception NullPointerException
     */
    public ArrayList<Order> getOrders() {
        ArrayList<Order> orders = new ArrayList<>();
        try(Connection connection = openConnection()) {
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
    public Order getOrderByID(int id) {
        Order order = null; 
        try (Connection connection = openConnection()) {
            
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
     * Get orderlines by id from the database
     * @param id OrderID
     * @return ArrayList with all orderlines
     * @exception SQLException
     */
    public ArrayList<Orderline> getOrderlinesByID(int id) {
        ArrayList<Orderline> orderlines = new ArrayList<>();
        try (Connection connection = openConnection()) {
            if(connection != null) {
                try (PreparedStatement statement = connection.prepareStatement("SELECT L.OrderLineID, L.OrderID, L.StockItemID, I.StockItemName, L.Quantity, L.UnitPrice, L.TaxRate FROM orderlines L\n" +
                        "JOIN stockitems I ON I.StockItemID = L.StockItemID\n" +
                        "WHERE OrderID = ?;")) {
                    statement.setInt(1, id);

                    try (ResultSet rs = statement.executeQuery()) {
                        while (rs.next()) {
                            orderlines.add(
                                new Orderline(
                                    rs.getInt("OrderLineID"),
                                    rs.getInt("OrderID"),
                                    rs.getInt("StockItemID"),
                                    rs.getString("StockItemName"),
                                    rs.getInt("Quantity"),
                                    rs.getDouble("UnitPrice"),
                                    rs.getDouble("TaxRate")
                            ));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            Utility.handleUnexpectedException(e);
        }

        return orderlines;
    }

    /**
     * Get customer by id from the database
     * 
     * @param id Customer id
     * @return Customer object
     * @exception SQLException
     * @exception NullPointerException
     */
    public Customer getCustomerByID(int id) {
        Customer customer = null;
        try(Connection connection = openConnection()) {
            if(connection != null) {
                try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM customers WHERE CustomerID = ?;")) {
                    statement.setInt(1, id);

                    try (ResultSet rs = statement.executeQuery()) {
                        if(rs.next()) {
                            customer = new Customer(
                                    rs.getInt("CustomerID"), 
                                    rs.getString("CustomerName"),
                                    rs.getString("PhoneNumber"), 
                                    rs.getString("DeliveryAddressLine1"),
                                    rs.getString("DeliveryAddressLine2"), 
                                    rs.getString("DeliveryPostalCode"),
                                    rs.getDate("AccountOpenedDate")
                                );
                        }
                    }
                }
            } else {
                throw new NullPointerException("connection");
            }
        } catch (Exception e) {
            Utility.handleUnexpectedException(e);
        }

        return customer;
    }

    /**
     * Update order by id
     *
     * @param id Order id
     * @param expectedDeliveryDate Order expectedDeliveryDate
     * @exception Exception
     * @exception NullPointerException
     */
    public void updateOrderByID(int id, Date expectedDeliveryDate) {
        try(Connection connection = openConnection()) {
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

    public boolean updateCustomerByID(int id, String DeliveryAddressLine1, String DeliveryAddressLine2, String DeliveryPostalCode){
        try (Connection connection = openConnection()) {
            if(connection != null) {
                try (PreparedStatement statement = connection.prepareStatement("UPDATE customers SET DeliveryAddressLine1 = ?, DeliveryAddressLine2 = ?, DeliveryPostalCode = ? WHERE CustomerID = ?;")){
                   
                    statement.setString(1, DeliveryAddressLine1);
                    statement.setString(2, DeliveryAddressLine2);
                    statement.setString(3, DeliveryPostalCode);
                    statement.setInt(4, id);

                    statement.executeUpdate();
                }
                return true;
            }
            else {
                throw new NullPointerException("connection");
            }
        } catch (Exception e) {
            Utility.handleUnexpectedException(e);
        }

        return false;
    }




    /**
     * Update orderline by id
     *
     * @param id Orderline id
     * @param quantity Orderline quantity
     * @param unitPrice Orderline unitPrice
     * @param taxRate Orderline taxRate
     * @exception Exception
     * @exception NullPointerException
     */
    public void updateOrderlineByID(int id, int quantity, Double unitPrice, Double taxRate) {
        try(Connection connection = openConnection()) {
            if(connection != null) {
                try (PreparedStatement statement = connection.prepareStatement("UPDATE orderlines SET Quantity = ?, UnitPrice = ?, TaxRate = ? WHERE OrderLineID = ?")) {
                    statement.setInt(1, quantity);
                    statement.setDouble(2, unitPrice);
                    statement.setDouble(3, taxRate);
                    statement.setInt(4, id);
                    statement.executeUpdate();
                }
            } else {
                throw new NullPointerException("connection");
            }
        } catch (Exception e) {
            Utility.handleUnexpectedException(e);
        }
    }

    public List<Stock> getStock() {
        ArrayList<Stock> stock = new ArrayList<>();
        try (Connection connection = openConnection()) {
            if (connection != null) {
                try (Statement statement = connection.createStatement()) {
                    try (ResultSet result = statement
                            .executeQuery("select  i.StockItemID, s.StockItemName, i.QuantityOnHand from stockitemholdings i left join stockitems s on s.StockItemID = i.StockItemID;");
                    ) {
                        while (result.next()) {
                            stock.add(new Stock(result.getInt("StockItemId"), result.getInt("QuantityOnHand"), result.getString("StockItemName")));
                        }
                    }

                }
            } else {
                throw new NullPointerException("connection");
            }

        } catch (SQLException e) {
            Utility.handleUnexpectedException(e);
        }
        return stock;
    }

    /**
     * Opens a fresh connection to the database.
     * 
     * @exception Exception
     */
    private Connection openConnection() {
        try {
            Properties props = new Properties();
            props.setProperty("user", username);
            props.setProperty("password", password);
            
            return DriverManager.getConnection(url, props);            
        } catch (SQLException e) {
            Utility.handleUnexpectedException(e);
        } 

        return null;
    }

}