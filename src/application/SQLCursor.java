package application;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

public class SQLCursor {
	// DB connection variables
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String db = "sys";
	private String localhost = "jdbc:mysql://127.0.0.1:3306/" + db;
	private String username = "root";
	private String password = "fadeamlehvic1";
	private Connection conn = null;
	private static SQLCursor classObj = null;

	public SQLCursor() {

	}

	public static SQLCursor createSQLCursor() {
		if (classObj == null)
			classObj = new SQLCursor();
		return classObj;
	}
	public Connection getCon() {
		try{
	        Class.forName(this.driver);
	        return DriverManager.getConnection(this.localhost, this.username, this.password);
	    }catch(Exception ex){
	        System.out.println(ex.getMessage());
	        System.out.println("couldn't connect!");
	        throw new RuntimeException(ex);
	    }
	}
	public void establishConnection() {
		try {
			
				Class.forName(this.driver);
				this.conn = DriverManager.getConnection(this.localhost, this.username, this.password);
				System.out.println("Connected Successfully");
			
		} catch (Exception e) {
			System.out.println("-E- An issue happens while establishing connection: " + e);
			this.conn = null;
		}
	}

	public void closeConnection() {
		if (this.conn != null) {
			try {
				this.conn.close();
			} catch (Exception e) {
				System.out.println("-E- An issue happens while closing connection: " + e);
				this.conn = null;
			}
		}
	}

	public ResultSet executeQuery(String query) {
		ResultSet rs = null;
		this.establishConnection();
		if (this.conn != null) {
			try {
				Statement stmt = this.conn.createStatement();
				rs = stmt.executeQuery(query);
			} catch (Exception e) {
				System.out.println("-E- An issue happens while executing query \'" + query + "\': " + e);
				rs = null;
			}
		}
		return rs;
	}

	public int executeUpdate(String query) throws SQLIntegrityConstraintViolationException {
		int rs = 0;
		this.establishConnection();
		if (this.conn != null) {
			try {
				Statement stmt = this.conn.createStatement();
				rs = stmt.executeUpdate(query);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("-----------------");
				throw new SQLIntegrityConstraintViolationException();
			}
		}
		return rs;
	}
}