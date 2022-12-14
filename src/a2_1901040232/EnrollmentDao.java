package a2_1901040232;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import a2_1901040232.model.Emodule;
import a2_1901040232.model.Enrolment;
import a2_1901040232.model.Module;
import a2_1901040232.model.Student;

public class EnrollmentDao {
	
	private static String databasePath = "jdbc:sqlite:src/a2_1901040232/database.sqlite3";  
	
	public void addEnrollment(Enrolment enrollment) throws SQLException {
		String query = "INSERT INTO enrolment(SID, code, internalMark, examinationMark, finalGrade, sName, mName) values(?,?,?,?,?,?,?)";  
		Connection con = EnrollmentDao.connect();
		PreparedStatement ps = con.prepareStatement(query);  

		ps.setString(1, enrollment.getS().getSID());  
		ps.setString(2, enrollment.getM().getCode());
		ps.setDouble(3, enrollment.getInternalM());
		ps.setDouble(4, enrollment.getExamM());
		String finalG = Character.toString(enrollment.getFinalG());
		ps.setString(5, finalG);
		ps.setString(6, enrollment.getS().getName());
		ps.setString(7, enrollment.getM().getName());
        ps.executeUpdate();  
        con.close();
	}
	
	
	public Enrolment getEnrolment(Student student, Module module) throws Exception {
		Enrolment enrolment = null;
		String query = "Select * FROM enrolment where SID = ? and code = ?";
		Connection con = connect();
		PreparedStatement ps = con.prepareStatement(query);  
		ps.setString(1,student.getSID());
		ps.setString(2,module.getCode());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {  
			double internalMark = rs.getDouble("internalMark");
			double examM = rs.getDouble("examinationMark");
			enrolment = new Enrolment(student, module, internalMark, examM);
        }  
		con.close();
		return enrolment;
	}
	
	public void setMark(Student student, Module module, double internalMark, double examinationMark) throws SQLException {
		String query = "UPDATE enrolment SET internalMark = ?, examinationMark = ? WHERE (SID = ? AND code = ?)";  
		Connection con = connect();
		PreparedStatement ps = con.prepareStatement(query);  
		ps.setDouble(1, internalMark);
		ps.setDouble(2, examinationMark);
		ps.setString(3, student.getSID());  
		ps.setString(4, module.getCode());
        ps.executeUpdate();  
        con.close();
	}
	
	public ArrayList<ArrayList<String>> report() throws SQLException{
		ArrayList<ArrayList<String>> list = new ArrayList<>();
		String query = "SELECT e.id, SID, sName, code, mName "
				+ "FROM enrolment e ";
		Connection con = connect();
		PreparedStatement ps = con.prepareStatement(query);  
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {  
			String eId = Integer.toString(rs.getInt("id"));
			String SID = rs.getString("SID");
			String sName = rs.getString("sName");
			String moduleName = rs.getString("mName");
			String moduleCode = rs.getString("code");
			ArrayList<String> row = new ArrayList<>(); 
			
			row.add(eId);
			row.add(SID);
			row.add(sName);
			row.add(moduleName);
			row.add(moduleCode);
			
			list.add(row);
        }  
		con.close();
		return list;
		
	}
	
	public ArrayList<ArrayList<String>> reportAssignemt() throws SQLException{
		ArrayList<ArrayList<String>> enrolments = new ArrayList<>();
		String query = "SELECT id, SID, code, internalMark, examinationMark,finalGrade FROM enrolment";
		Connection con = connect();
		PreparedStatement ps = con.prepareStatement(query);  
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {  
			
			String eId = Integer.toString(rs.getInt("id"));
			String SID = rs.getString("SID");
			String code = rs.getString("code");
			String internalMark = Double.toString(rs.getDouble("internalMark"));
			String examM =  Double.toString(rs.getDouble("examinationMark"));
			String finalG = rs.getString("finalGrade");

			ArrayList<String> row = new ArrayList<>();
			row.add(eId);
			row.add(SID);
			row.add(code);
			row.add(internalMark);
			row.add(examM);
			row.add(finalG);
			
			enrolments.add(row);
			}  
		con.close();
		return enrolments;
		
	}
	
	public static Connection connect() throws SQLException {
        Connection connection = DriverManager.getConnection(databasePath);
        return connection;
    }
}
