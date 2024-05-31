package swing.inventory.project.components;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

public class RoundedBorder implements Border {

	private int radius;
	private int inset;
	
	public RoundedBorder(int radius) {
		this(radius, 10);
	}
	
	public RoundedBorder(int radius, int inset) {
		this.radius = radius;
		this.inset = inset;
	}
	
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		g.drawRoundRect(x, y, width-1, height-1, radius, radius);
	}

	@Override
	public Insets getBorderInsets(Component c) {
		//return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
		return new Insets(this.inset, this.inset, this.inset, this.inset);
	}

	@Override
	public boolean isBorderOpaque() {
		return true;
	}

}
