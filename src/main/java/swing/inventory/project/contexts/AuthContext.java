package swing.inventory.project.contexts;

import java.util.logging.*;
import swing.inventory.project.objects.UserObject;

public class AuthContext {

	private static UserObject user;

	private AuthContext() {}
	
	public static void setUser(UserObject user) {
		Logger.getLogger(AuthContext.class.getName()).log(Level.INFO, () -> "User " + user.getUser_name() + " login.");
		AuthContext.user = user;
	}
	
	public static UserObject getUser() {
		return AuthContext.user;
	}

    public static void logOut() {
        Logger.getLogger(AuthContext.class.getName()).log(Level.INFO, () -> "User logout.");
		AuthContext.user = null;
    }

	public static boolean hasRoleManager() {
		return AuthContext.user.getUser_role() >= 3;
	}

	public static boolean hasRoleAdmin() {
		return AuthContext.user.getUser_role() >= 5;
	}

	public static boolean isCurrentUser(int id) {
		return AuthContext.user.getUser_id() == id;
	}

	public static boolean isCurrentUser(UserObject o) {
		return AuthContext.user.getUser_id() == o.getUser_id();
	}
	
}
