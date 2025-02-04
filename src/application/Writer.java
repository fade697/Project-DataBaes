package application;

public class Writer {
	private int Writer_ID;
	private int Writer_Salary;
	private String Writer_Name;

	public Writer(int writer_ID, int writer_Salary, String writer_Name) {
		super();
		Writer_ID = writer_ID;
		Writer_Salary = writer_Salary;
		Writer_Name = writer_Name;
	}

	public int getWriter_ID() {
		return Writer_ID;
	}

	public void setWriter_ID(int writer_ID) {
		Writer_ID = writer_ID;
	}

	public int getWriter_Salary() {
		return Writer_Salary;
	}

	public void setWriter_Salary(int writer_Salary) {
		Writer_Salary = writer_Salary;
	}

	@Override
	public String toString() {
		return "Writer [Writer_ID=" + Writer_ID + ", Writer_Salary=" + Writer_Salary + ", Writer_Name=" + Writer_Name
				+ "]";
	}

	public String getWriter_Name() {
		return Writer_Name;
	}

	public void setWriter_Name(String writer_Name) {
		Writer_Name = writer_Name;
	}

}
