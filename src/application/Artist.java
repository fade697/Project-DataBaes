 package application;

import java.sql.ResultSet;

public class Artist {
	private int Artist_ID;
	private int Artist_Salary;
	private String Artist_Name;

	public Artist(int artist_ID, String artist_Name, int artist_Salary) {
		super();
		Artist_ID = artist_ID;
		Artist_Salary = artist_Salary;
		Artist_Name = artist_Name;
	}

	public Artist(ResultSet rs) {
		// TODO Auto-generated constructor stub
	}

	public int getArtist_ID() {
		return Artist_ID;
	}

	public void setArtist_ID(int artist_ID) {
		Artist_ID = artist_ID;
	}

	public int getArtist_Salary() {
		return Artist_Salary;
	}

	public void setArtist_Salary(int artist_Salary) {
		Artist_Salary = artist_Salary;
	}

	public String getArtist_Name() {
		return Artist_Name;
	}

	public void setArtist_Name(String artist_Name) {
		Artist_Name = artist_Name;
	}

	@Override
	public String toString() {
		return "Artist [Artist_ID=" + Artist_ID + ", Artist_Salary=" + Artist_Salary + ", Artist_Name=" + Artist_Name
				+ "]";
	}
	

}
