package swing.inventory.project.components.table.user;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import swing.inventory.project.objects.UserObject;

public class UserTableModel extends AbstractTableModel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final String[] columnNames = {"ID", "Hình ảnh", "Tên đăng nhập", "Họ tên", "Hộp thư", "Số điện thoại", "Địa chỉ", "Ghi chú", "Thao tác"};
    private final Class[] columnClass = new Class[] {Object.class, Object.class, String.class, String.class, String.class, String.class, String.class, String.class, Object.class};
    private List<UserObject> items;

    public UserTableModel(List<UserObject> items) {
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
        UserObject item = items.get(row);
        switch(col) {
            case 0: return item.getUser_id();
            case 1: return item.getUser_image();
            case 2: return item.getUser_name();
            case 3: return item.getUser_fullname();
            case 4: return item.getUser_email();
            case 5: return item.getUser_phone();
            case 6: return item.getUser_address();
            case 7: return item.getUser_notes();
            default: return null;
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return col != 0 && col != 1 && col != 2;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        UserObject item = items.get(row);
        switch(col) {
            case 0: item.setUser_id((int) value);
                break;
            case 1: item.setUser_image((String) value);
                break;
            case 2: item.setUser_name((String) value);
                break;
            case 3: item.setUser_fullname((String) value);
                break;
            case 4: item.setUser_email((String) value);
                break;
            case 5: item.setUser_phone((String) value);
                break;
            case 6: item.setUser_address((String) value);
                break;
            case 7: item.setUser_notes((String) value);
                break;
            default:
                break;
        }
    }
    
}
