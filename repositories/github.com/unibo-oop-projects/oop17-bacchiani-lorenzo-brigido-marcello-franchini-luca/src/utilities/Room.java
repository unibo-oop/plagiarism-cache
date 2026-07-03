package utilities;

import java.io.Serializable;

public enum Room implements Serializable  {

	R01C1(Department.Cardiologia, 1),
	R01C2(Department.Cardiologia, 2),
	R01C3(Department.Cardiologia, 3),
	R01C4(Department.Cardiologia, 4),
	R01C5(Department.Cardiologia, 5),
	R01C6(Department.Cardiologia, 6),
	R02C1(Department.TerapiaIntensiva, 1),
	R02C2(Department.TerapiaIntensiva, 2),
	R02C3(Department.TerapiaIntensiva, 3),
	R02C4(Department.TerapiaIntensiva, 4),
	R02C5(Department.TerapiaIntensiva, 5),
	R03C1(Department.Neurologia, 1),
	R03C2(Department.Neurologia, 2),
	R03C3(Department.Neurologia, 3),
	R03C4(Department.Neurologia, 4),
	R03C5(Department.Neurologia, 5),
	R04C1(Department.Oncologia, 1),
	R04C2(Department.Oncologia, 2),
	R04C3(Department.Oncologia, 3),
	R04C4(Department.Oncologia, 4),
	R04C5(Department.Oncologia, 5),
	R05C1(Department.Psichiatria, 1),
	R05C2(Department.Psichiatria, 2),
	R05C3(Department.Psichiatria, 3),
	R05C4(Department.Psichiatria, 4),
	R05C5(Department.Psichiatria, 5),
	R06C1(Department.Oculistica, 1),
	R06C2(Department.Oculistica, 2),
	R06C3(Department.Oculistica, 3),
	R06C4(Department.Oculistica, 4),
	R06C5(Department.Oculistica, 5),
	R07C1(Department.Ginecologia, 1),
	R07C2(Department.Ginecologia, 2),
	R07C3(Department.Ginecologia, 3),
	R07C4(Department.Ginecologia, 4),
	R07C5(Department.Ginecologia, 5),
	R08C1(Department.Riabilitazione, 1),
	R08C2(Department.Riabilitazione, 2),
	R08C3(Department.Riabilitazione, 3),
	R08C4(Department.Riabilitazione, 4),
	R08C5(Department.Riabilitazione, 5),
	R09C1(Department.Ortopedia, 1),
	R09C2(Department.Ortopedia, 2),
	R09C3(Department.Ortopedia, 3),
	R09C4(Department.Ortopedia, 4),
	R09C5(Department.Ortopedia, 5),
	R10C1(Department.Gastroenterologia, 1),
	R10C2(Department.Gastroenterologia, 2),
	R10C3(Department.Gastroenterologia, 3),
	R10C4(Department.Gastroenterologia, 4),
	R10C5(Department.Gastroenterologia, 5),
	R11C1(Department.Geriatria, 1),
	R11C2(Department.Geriatria, 2),
	R11C3(Department.Geriatria, 3),
	R11C4(Department.Geriatria, 4),
	R11C5(Department.Geriatria, 5),
	R12C1(Department.Pediatria, 1),
	R12C2(Department.Pediatria, 2),
	R12C3(Department.Pediatria, 3),
	R12C4(Department.Pediatria, 4),
	R12C5(Department.Pediatria, 5);

	private Department rep;
	private int num;

	private Room(final Department rep, final int num) {
		this.rep = rep;
		this.num = num;

	}

	public Department getDepartment() {

		return this.rep;
	}

	public int getNumber() {
		return this.num;
	}

}
