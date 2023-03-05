/* Student Name: Megan Cash
 * Student Number: C19317723
 * 
 * This is a dialog for searching Employees by their surname.
 * 
 * */

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class SearchBySurnameDialog extends JDialog implements ActionListener{
	EmployeeDetails parent;
	JButton search, cancel;
	JTextField searchBySurnameField, searchField;
	SearchRecords searchRecords;
	SearchPanel searchPanel;

	// constructor for search by surname dialog
	public SearchBySurnameDialog(EmployeeDetails employeeDetails) {
		setTitle("Search by Surname");
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JScrollPane scrollPane = new JScrollPane(searchPane());
		setContentPane(scrollPane);
		searchPanel.search();

	}
	
	// initialize search container
	public Container searchPane() {
		JPanel searchPanel = new JPanel(new GridLayout(3,1));
		JPanel textPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JLabel searchLabel;

		searchPanel.add(new JLabel("Search by Surname"));
		textPanel.add(searchLabel = new JLabel("Input Surname:"));
	
		
		searchPanel.add(textPanel);
		searchPanel.add(buttonPanel);

		return searchPanel;
	}

	// action listener for save and cancel button
	public void actionPerformed(ActionEvent e) {
		// if option search, search for Employee
		if(e.getSource() == search){
			searchBySurnameField.setText(searchField.getText());
			// search Employee by surname
			searchRecords.searchEmployeeBySurname();
			dispose();// dispose dialog
		}
		else if(e.getSource() == cancel)
			dispose();// dispose dialog
	}
}// end class SearchBySurnameDialog
