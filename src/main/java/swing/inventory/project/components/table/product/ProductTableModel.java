package swing.inventory.project.components.table.product;

import java.util.*;
import java.util.logging.Logger;

import javax.swing.table.AbstractTableModel;

import swing.inventory.project.objects.ProductObject;

public class ProductTableModel extends AbstractTableModel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final String[] columnNames = {"ID", "Hình ảnh", "Tên sản phẩm", "Số lượng", "Đơn giá", "Kích thước", "Đơn vị tính", "Danh mục", "Mô tả", "Thao tác"};
    private final Class<?>[] columnClass = {Object.class, Object.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, Object.class};
    private List<ProductObject> items;

    public ProductTableModel(List<ProductObject> items) {
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
    public Class<?> getColumnClass(int col) {
        return columnClass[col];
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return col != 0 && col != 1;
    }

    @Override
    public Object getValueAt(int row, int col) {
        ProductObject item = items.get(row);
        switch (col) {
            case 0: return item.getProduct_id();
            case 1: return item.getProduct_image();
            case 2: return item.getProduct_name();
            case 3: return item.getProduct_quantity();
            case 4: return item.getProduct_price();
            case 5: return item.getProduct_size();
            case 6: return item.getProduct_unit();
            case 7: return item.getCategory_name();
            case 8: return item.getProduct_details();
            default: return null;
        }
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        ProductObject item = items.get(row);
        switch (col) {
            case 0: item.setProduct_id((int) value);
                break;
            case 1: item.setProduct_image((String) value);
                break;
            case 2: item.setProduct_name((String) value);
                break;
            case 3: 
                try {
                    int q = Integer.parseInt((String) value);
                    item.setProduct_quantity(q);
                } catch (Exception e) {
                    Logger.getLogger(ProductTableModel.class.getName()).info(e.getMessage());
                }
                break;
            case 4: 
                try {
                    double p = Double.parseDouble((String) value);
                    item.setProduct_price(p);
                } catch (Exception e) {
                    Logger.getLogger(ProductTableModel.class.getName()).info(e.getMessage());
                }
                break;
            case 5: item.setProduct_size((String) value);
                break;
            case 6: item.setProduct_unit((String) value);
                break;
            case 7: //item.setProduct_category_id((int) value);
                break;
            case 8: item.setProduct_details((String) value);
                break;
            default: break;
        }
    }
    
}
