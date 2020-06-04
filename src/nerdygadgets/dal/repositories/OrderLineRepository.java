package nerdygadgets.dal.repositories;

import java.sql.*;
import java.util.ArrayList;

import nerdygadgets.dal.Database;
import nerdygadgets.dal.entities.Orderline;
import nerdygadgets.shared.Utility;

public class OrderLineRepository extends Repository {
    
    public OrderLineRepository(Database database) {
        super(database);
    }    

    
    /**
     * Get orderlines by id from the database
     * @param id OrderID
     * @return ArrayList with all orderlines
     * @exception SQLException
     */
    public ArrayList<Orderline> getOne(int id) {
        ArrayList<Orderline> orderlines = new ArrayList<>();
        try (Connection connection = getConnection().open()) {
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
     * Update orderline by id
     *
     * @param id Orderline id
     * @param quantity Orderline quantity
     * @param unitPrice Orderline unitPrice
     * @param taxRate Orderline taxRate
     * @exception Exception
     * @exception NullPointerException
     */
    public void update(int id, int quantity, Double unitPrice, Double taxRate) {
        try(Connection connection = getConnection().open()) {
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
}