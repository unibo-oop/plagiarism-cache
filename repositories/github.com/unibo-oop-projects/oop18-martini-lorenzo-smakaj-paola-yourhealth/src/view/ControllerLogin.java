package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerLogin implements ActionListener {
	
	LoginForm login;
	
	public ControllerLogin(LoginForm login) throws Exception {
		this.login = login;
	}
	
	public void actionPerformed(ActionEvent ae) {
		switch (ae.getActionCommand()) {
		case "Amministratore":
			new AdminForm();
			break;
		case "Dottore":
			new DocForm();
			break;
		case "Paziente":
			new PatientForm();
			break;
			
	  }
	}
}
