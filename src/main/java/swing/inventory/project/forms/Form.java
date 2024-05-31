package swing.inventory.project.forms;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import swing.inventory.project.components.RoundedBorder;
import swing.inventory.project.components.button.Button;
import swing.inventory.project.components.button.ButtonType;
import swing.inventory.project.components.text.PlaceholderTextArea;
import swing.inventory.project.components.text.PlaceholderTextField;
import swing.inventory.project.themes.Colors;
import swing.inventory.project.themes.Fonts;

public class Form extends JPanel {

    /**
     *
     */
    public static final long serialVersionUID = 1L;
    
    public Form() {
        this.setBackground(Colors.White);
    }

    public JPanel createTitle(String title) {
		return createTitle(title, new EmptyBorder(5, 0, 20, 0));
	}

	public GridBagConstraints createGridBagConstraints() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		return gbc;
	}

    public JPanel createTitle(String title, Border border) {
		JPanel panel = this.createPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel label = new JLabel(title);
		label.setFont(Fonts.fontBold(24));
		label.setBorder(border);
		panel.add(label);
		return panel;
	}

	public JPanel createTitle(String title, int top, int left, int bottom, int right) {
		JPanel panel = this.createPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel label = new JLabel(title);
		label.setFont(Fonts.fontBold(24));
		label.setBorder(new EmptyBorder(top, left, bottom, right));
		panel.add(label);
		return panel;
	}

	public JPanel notesField(String label, String value, JTextArea area) {
		JPanel panel = this.createPanelField();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = createGridBagConstraints();
		panel.add(this.createLabel(label), gbc);
		area = new JTextArea(5, 10);
		area.setFont(new Font("Tahoma", Font.PLAIN, 15));
		area.setText(value);
		area.setWrapStyleWord(true);
		area.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(area);
		scroll.setBorder(new RoundedBorder(0, 1));
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		gbc.gridy = 1;
		panel.add(scroll, gbc);
		return panel;
	}

	public JPanel createPanelField(String label, String value, JTextField input, boolean enable) {
		return createPanelField(label, value, input, enable, new EmptyBorder(0, 35, 20, 0));
	}

	public JPanel createPanelField(String label, String value, JTextField input, boolean enable, EmptyBorder border) {
		JPanel panel = this.createPanelField(border);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = createGridBagConstraints();
		panel.add(this.createLabel(label), gbc);
		input = this.createTextField(value);
		input.setEnabled(enable);
		gbc.gridy = 1;
		panel.add(input, gbc);
		return panel;
	}

	public JComboBox<String> createComboBoxTotal(String[] items) {
		JComboBox ccb = new JComboBox<>(items);
        ccb.setSelectedIndex(0);
		ccb.setFont(Fonts.fontLight(15));
		int width = (int) ccb.getPreferredSize().getWidth();
		ccb.setPreferredSize(new Dimension(width, 38));
        ccb.setToolTipText("Số lượng bản ghi trên một trang.");
		ccb.setFocusable(false);
		ccb.setBorder(new LineBorder(Colors.Black));
		return ccb;
	}

	public JPanel createPanelField() {
		return createPanelField(new EmptyBorder(0, 35, 20, 0));
	}

    public JPanel createPanelField(EmptyBorder border) {
		JPanel panel = this.createPanel();
		panel.setBorder(border);
		return panel;
	}

	public JPanel createPanel(int t, int l, int b, int r) {
		return createPanel(new EmptyBorder(t, l, b, r));
	}

	public JPanel createPanel() {
		return createPanel(new EmptyBorder(0, 0, 0, 0));
	}

    public JPanel createPanel(Border border) {
		JPanel panel = new JPanel();
		panel.setForeground(Colors.Black);
		panel.setBackground(Colors.White);
		panel.setBorder(border);
		return panel;
	}

    public JTextField createTextField(String value) {
		JTextField field = new JTextField();
		field.setBorder(new RoundedBorder(0));
		field.setFont(Fonts.fontLight(15));
		field.setPreferredSize(new Dimension(275, 38));
		field.setText(value);
		return field;
	}

	public JTextField createTextFieldPlaceholder(String value, String placeholder) {
		JTextField field = createTextFieldPlaceholder(placeholder);
		if(value != null && !value.equals("")) {
			field.setForeground(Colors.Black);
			field.setText(value);
		}
		return field;
	}

	public JTextField createTextFieldPlaceholder(String placeholder) {
		JTextField field = new PlaceholderTextField(placeholder);
		field.setBorder(new RoundedBorder(0));
		field.setFont(Fonts.fontLight(15));
		field.setPreferredSize(new Dimension(275, 38));
		return field;
	}

    public JTextField createTextField(String value, int width, int height) {
		JTextField field = createTextField(value);
		field.setPreferredSize(new Dimension(width, height));
		return field;
	}

    public JTextField createTextField(String value, int width, int height, int radius) {
		JTextField field = createTextField(value, width, height);
		field.setBorder(new RoundedBorder(radius));
		return field;
	}

    public JTextField createTextField(String value, int width, int height, Font font) {
		JTextField field = createTextField(value, width, height);
		field.setFont(font);
		return field;
	}

    public JTextField createTextField(String value, int width, int height, int radius, Font font) {
		JTextField field = createTextField(value, width, height, font);
		field.setBorder(new RoundedBorder(radius));
		return field;
	}

    public JPasswordField createPasswordField() {
		JPasswordField field = new JPasswordField();
		field.setBorder(new RoundedBorder(0));
		field.setFont(Fonts.fontLight(15));
		field.setPreferredSize(new Dimension(275, 38));
		field.setForeground(Color.GRAY);
		field.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent arg0) {
				field.setForeground(Colors.Black);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				if(field.getPassword().length == 0) field.setForeground(Color.GRAY);
			}
		});
		return field;
	}

    public JPasswordField createPasswordField(int width, int height) {
		JPasswordField field = createPasswordField();
		field.setPreferredSize(new Dimension(width, height));
		return field;
	}

    public JPasswordField createPasswordField(int width, int height, int radius) {
		JPasswordField field = createPasswordField(width, height);
		field.setBorder(new RoundedBorder(radius));
		return field;
	}

    public JPasswordField createPasswordField(int width, int height, Font font) {
		JPasswordField field = createPasswordField(width, height);
		field.setFont(font);
		return field;
	}

    public JPasswordField createPasswordField(int width, int height, int radius, Font font) {
		JPasswordField field = createPasswordField(width, height, font);
		field.setBorder(new RoundedBorder(radius));
		return field;
	}

	public JLabel createLabel(String content, Font font) {
		return createLabel(content, font, new EmptyBorder(0, 0, 5, 0));
	}

	public JLabel createLabel(String content, Font font, int t, int l, int b, int r) {
		return createLabel(content, font, new EmptyBorder(t, l, b, r));
	}

	public JLabel createLabel(String content, int t, int l, int b, int r) {
		return createLabel(content, Fonts.fontLight(14), new EmptyBorder(t, l, b, r));
	}

	public JLabel createLabel(String content, Font font, Border border) {
		JLabel label = new JLabel(content);
		label.setFont(font);
		label.setBorder(border);
		return label;
	}

    public JLabel createLabel(String content) {
		return createLabel(content, 0, 0, 5, 0);
	}

	public JLabel createLabel(int t, int l, int b, int r) {
		JLabel label = new JLabel();
		label.setFont(Fonts.fontLight(14));
		label.setBorder(new EmptyBorder(t, l, b, r));
		return label;
	}

	public JLabel createLabel() {
		return createLabel(0, 0, 5, 0);
	}

	public Button createButton(String label) {
		return createButton(label, ButtonType.PRIMARY);
	}

	public Button createButton(String label, ButtonType type) {
		return createButton(label, type, 10);
	}

	public Button createButton(String label, ButtonType type, int height) {
		Button btn = new Button(label, type, 0);
		btn.setArcHeight(height);
		return btn;
	}

	public JTextArea createTextArea(int rows, int cols, String value) {
		JTextArea ta = new JTextArea(rows, cols);
        ta.setBorder(new EmptyBorder(5, 8, 4, 0));
		ta.setFont(Fonts.fontLight(15));
		ta.setText(value);
		ta.setWrapStyleWord(true);
		ta.setLineWrap(true);
		return ta;
	}

	public JTextArea createTextAreaPLaceholder(int rows, int cols, String placeholder) {
		JTextArea ta = new PlaceholderTextArea(rows, cols, placeholder);
        ta.setBorder(new EmptyBorder(5, 8, 4, 0));
		ta.setFont(Fonts.fontLight(15));
		return ta;
	}

	public JTextArea createTextAreaPLaceholder(int rows, int cols, String value, String placeholder) {
		JTextArea ta = createTextAreaPLaceholder(rows, cols, placeholder);
		if(value != null && !value.equals("")) {
			ta.setForeground(Colors.Black);
			ta.setText(value);
		}
		return ta;
	}

}
