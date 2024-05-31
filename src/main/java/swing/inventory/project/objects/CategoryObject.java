package swing.inventory.project.objects;

public class CategoryObject {
	private int category_id;
	private String category_name;
	private String category_notes;
	private int author_id;
	private String category_created_date;
	private String category_modified_date;

	public int getCategory_id() {
		return category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public String getCategory_notes() {
		return category_notes;
	}

	public int getAuthor_id() {
		return author_id;
	}

	public String getCategory_created_date() {
		return category_created_date;
	}

	public String getCategory_modified_date() {
		return category_modified_date;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public void setCategory_notes(String category_notes) {
		this.category_notes = category_notes;
	}

	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}

	public void setCategory_created_date(String category_created_date) {
		this.category_created_date = category_created_date;
	}

	public void setCategory_modified_date(String category_modified_date) {
		this.category_modified_date = category_modified_date;
	}
	
	@Override
	public String toString() {
		return this.category_name;
	}

}
