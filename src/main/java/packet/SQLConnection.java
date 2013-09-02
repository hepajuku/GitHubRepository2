package packet;

import java.sql.*;


/**
 * Connects and sends data to PostgreSQL database with JDBC
 * @author Henri
 *
 */
public class SQLConnection {
	private String host = "jdbc:postgresql://localhost:5432/mydatabase";
	private String user = "admin";
	private String password = "1234";
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;

		
	/**
	 * Connect to SQL database
	 * @return returns integer, 0 no problems, 1 connection problem
	 */
	public int setConnection() {
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return 1;
		}
		
		try {
			connection = DriverManager.getConnection(host, user, password);	
			statement = connection.createStatement();
		}
		catch (SQLException error) {
			System.out.println(error.getMessage());
			return 1;
		}		
		return 0;
	}
	
	/**
	 * Close connection to SQL database
	 */
	public void closeConnection() {
		try {
			if (connection != null)
				connection.close();
			
			if (statement != null)
				statement.close();
			
			if (resultSet != null)
				resultSet.close();
			
			} catch (SQLException error) {
				System.out.println(error.getMessage());
			}			
	}
	
	/**
	 * Send string to database. String needs to be 'comma' separated.
	 * "'fName','lName','gender','application'".
	 * @param str Comma separated string that is sent to SQL database
	 * @return 0 if data was sent to database, 1 if error occurred
	 */
	public int sendToDatabase(String str) {
		try {
			//statem is executed SQL statement
			String statem = "INSERT INTO job_applications(first_name,last_name,gender,application) VALUES(" 
					+str +")";
			statement.execute(statem);			
			
		} catch (SQLException error) {
			System.out.println(error.getMessage());
			return 1;
		}
		return 0;
	}
}