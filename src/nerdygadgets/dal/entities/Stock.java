package nerdygadgets.dal.entities;

public class Stock {
    private int stockItemID;
    private int quantityOnHand;
    private String stockItemName;

    /**
     * Initializes a new instance of the Stock class
     */
    public Stock (int stockItemID, int quantityOnHand, String stockItemName) {
        this.stockItemID = stockItemID;
        this.quantityOnHand = quantityOnHand;
        this.stockItemName = stockItemName;
    }

    /**
     * Get stockItemID from instance
     * @return stockItemID
     */
    public int getStockItemID() { return stockItemID; }

    /**
     * Get quantityOnHand from instance
     * @return quantityOnHand
     */
    public int getQuantityOnHand () { return quantityOnHand; }

    /**
     * Get stockItemName from instance
     * @return stockItemName
     */
    public String getStockItemName () { return stockItemName; }

    /**
     * Get array from instance
     * @return stockItemID, quantityOnHand, stockItemName
     */
    public Object[] asArray () { return new Object[] {getStockItemID(), getQuantityOnHand(), getStockItemName() }; }
}
