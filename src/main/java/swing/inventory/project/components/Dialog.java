package swing.inventory.project.components;

import java.awt.Component;

import javax.swing.JOptionPane;

public class Dialog {
	
	public static int success(Component component, String label, String title) {
		return JOptionPane.showConfirmDialog(component, label, title,
				JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static int success(Component component, String label) {
		return Dialog.success(component, label, "Success");
	}
	
	public static int error(Component component, String label, String title) {
		return JOptionPane.showConfirmDialog(component, label, title,
				JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
	}
	
	public static int error(Component component, String label) {
		return Dialog.error(component, label, "Error");
	}
	
	public static int warning(Component component, String label, String title) {
		return JOptionPane.showConfirmDialog(component, label, title,
				JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
	}
	
	public static int warning(Component component, String label) {
		return Dialog.warning(component, label, "Warning");
	}
	
	public static boolean confirm(Component component, String label, String title) {
		int check = JOptionPane.showConfirmDialog(component, label, title,
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		return check == JOptionPane.YES_OPTION;
	}
	
	public static boolean confirm(Component component, String label) {
		return Dialog.confirm(component, label, "Confirm");
	}
	
}
