package swing.inventory.project.models.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import swing.inventory.project.ConnectionPool;
import swing.inventory.project.enums.UserSortType;
import swing.inventory.project.objects.UserObject;
import swing.inventory.project.utils.Utilities;

public class UserModel {
	private User u;

	public UserModel(ConnectionPool cp) {
		this.u = new UserImpl(cp);
	}

	protected void finallize() throws Throwable {
		this.u = null;
	}

	public ConnectionPool getCP() {
		return this.u.getCP();
	}

	public void releaseConnection() {
		this.u.releaseConnection();
	}

	public boolean addUser(UserObject item) {
		return this.u.addUser(item);
	}

	public boolean isExistsByName(String username) {
		UserObject user = new UserObject();
		user.setUser_name(username);
		return this.u.isExisting(user);
	}

	public boolean editUser(UserObject item) {
		return this.u.editUser(item);
	}

	public boolean editUserPermission(UserObject item) {
		return this.u.editUserPermission(item);
	}

	public boolean editUserPassword(UserObject item) {
		return this.u.editUserPassword(item);
	}

	public boolean isExisting(String username, String password) {
		return this.u.isExisting(username, password);
	}

	public boolean delUser(UserObject item) {
		return this.u.delUser(item);
	}

	public UserObject getUserObject(int id) {
		UserObject item = null;

		ResultSet rs = this.u.getUser(id);
		if (rs != null) {
			try {
				if (rs.next()) {
					item = new UserObject();
					item.setUser_id(rs.getInt("user_id"));
					item.setUser_name(rs.getString("user_name"));
					item.setUser_fullname(Utilities.decode(rs.getString("user_fullname")));
					item.setUser_email(rs.getString("user_email"));
					item.setUser_phone(rs.getString("user_phone"));
					item.setUser_logined(rs.getInt("user_logined"));
					item.setUser_role(rs.getInt("user_role"));
					item.setUser_created_at(rs.getString("user_created_at"));
					item.setUser_modified_at(rs.getString("user_modified_at"));
					item.setUser_notes(Utilities.decode(rs.getString("user_notes")));
					item.setUser_address(Utilities.decode(rs.getString("user_address")));
					item.setUser_image(rs.getString("user_image"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return item;
	}

	public UserObject getUserObject(String username, String userpass) {
		UserObject item = null;
		ResultSet rs = this.u.getUser(username, userpass);
		if (rs != null) {
			try {
				if (rs.next()) {
					item = new UserObject();
					item.setUser_id(rs.getInt("user_id"));
					item.setUser_name(rs.getString("user_name"));
					item.setUser_fullname(Utilities.decode(rs.getString("user_fullname")));
					item.setUser_email(rs.getString("user_email"));
					item.setUser_phone(rs.getString("user_phone"));
					item.setUser_logined(rs.getInt("user_logined"));
					item.setUser_role(rs.getInt("user_role"));
					item.setUser_created_at(rs.getString("user_created_at"));
					item.setUser_modified_at(rs.getString("user_modified_at"));
					item.setUser_notes(Utilities.decode(rs.getString("user_notes")));
					item.setUser_address(Utilities.decode(rs.getString("user_address")));
					item.setUser_image(rs.getString("user_image"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return item;
	}

	public ArrayList<UserObject> getUserObjects(UserObject similar, int page, int total, UserSortType type) {
		ArrayList<UserObject> items = new ArrayList<>();
		UserObject item = null;
		ResultSet rs = this.u.getUsers(similar, page, total, type);
		if (rs != null) {
			try {
				while (rs.next()) {
					item = new UserObject();
					item.setUser_id(rs.getInt("user_id"));
					item.setUser_name(rs.getString("user_name"));
					item.setUser_fullname(Utilities.decode(rs.getString("user_fullname")));
					item.setUser_email(rs.getString("user_email"));
					item.setUser_phone(rs.getString("user_phone"));
					item.setUser_logined(rs.getInt("user_logined"));
					item.setUser_role(rs.getInt("user_role"));
					item.setUser_created_at(rs.getString("user_created_at"));
					item.setUser_modified_at(rs.getString("user_modified_at"));
					item.setUser_notes(Utilities.decode(rs.getString("user_notes")));
					item.setUser_address(Utilities.decode(rs.getString("user_address")));
					item.setUser_image(rs.getString("user_image"));
					items.add(item);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return items;
	}

	public int countUser(UserObject similar) {
		ResultSet rs = this.u.countUser(similar);
		if (rs != null) {
			try {
				if (rs.next()) {
					return rs.getInt("total");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return 0;
	}
}
