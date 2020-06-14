package nerdygadgets.appswing;

import javax.swing.JOptionPane;
import java.awt.Component;

import nerdygadgets.shared.Utility;

public class SwingUtility extends Utility {

    public SwingUtility() {super();}
    
    public static void handleUnexpectedException(Exception e) {
        SwingUtility.handleUnexpectedException(e, false, null);
    }

    /**
     * 
     * Handles the unexpected exception and shows the user a error message.
     * 
     * @param e
     * @param showMessageToUser
     * @param object
     */
    public static void handleUnexpectedException(Exception e, boolean showMessageToUser, Component object) {

        if (showMessageToUser && object != null) {
            JOptionPane.showMessageDialog(object, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
        }

        Utility.handleUnexpectedException(e, Utility.defaultShowMessageToUser);
        
    }

}