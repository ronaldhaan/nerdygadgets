package nerdygadgets.appswing.views;

import nerdygadgets.dal.Database;
import nerdygadgets.dal.entities.Order;
import nerdygadgets.dal.repositories.OrderRepository;
import nerdygadgets.appswing.SwingUtility;
import nerdygadgets.appswing.helpers.ButtonColumn;
import nerdygadgets.shared.Utility;
import nerdygadgets.appswing.models.AppTableAction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;
import java.util.List;

public class OrderPanel extends AppPanel
{
    private static final long serialVersionUID = 1L;

    private OrderRepository orderRepository;
    private SwingUtility utility = new SwingUtility();

    /**
     * Initializes a new instance of the OrderPanel class
     */
    public OrderPanel(Database database) {
        super(database);
        orderRepository = new OrderRepository(database);
        try {
            // Get the orders from the database
            List<Order> orders = orderRepository.getAll();

            // Show warning when there are no orders
            if (orders.isEmpty()) {
                String message = "No orders found";
                utility.handleUnexpectedException(new Exception(message), true, this);
            } else {
                // Column Names
                String[] cols = { "OrderID", "CustomerID", "OrderDate" };

                // Initializing the JTable
                DefaultTableModel tableModel = new DefaultTableModel(cols, 0);
                JTable table = new JTable(tableModel);

                // Disable editing cells
                table.setDefaultEditor(Object.class, null);

                // Adding it to JScrollPane
                JScrollPane sp = new JScrollPane(table);
                add(sp);

                // Adding the orders to the table
                for (Order order : orders) {
                    tableModel.addRow(order.asArray());
                }

                // Action for view order button
                Action viewOrder = new AppTableAction(this, Utility.orderDetailPanelName);

                // Action for view customer button
                Action viewCustomer = new AppTableAction(this, Utility.customerPanelName);

                // Change orderIDColumn to button
                ButtonColumn orderIDColumn = new ButtonColumn(table, viewOrder, 0);
                orderIDColumn.setMnemonic(KeyEvent.VK_D);

                // Change CustomerIDColumn to button
                ButtonColumn customerIDColumn = new ButtonColumn(table, viewCustomer, 1);
                customerIDColumn.setMnemonic(KeyEvent.VK_D);

                // Set screen to visible
                setVisible(true);
            }
        }
        catch(Exception e) {
            utility.handleUnexpectedException(e);
        }
    }
}
