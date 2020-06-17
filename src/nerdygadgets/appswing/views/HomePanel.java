package nerdygadgets.appswing.views;

import nerdygadgets.dal.Database;
import nerdygadgets.dal.entities.*;
import nerdygadgets.dal.repositories.CustomerRepository;
import nerdygadgets.dal.repositories.OrderRepository;
import nerdygadgets.appswing.SwingUtility;
import nerdygadgets.algorithm.*;

import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class HomePanel extends AppPanel {
    private static final long serialVersionUID = 1L;

    private RouteManager manager;
    private JButton btnGenerate;
    private JButton btnShowMaps;
    private JPanel routePanel;
    private JList<String> routeList;

    private CustomerRepository customerRepository;
    private OrderRepository orderRepository;
    
    public HomePanel(Database database) {
        super(database);   
        customerRepository = new CustomerRepository(database);
        orderRepository = new OrderRepository(database);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        btnGenerate = new JButton("Generate route");
        btnGenerate.addActionListener(generateRouteAction());
        add(btnGenerate);
        
        btnShowMaps = new JButton("Show Route on Google Maps");
        btnShowMaps.addActionListener(openrouteInMapsAction());
        btnShowMaps.setVisible(false);
        add(btnShowMaps);         
        
        routePanel = new JPanel();

        add(routePanel);
    }
    /**
     * Generates te route.
     */
    private boolean generateRoute() {
        // Get orders
        ArrayList<Order> orders = orderRepository.getAll();

        if (orders.isEmpty()) {
            SwingUtility.handleUnexpectedException(new Exception("No orders found"), true, this);
        } else {
            Node startNode = RouteManager.getNodeByPostalCodeAndPlace("8017 CA", "Campus 2, 8017 CA Zwolle");
            
            NodeCollection collection = new NodeCollection();
            
            orders.forEach(order -> {
                Customer customer = customerRepository.getOne(order.getCustomerID());
                String postal = customer.getDeliveryPostalCode();
                collection.add(RouteManager.getNodeByPostalCodeAndPlace(postal, customer.getAddress()));
                
            });

            collection.add(startNode);

            manager = new RouteManager(collection, startNode);
            manager.generate(); 
            
            setRouteOnFrame();
        }

        return !orders.isEmpty();
    }

    /**
     * Sets the generated route on the frame.
     */
    private void setRouteOnFrame() {
        routePanel.remove(routeList);
        String[] nodeNames = new String[manager.getNodes().size()];

        int i = 0;
        for (Node node : manager.getNodes()) {
            nodeNames[i] = (i+1) + ". -> " + node.getName();
            i++;
        }

        routeList = new JList<>(nodeNames);
        routePanel.add(routeList);
    }

    // Private Abstract Actions.
    
    private AbstractAction generateRouteAction() {
        return new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton buttonGenerate = (JButton)e.getSource();
                buttonGenerate.setEnabled(false);
                routePanel.removeAll();
                boolean success = generateRoute();
                buttonGenerate.setEnabled(true);
                btnShowMaps.setVisible(success);
            }            
        };
    }

    private AbstractAction openrouteInMapsAction() {
        return new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                manager.openRouteInMaps();
            }            
        };
    }
}
