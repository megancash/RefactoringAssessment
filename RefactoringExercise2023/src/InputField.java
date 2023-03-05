import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Color;

public class InputField {
	
	JComboBox<String> genderCombo, departmentCombo, FullTimeCombo;
	JComboBox[] comboBoxes = { genderCombo, departmentCombo, FullTimeCombo };
	JTextField idField, ppsField, surnameField, firstNameField, salaryField, searchByIdField, searchBySurnameField;
	JTextField[] textFields = { idField, ppsField, surnameField, firstNameField, salaryField };
	JButton searchId, searchSurname, saveChange, cancelChange;

	// set text field background colour to white
	private void setToWhite() {
		for (int i=0; i<= textFields.length; i++) {
			textFields[i].setBackground(UIManager.getColor("TextField.background"));
			}
		for (int i=0; i<= textFields.length; i++) {
			comboBoxes[i].setBackground(UIManager.getColor("TextField.background"));
			}
	}

	// enable text fields for editing
	public void setEnabled(boolean booleanValue) {
		boolean search;
		if (booleanValue)
			search = false;
		else
			search = true;
	
		setEnabledTextFields(booleanValue, search);
		setEnabledButtons(booleanValue, search);
		setEnabledComboBoxes(booleanValue);
		
	}
	
	public void setEnabledTextFields(boolean booleanValue, boolean search) {
		JTextField[] textFieldAll = { idField, ppsField, surnameField, firstNameField, salaryField, searchByIdField, searchBySurnameField };

		for (int i = 0; i <= textFields.length; i++) {
			if (textFieldAll[i] == searchByIdField || textFieldAll[i] == searchBySurnameField) {
				textFieldAll[i].setEnabled(search);
			} else {
				textFieldAll[i].setEditable(booleanValue);
			}
		}
	}
	
	public void setEnabledButtons(boolean booleanValue, boolean search) {
		JButton[] btn = { saveChange, cancelChange, searchId, searchSurname };
		for (int i = 0; i <= btn.length; i++) {

			if (btn[i] == saveChange || btn[i] == cancelChange) {
				btn[i].setVisible(booleanValue);
			} else {
				btn[i].setEnabled(search);
			}
		}
	}

	public void setEnabledComboBoxes(boolean booleanValue) {
		for (int i = 0; i <= comboBoxes.length; i++) {
			comboBoxes[i].setEnabled(booleanValue);
		}
	}
}
