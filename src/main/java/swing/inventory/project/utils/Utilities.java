package swing.inventory.project.utils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.htmlparser.jericho.CharacterReference;

public class Utilities {

	private Utilities() {}

	public static String encode(String str) {
		return CharacterReference.encode(str);
	}
	
	public static String decode(String str) {
		return CharacterReference.decode(str);
	}
	
	public static String doubleFormat(Double a) {
		return new DecimalFormat("#,###.##").format(a);
	}
	
	public static String doubleFormat(Double a, String format) {
		return new DecimalFormat(format).format(a);
	}
	
	public static String getDate(String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public static String getDate() {
		return Utilities.getDate("dd/MM/yyyy");
	}
	
	public static String getDateProfiles() {
		return Utilities.getDate("ddMMyyHHmmss");
	}

    public static boolean checkEmail(String email) {
		int index = email.lastIndexOf("@");
		if(index != -1 && index != email.length() - 1) {
			String s = email.substring(index + 1);
			if(s.lastIndexOf(".") != -1) {
				return true;
			}
		}
        return false;
    }
	
}
