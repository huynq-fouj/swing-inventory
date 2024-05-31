package swing.inventory.project.events;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.text.JTextComponent;

import swing.inventory.project.themes.Colors;

public class PlaceholderListener implements FocusListener {

    private String placeholder;
    private JTextComponent com;

    public PlaceholderListener(JTextComponent com, String placeholder) {
        this.placeholder = placeholder;
        this.com = com;
    }

    @Override
    public void focusGained(FocusEvent arg0) {
        if(com.getText().equals("")) {
            com.setText("");
            com.setForeground(Colors.Black);
        }
    }

    @Override
    public void focusLost(FocusEvent arg0) {
        if(com.getText().equals("")) {
            com.setText(placeholder);
            com.setForeground(Color.GRAY);
        }
    }
    
}
