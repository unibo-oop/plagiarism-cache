package model;

import java.util.Date;

public class SimpleUser implements Person {

	private String name;
	private String lastName;
	private Date birth;
	
	public SimpleUser(final String name, final String lastName, final Date birthDate) {
		this.name = name;
		this.lastName = lastName;
		this.birth = birthDate;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getLastname() {
		return this.lastName;
	}

	@Override
	public Date getBirthDate() {
		return (Date)this.birth.clone();
	}


}
