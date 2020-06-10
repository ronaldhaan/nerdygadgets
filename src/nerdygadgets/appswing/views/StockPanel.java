package nerdygadgets.appswing.views;

import nerdygadgets.appswing.SwingUtility;
import nerdygadgets.dal.Database;
import nerdygadgets.dal.entities.Stock;
import nerdygadgets.dal.repositories.StockRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class StockPanel extends AppPanel {
    private static final long serialVersionUID = 1L;

    private StockRepository stockRepository;
    private SwingUtility utility = new SwingUtility();

    /**
     * Initializes a new instance of the StockPanel class
     */
    public StockPanel(Database database) {
        super(database);
        stockRepository = new StockRepository(database);
        try {
            // haalt voorraad op van database
            List<Stock> stocks = stockRepository.getAll();

            // warning bij geen voorraad
            if (stocks.isEmpty()) {
                String message = "No stock found";
                utility.handleUnexpectedException(new Exception(message), true, this);
            } else {
                // column names
                String[] cols = {"StockItemID", "Quantity", "StockItemName"};
                // Initializing the JTable

                DefaultTableModel stockTableModel = new DefaultTableModel(cols, 0);
                JTable stockTable = new JTable(stockTableModel);

                // Disable editing cells
                stockTable.setDefaultEditor(Object.class, null);

                // Adding it to JScrollPane
                JScrollPane stockSp = new JScrollPane(stockTable);
                add(stockSp);

                // voorraad toevoegan aan de table
                for (Stock stock : stocks) {
                    stockTableModel.addRow(stock.asArray());
                }

                setVisible(true);
            }
        } catch (Exception e) {
            utility.handleUnexpectedException(e);

        }
    }
}
