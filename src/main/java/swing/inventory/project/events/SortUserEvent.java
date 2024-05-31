package swing.inventory.project.events;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import swing.inventory.project.components.table.user.UserTable;
import swing.inventory.project.enums.UserSortType;

public class SortUserEvent extends MouseAdapter {

    private UserTable userTable;

    public SortUserEvent(UserTable userTable) {
        this.userTable = userTable;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point point = e.getPoint();
        int column = userTable.columnAtPoint(point);
        switch (column) {
            case 0:
                if(userTable.getSortType() != UserSortType.ID_ASC) {
                    userTable.setSortType(UserSortType.ID_ASC);
                    userTable.loadModel();
                } else {
                    userTable.setSortType(UserSortType.ID_DESC);
                    userTable.loadModel();
                }
                break;
            case 1:
                if(userTable.getSortType() != UserSortType.IMAGE_ASC) {
                    userTable.setSortType(UserSortType.IMAGE_ASC);
                    userTable.loadModel();
                } else {
                    userTable.setSortType(UserSortType.IMAGE_DESC);
                    userTable.loadModel();
                }
                break;
            case 2:
                if(userTable.getSortType() != UserSortType.NAME_ASC) {
                    userTable.setSortType(UserSortType.NAME_ASC);
                    userTable.loadModel();
                } else {
                    userTable.setSortType(UserSortType.NAME_DESC);
                    userTable.loadModel();
                }
                break;
            case 3:
                if(userTable.getSortType() != UserSortType.FULLNAME_ASC) {
                    userTable.setSortType(UserSortType.FULLNAME_ASC);
                    userTable.loadModel();
                } else {
                    userTable.setSortType(UserSortType.FULLNAME_DESC);
                    userTable.loadModel();
                }
                break;
            case 4:
                if(userTable.getSortType() != UserSortType.EMAIL_ASC) {
                    userTable.setSortType(UserSortType.EMAIL_ASC);
                    userTable.loadModel();
                } else {
                    userTable.setSortType(UserSortType.EMAIL_DESC);
                    userTable.loadModel();
                }
                break;
            case 5:
                if(userTable.getSortType() != UserSortType.PHONE_ASC) {
                    userTable.setSortType(UserSortType.PHONE_ASC);
                    userTable.loadModel();
                } else {
                    userTable.setSortType(UserSortType.PHONE_DESC);
                    userTable.loadModel();
                }
                break;
            case 6:
                if(userTable.getSortType() != UserSortType.ADDRESS_ASC) {
                    userTable.setSortType(UserSortType.ADDRESS_ASC);
                    userTable.loadModel();
                } else {
                    userTable.setSortType(UserSortType.ADDRESS_DESC);
                    userTable.loadModel();
                }
                break;
            case 7:
                if(userTable.getSortType() != UserSortType.NOTES_ASC) {
                    userTable.setSortType(UserSortType.NOTES_ASC);
                    userTable.loadModel();
                } else {
                    userTable.setSortType(UserSortType.NOTES_DESC);
                    userTable.loadModel();
                }
                break;
            default:
                break;
        }
    }
    
}
