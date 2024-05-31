package swing.inventory.project.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import swing.inventory.project.ConnectionPool;
import swing.inventory.project.ConnectionPoolImpl;


public class BasicImpl implements Basic {
	
	private ConnectionPool cp;
	protected Connection con;
	private String objectName;

	public BasicImpl(ConnectionPool cp, String objectName) {
		this.objectName = objectName;

		if (cp == null) {
			this.cp = new ConnectionPoolImpl();
		} else {
			this.cp = cp;
		}

		try {
			this.con = this.cp.getConnection(this.objectName);

			if (this.con.getAutoCommit()) {
				this.con.setAutoCommit(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private boolean exe(PreparedStatement pre) {
		if (pre != null) {
			try {
				int results = pre.executeUpdate();
				if (results == 0) {
					this.con.rollback();
					return false;
				}
				this.con.commit();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					this.con.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} finally {
				pre = null;
			}
		}
		return false;
	}

	@Override
	public boolean add(PreparedStatement pre) {
		return this.exe(pre);
	}

	@Override
	public boolean edit(PreparedStatement pre) {
		return this.exe(pre);
	}

	@Override
	public boolean del(PreparedStatement pre) {
		return this.exe(pre);
	}

	@Override
	public ResultSet get(String sql, int id) {
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			if (id > 0) {
				pre.setInt(1, id);
			}
			return pre.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			sql = null;
		}
		return null;
	}

	@Override
	public ResultSet get(ArrayList<String> sql, String name, String pass) {
		try {
			String str_select = sql.get(0);
			PreparedStatement pre = this.con.prepareStatement(str_select);
			pre.setString(1, name);
			pre.setString(2, pass);
			ResultSet rs = pre.executeQuery();
			if (rs != null) {
				String str_upd = sql.get(1);
				PreparedStatement preupd = this.con.prepareStatement(str_upd);
				preupd.setString(1, name);
				preupd.setString(2, pass);
				int result = preupd.executeUpdate();
				if (result == 0) {
					this.con.rollback();
					return null;
				} else {
					if (!this.con.getAutoCommit()) {
						this.con.commit();
					}
					return rs;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			name = null;
			pass = null;
		}

		return null;
	}

	@Override
	public ResultSet gets(String sql) {
		return this.get(sql, 0);
	}

	@Override
	public ConnectionPool getCP() {
		return this.cp;
	}

	@Override
	public void releaseConnection() {
		try {
			this.cp.releaseConnection(this.con, this.objectName);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public ArrayList<ResultSet> getReList(String multiSelect) {
		ArrayList<ResultSet> res = new ArrayList<>();
		try {
			PreparedStatement pre = this.con.prepareStatement(multiSelect);
			boolean result = pre.execute();
			do {
				res.add(pre.getResultSet());
				result = pre.getMoreResults(Statement.KEEP_CURRENT_RESULT);
			} while (result);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return res;
	}
}
