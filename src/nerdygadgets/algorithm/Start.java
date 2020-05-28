package nerdygadgets.algorithm;

import nerdygadgets.dal.Database;
import nerdygadgets.dal.entities.Customer;
import nerdygadgets.dal.entities.Order;
import nerdygadgets.shared.Utility;

import java.util.ArrayList;

public class Start {
    public static void main(String[] args) {
        Database database = new Database();

        // Get orders
        ArrayList<Order> orders = database.getOrders();

        if (orders.isEmpty()) {
            Utility.handleUnexpectedException(new Exception("No orders found"));
        } else {
            NodeCollection collection = new NodeCollection();

            Node startNode = RouteManager.getNodeByPostalCodeAndPlace("8017 CA", "Campus 2, 8017 CA Zwolle");

            for (Order order : orders) {
                Customer customer = database.getCustomerByID(order.getCustomerID());
                String postal = customer.getDeliveryPostalCode();
                collection.add(RouteManager.getNodeByPostalCodeAndPlace(postal, customer.getAddress()));
            }

            collection.add(startNode);

            RouteManager manager = new RouteManager(collection, startNode);
            manager.generate();

            System.out.println("BEST route:");       
            
            manager.openRouteInMaps();
        }
    }
}
