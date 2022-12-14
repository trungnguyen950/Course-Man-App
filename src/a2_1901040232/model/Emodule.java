package a2_1901040232.model;

import java.util.Objects;

public class Emodule extends Module {

	private String department;

	public Emodule(String name, int semester, int credit, String department) throws Exception {
		super(name, semester, credit);
		if (!validateDepartmentName(department)) {
			
			throw new Exception("Invalid departmentName");
		} else {
			this.department = department;
		}
	}
	
	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		if (!validateDepartmentName(department)) {
			return;
		} else {
			this.department = department;
		}
	}

	private boolean validateDepartmentName(String departmentName) {
		return departmentName != null && departmentName.length() > 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Emodule))
			return false;
		Emodule other = (Emodule) obj;
		return Objects.equals(department, other.department);
	}

}
