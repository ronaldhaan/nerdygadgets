package nerdygadgets.dal.entities;

import java.sql.Date;

public class Order implements Entity {
    private int orderID;
    private int customerID;
    private Date orderDate;
    private Date expectedDeliveryDate;

    /**
     * Initializes a new instance of the Order class
     */
    public Order(int orderID, int customerID, Date orderDate) {
        this(orderID, customerID, orderDate, null);
    }

    /**
     * Initializes a new instance of the Order class
     */
    public Order(int orderID, int customerID, Date orderDate, Date expectedDeliveryDate) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.orderDate = orderDate;
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    /**
     * Get orderID from instance
     * @return orderID
     */
    public int getId() {
        return orderID;
    }

    /**
     * Get customerID from instance
     * @return customerID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * Get orderDate from instance
     * @return orderDate
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * Get expectedDeliveryDate from instance
     * @return expectedDeliveryDate
     */
    public Date getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    /**
     * Get array from instance
     * @return orderID, customerID, expectedDeliveryDate
     */
    public Object[] asArray() {
        return new Object[] { getId(), getCustomerID(), getOrderDate()};
    }
}
