package nerdygadgets.appswing.models;


import nerdygadgets.appswing.views.*;
import nerdygadgets.shared.Utility;
import nerdygadgets.dal.repositories.*;

import javax.swing.*;
import java.awt.event.*;

public class AppTableAction extends AbstractAction {  
    private static final long serialVersionUID = 1L;
    // private final String orderDetailPanelName = OrderDetailPanel.class.getName();
    // private final String customerPanelName = CustomerPanel.class.getName();

    private AppPanel appPanel;
    private String panelName;

    public AppTableAction(AppPanel panel, String panelName) {
        appPanel = panel;
        this.panelName = panelName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {                        
            if(panelName.equals(Utility.ORDER_DETAIL_PANEL_NAME) || panelName.equals(Utility.CUSTOMER_PANEL_NAME)) {
                JTable table = (JTable)e.getSource();
                int modelRow = Integer.parseInt( e.getActionCommand() );

                AppPanel panel;

                if(panelName.equals(Utility.ORDER_DETAIL_PANEL_NAME)) {               
                    int id = getValue(table, modelRow, 0);
                    OrderRepository repository = new OrderRepository(appPanel.getDatabase());
                    panel = new OrderDetailPanel(appPanel.getDatabase(), repository.getOne(id)); 
                } else {                 
                    int id = getValue(table, modelRow, 1);
                    CustomerRepository repository = new CustomerRepository(appPanel.getDatabase());
                    panel = new CustomerPanel(appPanel.getDatabase(), repository.getOne(id)); 
                }
                // Add new panel to screen
                appPanel.togglePanelListeners(panel);
            }

        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }

    private int getValue(JTable table, int modelRow, int index) {
        return (int) table.getModel().getValueAt(modelRow, index);
    }
}