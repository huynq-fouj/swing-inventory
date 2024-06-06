package swing.inventory.project.components.table.category;

import swing.inventory.project.ConnectionPool;
import swing.inventory.project.components.Dialog;
import swing.inventory.project.components.table.TableActionEvent;
import swing.inventory.project.contexts.ConnectionContext;
import swing.inventory.project.controllers.CategoryController;
import swing.inventory.project.objects.CategoryObject;

public class CategoryActionEvent implements TableActionEvent {

    private CategoryTable table;

    public CategoryActionEvent(CategoryTable table) {
        this.table = table;
    }

    @Override
    public void onView(int row) {}

    @Override
    public void onEdit(int row) {
        CategoryObject item = new CategoryObject();
        item.setCategory_id((int) table.getValueAt(row, 0));
        item.setCategory_name((String) table.getValueAt(row, 1));
        item.setCategory_notes((String) table.getValueAt(row, 2));
        boolean confirm = Dialog.confirm(table, "Lưu thay đổi danh mục: " + item.getCategory_name());
        if(confirm && checkValid(item)) {
            ConnectionPool cp = ConnectionContext.getCP();
            CategoryController cc = new CategoryController(cp);
            if(cp == null) ConnectionContext.setCP(cc.getCP());
            boolean result = cc.editCategory(item);
            cc.releaseConnection();
            if(!result) Dialog.error(table, "Không thể lưu thay đổi!");
            else {
                Dialog.success(table, "Cập nhật thông tin danh mục thành công!");
                table.loadModel();
            }
        }
    }

    private boolean checkValid(CategoryObject item) {
        if(item.getCategory_name() == null || item.getCategory_name().trim().equals("")) {
            Dialog.error(table, "Trường tên danh mục không được để trống!");
            return false;
        }
        return true;
    }

    @Override
    public void onDelete(int row) {
        CategoryObject item = new CategoryObject();
        item.setCategory_id((int) table.getValueAt(row, 0));
        item.setCategory_name((String) table.getValueAt(row, 1));
        boolean confirm = Dialog.confirm(table, "Bạn có chắc chắn muốn xóa danh mục: " + item.getCategory_name(), "Xác nhận xóa");
        if(confirm) {
            ConnectionPool cp = ConnectionContext.getCP();
            CategoryController cc = new CategoryController(cp);
            if(cp == null) ConnectionContext.setCP(cc.getCP());
            boolean result = cc.delCategory(item);
            cc.releaseConnection();
            if(result) {
                Dialog.success(table, "Đã xóa danh mục: " + item.getCategory_name());
                table.loadModel();
            } else {
                Dialog.error(table, "Không thể xóa danh mục: " + item.getCategory_name());
            }
        }
    }
}
