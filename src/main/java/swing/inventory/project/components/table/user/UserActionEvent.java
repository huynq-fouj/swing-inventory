package swing.inventory.project.components.table.user;

import swing.inventory.project.ConnectionPool;
import swing.inventory.project.components.Dialog;
import swing.inventory.project.components.table.TableActionEvent;
import swing.inventory.project.contexts.AuthContext;
import swing.inventory.project.contexts.ConnectionContext;
import swing.inventory.project.controllers.UserController;
import swing.inventory.project.objects.UserObject;
import swing.inventory.project.utils.Utilities;
import swing.inventory.project.views.UserDetail;

public class UserActionEvent implements TableActionEvent {

    private UserTable table;

    public UserActionEvent(UserTable t) {
        table = t;
    }

    @Override
    public void onView(int row) {
        UserDetail o = UserDetail.getCurrentClass();
        if(o != null) o.dispose();
        UserDetail u = new UserDetail((int) table.getValueAt(row, 0));
        u.setVisible(true);
    }

    @Override
    public void onEdit(int row) {
        UserDetail o = UserDetail.getCurrentClass();
        if(o != null) o.dispose();
        UserObject item = new UserObject();
        item.setUser_id((int) table.getValueAt(row, 0));
        item.setUser_image((String) table.getValueAt(row, 1));
        item.setUser_name((String) table.getValueAt(row, 2));
        item.setUser_fullname((String) table.getValueAt(row, 3));
        item.setUser_email((String) table.getValueAt(row, 4));
        item.setUser_phone((String) table.getValueAt(row, 5));
        item.setUser_address((String) table.getValueAt(row, 6));
        item.setUser_notes((String) table.getValueAt(row, 7));
        if(!checkValid(item)) return;
        boolean confirm = Dialog.confirm(table, "Bạn có chắc chắn muốn lưu thay đổi thông tin người dùng: " + item.getUser_name());
        if(confirm) {
            if(!AuthContext.hasRoleManager() && !AuthContext.isCurrentUser(item)) {
                Dialog.error(table, "Không đủ quyền để thực hiện cập nhật thông tin người dùng khác!");
            } else {
                editUser(item);
            }
        }
    }

    @Override
    public void onDelete(int row) {
        UserDetail o = UserDetail.getCurrentClass();
        if(o != null) o.dispose();
        UserObject item = new UserObject();
        item.setUser_id((int) table.getValueAt(row, 0));
        item.setUser_name((String) table.getValueAt(row, 2));
        boolean confirm = Dialog.confirm(table, "Bạn có chắc chắn muốn xóa người dùng: " + item.getUser_name(), "Xác nhận xóa");
        if(confirm) {
            if(!AuthContext.hasRoleManager()) {
                Dialog.error(table, "Không đủ quyền để thực hiện xóa người dùng!");
            } else {
                if(AuthContext.isCurrentUser(item)) {
                    Dialog.error(table, "Không thể xóa tài khoản của chính mình!");
                } else {
                    deleteUser(item);
                }
            }
        }
    }

    private void deleteUser(UserObject item) {
        ConnectionPool cp = ConnectionContext.getCP();
        UserController uc = new UserController(cp);
        if(cp == null) ConnectionContext.setCP(uc.getCP());
        boolean result = uc.delUser(item);
        uc.releaseConnection();
        if(result) {
            Dialog.success(table, "Đã xóa người dùng: " + item.getUser_name());
            table.loadModel();
        } else Dialog.error(table, "Không thể xóa người dùng: " + item.getUser_name());
        item = null;
    }

    private void editUser(UserObject item) {
        ConnectionPool cp = ConnectionContext.getCP();
        UserController uc = new UserController(cp);
        if(cp == null) ConnectionContext.setCP(uc.getCP());
        boolean result = uc.editUser(item);
        if(result && AuthContext.getUser().getUser_id() == item.getUser_id()) {
            UserObject u = uc.getUserObject(item.getUser_id());
            if(u != null) AuthContext.setUser(u);
        }
        uc.releaseConnection();
        if(result) {
            Dialog.success(table, "Cập nhật thông tin người dùng thành công!");
            table.loadModel();
        } else Dialog.error(table, "Không thể cập nhật thông tin người dùng: " + item.getUser_name());
        item = null;
    }

    private boolean checkValid(UserObject item) {
        if(!checkValueString(item.getUser_fullname())) {
            Dialog.error(table, "Trường họ tên không được để trống!");
            return false;
        }
        if(!checkValueString(item.getUser_email())) {
            Dialog.error(table, "Trường hộp thư không được để trống!");
            return false;
        } else {
            if(!Utilities.checkEmail(item.getUser_email())) {
                Dialog.error(table, "Trường hộp thư không hợp lệ!");
                return false;
            }
        }
        if(!checkValueString(item.getUser_phone())) {
            Dialog.error(table, "Trường số điện thoại không được để trống!");
            return false;
        }
        return true;
    }

    private boolean checkValueString(String str) {
        return str != null && !str.trim().equals("");
    }
    
}
