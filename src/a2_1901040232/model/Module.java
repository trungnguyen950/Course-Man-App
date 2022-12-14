package a2_1901040232.model;

import java.util.Objects;

import a2_1901040232.ModuleDao;

public class Module {
	private String code;
	private String name;
	private int semester;
	private int credit;

	public Module(String name, int semester, int credit) throws Exception {
		if(!validateName(name)) {
			throw new Exception("Invalid name");
		}
		if(!validateSemester(semester)) {
			throw new Exception("Invalid semester");
		}
		if(!validateCredits(credit)) {
			throw new Exception("Invalid credit");
		}
		this.name = name;
		this.semester = semester;
		this.credit = credit;
		this.code = generateCode();
	}


	public void setCode(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (!validateName(name)) {
			return;
		}
		this.name = name;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		if (!validateSemester(semester)) {
			return;
		}
		this.semester = semester;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		if (!validateCredits(credit)) {
			return;
		}
		this.credit = credit;
	}

	@Override
	public String toString() {
		return "Module [code=" + code + ", name=" + name + ", semester=" + semester + ", credit=" + credit + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Module))
			return false;
		Module other = (Module) obj;
		return Objects.equals(code, other.code) && credit == other.credit && Objects.equals(name, other.name)
				&& semester == other.semester;
	}

	private boolean validateName(String name) {
		return name != null && name.length() > 0;
	}

	private boolean validateSemester(int semester) {
		return semester > 0;
	}

	private boolean validateCredits(int credits) {
		return credits > 0;
	}
	
	private String generateCode() {
		ModuleDao dao = new ModuleDao();
		int currentCodeOfSemester = 0;
		try {
			String maxCode = dao.getCurrentMaxCodeForSemester(semester);
			if(maxCode != null && maxCode != "") {
				currentCodeOfSemester = Integer.valueOf(maxCode.substring(2));
			}
		} catch (Exception e) {
			// Do nothing
		}
		currentCodeOfSemester += 1;
		String moduleCode = "M";
		moduleCode += (semester*100 + currentCodeOfSemester);
		return moduleCode;
		
	}
}
