package swing.inventory.project.forms;

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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import swing.inventory.project.components.button.ButtonType;
import swing.inventory.project.ConnectionPool;
import swing.inventory.project.components.Dialog;
import swing.inventory.project.components.RoundedBorder;
import swing.inventory.project.components.button.Button;
import swing.inventory.project.contexts.AuthContext;
import swing.inventory.project.contexts.ConnectionContext;
import swing.inventory.project.controllers.UserController;
import swing.inventory.project.objects.UserObject;
import swing.inventory.project.themes.Colors;
import swing.inventory.project.themes.Fonts;
import swing.inventory.project.utils.*;

public class AccountForm extends Form {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JTextField userName;
    private JTextField userFullname;
    private JTextField userEmail;
    private JTextField userAddress;
    private JTextField userPhone;
    private JTextArea userNotes;
    private JLabel imagePreview;
    private UserObject user;
    private BufferedImage image;
    
    public AccountForm() {
        user = AuthContext.getUser();
        initUI();
    }

    private void initUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = createGridBagConstraints();
        add(avatar(), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        gbc.gridheight = 3;
        add(formControl(), gbc);
    }

    private JPanel avatar() {
        JPanel panel = createPanel();
        panel.setBorder(new EmptyBorder(20, 0, 0, 40));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = createGridBagConstraints();
        panel.add(createTitle("Ảnh Đại Diện"), gbc);
        String path = Resource.loadStaticImagePath("default.png");
        if(user.getUser_image() != null && !user.getUser_image().equals(""))
            path = Resource.loadUploadImagePath(user.getUser_image());
        ImageIcon img = ImageUtil.resize(new ImageIcon(path), 250, 250);
        imagePreview = createLabel();
        imagePreview.setIcon(img);
        imagePreview.setBorder(new LineBorder(Colors.Black, 1));
        gbc.gridy = 1;
        panel.add(imagePreview, gbc);
        JPanel sub = createPanel();
        sub.setLayout(new FlowLayout(FlowLayout.CENTER));
        Button btn = new Button("Chọn ảnh", ButtonType.SECONDARY, 0);
        btn.setArcHeight(10);
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

    private JPanel formControl() {
        JPanel panel = createPanel();
        panel.setBorder(new EmptyBorder(20, 10, 0, 0));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = createGridBagConstraints();
        panel.add(createTitle("Thông Tin Tài Khoản", 5, 30, 20, 0), gbc);
        gbc.gridy = 1;
        panel.add(createPanelField(
                "Tên đăng nhập",
                user.getUser_name(),
                userName,
                false),
            gbc);
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        JLabel label = createLabel("Bạn đã tham gia hệ thống từ " + user.getUser_created_at() + ", với " + user.getUser_logined() + " lần đăng nhập.");
        label.setBorder(new EmptyBorder(0, 35, 10, 0));
        panel.add(label, gbc);
        gbc.gridwidth = 1;
        gbc.gridy = 3;
        panel.add(createTitle("Thông Tin Cá Nhân", 5, 30, 20, 0), gbc);
        gbc.gridy = 4;
        panel.add(userFullnameField(),gbc);
        gbc.gridx = 1;
        panel.add(userEmailField(),gbc);
        gbc.gridx = 2;
        panel.add(userPhoneField(),gbc); 
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.gridy = 5;
        panel.add(userAddressField(),gbc); 
        gbc.gridy = 6;
        gbc.gridheight = 2;
        panel.add(userNotesField(), gbc);
        gbc.gridheight = 1;
        gbc.gridy = 8;
        panel.add(btnUpdate(), gbc);
        return panel;
    }

    private JPanel userFullnameField() {
		JPanel panel = this.createPanelField();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = createGridBagConstraints();
		panel.add(this.createLabel("Họ tên"), gbc);
		userFullname = this.createTextFieldPlaceholder(user.getUser_fullname(), "Nhập họ và tên");
		gbc.gridy = 1;
		panel.add(userFullname, gbc);
		return panel;
	}

    private JPanel userPhoneField() {
		JPanel panel = this.createPanelField();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = createGridBagConstraints();
		panel.add(this.createLabel("Số điện thoại"), gbc);
		userPhone = this.createTextFieldPlaceholder(user.getUser_phone(), "0xxx xxx xxx");
		gbc.gridy = 1;
		panel.add(userPhone, gbc);
		return panel;
	}

    private JPanel userAddressField() {
		JPanel panel = this.createPanelField();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = createGridBagConstraints();
		panel.add(this.createLabel("Địa chỉ"), gbc);
		userAddress = this.createTextFieldPlaceholder(user.getUser_address(), "Nhập địa chỉ");
		gbc.gridy = 1;
		panel.add(userAddress, gbc);
		return panel;
	}

    private JPanel userEmailField() {
		JPanel panel = this.createPanelField();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = createGridBagConstraints();
		panel.add(this.createLabel("Hộp thư"), gbc);
		userEmail = this.createTextFieldPlaceholder(user.getUser_email(), "example@gmail.com");
		gbc.gridy = 1;
		panel.add(userEmail, gbc);
		return panel;
	}

    private JPanel userNotesField() {
        JPanel panel = this.createPanelField();
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

    private JPanel btnUpdate() {
        JPanel panel = createPanelField(new EmptyBorder(0, 30, 10, 0));
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        Button btn = new Button("Lưu thay đổi", ButtonType.SUCCESS, 0);
        btn.addActionListener(e -> handleUpdate());
        btn.setArcHeight(10);
        panel.add(btn);
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

    private boolean isChange() {
        if(image != null && !ImageUtil.isEmpty(image)) return true;
        if(!userFullname.getText().trim().equals(user.getUser_fullname())) return true;
        if(!userEmail.getText().trim().equals(user.getUser_email())) return true;
        if(!userPhone.getText().trim().equals(user.getUser_phone())) return true;
        if(!userAddress.getText().trim().equals(user.getUser_address())) return true;
        if(!userNotes.getText().equals(user.getUser_notes())) return true;
        return false;
    }

    private boolean checkValid() {
        if(userFullname.getText().trim().equals("")) {
            Dialog.error(this, "Trường họ tên không được để trống!");
            return false;
        }
        String email = userEmail.getText().trim();
        if(email.equals("")) {
            Dialog.error(this, "Trường hộp thư không được để trống!");
            return false;
        } else {
            if(!Utilities.checkEmail(email)) {
                Dialog.error(this, "Trường hộp thư không hợp lệ!");
                return false;
            }
        }
        if(userPhone.getText().trim().equals("")) {
            Dialog.error(this, "Trường số điện thoại không được để trống!");
            return false;
        }
        return true;
    }

    private void handleUpdate() {
        if(isChange() && checkValid()) {
            user.setUser_address(userAddress.getText().trim());
            user.setUser_email(userEmail.getText().trim());
            user.setUser_fullname(userFullname.getText().trim());
            user.setUser_phone(userPhone.getText().trim());
            user.setUser_notes(userNotes.getText());
            String imageName = ImageUtil.saveImage(image);
            String oldImg = user.getUser_image();
            if(imageName != null && !imageName.equals("")) {
                user.setUser_image(imageName);
                ImageUtil.removeImage(oldImg);
            }
            ConnectionPool cp = ConnectionContext.getCP();
            UserController uc = new UserController(cp);
            if(cp == null) ConnectionContext.setCP(uc.getCP());
            boolean check = uc.editUser(user);
            uc.releaseConnection();
            if(check) Dialog.success(this, "Cập nhật thông tin thành công!");
            else Dialog.success(this, "Cập nhật thông tin không thành công!");
        }
    }

}
