package swing.inventory.project.components.table.category;

import java.util.List;

import swing.inventory.project.ConnectionPool;
import swing.inventory.project.components.table.ActionCellEditor;
import swing.inventory.project.components.table.ActionCellRenderer;
import swing.inventory.project.components.table.MyTable;
import swing.inventory.project.components.table.Table;
import swing.inventory.project.contexts.ConnectionContext;
import swing.inventory.project.controllers.CategoryController;
import swing.inventory.project.enums.CategorySortType;
import swing.inventory.project.events.SortCategoryEvent;
import swing.inventory.project.objects.CategoryObject;

public class CategoryTable extends MyTable implements Table {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private CategorySortType sortType = CategorySortType.ID_DESC;
    private CategoryObject similar = new CategoryObject();
    private int currentPage = 1;
    private int totalPerPage = 10;
    private int countPage = 1;


    public CategoryTable() {
        loadModel();
        SortCategoryEvent event = new SortCategoryEvent(this);
        getTableHeader().addMouseListener(event);
    }

    @Override
    public void loadModel() {
        ConnectionPool cp = ConnectionContext.getCP();
        CategoryController cc = new CategoryController(cp);
        if(cp == null) ConnectionContext.setCP(cc.getCP());
        List<CategoryObject> items = cc.getCategories(similar, (short) currentPage, totalPerPage, sortType);
        int total = cc.countCategory(similar);
        cc.releaseConnection();
        //Count page
        countPage = total / totalPerPage;
        if(total % totalPerPage != 0 || countPage == 0) {
            countPage++;
        }
        //Set model
        setModel(new CategoryTableModel(items));
        getColumnModel().getColumn(3).setCellRenderer(new ActionCellRenderer());
        getColumnModel().getColumn(3).setCellEditor(new ActionCellEditor(new CategoryActionEvent(this)));
        getColumnModel().getColumn(0).setMinWidth(60);
        getColumnModel().getColumn(0).setMaxWidth(60);
        getColumnModel().getColumn(3).setMaxWidth(94);
        getColumnModel().getColumn(3).setMinWidth(94);      
    }

    public void setSortType(CategorySortType type) {
        sortType = type;
    }

    public CategorySortType getSortType() {
        return sortType;
    }

    public void setSimilar(CategoryObject s) {
        similar = s;
    }

    public CategoryObject getSimilar() {
        return similar;
    }

    public void setTotalPerPage(int total) {
        totalPerPage = total;
    }

    @Override
    public void setCurrentPage(int page) {
        if(page > countPage) page = countPage;
        if(page < 1) page = 1;
        currentPage = page;
    }

    @Override
    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    public int getCountPage() {
        return countPage;
    }

}
