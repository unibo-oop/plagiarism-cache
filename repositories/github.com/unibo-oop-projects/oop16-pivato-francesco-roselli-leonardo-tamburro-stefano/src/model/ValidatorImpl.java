package model;
import model.enumerations.*;
import model.interfaces.*;

public class ValidatorImpl implements Validator {
	
	private final Data data = DataImpl.getInstance();
	
	public ValidatorImpl() {
		
	}
	
	@Override
	public Status validate(String id, String password) {
		
		if (!data.getLoginData().containsKey(id)) {
			return Status.ADMIN_NOT_FOUND;
		}
		
		if (id == null || password == null) {
			return Status.NO_INPUT;
		}
		
		if (!data.getLoginData().get(id).equals(password)) {
			return Status.PASSWORD_WRONG;
		}
		
		return Status.ALL_RIGHT;
		
	}
	
}
