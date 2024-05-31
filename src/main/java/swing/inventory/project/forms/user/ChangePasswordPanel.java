package swing.inventory.project.forms.user;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Utilities;

import swing.inventory.project.ConnectionPool;
import swing.inventory.project.components.Dialog;
import swing.inventory.project.components.button.Button;
import swing.inventory.project.components.button.ButtonType;
import swing.inventory.project.contexts.AuthContext;
import swing.inventory.project.contexts.ConnectionContext;
import swing.inventory.project.controllers.UserController;
import swing.inventory.project.forms.Form;
import swing.inventory.project.objects.UserObject;
import swing.inventory.project.themes.Fonts;

public class ChangePasswordPanel extends Form {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private UserObject user;
    private JPasswordField oldPass;
    private JPasswordField newPass;
    private JPasswordField newPass1;

    public ChangePasswordPanel(UserObject user) {
        this.user = user;
        initUI();
    }

    private void initUI() {
        if(!AuthContext.hasRoleManager() && !AuthContext.isCurrentUser(user)) {
            add(createLabel(
                "Hiện không có chức năng khả dụng với tài khoản này.",
                Fonts.fontLight(20),
                new EmptyBorder(40, 0, 0, 0)
            ));
        } else {
            setLayout(new FlowLayout(FlowLayout.LEFT));
            setPreferredSize(new Dimension(1100, 600));
            add(mainView());
        }
    }

    private JPanel mainView() {
        JPanel panel = createPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = createGridBagConstraints();
        panel.add(createLabel("Đổi mật khẩu",
            Fonts.fontBold(20),
            new EmptyBorder(20, 0, 20, 0)
        ), gbc);
        gbc.gridy = 1;
        panel.add(formGroup(), gbc);
        return panel;
    }

    private JPanel formGroup() {
        JPanel panel = createPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = createGridBagConstraints();
        panel.add(createLabel("Mật khẩu hiện tại: "), gbc);
        gbc.gridy = 1;
        panel.add(createLabel("Mật khẩu mới: "), gbc);
        gbc.gridy = 2;
        panel.add(createLabel("Xác nhận mật khẩu: "), gbc);
        oldPass = createPasswordField();
        JPanel p1 = createPanel(0, 0, 15, 0);
        p1.add(oldPass);
        newPass = createPasswordField();
        JPanel p2 = createPanel(0, 0, 15, 0);
        p2.add(newPass);
        newPass1 = createPasswordField();
        JPanel p3 = createPanel(0, 0, 15, 0);
        p3.add(newPass1);
        gbc.gridx = 1;
        panel.add(p3, gbc);
        gbc.gridy = 0;
        panel.add(p1, gbc);
        gbc.gridy = 1;
        panel.add(p2, gbc);
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        Button btn = createButton("Cập nhật", ButtonType.SUCCESS);
        btn.addActionListener(e -> handleUpdate());
        Button btn1 = createButton("Đặt lại", ButtonType.SECONDARY);
        btn1.addActionListener(e -> resetForm());
        JPanel p4 = createPanel(0, 0, 15, 0);
        p4.setLayout(new FlowLayout(FlowLayout.LEFT));
        p4.add(btn);
        p4.add(btn1);
        panel.add(p4, gbc);
        return panel;
    }

    private boolean checkValid() {
        String p1 = new String(newPass.getPassword());
        String p2 = new String(newPass1.getPassword());
        if(p1 == null || p1.equals("")) {
            Dialog.error(this, "Mật khẩu mới không được để trống!");
            return false;
        } else {
            if(p1.indexOf(" ") != -1) {
                Dialog.error(this, "Mật khẩu mới không được chứa dấu cách!");
                return false;
            } else {
                if(p1.length() < 8) {
                    Dialog.error(this, "Mật khẩu mới phải có độ dài từ 8 ký tự trở lên!");
                    return false;
                } else {
                    if(!p1.equals(p2)) {
                        Dialog.error(this, "Mật khẩu mới và mật khẩu xác nhận không trùng khớp!");
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void handleUpdate() {
        if(checkValid()) {
            user.setUser_password(new String(newPass.getPassword()));
            if(AuthContext.hasRoleManager() || AuthContext.isCurrentUser(user)) {
                ConnectionPool cp = ConnectionContext.getCP();
                UserController uc = new UserController(cp);
                if(cp == null) ConnectionContext.setCP(uc.getCP());
                boolean isExists = uc.isExisting(user.getUser_name(), new String(oldPass.getPassword()));
                boolean check = false;
                if(isExists) check = uc.editUserPassword(user);
                uc.releaseConnection();
                if(!isExists) Dialog.error(this, "Mật khẩu cũ không chính xác!");
                else {
                    if(check) {
                        Dialog.success(this, "Đổi mật khẩu thành công!");
                        resetForm();
                    }
                    else Dialog.success(this, "Đổi mật khẩu không thành công!");
                }
            } else Dialog.error(this, "Bạn không có quyền thực hiện thao tác này!");
        }
    }

    public void resetForm() {
        oldPass.setText("");
        newPass.setText("");
        newPass1.setText("");
    }
    
}
