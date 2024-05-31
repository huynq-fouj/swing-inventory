package swing.inventory.project.components.table;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import swing.inventory.project.themes.Colors;

public class TableHeader extends JLabel {
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public TableHeader(String text) {
        super(text);
        setOpaque(true);
        setBorder(new EmptyBorder(10, 5, 10, 5));
        setFont(new Font("Segoe UI", Font.BOLD, 13));
		setForeground(Colors.White);
		setBackground(Colors.Primary);
        setPreferredSize(new Dimension(0, 32));
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        grphcs.setColor(new Color(230, 230, 230));
        grphcs.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
    }

}
