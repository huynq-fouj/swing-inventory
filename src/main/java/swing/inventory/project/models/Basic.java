package swing.inventory.project.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import swing.inventory.project.ShareControl;

public interface Basic extends ShareControl {

	public boolean add(PreparedStatement pre);
	public boolean edit(PreparedStatement pre);
	public boolean del(PreparedStatement pre);
	
	public ResultSet get(String sql, int id);
	public ResultSet get(ArrayList<String> sql, String name, String pass);
	public ResultSet gets(String sql);
	public ArrayList<ResultSet> getReList(String multiSelect);
	
}
