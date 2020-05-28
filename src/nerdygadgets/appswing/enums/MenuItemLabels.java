package nerdygadgets.appswing.enums;

public final class MenuItemLabels {

    public static final String HOME = "Home";
    public static final String ORDERS = "Orders";
    public static final String STOCK  ="Stock";
    public static final String RETURNS = "Returns";
    
    private MenuItemLabels() {

    }

    public static String[] asArray() {
        return new String[] { 
            HOME, 
            ORDERS, 
            STOCK /*, 
            RETURNS */ 
        };
    }
}