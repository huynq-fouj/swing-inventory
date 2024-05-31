package swing.inventory.project.components.table;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MyHeaderCellRenderer extends DefaultTableCellRenderer {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return new TableHeader(value + "");
    }
    
}
