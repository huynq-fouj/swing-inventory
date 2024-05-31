package swing.inventory.project.components.button;

import java.awt.Color;
import java.awt.Font;

import swing.inventory.project.events.HoverEvent;
import swing.inventory.project.themes.Colors;

public class Button extends RoundButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Button(String text) {
		this(text, ButtonType.PRIMARY);
	}

	public Button(String text, ButtonType type) {
		super(text);
		this.setForeground(Colors.White);
		this.setFont(new Font("Tahoma", Font.BOLD, 14));
		switch (type) {
			case PRIMARY:
				this.setBackground(Colors.Primary);
				this.addMouseListener(HoverEvent.changeBackground(this, Colors.Primary, Colors.DarkPrimary));
				break;
			case DANGER:
				this.setBackground(Colors.Danger);
				this.addMouseListener(HoverEvent.changeBackground(this, Colors.Danger, Colors.DarkDanger));
				break;
			case SUCCESS:
				this.setBackground(Colors.Success);
				this.addMouseListener(HoverEvent.changeBackground(this, Colors.Success, Colors.DarkSuccess));
				break;
			case SECONDARY:
				this.setBackground(Colors.Secondary);
				this.addMouseListener(HoverEvent.changeBackground(this, Colors.Secondary, Colors.DarkSecondary));
				break;
			case DARK:
				this.setBackground(Colors.Dark);
				this.addMouseListener(HoverEvent.changeBackground(this, Colors.Dark, Colors.DarkHover));
				break;
			case LIGHT:
				this.setForeground(Colors.Black);
				this.setBackground(Colors.White);
				this.addMouseListener(HoverEvent.changeBackground(this, Colors.White, new Color(230, 230, 230)));
				break;
			case WARNING:
				this.setForeground(Colors.Black);
				this.setBackground(Colors.Warning);
				this.addMouseListener(HoverEvent.changeBackground(this, Colors.Warning, Colors.LightWarning));
				break;
			default:
				this.setBackground(Colors.Primary);
				this.addMouseListener(HoverEvent.changeBackground(this, Colors.Primary, Colors.DarkPrimary));
		}
	}

	public Button(String text, ButtonType type, int radius) {
		this(text, type);
		setRounded(radius);
	}
	
}