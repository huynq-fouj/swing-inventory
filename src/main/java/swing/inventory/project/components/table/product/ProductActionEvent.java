package swing.inventory.project.components.table.product;

import java.util.List;
import java.util.Optional;

import swing.inventory.project.ConnectionPool;
import swing.inventory.project.components.Dialog;
import swing.inventory.project.components.table.TableActionEvent;
import swing.inventory.project.contexts.ConnectionContext;
import swing.inventory.project.controllers.CategoryController;
import swing.inventory.project.controllers.ProductController;
import swing.inventory.project.enums.CategorySortType;
import swing.inventory.project.objects.CategoryObject;
import swing.inventory.project.objects.ProductObject;
import swing.inventory.project.views.ProductDetail;

public class ProductActionEvent implements TableActionEvent {

    private ProductTable table;
    private List<CategoryObject> categories;

    public ProductActionEvent(ProductTable t) {
        table = t;
        ConnectionPool cp = ConnectionContext.getCP();
        CategoryController cc = new CategoryController(cp);
        if(cp == null) ConnectionContext.setCP(cc.getCP());
        int total = cc.countCategory(null);
        categories = cc.getCategories(null, (short) 1, total, CategorySortType.NAME_ASC);
        cc.releaseConnection();
    }

    @Override
    public void onView(int row) {
        closeFrame();
        ProductDetail t = new ProductDetail((int) table.getValueAt(row, 0));
        t.setVisible(true);
    }

    private boolean checkValid(int row) {
        String name = (String) table.getValueAt(row, 2);
        if(name == null || name.trim().equals("")) {
            Dialog.error(table, "Trường tên sản phẩm không được để trống!");
            return false;
        }
        return true;
    }

    @Override
    public void onEdit(int row) {
        closeFrame();
        if(checkValid(row)) {
            boolean confirm = Dialog.confirm(table, "Bạn có chắc chắn cập nhật thông tin cho sản phẩm này?");
            if(confirm) {
                int id = (int) table.getValueAt(row, 0);
                ConnectionPool cp = ConnectionContext.getCP();
                ProductController pc = new ProductController(cp);
                if(cp == null) ConnectionContext.setCP(pc.getCP());
                ProductObject item = pc.getProduct(id);
                boolean result = false;
                if(item != null) {
                    item.setProduct_name((String) table.getValueAt(row, 2));
                    item.setProduct_quantity((int) table.getValueAt(row, 3));
                    item.setProduct_price((double) table.getValueAt(row, 4));
                    item.setProduct_size((String) table.getValueAt(row, 5));
                    item.setProduct_unit((String) table.getValueAt(row, 6));
                    item.setProduct_details((String) table.getValueAt(row, 8));
                    String name = (String) table.getValueAt(row, 7);
                    Optional<CategoryObject> category = categories.stream()
                        .filter(i -> i.getCategory_name().equals(name))
                            .findFirst();
                    if(category.isPresent()) item.setProduct_category_id(category.get().getCategory_id());
                    result = pc.editProduct(item);
                }
                pc.releaseConnection();
                if(result) {
                    Dialog.success(table, "Cập nhật thông tin sản phẩm thành công!");
                    table.loadModel();
                } else Dialog.error(table, "Cập nhật thông tin sản phẩm không thành công!");
            }
        }
    }

    @Override
    public void onDelete(int row) {
        closeFrame();
        ProductObject item = new ProductObject();
        item.setProduct_id((int) table.getValueAt(row, 0));
        item.setProduct_name((String) table.getValueAt(row, 2));
        boolean confirm = Dialog.confirm(table, "Bạn có chắc chắn muốn xóa sản phẩm: " + item.getProduct_name(), "Xác nhận xóa");
        if(confirm) {
            ConnectionPool cp = ConnectionContext.getCP();
            ProductController pc = new ProductController(cp);
            if(cp == null) ConnectionContext.setCP(pc.getCP());
            boolean result = pc.delProduct(item);
            pc.releaseConnection();
            if(result) {
                Dialog.success(table, "Xóa sản phẩm thành công!");
                table.loadModel();
            } else Dialog.success(table, "Xóa sản không phẩm thành công!");
        }
    }

    private void closeFrame() {
        ProductDetail o = ProductDetail.getCurrentClass();
        if(o != null) o.dispose();
    }
    
}
