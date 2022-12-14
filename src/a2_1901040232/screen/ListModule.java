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
import a2_1901040232.model.Emodule;
import a2_1901040232.model.Module;

public class ListModule extends JPanel {
	
	EnrolmentManager manager = new EnrolmentManager();

	
	public ListModule() {
		List<Module> modules = null;
		try {
			modules = manager.getAllModule();
		} catch (Exception e) {
			modules = new ArrayList<>();
		}
		setLayout(new GridLayout(0,1,0,0));
		
		// create Table
		JTable table = new JTable(){
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		DefaultTableModel model = new DefaultTableModel();

		model.addColumn("Module Name");
		model.addColumn("Module Code");
		model.addColumn("Semester");
		model.addColumn("Credit");
		model.addColumn("Type");
		model.addColumn("Department");
		
		for(Module module : modules){ 
	           Vector<String> r  = new Vector<String>();

	            r.addElement(module.getName());
	            r.addElement(module.getCode());
	            r.addElement(String.valueOf(module.getSemester()));
	            r.addElement(String.valueOf(module.getCredit()));
	            if(module instanceof Emodule) {
	            	r.addElement("Elective");
	            } else {
	            	r.addElement("Compulsory");
	            }
	            if(module instanceof Emodule) {
	            	r.addElement(((Emodule) module).getDepartment());
	            } else {
	            	r.addElement("");
	            }
	            model.addRow(r);
	    } 
		
	    table.setModel(model);
		
		JScrollPane scrollBar = new JScrollPane(table);
		add(scrollBar);
		
	}
}
