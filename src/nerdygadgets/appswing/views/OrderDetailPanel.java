package nerdygadgets.appswing.views;

import nerdygadgets.appswing.models.*;
import nerdygadgets.dal.Database;
import nerdygadgets.dal.entities.Order;
import nerdygadgets.dal.entities.OrderLine;
import nerdygadgets.dal.repositories.OrderRepository;
import nerdygadgets.dal.repositories.OrderLineRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

public class OrderDetailPanel extends AppPanel implements ActionListener
{
    private static final long serialVersionUID = 1L;
    private JTextField orderID;
    private JTextField expectedDeliveryDate;
    private JTable table;
    private JButton updateBtn;
    private JButton cancelBtn;

    private OrderRepository orderRepository;
    private OrderLineRepository orderLineRepository;

    /**
     * Initializes a new instance of the OrderDetailPanel class
     */
    public OrderDetailPanel(Database database, Order order) {
        super(database);
        orderRepository = new OrderRepository(database);
        orderLineRepository = new OrderLineRepository(database);

        // Show warning when there is no order
        if (order == null) {
            JOptionPane.showMessageDialog(this, "No order found", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

            // Panel 1
            JPanel panel1 = new JPanel();
            panel1.setLayout(new GridLayout(0, 1));
            panel1.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));

            String[][] labels = {
                    { "OrderID:", String.valueOf(order.getId()), "Label"},
                    { "CustomerID:", String.valueOf(order.getCustomerID()), "Label"},
                    { "OrderDate:", String.valueOf(order.getOrderDate()), "Label"},
                    { "ExpectedDeliveryDate:", String.valueOf(order.getExpectedDeliveryDate()), "Textfield"}
            };

            for(String[] label : labels) {
                addLabels(panel1, label);
            }

            // Panel 2
            JPanel panel2 = new JPanel();
            panel2.setLayout(new GridLayout(0, 1));

            // Get orderlines by OrderID
            ArrayList<OrderLine> orderlines = orderLineRepository.getLinesByOrderID(order.getId());

            // Column Names
            String[] cols = { "OrderLineID", "StockItemID", "StockItemName", "Quantity", "UnitPrice", "TaxRate" };

            // Initializing the JTable
            DefaultTableModel tableModel = new DefaultTableModel(cols, 0);
            table = new AppTable(tableModel);

            // Adding it to JScrollPane
            JScrollPane sp = new JScrollPane(table);
            panel2.add(sp);

            // Adding the orderlines to the table
            for (OrderLine orderline : orderlines) {
                tableModel.addRow(orderline.asArray());
            }

            updateBtn = new JButton("Update");
            updateBtn.addActionListener(this);
            updateBtn.setSize(new Dimension(100, 25));

            cancelBtn = new JButton("Cancel");
            cancelBtn.addActionListener(this);
            cancelBtn.setSize(new Dimension(100, 25));

            // Panel 3
            JPanel panel3 = new JPanel();
            panel3.setLayout(new FlowLayout());
            panel3.add(updateBtn);
            panel3.add(cancelBtn);

            // Add panels
            add(panel1);
            add(panel2);
            add(panel3);

            // Set screen to visible
            setVisible(true);
        }
    }

    public void addLabels(JPanel panel, String[] labels) {
        addLabels(panel, labels[0], labels[1], labels[2]);
    }

    public void addLabels(JPanel panel, String label, String orderInfo, String component) {
        panel.add(new JLabel(label));

        switch(component) {
            case "Label":
                if (label.equals("OrderID:")) {
                    orderID = new JTextField(orderInfo);
                    orderID.setEditable(false);
                    panel.add(orderID);
                } else {
                    JTextField jTextField = new JTextField(orderInfo);
                    jTextField.setEditable(false);
                    panel.add(jTextField);
                }
            break;
            case "Textfield":
                if (label.equals("ExpectedDeliveryDate:")) {
                    expectedDeliveryDate = new JTextField(orderInfo);
                    panel.add(expectedDeliveryDate);
                } else {
                    panel.add(new JTextField(orderInfo));
                }
            break;
            default: 
            break;
        }
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateBtn) {
           updateBtnPerformed();
        }

        if (e.getSource() == cancelBtn) {
            setVisible(false);
            togglePanelListeners(new OrderPanel(getDatabase()));
        }
    }

    /**
     * Performes the action of the update button
     */
    private void updateBtnPerformed() {
        try {
            boolean error = false;
            int id = Integer.parseInt(orderID.getText());
            Date date = Date.valueOf(expectedDeliveryDate.getText());

            if(orderRepository.update(id, date)) {
                for (int count = 0; count < table.getRowCount(); count++) {
                    int lineID = Integer.parseInt(table.getValueAt(count, 0).toString());
                    int quality = Integer.parseInt(table.getValueAt(count, 3).toString());
                    double unitPrice = Double.parseDouble(table.getValueAt(count, 4).toString());
                    double taxRate = Double.parseDouble(table.getValueAt(count, 5).toString());

                    if (quality <= 0 || unitPrice <= 0 || taxRate <= 0) {
                        error = true;
                        JOptionPane.showMessageDialog(this, "The Quantity, UnitPrice and TaxRate fields can't contains a value under 1, please try again", "Warning", JOptionPane.WARNING_MESSAGE);
                    }

                    if (!error)
                        orderLineRepository.update(lineID, quality, unitPrice, taxRate);
                }

                if (!error)
                    JOptionPane.showMessageDialog(this, "The order with OrderID: " + id + " has been successfully updated");
            }
        } catch (IllegalArgumentException exception) {
            JOptionPane.showMessageDialog(this, "Some fields contain invalid input. Please check the form for typos or missing values", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
}
