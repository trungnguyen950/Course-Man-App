package a2_1901040232;

import java.awt.EventQueue;

import a2_1901040232.screen.MainScreen;

public class CourseManProg {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen ms = new MainScreen();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
