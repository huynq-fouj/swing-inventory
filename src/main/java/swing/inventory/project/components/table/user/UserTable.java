package swing.inventory.project.components.table.user;

import java.awt.Dimension;
import java.util.List;

import swing.inventory.project.ConnectionPool;
import swing.inventory.project.components.table.ActionCellEditor;
import swing.inventory.project.components.table.ActionCellRenderer;
import swing.inventory.project.components.table.ImageCellRenderer;
import swing.inventory.project.components.table.MyTable;
import swing.inventory.project.components.table.Table;
import swing.inventory.project.contexts.AuthContext;
import swing.inventory.project.contexts.ConnectionContext;
import swing.inventory.project.controllers.UserController;
import swing.inventory.project.enums.UserSortType;
import swing.inventory.project.events.SortUserEvent;
import swing.inventory.project.objects.UserObject;

public class UserTable extends MyTable implements Table {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private UserSortType sortType = UserSortType.ID_DESC;
    private UserObject similar = new UserObject();
    private int currentPage = 1;
    private int totalPerPage = 10;
    private int countPage = 1;

    public UserTable() {
        similar.setUser_role(AuthContext.getUser().getUser_role());
        loadModel();
        SortUserEvent event = new SortUserEvent(this);
        getTableHeader().addMouseListener(event);
        setRowHeight(36);
        getTableHeader().setPreferredSize(new Dimension(0, 36));
    }

    @Override
    public void loadModel() {
        ConnectionPool cp = ConnectionContext.getCP();
        UserController uc = new UserController(cp);
        if(cp == null) ConnectionContext.setCP(uc.getCP());
        List<UserObject> items = uc.getUserObjects(similar, currentPage, totalPerPage, sortType);
        int total = uc.countUser(similar);
        uc.releaseConnection();
        //Count page
        countPage = total / totalPerPage;
        if(total % totalPerPage != 0 || countPage == 0) {
            countPage++;
        }
        //Set model
        setModel(new UserTableModel(items));
        getColumnModel().getColumn(8).setCellRenderer(new ActionCellRenderer());
        getColumnModel().getColumn(8).setCellEditor(new ActionCellEditor(new UserActionEvent(this)));
        getColumnModel().getColumn(1).setCellRenderer(new ImageCellRenderer());
        getColumnModel().getColumn(0).setMaxWidth(60);
        getColumnModel().getColumn(0).setMinWidth(60);
        getColumnModel().getColumn(1).setMaxWidth(70);
        getColumnModel().getColumn(1).setMinWidth(70);
        getColumnModel().getColumn(8).setMaxWidth(94);
        getColumnModel().getColumn(8).setMinWidth(94);
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

    public UserSortType getSortType() {
        return sortType;
    }

    public void setSortType(UserSortType sortType) {
        this.sortType = sortType;
    }

    public UserObject getSimilar() {
        return similar;
    }

    public void setSimilar(UserObject similar) {
        this.similar = similar;
    }

    public int getTotalPerPage() {
        return totalPerPage;
    }

    public void setTotalPerPage(int totalPerPage) {
        this.totalPerPage = totalPerPage;
    }

    @Override
    public int getCountPage() {
        return countPage;
    }

    public void setCountPage(int countPage) {
        this.countPage = countPage;
    }
    
}
