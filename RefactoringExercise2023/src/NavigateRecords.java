import java.io.File;


public class NavigateRecords {
	
	private RandomFile application = new RandomFile();
	private long currentByteStart = 0;
	private File file;
	CheckInput check = new CheckInput();
	Employee currentEmployee;
	
	// find byte start in file for first active record
		public void firstRecord() {
			// if any active record in file look for first record
			if (check.isSomeoneToDisplay()) {
				// open file for reading
				application.openReadFile(file.getAbsolutePath());
				// get byte start in file for first record
				currentByteStart = application.getFirst();
				// assign current Employee to first record in file
				currentEmployee = application.readRecords(currentByteStart);
				application.closeReadFile();// close file for reading
				// if first record is inactive look for next record
				if (currentEmployee.getEmployeeId() == 0)
					nextRecord();// look for next record
			} 
		}

		// find byte start in file for previous active record
		public void previousRecord() {
			// if any active record in file look for first record
			if (check.isSomeoneToDisplay()) {
				// open file for reading
				application.openReadFile(file.getAbsolutePath());
				// get byte start in file for previous record
				currentByteStart = application.getPrevious(currentByteStart);
				// assign current Employee to previous record in file
				currentEmployee = application.readRecords(currentByteStart);
				// loop to previous record until Employee is active - ID is not 0
				while (currentEmployee.getEmployeeId() == 0) {
					// get byte start in file for previous record
					currentByteStart = application.getPrevious(currentByteStart);
					// assign current Employee to previous record in file
					currentEmployee = application.readRecords(currentByteStart);
				} 
				application.closeReadFile();// close file for reading
			}
		}

		// find byte start in file for next active record
		public void nextRecord() {
			// if any active record in file look for first record
			if (check.isSomeoneToDisplay()) {
				// open file for reading
				application.openReadFile(file.getAbsolutePath());
				// get byte start in file for next record
				currentByteStart = application.getNext(currentByteStart);
				// assign current Employee to record in file
				currentEmployee = application.readRecords(currentByteStart);
				// loop to previous next until Employee is active - ID is not 0
				while (currentEmployee.getEmployeeId() == 0) {
					// get byte start in file for next record
					currentByteStart = application.getNext(currentByteStart);
					// assign current Employee to next record in file
					currentEmployee = application.readRecords(currentByteStart);
				} // end while
				application.closeReadFile();// close file for reading
			} 
		}

		// find byte start in file for last active record
		public void lastRecord() {
			// if any active record in file look for first record
			if (check.isSomeoneToDisplay()) {
				// open file for reading
				application.openReadFile(file.getAbsolutePath());
				// get byte start in file for last record
				currentByteStart = application.getLast();
				// assign current Employee to first record in file
				currentEmployee = application.readRecords(currentByteStart);
				application.closeReadFile();// close file for reading
				// if last record is inactive look for previous record
				if (currentEmployee.getEmployeeId() == 0)
					previousRecord();// look for previous record
			} 
		}

}
