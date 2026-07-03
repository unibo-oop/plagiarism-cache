package model;

import java.io.Serializable;

public class MasterImpl implements Master,Serializable {

	private static final long serialVersionUID = 1L;
	private final String name;
	private final String surname;

	private final Integer dan;
	
	public MasterImpl(String name, String surname, Integer dan) {
		super();
		this.name = name;
		this.surname = surname;
		this.dan = dan;
	}
	public String getName() {
		
		return name;
	}

	
	public String getSurname() {
		
		return surname;
	}

	
	public Integer getDan() {

		return dan;
	}
	
	public String toString(){
		return name+" "+surname+" "+dan;
	}
}
