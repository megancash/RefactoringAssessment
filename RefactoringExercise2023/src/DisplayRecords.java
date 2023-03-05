import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.text.DecimalFormat;

import javax.swing.JButton;

public class DisplayRecords {
	
	// decimal format for inactive currency text field
	private static final DecimalFormat format = new DecimalFormat("\u20ac ###,###,##0.00");
	private boolean change = false;
	private JButton first, previous, next, last, add, edit, deleteButton, displayAll, searchId, searchSurname, saveChange, cancelChange;
    private JComboBox<String> genderCombo, departmentCombo, fullTimeCombo;
    private JTextField idField, ppsField, surnameField, firstNameField, salaryField;
	JTextField searchByIdField, searchBySurnameField;
	// gender combo box values
	String[] gender = { "", "M", "F" };
	// department combo box values
	String[] department = { "", "Administration", "Production", "Transport", "Management" };		// full time combo box values
	// fulltime combo box values
	String[] fullTime = { "", "Yes", "No" };
	
	// display current Employee details
		public void displayRecords(Employee thisEmployee) {
			int countGender = 0;
			int countDep = 0;
			boolean found = false;

			searchByIdField.setText("");
			searchBySurnameField.setText("");
			// if Employee is null or ID is 0 do nothing else display Employee
			// details
			if (thisEmployee == null || thisEmployee.getEmployeeId() == 0) {
			} else {
				// find corresponding gender combo box value to current employee
				findGender(countGender, found, thisEmployee);
				found = false;
				findDepartment(countDep, found, thisEmployee);
				setFields(thisEmployee, countDep, countGender);
				// set corresponding full time combo box value to current employee	
				if (thisEmployee.getFullTime()== true) 
						fullTimeCombo.setSelectedIndex(1);
					else
						fullTimeCombo.setSelectedIndex(2);
			}
			change=false;
		}
		
		public void setFields(Employee thisEmployee, int countGender, int countDep) {
			
			
				idField.setText(Integer.toString(thisEmployee.getEmployeeId()));
				ppsField.setText(thisEmployee.getPps().trim());
				surnameField.setText(thisEmployee.getSurname().trim());
				firstNameField.setText(thisEmployee.getFirstName());
				salaryField.setText(format.format(thisEmployee.getSalary()));
		}
		
		// find corresponding gender combo box value to current employee
		public void findGender(int countGender, boolean found, Employee thisEmployee) {
			while (!found && countGender < gender.length - 1) {
				if (Character.toString(thisEmployee.getGender()).equalsIgnoreCase(gender[countGender]))
					found = true;
				else
					countGender++;
			}
		}

		// find corresponding department combo box value to current employee
		public void findDepartment(int countDep, boolean found, Employee thisEmployee) {
			while (!found && countDep < department.length - 1) {
				if (thisEmployee.getDepartment().trim().equalsIgnoreCase(department[countDep]))
					found = true;
				else
					countDep++;
			}
		}
	}
				
	
	
	
