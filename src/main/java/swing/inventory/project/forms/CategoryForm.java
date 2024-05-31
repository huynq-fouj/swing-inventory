package swing.inventory.project.forms;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import swing.inventory.project.ConnectionPool;
import swing.inventory.project.components.Dialog;
import swing.inventory.project.components.RoundedBorder;
import swing.inventory.project.components.button.Button;
import swing.inventory.project.components.button.ButtonType;
import swing.inventory.project.components.pagination.Pagination;
import swing.inventory.project.components.table.category.CategoryTable;
import swing.inventory.project.components.text.PlaceholderTextArea;
import swing.inventory.project.components.text.PlaceholderTextField;
import swing.inventory.project.contexts.AuthContext;
import swing.inventory.project.contexts.ConnectionContext;
import swing.inventory.project.controllers.CategoryController;
import swing.inventory.project.enums.CategorySortType;
import swing.inventory.project.objects.CategoryObject;
import swing.inventory.project.themes.Colors;
import swing.inventory.project.themes.Fonts;
import swing.inventory.project.utils.Chooser;
import swing.inventory.project.utils.ExportFileExcel;

public class CategoryForm extends Form {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JTextField categoryName;
    private JTextArea categoryNotes;
    private JTextField search;
    private JComboBox<String> totalPerPage;
    private CategoryTable table;
    private Pagination pagination;
    
    public CategoryForm() {
        initUI();
    }

    private void initUI() {
        setBorder(new EmptyBorder(20, 0, 0, 0));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = createGridBagConstraints();
        gbc.gridwidth = 2;
        add(createLabel("DANH MỤC SẢN PHẨM", 
                Fonts.fontBold(24),
                new EmptyBorder(5, 0, 20, 0)
            ), gbc);
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        add(leftPanel(), gbc);
        gbc.gridheight = 2;
        gbc.gridx = 1;
        add(rightPanel(), gbc);
    }

    private JPanel leftPanel() {
        JPanel panel = createPanel(0, 0, 0, 0);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = createGridBagConstraints();
        panel.add(createLabel("Thêm mới",
                Fonts.fontBold(18),
                new EmptyBorder(0, 0, 10, 0)
            ), gbc);
        gbc.gridy = 1;
        panel.add(cNameField(), gbc);
        gbc.gridy = 2;
        gbc.gridheight = 2;
        panel.add(cNotesField(), gbc);
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        panel.add(leftGroupButton(), gbc);
        return panel;
    }

    private JPanel rightPanel() {
        JPanel panel = createPanel(0, 50, 0, 0);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = createGridBagConstraints();
        panel.add(createLabel("Danh sách",
                Fonts.fontBold(18),
                new EmptyBorder(0, 0, 10, 0)
            ), gbc);
        gbc.gridwidth = 2;
        gbc.gridy = 1;
        panel.add(topToolGroup(), gbc);
        gbc.gridy = 2;
        panel.add(createTable(), gbc);
        gbc.gridy = 3;
        pagination = new Pagination(table);
        panel.add(pagination, gbc);
        return panel;
    }

    private JPanel createTable() {
        table = new CategoryTable();
        JPanel panel = createPanel(0, 0, 0, 0);
        JScrollPane scroll = new JScrollPane();
        scroll.setBackground(Colors.White);
        scroll.setViewportView(table);
        scroll.setPreferredSize(new Dimension(800, 354));
        panel.add(scroll);
        return panel;
    }

    private JPanel cNameField() {
		JPanel panel = this.createPanel(0, 0, 20, 0);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = createGridBagConstraints();
		panel.add(this.createLabel("Tên danh mục"), gbc);
		categoryName = this.createTextFieldPlaceholder("Nhập tên danh mục");
		gbc.gridy = 1;
		panel.add(categoryName, gbc);
		return panel;
	}

    private JPanel cNotesField() {
        JPanel panel = this.createPanel(0, 0, 20, 0);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = createGridBagConstraints();
		panel.add(this.createLabel("Ghi chú"), gbc);
		categoryNotes = createTextAreaPLaceholder(7, 10, "Nhập nội dung");
		JScrollPane scroll = new JScrollPane(categoryNotes);
		scroll.setBorder(new RoundedBorder(0, 1));
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		gbc.gridy = 1;
		panel.add(scroll, gbc);
		return panel;
    }

