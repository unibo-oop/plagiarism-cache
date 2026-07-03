package model;

/**
 * 
 * This is an implementation of {@link model.Owner}.
 * 
 */
public class OwnerImpl implements Owner {

    private String ownerPassword;
    private String staffPassword;

    @Override
    public String getOwnerPassword() {
        return ownerPassword;
    }

    @Override
    public void setOwnerPassword(final String ownerPassword) {
        this.ownerPassword = ownerPassword;
    }

    @Override
    public String getStaffPassword() {
        return staffPassword;
    }

    @Override
    public void setStaffPassword(final String staffPassword) {
        this.staffPassword = staffPassword;
    }

}
