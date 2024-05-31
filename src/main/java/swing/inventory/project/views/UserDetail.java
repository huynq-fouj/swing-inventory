package swing.inventory.project.views;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import swing.inventory.project.ConnectionPool;
import swing.inventory.project.contexts.ConnectionContext;
import swing.inventory.project.controllers.UserController;
import swing.inventory.project.forms.user.ChangePasswordPanel;
import swing.inventory.project.forms.user.UserSettingPanel;
import swing.inventory.project.forms.user.UserDetailPanel;
import swing.inventory.project.objects.UserObject;
import swing.inventory.project.themes.Colors;
import swing.inventory.project.themes.Fonts;
import swing.inventory.project.themes.IconPack;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GraphicsEnvironment;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class UserDetail extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel mainPanel;
    private UserObject user;

    public UserDetail(int id) {
        user = loadUser(id);
        initComponents();
        switchForm("detail");
        setCurrentClass(this);
    }

    private void setForm(JComponent com) {
        mainPanel.removeAll();
        mainPanel.add(com);
        mainPanel.repaint();
        mainPanel.revalidate();
    }

    private void initComponents() {
        mainPanel = new JPanel();
		mainPanel.setOpaque(false);
        Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		setBounds(0, 0, r.width - 100, r.height - 80);
		setTitle("Inventory Manager");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);

        setJMenuBar(menuBar());

        JPanel panel = new JPanel();
		panel.setBackground(Colors.White);
		panel.add(mainPanel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(panel);
    }

    private void switchForm(String form) {
        switch (form) {
            case "setting":
                setForm(new UserSettingPanel(user));
                break;
            case "password":
                setForm(new ChangePasswordPanel(user));
                break;
            case "detail":        
            default:
                setForm(new UserDetailPanel(user));
                break;
        }
    }

    private JMenuBar menuBar() {
        JMenuBar mb = new JMenuBar();
		mb.setPreferredSize(new Dimension(0, 36));
        mb.add(menu("Thông tin chung", "detail", getIcon("account.png")));
        mb.add(menu("Cài đặt", "setting", getIcon("setting.png")));
        mb.add(menu("Đổi mật khẩu", "password", getIcon("profile.png")));
        return mb;
    }

    private JMenu menu(String label, String form, String iconPath) {
		ImageIcon icon = new ImageIcon(iconPath);
		return menu(label, form, icon);
	}

    private JMenu menu(String label, String form, Icon icon) {
		JMenu menu = new JMenu(label);
		menu.setFont(Fonts.fontLight(13));
		menu.setBorder(new EmptyBorder(0, 5, 0, 5));
		if(form != null && !form.trim().equals("")) 
			menu.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent evt) {
					switchForm(form);
				}
			});
		menu.setIcon(icon);
		return menu;
	}

    private static UserDetail currentClass;

    public static void setCurrentClass(UserDetail o) {
        UserDetail.currentClass = o;
    }

    public static UserDetail getCurrentClass() {
        return UserDetail.currentClass;
    }

    private UserObject loadUser(int id) {
        UserObject u = null;
        ConnectionPool cp = ConnectionContext.getCP();
        UserController uc = new UserController(cp);
        if(cp == null) ConnectionContext.setCP(uc.getCP());
        u = uc.getUserObject(id);
        uc.releaseConnection();
        return u;
    }

    private String getIcon(String path) {
		return IconPack.getIcon(path);
	}

}