    private JPanel leftGroupButton() {
        JPanel panel = createPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        Button addBtn = createButton("Thêm mới", ButtonType.PRIMARY);
        addBtn.addActionListener(e -> handleCreate());
        panel.add(addBtn);
        Button cancelBtn = createButton("Đặt lại", ButtonType.SECONDARY);
        cancelBtn.addActionListener(e -> resetForm());
        panel.add(cancelBtn);
        return panel;
    }

    private JPanel topToolGroup() {
        JPanel panel = createPanel(0, 0, 20, 0);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = createGridBagConstraints();
        search = createTextFieldPlaceholder("Nhập từ khóa...");
        search.setToolTipText("Tìm kiếm theo tên danh mục hoặc ghi chú");
        search.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				handleSearch();
		    }
		});
        Button btn = createButton("Tìm kiếm", ButtonType.PRIMARY, 13);
        btn.addActionListener(e -> handleSearch());
        Button btnRefrresh = createButton("Refresh", ButtonType.SECONDARY, 13);
        btnRefrresh.addActionListener(e -> resetState());
        Button btnExport = createButton("Xuất file", ButtonType.SUCCESS, 13);
        btnExport.addActionListener(e -> exportFile());
        String[] values = {"10 dòng", "20 dòng", "50 dòng"};
        totalPerPage = createComboBoxTotal(values);
        totalPerPage.addItemListener(e -> handleSelect());
        JPanel sub = createPanel(0, 0, 0, 0);
        sub.setLayout(new FlowLayout(FlowLayout.LEFT));
        sub.add(search);
        sub.add(btn);
        sub.add(btnRefrresh);
        sub.add(btnExport);
        sub.add(totalPerPage);
        panel.add(sub, gbc);
        return panel;
    }

    private boolean checkValidForm() {
        if(categoryName.getText() == null || categoryName.getText().trim().equals("")) {
            Dialog.error(this, "Trường tên danh mục không được để trống!");
            categoryName.setFocusable(true);
            return false;
        }
        return true;
    }

    private void handleCreate() {
        if(checkValidForm()) {
            CategoryObject co = new CategoryObject();
            co.setAuthor_id(AuthContext.getUser().getUser_id());
            co.setCategory_name(categoryName.getText().trim());
            co.setCategory_notes(categoryNotes.getText().trim());
            ConnectionPool cp = ConnectionContext.getCP();
            CategoryController cc = new CategoryController(cp);
            if(cp == null) ConnectionContext.setCP(cc.getCP());
            boolean check = cc.addCategory(co);
            cc.releaseConnection();
            if(check) {
                Dialog.success(this, "Đã thêm mới danh mục: " + co.getCategory_name());
                table.setCurrentPage(1);
                table.setSortType(CategorySortType.ID_DESC);
                table.loadModel();
                pagination.reload();
                resetState();
            } else Dialog.success(this, "Thêm mới danh mục không thành công!");
        }
    }

    private void handleSearch() {
        CategoryObject similar = table.getSimilar();
        similar.setCategory_name(search.getText());
        table.setSimilar(similar);
        table.setCurrentPage(1);
        table.loadModel();
        pagination.reload();
    }

    private void handleSelect() {
        int index = totalPerPage.getSelectedIndex();
        switch (index) {
            case 1:
                table.setTotalPerPage(20);
                break;
            case 2:
            table.setTotalPerPage(50);
                break;
            case 0:
            default:
                table.setTotalPerPage(10);
                break;
        }
        table.setCurrentPage(1);
        table.loadModel();
        pagination.reload();
    }

    private void exportFile() {
        ExportFileExcel.exportCategory(Chooser.chooserExport("category"), this);
    }

    private void resetForm() {
        ((PlaceholderTextField)categoryName).reset();
        ((PlaceholderTextArea)categoryNotes).reset();
    }

    private void resetState() {
        removeAll();
        initUI();
        repaint();
        revalidate();
    }

}
