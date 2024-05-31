package swing.inventory.project.configuration;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import swing.inventory.project.ConnectionPool;
import swing.inventory.project.ConnectionPoolImpl;
import swing.inventory.project.contexts.ConnectionContext;
import swing.inventory.project.controllers.UserController;
import swing.inventory.project.objects.UserObject;

public class CreateData {

    private ConnectionPool cp;
    private Random random = new Random();

	public CreateData() {
		this.cp = ConnectionContext.getCP();
        if(this.cp == null) {
            this.cp = new ConnectionPoolImpl();
            ConnectionContext.setCP(this.cp);
        }
	}

    private String randomLastName() {
		String[] lastNames = {
				"Hoài Thanh", "Thanh Hoài", "Thị Thanh Hoài", "Quang Huy", "Khánh Linh", "Thùy Dung", "Thùy Dương", "Thị Ly",
				"Anh", "Minh Anh", "Kim Anh", "Phương Anh", "Vân Anh", "Tuấn Anh", "Văn Huy", "Việt Huy", "Quốc Huy",
				"Quang Phúc", "Thái Phúc", "Hữu Phúc", "Đức Dũng", "Văn Dũng", "Việt Hùng", "Văn Hùng",
				"Thái Hưng", "Ngọc Hưng", "Mai Hương", "Thu Hương", "Gia Ân", "Bảo Ân", "Minh Hiếu", "Trung Hiếu",
				"Ngọc Quang", "Văn Quang", "Văn Đại", "Quang Đạo", "Minh Đăng", "Việt Hoàng", "Văn Hoàng",
				"Quang Minh", "Thành Thuận", "Đức Thông", "Thị Thu Hường", "Thanh Quỳnh", "Như Quỳnh", "Ngọc Thúy"
		};
		return lastNames[random.nextInt(lastNames.length)];
	}
	
	private String randomFirstName() {
		String[] firstNames = {
				"Nguyễn", "Trần", "Hoàng", "Lương", "Vũ", "Cao", "Khổng", "Lại", "Mai", "Ma", "Triệu",
				"Đỗ", "Võ", "Phạm", "Trịnh", "Trương", "Đại"
		};
		return firstNames[random.nextInt(firstNames.length)];
	}
	
	private String randomAddress() {
		String[] address = {
				"Vĩnh Phúc", "Hà Nội", "Tp. Hồ Chí Minh", "Thái Nguyên", "Phú Thọ", "Nghệ An", "Hải Phòng",
				"Quảng Ninh", "Hà Nam", "Bình Thuận", "Bình Dương", "Bắc Ninh", "Ninh Thuận", "Lạng Sơn"
		};
		return address[random.nextInt(address.length)];
	}
	
	private String randomUsername() {
		String[] username = {
				"sa", "ku", "ra", "ma", "ka", "ta", "mu", "yu", "an", "to", "ni", "ri", "rj",
				"yo", "ny", "li", "hi", "hu", "ko", "ne", "no", "le", "la", "ek", "ya", "to"
		};
		return username[random.nextInt(username.length)] + username[random.nextInt(username.length)] + username[random.nextInt(username.length)] + random.nextInt(1000);
	}
	
	private String randomPhoneNumber() {
		return "0" + (random.nextInt(9) + 1) + random.nextInt(10) + random.nextInt(10) + random.nextInt(10) + random.nextInt(10) + random.nextInt(10) + random.nextInt(10) + random.nextInt(10) + random.nextInt(10);
	}
	
	private String randomEmail() {
		return this.randomUsername() + "@gmail.com";
	}
	
	private String randomFullname() {
		return this.randomFirstName() + " " + this.randomLastName();
	}
	
	private UserObject createUser() {
		String fullname = this.randomFullname();
		String address = this.randomAddress();
		String username = this.randomUsername();
		String email = this.randomEmail();
		String phone = this.randomPhoneNumber();
		String password = "123456";
		UserObject item = new UserObject();
		item.setUser_address(address);
		item.setUser_email(email);
		item.setUser_password(password);
		item.setUser_logined(random.nextInt(100));
		item.setUser_role(0);
		item.setUser_fullname(fullname);
		item.setUser_phone(phone);
		item.setUser_name(username);
		return item;
	}
	
	public void createUsers(int n) {
		UserController uc = new UserController(this.cp);
		for(int i = 0; i < n; i++) {
			UserObject item = this.createUser();
			String result = uc.addUser(item) ? "Success" : "Error";
			Logger.getLogger(CreateData.class.getName()).log(Level.INFO, () -> result + " - User: " + item.getUser_name() + " - " + item.getUser_fullname());
		}
		uc.releaseConnection();
	}
	
}
