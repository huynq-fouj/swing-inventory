package swing.inventory.project.components.table.category;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import swing.inventory.project.objects.CategoryObject;

public class CategoryTableModel extends AbstractTableModel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final String[] columnNames = {"ID", "Tên danh mục", "Ghi chú", "Thao tác"};
    private final Class[] columnClass = new Class[] {Object.class, String.class, String.class, Object.class};
    private List<CategoryObject> items;

    public CategoryTableModel(List<CategoryObject> items) {
        this.items = items;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }

    @Override
    public Object getValueAt(int row, int col) {
        CategoryObject item = items.get(row);
        if(col == 0) return item.getCategory_id();
        if(col == 1) return item.getCategory_name();
        if(col == 2) return item.getCategory_notes();
        return null;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return col == 1 || col == 2 || col == 3;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        CategoryObject item = items.get(row);
        if(col == 0) item.setCategory_id((int) value);
        if(col == 1) item.setCategory_name((String) value);
        if(col == 2) item.setCategory_notes((String) value);
    }
    
}
