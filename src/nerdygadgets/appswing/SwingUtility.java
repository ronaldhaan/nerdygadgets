package nerdygadgets.appswing;

import javax.swing.JOptionPane;
import java.awt.Component;

import nerdygadgets.shared.Utility;

public class SwingUtility extends Utility {

    public SwingUtility() {super();}
    
    @Override
    protected void showUser(Component object, Exception e) {
        JOptionPane.showMessageDialog(object, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
    }

}