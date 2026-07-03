package model;

import java.io.Serializable;
import java.time.LocalDate;

import model.enumerations.ContractType;
import model.interfaces.Employee;
import model.interfaces.Person;

public class EmployeeImpl implements Employee, Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1580055083451140856L;
	private final Person person;
	private double salary;
	private LocalDate signingContract;
	private ContractType contracType;


	public EmployeeImpl(Person person, double salary, LocalDate signingContract, ContractType contracType) {
		super();
		this.person = person;
		this.salary = salary;
		this.signingContract = signingContract;
		this.contracType = contracType;
	}


	@Override
    public double getSalary() {
		return salary;
	}


	@Override
    public void setSalary(double salary) {
		this.salary = salary;
	}


	@Override
    public LocalDate getSigningContract() {
		return signingContract;
	}


	@Override
    public void setSigningContract(LocalDate signingContract) {
		this.signingContract = signingContract;
	}


	@Override
    public ContractType getContracType() {
		return contracType;
	}


	@Override
    public void setContracType(ContractType contracType) {
		this.contracType = contracType;
	}


	@Override
    public Person getPerson() {
		return person;
	}





}
