package swing.inventory.project.views;

import java.util.Map;
import java.util.logging.*;

import java.awt.event.*;
import java.awt.Frame;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.GraphicsEnvironment;

import javax.swing.Icon;
import javax.swing.JMenu;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JComponent;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import swing.inventory.project.utils.*;
import swing.inventory.project.forms.*;
import swing.inventory.project.forms.product.ProductForm;
import swing.inventory.project.forms.user.UserForm;
import swing.inventory.project.themes.*;
import swing.inventory.project.views.auth.AuthPage;
import swing.inventory.project.contexts.AuthContext;


public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(Main.class.getName());
	private JPanel mainPanel;
	private boolean isFullScreen = false;
	private Map<String, String> appData;

	public Main() {
		appData = AppData.getData();
		initComponents();
		switchForm("home");
		setCurrentFrame(this);
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
		
		if(appData.get(AppDataKey.ISFULLSCREEN) != null && appData.get(AppDataKey.ISFULLSCREEN).equals("true")) {
			isFullScreen = true;
			setExtendedState(Frame.MAXIMIZED_BOTH);
		}
		if(appData.get(AppDataKey.ICONPACK) != null && appData.get(AppDataKey.ICONPACK).equals("COLOR_ICON")) IconPack.setPack(IconPack.COLOR_ICON);
		Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		setBounds(0, 0, r.width, r.height);
		setTitle("Inventory Manager");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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

	public void switchForm(String form) {
		switch (form) {
			case "home":
				setForm(new HomeForm());
				logger.info("Switch to home page");
				break;
			case "user":
				setForm(new UserForm());
				logger.info("Switch to user page");
				break;
			case "add-user":
				setForm(new UserForm("add"));
				logger.info("Switch to add user page");
				break;
			case "product":
				setForm(new ProductForm());
				logger.info("Switch to product page");
				break;
			case "add-product":
				setForm(new ProductForm("add"));
				logger.info("Switch to add product page");
				break;
			case "category":
				setForm(new CategoryForm());
				logger.info("Switch to category page");
				break;
			case "add-category":
				setForm(new CategoryForm());
				logger.info("Switch to add category page");
				break;
			case "my-account":
				setForm(new AccountForm());
				logger.info("Switch to personal information page");
				break;
			default:
				break;
		}
	}

	private JMenuBar menuBar() {
		final String listPng = "list.png";
		final String plusPng = "plus.png";
		JMenuBar mb = new JMenuBar();
		mb.setPreferredSize(new Dimension(0, 36));

		mb.add(menu("Trang chủ", "home", getIcon("home.png")));

		JMenu product = menu("Sản phẩm", null, getIcon("product.png"));
		product.add(menuItem("Danh sách sản phẩm", "product", getIcon(listPng)));
		product.add(menuItem("Thêm sản phẩm", "add-product", getIcon(plusPng)));
		product.add(exportProduct());
		mb.add(product);

		JMenu category = menu("Danh mục sản phẩm", null, getIcon("category.png"));
		category.add(menuItem("Danh sách danh mục", "category", getIcon(listPng)));
		category.add(exportCategory());
		mb.add(category);

		JMenu user = menu("Người dùng", null, getIcon("users.png"));
		user.add(menuItem("Danh sách người dùng", "user", getIcon(listPng)));
		user.add(menuItem("Thêm người dùng", "add-user", getIcon(plusPng)));
		user.add(exportUser());
		mb.add(user);

		JMenu myAccount = menu("Tài khoản của tôi", null, getIcon("profile.png"));
		myAccount.add(menuItem("Thông tin cá nhân", "my-account", getIcon("account.png")));
		myAccount.add(logOut());
		mb.add(myAccount);

		mb.add(menu("Trợ giúp", null, getIcon("help.png")));

		JMenu setting = menu("Cài đặt", null, getIcon("setting.png"));
		setting.add(menuItem("Cài đặt chung", null, getIcon("setting.png")));
		setting.add(fullScreen());
		setting.add(changeIcon());
		setting.add(exitFrame());
		mb.add(setting);

		return mb;
	}

	private JMenuItem logOut() {
		JMenuItem item = menuItem("Đăng xuất", null, getIcon("logout.png"));
		item.addActionListener(e -> {
			AuthContext.logOut();
			AuthPage authPage = new AuthPage();
			authPage.setVisible(true);
			this.dispose();
		});
		return item;
	}

	private JMenuItem fullScreen() {
		String name1 = "Toàn màn hình";
		String icon1 = "expand-screen.png";
		String name2 = "Thu nhỏ màn hình";
		String icon2 = "minimize-screen.png";
		String name = isFullScreen ? name2 : name1;
		String icon = isFullScreen ? icon2 : icon1;
		JMenuItem item = menuItem(name, null, getIcon(icon));
		item.addActionListener(e -> {
			isFullScreen = !isFullScreen;
			appData.put(AppDataKey.ISFULLSCREEN, String.valueOf(isFullScreen));
			AppData.save(appData);
			if(isFullScreen) {
				item.setText(name2);
				item.setIcon(new ImageIcon(getIcon(icon2)));
				setExtendedState(Frame.MAXIMIZED_BOTH);
				logger.info("Expand the screen.");
			} else {
				item.setText(name1);
				item.setIcon(new ImageIcon(getIcon(icon1)));
				setExtendedState(Frame.NORMAL);
				logger.info("Minimize the screen.");
			}
		});
		return item;
	}

	public JMenuItem exportCategory() {
		JMenuItem item = menuItem("Xuất file excel", null, getIcon("export.png"));
		item.addActionListener(e -> ExportFileExcel.exportCategory(Chooser.chooserExport("category"), this));
		return item;
	}

	public JMenuItem exportProduct() {
		JMenuItem item = menuItem("Xuất file excel", null, getIcon("export.png"));
		item.addActionListener(e -> ExportFileExcel.exportProduct(Chooser.chooserExport("product"), this));
		return item;
	}

	public JMenuItem exportUser() {
		JMenuItem item = menuItem("Xuất file excel", null, getIcon("export.png"));
		item.addActionListener(e -> ExportFileExcel.exportUser(Chooser.chooserExport("user"), this));
		return item;
	}

	public JMenuItem changeIcon() {
		boolean isFI = IconPack.getPack().equals(IconPack.FLAT_ICON);
		String name1 = "Color icons";
		String name2 = "Dark icons";
		String name = isFI ? name1 : name2;
		JMenuItem item = menuItem(name, null, getIcon("change-icon.png"));
		item.addActionListener(e -> {
			if(!isFI) {
				item.setText(name1);
				IconPack.setPack(IconPack.FLAT_ICON);
				appData.put(AppDataKey.ICONPACK, "FLAT_ICON");
				AppData.save(appData);
				dispose();
				new Main();
				logger.info("Change to dark icon.");
			} else {
				item.setText(name2);
				IconPack.setPack(IconPack.COLOR_ICON);
				appData.put(AppDataKey.ICONPACK, "COLOR_ICON");
				AppData.save(appData);
				dispose();
				new Main();
				logger.info("Change to color cion.");
			}
		});
		return item;
	}

	private JMenuItem exitFrame() {
		JMenuItem item = menuItem("Thoát", null, getIcon("exit.png"));
		item.addActionListener(e -> dispose());
		return item;
	}

	private JMenuItem menuItem(String label, String form, String icon) {
		JMenuItem item = new JMenuItem(label);
		item.setFont(Fonts.fontLight(13));
		if(form != null && !form.trim().equals(""))
			item.addActionListener(e -> switchForm(form));
		item.setIcon(new ImageIcon(icon));
		return item;
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

	private String getIcon(String path) {
		return IconPack.getIcon(path);
	}

	private static Main currentFrame;

	private static void setCurrentFrame(Main frame) {
		currentFrame = frame;
	}

	public static Main getCurrentFrame() {
		return currentFrame;
	}
	
}
