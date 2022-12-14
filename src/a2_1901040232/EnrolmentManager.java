package a2_1901040232;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import a2_1901040232.model.Enrolment;
import a2_1901040232.model.Module;
import a2_1901040232.model.Student;

public class EnrolmentManager {
	
	EnrollmentDao enrolDao = new EnrollmentDao();
	StudentDao studentDao = new StudentDao();
	ModuleDao moduleDao = new ModuleDao();
	
	/**
	 * @effects adds a new enrollment
	 * @param e
	 * @throws Exception 
	 */
	public void addEnrolment(Enrolment e) throws Exception{
		try {
			enrolDao.addEnrollment(e);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * @effects returns the enrollment of the specified student and module
	 * @param s
	 * @param m
	 * @return
	 * @throws Exception 
	 */
	public Enrolment getEnrolment(Student s, Module m) throws Exception {
		return enrolDao.getEnrolment(s, m);
	}

	/**
	 * 
	 * @param s
	 * @param m
	 * @param internalM
	 * @param examM
	 * @throws Exception
	 * @effects records the internal and examination marks(in that order) into the
	 *          enrolment of the specified module of the given student
	 */
	public void setMarks(Student s, Module m, double internalM, double examM) throws Exception {
		if(internalM < 0 || internalM > 10) {
			throw new Exception("Invalid Internal Mark");
		}
		if(examM < 0 || examM > 10) {
			throw new Exception("Invalid Exam Mark");
		}
		try {
			enrolDao.setMark(s, m, internalM, examM);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * @effects generates an assessment report of all the enrolments together with
	 *          their internal, exam marks and final grade
	 * @return
	 * @throws SQLException 
	 */
	public ArrayList<ArrayList<String>> reportAssessment() throws SQLException {
	
		return enrolDao.reportAssignemt();
	}

	/**
	 * @effects generates an initial report of all the current enrolments (without
	 *          marks)in the program
	 * @return
	 * @throws SQLException 
	 */
	public ArrayList<ArrayList<String>> report() throws SQLException {
		return enrolDao.report();
	}

	/**
	 * @effects sorts the current enrolments in the descending order of the student
	 *          identifier
	 */
	public void sort(List<Enrolment> enrolments) {
		Collections.sort(enrolments);
	}
	
	public void addStudent(Student student) throws Exception {
		try {
			studentDao.addStudent(student);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Add new student fail to save database");
		}
	}
	
	public List<Student> getAllStudent() throws Exception {
		return studentDao.getAllStudents();
	}
	
	public void addModule(Module module) throws Exception {
		try {
			moduleDao.addModule(module);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Add new module fail to save database");
		}
	}
	
	public List<Module> getAllModule() throws Exception {
		return moduleDao.getAllModule();
	}
}
