package nerdygadgets.appswing.views;

import nerdygadgets.dal.Database;
import nerdygadgets.dal.entities.Customer;
import nerdygadgets.dal.repositories.CustomerRepository;
import nerdygadgets.shared.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerPanel extends AppPanel implements ActionListener {
    private static final long serialVersionUID = 1L;

    private JTextField tbxCustomerID;
    private JTextField tbxDeliveryAddressLine1;
    private JTextField tbxDeliveryAddressLine2;
    private JTextField tbxDeliveryPostalCode;
    private final JButton updateBtn;
    private final JButton cancelBtn;

    private final CustomerRepository customerRepository;


    /**
     * @param customer Customer object
     *                 Initializes a new instance of the CustomerPanel class
     */
    public CustomerPanel(final Database database, final Customer customer) {
        super(database);
        customerRepository = new CustomerRepository(database);

        try {
            setLayout(new GridLayout(10, 2));

            final String[][] labels = {
                    {"CustomerID:", String.valueOf(customer.getId()), "Label"},
                    {"CustomerName:", customer.getCustomerName(), "Label"},
                    {"PhoneNumber:", customer.getPhoneNumber(), "Label"},
                    {"DeliveryAddressLine1:", customer.getDeliveryAddressLine1(), "Textfield"},
                    {"DeliveryAddressLine2:", customer.getDeliveryAddressLine2(), "Textfield"},
                    {"DeliveryPostalCode:", customer.getDeliveryPostalCode(), "Textfield"},
                    {"AccountOpenedDate:", String.valueOf(customer.getAccountOpenedDate()), "Label"}
            };

            for (final String[] label : labels) {
                addLabels(label);
            }

            // Set screen to visible
            setVisible(true);
        } catch (final Exception e) {
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

    public void addLabels(final String[] labels) {
        addLabels(labels[0], labels[1], labels[2]);
    }

    public void addLabels(final String label, final String customerInfo, final String component) {
        add(new JLabel(label));

        switch (component) {
            case "Label":
                if (label.equals("CustomerID:")) {
                    tbxCustomerID = new JTextField(customerInfo);
                    tbxCustomerID.setEditable(false);
                    add(tbxCustomerID);
                } else {
                    final JTextField jTextField = new JTextField(customerInfo);
                    jTextField.setEditable(false);
                    add(jTextField);
                }
                break;
            case "Textfield":
                if (label.equals("DeliveryAddressLine1:")) {
                    tbxDeliveryAddressLine1 = new JTextField(customerInfo);
                    add(tbxDeliveryAddressLine1);
                } else if (label.equals("DeliveryAddressLine2:")) {
                    tbxDeliveryAddressLine2 = new JTextField(customerInfo);
                    add(tbxDeliveryAddressLine2);
                } else if (label.equals("DeliveryPostalCode:")) {
                    tbxDeliveryPostalCode = new JTextField(customerInfo);
                    add(tbxDeliveryPostalCode);
                } else {
                    add(new JTextField(customerInfo));
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        if ((JButton)e.getSource() == updateBtn) {
            try {
                Customer customer = new Customer(
                    Integer.parseInt(tbxCustomerID.getText()),
                    tbxDeliveryAddressLine1.getText(),
                    tbxDeliveryAddressLine2.getText(),
                    tbxDeliveryPostalCode.getText()
                );

                if(customerRepository.update(customer.getId(), customer)) {                    
                    JOptionPane.showMessageDialog(this, "The customer with CustomerID: " + customer.getId() + " has been successfully updated");
                }

            } catch (final IllegalArgumentException exception) {
                JOptionPane.showMessageDialog(this, "Some fields contain invalid input. Please check the form for typos or missing values", "Warning", JOptionPane.WARNING_MESSAGE);
            } catch (final Exception ex) {
                Utility.handleUnexpectedException(ex, true, this );
            }
        }

        if ((JButton)e.getSource() == cancelBtn) {
            setVisible(false);
            togglePanelListeners(new OrderPanel(getDatabase()));
        }
    }
}

