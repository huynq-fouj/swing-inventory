package swing.inventory.project.events;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import swing.inventory.project.components.table.product.ProductTable;
import swing.inventory.project.enums.ProductSortType;


public class SortProductEvent extends MouseAdapter {

    private ProductTable productTable;

    public SortProductEvent(ProductTable productTable) {
        this.productTable = productTable;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point point = e.getPoint();
        int column = productTable.columnAtPoint(point);
        switch (column) {
            case 0:
                if(productTable.getSortType() != ProductSortType.ID_ASC) {
                    productTable.setSortType(ProductSortType.ID_ASC);
                    productTable.loadModel();
                } else {
                    productTable.setSortType(ProductSortType.ID_DESC);
                    productTable.loadModel();
                }
                break;
            case 1:
                if(productTable.getSortType() != ProductSortType.IMAGE_ASC) {
                    productTable.setSortType(ProductSortType.IMAGE_ASC);
                    productTable.loadModel();
                } else {
                    productTable.setSortType(ProductSortType.IMAGE_DESC);
                    productTable.loadModel();
                }
                break;
            case 2:
                if(productTable.getSortType() != ProductSortType.NAME_ASC) {
                    productTable.setSortType(ProductSortType.NAME_ASC);
                    productTable.loadModel();
                } else {
                    productTable.setSortType(ProductSortType.NAME_DESC);
                    productTable.loadModel();
                }
                break;
            case 3:
                if(productTable.getSortType() != ProductSortType.QUANTITY_ASC) {
                    productTable.setSortType(ProductSortType.QUANTITY_ASC);
                    productTable.loadModel();
                } else {
                    productTable.setSortType(ProductSortType.QUANTITY_DESC);
                    productTable.loadModel();
                }
                break;
            case 4:
                if(productTable.getSortType() != ProductSortType.PRICE_ASC) {
                    productTable.setSortType(ProductSortType.PRICE_ASC);
                    productTable.loadModel();
                } else {
                    productTable.setSortType(ProductSortType.PRICE_DESC);
                    productTable.loadModel();
                }
                break;
            case 5:
                if(productTable.getSortType() != ProductSortType.SIZE_ASC) {
                    productTable.setSortType(ProductSortType.SIZE_ASC);
                    productTable.loadModel();
                } else {
                    productTable.setSortType(ProductSortType.SIZE_DESC);
                    productTable.loadModel();
                }
                break;
            case 6:
                if(productTable.getSortType() != ProductSortType.UNIT_ASC) {
                    productTable.setSortType(ProductSortType.UNIT_ASC);
                    productTable.loadModel();
                } else {
                    productTable.setSortType(ProductSortType.UNIT_DESC);
                    productTable.loadModel();
                }
                break;
            case 7:
                if(productTable.getSortType() != ProductSortType.CATEGORY_NAME_ASC) {
                    productTable.setSortType(ProductSortType.CATEGORY_NAME_ASC);
                    productTable.loadModel();
                } else {
                    productTable.setSortType(ProductSortType.CATEGORY_NAME_DESC);
                    productTable.loadModel();
                }
                break;
            case 8:
                if(productTable.getSortType() != ProductSortType.DETAIL_ASC) {
                    productTable.setSortType(ProductSortType.DETAIL_ASC);
                    productTable.loadModel();
                } else {
                    productTable.setSortType(ProductSortType.DETAIL_DESC);
                    productTable.loadModel();
                }
                break;
            default:
                break;
        }
    }
    
}
