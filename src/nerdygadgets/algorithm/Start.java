package nerdygadgets.algorithm;

import nerdygadgets.dal.Database;
import nerdygadgets.dal.entities.Customer;
import nerdygadgets.dal.entities.Order;
import nerdygadgets.dal.repositories.CustomerRepository;
import nerdygadgets.dal.repositories.OrderRepository;
import nerdygadgets.shared.Utility;

import java.util.ArrayList;

public class Start {
    public static void main(String[] args) {
        OrderRepository orderRepository = new OrderRepository();
        CustomerRepository customerRepository = new CustomerRepository();

        // Get orders
        ArrayList<Order> orders = orderRepository.getAll();

        if (orders.isEmpty()) {
            Utility.handleUnexpectedException(new Exception("No orders found"));
        } else {
            NodeCollection collection = new NodeCollection();

            Node startNode = RouteManager.getNodeByPostalCodeAndPlace("8017 CA", "Campus 2, 8017 CA Zwolle");

            for (Order order : orders) {
                Customer customer = customerRepository.getOne(order.getCustomerID());
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
