package nerdygadgets.appswing.interfaces;

import nerdygadgets.appswing.views.AppPanel;

import java.io.Serializable;

public interface PanelListener extends Serializable {
    void setPanel(AppPanel p);
}