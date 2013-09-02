package packet;

import packet.SQLConnection;

/**
 * Stores and handles data of job application
 * @author Henri
 *
 */
public class JobApplication {
	
	private String fName;
	private String lName;
	private String gender;
	private String application;
	
	/**
	 * Constructor for JobApplication. Doesn't initialize data.
	 */
	public JobApplication() {
		
	}
	
	/**
	 * Constructor for JobApplication
	 * @param fname First name
	 * @param lname Last name
	 * @param gendr Gender
	 * @param applictn Application text
	 */
	public JobApplication(String fname, String lname, String gendr, String applictn) {
		fName = fname;
		lName = lname;
		gender = gendr;
		application = applictn;	
		
		if (fname == null)
			fName = "";
		if (lname == null)
			lName = "";
		if (gendr == null)
			gender = "";
		if (applictn == null)
			application = "";				
	}
	
	/**
	 * @param str First name 
	 */
	public void setFirstName(String str) {		
		fName = str;
	}
	
	/**
	 * @param str Last name 
	 */
	public void setLastName(String str) {
		lName = str;
	}
	
	/**
	 * @param str Gender 
	 */
	public void setGender(String str) {
		gender = str;
		
	}
	
	/**
	 * @param str Application 
	 */
	public void setApplication(String str) {
		application = str;
	}
	
	/**
	 * @return First name as string
	 */
	public String getFirstName() {
		return fName;
	}
	/**
	 * @return Last name as string
	 */
	public String getLastName() {
		return lName;
	}
	/**
	 * @return Gender as string
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @return Application as string
	 */
	public String getApplication() {
		return application;
	}

	/**
	 * Save data to SQL database
	 * @return return 0 if no error and 1 if connection problem
	 */
	public int saveToDatabase() {
		SQLConnection connection = new SQLConnection();
		int error = 0;
		
		//Connect to database
		error = connection.setConnection();
		if (error == 1) {
			connection.closeConnection();
			return error;
		}
		//Send data to database
		error = connection.sendToDatabase(this.toCommaSeparatedString());
		if (error == 1) {
			connection.closeConnection();
			return error;
		}
		//Close connection to database
		connection.closeConnection();
		return error;
	}
	
	/**
	 * Returns data in a string that is separated with 'commas' "','". 
	 * @return Comma separated string "'fName','lName','gender','application'"
	 */
	public String toCommaSeparatedString() {
		return "'" +fName +"','" +lName +"','" +gender +"','" +application +"'";		
	}
}