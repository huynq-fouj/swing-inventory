package swing.inventory.project.views.auth;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import swing.inventory.project.components.button.*;
import swing.inventory.project.themes.Colors;
import swing.inventory.project.utils.Resource;

public class AuthPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public AuthPage() {
		initUI();
	}
	
	public void initUI() {
		setTitle("Phần mềm quản lý sản phẩm");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(0, 0, 650, 400);
		setIconImage(new ImageIcon(Resource.loadStaticImagePath("application_icons\\store.png")).getImage());
		setContentPane(createPanel());
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	public JPanel createPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(Colors.White);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1; 
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(textLabel(), gbc);
		gbc.gridy = 1;
		panel.add(imgLabel(), gbc);
		gbc.gridy = 2;
		panel.add(buttonPanel(), gbc);
		return panel;
	}
	
	public JLabel imgLabel() {
		String imgPath = Resource.loadStaticImagePath("analyze.png");
		ImageIcon imageIcon = new ImageIcon(imgPath);
		Image image = imageIcon.getImage().getScaledInstance(350, 232, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		JLabel imgLabel = new JLabel(imageIcon);
		imgLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
		return imgLabel;
	}
	
	public JPanel textLabel() {
		JLabel label = new JLabel("PHẦN MỀM QUẢN LÝ SẢN PHẨM");
		label.setFont(new Font("Arial", Font.BOLD, 20));
		label.setForeground(Colors.Black);
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.setBackground(Colors.White);
		panel.add(label);
		return panel;
	}
	
	public JPanel buttonPanel() {
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.setBackground(Colors.White);
        buttonPanel.add(BtnDangNhapComponent());
        buttonPanel.add(BtnDangKyComponent());
        return buttonPanel;
	}
	
	public JButton BtnDangNhapComponent() {
		JButton btn = BtnComponent("Đăng nhập");
		btn.addActionListener(e -> {
			Login view = new Login();
			view.setVisible(true);
			dispose();
		});
		return btn;
	}
	
	public JButton BtnDangKyComponent() {
		JButton btn = BtnComponent("Đăng ký");
		btn.addActionListener(e -> {
			Signup view = new Signup();
			view.setVisible(true);
			dispose();
		});
		return btn;
	}
	
	public JButton BtnComponent(String label) {
		JButton btn = new Button(label, ButtonType.PRIMARY);
		return btn;
	}
	
}
