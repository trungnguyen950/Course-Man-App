package a2_1901040232.screen;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

public class MainScreen {

	private JFrame frame;
	private JPanel body;
//	private final Action action = new SwingAction();
	

	public MainScreen() {
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		menuBar.add(file());
		menuBar.add(student());
		menuBar.add(module());
		menuBar.add(enrollment());
		
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		body = new JPanel();
		body.setLayout(new GridLayout(0, 1, 0,0));
		frame.getContentPane().add(body);
		
	}
	
	private JMenu file() {
		JMenu mnFile = new JMenu("File");
		
		JMenuItem mnExit = new JMenuItem("Exit");
		mnExit.addActionListener(e -> System.exit(0));
		mnFile.add(mnExit);
		return mnFile;
	}
	
	private JMenu student() {
		JMenu mnParent = new JMenu("Student");
		JMenuItem mnNew = new JMenuItem("New Student");
		mnNew.addActionListener(e-> changeScreen(new NewStudent()));
		mnParent.add(mnNew);
		mnParent.addSeparator();
		JMenuItem mnList = new JMenuItem("List Student");
		mnList.addActionListener(e-> changeScreen(new ListStudent()));
		mnParent.add(mnList);
		return mnParent;
	}

	private JMenu module() {
		JMenu mnParent = new JMenu("Module");
		JMenuItem mnNew = new JMenuItem("New Module");
		mnNew.addActionListener(e-> changeScreen(new NewModule()));
		mnParent.add(mnNew);
		mnParent.addSeparator();
		JMenuItem mnList = new JMenuItem("List Module");
		mnList.addActionListener(e-> changeScreen(new ListModule()));
		mnParent.add(mnList);
		return mnParent;
	}
	
	private JMenu enrollment() {
		JMenu mnParent = new JMenu("Enrolment");
		JMenuItem mnNew = new JMenuItem("New Enrolment");
		mnNew.addActionListener(e-> changeScreen(new NewEnrolment()));
		mnParent.add(mnNew);
		mnParent.addSeparator();
		JMenuItem mnInitial = new JMenuItem("Initial report");
		mnInitial.addActionListener(e-> changeScreen(new InitalRp()));
		mnParent.add(mnInitial);
		mnParent.addSeparator();
		JMenuItem mnAssessment = new JMenuItem("Assessment report");
		mnAssessment.addActionListener(e-> changeScreen(new AssessmentRp()));
		mnParent.add(mnAssessment);
		return mnParent;
	}
	
	

//	private class SwingAction extends AbstractAction {
//		public SwingAction() {
//			putValue(NAME, "SwingAction");
//			putValue(SHORT_DESCRIPTION, "Some short description");
//		}
//		public void actionPerformed(ActionEvent e) {
//		}
//	}
//	
	private void changeScreen(JPanel jpanel) {
		body.removeAll();
		body.add(jpanel);
		body.revalidate();
		body.repaint();
	}
}
