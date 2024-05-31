package swing.inventory.project.events;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import swing.inventory.project.components.table.category.CategoryTable;
import swing.inventory.project.enums.CategorySortType;

public class SortCategoryEvent implements MouseListener {

    private CategoryTable categoryTable;

    public SortCategoryEvent(CategoryTable categoryTable) {
        this.categoryTable = categoryTable;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point point = e.getPoint();
        int column = categoryTable.columnAtPoint(point);
        if(column == 0) {
			if(categoryTable.getSortType() != CategorySortType.ID_ASC) {
				categoryTable.setSortType(CategorySortType.ID_ASC);
				categoryTable.loadModel();
            } else {
				categoryTable.setSortType(CategorySortType.ID_DESC);
				categoryTable.loadModel();
			}
		}
        if(column == 1) {
			if(categoryTable.getSortType() != CategorySortType.NAME_ASC) {
                categoryTable.setSortType(CategorySortType.NAME_ASC);
				categoryTable.loadModel();
            } else {
				categoryTable.setSortType(CategorySortType.NAME_DESC);
				categoryTable.loadModel();
			}
		}
        if(column == 2) {
			if(categoryTable.getSortType() != CategorySortType.NOTES_ASC) {
				categoryTable.setSortType(CategorySortType.NOTES_ASC);
                categoryTable.loadModel();
			} else {
				categoryTable.setSortType(CategorySortType.NOTES_DESC);
				categoryTable.loadModel();
			}
		}
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}
    
}
