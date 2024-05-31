package swing.inventory.project.components.button;

import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;

import swing.inventory.project.events.ActiveEvent;
import swing.inventory.project.events.HoverEvent;
import swing.inventory.project.themes.Colors;
import swing.inventory.project.utils.ImageUtil;
import swing.inventory.project.utils.Resource;

public class ButtonAction extends RoundButton {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    public ButtonAction(String type) {
        super("");
        setBorder(new EmptyBorder(0, 0, 0, 0));
        int size = 14;
        ImageIcon icon;
        switch (type) {
            case "edit":
                icon = new ImageIcon(Resource.loadStaticImagePath("light_icons\\pencil.png"));
                setIcon(ImageUtil.resize(icon, size, size));
                setBackground(Colors.Success);
                addMouseListener(HoverEvent.changeBackground(this, Colors.Success, Colors.DarkSuccess));
                break;
            case "delete":
                icon = new ImageIcon(Resource.loadStaticImagePath("light_icons\\trash.png"));
                setIcon(ImageUtil.resize(icon, size, size));
                setBackground(Colors.Danger);
                addMouseListener(HoverEvent.changeBackground(this, Colors.Danger, Colors.DarkDanger));
                break;
            case "view":
            default:
                icon = new ImageIcon(Resource.loadStaticImagePath("light_icons\\eye.png"));
                setIcon(ImageUtil.resize(icon, size, size));
                setBackground(Colors.Primary);
                addMouseListener(HoverEvent.changeBackground(this, Colors.Primary, Colors.DarkPrimary));
                break;
        }
    }

}
