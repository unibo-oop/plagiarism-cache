package it.unibo.coinquify.roommates.model;

import java.util.Date;
import java.util.List;

import it.unibo.coinquify.balance.model.Debt;
import it.unibo.coinquify.balance.model.Lending;
import it.unibo.coinquify.home.Room;
import it.unibo.coinquify.mansionsmng.model.Mansion;

/**
 * Room mate inferface.
 */
public interface RoomMate extends Person {
    /**
     * 
     * @return the list of a room mate mansions
     */
    List<Mansion> getMansions();

    /**
     * Add new mansion.
     * 
     * @param mansion
     *            to add
     */
    void addMansion(Mansion mansion);

    /**
     * Delete mansion.
     * 
     * @param mansion
     *            to delete
     */
    void deleteMansion(Mansion mansion);

    /**
     * 
     * @return the RoomMate's room
     */
    Room getRoom();

    /**
     * Set RoomMate name.
     * 
     * @param name
     *            to set
     */
    void setName(String name);

    /**
     * Set RoomMate fiscal code.
     * 
     * @param fiscalCode
     *            to set
     */
    void setFiscalCode(String fiscalCode);

    /**
     * Set RoomMate surname.
     * 
     * @param surname
     *            to set
     */
    void setSurname(String surname);

    /**
     * Set RoomMate phoneNumber.
     * 
     * @param phoneNumber
     *            to set
     */
    void setPhoneNumber(String phoneNumber);

    /**
     * Set RoomMate room.
     * 
     * @param room
     *            to set
     */
    void setRoom(Room room);

    /**
     * Set RoomMate birthDay.
     * 
     * @param birthDay
     *            to set
     */
    void setBirthDay(Date birthDay);

    /**
     * 
     * @return RoomMate birthDay.
     */
    Date getBirthDay();

    /**
     * @return the List of the debts 
     */
    List<Debt> getListDebt();

    /**
     * add a new debt.
     * 
     * @param debt 
     */
    void addDebt(Debt debt);

    /**
     * remove debt.
     * 
     * @param debt 
     */
    void removeDebt(Debt debt);

    /**
     * Add new Lending.
     * @param lending 
     */
    void addLending(Lending lending);

    /**
     * Remove a Lending.
     * @param lending 
     */
    void removeLending(Lending lending);

    /**
     * @return the list of Lending
     */
    List<Lending> getListLending();
}
