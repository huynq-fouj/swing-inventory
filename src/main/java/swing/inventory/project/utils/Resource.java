package swing.inventory.project.utils;

import java.io.File;
import java.io.IOException;

public class Resource {
	
	private Resource() {}

	public static String loadPathResource(String path) {
		File directory = new File(".");
		String finalPath = "";
		try {
			finalPath = directory.getCanonicalPath() + "\\src\\main\\resources\\" + path;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return finalPath;
	}
	
	public static String loadStaticImagePath(String path) {
		return loadPathResource("images\\static\\" + path);
	}
	
	public static String loadUploadImagePath(String path) {
		return loadPathResource("images\\upload\\" + path);
	}
	
}
