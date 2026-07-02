package cartasoci;

import java.io.Serializable;

/**
 * 
 * Basic class of a Fidelity Card Owner.
 * 
 * @author Alberto Mulazzani
 *
 */
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8503437543137206221L;
	private String name;
	private String surname;
	private String email;
	private final IFidCard carta = new FidCard();
	private int id;
	
	/**
	 * 
	 * @param nname of the User
	 */
	public User(final String nname) {
		this.name = nname;
	}
	
	/**
	 * 
	 * @param nname of user
	 * @param nsurname of user
	 */
	public User(final String nname, final String nsurname) {
		this(nname);
		this.surname = nsurname;
	}
	
	/**
	 * 
	 * @param nname of user
	 * @param nsurname of user
	 * @param nemail of user
	 */
	public User(final String nname, final String nsurname, final String nemail) {
		this(nname, nsurname);
		this.email = nemail;
	}
	
	/**
	 * 
	 * @param nid the id to set
	 */
	public void setID(final int nid) {
		this.id = nid;
	}
	
	/**
	 * 
	 * @param nname the name to set
	 */
	public void setName(final String nname) {
		this.name = nname;
	}
	
	/**
	 * 
	 * @param nsurname the surname to set
	 */
	public void setSurname(final String nsurname) {
		this.surname = nsurname;
	}
	
	/**
	 * 
	 * @param nemail the email to set
	 */
	public void setEmail(final String nemail) {
		this.email = nemail;
	}
	
	/**
	 * 
	 * @return the name of user
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 
	 * @return the surname of user
	 */
	public String getSurname() {
		return this.surname;
	}
	
	/**
	 *  
	 * @return the mail of user
	 */
	public String getEmail() {
		return this.email;
	}
	
	/**
	 * 
	 * @return the id of user
	 */
	public int getID() {
		return this.id;
	}
	
	/**
	 * 
	 * @return the points of the user
	 */
	public Integer getPoints() {
		return this.carta.getPoints();
	}
	
	/**
	 * 
	 * @param points to add
	 */
	public void addPoints(final int points) {
		this.carta.addPoints(points);
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((carta == null) ? 0 : carta.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final User other = (User) obj;
		if (id != other.id) {
			return false;
		}
		if (carta == null) {
			if (other.carta != null) {
				return false;
			}
		} else if (!carta.equals(other.carta)) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (surname == null) {
			if (other.surname != null) {
				return false;
			}
		} else if (!surname.equals(other.surname)) {
			return false;
		}
		return true;
	}

}
