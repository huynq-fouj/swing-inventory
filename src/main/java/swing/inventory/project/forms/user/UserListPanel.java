package swing.inventory.project.forms.user;

import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;

import swing.inventory.project.components.button.Button;
import swing.inventory.project.components.button.ButtonType;
import swing.inventory.project.components.pagination.Pagination;
import swing.inventory.project.components.table.user.UserTable;
import swing.inventory.project.forms.Form;
import swing.inventory.project.objects.UserObject;
import swing.inventory.project.themes.Colors;
import swing.inventory.project.themes.Fonts;
import swing.inventory.project.utils.Chooser;
import swing.inventory.project.utils.ExportFileExcel;

public class UserListPanel extends Form {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JTextField search;
    private JComboBox<String> totalPerPage;
    private UserTable table;
    private Pagination pagination;
    
    public UserListPanel() {
        initUI();
        UserListPanel.setCurrentPage(this);
    }

    private void initUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = createGridBagConstraints();
        add(createLabel("DANH SÁCH NGƯỜI DÙNG",
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
        search = createTextFieldPlaceholder("Nhập tên đăng nhập, họ tên...");
        search.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				handleSearch();
		    }
		});
        search.setToolTipText("Tìm kiếm theo tên đăng nhập, họ tên hoặc email.");
        Button btn = createButton("Tìm kiếm", ButtonType.PRIMARY, 13);
        btn.addActionListener(e -> handleSearch());
        Button btnRefrresh = createButton("Refresh", ButtonType.SECONDARY, 13);
        btnRefrresh.addActionListener(e -> resetState());
        Button btnExport = createButton("Xuất file", ButtonType.SUCCESS, 13);
        btnExport.addActionListener(e -> ExportFileExcel.exportUser(Chooser.chooserExport("user"), this));
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

    private JPanel createTable() {
        table = new UserTable();
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
        UserObject similar = table.getSimilar();
        similar.setUser_name(search.getText());
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

    private static UserListPanel currentClass;

    private static void setCurrentPage(UserListPanel o) {
        UserListPanel.currentClass = o;
    }

    public static UserListPanel getCurrentPage() {
        return UserListPanel.currentClass;
    }

    public UserTable getTable() {
        return table;
    }

    public Pagination getPagination() {
        return pagination;
    }

}
