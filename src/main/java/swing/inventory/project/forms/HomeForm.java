package swing.inventory.project.forms;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.Year;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.poi.ss.usermodel.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;

import swing.inventory.project.ConnectionPool;
import swing.inventory.project.contexts.AuthContext;
import swing.inventory.project.contexts.ConnectionContext;
import swing.inventory.project.controllers.CategoryController;
import swing.inventory.project.controllers.ProductController;
import swing.inventory.project.controllers.UserController;
import swing.inventory.project.events.HoverEvent;
import swing.inventory.project.objects.UserObject;
import swing.inventory.project.themes.Colors;
import swing.inventory.project.themes.Fonts;
import swing.inventory.project.utils.Resource;
import swing.inventory.project.views.Main;

public class HomeForm extends Form {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    public HomeForm() {
        initUI();
    }

    private void initUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = createGridBagConstraints();
        gbc.gridheight = 3;
        add(banner(), gbc);
        gbc.gridheight = 1;
        gbc.gridy = 3;
        add(addProduct(), gbc);
        gbc.gridy = 4;
        add(addUser(), gbc);
        gbc.gridy = 0;
        gbc.gridx = 1;
        add(productCard(), gbc);
        gbc.gridx = 2;
        add(categoryCard(), gbc);
        gbc.gridx = 3;
        add(userCard(), gbc);
        gbc.gridy = 1;
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        gbc.gridheight = 4;
        gbc.gridy = 2;
        add(chart(), gbc);
    }

    private JPanel banner() {
        JPanel panel = createPanel(10, 10, 10, 10);
        panel.setBackground(Colors.BannerColor);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = createGridBagConstraints();
        String name = AuthContext.getUser().getUser_fullname();
        JLabel title = createLabel("Hi, " + name, 
            Fonts.fontBold(24)
        );
        title.setForeground(Colors.White);
        panel.add(title, gbc);
        gbc.gridy = 1;
        JLabel subTitle = createLabel("Have a nice day at work", Fonts.fontLight(16));
        subTitle.setForeground(Colors.White);
        panel.add(subTitle, gbc);
        JLabel image = createLabel();
        image.setIcon(new ImageIcon(Resource.loadStaticImagePath("working_time.png")));
        gbc.gridy = 2;
        panel.add(image, gbc);
        JPanel outer = createPanel(0, 0, 0, 10);
        outer.add(panel);
        return outer;
    }

    private JPanel userCard() {
        ConnectionPool cp = ConnectionContext.getCP();
        UserController uc = new UserController(cp);
        if(cp == null) ConnectionContext.setCP(uc.getCP());
        UserObject similar = new UserObject();
        similar.setUser_role(AuthContext.getUser().getUser_role());
        int countUser = uc.countUser(similar);
        uc.releaseConnection();
        //Component
        JPanel panel = createPanel(15, 10, 15, 10);
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.setBackground(Colors.Success);
        panel.addMouseListener(HoverEvent.changeBackground(panel, Colors.Success, Colors.LightSuccess));
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Main.getCurrentFrame().switchForm("user");
            }
        });
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = createGridBagConstraints();
        gbc.gridwidth = 2;
        JLabel title = createLabel("Số lượng người dùng", Fonts.fontLight(14));
        title.setForeground(Colors.White);
        panel.add(title, gbc);
        gbc.gridy = 1;
        JLabel value = createLabel(countUser + "", Fonts.fontBold(28));
        value.setForeground(Colors.White);
        panel.add(value, gbc);
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        gbc.gridx = 2;
        JLabel iconLabel = createLabel();
        iconLabel.setBorder(new EmptyBorder(0, 100, 0, 0));
        iconLabel.setIcon(new ImageIcon(Resource.loadStaticImagePath("light_icons\\user.png")));
        panel.add(iconLabel, gbc);
        JPanel outer = createPanel(0, 10, 0, 0);
        outer.add(panel);
        return outer;
    }

    private JPanel productCard() {
        ConnectionPool cp = ConnectionContext.getCP();
        ProductController pc = new ProductController(cp);
        if(cp == null) ConnectionContext.setCP(pc.getCP());
        int countProduct = pc.countProduct(null);
        pc.releaseConnection();
        //Component
        JPanel panel = createPanel(15, 10, 15, 10);
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.setBackground(Colors.Primary);
        panel.addMouseListener(HoverEvent.changeBackground(panel, Colors.Primary, Colors.LightPrimary));
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               Main.getCurrentFrame().switchForm("product");
            }
        });
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = createGridBagConstraints();
        gbc.gridwidth = 2;
        JLabel title = createLabel("Số lượng sản phẩm", Fonts.fontLight(14));
        title.setForeground(Colors.White);
        panel.add(title, gbc);
        gbc.gridy = 1;
        JLabel value = createLabel(countProduct + "", Fonts.fontBold(28));
        value.setForeground(Colors.White);
        panel.add(value, gbc);
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        gbc.gridx = 2;
        JLabel iconLabel = createLabel();
        iconLabel.setBorder(new EmptyBorder(0, 100, 0, 0));
        iconLabel.setIcon(new ImageIcon(Resource.loadStaticImagePath("light_icons\\product.png")));
        panel.add(iconLabel, gbc);
        JPanel outer = createPanel(0, 0, 0, 10);
        outer.add(panel);
        return outer;
    }

    private JPanel categoryCard() {
        ConnectionPool cp = ConnectionContext.getCP();
        CategoryController cc = new CategoryController(cp);
        if(cp == null) ConnectionContext.setCP(cc.getCP());
        int countCategory = cc.countCategory(null);
        cc.releaseConnection();
        //Component
        JPanel panel = createPanel(15, 10, 15, 10);
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.setBackground(Colors.Warning);
        panel.addMouseListener(HoverEvent.changeBackground(panel, Colors.Warning, Colors.LightWarning));
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Main.getCurrentFrame().switchForm("category");
            }
        });
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = createGridBagConstraints();
        gbc.gridwidth = 2;
        JLabel title = createLabel("Số lượng danh mục", Fonts.fontLight(14));
        title.setForeground(Colors.White);
        panel.add(title, gbc);
        gbc.gridy = 1;
        JLabel value = createLabel(countCategory + "", Fonts.fontBold(28));
        value.setForeground(Colors.White);
        panel.add(value, gbc);
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        gbc.gridx = 2;
        JLabel iconLabel = createLabel();
        iconLabel.setBorder(new EmptyBorder(0, 100, 0, 0));
        iconLabel.setIcon(new ImageIcon(Resource.loadStaticImagePath("light_icons\\category.png")));
        panel.add(iconLabel, gbc);
        JPanel outer = createPanel(0, 10, 0, 10);
        outer.add(panel);
        return outer;
    }

    private JPanel chart() {
        JPanel panel = createPanel();
        JFreeChart barChart = createChart();
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(1000, 500));
        panel.add(chartPanel);
        JPanel outer = createPanel(0, 0, 0, 0);
        outer.add(panel);
        return outer;
    }

    private JFreeChart createChart() {
        int y = Year.now().getValue();
        DefaultCategoryDataset dataset = createDataset();
        JFreeChart bChart = ChartFactory.createBarChart(
            "Thống kê sản phẩm mới từng tháng trong năm " + y,
            "Tháng",
            "Số lượng sản phẩm",
            dataset,
            PlotOrientation.VERTICAL,
            true, true, false);
        
        CategoryPlot plot = bChart.getCategoryPlot();
        plot.setBackgroundPaint(Colors.White);
        plot.setOutlineVisible(false);
        plot.setRangeGridlinePaint(Colors.DarkSecondary);

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, Colors.BannerColor);
        renderer.setBarPainter(new StandardBarPainter());
        return bChart;
    }

    private DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        ConnectionPool cp = ConnectionContext.getCP();
        ProductController pc = new ProductController(cp);
        if(cp == null) ConnectionContext.setCP(pc.getCP());
        for(int i = 1; i <= 12; i++) {
            dataset.addValue(pc.countProduct(i), "Sản phẩm mới", "Tháng " + i);
        }
        pc.releaseConnection();
        return dataset;
    }

    private JPanel addProduct() {
        JPanel panel = createPanel(10, 10, 10, 10);
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.setBackground(Colors.Primary);
        panel.addMouseListener(HoverEvent.changeBackground(panel, Colors.Primary, Colors.LightPrimary));
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               Main.getCurrentFrame().switchForm("add-product");
            }
        });
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = createGridBagConstraints();
        JLabel title = createLabel("Thêm sản phẩm", Fonts.fontLight(16));
        title.setPreferredSize(new Dimension(150, 35));
        title.setForeground(Colors.White);
        JLabel iconLabel = createLabel();
        iconLabel.setIcon(new ImageIcon(Resource.loadStaticImagePath("light_icons\\plus.png")));
        iconLabel.setBorder(new EmptyBorder(0, 60, 0, 0));
        panel.add(title, gbc);
        gbc.gridx = 1;
        panel.add(iconLabel, gbc);
        JPanel outer = createPanel(10, 0, 0, 0);
        outer.setLayout(new FlowLayout(FlowLayout.LEFT));
        outer.add(panel);
        return outer;
    }

    private JPanel addUser() {
        JPanel panel = createPanel(10, 10, 10, 10);
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.setBackground(Colors.Success);
        panel.addMouseListener(HoverEvent.changeBackground(panel, Colors.Success, Colors.LightSuccess));
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               Main.getCurrentFrame().switchForm("add-user");
            }
        });
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = createGridBagConstraints();
        JLabel title = createLabel("Thêm người dùng", Fonts.fontLight(16));
        title.setForeground(Colors.White);
        title.setPreferredSize(new Dimension(150, 35));
        JLabel iconLabel = createLabel();
        iconLabel.setIcon(new ImageIcon(Resource.loadStaticImagePath("light_icons\\plus.png")));
        iconLabel.setBorder(new EmptyBorder(0, 60, 0, 0));
        panel.add(title, gbc);
        gbc.gridx = 1;
        panel.add(iconLabel, gbc);
        JPanel outer = createPanel(10, 0, 0, 0);
        outer.setLayout(new FlowLayout(FlowLayout.LEFT));
        outer.add(panel);
        return outer;
    }

}
