package swing.inventory.project.models.product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import swing.inventory.project.ConnectionPool;
import swing.inventory.project.enums.ProductSortType;
import swing.inventory.project.objects.ProductObject;
import swing.inventory.project.utils.Utilities;

public class ProductModel {
	private Product p;

	public ProductModel(ConnectionPool cp) {
		this.p = new ProductImpl(cp);
	}

	public ConnectionPool getCP() {
		return this.p.getCP();
	}

	public void releaseConnection() {
		this.p.releaseConnection();
	}

	public boolean addProduct(ProductObject item) {
		return this.p.addProduct(item);
	}

	public boolean editProduct(ProductObject item) {
		return this.p.editProduct(item);
	}

	public boolean delProduct(ProductObject item) {
		return this.p.delProduct(item);
	}

	public ProductObject getProduct(int id) {
		ResultSet rs = this.p.getProduct(id);
		ProductObject item = null;
		if (rs != null) {
			try {
				if (rs.next()) {
					item = new ProductObject();
					item.setProduct_id(rs.getInt("product_id"));
					item.setProduct_name(Utilities.decode(rs.getString("product_name")));
					item.setProduct_quantity(rs.getInt("product_quantity"));
					item.setProduct_price(rs.getDouble("product_price"));
					item.setProduct_category_id(rs.getInt("product_category_id"));
					item.setAuthor_id(rs.getInt("product_author_id"));
					item.setProduct_details(Utilities.decode(rs.getString("product_details")));
					item.setProduct_created_date(rs.getString("product_created_date"));
					item.setProduct_modified_date(rs.getString("product_modified_date"));
					item.setProduct_size(Utilities.decode(rs.getString("product_size")));
					item.setProduct_unit(Utilities.decode(rs.getString("product_unit")));
					item.setProduct_image(rs.getString("product_image"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return item;
	}

	public ArrayList<ProductObject> getProducts(ProductObject similar, int page, int total, ProductSortType type) {
		ArrayList<ProductObject> items = new ArrayList<>();
		ProductObject item = null;
		ResultSet rs = this.p.getProducts(similar, page, total, type);
		if (rs != null) {
			try {
				while (rs.next()) {
					item = new ProductObject();
					item.setProduct_id(rs.getInt("product_id"));
					item.setProduct_name(Utilities.decode(rs.getString("product_name")));
					item.setProduct_quantity(rs.getInt("product_quantity"));
					item.setProduct_price(rs.getDouble("product_price"));
					item.setProduct_category_id(rs.getInt("product_category_id"));
					item.setAuthor_id(rs.getInt("product_author_id"));
					item.setProduct_details(Utilities.decode(rs.getString("product_details")));
					item.setProduct_created_date(rs.getString("product_created_date"));
					item.setProduct_modified_date(rs.getString("product_modified_date"));
					item.setProduct_size(Utilities.decode(rs.getString("product_size")));
					item.setProduct_unit(Utilities.decode(rs.getString("product_unit")));
					item.setProduct_image(rs.getString("product_image"));
					item.setCategory_name(Utilities.decode(rs.getString("category_name")));
					items.add(item);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return items;
	}

	public int countProduct(ProductObject similar) {
		ResultSet rs = this.p.countProduct(similar);
		if (rs != null) {
			try {
				if (rs.next()) {
					return rs.getInt("total");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public int countProduct(int month) {
		ResultSet rs = this.p.countProduct(month);
		if (rs != null) {
			try {
				if (rs.next()) {
					return rs.getInt("total");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
}
