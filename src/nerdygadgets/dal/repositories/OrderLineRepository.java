package nerdygadgets.dal.repositories;

import java.sql.*;
import java.util.ArrayList;

import nerdygadgets.dal.Database;
import nerdygadgets.dal.entities.OrderLine;
import nerdygadgets.shared.Utility;

public class OrderLineRepository extends Repository<OrderLine> {
    
    public OrderLineRepository(Database database) {
        super(database);
    }    

    @Override
    public ArrayList<OrderLine> getAll() {
        return new ArrayList<>();
    }

    @Override
    public OrderLine getOne(int id) {
        return null;
    }
    
    /**
     * Get orderlines by id from the database
     * @param orderID OrderID
     * @return ArrayList with all orderlines
     * @exception SQLException
     */
    public ArrayList<OrderLine> getLinesByOrderID(int orderID) {
        ArrayList<OrderLine> orderlines = new ArrayList<>();
        try (Connection connection = getConnection().open()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT L.OrderLineID, L.OrderID, L.StockItemID, I.StockItemName, L.Quantity, L.UnitPrice, L.TaxRate FROM orderlines L\n" +
                    "JOIN stockitems I ON I.StockItemID = L.StockItemID\n" +
                    "WHERE OrderID = ?;")) {
                statement.setInt(1, orderID);

                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        orderlines.add(
                            new OrderLine(
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
    public boolean update(int id, int quantity, Double unitPrice, Double taxRate) {
        return update(id, new OrderLine(
                            id, 
                            quantity, 
                            unitPrice, 
                            taxRate
                        )
                    );
    }

	@Override
	public boolean update(int id, OrderLine entity) {
        try(Connection connection = getConnection().open()) {
            try (PreparedStatement statement = connection.prepareStatement("UPDATE orderlines SET Quantity = ?, UnitPrice = ?, TaxRate = ? WHERE OrderLineID = ?")) {
                statement.setInt(1, entity.getQuantity());
                statement.setDouble(2, entity.getUnitPrice());
                statement.setDouble(3, entity.getTaxRate());
                statement.setInt(4, id);

                statement.executeUpdate();

                return true;
            }
        } catch (Exception e) {
            Utility.handleUnexpectedException(e);
        }
		return false;
	}
}