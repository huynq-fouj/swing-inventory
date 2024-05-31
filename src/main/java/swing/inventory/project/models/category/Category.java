package swing.inventory.project.models.category;

import java.sql.ResultSet;

import swing.inventory.project.ShareControl;
import swing.inventory.project.enums.CategorySortType;
import swing.inventory.project.objects.CategoryObject;

public interface Category extends ShareControl {
	public boolean addCategory(CategoryObject item);

	public boolean editCategory(CategoryObject item);

	public boolean delCategory(CategoryObject item);

	public ResultSet getCategory(int id);

	public ResultSet getCategories(CategoryObject similar, short page, int total, CategorySortType type);

	public ResultSet countCategory(CategoryObject similar);

}
