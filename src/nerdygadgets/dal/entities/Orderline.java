package nerdygadgets.dal.entities;

public class OrderLine {
    private int orderLineID;
    private int orderID;
    private int stockItemID;
    private String stockItemName;
    private int quantity;
    private Double unitPrice;
    private Double taxRate;

    /**
     * Initializes a new instance of the Orderline class
     */
    public OrderLine(int orderLineID, int quantity, Double unitPrice, Double taxRate) {
        this(orderLineID, -1, -1, null, quantity, unitPrice, taxRate);
    }

    /**     * 
     * Initializes a new instance of the Orderline class
     */
    public OrderLine(int orderLineID, int orderID, int stockItemID, String stockItemName, int quantity, Double unitPrice, Double taxRate) {
        this.orderLineID = orderLineID;
        this.orderID = orderID;
        this.stockItemID = stockItemID;
        this.stockItemName = stockItemName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.taxRate = taxRate;
    }

    /**
     * Get orderID from instance
     * @return orderID
     */
    public int getOrderID() {
        return orderID;
    }

    /**
     * Get orderLineID from instance
     * @return orderLineID
     */
    public int getOrderLineID() {
        return orderLineID;
    }

    /**
     * Get stockItemID from instance
     * @return stockItemID
     */
    public int getStockItemID() {
        return stockItemID;
    }

    /**
     * Get stockItemName from instance
     * @return stockItemName
     */
    public String getStockItemName() {
        return stockItemName;
    }

    /**
     * Get quantity from instance
     * @return quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Get unitPrice from instance
     * @return unitPrice
     */
    public Double getUnitPrice() {
        return unitPrice;
    }

    /**
     * Get taxRate from instance
     * @return taxRate
     */
    public Double getTaxRate() {
        return taxRate;
    }

    /**
     * Get array from instance
     * @return orderLineID, stockItemID, stockItemName
     */
    public Object[] asArray() {
        return new Object[] { getOrderLineID(), getStockItemID(), getStockItemName(), getQuantity(), getUnitPrice(), getTaxRate() };
    }
}
