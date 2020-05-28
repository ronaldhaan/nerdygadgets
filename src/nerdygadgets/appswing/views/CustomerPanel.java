package nerdygadgets.appswing.views;

import nerdygadgets.dal.Database;
import nerdygadgets.dal.entities.Customer;
import nerdygadgets.shared.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerPanel extends AppPanel implements ActionListener {
    private static final long serialVersionUID = 1L;

    private JTextField CustomerID;
    private JTextField DeliveryAddressLine1;
    private JTextField DeliveryAddressLine2;
    private JTextField DeliveryPostalCode;
    private JButton updateBtn;
    private JButton cancelBtn;


    /**
     * @param customer Customer object
     *                 Initializes a new instance of the CustomerPanel class
     */
    public CustomerPanel(Customer customer) {
        super(null);

        try {
            setLayout(new GridLayout(10, 2));

            String[][] labels = {
                    {"CustomerID:", String.valueOf(customer.getCustomerID()), "Label"},
                    {"CustomerName:", customer.getCustomerName(), "Label"},
                    {"PhoneNumber:", customer.getPhoneNumber(), "Label"},
                    {"DeliveryAddressLine1:", customer.getDeliveryAddressLine1(), "Textfield"},
                    {"DeliveryAddressLine2:", customer.getDeliveryAddressLine2(), "Textfield"},
                    {"DeliveryPostalCode:", customer.getDeliveryPostalCode(), "Textfield"},
                    {"AccountOpenedDate:", String.valueOf(customer.getAccountOpenedDate()), "Label"}
            };

            for (String[] label : labels) {
                addLabels(label);
            }

            // Set screen to visible
            setVisible(true);
        } catch (Exception e) {
            Utility.handleUnexpectedException(e);
        }
        updateBtn = new JButton("Update");
        add(updateBtn);
        updateBtn.addActionListener(this);
        updateBtn.setSize(new Dimension(100, 25));

        cancelBtn = new JButton("Cancel");
        add(cancelBtn);
        cancelBtn.addActionListener(this);
        cancelBtn.setSize(new Dimension(100, 25));

    }

    public void addLabels(String[] labels) {
        addLabels(labels[0], labels[1], labels[2]);
    }

    public void addLabels(String label, String customerInfo, String component) {
        add(new JLabel(label));

        switch (component) {
            case "Label":
                if (label.equals("CustomerID:")) {
                    CustomerID = new JTextField(customerInfo);
                    CustomerID.setEditable(false);
                    add(CustomerID);
                } else {
                    JTextField jTextField = new JTextField(customerInfo);
                    jTextField.setEditable(false);
                    add(jTextField);
                }
                break;
            case "Textfield":
                if (label.equals("DeliveryAddressLine1:")) {
                    DeliveryAddressLine1 = new JTextField(customerInfo);
                    add(DeliveryAddressLine1);
                } else if (label.equals("DeliveryAddressLine2:")) {
                    DeliveryAddressLine2 = new JTextField(customerInfo);
                    add(DeliveryAddressLine2);
                } else if (label.equals("DeliveryPostalCode:")) {
                    DeliveryPostalCode = new JTextField(customerInfo);
                    add(DeliveryPostalCode);
                } else {
                    add(new JTextField(customerInfo));
                }
            default:
                break;
        }
    }




        @Override
    public void actionPerformed(ActionEvent e) {
        Database database = new Database();
        if ((JButton)e.getSource() == updateBtn) {
            try {
                int id = Integer.parseInt(CustomerID.getText());
                String deliveryAddressLine1 = DeliveryAddressLine1.getText();
                String deliveryAddressLine2 = DeliveryAddressLine2.getText();
                String deliveryPostalCode =  DeliveryPostalCode.getText();

                if(database.updateCustomerByID(id, deliveryAddressLine1, deliveryAddressLine2, deliveryPostalCode)) {                    
                    JOptionPane.showMessageDialog(this, "The customer with CustomerID: " + id + " has been successfully updated");
                }

            } catch (IllegalArgumentException exception) {
                JOptionPane.showMessageDialog(this, "Some fields contain invalid input. Please check the form for typos or missing values", "Warning", JOptionPane.WARNING_MESSAGE);
            } catch (Exception ex) {
                Utility.handleUnexpectedException(ex, true, this );
            }
        }

        if ((JButton)e.getSource() == cancelBtn) {
            setVisible(false);
            togglePanelListeners(new OrderPanel(database));
        }
    }
}

