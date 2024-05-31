package swing.inventory.project.models.user;

import java.sql.ResultSet;

import swing.inventory.project.ShareControl;
import swing.inventory.project.enums.UserSortType;
import swing.inventory.project.objects.UserObject;

public interface User extends ShareControl {
	public boolean addUser(UserObject item);

	public boolean isExisting(UserObject item);

	public boolean editUser(UserObject item);

	public boolean editUserPermission(UserObject item);

	public boolean editUserPassword(UserObject item);

	public boolean isExisting(String username, String password);

	public boolean delUser(UserObject item);

	public ResultSet getUser(int id);

	public ResultSet getUser(String username, String userpass);

	public ResultSet getUsers(UserObject similar, int page, int total, UserSortType type);

	public ResultSet countUser(UserObject similar);
}
