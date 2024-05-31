package swing.inventory.project.contexts;

import swing.inventory.project.ConnectionPool;

public class ConnectionContext {

	private static ConnectionPool cp;
	
	public static ConnectionPool getCP() {
		return ConnectionContext.cp;
	}
	
	public static void setCP(ConnectionPool cp) {
		ConnectionContext.cp = cp;
	}
	
}
