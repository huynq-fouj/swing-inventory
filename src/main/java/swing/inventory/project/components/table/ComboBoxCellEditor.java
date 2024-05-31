package swing.inventory.project.components.table;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;

import swing.inventory.project.ConnectionPool;
import swing.inventory.project.contexts.ConnectionContext;

public class ComboBoxCellEditor extends DefaultCellEditor {
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    public ComboBoxCellEditor() {
        super(new JComboBox<>());
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int col) {
        ConnectionPool cp = ConnectionContext.getCP();
        
        return null;
    }
    
}
