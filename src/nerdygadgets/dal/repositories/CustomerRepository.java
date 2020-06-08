package nerdygadgets.dal.repositories;

import java.sql.*;
import java.util.ArrayList;

import nerdygadgets.dal.entities.Customer;
import nerdygadgets.dal.Database;

import nerdygadgets.shared.Utility;

public class CustomerRepository extends Repository<Customer> {

    public CustomerRepository() {
        this(new Database());
    }

    public CustomerRepository(Database connection) {
        super(connection);
    }

    @Override
    public ArrayList<Customer> getAll() {
        return new ArrayList<>();
    }

    /**
     * Get customer by id from the database
     * 
     * @param id Customer id
     * @return Customer object
     * @exception SQLException
     * @exception NullPointerException
     */
    @Override
    public Customer getOne(int id) {
        Customer customer = null;
        try (Connection connection = getConnection().open()) {
            String query = "SELECT * FROM customers WHERE CustomerID = ?;";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);

                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        customer = new Customer(rs.getInt("CustomerID"), rs.getString("CustomerName"),
                                rs.getString("PhoneNumber"), rs.getString("DeliveryAddressLine1"),
                                rs.getString("DeliveryAddressLine2"), rs.getString("DeliveryPostalCode"),
                                rs.getDate("AccountOpenedDate"));
                    }
                }
            }
        } catch (Exception e) {
            Utility.handleUnexpectedException(e);
        }

        return customer;
    }


    public boolean update(int id, Customer entity) {
        if(entity != null && id == entity.getId()) {
            try (Connection connection = getConnection().open()) {
                String query = "UPDATE customers" +
                                "SET DeliveryAddressLine1 = ?," +
                                    "DeliveryAddressLine2 = ?," +
                                    "DeliveryPostalCode = ? " +
                                "WHERE CustomerID = ?;";

                try (PreparedStatement statement = connection.prepareStatement(query)) {    
                    statement.setString(1, entity.getDeliveryAddressLine1());
                    statement.setString(2, entity.getDeliveryAddressLine2());
                    statement.setString(3, entity.getDeliveryPostalCode());
                    statement.setInt(4, id);
    
                    statement.executeUpdate();
                }
                return true;
            } catch (Exception e) {
                Utility.handleUnexpectedException(e);
            }    
        }

        return false;
    }

    public boolean update(int id, String deliveryAddressLine1, String deliveryAddressLine2, String deliveryPostalCode) {
        return update(id, new Customer(
            id,
            deliveryAddressLine1,
            deliveryAddressLine2,
            deliveryPostalCode
        ));
    }

}