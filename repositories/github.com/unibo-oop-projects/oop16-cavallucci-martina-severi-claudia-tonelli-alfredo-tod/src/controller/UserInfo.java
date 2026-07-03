package controller;

import java.util.function.Predicate;

/**
 * 
 * enum for support controller for the login.
 *
 */
public enum UserInfo {
    /**
     * 
     */
    USERNAME("*Username:", n->!n.isEmpty(), "Nome vuoto"),
    /**
     * 
     */
    PASSWORD("Password:", n->!n.isEmpty() && n.length() > 5, "Password non valida");
/*    private static final Path PATH_TO_FILE = Paths.get(System.getProperty("user.home)"
            + System.getProperty("file.separator") + ".tod"
            + System.getProperty("file.separator")
            + "user_list.txt"));*/
    private final String info;
    private final Predicate<String> validity;
    private final String errorMsg;

    UserInfo(final String s, final Predicate<String> validity, final String err) {
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
