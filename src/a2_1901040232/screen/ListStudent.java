package a2_1901040232.screen;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import a2_1901040232.EnrolmentManager;
import a2_1901040232.model.Student;

public class ListStudent extends JPanel {
	
	EnrolmentManager manager = new EnrolmentManager();

	
	public ListStudent() {
		List<Student> students = null;
		try {
			students = manager.getAllStudent();
		} catch (Exception e) {
			students = new ArrayList<>();
		}
		setLayout(new GridLayout(0,1,0,0));
		JTable table = new JTable(){
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};;
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Student ID");
		model.addColumn("Student Name");
		model.addColumn("DoB");
		model.addColumn("Address");
		model.addColumn("Email");
		
		for(Student student : students){ 
	           Vector<String> r  = new Vector<String>();
	            r.addElement(student.getSID());
	            r.addElement(student.getName());
	            r.addElement(student.getDOBStr());
	            r.addElement(student.getAddress());
	            r.addElement(student.getEmail());
	            model.addRow(r);
	    } 
		
	    table.setModel(model);
		
		JScrollPane scrollBar = new JScrollPane(table);
		add(scrollBar);
		
	}
}
