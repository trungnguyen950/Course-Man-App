package a2_1901040232;

public enum ModuleType {
	
	COMPULSORY(1, "Compulsory Module"),
	ELECTIVE(2, "Elective Module");
	
	private int id;
	private String display;
	private ModuleType(int id, String display) {
		this.id = id;
		this.display = display;
	}
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return display;
	}
	
	

}
