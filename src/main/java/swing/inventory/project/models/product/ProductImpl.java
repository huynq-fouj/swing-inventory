package swing.inventory.project.models.product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;

import swing.inventory.project.ConnectionPool;
import swing.inventory.project.enums.ProductSortType;
import swing.inventory.project.models.BasicImpl;
import swing.inventory.project.objects.ProductObject;
import swing.inventory.project.utils.Utilities;

public class ProductImpl extends BasicImpl implements Product {
	
	public ProductImpl(ConnectionPool cp) {
		super(cp, "Product");
	}

	@Override
	public boolean addProduct(ProductObject item) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO tblproduct(")
		.append("product_name, product_quantity, product_price,")
		.append("product_category_id, product_author_id, product_details,")
		.append("product_created_date, product_modified_date, product_size,")
		.append("product_unit, product_image")
		.append(") VALUES(?,?,?,?,?,?,?,?,?,?,?)");
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, Utilities.encode(item.getProduct_name()));
			pre.setInt(2, item.getProduct_quantity());
			pre.setDouble(3, item.getProduct_price());
			pre.setInt(4, item.getProduct_category_id());
			pre.setInt(5, item.getAuthor_id());
			pre.setString(6, Utilities.encode(item.getProduct_details()));
			pre.setString(7, Utilities.getDate());
			pre.setString(8, Utilities.getDate());
			pre.setString(9, Utilities.encode(item.getProduct_size()));
			pre.setString(10, Utilities.encode(item.getProduct_unit()));
			pre.setString(11, item.getProduct_image());
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

	@Override
	public boolean delProduct(ProductObject item) {
		String sql = "DELETE FROM tblproduct WHERE product_id=?";
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setInt(1, item.getProduct_id());
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
	public boolean editProduct(ProductObject item) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE tblproduct SET ")
		.append("product_name=?, product_quantity=?, product_price=?,")
		.append("product_category_id=?, product_details=?,")
		.append("product_modified_date=?, product_size=?,")
		.append("product_unit=?, product_image=? ")
		.append("WHERE product_id=?");
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, Utilities.encode(item.getProduct_name()));
			pre.setInt(2, item.getProduct_quantity());
			pre.setDouble(3, item.getProduct_price());
			pre.setInt(4, item.getProduct_category_id());
			pre.setString(5, Utilities.encode(item.getProduct_details()));
			pre.setString(6, Utilities.getDate());
			pre.setString(7, Utilities.encode(item.getProduct_size()));
			pre.setString(8, Utilities.encode(item.getProduct_unit()));
			pre.setString(9, item.getProduct_image());
			pre.setInt(10, item.getProduct_id());
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
	public ResultSet getProduct(int id) {
		String sql = "SELECT * FROM tblproduct WHERE product_id=?;";
		return this.get(sql, id);
	}

	@Override
	public ResultSet getProducts(ProductObject similar, int  page, int total, ProductSortType type) {
		int at = (page - 1) * total;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tblproduct ")
		.append("LEFT JOIN tblcategory ON tblproduct.product_category_id=tblcategory.category_id ")
		.append(this.createConditions(similar));
		switch(type) {
		case ID_ASC:
			sql.append("ORDER BY product_id ASC");
			break;
		case NAME_ASC:
			sql.append("ORDER BY product_name ASC");
			break;
		case QUANTITY_ASC:
			sql.append("ORDER BY product_quantity ASC");
			break;
		case PRICE_ASC:
			sql.append("ORDER BY product_price ASC");
			break;
		case CATEGORY_NAME_ASC:
			sql.append("ORDER BY category_name ASC");
			break;
		case IMAGE_ASC:
			sql.append("ORDER BY product_image ASC");
			break;
		case DETAIL_ASC:
			sql.append("ORDER BY product_details ASC");
			break;
		case SIZE_ASC:
			sql.append("ORDER BY product_size ASC");
			break;
		case UNIT_ASC:
			sql.append("ORDER BY product_unit ASC");
			break;
		case NAME_DESC:
			sql.append("ORDER BY product_name DESC");
			break;
		case QUANTITY_DESC:
			sql.append("ORDER BY product_quantity DESC");
			break;
		case PRICE_DESC:
			sql.append("ORDER BY product_price DESC");
			break;
		case CATEGORY_NAME_DESC:
			sql.append("ORDER BY category_name DESC");
			break;
		case IMAGE_DESC:
			sql.append("ORDER BY product_image DESC");
			break;
		case DETAIL_DESC:
			sql.append("ORDER BY product_details DESC");
			break;
		case SIZE_DESC:
			sql.append("ORDER BY product_size DESC");
			break;
		case UNIT_DESC:
			sql.append("ORDER BY product_unit DESC");
			break;
		default:
			sql.append("ORDER BY product_id DESC");
		}
		sql.append(" ");
		sql.append("LIMIT ").append(at).append(", ").append(total);
		sql.append(";");
		return this.gets(sql.toString());
	}
	
	@Override
	public ResultSet countProduct(ProductObject similar) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) AS total FROM tblproduct ")
		.append(this.createConditions(similar));
		return this.gets(sql.toString());
	}
	
	private String createConditions(ProductObject similar) {
		StringBuilder conds = new StringBuilder();
		if(similar != null) {
			String name = similar.getProduct_name();
			if(name != null && !name.equalsIgnoreCase("")) {
				if(!conds.toString().equalsIgnoreCase("")) {
					conds.append(" AND ");
				}
				String key = Utilities.encode(name);
				conds.append(" ((product_name LIKE '%"+key+"%') OR ");
				conds.append(" (product_details LIKE '%"+key+"%')) ");
			}

			int cid = similar.getProduct_category_id();
			if(cid > 0) {
				if(!conds.toString().equalsIgnoreCase("")) {
					conds.append(" AND ");
				}
				conds.append(" (product_category_id="+cid+") ");
			}

			String price = similar.getProduct_details();
			if(price != null && !price.equals("") && price.indexOf(":") != -1) {
				String prices[] = price.split(":");
				if(prices.length == 2) {
					String strMin = prices[0].trim();
					String strMax = prices[1].trim();
					if(!strMin.equals("")) {
						try {
							Double min = Double.parseDouble(strMin);
							if(min > 0) {
								if(!conds.toString().equalsIgnoreCase("")) {
									conds.append(" AND ");
								}
								conds.append(" (product_price>"+min+") ");
							}
						} catch (Exception e) {}
					}
					if(!strMax.equals("")) {
						try {
							Double max = Double.parseDouble(strMax);
							if(max > 0) {
								if(!conds.toString().equalsIgnoreCase("")) {
									conds.append(" AND ");
								}
								conds.append(" (product_price<"+max+") ");
							}
						} catch (Exception e) {}
					}
				}
			}
		}

		
		if(!conds.toString().equalsIgnoreCase("")) {
			conds.insert(0, " WHERE ");
		}
		
		conds.append(" ");
		
		return conds.toString();
	}

	public ResultSet countProduct(int month) {
		String m = "0" + month;
		m = m.length() > 2 ? m.substring(1) : m;
		int y = Year.now().getValue();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) AS total FROM tblproduct WHERE product_created_date LIKE '%/")
		.append(m)
		.append("/")
		.append(y)
		.append("';");
		return this.gets(sql.toString());
	}

}
