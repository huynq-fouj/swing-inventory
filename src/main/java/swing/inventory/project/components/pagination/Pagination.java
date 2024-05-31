package swing.inventory.project.components.pagination;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import swing.inventory.project.components.table.Table;
import swing.inventory.project.themes.Colors;
import swing.inventory.project.themes.Fonts;

public class Pagination extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Table table;

    public Pagination(Table table) {
        this.table = table;
        initUI();
    }

    private void initUI() {
        int countPage = table.getCountPage();
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(Colors.White);
        add(prevButton());
        add(paginationButton(1));
        if(countPage > 1) {
            int page = table.getCurrentPage();

            if(page > 4) add(threeDot());

            int count = 0;
            if(page >= 3)
                for(int i = page - 2; i < page; i++) {
                    if(i != 1) add(paginationButton(i));
                    if(++count > 1) break;
                }

            if(page > 1 && page < countPage) add(paginationButton(page));

            count = 0;
            for(int i = page + 1; i < countPage; i++) {
                add(paginationButton(i));
                if(++count > 1) break;
            }

            if(page < countPage - 3) add(threeDot());
            add(paginationButton(countPage));
        }
        add(nextButton());
    }

    private JPanel threeDot() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("...");
        label.setFont(Fonts.fontLight(15));
        panel.add(label);
        panel.setBackground(Colors.White);
        return panel;
    }

    private JPanel paginationButton(int page) {
        boolean isCurrentPage = page == table.getCurrentPage();
        JPanel panel = createPanel(page + "", isCurrentPage);
        if(!isCurrentPage) {
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent arg0) {
                    table.setCurrentPage(page);
                    table.loadModel();
                    reload();
                }
            });
        }
        return panel;
    }

    private JPanel prevButton() {
        JPanel panel = createPanel("<");
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                table.setCurrentPage(table.getCurrentPage() - 1);
                table.loadModel();
                reload();
            }
        });
        return panel;
    }

    private JPanel nextButton() {
        JPanel panel = createPanel(">");
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                table.setCurrentPage(table.getCurrentPage() + 1);
                table.loadModel();
                reload();
            } 
        });
        return panel;
    }

    private JPanel createPanel(String content) {
        return createPanel(content, false);
    }

    private JPanel createPanel(String content, boolean isActive) {
        JPanel panel = new JPanel();
        panel.setBackground(Colors.White);
        panel.setBorder(new EmptyBorder(3, 5, 3, 5));
        JLabel label = new JLabel(content);
        label.setFont(Fonts.fontLight(15));
        panel.add(label);
        if(isActive) {
            panel.setBackground(Colors.Primary);
            label.setForeground(Colors.White);
        } else {
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent arg0) {
                    panel.setBackground(Colors.Primary);
                    label.setForeground(Colors.White);
                }
                @Override
                public void mouseExited(MouseEvent arg0) {
                    panel.setBackground(Colors.White);
                    label.setForeground(Colors.Black);
                }   
            });
        }
        return panel;
    }

    public void reload() {
        removeAll();
        initUI();
        repaint();
        revalidate();
    }

}
