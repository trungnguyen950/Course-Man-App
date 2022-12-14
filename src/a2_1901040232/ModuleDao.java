package a2_1901040232;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import a2_1901040232.model.Emodule;
import a2_1901040232.model.Module;

public class ModuleDao {
	
	private static String databasePath = "jdbc:sqlite:src/a2_1901040232/database.sqlite3";  
	
	
	public void addModule(Module module) throws SQLException {
		String query = "INSERT INTO module(code, name, semester, credit, department) values(?,?,?,?,?)";  
		Connection con = connect();
		PreparedStatement ps = con.prepareStatement(query);  
		ps.setString(1, module.getCode());  
		ps.setString(2, module.getName());
		ps.setInt(3, module.getSemester());
		ps.setInt(4, module.getCredit());
		if(module instanceof Emodule) {
			ps.setString(5, ((Emodule) module).getDepartment());
		} else {
			ps.setNull(5, java.sql.Types.NULL);
		}
        ps.executeUpdate();  
        con.close();
	}
	
	public List<Module> getAllModule() throws Exception{
		List<Module> modules = new ArrayList<>();
		
		String sql = "SELECT code, name, semester, credit, department FROM module";
		Connection con = connect();
		PreparedStatement ps = con.prepareStatement(sql);  
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {  
			String name = rs.getString("name");
			String code = rs.getString("code");
			int semester = rs.getInt("semester");
			int credit = rs.getInt("credit");
			String department = rs.getString("department");
			Module module = null;
			if(department != null) {
				module = new Emodule(name, semester, credit, department);
				
			} else {
				module = new Module(name, semester, credit);
			}
			module.setCode(code);
			modules.add(module);
        } 
		con.close();
		return modules;
	}
	
	public String getCurrentMaxCodeForSemester(int semester) throws SQLException {
		String codeBegining = "M"+ semester+"%";
		String sql = "SELECT MAX(code) AS code FROM module WHERE code like ?";
		String maxCode = "";
		Connection con = connect();
		PreparedStatement ps = con.prepareStatement(sql);  
		ps.setString(1, codeBegining);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {  
			maxCode = rs.getString("CODE");
        }  
		con.close();
		return maxCode;
	}

	public static Connection connect() throws SQLException {
        Connection conn = DriverManager.getConnection(databasePath);
        return conn;
    }
}
