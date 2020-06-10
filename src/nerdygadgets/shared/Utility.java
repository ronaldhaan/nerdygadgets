package nerdygadgets.shared;

import nerdygadgets.appswing.views.*;
import java.awt.*;
import java.util.*;

public class Utility {
    private static final boolean defaultShowMessageToUser = false;

    public static final String orderDetailPanelName = OrderDetailPanel.class.getName();
    public static final String customerPanelName = CustomerPanel.class.getName();
    public static final String googleMapsUrl = "https://www.google.com/maps/dir/";

    private int screenWidth = 854;
    private int screenHeight = 480;

    protected Utility() {
    }

    /**
     * Handles the unexpected exception without showing the user what's going on.
     * 
     * @param e
     */
    public void handleUnexpectedException(Exception e) {
        handleUnexpectedException(e, defaultShowMessageToUser, null);
    }

    /**
     * 
     * Handles the unexpected exception and shows the user a error message.
     * 
     * @param e
     * @param showMessageToUser
     * @param object
     */
    public void handleUnexpectedException(Exception e, boolean showMessageToUser, Component object) {

        if (showMessageToUser) {
            showUser(object, e);
        }

        System.err.println(e);

        FileHelper.writeToLogFile(e.getMessage() + "/n/r" + Arrays.toString(e.getStackTrace()));
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public Dimension getScreenSize() {
        return new Dimension(getScreenWidth(), getScreenHeight());
    }

    protected void showUser(Component object, Exception e) {
        System.out.println(object);
        System.err.println(e);
    }

}