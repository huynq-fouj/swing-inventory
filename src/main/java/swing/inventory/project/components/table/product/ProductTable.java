package swing.inventory.project.components.table.product;

import java.util.*;

import java.awt.Dimension;

import swing.inventory.project.ConnectionPool;
import swing.inventory.project.components.table.ActionCellEditor;
import swing.inventory.project.components.table.ActionCellRenderer;
import swing.inventory.project.components.table.ImageCellRenderer;
import swing.inventory.project.components.table.MyTable;
import swing.inventory.project.components.table.Table;
import swing.inventory.project.contexts.ConnectionContext;
import swing.inventory.project.controllers.ProductController;
import swing.inventory.project.enums.ProductSortType;
import swing.inventory.project.events.SortProductEvent;
import swing.inventory.project.objects.ProductObject;

public class ProductTable extends MyTable implements Table {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private ProductSortType sortType = ProductSortType.ID_DESC;
    private ProductObject similar = new ProductObject();
    private int currentPage = 1;
    private int totalPerPage = 10;
    private int countPage = 1;

    public ProductTable() {
        loadModel();
        SortProductEvent event = new SortProductEvent(this);
        getTableHeader().addMouseListener(event);
        setRowHeight(36);
        getTableHeader().setPreferredSize(new Dimension(0, 36));
    }

    @Override
    public void loadModel() {
        ConnectionPool cp = ConnectionContext.getCP();
        ProductController pc = new ProductController(cp);
        if(cp == null) ConnectionContext.setCP(pc.getCP());
        List<ProductObject> items = pc.getProducts(similar, sortType);
        int total = pc.countProduct(similar);
        pc.releaseConnection();
        //Count page
        countPage = total / totalPerPage;
        if(total % totalPerPage != 0 || countPage == 0) {
            countPage++;
        }
        //Set model
        setModel(new ProductTableModel(items));
        getColumnModel().getColumn(9).setCellRenderer(new ActionCellRenderer());
        getColumnModel().getColumn(9).setCellEditor(new ActionCellEditor(new ProductActionEvent(this)));
        getColumnModel().getColumn(1).setCellRenderer(new ImageCellRenderer());
        getColumnModel().getColumn(0).setMaxWidth(60);
        getColumnModel().getColumn(0).setMinWidth(60);
        getColumnModel().getColumn(1).setMaxWidth(70);
        getColumnModel().getColumn(1).setMinWidth(70);
        getColumnModel().getColumn(9).setMaxWidth(94);
        getColumnModel().getColumn(9).setMinWidth(94);
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

    public ProductSortType getSortType() {
        return sortType;
    }

    public void setSortType(ProductSortType sortType) {
        this.sortType = sortType;
    }

    public ProductObject getSimilar() {
        return similar;
    }

    public void setSimilar(ProductObject similar) {
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
