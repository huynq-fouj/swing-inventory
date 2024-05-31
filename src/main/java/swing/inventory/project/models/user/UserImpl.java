package swing.inventory.project.models.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import swing.inventory.project.ConnectionPool;
import swing.inventory.project.enums.UserSortType;
import swing.inventory.project.models.BasicImpl;
import swing.inventory.project.objects.UserObject;
import swing.inventory.project.utils.Utilities;

public class UserImpl extends BasicImpl implements User {

	public UserImpl(ConnectionPool cp) {
		super(cp, "User");
	}

	@Override
	public boolean addUser(UserObject item) {

		if (this.isExisting(item)) {
			return false;
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO tbluser(");
		sql.append("user_name, user_password, user_fullname, user_email, user_phone, user_role, user_logined,");
		sql.append("user_created_at, user_modified_at, user_notes, user_address, user_image");
		sql.append(") VALUE(?,md5(?),?,?,?,?,?,?,?,?,?,?)");
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, item.getUser_name());
			pre.setString(2, item.getUser_password());
			pre.setString(3, Utilities.encode(item.getUser_fullname()));
			pre.setString(4, item.getUser_email());
			pre.setString(5, item.getUser_phone());
			pre.setInt(6, item.getUser_role());
			pre.setInt(7, item.getUser_logined());
			pre.setString(8, Utilities.getDate());
			pre.setString(9, Utilities.getDate());
			pre.setString(10, Utilities.encode(item.getUser_notes()));
			pre.setString(11, Utilities.encode(item.getUser_address()));
			pre.setString(12, item.getUser_image());
			return this.add(pre);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return false;
	}

	public boolean isExisting(UserObject item) {
		boolean flag = false;
		String sql = "SELECT user_id FROM tbluser WHERE user_name='" + item.getUser_name() + "'";
		ResultSet rs = this.gets(sql);
		if (rs != null) {
			try {
				if (rs.next()) {
					flag = true;
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public boolean editUser(UserObject item) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE tbluser SET ");
		sql.append("user_fullname=?, user_email=?, user_phone=?, ");
		sql.append("user_modified_at=?, user_notes=?, user_address=?, user_image=? ");
		sql.append("WHERE user_id=? ");
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, Utilities.encode(item.getUser_fullname()));
			pre.setString(2, item.getUser_email());
			pre.setString(3, item.getUser_phone());
			pre.setString(4, Utilities.getDate());
			pre.setString(5, Utilities.encode(item.getUser_notes()));
			pre.setString(6, Utilities.encode(item.getUser_address()));
			pre.setString(7, item.getUser_image());
			pre.setInt(8, item.getUser_id());
			return this.edit(pre);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean editUserPermission(UserObject item) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE tbluser SET ");
		sql.append("user_role=? ");
		sql.append("WHERE user_id=? ");
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setInt(1, item.getUser_role());
			pre.setInt(2, item.getUser_id());
			return this.edit(pre);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean delUser(UserObject item) {
		String sql = "DELETE FROM tbluser WHERE user_id=? ";
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setInt(1, item.getUser_id());
			return this.del(pre);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public ResultSet getUser(int id) {
		String sql = "SELECT * FROM tbluser WHERE (user_id=?);";
		return this.get(sql, id);
	}

	@Override
	public ResultSet getUser(String username, String userpass) {
		ArrayList<String> sql = new ArrayList<>();
		String sqlSelect = "SELECT * FROM tbluser WHERE (user_name = ?) AND (user_password = md5(?));";
		String sqlUpdate = "UPDATE tbluser SET user_logined = user_logined + 1 WHERE (user_name=?) AND (user_password = md5(?));";
		sql.add(sqlSelect);
		sql.add(sqlUpdate);
		return this.get(sql, username, userpass);
	}
	
	private String createConditions(UserObject similar) {
		StringBuilder conds = new StringBuilder();
		if(similar != null) {
			conds.append(" user_role <= ").append(similar.getUser_role()).append(" ");
			
			String key = similar.getUser_name();
			if(key != null && !key.equalsIgnoreCase("")) {
				key = Utilities.encode(key);
				conds.append(" AND ((user_name LIKE '%"+key+"%') OR ");
				conds.append(" (user_fullname LIKE '%"+key+"%') OR ");
				conds.append(" (user_email LIKE '%"+key+"%')) ");
			}
		}
		
		if(!conds.toString().equalsIgnoreCase("")) {
			conds.insert(0, " WHERE ");
		}
		
		conds.append(" ");
		
		return conds.toString();
	}

	@Override
	public ResultSet getUsers(UserObject similar, int page, int total, UserSortType type) {
		int at = (page - 1) * total;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tbluser ")
		.append(this.createConditions(similar));
		switch(type) {
		case ID_ASC:
			sql.append("ORDER BY user_id asc");
			break;
		case FULLNAME_ASC:
			sql.append("ORDER BY user_fullname asc");
			break;
		case NAME_ASC:
			sql.append("ORDER BY user_name asc");
			break;
		case EMAIL_ASC:
			sql.append("ORDER BY user_email asc");
			break;
		case PHONE_ASC:
			sql.append("ORDER BY user_phone asc");
			break;
		case LOGINED_ASC:
			sql.append("ORDER BY user_logined asc");
			break;
		case IMAGE_ASC:
			sql.append("ORDER BY user_image asc");
			break;
		case ADDRESS_ASC:
			sql.append("ORDER BY user_address asc");
			break;
		case NOTES_ASC:
			sql.append("ORDER BY user_notes asc");
			break;
		case FULLNAME_DESC:
			sql.append("ORDER BY user_fullname desc");
			break;
		case NAME_DESC:
			sql.append("ORDER BY user_name desc");
			break;
		case EMAIL_DESC:
			sql.append("ORDER BY user_email desc");
			break;
		case PHONE_DESC:
			sql.append("ORDER BY user_phone desc");
			break;
		case LOGINED_DESC:
			sql.append("ORDER BY user_logined desc");
			break;
		case IMAGE_DESC:
			sql.append("ORDER BY user_image desc");
			break;
		case ADDRESS_DESC:
			sql.append("ORDER BY user_address desc");
			break;
		case NOTES_DESC:
			sql.append("ORDER BY user_notes desc");
			break;
		default:
			sql.append("ORDER BY user_id desc");
		}
		sql.append(" ");
		sql.append("LIMIT ").append(at).append(", ").append(total);
		sql.append(";");
		return this.gets(sql.toString());
	}
	
	@Override
	public ResultSet countUser(UserObject similar) {
		String sql = "SELECT COUNT(*) AS total FROM tbluser ";
		sql += this.createConditions(similar);
		sql += ";";
		return this.gets(sql);
	}

	@Override
	public boolean editUserPassword(UserObject item) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE tbluser SET ");
		sql.append("user_password=md5(?) ");
		sql.append("WHERE user_id=? ");
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, item.getUser_password());
			pre.setInt(2, item.getUser_id());
			return this.edit(pre);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean isExisting(String username, String password) {
		boolean flag = false;
		String sql = "SELECT user_id FROM tbluser WHERE user_name='" + username + "' AND user_password=md5('" + password +"')";
		ResultSet rs = this.gets(sql);
		if (rs != null) {
			try {
				if (rs.next()) {
					flag = true;
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	
}
