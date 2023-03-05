
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import java.awt.Color;

public class SearchRecords {
	
	private final Color red = new Color(255,150,150);
	private JTextField idField, ppsField, surnameField, firstNameField, salaryField;
	JTextField searchBySurnameField, searchByIdField;
	CheckInput check = new CheckInput();
	
	DisplayRecords display;
	NavigateRecords navigate;
	Employee currentEmployee;
	
	// search Employee by ID
		public void searchEmployeeById() {
			boolean found = false;

			try {// try to read correct correct from input
					// if any active Employee record search for ID else do nothing
				if (check.isSomeoneToDisplay()) {
					navigate.firstRecord();// look for first record
					int firstId = currentEmployee.getEmployeeId();
					// if ID to search is already displayed do nothing else loop
					// through records
					if (searchByIdField.getText().trim().equals(idField.getText().trim()))
						found = true;
					else if (searchByIdField.getText().trim().equals(Integer.toString(currentEmployee.getEmployeeId()))) {
						found = true;
						display.displayRecords(currentEmployee);
					} // end else if
					else {
						navigate.nextRecord();// look for next record
						// loop until Employee found or until all Employees have
						// been checked
						while (firstId != currentEmployee.getEmployeeId()) {
							// if found break from loop and display Employee details
							// else look for next record
							if (Integer.parseInt(searchByIdField.getText().trim()) == currentEmployee.getEmployeeId()) {
								found = true;
								display.displayRecords(currentEmployee);
								break;
							} else
								navigate.nextRecord();// look for next record
						} 
					} 
						// if Employee not found display message
					if (!found)
						JOptionPane.showMessageDialog(null, "Employee not found!");
				} 
			} 
			catch (NumberFormatException e) {
				searchByIdField.setBackground(red);
				JOptionPane.showMessageDialog(null, "Wrong ID format!");
			} 
			searchByIdField.setBackground(Color.WHITE);
			searchByIdField.setText("");
		}

		// search Employee by surname
		public void searchEmployeeBySurname() {
			boolean found = false;
			// if any active Employee record search for ID else do nothing
			if (check.isSomeoneToDisplay()) {
				display.firstRecord();// look for first record
				String firstSurname = currentEmployee.getSurname().trim();
				// if ID to search is already displayed do nothing else loop through
				// records
				if (searchBySurnameField.getText().trim().equalsIgnoreCase(surnameField.getText().trim()))
					found = true;
				else if (searchBySurnameField.getText().trim().equalsIgnoreCase(currentEmployee.getSurname().trim())) {
					found = true;
					display.displayRecords(currentEmployee);
				} 
				else {
					navigate.nextRecord();// look for next record
					// loop until Employee found or until all Employees have been
					// checked
					while (!firstSurname.trim().equalsIgnoreCase(currentEmployee.getSurname().trim())) {
						// if found break from loop and display Employee details
						// else look for next record
						if (searchBySurnameField.getText().trim().equalsIgnoreCase(currentEmployee.getSurname().trim())) {
							found = true;
							display.displayRecords(currentEmployee);
							break;
						} 
						else
							navigate.nextRecord();// look for next record
					} 
				} 
					// if Employee not found display message
				if (!found)
					JOptionPane.showMessageDialog(null, "Employee not found!");
			} 
			searchBySurnameField.setText("");
		}
	
	

}
