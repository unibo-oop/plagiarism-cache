package model.interfaces;

import java.time.LocalDate;

import model.enumerations.ContractType;

public interface Employee {
	
	/**
	 * 
	 * @return Salary of Employee
	 */
	
	public double getSalary();
	
	/**
	 * Set new salary's value 
	 * @param salary of the Employee
	 */
	
	public void setSalary(double salary);
	
	/**
	 * 
	 * @return Date in which contract is signed
	 */
	
	public LocalDate getSigningContract();
	
	/**
	 * Set the date of the signing contract
	 * @param signingContract date in which contract is signed
	 */
	
	public void setSigningContract(LocalDate signingContract);
	
	/**
	 * 
	 * @return type of Contract 
	 */
	
	public ContractType getContracType();
	
	/**
	 * set the contract type
	 * @param contracType to be set
	 */
	
	public void setContracType(ContractType contracType);
	
	/**
	 * 
	 * @return a Person object contains the personal data
	 */
	
	public Person getPerson();

}
