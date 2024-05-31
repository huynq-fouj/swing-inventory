package swing.inventory.project.utils;

import java.awt.Component;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import swing.inventory.project.ConnectionPool;
import swing.inventory.project.contexts.ConnectionContext;
import swing.inventory.project.controllers.CategoryController;
import swing.inventory.project.controllers.ProductController;
import swing.inventory.project.controllers.UserController;

public class ExportFileExcel {

    private ExportFileExcel() {}

    public static void exportCategory(JFileChooser chooser, Component component) {
        int returnVal = chooser.showSaveDialog(component);
        if ( returnVal == JFileChooser.APPROVE_OPTION) {
            File fileToSave = chooser.getSelectedFile();
            FileNameExtensionFilter filter = (FileNameExtensionFilter) chooser.getFileFilter();
            String ext = filter.getExtensions()[0];
            ConnectionPool cp = ConnectionContext.getCP();
            CategoryController cc = new CategoryController(cp);
            if(cp == null) ConnectionContext.setCP(cc.getCP());
            cc.export(fileToSave, ext);
            cc.releaseConnection();
        }
    }

    public static void exportProduct(JFileChooser chooser, Component component) {
        int returnVal = chooser.showSaveDialog(component);
        if ( returnVal == JFileChooser.APPROVE_OPTION) {
            File fileToSave = chooser.getSelectedFile();
            FileNameExtensionFilter filter = (FileNameExtensionFilter) chooser.getFileFilter();
            String ext = filter.getExtensions()[0];
            ConnectionPool cp = ConnectionContext.getCP();
            ProductController pc = new ProductController(cp);
            if(cp == null) ConnectionContext.setCP(pc.getCP());
            pc.export(fileToSave, ext);
            pc.releaseConnection();
        }
    }

    public static void exportUser(JFileChooser chooser, Component component) {
        int returnVal = chooser.showSaveDialog(component);
        if ( returnVal == JFileChooser.APPROVE_OPTION) {
            File fileToSave = chooser.getSelectedFile();
            FileNameExtensionFilter filter = (FileNameExtensionFilter) chooser.getFileFilter();
            String ext = filter.getExtensions()[0];
            ConnectionPool cp = ConnectionContext.getCP();
            UserController uc = new UserController(cp);
            if(cp == null) ConnectionContext.setCP(uc.getCP());
            uc.export(fileToSave, ext);
            uc.releaseConnection();
        }
    }

}
