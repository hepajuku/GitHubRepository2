package packet;

import com.vaadin.server.UserError;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;
import packet.JobApplication;


/**
 * MyButtonListener for button click events and checking errors on TextFields, OptionGroup and TextArea
 * @author Henri
 *
 */
@SuppressWarnings("serial")
public class MyButtonListener implements Button.ClickListener {
	private JobApplication jobApplication;
	private TextField fName;
	private TextField lName;
	private OptionGroup gender;
	private TextArea application;
	private Layout layout;
	
	
	/**
	 * @param jApp reference to JobApplication
	 */
	public MyButtonListener(JobApplication jApp) {
		jobApplication = jApp;
	}
	
	/**
	 * @param fname TextField First name
	 * @param lname TextField Last name
	 * @param gndr OptionGroup Gender
	 * @param appl TextArea application
	 * @param someLayout Layout layout
	 */
	public void importComponents(TextField fname, TextField lname, OptionGroup gndr, TextArea appl, Layout someLayout) {
		fName = fname;
		lName = lname;
		gender = gndr;
		layout = someLayout;
		application = appl;
	}	
	
	@Override
	public void buttonClick(ClickEvent event) {	
		
		//if at end i == 4 all is ok to save to database
		int i = 0;
		
		//checks TextFields and OptionGroup and TextArea if all is ok
		if (fName.getValue() == null || fName.getValue().trim().equals("")) {			
			fName.setComponentError(new UserError("Enter first name"));
		} 
		else {				
			//layout.addComponent(new Label("Thank you for adding first name: |" +fName.getValue() +"|"));
			fName.setComponentError(null);
			i++;
		}
				
		if (lName.getValue() == null || lName.getValue().trim().equals("")) {
			lName.setComponentError(new UserError("Enter last name"));
		} 
		else {
			//layout.addComponent(new Label("Thank you for adding last name: " +lName.getValue()));
			lName.setComponentError(null);
			i++;
		}
		
		if (gender.getValue() == null || gender.getValue().toString().equals("")) {
			gender.setComponentError(new UserError("Select gender"));
		} 
		else {
			//layout.addComponent(new Label("Thank you for choosing gender: " +gender.getValue()));
			gender.setComponentError(null);
			i++;
		}
		
		if (application.getValue() == null || application.getValue().trim().equals("")) {
			application.setComponentError(new UserError("Fill in application"));
		} 
		else {
			//layout.addComponent(new Label("Thank you for applying: " +application.getValue()));
			application.setComponentError(null);
			i++;
		}
		
		//helper string to check if gender returns null
		String genTemp = "";
		if (gender.getValue() != null)
			genTemp = gender.getValue().toString();
		
		//set information to jobApplication
		fName.setValue(fName.getValue().trim());
		lName.setValue(lName.getValue().trim());
		application.setValue(application.getValue().trim());
		jobApplication.setFirstName(fName.getValue());
		jobApplication.setLastName(lName.getValue());
		jobApplication.setGender(genTemp);
		jobApplication.setApplication(application.getValue());
		
		//all is ok to save data to database
		if (i == 4) {
			int error = 0;
			error = jobApplication.saveToDatabase();
			if (error == 1)
				layout.addComponent(new Label("Couldn't save application to database. Try again"));
			if (error == 0)
				layout.addComponent(new Label("Application saved to database"));
		}
	}
}