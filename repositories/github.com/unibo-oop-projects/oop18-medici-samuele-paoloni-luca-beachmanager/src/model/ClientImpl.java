package model;

import java.util.Date;

public class ClientImpl extends SimpleUser implements Client {

	private String id;
	private Date registrationDate;
	
	
	public ClientImpl(final String name,final String lastName,final Date birthDate, final String id, final Date registrationDate) {
		super(name, lastName, birthDate);
		this.id = id;
		this.registrationDate = registrationDate;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public Date getRegistrationDate() {
		return (Date)this.registrationDate.clone();
	}

}
