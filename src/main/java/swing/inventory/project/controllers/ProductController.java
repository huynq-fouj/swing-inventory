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
import swing.inventory.project.enums.ProductSortType;
import swing.inventory.project.models.product.ProductModel;
import swing.inventory.project.objects.ProductObject;

public class ProductController {
	private ProductModel pm;
	private Logger logger = Logger.getLogger(ProductController.class.getName());

	public ProductController(ConnectionPool cp) {
		this.pm = new ProductModel(cp);
	}

	public ConnectionPool getCP() {
		return this.pm.getCP();
	}

	public void releaseConnection() {
		this.pm.releaseConnection();
	}

	public boolean addProduct(ProductObject item) {
		return this.pm.addProduct(item);
	}

	public boolean editProduct(ProductObject item) {
		return this.pm.editProduct(item);
	}

	public boolean delProduct(ProductObject item) {
		return this.pm.delProduct(item);
	}

	public ProductObject getProduct(int id) {
		return this.pm.getProduct(id);
	}

	public ArrayList<ProductObject> getProducts(ProductObject similar, int page, int total, ProductSortType type) {
		return this.pm.getProducts(similar, page, total, type);
	}

	public int countProduct(ProductObject similar) {
		return this.pm.countProduct(similar);
	}

	public int countProduct(int month) {
		return this.pm.countProduct(month);
	}

	public void export(File fileToSave, String ext) {
		int total = countProduct(null);
		List<ProductObject> items = getProducts(null, 1, total, ProductSortType.ID_ASC);
		String[] columns = {"ID", "Tên sản phẩm", "Số lượng",
			"Đơn giá", "Danh mục", "Mô tả",
			"Kích thước", "Đơn vị tính", "ID người tạo",
			"Ngày tạo", "Sửa lần cuối"};
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Product");

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
		for(ProductObject item: items) {
			Row row = sheet.createRow(i++);
			row.createCell(0).setCellValue(item.getProduct_id());
			row.createCell(1).setCellValue(item.getProduct_name());
			row.createCell(2).setCellValue(item.getProduct_quantity());
			row.createCell(3).setCellValue(item.getProduct_price());
			row.createCell(4).setCellValue(item.getCategory_name());
			row.createCell(5).setCellValue(item.getProduct_details());
			row.createCell(6).setCellValue(item.getProduct_size());
			row.createCell(7).setCellValue(item.getProduct_unit());
			row.createCell(8).setCellValue(item.getAuthor_id());
			row.createCell(9).setCellValue(item.getProduct_created_date());
			row.createCell(10).setCellValue(item.getProduct_modified_date());
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
