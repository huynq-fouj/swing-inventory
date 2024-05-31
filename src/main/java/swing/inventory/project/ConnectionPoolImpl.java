package swing.inventory.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionPoolImpl implements ConnectionPool {
	
	private String driver;
	private String url;
	private String username;
	private String userpass;
	private Stack<Connection> pool;
	
	public ConnectionPoolImpl() {
		this.driver = "com.mysql.jdbc.Driver";
		this.url = "jdbc:mysql://localhost:3306/inventory_manager?allowMultiQueries=true";
		this.username = "root";
		this.userpass = "123456";
		this.loadDriver();
		this.pool = new Stack<>();
	}
	
	private void loadDriver() {
		try {
			Class.forName(this.driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Connection getConnection(String objectName) throws SQLException {
		if(this.pool.isEmpty()) {
			Logger.getLogger(ConnectionPoolImpl.class.getName()).log(Level.INFO, () -> objectName + " have created a new Connection");
			return DriverManager.getConnection(this.url, this.username, this.userpass);
		}else {
			Logger.getLogger(ConnectionPoolImpl.class.getName()).log(Level.INFO, () -> objectName + " have popped the Connection");
			return this.pool.pop();
		}
	}

	@Override
	public void releaseConnection(Connection con, String objectName) throws SQLException {
		Logger.getLogger(ConnectionPoolImpl.class.getName()).log(Level.INFO, () -> objectName + " have pushed the Connection.");
		this.pool.push(con);
	}
	
	protected void finalized() throws Throwable {
		this.pool.clear();
		this.pool = null;
		System.gc();
		Logger.getLogger(ConnectionPoolImpl.class.getName()).log(Level.INFO, "ConnectionPool is closed.");
	}
	
}
