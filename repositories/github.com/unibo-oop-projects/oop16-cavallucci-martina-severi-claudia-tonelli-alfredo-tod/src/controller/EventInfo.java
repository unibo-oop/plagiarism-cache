package controller;

import java.util.function.Predicate;
/**
 * 
 * event info.
 *
 */
public enum EventInfo {
    /**
     * 
     */
    DESCRIPTION("*Description:", n->!n.isEmpty(), "Campo vuoto"),
    /**
     * 
     */
    STARTTIME("*Start time:", s->!s.isEmpty(), "Campo vuoto"),
    /**
     * 
     */
    PLACE("*Place:"),
    /**
     * 
     */
    ENDTIME("*End time:");

    private final String info;
    private final Predicate<String> validity;
    private final String errorMsg;

    EventInfo(final String s) {
        this.info = s;
        this.validity = null;
        this.errorMsg = "";
    }

    EventInfo(final String s, final Predicate<String> validity, final String err) {
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
