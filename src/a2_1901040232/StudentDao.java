package a2_1901040232;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import a2_1901040232.model.Student;


public class StudentDao {
	
	private static String databasePath = "jdbc:sqlite:src/a2_1901040232/database.sqlite3";  
	
	public void addStudent(Student student) throws SQLException {
		String query = "INSERT INTO students(name, dob, address, email, SID) values(?,?,?,?,?)";  
		Connection con = connect();
		PreparedStatement ps = con.prepareStatement(query); 

		ps.setString(5, student.getSID());
		ps.setString(1, student.getName());  
        ps.setDate(2, Date.valueOf(student.getDob()));
        ps.setString(3, student.getAddress());
        ps.setString(4, student.getEmail());
        ps.executeUpdate();  
        con.close();
	}
	
	public List<Student> getAllStudents() throws SQLException{
		List<Student> students = new ArrayList<>();
		
		String sql = "SELECT * FROM students";
		Connection con = connect();
		PreparedStatement ps = con.prepareStatement(sql);  
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {  
			String SID = rs.getString("SID");
			String name = rs.getString("name");
			LocalDate dob = rs.getDate("dob").toLocalDate();
			String address = rs.getString("address");
			String email = rs.getString("email");
            Student student;
//            System.out.println(SID + "\t" + name + "\t"
//            		+ dob + "\t" + address + "\t" + email);
			try {
				student = new Student(name, dob, address, email);
				student.setSID(SID);
				students.add(student);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        }  
		con.close();
		return students;
	}

	public static Connection connect() throws SQLException {
        Connection conn = DriverManager.getConnection(databasePath);
        return conn;
    }
	
}
