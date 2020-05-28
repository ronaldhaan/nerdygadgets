package nerdygadgets.shared;

import nerdygadgets.appswing.views.*;
import java.awt.*;
import java.util.*;

import javax.swing.JOptionPane;

public final class Utility {
    private static final boolean DEFAULT_SHOW_MESSAGE_TO_USER = false;

    public static final String ORDER_DETAIL_PANEL_NAME = OrderDetailPanel.class.getName();
    public static final String CUSTOMER_PANEL_NAME = CustomerPanel.class.getName();
    public static final String GOOGLE_MAPS_URL = "https://www.google.com/maps/dir/";

    private static int screenWidth = 854;
    private static int screenHeight = 480;
    
    /**
     * Handles the unexpected exception without showing the user what's going on.
     * @param e
     */
    public static void handleUnexpectedException(Exception e) {
        handleUnexpectedException(e, DEFAULT_SHOW_MESSAGE_TO_USER, null);
    }

    /**
     * 
     * Handles the unexpected exception and shows the user a error message.
     * @param e
     * @param showMessageToUser
     * @param object
     */
    public static void handleUnexpectedException(Exception e, boolean showMessageToUser, Component object) {
                
        if(showMessageToUser) {
            JOptionPane.showMessageDialog(object, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);            
        } 

        System.err.println(e);

        FileHelper.writeToLogFile(e.getMessage() + "/n/r" + Arrays.toString(e.getStackTrace()));
    }

    public static void setScreenWidth(int screenWidth) {
        Utility.screenWidth = screenWidth;
    }

    public static int getScreenWidth() {
        return screenWidth;
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
}