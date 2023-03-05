import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Color;
import java.io.File;

public class CheckInput {
	
	private boolean change = false;
	private JComboBox<String> genderCombo, departmentCombo, fullTimeCombo;
	private JTextField idField, ppsField, surnameField, firstNameField, salaryField;
	long currentByteStart = 0;
	Color red = new Color(255,150,150);
	Employee currentEmployee;
	DisplayRecords display;
	Actions save;
	RandomFile application = new RandomFile();
	File file;
	InputField input;
	
	
	// check if any of records in file is active - ID is not 0
		public boolean isSomeoneToDisplay() {
			boolean someoneToDisplay = false;
			// open file for reading
			application.openReadFile(file.getAbsolutePath());
			// check if any of records in file is active - ID is not 0
			someoneToDisplay = application.isSomeoneToDisplay();
			application.closeReadFile();// close file for reading
			// if no records found clear all text fields and display message
			if (!someoneToDisplay) {
				currentEmployee = null;
				idField.setText("");
				ppsField.setText("");
				surnameField.setText("");
				firstNameField.setText("");
				salaryField.setText("");
				genderCombo.setSelectedIndex(0);
				departmentCombo.setSelectedIndex(0);
				fullTimeCombo.setSelectedIndex(0);
				JOptionPane.showMessageDialog(null, "No Employees registered!");
			}
			return someoneToDisplay;
		}
		
		// check if any changes text field where made
		private boolean checkForChanges() {
			boolean anyChanges = false;
			// if changes where made, allow user to save there changes
			if (change) {
				save.saveChanges();// save changes
				anyChanges = true;
			} 
				// if no changes made, set text fields as unenabled and display
				// current Employee
			else {
				input.setEnabled(false);
				display.displayRecords(currentEmployee);
			} 

			return anyChanges;
		}
		
		// check for correct PPS format and look if PPS already in use
		public boolean correctPps(String pps, long currentByte) {
			boolean ppsExist = false;
			// check for correct PPS format based on assignment description
			if (pps.length() == 8 || pps.length() == 9) {
				if (Character.isDigit(pps.charAt(0)) && Character.isDigit(pps.charAt(1))
						&& Character.isDigit(pps.charAt(2))	&& Character.isDigit(pps.charAt(3)) 
						&& Character.isDigit(pps.charAt(4))	&& Character.isDigit(pps.charAt(5)) 
						&& Character.isDigit(pps.charAt(6))	&& Character.isLetter(pps.charAt(7))
						&& (pps.length() == 8 || Character.isLetter(pps.charAt(8)))) {
					// open file for reading
					application.openReadFile(file.getAbsolutePath());
					// look in file is PPS already in use
					ppsExist = application.isPpsExist(pps, currentByte);
					application.closeReadFile();// close file for reading
				} 
				else
					ppsExist = true;
			} 
			else
				ppsExist = true;

			return ppsExist;
		}
		
		// check for input in text fields
		private boolean checkInput() {
			boolean valid = true;
			// if any of inputs are in wrong format, colour text field and display
			// message
			if (ppsField.isEditable() && ppsField.getText().trim().isEmpty()) {
				ppsField.setBackground(red);
				valid = false;
			} 
			if (ppsField.isEditable() && correctPps(ppsField.getText().trim(), currentByteStart)) {
				ppsField.setBackground(red);
				valid = false;
			} 
			if (surnameField.isEditable() && surnameField.getText().trim().isEmpty()) {
				surnameField.setBackground(red);
				valid = false;
			} 
			if (firstNameField.isEditable() && firstNameField.getText().trim().isEmpty()) {
				firstNameField.setBackground(red);
				valid = false;
			} 
			if (genderCombo.getSelectedIndex() == 0 && genderCombo.isEnabled()) {
				genderCombo.setBackground(red);
				valid = false;
			} 
			if (departmentCombo.getSelectedIndex() == 0 && departmentCombo.isEnabled()) {
				departmentCombo.setBackground(red);
				valid = false;
			} 
			try {// try to get values from text field
				Double.parseDouble(salaryField.getText());
				// check if salary is greater than 0
				if (Double.parseDouble(salaryField.getText()) < 0) {
					salaryField.setBackground(red);
					valid = false;
				} 
			} 
			catch (NumberFormatException num) {
				if (salaryField.isEditable()) {
					salaryField.setBackground(red);
					valid = false;
				} 
			} 
			if (fullTimeCombo.getSelectedIndex() == 0 && fullTimeCombo.isEnabled()) {
				fullTimeCombo.setBackground(red);
				valid = false;
			} 
				// display message if any input or format is wrong
			if (!valid)
				JOptionPane.showMessageDialog(null, "Wrong values or format! Please check!");
			// set text field to white colour if text fields are editable
			if (ppsField.isEditable())
				input.setToWhite();

			return valid;
		}	
}
