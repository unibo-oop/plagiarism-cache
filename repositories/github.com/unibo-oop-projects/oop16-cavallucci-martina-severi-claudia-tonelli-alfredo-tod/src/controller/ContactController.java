package controller;

import java.util.Map;

import view.ContactView;
/**
 * 
 * contact controller interface.
 *
 */
public interface ContactController {
    /**
     * 
     * @return contactView
     * contact view
     */
    ContactView getContactView();
    /**
     * when you confirm.
     * @param map
     * map
     * @param data
     * data
     */
    void confirmChanges(Map<ContactInfo, String> map, org.joda.time.LocalDate data);
    /**
     * when you modify.
     * @param map
     * map
     * @param data
     * data
     */
    void modifyContact(Map<ContactInfo, String> map, org.joda.time.LocalDate data);
    /**
     * when you remove.
     */
    void removeContact();

    /**
     * get contact table.
     * 
     * @return contactTable
     * contact table
     */
    Object[][] getContactTable();
    /**
     * select contact.
     * @param index
     * index
     */
    void selectContact(int index);
}