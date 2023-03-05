import java.io.File;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class RecordDetails {

	File file;
	NavigateRecords record;
	Employee currentEmployee;
	CheckInput check;
	RandomFile application = new RandomFile();
	JComboBox<String> genderCombo, departmentCombo, fullTimeCombo;
	JTextField idField, ppsField, surnameField, firstNameField, salaryField;
	DisplayRecords displayRecords;
	InputField inputField;
	boolean change = false;
	long currentByteStart = 0;
	private static final DecimalFormat fieldFormat = new DecimalFormat("0.00");
	private static EmployeeDetails frame = new EmployeeDetails();

	// get next free ID from Employees in the file
	public int getNextFreeId() {
		int nextFreeId = 0;
		if (file.length() == 0 || !check.isSomeoneToDisplay())
			nextFreeId++;
		else {
			record.lastRecord();
			nextFreeId = currentEmployee.getEmployeeId() + 1;
		}
		return nextFreeId;
	}

	// get values from text fields and create Employee object
	public Employee getChangedDetails() {
		boolean fullTime = false;
		Employee theEmployee;
		if (((String) fullTimeCombo.getSelectedItem()).equalsIgnoreCase("Yes"))
			fullTime = true;

		theEmployee = new Employee(Integer.parseInt(idField.getText()), ppsField.getText().toUpperCase(),
				surnameField.getText().toUpperCase(), firstNameField.getText().toUpperCase(),
				genderCombo.getSelectedItem().toString().charAt(0), departmentCombo.getSelectedItem().toString(),
				Double.parseDouble(salaryField.getText()), fullTime);

		return theEmployee;
	}

	// add Employee object to fail
	public void addRecord(Employee newEmployee) {

		application.openWriteFile(file.getAbsolutePath());
		currentByteStart = application.addRecords(newEmployee);
		application.closeWriteFile();// close file for writing
	}

	// delete (make inactive - empty) record from file
	public void deleteRecord() {
		if (check.isSomeoneToDisplay()) {

			int returnVal = JOptionPane.showOptionDialog(frame, "Do you want to delete record?", "Delete",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

			if (returnVal == JOptionPane.YES_OPTION) {

				application.openWriteFile(file.getAbsolutePath());

				application.deleteRecords(currentByteStart);
				application.closeWriteFile();

				if (check.isSomeoneToDisplay()) {
					record.nextRecord();
					displayRecords.displayRecords(currentEmployee);
				}
			}
		}
	}

	// create vector of vectors with all Employee details
	public Vector<Object> getAllEmloyees() {

		Vector<Object> allEmployee = new Vector<Object>();
		Vector<Object> empDetails;
		long byteStart = currentByteStart;
		int firstId;

		record.firstRecord();
		firstId = currentEmployee.getEmployeeId();

		do {
			empDetails = new Vector<Object>();
			empDetails.addElement(new Integer(currentEmployee.getEmployeeId()));
			empDetails.addElement(currentEmployee.getPps());
			empDetails.addElement(currentEmployee.getSurname());
			empDetails.addElement(currentEmployee.getFirstName());
			empDetails.addElement(new Character(currentEmployee.getGender()));
			empDetails.addElement(currentEmployee.getDepartment());
			empDetails.addElement(new Double(currentEmployee.getSalary()));
			empDetails.addElement(new Boolean(currentEmployee.getFullTime()));

			allEmployee.addElement(empDetails);
			record.nextRecord();
		} while (firstId != currentEmployee.getEmployeeId());
		currentByteStart = byteStart;

		return allEmployee;
	}

	// activate field for editing
	public void editDetails() {

		if (check.isSomeoneToDisplay()) {
			salaryField.setText(fieldFormat.format(currentEmployee.getSalary()));
			change = false;
			inputField.setEnabled(true);
		}
	}

	// ignore changes and set text field unenabled
	public void cancelChange() {
		inputField.setEnabled(false);
		displayRecords.displayRecords(currentEmployee);
	}

}

