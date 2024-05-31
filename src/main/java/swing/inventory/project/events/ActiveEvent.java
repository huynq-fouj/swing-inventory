package swing.inventory.project.events;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ActiveEvent {
    
    private ActiveEvent() {}

	public static MouseAdapter changeBackground(final Component x, final Color from, final Color to) {
		return new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				x.setBackground(to);
            }

			@Override
            public void mouseReleased(MouseEvent e) {
            	x.setBackground(from);
            }
		};
	}
	
	public static MouseAdapter changeForeground(final Component x, final Color from, final Color to) {
		return new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				x.setForeground(to);
            }

			@Override
            public void mouseReleased(MouseEvent e) {
            	x.setForeground(from);
            }
		};
	}

}
