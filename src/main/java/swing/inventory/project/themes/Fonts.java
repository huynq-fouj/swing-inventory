package swing.inventory.project.themes;

import java.awt.Font;

public class Fonts {
    
    public static final String FONT_FAMILY = "Arial";
    public static final String FONT_FAMILY_2 = "SansSerif";

    private Fonts() {}

    public static Font fontLight(int size) {
        return new Font(FONT_FAMILY, Font.PLAIN, size);
    }

    public static Font fontBold(int size) {
        return new Font(FONT_FAMILY, Font.BOLD, size);
    }

    public static Font fontItalic(int size) {
        return new Font(FONT_FAMILY, Font.ITALIC, size);
    }

}
