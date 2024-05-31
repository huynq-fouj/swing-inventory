package swing.inventory.project.controllers;

import swing.inventory.project.ConnectionPool;
import swing.inventory.project.components.Dialog;
import swing.inventory.project.contexts.AuthContext;
import swing.inventory.project.contexts.ConnectionContext;
import swing.inventory.project.objects.UserObject;
import swing.inventory.project.views.Main;
import swing.inventory.project.views.auth.Login;
import swing.inventory.project.views.auth.Signup;

public class AuthController {
	
	public void login(Login view) {
		String username = view.getTfUsername().getText();
		char[] ps = view.getPfPassword().getPassword();
		String password = new String(ps);
		ConnectionPool cp = ConnectionContext.getCP();
		UserController uc = new UserController(cp);
		if(cp == null) {
			ConnectionContext.setCP(uc.getCP());
		}
		UserObject user = uc.getUserObject(username, password);
		uc.releaseConnection();
		if (user != null) {
			AuthContext.setUser(user);
			Main home = new Main();
			home.setVisible(true);
			view.dispose();
		} else {
			Dialog.error(view, "Sai tên đăng nhập hoặc mật khẩu!");
		}
	}
	
	public void signup(Signup view) {
		String userName = view.getTfUsername().getText().trim();
		String pass1 = new String(view.getPwdPassword().getPassword()).trim();
		String pass2 = new String(view.getPwdConfirm().getPassword()).trim();
		if (userName != null && !userName.equalsIgnoreCase("")) {
			if (pass1 != null && !pass1.equalsIgnoreCase("") && pass1 != null && !pass2.equalsIgnoreCase("")
					&& pass1.equals(pass2)) {
				ConnectionPool cp = ConnectionContext.getCP();
				UserController uc = new UserController(cp);
				if(cp == null) {
					ConnectionContext.setCP(uc.getCP());
				}
				UserObject user = new UserObject();
				user.setUser_name(userName);
				user.setUser_password(pass1);
				boolean isExists = uc.existsByName(userName);
				if (!isExists) {
					boolean check = uc.addUser(user);
					if(check) {
						user = uc.getUserObject(userName, pass2);
						if(user != null) {
							AuthContext.setUser(user);
							Main home = new Main();
							home.setVisible(true);
							view.dispose();
							Dialog.success(home, "Đăng ký thành công!");
						} else {
							Login lview = new Login();
							lview.setVisible(true);
							lview.dispose();
							Dialog.success(lview, "Đăng ký thành công!");
						}
					} else {
						Dialog.error(view, "Đăng ký không thành công!");
					}
				} else {
					Dialog.error(view, "Tên đăng nhập đã tồn tại!");
				}
				uc.releaseConnection();
			} else {
				Dialog.error(view, "Mật khẩu không hợp lệ!");
			}
		} else {
			Dialog.error(view, "Tên đăng nhập không hợp lệ!");
		}
	}
	
}
