package swing.inventory.project.objects;

public class ProductObject extends CategoryObject {

	private int product_id;
	private String product_name;
	private int product_quantity;
	private double product_price;
	private int product_category_id;
	private int author_id;
	private String product_details;
	private String product_created_date;
	private String product_modified_date;
	private String product_size;
	private String product_unit;
	private String product_image;

	public int getProduct_id() {
		return product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public int getProduct_quantity() {
		return product_quantity;
	}

	public double getProduct_price() {
		return product_price;
	}

	public int getProduct_category_id() {
		return product_category_id;
	}

	public int getAuthor_id() {
		return author_id;
	}

	public String getProduct_details() {
		return product_details;
	}

	public String getProduct_created_date() {
		return product_created_date;
	}

	public String getProduct_modified_date() {
		return product_modified_date;
	}

	public String getProduct_size() {
		return product_size;
	}

	public String getProduct_unit() {
		return product_unit;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public void setProduct_quantity(int product_quantity) {
		this.product_quantity = product_quantity;
	}

	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}

	public void setProduct_category_id(int product_category_id) {
		this.product_category_id = product_category_id;
	}

	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}

	public void setProduct_details(String product_details) {
		this.product_details = product_details;
	}

	public void setProduct_created_date(String product_created_date) {
		this.product_created_date = product_created_date;
	}

	public void setProduct_modified_date(String product_modified_date) {
		this.product_modified_date = product_modified_date;
	}

	public void setProduct_size(String product_size) {
		this.product_size = product_size;
	}

	public void setProduct_unit(String product_unit) {
		this.product_unit = product_unit;
	}

	public String getProduct_image() {
		return product_image;
	}

	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}

}
