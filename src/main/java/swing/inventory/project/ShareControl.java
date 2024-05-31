package swing.inventory.project;

public interface ShareControl {
	public ConnectionPool getCP();
	public void releaseConnection();
}
