package swing.inventory.project.forms.user;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.FlowLayout;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import swing.inventory.project.ConnectionPool;
import swing.inventory.project.components.Dialog;
import swing.inventory.project.components.button.Button;
import swing.inventory.project.components.button.ButtonType;
import swing.inventory.project.contexts.AuthContext;
import swing.inventory.project.contexts.ConnectionContext;
import swing.inventory.project.controllers.UserController;
import swing.inventory.project.forms.Form;
import swing.inventory.project.objects.UserObject;
import swing.inventory.project.themes.Colors;
import swing.inventory.project.themes.Fonts;
import swing.inventory.project.views.UserDetail;

public class UserSettingPanel extends Form {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private UserObject user;
    private JRadioButton userButton;
    private JRadioButton managerButton;

    public UserSettingPanel(UserObject user) {
        this.user = user;
        initUI();
    }

    private void initUI() {
        if(!AuthContext.hasRoleAdmin()) {
            add(createLabel(
                "Hiện không có chức năng khả dụng với tài khoản đang được sử dụng.",
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
        panel.add(createLabel("Chọn vai trò tài khoản",
            Fonts.fontBold(20),
            new EmptyBorder(20, 0, 20, 0)
        ), gbc);
        gbc.gridy = 1;
        panel.add(formGroup(), gbc);
        gbc.gridy = 2;
        panel.add(updateButton(), gbc);
        gbc.gridy = 3;
        panel.add(createLabel("Vô hiệu hóa tài khoản",
            Fonts.fontBold(20),
            new EmptyBorder(20, 0, 10, 0)
        ), gbc);
        gbc.gridy = 4;
        panel.add(disableButton(), gbc);
        gbc.gridy = 5;
        panel.add(createLabel("Xóa tài khoản này",
            Fonts.fontBold(20),
            new EmptyBorder(20, 0, 10, 0)
        ), gbc);
        gbc.gridy = 6;
        panel.add(deleteButton(), gbc);
        return panel;
    }

    private JPanel formGroup() {
        JPanel panel = createPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = createGridBagConstraints();
        userButton = createRadioButton("Người dùng thông thường", 0);
        managerButton = createRadioButton("Quản trị viên", 3);
        ButtonGroup group = new ButtonGroup();
        group.add(userButton);
        group.add(managerButton);
        panel.add(userButton, gbc);
        gbc.gridy = 1;
        panel.add(managerButton, gbc);
        return panel;
    }

    private JRadioButton createRadioButton(String label, int role) {
        JRadioButton btn = new JRadioButton(label);
        btn.setBackground(Colors.White);
        btn.setFont(Fonts.fontLight(14));
        btn.setActionCommand(label);
        btn.setSelected(user.getUser_role() == role);
        btn.setBorder(new EmptyBorder(5, 0, 15, 0));
        btn.setFocusable(false);
        return btn;
    }

    private JPanel updateButton() {
        JPanel panel = createPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        Button btn = createButton("Cập nhật", ButtonType.SUCCESS);
        btn.addActionListener(e -> handleUpdate());
        panel.add(btn);
        return panel;
    }

    private JPanel deleteButton() {
        JPanel panel = createPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        Button btn = createButton("Xóa tài khoản", ButtonType.DANGER);
        btn.addActionListener(e -> handleDelete());
        panel.add(btn);
        return panel;
    }

    private JPanel disableButton() {
        JPanel panel = createPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        Button btn = createButton("Vô hiệu hóa", ButtonType.DANGER);
        panel.add(btn);
        return panel;
    }

    private void handleUpdate() {
        if(AuthContext.isCurrentUser(user)) {
            Dialog.error(this, "Không thể thay đổi quyền hạn của bản thân!");
            return;
        }
        int role = 0;
        String roleName = "Người dùng thông thường";
        if(managerButton.isSelected()) {
            role = 3;
            roleName = "Quản trị viên";
        }
        if(user.getUser_role() == role) return;
        user.setUser_role(role);
        if(AuthContext.hasRoleAdmin()) {
            ConnectionPool cp = ConnectionContext.getCP();
            UserController uc = new UserController(cp);
            if(cp == null) ConnectionContext.setCP(uc.getCP());
            boolean check = uc.editUserPermission(user);
            uc.releaseConnection();
            if(check) Dialog.success(this, "Đã cập nhật quyền hạn người dùng thành: " + roleName);
            else Dialog.error(this, "Cập nhật không thành công!");
        } else  Dialog.error(this, "Bạn không có quyền thực hiện thành động này!");
    }

    private void handleDelete() {
        if(AuthContext.hasRoleManager()) {
            if(!AuthContext.isCurrentUser(user)) {
                String name = user.getUser_name();
                ConnectionPool cp = ConnectionContext.getCP();
                UserController uc = new UserController(cp);
                if(cp == null) ConnectionContext.setCP(uc.getCP());
                boolean check = uc.delUser(user);
                uc.releaseConnection();
                if(check) {
                    handleExit();
                    UserListPanel o = UserListPanel.getCurrentPage();
                    Dialog.success(o, "Đã xóa người dùng: " + name);
                    o.getTable().loadModel();
                    o.getPagination().reload();
                } else Dialog.error(this, "Xóa người dùng không thành công!");
            } else Dialog.error(this, "Không thể tự xóa tài khoản của bản thân!");
        } else  Dialog.error(this, "Bạn không có quyền thực hiện thành động này!");
    }

    private void handleExit() {
        UserDetail o = UserDetail.getCurrentClass();
        o.dispose();
    }
    
}
