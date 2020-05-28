package nerdygadgets.appswing.views;

import javax.swing.*;

import nerdygadgets.dal.Database;
import nerdygadgets.appswing.interfaces.PanelListener;

public abstract class AppPanel extends JPanel{
    private static final long serialVersionUID = 1L;

    private PanelListener listener;

    private Database database;

    public AppPanel(Database database) {
        listener = null;
        this.database = database;
    }

    public Database getDatabase() {
        return database;
    }

    public void togglePanelListeners(AppPanel jp) {
        listener.setPanel(jp);
    }
    
    public void setPanelListener(PanelListener listener) {
        this.listener = listener;
    }
}