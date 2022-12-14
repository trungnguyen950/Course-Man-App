package a2_1901040232.screen;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import a2_1901040232.EnrolmentManager;
import a2_1901040232.ModuleType;
import a2_1901040232.model.Emodule;
import a2_1901040232.model.Module;
import a2_1901040232.model.Student;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JComboBox;

public class NewModule extends JPanel {
	private JTextField txtName, txtSmester, txtCredit, txtDepartment;
	
	EnrolmentManager manager = new EnrolmentManager();

	
	public NewModule() {
		setSize(600, 400);
		setLayout(null);
		
		JLabel nameLbl = new JLabel("Name");
		nameLbl.setBounds(50, 60, 130, 16);
		add(nameLbl);
		
		txtName = new JTextField();
		txtName.setBounds(200, 55, 350, 26);
		add(txtName);
		
		JLabel semLbl = new JLabel("Semester");
		semLbl.setBounds(50, 100, 150, 16);
		add(semLbl);
		
		txtSmester = new JTextField();
		txtSmester.setBounds(200, 95, 350, 26);
		add(txtSmester);
		
		JLabel creditLbl = new JLabel("Credit");
		creditLbl.setBounds(50, 140, 150, 16);
		add(creditLbl);
		
		txtCredit = new JTextField();
		txtCredit.setBounds(200, 135, 350, 26);
		add(txtCredit);
		
		JLabel departmentLbl = new JLabel("Department");
		departmentLbl.setBounds(50, 220, 150, 16);
		
		
		JLabel typeLbl = new JLabel("Type");
		typeLbl.setBounds(50, 180, 61, 16);
		add(typeLbl);
		
		Vector<ModuleType> model = new Vector<ModuleType>();
		model.add(ModuleType.COMPULSORY);
		model.add(ModuleType.ELECTIVE);
		JComboBox comboBox = new JComboBox<ModuleType>(model);
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(200, 175, 150, 27);
		comboBox.addActionListener(e-> {
			ModuleType selected = (ModuleType) comboBox.getSelectedItem();
			switch (selected.getId()){
				case 1: 
					remove(departmentLbl);
					remove(txtDepartment);
					this.repaint();
					break;
				default:
					
					add(departmentLbl);
					
					txtDepartment = new JTextField();
					txtDepartment.setBounds(200, 215, 350, 26);
					add(txtDepartment);
					
					this.repaint();
					break;
			}
		});
		add(comboBox);
		
		
		JLabel lblError = new JLabel("");
		lblError.setForeground(Color.DARK_GRAY);
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setBounds(0, 240, 600, 16);
		add(lblError);
		
		JLabel lblSucces = new JLabel("");
		lblSucces.setForeground(Color.GREEN);
		lblSucces.setHorizontalAlignment(SwingConstants.CENTER);
		lblSucces.setBounds(0, 240, 600, 16);
		add(lblSucces);
		
		JButton btnAdd = new JButton("Add Module");
		btnAdd.setBounds(240, 260, 130, 35);
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lblError.setText("");
				lblSucces.setText("");
				try {
					String name = txtName.getText();
					int semester = 0;
					try {
						semester = Integer.parseInt(txtSmester.getText());
					} catch (Exception e2) {
						lblError.setText("Semester must be number");
						return;
					}
					int credit = 0;
					try {
						credit = Integer.parseInt(txtCredit.getText());
					} catch (Exception e2) {
						lblError.setText("Credit must be number");
						return;
					}
					
//					System.out.println(name +"\t" + "credit:" + credit + "semester:" + semester);
					Module module = null;
					ModuleType selected = (ModuleType) comboBox.getSelectedItem();
					switch (selected.getId()){
						case 1: 
							module = new Module(name, semester, credit);
							break;
						default:
							String department = txtDepartment.getText();
							module = new Emodule(name, semester, credit, department);
							txtDepartment.setText("");
							break;
							
					}
					manager.addModule(module);
					txtName.setText(""); txtSmester.setText(""); txtCredit.setText("");
					
					lblSucces.setText("New Module added");
				} catch (Exception e2) {
					e2.printStackTrace();
					lblError.setText(e2.getMessage());
				}
			}
		});
		add(btnAdd);
		
		
		
	}
}
