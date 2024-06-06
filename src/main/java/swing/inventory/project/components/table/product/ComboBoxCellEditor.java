package swing.inventory.project.components.table.product;

import java.awt.Component;
import java.awt.Dimension;
import java.util.List;
import java.util.Optional;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import swing.inventory.project.ConnectionPool;
import swing.inventory.project.contexts.ConnectionContext;
import swing.inventory.project.controllers.CategoryController;
import swing.inventory.project.enums.CategorySortType;
import swing.inventory.project.objects.CategoryObject;
import swing.inventory.project.themes.Colors;
import swing.inventory.project.themes.Fonts;

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
        String name = (String) jtable.getValueAt(row, col);
        int width = jtable.getColumnModel().getColumn(col).getPreferredWidth();
        int height = jtable.getRowHeight();
        ConnectionPool cp = ConnectionContext.getCP();
        CategoryController cc = new CategoryController(cp);
        if(cp == null) ConnectionContext.setCP(cc.getCP());
        int total = cc.countCategory(null);
        List<CategoryObject> categories = cc.getCategories(null, (short) 1, total, CategorySortType.NAME_ASC);
        cc.releaseConnection();
        CategoryObject[] items = categories.toArray(new CategoryObject[total]);
        Optional<CategoryObject> item = categories.stream()
            .filter(i -> i.getCategory_name().equals(name))
                .findFirst();
        JComboBox<CategoryObject> category = new JComboBox<>(items);
        if(item.isPresent()) category.setSelectedItem(item.get());
        else category.setSelectedIndex(0);
        category.setPreferredSize(new Dimension(width, height));
        category.setFont(Fonts.fontLight(15));
        category.setFocusable(false);
        category.setBorder(new LineBorder(Colors.Black));
        category.addItemListener(e -> {
            CategoryObject c = (CategoryObject) category.getSelectedItem();
            jtable.setValueAt(c.getCategory_name(), row, col);
        });
        return category;
    }
    
}
