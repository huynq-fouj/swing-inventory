package swing.inventory.project.components.text;

import java.awt.Color;
import javax.swing.JTextArea;

import swing.inventory.project.events.PlaceholderListener;

public class PlaceholderTextArea extends JTextArea {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String placeholder;

    public PlaceholderTextArea(int row, int col, String placeholder) {
        super(row, col);
        this.placeholder = placeholder;
        setText(placeholder);
        setForeground(Color.GRAY);
        setWrapStyleWord(true);
		setLineWrap(true);
        addFocusListener(new PlaceholderListener(this, placeholder));
    }
    
    @Override
    public String getText() {
        String str = super.getText();
        return !str.equals(placeholder) ? str : "";
    }

    public void reset() {
        setText(placeholder);
        setForeground(Color.GRAY);
    }

}
