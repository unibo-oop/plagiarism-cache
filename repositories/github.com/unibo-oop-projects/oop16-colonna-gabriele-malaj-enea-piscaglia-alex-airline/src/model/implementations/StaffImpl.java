package model.implementations;

import java.io.Serializable;
import java.util.Date;

import model.interfaces.Staff;

/**
 * 
 * Implements a staff member.
 */
public class StaffImpl extends PersonImpl implements Staff, Serializable {

    private static final long serialVersionUID = -4462519064517551908L;

    private final String username;
    private String password;

    /**
     * Creates a new staff member.
     * 
     * @param firstName           the staff member's name
     * @param lastName            the staff member's surname
     * @param gender              the staff member's gender
     * @param birthDate           the staff member's birth date
     * @param fc                  the staff member's fiscal code
     * @param telephoneNum        the staff member's telephone number
     * @param mail                the staff member's email
     * @param usrname             the staff member's username
     * @param pwd                 the staff member's password
     */
    public StaffImpl(final String firstName, final String lastName, final String gender, final Date birthDate, final String fc,
                     final String telephoneNum, final String mail, final String usrname, final String pwd) {
        super(firstName, lastName, gender, birthDate, fc, telephoneNum, mail);
        this.username = usrname;
        this.password = pwd;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void setPassword(final String pwd) {
        this.password = pwd;
    }

    @Override
    public String toString() {
        return "USERNAME: " + this.username + super.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((this.password == null) ? 0 : this.password.hashCode());
        result = prime * result + ((this.username == null) ? 0 : this.username.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StaffImpl other = (StaffImpl) obj;
        if (this.username == null) {
            if (other.username != null) {
                return false;
            }
        } else if (!this.username.equals(other.username)) {
            return false;
        }
        return true;
    }

}