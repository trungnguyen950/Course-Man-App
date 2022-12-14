package a2_1901040232.screen;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import a2_1901040232.EnrolmentManager;
import a2_1901040232.ModuleType;
import a2_1901040232.model.Enrolment;
import a2_1901040232.model.Module;
import a2_1901040232.model.Student;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JComboBox;

public class NewEnrolment extends JPanel {
	private JTextField txtInternalM, txtExamM;
	
	EnrolmentManager manager = new EnrolmentManager();

	
	public NewEnrolment() {
		setSize(600, 400);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Student");
		lblNewLabel.setBounds(50, 60, 130, 16);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Module");
		lblNewLabel_1.setBounds(50, 100, 150, 16);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Internal mark");
		lblNewLabel_2.setBounds(50, 140, 150, 16);
		add(lblNewLabel_2);
		
		txtInternalM = new JTextField();
		txtInternalM.setBounds(200, 135, 140, 26);
		add(txtInternalM);
		
		JLabel lblNewLabel_3 = new JLabel("Exam mark");
		lblNewLabel_3.setBounds(50, 180, 150, 16);
		add(lblNewLabel_3);
		
		txtExamM = new JTextField();
		txtExamM.setBounds(200, 175, 140, 26);
		add(txtExamM);
		
		Vector modelStudent = new Vector();
		try {
			List<Student> students = manager.getAllStudent();
			for(Student s : students) {
				modelStudent.add(s);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JComboBox cbbStudent = new JComboBox(modelStudent);
		cbbStudent.setBounds(200, 55, 350, 27);
		cbbStudent.setSelectedIndex(0);
		add(cbbStudent);
		
		Vector modelModule = new Vector();
		try {
			List<Module> modules = manager.getAllModule();
			for( Module module : modules) {
				modelModule.add(module);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JComboBox cbbModule = new JComboBox(modelModule);
		cbbModule.setBounds(200, 95, 350, 27);
		cbbStudent.setSelectedIndex(0);
		add(cbbModule);
		
		JLabel lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setBounds(0, 220, 600, 16);
		add(lblError);
		
		JLabel lblSucces = new JLabel("");
		lblSucces.setForeground(Color.GREEN);
		lblSucces.setHorizontalAlignment(SwingConstants.CENTER);
		lblSucces.setBounds(0, 220, 600, 16);
		add(lblSucces);
		
		JButton btnAdd = new JButton("Add Enrolment");
		btnAdd.setBounds(100, 260, 130, 35);
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lblError.setText("");
				lblSucces.setText("");
				Student student = (Student) cbbStudent.getSelectedItem();
				Module module = (Module) cbbModule.getSelectedItem();
				double internalM, examM;
				try {
					internalM = Double.valueOf(txtInternalM.getText());
				} catch (Exception e2) {
					lblError.setText("Invalid Internal Mark");
					return;
				}
				try {
					examM = Double.valueOf(txtExamM.getText());
				} catch (Exception e2) {
					lblError.setText("Invalid Exam Mark");
					return;
				}
				
				try {
					Enrolment enrolment = new Enrolment(student, module,internalM, examM);
					manager.addEnrolment(enrolment);
					lblSucces.setText("New Enrolment added");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					lblError.setText(e1.getMessage());
				}
				
			}
		});
		add(btnAdd);
		
		JButton btnSetMark = new JButton("Set Mark");
		btnSetMark.setBounds(240, 260, 130, 35);
		btnSetMark.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lblError.setText("");
				lblSucces.setText("");
				double internalM = 0;
				double examM = 0;
				try {
					internalM = Double.valueOf(txtInternalM.getText());
				} catch (Exception e2) {
					lblError.setText("Invalid Internal Mark");
					return;
				}
				try {
					examM = Double.valueOf(txtExamM.getText());
				} catch (Exception e2) {
					lblError.setText("Invalid Exam Mark");
					return;
				}
				Student student = (Student) cbbStudent.getSelectedItem();
				Module module = (Module) cbbModule.getSelectedItem();
				try {
					Enrolment enrolment = manager.getEnrolment(student, module);
					if(enrolment == null) {
						lblError.setText("Please add enrolment first");
						return;
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					manager.setMarks(student, module, internalM, examM);
					lblSucces.setText("Set Marks Success");
				} catch (Exception e1) {
					lblError.setText(e1.getMessage());
				}
				
			}
		});
		add(btnSetMark);
		
		
	}
}
