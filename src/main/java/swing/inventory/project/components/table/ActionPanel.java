package swing.inventory.project.components.table;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import swing.inventory.project.components.button.ButtonAction;
import swing.inventory.project.themes.Colors;

public class ActionPanel extends JPanel {
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JButton view = new ButtonAction("view");
    private JButton edit = new ButtonAction("edit");
    private JButton delete = new ButtonAction("delete");

    public ActionPanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(Colors.White);
        add(view);
        add(edit);
        add(delete);
    }

    public void initEvent(TableActionEvent event, int row) {
        view.addActionListener(e -> event.onView(row));
        edit.addActionListener(e -> event.onEdit(row));
        delete.addActionListener(e -> event.onDelete(row));
    }

}
