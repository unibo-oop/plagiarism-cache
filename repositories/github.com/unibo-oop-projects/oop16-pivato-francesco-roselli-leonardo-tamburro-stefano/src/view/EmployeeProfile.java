package view;


import java.time.LocalDate;

import model.enumerations.ContractType;
import model.interfaces.Employee;

public interface EmployeeProfile {
	
	void setFields(Employee employee);

    /**
     * @return name, null if the field is empty
     */
    String getName();

    /**
     * @return surname, null if the field is empty
     */
    String getSurname();

    /**
     * @return birth date, null if the field is empty
     */
    LocalDate getBirthdate();

    /**
     * @return phone number, null if the field is empty
     */
    String getPhoneNumber();

    /**
     * @return email, null if the field is empty
     */
    String getEmail();

    /**
     * @return tax code, null if the field is empty
     */
    String getTaxCode();

    /**
     * @return salary, null if the field is empty
     */
    Double getSalary();

    /**
     * @return contract type, null if the field is empty
     */
    ContractType getContractType();

    /**
     * @return sign in date, null if the field is empty
     */
    LocalDate getSigninDate();

}
