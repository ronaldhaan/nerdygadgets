package nerdygadgets.appswing;

import nerdygadgets.dal.Database;
import nerdygadgets.appswing.enums.MenuItemLabels;
import nerdygadgets.shared.Utility;
import nerdygadgets.appswing.interfaces.PanelListener;
import nerdygadgets.appswing.views.*;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 */
public class AppScreen extends JFrame implements PanelListener, MouseInputListener {
    private static final long serialVersionUID = 1L;

    private SwingUtility utility = new SwingUtility();

    private JMenuBar appMenuBar; 

    private AppPanel panelContent;
    private Map<String, JMenu> menuItems;
    private Database database;

    public AppScreen() {
        // Initializing the JFrame
        setTitle("NerdyGadgets");
        setSize(utility.getScreenSize());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Menu bar
        appMenuBar = new JMenuBar();
        // Menu items sorted bij the Label as string and the menu item object as menu item.
        menuItems = new HashMap<>();
        
        // based on the labels, loop through them to create the menu items.
        for(String menuItemLabel : MenuItemLabels.asArray()) {
            JMenu menuItem = new JMenu(menuItemLabel);
            menuItem.addMouseListener(this);
            menuItems.put(menuItemLabel, menuItem);
            appMenuBar.add(menuItem);
        }

        // Set the menu
        setJMenuBar(appMenuBar);
        database = new Database();
        // Set the panel
        setPanel(new HomePanel(database));

        // Set screen to visible
        setVisible(true);
    }

    /**
     * Sets the content panel.
     * 
     * @param p , the new content panel.
     */
    @Override
    public void setPanel(AppPanel p) {
        // If there already is a panel, hide it.
        if(panelContent != null) {
            panelContent.setVisible(false);
        }
        // Place the new content.
        panelContent = p;
        // Register this Frame instance as the panel listener so that the Panels can communicate with each other
        panelContent.setPanelListener(this);
        // Set this new Frame as the new content panel.
        setContentPane(panelContent);
    }

    /**
     * Event that fires when one of the menu items has been clicked on.
     * 
     * @param e Event collection with the information which button was clicked on. 
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        // loop through the key/value pair of the menucollection.
        for (Map.Entry<String, JMenu> x : menuItems.entrySet()) {
            // check if the current value of the key/value pair is the menu item that was clicked on.
            if (e.getSource() == x.getValue()) {
                // Get the key that belongs to the value of the current key/value pair and set the right panel.
                AppPanel newPanel;
                switch (x.getKey()) {
                    case MenuItemLabels.HOME:
                        newPanel = new HomePanel(database);
                    break;
                    case MenuItemLabels.ORDERS:
                        newPanel = new OrderPanel(database);
                    break;
                    case MenuItemLabels.STOCK:
                        newPanel = new StockPanel(database);
                    break;
                    case MenuItemLabels.RETURNS:                         
                        newPanel = new ReturnPanel(database);
                    break;
                    default: 
                        newPanel = null;
                        // The key is not a registered menu item.
                        String message = "Panel " + x.getKey() + " not found";
                        utility.handleUnexpectedException(new Exception(message));
                    break;
                }

                if(newPanel != null) {
                    setPanel(newPanel);
                } else {
                    add(new Label("Error 404"));
                }

                invalidate();
                validate();
            }
        }

    }

    // methods we are currently not using but must be implemented because of the interface MouseInputListener

    @Override
    public void mousePressed(MouseEvent e) {
        // 

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //

    }

    @Override
    public void mouseExited(MouseEvent e) {
        //
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //
    }
}