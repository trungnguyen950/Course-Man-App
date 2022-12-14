package a2_1901040232.model;

public class Enrolment implements Comparable<Enrolment> {
	
	private int id;
	private Student s;
	private Module m;
	private double internalM;
	private double examM;
	private char finalG;

	public Enrolment(Student s, Module m, double internalM, double examM) throws Exception {
		if (!validateStudent(s) || !validateModule(m) || !validateInternalMark(internalM) || !validateExamMark(examM)) {
			throw new Exception("Invalid name, dob, address, email");
		} else {
			this.s = s;
			this.m = m;
			this.internalM = internalM;
			this.examM = examM;
			this.finalG = Enrolment.this.calculateFinal(internalM, examM);
		}
	}
	
	

	private char calculateFinal(double internalM2, double examM2) {
		// TODO Auto-generated method stub
		double aggregatedM = internalM2 * 0.4 + examM2 * 0.6;
		if (aggregatedM > 8) {
			return 'E';
		} else if (aggregatedM > 5) {
			return 'G';
		} else if (aggregatedM == 5) {
			return 'P';
		} else {
			return 'F';
		}
	}

	/**
	 * @return the s
	 */
	public Student getS() {
		return s;
	}

	/**
	 * @param s the s to set
	 */
	public void setS(Student s) {
		if (!validateStudent(s)) {
			return;
		}
		this.s = s;
	}

	/**
	 * @return the m
	 */
	public Module getM() {
		return m;
	}

	/**
	 * @param m the m to set
	 */
	public void setM(Module m) {
		if (!validateModule(m)) {
			return;
		}
		this.m = m;
	}

	/**
	 * @return the internalM
	 */
	public double getInternalM() {
		return internalM;
	}

	/**
	 * @param internalM the internalM to set
	 */
	public void setInternalM(double internalM) {

		if (!validateInternalMark(internalM)) {
			return;
		}
		this.internalM = internalM;
	}

	/**
	 * @return the examM
	 */
	public double getExamM() {
		return examM;
	}

	/**
	 * @param examM the examM to set
	 */
	public void setExamM(double examM) {
		if (!validateExamMark(examM)) {
			return;
		}
		this.examM = examM;
	}

	/**
	 * @return the finalG
	 */
	public char getFinalG() {
		return finalG;
	}

	/**
	 * @param finalG the finalG to set
	 */
	public void setfinalG(char finalG) {
		this.finalG = finalG;
	}

	@Override
	public String toString() {
		return "Enrolment [s:" + s + ", m:" + m + ", internal Mark:" + internalM + ", exam Mark:" + examM
				+ ", final Grade: " + finalG + "]";
	}

	@Override
	public int compareTo(Enrolment o) {

		return o.s.getSID().compareTo(this.s.getSID());
	}

	private boolean validateStudent(Student s) {
		return s != null;
	}

	private boolean validateModule(Module m) {
		return m != null;
	}

	private boolean validateInternalMark(double internalM) {
		return internalM >= 0 && internalM <= 10;
	}

	private boolean validateExamMark(double examM) {
		return examM >= 0 && examM <= 10;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFinalG(char finalG) {
		this.finalG = finalG;
	}
	
	
}
