package swing.inventory.project.forms.user;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import swing.inventory.project.ConnectionPool;
import swing.inventory.project.components.Dialog;
import swing.inventory.project.components.RoundedBorder;
import swing.inventory.project.components.button.Button;
import swing.inventory.project.components.button.ButtonType;
import swing.inventory.project.contexts.AuthContext;
import swing.inventory.project.contexts.ConnectionContext;
import swing.inventory.project.controllers.UserController;
import swing.inventory.project.forms.Form;
import swing.inventory.project.objects.UserObject;
import swing.inventory.project.themes.Colors;
import swing.inventory.project.themes.Fonts;
import swing.inventory.project.utils.ImageUtil;
import swing.inventory.project.utils.Resource;
import swing.inventory.project.utils.Utilities;
import swing.inventory.project.views.UserDetail;

public class UserDetailPanel extends Form {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private UserObject user;
    private JTextField userFullName;
    private JTextField userEmail;
    private JTextField userPhone;
    private JTextField userAddress;
    private JTextArea userNotes;
    private JLabel imagePreview;
    private BufferedImage image;
    
    public UserDetailPanel(UserObject user) {
        this.user = user;
        initUI();
    }

    private void initUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = createGridBagConstraints();
        add(formControl(), gbc);
        gbc.gridy = 1;
        add(bottomBtn(), gbc);
    }

    private JPanel formControl() {
        JPanel panel = createPanel(20, 0, 0, 0);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = createGridBagConstraints();
        gbc.gridheight = 6;
        panel.add(avatar(), gbc);
        gbc.gridx = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        panel.add(createLabel("Thông tin tài khoản",
            Fonts .fontBold(18),
            new EmptyBorder(0, 30, 20, 0)
        ), gbc);
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        panel.add(userIdField(), gbc);
        gbc.gridx = 2;
        panel.add(userNameField(), gbc);
        gbc.gridx = 3;
        panel.add(userModifiedField(), gbc);
        gbc.gridy = 2;
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        panel.add(createLabel("Thông tin người dùng", 
            Fonts.fontBold(18),
            new EmptyBorder(0, 30, 20, 0)
        ), gbc);
        gbc.gridwidth = 1;
        gbc.gridy = 3;
        panel.add(userFullnameField(), gbc);
        gbc.gridx = 2;
        panel.add(userEmailField(), gbc);
        gbc.gridx = 3;
        panel.add(userPhoneField(), gbc);
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.gridx = 1;
        panel.add(userAddressField(), gbc);
        gbc.gridy = 5;
        gbc.gridheight = 2;
        panel.add(userNotesField(), gbc);
        return panel;
    }

    private JPanel avatar() {
        JPanel panel = createPanel();
        panel.setBorder(new EmptyBorder(0, 0, 0, 20));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = createGridBagConstraints();
        panel.add(createLabel("Ảnh Đại Diện",
            Fonts.fontBold(18),
            new EmptyBorder(0, 0, 15, 0)
        ), gbc);
        String path = Resource.loadStaticImagePath("default.png");
        if(user.getUser_image() != null && !user.getUser_image().equals("")) {
            path = Resource.loadUploadImagePath(user.getUser_image());
        }
        ImageIcon img = ImageUtil.resize(new ImageIcon(path), 250, 250);
        imagePreview = createLabel();
        imagePreview.setIcon(img);
        imagePreview.setBorder(new LineBorder(Colors.Black, 1));
        gbc.gridy = 1;
        panel.add(imagePreview, gbc);
        JPanel sub = createPanel();
        sub.setLayout(new FlowLayout(FlowLayout.CENTER));
        Button btn = createButton("Chọn ảnh", ButtonType.SECONDARY, 10);
        btn.addActionListener(e -> handleChooseImg());
        sub.add(btn);
        gbc.gridy = 3;
        panel.add(sub, gbc);
        JLabel label = createLabel("Hỗ trợ định dạng " + String.join(", ", ImageUtil.EXTS) + ".");
        label.setFont(Fonts.fontItalic(11));
        gbc.gridy = 2;
        panel.add(label, gbc);
        return panel;
    }

    private JPanel userIdField() {
		JPanel panel = createPanel(0, 30, 20, 0);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = createGridBagConstraints();
		panel.add(createLabel("ID"), gbc);
		JTextField userID = createTextField(user.getUser_id() + "");
        userID.setEnabled(false);
		gbc.gridy = 1;
		panel.add(userID, gbc);
		return panel;
	}

    private JPanel userNameField() {
		JPanel panel = createPanel(0, 30, 20, 0);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = createGridBagConstraints();
		panel.add(createLabel("Tên đăng nhập"), gbc);
		JTextField userName = createTextField(user.getUser_name());
        userName.setEnabled(false);
		gbc.gridy = 1;
		panel.add(userName, gbc);
		return panel;
	}

    private JPanel userModifiedField() {
		JPanel panel = createPanel(0, 30, 20, 0);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = createGridBagConstraints();
		panel.add(createLabel("Cập nhật lần cuối"), gbc);
		JTextField userModifiedAt = createTextField(user.getUser_created_at());
        userModifiedAt.setEnabled(false);
		gbc.gridy = 1;
		panel.add(userModifiedAt, gbc);
		return panel;
	}

    private JPanel userEmailField() {
		JPanel panel = createPanel(0, 30, 20, 0);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = createGridBagConstraints();
		panel.add(createLabel("Hộp thư"), gbc);
		userEmail = createTextFieldPlaceholder(user.getUser_email(), "example@gmail.com");
		gbc.gridy = 1;
		panel.add(userEmail, gbc);
		return panel;
	}

    private JPanel userFullnameField() {
		JPanel panel = createPanel(0, 30, 20, 0);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = createGridBagConstraints();
		panel.add(createLabel("Họ tên"), gbc);
		userFullName = createTextFieldPlaceholder(user.getUser_fullname(), "Nhập tên đăng nhập");
		gbc.gridy = 1;
		panel.add(userFullName, gbc);
		return panel;
	}

    private JPanel userPhoneField() {
		JPanel panel = createPanel(0, 30, 20, 0);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = createGridBagConstraints();
		panel.add(createLabel("Số điện thoại"), gbc);
		userPhone = createTextFieldPlaceholder(user.getUser_phone(), "0xxx xxx xxx");
		gbc.gridy = 1;
		panel.add(userPhone, gbc);
		return panel;
	}

    private JPanel userAddressField() {
		JPanel panel = createPanel(0, 30, 20, 0);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = createGridBagConstraints();
		panel.add(createLabel("Địa chỉ"), gbc);
		userAddress = createTextFieldPlaceholder(user.getUser_address(), "Nhập địa chỉ");
		gbc.gridy = 1;
		panel.add(userAddress, gbc);
		return panel;
	}

    private JPanel userNotesField() {
        JPanel panel = this.createPanel(0, 30, 0, 0);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = createGridBagConstraints();
		panel.add(this.createLabel("Ghi chú"), gbc);
		userNotes = createTextAreaPLaceholder(5, 10, user.getUser_notes(), "Nhập nội dung");
		JScrollPane scroll = new JScrollPane(userNotes);
		scroll.setBorder(new RoundedBorder(0, 1));
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		gbc.gridy = 1;
		panel.add(scroll, gbc);
		return panel;
    }

    private JPanel bottomBtn() {
        JPanel panel = createPanel();
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        Button upd = createButton("Lưu thay đổi", ButtonType.SUCCESS);
        upd.addActionListener(e -> handleCreate());
        Button del = createButton("Xóa người dùng", ButtonType.DANGER);
        del.addActionListener(e -> handleDelete());
        Button exit = createButton("Thoát", ButtonType.SECONDARY);
        exit.addActionListener(e -> handleExit());
        panel.add(upd);
        panel.add(del);
        panel.add(exit);
        return panel;
    }

    private void handleChooseImg() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", ImageUtil.EXTS);
        chooser.setFileFilter(filter);
        chooser.showOpenDialog(this);
        File f = chooser.getSelectedFile();
        if(f == null) return;
        if(!ImageUtil.checkImage(f)) {
            String exts = String.join(", ", ImageUtil.EXTS);
            Dialog.error(this, "Chỉ hỗ trợ dịnh dạng ảnh " + exts + "!");
            return;
        }

        try {
            image = ImageUtil.resize(ImageIO.read(f), 250, 250);
            ImageIcon img = new ImageIcon(image);
            imagePreview.setIcon(img);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkValid() {
        String fullname = userFullName.getText();
        String email = userEmail.getText();
        String phone = userPhone.getText();
        if(fullname == null || fullname.trim().equals("")) {
            Dialog.error(this, "Trường  họ tên không được để trống!");
            return false;
        }
        if(email == null || email.trim().equals("")) {
            Dialog.error(this, "Trường hộp thư không được để trống!");
            return false;
        } else {
            if(!Utilities.checkEmail(email.trim())) {
                Dialog.error(this, "Trường hộp thư không hợp lệ!");
                return false;
            }
        }
        if(phone == null || phone.trim().equals("")) {
            Dialog.error(this, "Trường số điện thoại không được để trống!");
            return false;
        }
        return true;
    }

    private void handleCreate() {
        if(!AuthContext.hasRoleManager() && !AuthContext.isCurrentUser(user)) {
            Dialog.error(this, "Bạn không có quyền thực hiện thành động này!");
            return;
        }
        if(checkValid()) {
            user.setUser_fullname(userFullName.getText().trim());
            user.setUser_email(userEmail.getText().trim());
            user.setUser_notes(userNotes.getText());
            user.setUser_address(userAddress.getText().trim());
            user.setUser_phone(userPhone.getText().trim());
            String imageName = ImageUtil.saveImage(image);
            String oldImg = user.getUser_image();
            if(imageName != null && !imageName.equals("")) user.setUser_image(imageName);
            ConnectionPool cp = ConnectionContext.getCP();
            UserController uc = new UserController(cp);
            if(cp == null) ConnectionContext.setCP(uc.getCP());
            boolean check = uc.editUser(user);
            uc.releaseConnection();
            if(check) {
                Dialog.success(this, "Đã cập nhật thông tin người dùng!");
                UserListPanel.getCurrentPage().getTable().loadModel();
                if(!user.getUser_image().equals(oldImg)) ImageUtil.removeImage(oldImg);
            } else Dialog.error(this, "Không thể lưu thay đổi thông tin người dùng!");
        }
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
