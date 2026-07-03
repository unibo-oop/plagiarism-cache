package model;

import java.time.LocalDate;

/**
 * builder for employee.
 */
public class EmployeeBuilder {

    private String newname;
    private String newsurname;
    private String newcf;
    private String newcity;
    private String newaddress;
    private String newphonenumber;
    private String newmail;
    private LocalDate newdate;


    /**
     * 
     * @param n the name of the employee.
     * @return this
     */
    public EmployeeBuilder name(final String n) {
        this.newname = n;
        return this;
    }

    /**
     * 
     * @param sn the surname of the employee.
     * @return this
     */
    public EmployeeBuilder surname(final String sn) {
        this.newsurname = sn;
        return this;
    }

    /**
     * 
     * @param cf the fiscal code of the employee.
     * @return this
     */
    public EmployeeBuilder cf(final String cf) {
        this.newcf = cf;
        return this;
    }

    /**
     * 
     * @param city the city of the employee.
     * @return this
     */
    public EmployeeBuilder city(final String city) {
        this.newcity = city;
        return this;
    }

    /**
     * 
     * @param address the address of the employee.
     * @return this
     */
    public EmployeeBuilder address(final String address) {
        this.newaddress = address;
        return this;
    }


    /**
     * 
     * @param phonenumber the phone number of the employee.
     * @return this
     */
    public EmployeeBuilder phonenumber(final String phonenumber) {
        this.newphonenumber = phonenumber;
        return this;
    }


    /**
     * 
     * @param mail the mail of the employee.
     * @return this
     */
    public EmployeeBuilder mail(final String mail) {
        this.newmail = mail;
        return this;
    }

    /**
     * 
     * @param date the birth date
     * @return this
     */
    public EmployeeBuilder date(final LocalDate date) {
        this.newdate = date;
        return this;
    }


    /**
     *  build for employee.
     * @return employee
     */
    public Employee build() {
        //controllo NULL POINTER EXEPTION nel caso di due costruttori
        if (this.newdate != null) {
            return new Employee(this.newname, this.newsurname, this.newcf, this.newcity,
                    this.newaddress, this.newphonenumber, this.newmail, this.newdate);
        } else {
            return new Employee(this.newname, this.newsurname, this.newcf, this.newcity,
                    this.newaddress, this.newphonenumber, this.newmail);
        }
    }

}