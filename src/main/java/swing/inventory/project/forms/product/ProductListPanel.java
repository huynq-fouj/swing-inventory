package swing.inventory.project.forms.product;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Optional;

import swing.inventory.project.ConnectionPool;
import swing.inventory.project.components.button.Button;
import swing.inventory.project.components.button.ButtonType;
import swing.inventory.project.components.pagination.Pagination;
import swing.inventory.project.components.table.product.ProductTable;
import swing.inventory.project.contexts.ConnectionContext;
import swing.inventory.project.controllers.CategoryController;
import swing.inventory.project.enums.CategorySortType;
import swing.inventory.project.forms.Form;
import swing.inventory.project.objects.CategoryObject;
import swing.inventory.project.objects.ProductObject;
import swing.inventory.project.themes.Colors;
import swing.inventory.project.themes.Fonts;
import swing.inventory.project.utils.Chooser;
import swing.inventory.project.utils.ExportFileExcel;

public class ProductListPanel extends Form {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JTextField search;
    private JComboBox<String> totalPerPage;
    private JComboBox<CategoryObject> category;
    private JTextField minPrice;
    private JTextField maxPrice;
    private ProductTable table;
    private Pagination pagination;

    public ProductListPanel() {
        initUI();
        setCurrentPage(this);
    }

    private void initUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = createGridBagConstraints();
        add(createLabel("DANH SÁCH SẢN PHẨM",
            Fonts.fontBold(24),
            new EmptyBorder(0, 0, 20, 0)),
        gbc);
        gbc.gridy = 1;
        add(topTool(), gbc);
        gbc.gridy = 2;
        add(createTable(), gbc);
        gbc.gridy = 3;
        pagination = new Pagination(table);
        add(pagination, gbc);
    }

    private JPanel topTool() {
        JPanel panel = createPanel(0, 0, 20, 0);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = createGridBagConstraints();
        //Search name box
        search = createTextFieldPlaceholder("Nhập tên sản phẩm...");
        search.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				handleSearch();
		    }
		});
        search.setToolTipText("Tìm kiếm theo tên sản phẩm hoặc danh mục.");
        //Search category
        ConnectionPool cp = ConnectionContext.getCP();
        CategoryController cc = new CategoryController(cp);
        if(cp == null) ConnectionContext.setCP(cc.getCP());
        int total = cc.countCategory(null);
        List<CategoryObject> categories = cc.getCategories(null, (short) 1, total, CategorySortType.NAME_ASC);
        cc.releaseConnection();
        CategoryObject first = new CategoryObject();
        first.setCategory_id(0);
        first.setCategory_name("Chọn danh mục");
        categories.addFirst(first);
        CategoryObject[] items = categories.toArray(new CategoryObject[total]);
        category = new JComboBox<>(items);
        category.setSelectedIndex(0);
		category.setFont(Fonts.fontLight(15));
        int width = category.getPreferredSize().width;
		category.setPreferredSize(new Dimension(width, 38));
		category.setFocusable(false);
        category.setBorder(new LineBorder(Colors.Black));
        category.addItemListener(e -> handleSelectCategory());
        category.setToolTipText("Tìm kiếm theo danh mục sản phẩm.");
        //Price
        minPrice = createTextFieldPlaceholder("Giá thấp nhất");
        minPrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				handleSearchPrice();
		    }
		});
        minPrice.setToolTipText("Tìm theo khoảng giá (VND).");
        minPrice.setPreferredSize(new Dimension(120, 38));
        maxPrice = createTextFieldPlaceholder("Giá lớn nhất");
        maxPrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				handleSearchPrice();
		    }
		});
        maxPrice.setToolTipText("Tìm theo khoảng giá (VND).");
        maxPrice.setPreferredSize(new Dimension(120, 38));
        //Button
        Button btn = createButton("Tìm kiếm", ButtonType.PRIMARY, 13);
        btn.addActionListener(e -> handleSearch());
        Button btnRefrresh = createButton("Refresh", ButtonType.SECONDARY, 13);
        btnRefrresh.addActionListener(e -> resetState());
        Button btnExport = createButton("Xuất file", ButtonType.SUCCESS, 13);
        btnExport.addActionListener(e -> ExportFileExcel.exportUser(Chooser.chooserExport("user"), this));
        //Change total
        String[] values = {"10 dòng", "20 dòng", "50 dòng"};
        totalPerPage = createComboBoxTotal(values);
        totalPerPage.addItemListener(e -> handleSelectRow());
        ////////////
        JPanel sub = createPanel(0, 0, 0, 0);
        sub.setLayout(new FlowLayout(FlowLayout.LEFT));
        sub.add(search);
        sub.add(category);
        sub.add(minPrice);
        sub.add(maxPrice);
        sub.add(btn);
        sub.add(btnRefrresh);
        sub.add(btnExport);
        sub.add(totalPerPage);
        panel.add(sub, gbc);
        return panel;
    }

    private JPanel createTable() {
        table = new ProductTable();
        JPanel panel = createPanel(0, 0, 0, 0);
        JScrollPane scroll = new JScrollPane();
        scroll.setBackground(Colors.White);
        scroll.setViewportView(table);
        scroll.setPreferredSize(new Dimension(1200, 398));
        panel.add(scroll);
        return panel;
    }

    private void resetState() {
        removeAll();
        initUI();
        repaint();
        revalidate();
    }

    private void handleSearch() {
        ProductObject similar = table.getSimilar();
        similar.setProduct_name(search.getText());
        table.setSimilar(similar);
        table.setCurrentPage(1);
        table.loadModel();
        pagination.reload();
    }

    private void handleSelectCategory() {
        ProductObject similar = table.getSimilar();
        CategoryObject item = (CategoryObject) category.getSelectedItem();
        similar.setProduct_category_id(item.getCategory_id());
        table.setCurrentPage(1);
        table.loadModel();
        pagination.reload();
    }

    private void handleSearchPrice() {
        String strMin = minPrice.getText().trim();
        String strMax = maxPrice.getText().trim();
        double min = 0;
        double max = 0;
        //Check min
        try {min = Double.parseDouble(strMin);} 
        catch (Exception e) {min = 0;}
        //Check max
        try {max = Double.parseDouble(strMax);}
        catch (Exception e) {max = 0;}
        String price = min + ":" + max;
        ProductObject similar = table.getSimilar();
        similar.setProduct_details(price);
        table.setCurrentPage(1);
        table.loadModel();
        pagination.reload();
    }

    private void handleSelectRow() {
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

    private static ProductListPanel page;

    public static void setCurrentPage(ProductListPanel o) {
        page = o;
    }

    public static ProductListPanel getCurrentPage() {
        return page;
    }

    public ProductTable getTable() {
        return table;
    }

    public void reload() {
        table.loadModel();
        pagination.reload(); 
    }
    
}
