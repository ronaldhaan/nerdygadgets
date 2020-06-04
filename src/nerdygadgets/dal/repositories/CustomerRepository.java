package nerdygadgets.dal.repositories;

import java.sql.*;
import nerdygadgets.dal.entities.Customer;
import nerdygadgets.dal.Database;

import nerdygadgets.shared.Utility;

public class CustomerRepository extends Repository {

    public CustomerRepository() {
        this(new Database());
    }

    public CustomerRepository(Database connection) {
        super(connection);
    }

    /**
     * Get customer by id from the database
     * 
     * @param id Customer id
     * @return Customer object
     * @exception SQLException
     * @exception NullPointerException
     */
    public Customer getOne(int id) {
        Customer customer = null;
        try(Connection connection = getConnection().open()) {
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
    

    public boolean update(int id, String DeliveryAddressLine1, String DeliveryAddressLine2, String DeliveryPostalCode){
        try (Connection connection =  getConnection().open()) {
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

}