package swing.inventory.project.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import swing.inventory.project.ConnectionPool;
import swing.inventory.project.enums.CategorySortType;
import swing.inventory.project.models.category.CategoryModel;
import swing.inventory.project.objects.CategoryObject;

public class CategoryController {
	private CategoryModel cm;
	private Logger logger = Logger.getLogger(CategoryController.class.getName());

	public CategoryController(ConnectionPool cp) {
		this.cm = new CategoryModel(cp);
	}

	public ConnectionPool getCP() {
		return this.cm.getCP();
	}

	public void releaseConnection() {
		this.cm.releaseConnection();
	}

	public boolean addCategory(CategoryObject item) {
		return this.cm.addCategory(item);
	}

	public boolean editCategory(CategoryObject item) {
		return this.cm.editCategory(item);
	}

	public boolean delCategory(CategoryObject item) {
		return this.cm.delCategory(item);
	}

	public CategoryObject getCategory(int id) {
		return this.cm.getCategory(id);
	}

	public ArrayList<CategoryObject> getCategories(CategoryObject similar, short page, int total, CategorySortType type) {
		return this.cm.getCategories(similar, page, total, type);
	}

	public int countCategory(CategoryObject similar) {
		return this.cm.countCategory(similar);
	}

	public void export(File fileToSave, String ext) {
		int total = countCategory(null);
		List<CategoryObject> list = getCategories(null, (short) 1, total, CategorySortType.ID_ASC);
		String[] columns = {"ID", "Tên danh mục", "Ghi chú", "ID người tạo", "Ngày tạo", "Sửa lần cuối"};
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Category");

		Font headerFont = workbook.createFont();  
		headerFont.setBold(true);  
		headerFont.setFontHeightInPoints((short) 12);  

		CellStyle headerCellStyle = workbook.createCellStyle();  
		headerCellStyle.setFont(headerFont);

		Row headerRow = sheet.createRow(0);  

		for (int i = 0; i < columns.length; i++) {  
			Cell cell = headerRow.createCell(i);  
			cell.setCellValue(columns[i]);  
			cell.setCellStyle(headerCellStyle);  
		}

		int i = 1;

		for(CategoryObject item: list) {
			Row row = sheet.createRow(i++);
			row.createCell(0).setCellValue(item.getCategory_id());
			row.createCell(1).setCellValue(item.getCategory_name());
			row.createCell(2).setCellValue(item.getCategory_notes());
			row.createCell(3).setCellValue(item.getAuthor_id());
			row.createCell(4).setCellValue(item.getCategory_created_date());
			row.createCell(5).setCellValue(item.getCategory_modified_date());
		}

		try {
			String path = fileToSave.getAbsolutePath() + "." + ext;
			FileOutputStream fileOut = new FileOutputStream(path);
			workbook.write(fileOut);  
			fileOut.close();
			logger.info(() -> "Export file: " + fileToSave.getAbsolutePath() + "." + ext);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
				logger.warning("Not close workbook.");
			}
		}
	}

}
