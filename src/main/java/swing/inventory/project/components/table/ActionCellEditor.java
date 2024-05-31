package swing.inventory.project.components.table;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class ActionCellEditor extends DefaultCellEditor {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private TableActionEvent event;

    public ActionCellEditor(TableActionEvent e) {
        super(new JCheckBox());
        event = e;
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int col) {
        ActionPanel action = new ActionPanel();
        action.initEvent(event, row);
        action.setBackground(jtable.getSelectionBackground());
        return action;
    }
    
}
