package it.unibo.coinquify.telephonebook.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

import it.unibo.coinquify.utils.PhoneNumberPresentException;
import it.unibo.coinquify.utils.PhoneUtils;

/**
 * Contact Implementation.
 */
public class ContactImpl implements Contact, Serializable {

    private static final String DEFAULTSTRING = "";

    private static final long serialVersionUID = -6922110221313970443L;
    private String name;
    private String surname;
    private String fiscalCode;
    private String phoneNumber;
    private Date birthday;
    private String address;
    private String email;
    private String homePhoneNumber;
    private String workPhoneNumber;

    /**
     * Contact Constructor.
     * @param name of the contact.
     * @param surname of the contact
     * @param fiscalCode can be a empty string or only a 16 char string
     * @param phoneNumber can be only a 15 char string
     * @param birthday Optional of a date that rappresent the birthday of contact
     * @param address of contact, where he/she lives
     * @param email of contact, internet mailbox address
     * @param homeNumber the home phone number of contact
     * @param workNumber the office phone number of contact
     * @throws IllegalArgumentException if name or surname or phoneNumber are empty, or fiscalCode or phoneNumber are illegal arguments
     * @throws PhoneNumberPresentException if the phonebook already contains the phoneNumber passed
     */
    public ContactImpl(final String name, final String surname, final String fiscalCode, final String phoneNumber,
            final Optional<Date> birthday, final String address, final String email, final String homeNumber,
            final String workNumber) throws IllegalArgumentException, PhoneNumberPresentException {
        this.editContact(name, surname, fiscalCode, phoneNumber, birthday, address, email, homeNumber, workNumber);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getSurname() {
        return this.surname;
    }

    @Override
    public String getFiscalCode() {
        return this.fiscalCode;
    }

    @Override
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    @Override
    public Optional<Date> getBirthday() {
        if (this.birthday == null) {
            return Optional.empty();
        } else {
            return Optional.of(this.birthday);
        }
    }

    @Override
    public String getAddress() {
        return this.address;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getHomePhoneNumber() {
        return this.homePhoneNumber;
    }

    @Override
    public String getWorkPhoneNumber() {
        return this.workPhoneNumber;
    }

    private void setName(final String name) {
        if (name == null) {
            this.name = DEFAULTSTRING;
        } else {
            this.name = name;
        }
    }

    private void setSurname(final String surname) {
        if (surname == null) {
            this.surname = DEFAULTSTRING;
        } else {
            this.surname = surname;
        }
    }

    private void setFiscalCode(final String fiscalCode) {
        if (fiscalCode == null) {
            this.fiscalCode = DEFAULTSTRING;
        } else {
            this.fiscalCode = fiscalCode;
        }
    }

    private void setPhoneNumber(final String phoneNumber) {
        if (phoneNumber == null) {
            this.phoneNumber = DEFAULTSTRING;
        } else {
            this.phoneNumber = phoneNumber;
        }
    }

    private void setBirthday(final Optional<Date> birthday) {
        if (birthday.isPresent()) {
            this.birthday = birthday.get();
        } else {
            this.birthday = null;
        }
    }

    private void setAddress(final String address) {
        if (address == null) {
            this.address = DEFAULTSTRING;
        } else {
            this.address = address;
        }
    }

    private void setEmail(final String email) {
        if (email == null) {
            this.email = DEFAULTSTRING;
        } else {
            this.email = email;
        }
    }

    private void setHomePhoneNumber(final String homePhoneNumber) {
        if (homePhoneNumber == null) {
            this.homePhoneNumber = DEFAULTSTRING;
        } else {
            this.homePhoneNumber = homePhoneNumber;
        }
    }

    private void setWorkPhoneNumber(final String workPhoneNumber) {
        if (workPhoneNumber == null) {
            this.workPhoneNumber = DEFAULTSTRING;
        } else {
            this.workPhoneNumber = workPhoneNumber;
        }
    }

    @Override
    public boolean isContactValid() {
        return PhoneUtils.isArgumentValid(this.getName(), this.getSurname(), this.getFiscalCode(), this.getPhoneNumber());
    }

    @Override
    @SuppressWarnings("all")
    public final void editContact(final String name, final String surname, final String fiscalCode,
            final String phoneNumber, final Optional<Date> birthday, final String address, final String email,
            final String homeNumber, final String workNumber) {
        if (!PhoneUtils.isArgumentValid(name, surname, fiscalCode, phoneNumber)) {
            throw new IllegalArgumentException();
        }
        this.setName(name);
        this.setSurname(surname);
        this.setFiscalCode(fiscalCode);
        this.setPhoneNumber(phoneNumber);
        this.setBirthday(birthday);
        this.setAddress(address);
        this.setEmail(email);
        this.setHomePhoneNumber(homeNumber);
        this.setWorkPhoneNumber(workNumber);
    }
}
