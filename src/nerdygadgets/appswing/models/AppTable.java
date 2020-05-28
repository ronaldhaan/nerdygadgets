package nerdygadgets.appswing.models;

import java.util.*;

import javax.swing.*;
import javax.swing.table.TableModel;

public class AppTable extends JTable {
    private static final long serialVersionUID = 1L;

    public AppTable(TableModel tableModel) {
        super(tableModel);
    }    
    
    @Override
    public boolean isCellEditable(int row, int column) {
        return !Arrays.asList(0, 1, 2).contains(column);
    }
}