package utility;

import java.util.regex.Pattern;

/**
 * This class validates input data
 * 
 * @author Vanessa Di Biasi
 *
 */

public class InputValidator {

    private String nameAndNum = "^[A-Za-z0-9-_./\\\\',\\s]+$";
    private String name = "^[A-Za-z-.'\\s]+$";
    private String phone = "^[0-9]{3,12}$";
    private String email = "^[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
    private String CF = "^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$";
    private String PIVA = "^[0-9]{11}$";
    private String CAP = "^[0-9]{5}$";
    private String date = "^((?:19|20)[0-9][0-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$";
    private String time = "^[0-2][0-3]:[0-5][0-9]$";
    /**
     * Check if the parameter is in alphanumeric format
     * @param txtField
     * @return
     */
    public Boolean isNameAndNum(String txtField) {
        return Pattern.matches(this.nameAndNum, txtField);
    }
    /**
     * Check if the parameter is in alphabetic format
     * @param txtField
     * @return
     */
    public Boolean isName(String txtField) {
        return Pattern.matches(name, txtField);
    }
    
    /**
     * Check if the parameter is in telephone format
     * @param txtField
     * @return
     */
    public Boolean isPhone(String txtField) {
        return Pattern.matches(phone, txtField);
    }
    
    /**
     * Check if the parameter is in email format
     * @param txtField
     * @return
     */
    public Boolean isEmail(String txtField) {
        return Pattern.matches(email, txtField);
    }
    
    /**
     * Check if the parameter is a integer number
     * @param txtField
     * @return
     */
    public Boolean isInteger(String txtField) {
        try {
            Integer.parseInt(txtField);
            return true;
        } catch (NullPointerException | NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * Check if the parameter is a double number
     * @param txtField
     * @return
     */
    public Boolean isDouble(String txtField) {
        try {
            Double.parseDouble(txtField);
            return true;
        } catch (NullPointerException | NumberFormatException e) {
            return false;
        }
    }
    
    public Boolean isCFPIVA(String txtField) {
        return (Pattern.matches(this.CF, txtField) || Pattern.matches(this.PIVA, txtField));
    }
    
    public Boolean isCAP(String txtField) {
        return Pattern.matches(this.CAP, txtField);
    }
}
