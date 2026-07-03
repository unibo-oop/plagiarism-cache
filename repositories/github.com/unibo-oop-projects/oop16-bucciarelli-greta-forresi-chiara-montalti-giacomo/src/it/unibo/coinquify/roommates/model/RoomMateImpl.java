package it.unibo.coinquify.roommates.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import it.unibo.coinquify.balance.model.Debt;
import it.unibo.coinquify.balance.model.Lending;
import it.unibo.coinquify.home.Room;
import it.unibo.coinquify.mansionsmng.model.Mansion;

/**
 * implementation of room mate.
 */
public class RoomMateImpl implements RoomMate, Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 2646727699299292304L;
    private String name;
    private String surname;
    private String fiscalCode;
    private Room room;
    private Date birthDay;
    private final List<Mansion> mansions;
    private String phoneNumber;
    private final List<Debt> listDebts;
    private final List<Lending> listLending;

    /**
     * 
     * @param name
     *            of room mate
     * @param surname
     *            of room mate
     * @param fiscalCode
     *            of room mate
     * @param room
     *            of room mate
     * @param phoneNumber
     *            of room mate
     * @param birthDay
     *            of room mate
     */
    public RoomMateImpl(final String name, final String surname, final String fiscalCode, final Room room,
            final String phoneNumber, final Date birthDay) {
        this.name = name;
        this.surname = surname;
        this.fiscalCode = fiscalCode;
        this.mansions = new ArrayList<>();
        this.room = room;
        this.phoneNumber = phoneNumber;
        this.birthDay = birthDay;
        this.listDebts = new ArrayList<>();
        this.listLending = new ArrayList<>();
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
    public List<Mansion> getMansions() {
        return Collections.unmodifiableList(this.mansions);
    }

    @Override
    public Room getRoom() {
        return this.room;
    }

    @Override
    public void addMansion(final Mansion mansion) {
        this.mansions.add(mansion);
    }

    @Override
    public String toString() {
        return this.getName() + " " + this.getSurname();
    }

    @Override
    public void deleteMansion(final Mansion mansion) {
        this.mansions.remove(mansion);
    }

    @Override
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    @Override
    public Date getBirthDay() {
        return this.birthDay;
    }

    @Override
    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public void setFiscalCode(final String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    @Override
    public void setSurname(final String surname) {
        this.surname = surname;
    }

    @Override
    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void setRoom(final Room room) {
        this.room = room;
    }

    @Override
    public void setBirthDay(final Date birthDay) {
        this.birthDay = birthDay;
    }

    @Override
    public boolean equals(final Object obj) {
        return obj != null && this.getName().equals(((RoomMate) obj).getName())
                && this.getSurname().equals(((RoomMate) obj).getSurname())
                && this.getFiscalCode().equals(((RoomMate) obj).getFiscalCode())
                && this.getPhoneNumber().equals(((RoomMate) obj).getPhoneNumber())
                && this.getRoom().equals(((RoomMate) obj).getRoom())
                // && this.getMansions().equals(((RoomMate) obj).getMansions())
                && this.getBirthDay().equals(((RoomMate) obj).getBirthDay());
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode() + this.getSurname().hashCode() + this.getFiscalCode().hashCode()
                + this.getPhoneNumber().hashCode() + this.getRoom().hashCode() + this.getBirthDay().hashCode()
                + this.getMansions().hashCode() + 1;
    }

    @Override
    public List<Debt> getListDebt() {
        return this.listDebts;
    }

    @Override
    public void addDebt(final Debt debt) {
        this.listDebts.add(debt);
    }

    @Override
    public void removeDebt(final Debt debt) {
        this.listDebts.remove(debt);
    }

    @Override
    public void addLending(final Lending lending) {
       this.listLending.add(lending);

    }

    @Override
    public void removeLending(final Lending lending) {
        this.listLending.remove(lending);

    }

    @Override
    public List<Lending> getListLending() {
        return this.listLending;
    }
}
