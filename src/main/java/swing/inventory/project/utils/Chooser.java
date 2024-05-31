package swing.inventory.project.utils;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Chooser {

    private Chooser() {}
    
    public static JFileChooser chooserExport() {
		return chooserExport("");
	}

	public static JFileChooser chooserExport(String initialFileName) {
		FileNameExtensionFilter xlsx = new FileNameExtensionFilter("Microsoft Excel(.xlsx)", "xlsx");
        FileNameExtensionFilter xls = new FileNameExtensionFilter("Microsoft Excel(.xls)", "xls");
        JFileChooser chooser = new JFileChooser();
        chooser.addChoosableFileFilter(xlsx);
        chooser.addChoosableFileFilter(xls);
		chooser.setFileFilter(xlsx);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setSelectedFile(new File(initialFileName));
		return chooser;
	}

}
