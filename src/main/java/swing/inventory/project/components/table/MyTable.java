package swing.inventory.project.components.table;

import java.awt.Dimension;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import swing.inventory.project.themes.Colors;
import swing.inventory.project.themes.Fonts;

public class MyTable extends JTable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public MyTable() {
        JTableHeader header = getTableHeader();
        header.setReorderingAllowed(false);
        header.setResizingAllowed(true);
        header.setDefaultRenderer(new MyHeaderCellRenderer());
        setTableHeader(header);
        setShowHorizontalLines(false);
        setShowVerticalLines(false);
        setRowHeight(32);
        setBackground(Colors.White);
        setSelectionBackground(Colors.Warning);
        setSelectionForeground(Colors.Black);
        setFont(Fonts.fontLight(14));
        setIntercellSpacing(new Dimension(0, 0));
        setRowMargin(0);
        setDefaultRenderer(Object.class, new MyTableCellRenderer());
        ((DefaultCellEditor)(getDefaultEditor(Object.class))).getComponent().setFont(getFont());
    }
    
}
