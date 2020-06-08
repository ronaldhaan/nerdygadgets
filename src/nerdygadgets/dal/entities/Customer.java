package nerdygadgets.dal.entities;

import java.sql.Date;

public class Customer implements Entity {
    private int customerID;
    private String customerName;
    private String phoneNumber;
    private String deliveryAddressLine1;
    private String deliveryAddressLine2;
    private String deliveryPostalCode;
    private Date accountOpenedDate;

    public Customer(int customerID, String deliveryAddressLine1, String deliveryAddressLine2, String deliveryPostalCode) {
        this(customerID, null, null, deliveryAddressLine1, deliveryAddressLine2, deliveryPostalCode, null);
    }

    /**
     * Initializes a new instance of the Customer class
     */
    public Customer(int customerID, String customerName, String phoneNumber, String deliveryAddressLine1, String deliveryAddressLine2, String deliveryPostalCode, Date accountOpenedDate) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.deliveryAddressLine1 = deliveryAddressLine1;
        this.deliveryAddressLine2 = deliveryAddressLine2;
        this.deliveryPostalCode = deliveryPostalCode;
        this.accountOpenedDate = accountOpenedDate;
    }

    /**
     * Get customerID from instance
     * @return customerID
     */
    public int getId() {
        return customerID;
    }

    /**
     * Get customerName from instance
     * @return customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Get phoneNumber from instance
     * @return phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Get deliveryAddressLine1 from instance
     * @return deliveryAddressLine1
     */
    public String getDeliveryAddressLine1() {
        return deliveryAddressLine1;
    }

    /**
     * Get deliveryAddressLine2 from instance
     * @return deliveryAddressLine2
     */
    public String getDeliveryAddressLine2() {
        return deliveryAddressLine2;
    }

    /**
     * Get address from instance
     * @return address
     */
    public String getAddress() {
        return deliveryAddressLine1 + ", " + deliveryPostalCode + " " + deliveryAddressLine2;
    }

    /**
     * Get deliveryPostalCode from instance
     * @return deliveryPostalCode
     */
    public String getDeliveryPostalCode() {
        return deliveryPostalCode;
    }

    /**
     * Get accountOpenedDate from instance
     * @return accountOpenedDate
     */
    public Date getAccountOpenedDate() {
        return accountOpenedDate;
    }
}
