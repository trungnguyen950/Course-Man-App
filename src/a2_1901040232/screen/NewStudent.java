package a2_1901040232.screen;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import a2_1901040232.EnrolmentManager;
import a2_1901040232.model.Student;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewStudent extends JPanel {
	private JTextField txtName, txtDOB, txtAddress, txtEmail;
	private static final String PATTERN_DATE = "dd/MM/yyyy";
	private static int year;
	EnrolmentManager manager = new EnrolmentManager();
	
	
	public NewStudent() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Student Name:");
		lblNewLabel.setBounds(50, 60, 130, 16);
		add(lblNewLabel);
		
		txtName = new JTextField();
		txtName.setBounds(200, 55, 350, 26);
		add(txtName);
		
		JLabel lblNewLabel_1 = new JLabel("Birthday (dd/mm/yyyy):");
		lblNewLabel_1.setBounds(50, 100, 150, 16);
		add(lblNewLabel_1);
		
		txtDOB = new JTextField();
		txtDOB.setBounds(200, 95, 350, 26);
		add(txtDOB);
		
		JLabel lblNewLabel_2 = new JLabel("Address:");
		lblNewLabel_2.setBounds(50, 140, 150, 16);
		add(lblNewLabel_2);
		
		txtAddress = new JTextField();
		txtAddress.setBounds(200, 135, 350, 26);
		add(txtAddress);
		
		JLabel lblNewLabel_3 = new JLabel("Email:");
		lblNewLabel_3.setBounds(50, 180, 150, 16);
		add(lblNewLabel_3);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(200, 175, 350, 26);
		add(txtEmail);
		
		JLabel lblError = new JLabel("");
		lblError.setForeground(Color.DARK_GRAY);
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setBounds(0, 220, 600, 16);
		add(lblError);
		
		JLabel lblSucces = new JLabel("");
		lblSucces.setForeground(Color.GREEN);
		lblSucces.setHorizontalAlignment(SwingConstants.CENTER);
		lblSucces.setBounds(0, 220, 600, 16);
		add(lblSucces);
		
		JButton btnAdd = new JButton("Add Student");
		btnAdd.setBounds(240, 260, 130, 35);
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lblError.setText("");
				lblSucces.setText("");
				
				try {
					LocalDate dob = getLocalDateForString(txtDOB.getText());
					Student std = new Student(txtName.getText(), dob, txtAddress.getText(), txtEmail.getText());
					year = checkSID();
					std.setSID(String.format("S%d", year++));
					manager.addStudent(std);
					txtName.setText(""); txtDOB.setText(""); txtAddress.setText(""); txtEmail.setText("");
					lblSucces.setText("New Student added");
				} catch (Exception e2) {
					e2.printStackTrace();
					lblError.setText(e2.getMessage());
				}
			}
		});
		add(btnAdd);
		   
	}
	
	private static LocalDate getLocalDateForString(final String string) {
        return LocalDate.parse(string,
                DateTimeFormatter.ofPattern(PATTERN_DATE));
    }
	
	private int checkSID() throws Exception {
		int maxYear = LocalDate.now().getYear();
		;
		if (manager.getAllStudent() != null) {
			for (Student s : manager.getAllStudent()) {
				int year = Integer.parseInt(s.getSID().substring(1));
				if (year > maxYear) {
					maxYear = year;
				}
			}
			return maxYear+1 ;
		}
		
		return maxYear;
	}
}
