package controller;

import java.util.function.Predicate;
/**
 * 
 * enum for support controller for insert contacts.
 *
 */
public enum ContactInfo {
    /**
     * 
     */
    NAME("*Name:", n->!n.isEmpty(), "Campo vuoto"),
    /**
     * 
     */
    SURNAME("*Surname:", s->!s.isEmpty(), "Campo vuoto"),
    /**
     * 
     */
    ADDRESS("*Address:"),
    /**
     * 
     */
    PHONENUMBER("*Phone Number:"),
    /**
     * 
     */
    EMAIL("*Email:");

    private final String info;
    private final Predicate<String> validity;
    private final String errorMsg;

    ContactInfo(final String s) {
        this.info = s;
        this.validity = null;
        this.errorMsg = "";
    }

    ContactInfo(final String s, final Predicate<String> validity, final String err) {
        this.info = s;
        this.validity = validity;
        this.errorMsg = err;
    }
    /**
     * 
     * @return info
     * info
     */
    public String getInfo() {
        return info;
    }
    /**
     * 
     * @return errorMsg
     * error
     */
    public Predicate<String> getValidity() {
        return validity;
    }
    /**
     * 
     * @return errorMsg
     * error message
     */
    public String getErrorMsg() {
        return errorMsg;
    }

}
