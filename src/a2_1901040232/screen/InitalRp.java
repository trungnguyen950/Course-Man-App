package a2_1901040232.screen;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import a2_1901040232.EnrolmentManager;


public class InitalRp extends JPanel {
	
	EnrolmentManager manager = new EnrolmentManager();

	
	public InitalRp() {
		ArrayList<ArrayList<String>> enrolments = null;
		try {
			enrolments = manager.report();
		} catch (Exception e) {
			enrolments = new ArrayList<>();
		}
		
		setLayout(new GridLayout(0,1,0,0));
		
		JTable table = new JTable(){
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Student ID");
		model.addColumn("Student Name");
		model.addColumn("Module Code");
		model.addColumn("Module Name");
		
		for(ArrayList row : enrolments){ 
	           Vector<String> r  = new Vector<String>();
	           for(String s : (ArrayList<String>) row) {
	        	   r.add(s);
	           }
	            model.addRow(r);
	    } 
		
	    table.setModel(model);
		
		JScrollPane scrollBar = new JScrollPane(table);
		add(scrollBar);
		
	}
}
