package swing.inventory.project.forms.product;

import javax.swing.JPanel;

import swing.inventory.project.forms.Form;

public class ProductForm extends Form {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel mainPanel;

    public ProductForm() {
        this("list");
    }

    public ProductForm(String type) {
        switch (type) {
            case "add":
                mainPanel = new AddProductPanel();
                break;
            case "list":
                mainPanel = new ProductListPanel();
                break;
            default:
                mainPanel = new ProductListPanel();
                break;
        }
        initUI();
    }

    private void initUI() {
        add(mainPanel);
    }

}
