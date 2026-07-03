package model;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * the employee of the greenhouse.
 */
public class Employee extends PersonaImpl {

    /**
     * UID generated.
     */
    private static final long serialVersionUID = -1880579353589128183L;

    /**
     * duties of employee.
     */
    public enum Duty {
        /**
         * take care of plants and flowers.
         */
        FLORIST,
        /**
         * take care of business.
         */
        MANAGER,
        /**
         * sell plants and flowers.
         */
        SELLER;
    }
    //default birthday, essendo stata tolta da un costruttore è presente una di default
    private static final int BIRTHDAY = 5;
    private static final int BIRTHMONTH = 9;
    private static final int BIRTHYEAR = 1995; 

    private Duty duty = Duty.FLORIST; //default is florist
    private int empCode;
    private String city;
    private String address;
    private String phonenumber;
    private String mail;

    //costruttore
    /**
     * 
     * @param name the name.
     * @param surname surname 
     * @param cf fiscal code
     * @param city the city of residence
     * @param address address of residence
     * @param phonenumber number of cell
     * @param mail the e-mail
     */
    public Employee(final String name, final String surname, final String cf, final String city,
                    final String address, final String phonenumber, final String mail) {
        super(name, surname, cf, LocalDate.of(BIRTHYEAR, BIRTHMONTH, BIRTHDAY));
        this.city = city;
        this.address = address;
        //controllo phone se c'è ed è sbagliato ritorna eccezione (c'è anche nel set)
        if (phonenumber != null && !Pattern.matches(Regex.getPhonenumber(), phonenumber)) {
            throw new IllegalArgumentException(Regex.getPhonenumberexeption());
        } else {
            this.phonenumber = phonenumber;
        }
        //controllo email, se c'è ed è sbagliata ritorna eccezione (c'è anche nel set)
        if (mail != null && !Pattern.matches(Regex.getEmail(), mail)) {
            throw new IllegalArgumentException(Regex.getEmailexeption());
        } else {
            this.mail = mail;
        }
    }

    //Costruttore completo
    /**
     * 
     * @param name the name.
     * @param surname surname 
     * @param cf fiscal code
     * @param city the city of residence
     * @param address address of residence
     * @param phonenumber number of cell
     * @param mail the e-mail
     * @param date the birth date
     */
    public Employee(final String name, final String surname, final String cf, final String city,
                    final String address, final String phonenumber, final String mail, final LocalDate date) {
        super(name, surname, cf, date);
        this.city = city;
        this.address = address;
        //controllo phone se c'è ed è sbagliato ritorna eccezione (c'è anche nel set)
        if (phonenumber != null && !Pattern.matches(Regex.getPhonenumber(), phonenumber)) {
            throw new IllegalArgumentException(Regex.getPhonenumberexeption());
        } else {
            this.phonenumber = phonenumber;
        }
        //controllo email, se c'è ed è sbagliata ritorna eccezione (c'è anche nel set)
        if (mail != null && !Pattern.matches(Regex.getEmail(), mail)) {
            throw new IllegalArgumentException(Regex.getEmailexeption());
        } else {
            this.mail = mail;
        }
    }


    /**
     * 
     * @return the employee code
     */
    public int getEmpCode() {
        return empCode;
    }

    /**
     * 
     * @return the duty of the employee
     */
    public Duty getDuty() {
        return duty;
    }

    /**
     * 
     * @param code the employee code 
     */
    public void setEmpCode(final int code) {
        this.empCode = code;
    }

    /**
     * 
     * @param duty the duty of the employee
     */
    public void setDuty(final Duty duty) {
        this.duty = duty;
    }

    /**
     * gets the city.
     * @return the city of residence
     */
    public String getCity() {
        return city;
    }

    /**
     * 
     * @param city the city of residence
     */
    public void setCity(final String city) {
        this.city = city;
    }

    /**
     * 
     * @return the address of residence
     */
    public String getAddress() {
        return address;
    }

    /**
     * 
     * @param address the address of residence
     */
    public void setAddress(final String address) {
        this.address = address;
    }

    /**
     * 
     * @return the phone number
     */
    public String getPhonenumber() {
        return phonenumber;
    }

    /**
     * 
     * @param phonenumber the phone number
     */
    public void setPhonenumber(final String phonenumber) {
        //controllo usando RegEx (Regular expressions)
        //la find restituirebbe true se la stringa è scorretta ma c'è una sottostringa corretta
        if (Pattern.matches("\\d*", phonenumber)) { // \d =tutti i caratteri numerici [0-9], * =più di 0 volte
            this.phonenumber = phonenumber;
        } else {
            throw new IllegalArgumentException("Invalid phone number, only numbers permitted");
        }
    }

    /**
     * 
     * @return the email
     */
    public String getMail() {
        return mail;
    }

    /**
     * 
     * @param mail the email
     */
    public void setMail(final String mail) {
        //stesso controllo di phone con le regex
        if (Pattern.matches("[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}", mail)) {
            this.mail = mail;
        } else {
            throw new IllegalArgumentException("Invalid email, only string+@+string+.+2or3or4letters");
        }
    }
}
