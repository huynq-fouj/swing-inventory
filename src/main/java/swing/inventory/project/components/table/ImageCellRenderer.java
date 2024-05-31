package swing.inventory.project.components.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

import swing.inventory.project.themes.Colors;
import swing.inventory.project.utils.ImageUtil;
import swing.inventory.project.utils.Resource;

public class ImageCellRenderer extends DefaultTableCellRenderer {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object o, boolean isSelected, boolean arg3, int row, int col) {
        Component com = super.getTableCellRendererComponent(table, o, isSelected, arg3, row, col);

        int size = 32;
        ImageIcon icon;
        if(o == null || o.equals("")) icon = new ImageIcon(Resource.loadStaticImagePath("default.png"));
        else icon = new ImageIcon(Resource.loadUploadImagePath((String) o));
        JLabel label = new JLabel();
        label.setIcon(ImageUtil.resize(icon, size, size));
        label.setBorder(new LineBorder(Colors.Dark, 1));

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.add(label);
        if(!isSelected && row % 2 == 0) panel.setBackground(new Color(240, 240, 240));
        else panel.setBackground(com.getBackground());
        return panel;
    }
    
}
