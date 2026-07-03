
package controller;

import java.util.function.Predicate;

/**
 * Enumeration that contains all the {@link Employee} possible field.
 *
 */
public enum EmployeeInfo {
    /**
     * Field that contain the Name of the {@link Employee}.
     */
    NAME("Name", n->!n.isEmpty() && !n.matches(".*\\d+.*"), "error: wrong name"),
    /**
     * Field that contains the Surname of the {@link Employee}.
     */
    SURNAME("Surname", s->!s.isEmpty() && !s.matches(".*\\d+.*"), "error: wrong surname"),
    /**
     * Field that contains the Fiscal code of the {@link Employee}.
     */
    FISCALCODE("Fiscal code", fc->fc.matches(".*\\d+.*[0-9]*") && fc.length() == 16, "error: wrong fiscal codess"),
    /**
     * Field that contains the City of residence of the {@link Employee}.
     */
    CITY("City", c->!c.isEmpty(), "error: wrong city"),
    /**
     * Field that contains the Address of the {@link Employee}.
     */
    ADDRESS("Address", a->!a.isEmpty(), "error: wrong address"),
    /**
     * Field that contains the Phone number of the {@link Employee}.
     */
    PHONENUM("Phone number", pn-> { 
        return pn.isEmpty() ? true : pn.chars().allMatch(c->Character.isDigit(c));
    }, "error: wrong phone number"),
    /**
     * Field that contains the email of the {@link Employee}.
     */
    MAIL("E-Mail", em-> {
        return em.isEmpty() ? true : em.contains("@");
    }, "error: wrong E-Mail");

    private final String info;
    private final Predicate<String> validity;
    private final String errorMsg;

    EmployeeInfo(final String info, final Predicate<String> validity, 
            final String errorMsg) {
        this.info = info;
        this.validity = validity;
        this.errorMsg = errorMsg;
    }

    /**
     * This getter return {@link Employee} info.
     * 
     * @return String 
     */
    public String getInfo() {
        return this.info;
    }

    /**
     * This getter return the validity of {@link Employee} input.
     * 
     * @return Predicate<String> 
     */
     public Predicate<String> getValidity() {
        return this.validity;
     }

     /**
      * This getter return a error message.
      * 
      * @return String
      */
     public String getErrorMsg() {
         return this.errorMsg;
     }
}