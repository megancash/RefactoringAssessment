import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class SearchPanel extends JDialog implements ActionListener {
	EmployeeDetails parent;
	JButton search, cancel;
	Font font1 = new Font("SansSerif", Font.BOLD, 16);
	JTextField searchByIdField;
	JTextField searchField;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
	public void search() {
		setModal(true);
		this.parent = parent;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		getRootPane().setDefaultButton(search);

		setSize(500, 190);
		setLocation(350, 250);
		setVisible(true);
	}

	public void setPanel(JLabel searchLabel) {
		JPanel searchPanel = new JPanel(new GridLayout(3, 1));
		JPanel textPanel = new JPanel();
		JPanel buttonPanel = new JPanel();

		textPanel.add(searchField = new JTextField(20));

		textPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		searchLabel.setFont(font1);
		searchField.setFont(font1);
		searchField.setDocument(new JTextFieldLimit(20));

		buttonPanel.add(cancel = new JButton("Cancel"));
		buttonPanel.add(search = new JButton("Search"));
		search.addActionListener(this);
		search.requestFocus();

		cancel.addActionListener(this);

		searchPanel.add(textPanel);
	}

	

}
