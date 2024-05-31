package swing.inventory.project.forms.product;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
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

import swing.inventory.project.ConnectionPool;
import swing.inventory.project.components.Dialog;
import swing.inventory.project.components.RoundedBorder;
import swing.inventory.project.components.button.Button;
import swing.inventory.project.components.button.ButtonType;
import swing.inventory.project.contexts.AuthContext;
import swing.inventory.project.contexts.ConnectionContext;
import swing.inventory.project.controllers.CategoryController;
import swing.inventory.project.controllers.ProductController;
import swing.inventory.project.enums.CategorySortType;
import swing.inventory.project.forms.Form;
import swing.inventory.project.models.category.Category;
import swing.inventory.project.objects.CategoryObject;
import swing.inventory.project.objects.ProductObject;
import swing.inventory.project.themes.Colors;
import swing.inventory.project.themes.Fonts;
import swing.inventory.project.utils.ImageUtil;
import swing.inventory.project.utils.Resource;

public class AddProductPanel extends Form {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JLabel imagePreview;
    private BufferedImage image;
    private JTextField productName;
    private JTextField productQuantity;
    private JTextField productPrice;
    private JTextField productSize;
    private JTextField productUnit;
    private JTextArea productDetails;
    private JComboBox<CategoryObject> category;
    
    public AddProductPanel() {
        initUI();
    }

