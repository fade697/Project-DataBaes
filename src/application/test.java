package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class test {
																																	private static  SQLCursor con;
	private static ArrayList<Artist> Ardata;
	
	
	

	public static void main(String[] args) throws NumberFormatException, SQLException {
		try {
			con=new SQLCursor();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		con=SQLCursor.createSQLCursor();
		String SQL;
		Ardata = new ArrayList<>();
		System.out.println("Connection established");
		SQL ="select * from sys.artist";
		
		ResultSet rs = con.executeQuery(SQL);
		while (rs.next())
			Ardata.add(new Artist(Integer.parseInt(rs.getString(1)),rs.getString(2),Integer.parseInt(rs.getString(3))));
		rs.close();
		
		for (int i=0;i<Ardata.size();i++) {
			System.out.println(Ardata.get(i));
		}
	}
	
		
	

}
