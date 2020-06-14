package nerdygadgets.appswing.views;

import javax.swing.*;

import nerdygadgets.appswing.SwingUtility;
import nerdygadgets.dal.Database;
import nerdygadgets.shared.Utility;

public class ReturnPanel extends AppPanel {
    private static final long serialVersionUID = 1L;

    /**
     * Initializes a new instance of the ReturnPanel class
     */
    public ReturnPanel(Database database) {
        super(database);
        try {
            add(new JLabel("Return Panel"));

            // Set screen to visible
            setVisible(true);

        } catch(Exception e) {
            SwingUtility.handleUnexpectedException(e);
        }
    }
}