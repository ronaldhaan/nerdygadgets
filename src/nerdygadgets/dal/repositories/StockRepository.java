package nerdygadgets.dal.repositories;

import java.sql.*;
import java.util.*;

import nerdygadgets.dal.Database;
import nerdygadgets.dal.entities.Stock;
import nerdygadgets.shared.Utility;

public class StockRepository extends Repository {
    
    public StockRepository() {
        this(new Database());
    }

    public StockRepository(Database database) {
        super(database);
    }

    public List<Stock> getAll() {
        ArrayList<Stock> stock = new ArrayList<>();
        try (Connection connection = getConnection().open()) {
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
}