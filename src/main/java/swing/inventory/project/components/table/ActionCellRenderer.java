package swing.inventory.project.components.table;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class ActionCellRenderer extends DefaultTableCellRenderer {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object o, boolean isSelected, boolean arg3, int row,
            int arg5) {
        Component com = super.getTableCellRendererComponent(table, o, isSelected, arg3, row, arg5);
        ActionPanel action = new ActionPanel();
        if(!isSelected && row % 2 == 0) action.setBackground(new Color(240, 240, 240));
        else action.setBackground(com.getBackground());
        return action;
    }
    
}
