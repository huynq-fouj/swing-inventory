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
import swing.inventory.project.enums.UserSortType;
import swing.inventory.project.models.user.UserModel;
import swing.inventory.project.objects.UserObject;

public class UserController {

	private UserModel um;
	private Logger logger = Logger.getLogger(UserController.class.getName());
	
	public UserController(ConnectionPool cp) {
		this.um = new UserModel(cp);
	}

	protected void finallize() throws Throwable {
		this.um = null;
	}
	
	public ConnectionPool getCP() {
		return this.um.getCP();
	}
	
	public void releaseConnection() {
		this.um.releaseConnection();
	}
	
	public boolean addUser(UserObject item) {
		return this.um.addUser(item);
	}
	
	public boolean existsByName(String name) {
		return this.um.isExistsByName(name);
	}

	public boolean editUser(UserObject item) {
		return this.um.editUser(item);
	}

	public boolean editUserPermission(UserObject item) {
		return this.um.editUserPermission(item);
	}

	public boolean editUserPassword(UserObject item) {
		return this.um.editUserPassword(item);
	}

	public boolean isExisting(String username, String password) {
		return this.um.isExisting(username, password);
	}

	public boolean delUser(UserObject item) {
		return this.um.delUser(item);
	}
	
	public UserObject getUserObject(int id) {
		return this.um.getUserObject(id);
	}
	
	public UserObject getUserObject(String username, String password) {
		return this.um.getUserObject(username, password);
	}
	
	public ArrayList<UserObject> getUserObjects(UserObject similar, int page, int total, UserSortType type) {
		return this.um.getUserObjects(similar, page, total, type);
	}

	public int countUser(UserObject similar) {
		return this.um.countUser(similar);
	}

	public void export(File fileToSave, String ext) {
		int total = countUser(null);
		List<UserObject> items = getUserObjects(null, 1, total, UserSortType.ID_ASC);
		String[] columns = {"ID", "Tên đăng nhập", "Họ tên", "Hộp thư",
			"Số điện thoại", "Số lần đăng nhập", "Địa chỉ",
			"Ghi chú", "Ngày tạo", "Sửa lần cuối"};
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("User");

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

		for(UserObject item: items) {
			Row row = sheet.createRow(i++);
			row.createCell(0).setCellValue(item.getUser_id());
			row.createCell(1).setCellValue(item.getUser_name());
			row.createCell(2).setCellValue(item.getUser_fullname());
			row.createCell(3).setCellValue(item.getUser_email());
			row.createCell(4).setCellValue(item.getUser_phone());
			row.createCell(5).setCellValue(item.getUser_logined());
			row.createCell(6).setCellValue(item.getUser_address());
			row.createCell(7).setCellValue(item.getUser_notes());
			row.createCell(8).setCellValue(item.getUser_created_at());
			row.createCell(9).setCellValue(item.getUser_modified_at());
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
