package swing.inventory.project.components.table.product;

import swing.inventory.project.ConnectionPool;
import swing.inventory.project.components.Dialog;
import swing.inventory.project.components.table.TableActionEvent;
import swing.inventory.project.contexts.ConnectionContext;
import swing.inventory.project.controllers.ProductController;
import swing.inventory.project.objects.ProductObject;
import swing.inventory.project.views.ProductDetail;

public class ProductActionEvent implements TableActionEvent {

    private ProductTable table;

    public ProductActionEvent(ProductTable t) {
        table = t;
    }

    @Override
    public void onView(int row) {
        closeFrame();
        ProductDetail t = new ProductDetail((int) table.getValueAt(row, 0));
        t.setVisible(true);
    }

    @Override
    public void onEdit(int row) {
        closeFrame();
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
        item = null;
    }

    private void closeFrame() {
        ProductDetail o = ProductDetail.getCurrentClass();
        if(o != null) o.dispose();
    }
    
}
