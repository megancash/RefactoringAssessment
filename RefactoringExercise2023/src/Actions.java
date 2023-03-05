import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Random;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Actions {

	File file;
	boolean change = false;
	static EmployeeDetails frame = new EmployeeDetails();
	RandomFile application = new RandomFile();
	FileNameExtensionFilter datfilter = new FileNameExtensionFilter("dat files (*.dat)", "dat");
	EmployeeDetails parent = new EmployeeDetails();
	Employee currentEmployee;
	NavigateRecords navigate;
	DisplayRecords displayRecord;
	boolean changesMade = false;
	CheckInput check;
	String generatedFileName;
	InputField editField;
	JTextField idField;
	long currentByteStart = 0;

	// open file
	public void openFile() {
		final JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Open");

		fc.setFileFilter(datfilter);
		File newFile;
		if (file.length() != 0 || change) {
			int returnVal = JOptionPane.showOptionDialog(frame, "Do you want to save changes?", "Save",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
			if (returnVal == JOptionPane.YES_OPTION) {
				saveFile();
			}
		}

		int returnVal = fc.showOpenDialog(parent);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			newFile = fc.getSelectedFile();
			if (file.getName().equals(generatedFileName))
				file.delete();
			file = newFile;
			application.openReadFile(file.getAbsolutePath());
			navigate.firstRecord();
			displayRecord.displayRecords(currentEmployee);
			application.closeReadFile();
		}
	}

	// allow to save changes to file when exiting the application
	public void exitApp() {

		if (file.length() != 0) {
			if (changesMade) {
				int returnVal = JOptionPane.showOptionDialog(frame, "Do you want to save changes?", "Save",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

				if (returnVal == JOptionPane.YES_OPTION) {
					saveFile();

					if (file.getName().equals(generatedFileName))
						file.delete();
					System.exit(0);
				} 
				else if (returnVal == JOptionPane.NO_OPTION) {

					if (file.getName().equals(generatedFileName))
						file.delete();
					System.exit(0);
				} 
			} 
			else {
				// delete generated file if user chooses not to save file
				if (file.getName().equals(generatedFileName))
					file.delete();
				System.exit(0);
			} 
		} else {
			// delete generated file if user chooses not to save file
			if (file.getName().equals(generatedFileName))
				file.delete();
			System.exit(0);
		} }

	// save file
	public void saveFile() {

		if (file.getName().equals(generatedFileName))
			saveFileAs();
		else {

			if (change) {
				int returnVal = JOptionPane.showOptionDialog(frame, "Do you want to save changes?", "Save",
						JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

				if (returnVal == JOptionPane.YES_OPTION) {

					if (!idField.getText().equals("")) {

						application.openWriteFile(file.getAbsolutePath());
						currentEmployee = record.getChangedDetails();
						application.changeRecords(currentEmployee, currentByteStart);
						application.closeWriteFile();// close file for writing
					}
				} 
			} 

			displayRecord.displayRecords(currentEmployee);
			editField.setEnabled(false);
		}
	}

	// save changes to current Employee
	public void saveChanges() {
		int returnVal = JOptionPane.showOptionDialog(frame, "Do you want to save changes to current Employee?", "Save",
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

		if (returnVal == JOptionPane.YES_OPTION) {

			application.openWriteFile(file.getAbsolutePath());
			currentEmployee = record.getChangedDetails();
			application.changeRecords(currentEmployee, currentByteStart);
			application.closeWriteFile();
			changesMade = false;
		} 
		displayRecord.displayRecords(currentEmployee);
		editField.setEnabled(false);
	}

	// save file as 'save as'
	public void saveFileAs() {
		final JFileChooser fc = new JFileChooser();
		File newFile;
		String defaultFileName = "new_Employee.dat";
		fc.setDialogTitle("Save As");
		// display files only with .dat extension
		fc.setFileFilter(datfilter);
		fc.setApproveButtonText("Save");
		fc.setSelectedFile(new File(defaultFileName));

		int returnVal = fc.showSaveDialog(parent);
		// if file has chosen or written, save old file in new file
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			newFile = fc.getSelectedFile();

			if (!check.checkFileName(newFile)) {

				newFile = new File(newFile.getAbsolutePath() + ".dat");

				application.createFile(newFile.getAbsolutePath());
			} 
			else
				application.createFile(newFile.getAbsolutePath());
			try {
				Files.copy(file.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

				if (file.getName().equals(generatedFileName))
					file.delete();
				file = newFile;
			} 
			catch (IOException e) {
			} 
		} 
		changesMade = false;
	}

	// generate 20 character long file name
	private String getFileName() {
		String fileNameChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890_-";
		StringBuilder fileName = new StringBuilder();
		Random rnd = new Random();

		while (fileName.length() < 20) {
			int index = (int) (rnd.nextFloat() * fileNameChars.length());
			fileName.append(fileNameChars.charAt(index));
		}
		String generatedfileName = fileName.toString();
		return generatedfileName;
	}

	// create file with generated file name when application is opened
	public void createRandomFile() {

		generatedFileName = getFileName() + ".dat";
		file = new File(generatedFileName);
		application.createFile(file.getName());
	}
}