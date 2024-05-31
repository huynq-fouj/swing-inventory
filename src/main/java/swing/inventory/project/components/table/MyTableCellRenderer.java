package swing.inventory.project.components.table;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import swing.inventory.project.themes.Colors;

public class MyTableCellRenderer extends DefaultTableCellRenderer {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
        setBorder(noFocusBorder);
        if(!isSelected && row % 2 == 0) setBackground(new Color(240, 240, 240));
        if(!isSelected && row % 2 == 1) setBackground(Colors.White);
        return this;
	 }
    
}
