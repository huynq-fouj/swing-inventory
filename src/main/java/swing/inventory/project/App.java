package swing.inventory.project;

import java.util.logging.*;

import javax.swing.*;

import java.awt.EventQueue;

import swing.inventory.project.controllers.UserController;
import swing.inventory.project.objects.UserObject;
import swing.inventory.project.views.Main;
import swing.inventory.project.views.auth.AuthPage;

public class App {

	private static final Logger logger = Logger.getLogger(App.class.getName());
    public static void main( String[] args ) {
    	createAdminAccount();
		initLaF();

		EventQueue.invokeLater(() -> {
			AuthPage frame = new AuthPage();
			//Main frame = new Main();
			frame.setVisible(true);
		});
    }

	private static void createAdminAccount() {
		UserObject admin = new UserObject();
		admin.setUser_email("admin@test.com");
		admin.setUser_password("admin");
		admin.setUser_name("admin");
		admin.setUser_fullname("Admin");
		admin.setUser_role(5);
		UserController uc = new UserController(null);
		if(uc.addUser(admin)) logger.info("Create admin account:\nUsername: admin\nPassword: admin");
		else logger.info("Admin account has been already.");
		uc.releaseConnection();
	}

	private static void initLaF() {
		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
			logger.log(Level.SEVERE, null, ex);
		}
	
	}

}
