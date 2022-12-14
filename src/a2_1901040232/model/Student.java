package a2_1901040232.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Pattern;

public class Student {
	private String SID;
	private String name;
	private LocalDate dob;
	private String address;
	private String email;

	
	private static int idGen = LocalDate.now().getYear();
	
	public Student(String name, LocalDate dob, String address, String email) throws Exception {
		if(!validateName(name)) {
			throw new Exception("Invalid Name");
		}
		if(!validateDob(dob)) {
			throw new Exception("Invalid Date of birth");
		}
		if(!validateAddress(address)) {
			throw new Exception("Invalid Address");
		}
		if(!validateEmail(email)) {
			throw new Exception("Invalid Email");
		}
		this.SID = "S2022";
		this.name = name;
		this.dob = dob;
		this.address = address;
		this.email = email;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Student))
			return false;
		Student other = (Student) obj;
		return Objects.equals(address, other.address) && Objects.equals(dob, other.dob)
				&& Objects.equals(email, other.email) && Objects.equals(SID, other.SID)
				&& Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		String student = String.format("SID: %s - Name: %s",SID,name);
		return student;
	}

	private boolean validateName(String name) {
		return name.length() > 0 && name != null;
	}

	private boolean validateDob(LocalDate dob) {
		return dob != null;
	}

	private boolean validateAddress(String address) {
		return address.length() > 0;
	}

	private boolean validateEmail(String email) {
		String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
		Pattern emailPattern = Pattern.compile(emailRegex);
		return emailPattern.matcher(email).matches();

	}
	
	private LocalDate parseStringtoLocalDate(String str) throws Exception  {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        LocalDate localDate = LocalDate.parse(str, formatter);
	        return localDate;
		} catch (Exception e) {
			throw new Exception("Birthday wrong format");
		}
		
	}
	
	public String getDOBStr(){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return dob.format(formatter);
		
	}

	public String getSID() {
		return SID;
	}

	public void setSID(String sID) {
		SID = sID;
	}
}
