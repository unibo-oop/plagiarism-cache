package model;

/**
 * class created for regular expressions used in model.
 * if a change must be done in restrictions can be made here and not in the controls all around the code.
 * String controls.
 */
final class Regex {

    /**
     * NAME
     * name only alphanumeric+"_"+"-" length minimum 2 and max 14
     */
    private static final String NAME = "^[a-zA-Z0-9_-]{2,14}$";
    private static final String NAMEEXEPTION = "Invalid name, only alfanumeric from 2 to 14 permitted";

    /**
     * EMAIL
     * email only string+@+string+.+2or3or4letters
     */
    private static final String EMAIL = "[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
    private static final String EMAILEXEPTION = "Invalid email, only string+@+string+.+2or3or4letters permitted";

    /**
     * PHONE
     * all numbers [0-9], more than 0
     */
    private static final String PHONENUMBER = "\\d*";
    private static final String PHONENUMBEREXEPTION = "Invalid phone number, only numbers permitted";

    /**
     * FISCAL CODE
     * only alphanumeric a-z,A-Z,0-9
     */
    private static final String FISCALCODE = "\\w*";
    //private static final String FISCALCODEAGGRESSIVE = "[a-zA-Z]{6}\d\d[a-zA-Z]\d\d[a-zA-Z]\d\d\d[a-zA-Z]";
    private static final String FISCALCODEEXEPTION = "Invalid fiscal code, only alphanumeric a-z,A-Z,0-9 permitted";

    /**
     * BLANK LINE
     * only blank line 
     */
    private static final String BLANKLINE = "^$";
    private static final String BLANKLINEEXEPTION = "Invalid blank line, no void permitted";

    //FUNCTIONS
    /**
     * the restrictions for the name/surname.
     * @return the string with the restrictions
     */
    public static String getName() {
        return NAME;
    }

    public static String getNameexeption() {
        return NAMEEXEPTION;
    }

    /**
     * the restrictions for the email.
     * @return the string with the restrictions
     */
    public static String getEmail() {
        return EMAIL;
    }

    public static String getEmailexeption() {
        return EMAILEXEPTION;
    }

    /**
     * the restrictions for the phone number.
     * @return the string with the restrictions
     */
    public static String getPhonenumber() {
        return PHONENUMBER;
    }

    public static String getPhonenumberexeption() {
        return PHONENUMBEREXEPTION;
    }

    /**
     * the restrictions for the fiscal code.
     * @return the string with the restrictions
     */
    public static String getFiscalcode() {
        return FISCALCODE;
    }

    public static String getFiscalcodeexeption() {
        return FISCALCODEEXEPTION;
    }

    /**
     * the restrictions for a blank line.
     * used if a blank line is insert
     * @return the string with the restrictions
     */
    public static String getBlankline() {
        return BLANKLINE;
    }

    public static String getBlanklineexeption() {
        return BLANKLINEEXEPTION;
    }

    private Regex() {
        //no call
    }
}
