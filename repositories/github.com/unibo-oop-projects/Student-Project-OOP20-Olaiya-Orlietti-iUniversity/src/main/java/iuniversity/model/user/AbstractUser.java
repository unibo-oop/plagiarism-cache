package iuniversity.model.user;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class AbstractUser implements User, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * Is the abstract user class, it is subsequently extended to student, professor and admin
     */
    private String name;
    private String lastName;
    private String username;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String address;
    private int id;
    
    public AbstractUser() {
    }
    
    public AbstractUser(String name, String lastName, String username, LocalDate dateOfBirth, Gender gender,
            String address, int id) {
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.id = id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
    
    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public Gender getGender() {
        return this.gender;
    }

    public String getAddress() {
        return this.address;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return name + " " + lastName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
        result = prime * result + ((gender == null) ? 0 : gender.hashCode());
        result = prime * result + id;
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
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
        final AbstractUser other = (AbstractUser) obj;
        if (address == null) {
            if (other.address != null) {
                return false;
            }
        } else if (!address.equals(other.address)) {
            return false;
        }
        if (dateOfBirth == null) {
            if (other.dateOfBirth != null) {
                return false;
            }
        } else if (!dateOfBirth.equals(other.dateOfBirth)) {
            return false;
        }
        if (gender != other.gender) {
            return false;
        }
        if (id != other.id) {
            return false;
        }
        if (lastName == null) {
            if (other.lastName != null) {
                return false;
            }
        } else if (!lastName.equals(other.lastName)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (username == null) {
            if (other.username != null) {
                return false;
            }
        } else if (!username.equals(other.username)) {
            return false;
        }
        return true;
    }
    
    
}