    private void initUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = createGridBagConstraints();
        add(createLabel("THÊM MỚI SẢN PHẨM",
            Fonts.fontBold(24),
            new EmptyBorder(0, 0, 20, 0)),
        gbc);
        gbc.gridy = 1;
        add(formControl(), gbc);
        gbc.gridy = 2;
        add(bottomBtn(), gbc);
    }

    private JPanel formControl() {
        JPanel panel = createPanel(20, 0, 0, 0);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = createGridBagConstraints();
        gbc.gridheight = 6;
        panel.add(productImage(), gbc);
        gbc.gridx = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        panel.add(createLabel("Thông tin sản phẩm",
            Fonts .fontBold(18),
            new EmptyBorder(0, 30, 20, 0)
        ), gbc);
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        panel.add(productNameField(), gbc);
        gbc.gridx = 2;
        panel.add(productPriceField(), gbc);
        gbc.gridx = 3;
        panel.add(productQuantityField(), gbc);
        gbc.gridy = 2;
        gbc.gridx = 1;
        panel.add(productCategoryField(), gbc);
        gbc.gridx = 2;
        panel.add(productSizeField(), gbc);
        gbc.gridx = 3;
        panel.add(productUnitField(), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(createLabel("Mô tả sản phẩm", 
            Fonts.fontBold(18),
            new EmptyBorder(0, 30, 20, 0)
        ), gbc);
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.gridheight = 2;
        panel.add(productDetailsField(), gbc);
        return panel;
    }

    private JPanel productImage() {
        JPanel panel = createPanel();
        panel.setBorder(new EmptyBorder(0, 0, 0, 20));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = createGridBagConstraints();
        panel.add(createLabel("Hình ảnh",
            Fonts.fontBold(18),
            new EmptyBorder(0, 0, 15, 0)
        ), gbc);
        String path = Resource.loadStaticImagePath("default.png");
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

    private JPanel productNameField() {
        JPanel panel = createPanel(0, 30, 20, 0);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = createGridBagConstraints();
		panel.add(createLabel("Tên sản phẩm"), gbc);
		productName = createTextFieldPlaceholder("Nhập tên sản phẩm");
		gbc.gridy = 1;
		panel.add(productName, gbc);
		return panel;
    }

    private JPanel productPriceField() {
        JPanel panel = createPanel(0, 30, 20, 0);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = createGridBagConstraints();
		panel.add(createLabel("Đơn giá"), gbc);
		productPrice = createTextFieldPlaceholder("Nhập đơn giá của sản phẩm (VND)");
		gbc.gridy = 1;
		panel.add(productPrice, gbc);
		return panel;
    }

    private JPanel productQuantityField() {
        JPanel panel = createPanel(0, 30, 20, 0);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = createGridBagConstraints();
		panel.add(createLabel("Số lượng"), gbc);
		productQuantity = createTextFieldPlaceholder("Nhập số lượng sản phẩm");
		gbc.gridy = 1;
		panel.add(productQuantity, gbc);
		return panel;
    }

    private JPanel productCategoryField() {
        JPanel panel = createPanel(0, 30, 20, 0);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = createGridBagConstraints();
        panel.add(createLabel("Danh mục sản phẩm"), gbc);

        ConnectionPool cp = ConnectionContext.getCP();
        CategoryController cc = new CategoryController(cp);
        if(cp == null) ConnectionContext.setCP(cc.getCP());
        int total = cc.countCategory(null);
        List<CategoryObject> categories = cc.getCategories(null, (short) 1, total, CategorySortType.NAME_ASC);
        cc.releaseConnection();
        CategoryObject[] items = categories.toArray(new CategoryObject[total]);

        category = new JComboBox<>(items);
        category.setSelectedIndex(0);
		category.setFont(Fonts.fontLight(15));
		category.setPreferredSize(new Dimension(275, 38));
		category.setFocusable(false);
        gbc.gridy = 1;
		panel.add(category, gbc);
        return panel;
    }

    private JPanel productSizeField() {
        JPanel panel = createPanel(0, 30, 20, 0);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = createGridBagConstraints();
		panel.add(createLabel("Kích thước sản phẩm"), gbc);
		productSize = createTextFieldPlaceholder("Gram, kg, m, m², ...");
		gbc.gridy = 1;
		panel.add(productSize, gbc);
		return panel;
    }

    private JPanel productUnitField() {
        JPanel panel = createPanel(0, 30, 20, 0);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = createGridBagConstraints();
		panel.add(createLabel("Đơn vị tính"), gbc);
		productUnit = createTextFieldPlaceholder("Chiếc, thùng, hộp, ...");
		gbc.gridy = 1;
		panel.add(productUnit, gbc);
		return panel;
    }

    private JPanel productDetailsField() {
        JPanel panel = this.createPanel(0, 30, 0, 0);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = createGridBagConstraints();
		productDetails = createTextAreaPLaceholder(6, 10, "Nhập nội dung");
		JScrollPane scroll = new JScrollPane(productDetails);
		scroll.setBorder(new RoundedBorder(0, 1));
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scroll, gbc);
		return panel;
    }

    private JPanel bottomBtn() {
        JPanel panel = createPanel(20, 0, 0, 0);
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        Button add = createButton("Thêm mới", ButtonType.PRIMARY);
        add.addActionListener(e -> handleCreate());
        Button reset = createButton("Đặt lại", ButtonType.SECONDARY);
        reset.addActionListener(e -> resetState());
        panel.add(add);
        panel.add(reset);
        return panel;
    }

    private boolean checkValid() {
        String name = productName.getText();
        String price = productPrice.getText();
        String quantity = productQuantity.getText();
        if(name == null || name.trim().equals("")) {
            Dialog.error(this, "Trường tên sản phẩm không được bỏ trống!");
            return false;
        }
        if(price == null || price.trim().equals("")) {
            Dialog.error(this, "Trường đơn giá sản phẩm không được bỏ trống!");
            return false;
        } else {
            try {
                double p = Double.parseDouble(price);
                if(p < 0) {
                    Dialog.error(this, "Trường đơn giá sản phẩm phải là số thực ≥ 0!");
                    return false;
                }
            } catch (Exception e) {
                Dialog.error(this, "Trường đơn giá sản phẩm phải là số thực ≥ 0!");
                return false;
            }
        }
        if(quantity == null || quantity.trim().equals("")) {
            Dialog.error(this, "Trường số lượng sản phẩm không được bỏ trống!");
            return false;
        } else {
            try {
                int p = Integer.parseInt(quantity);
                if(p < 0) {
                    Dialog.error(this, "Trường số lượng sản phẩm phải là số nguyên ≥ 0!");
                    return false;
                }
            } catch (Exception e) {
                Dialog.error(this, "Trường số lượng sản phẩm phải là số nguyên ≥ 0!");
                return false;
            }
        }
        return true;
    }

    private void handleCreate() {
        if(checkValid()) {
            ProductObject item = new ProductObject();
            item.setAuthor_id(AuthContext.getUser().getUser_id());
            item.setProduct_name(productName.getText().trim());
            item.setProduct_price(Double.parseDouble(productPrice.getText()));
            item.setProduct_quantity(Integer.parseInt(productQuantity.getText()));
            item.setProduct_category_id(((CategoryObject)(category.getSelectedItem())).getCategory_id());
            item.setProduct_size(productSize.getText().trim());
            item.setProduct_unit(productUnit.getText().trim());
            item.setProduct_details(productDetails.getText().trim());
            item.setProduct_image(ImageUtil.saveImage(image));
            ConnectionPool cp = ConnectionContext.getCP();
            ProductController pc = new ProductController(cp);
            if(cp == null) ConnectionContext.setCP(pc.getCP());
            boolean result = pc.addProduct(item);
            pc.releaseConnection();
            if(result) {
                Dialog.success(this, "Đã thêm mới sản phẩm: " + item.getProduct_name() + ".");
                resetState();
            } else Dialog.error(this, "Thêm sản phẩm không thành công!");
            item = null;
        }
    }

    private void resetState() {
        removeAll();
        initUI();
        repaint();
        revalidate();
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

}
