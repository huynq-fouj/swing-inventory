package swing.inventory.project.forms.user;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import swing.inventory.project.forms.Form;

public class UserForm extends Form {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private JPanel mainPanel;

    public UserForm() {
        this("list");
    }

    public UserForm(String form) {
        switch (form) {
            case "add":
                mainPanel = new AddUserPanel();
                break;
            case "list":
                mainPanel = new UserListPanel();
                break;
            default:
                mainPanel = new UserListPanel();
                break;
        }

        initUI();
    }

    private void initUI() {
        setBorder(new EmptyBorder(20, 0, 0, 0));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = createGridBagConstraints();
        add(mainPanel, gbc);
    }

}
