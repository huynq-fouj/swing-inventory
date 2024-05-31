package swing.inventory.project.components.text;

import java.awt.Color;
import javax.swing.JTextField;

import swing.inventory.project.events.PlaceholderListener;

public class PlaceholderTextField extends JTextField {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String placeholder;

    public PlaceholderTextField(String placeholder) {
        this.placeholder = placeholder;
        this.setText(placeholder);
        this.setForeground(Color.GRAY);
		this.addFocusListener(new PlaceholderListener(this, placeholder));
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
