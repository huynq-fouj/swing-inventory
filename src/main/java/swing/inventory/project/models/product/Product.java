package swing.inventory.project.models.product;

import java.sql.ResultSet;

import swing.inventory.project.ShareControl;
import swing.inventory.project.enums.ProductSortType;
import swing.inventory.project.objects.ProductObject;

public interface Product extends ShareControl {
	public boolean addProduct(ProductObject product);

	public boolean delProduct(ProductObject product);

	public boolean editProduct(ProductObject product);

	public ResultSet getProduct(int id);

	public ResultSet getProducts(ProductObject similar, ProductSortType type);

	public ResultSet countProduct(ProductObject similar);

	public ResultSet countProduct(int month);
	
}
