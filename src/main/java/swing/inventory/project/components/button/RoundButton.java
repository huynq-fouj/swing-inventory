package swing.inventory.project.components.button;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

public class RoundButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int arcWidth;
	private int arcHeight;
	
	public RoundButton(String text) {
		this(text, 10, 10);
	}
	
	public RoundButton(String text, int arcWidth, int arcHeight) {
		super(text);
		this.arcWidth = arcWidth;
		this.arcHeight = arcHeight;
		setContentAreaFilled(false);
		setFocusPainted(false);
	}

	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(getBackground());
		g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arcWidth, arcHeight));
		super.paintComponent(g2);
		g2.dispose();
	}

	protected void paintBorder(Graphics g) {
		// Không vẽ border mặc định của JButton
	}

	public Dimension getPreferredSize() {
		Dimension size = super.getPreferredSize();
		size.width += arcWidth;
		size.height += arcHeight;
		return size;
	}

	public int getArcWidth() {
		return arcWidth;
	}

	public int getArcHeight() {
		return arcHeight;
	}

	public void setArcWidth(int arcWidth) {
		this.arcWidth = arcWidth;
	}

	public void setArcHeight(int arcHeight) {
		this.arcHeight = arcHeight;
	}
	
	public void setRounded(int radius) {
		this.arcWidth = radius;
		this.arcHeight = radius;
	}
	
}
