package model;

import java.io.Serializable;
import java.time.LocalDate;

import javafx.scene.image.Image;
import model.interfaces.Person;

public class PersonImpl implements Person, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4595499275112073535L;
	private String firstName;
    private String lastName;
    private String taxCode;
    private LocalDate birthday;
    private String email;
    private String telephonNumber;
    private transient Image imageProfile;
    
	public PersonImpl(String firstName, String lastName, String taxCode, LocalDate birthday , String email, 
			String telephonNumber, Image imageProfile) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.taxCode = taxCode;
		this.birthday = birthday;
		this.email = email;
		this.telephonNumber = telephonNumber;
		this.imageProfile = imageProfile;
	}

	@Override
	public String getFirstName() {
		return this.firstName;
	}

	@Override
	public String getLastName() {
		return this.lastName;
	}
	
	@Override
	public String getTaxCode() {
		return this.taxCode;
	}

	@Override
	public String getEmail() {
		return this.email;
	}
	
	@Override
	public String getTelephonNumber() {
		return this.telephonNumber;
	}
	
	@Override
	public LocalDate getBirthday() {
		return this.birthday;
	}
	
	@Override
	public Image getImageProfile() {
		return this.imageProfile;
	}
	
	@Override
	public void setImageProfile(Image imageProfile) {
		this.imageProfile = imageProfile;
	}

	@Override
	public String toString() {
		return "PersonImpl [firstName=" + firstName + ", lastName=" + lastName + ", birthday=" + birthday + 
				", email=" + email + ", telephonNumber=" + telephonNumber + "]";
	}
	
	public static class PersonBuilder {
		
		private String firstName;
	    private String lastName;
	    private String taxCode;
	    private LocalDate birthday;
	    private String email;
	    private String telephonNumber;
	    private Image imageProfile;
	    
	    public PersonBuilder firstName(String name) {
	    	this.firstName = name;
	    	return this;
	    }
	    
	    public PersonBuilder lastName(String lastName) {
	    	this.lastName = lastName;
	    	return this;
	    }
	    
	    public PersonBuilder taxCode(String taxCode) {
	    	this.taxCode = taxCode;
	    	return this;
	    }
	    
	    public PersonBuilder birthday(LocalDate date) {
	    	this.birthday = date;
	    	return this;
	    }
	    
	    public PersonBuilder email(String email) {
	    	this.email = email;
	    	return this;
	    }
	    
	    public PersonBuilder telephoneNumber(String telephoneNumber) {
	    	this.telephonNumber = telephoneNumber;
	    	return this;
	    }
	    
	    public PersonBuilder imageProfile(Image imageProfile) {
	    	this.imageProfile = imageProfile;
	    	return this;
	    }
	    
	    public PersonImpl build() {
	    	return new PersonImpl(this.firstName, this.lastName, this.taxCode, this.birthday, this.email, this.telephonNumber, this.imageProfile);
	    	
	    }
	}
	
	

}
