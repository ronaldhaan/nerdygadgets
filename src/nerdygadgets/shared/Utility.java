package nerdygadgets.shared;

import nerdygadgets.appswing.views.*;
import java.awt.*;
import java.util.*;

public class Utility {
    public static final boolean defaultShowMessageToUser = false;
    public static final String orderDetailPanelName = OrderDetailPanel.class.getName();
    public static final String customerPanelName = CustomerPanel.class.getName();
    public static final String googleMapsUrl = "https://www.google.com/maps/dir/";

    private static int screenWidth = 854;
    private static int screenHeight = 480;

    protected Utility() {
    }

    /**
     * Handles the unexpected exception without showing the user what's going on.
     * 
     * @param e
     */
    public static void handleUnexpectedException(Exception e) {
        handleUnexpectedException(e, defaultShowMessageToUser);
    }

    /**
     * 
     * Handles the unexpected exception and shows the user a error message.
     * 
     * @param e
     * @param showMessageToUser
     * @param object
     */
    public static void handleUnexpectedException(Exception e, boolean showMessageToUser) {

        if (showMessageToUser) {
            System.err.println(e);
        }


        FileHelper.writeToLogFile(e.getMessage() + "/n/r" + Arrays.toString(e.getStackTrace()));
    }

    public static void setScreenWidth(int screenWidth) {
        Utility.screenWidth = screenWidth;
    }

    public static int getScreenWidth() {
        return Utility.screenWidth;
    }

    public static void setScreenHeight(int screenHeight) {
        Utility.screenHeight = screenHeight;
    }

    public static int getScreenHeight() {
        return screenHeight;
    }

    public static Dimension getScreenSize() {
        return new Dimension(getScreenWidth(), getScreenHeight());
    }

    protected static void showUser(Component object, Exception e) {
        System.out.println(object);
        System.err.println(e);
    }

}