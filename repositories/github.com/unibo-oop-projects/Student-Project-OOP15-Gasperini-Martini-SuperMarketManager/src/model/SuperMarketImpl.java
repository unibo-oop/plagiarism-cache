package model;

import java.util.ArrayList;
import java.io.*;



/**
 * @author Marco Martini
 * @author Andrea Gasperini
 */

public class SuperMarketImpl implements SuperMarket, Serializable {

	private static final long serialVersionUID = 1L;
	private final ArrayList<Department> departments;

	public SuperMarketImpl() {

		this.departments = getListDepartmentFile();

	};

	public void addDepartment(Department department) {

		this.departments.add(department);
		insertDepartmentFile();

	}

	public int getNumberDepartment() {

		return departments.size();

	}

	public ArrayList<Department> getListDepartment() {

		return departments;

	}

	public void deleteDepartment(Department department) {

		this.departments.remove(department);

	}

	public void insertDepartmentFile() {

		try {

			FileOutputStream stream = new FileOutputStream("Department.dat");

			ObjectOutputStream osStream = new ObjectOutputStream(stream);

			osStream.writeObject(departments);

			osStream.flush();

			osStream.close();

		} catch (Exception e) {

			System.out.println("I/O errore");

		}
	}

	public ArrayList<Department> getListDepartmentFile() {

		try {

			FileInputStream stream = new FileInputStream("Department.dat");

			ObjectInputStream osStream = new ObjectInputStream(stream);

			@SuppressWarnings("unchecked")
			ArrayList<Department> departmentFile = (ArrayList<Department>) osStream.readObject();

			osStream.close();

			return departmentFile;

		} catch (Exception e) {

			System.out.println("I/O errore di stampa");

		}

		ArrayList<Department> arrayEmpty = new ArrayList<Department>();
		return arrayEmpty;

	}

	public boolean logIn(String username, String password) {

		try {

			FileReader read = new FileReader("User.txt");

			@SuppressWarnings("resource")
			BufferedReader bufferRead = new BufferedReader(read);

			String line;

			while ((line = bufferRead.readLine()) != null) {

				String[] a = line.split(" ");

				for (int i = 0; i < a.length; i++) {

					if (username.equals(a[i]) && password.equals(a[i + 1])) {

						return true;

					}

				}

			}

		} catch (Exception e) {

		}
		return false;

	}

}
