package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import model.Employee;
import model .EmployeeManager;
import model.EmployeeManagerImpl;
import view.EmployeesViewImpl;

/**
 * This class implements {@link EmployeeController} interface. 
 *
 */
public final class EmployeeControllerImpl implements EmployeeController {

    private final int numCol = 7;
    private final String path = System.getProperty("user.home")
                                + System.getProperty("file.separator")
                                + ".thebloomingflorist"
                                + System.getProperty("file.separator")
                                + "employee.dat";

    private final EmployeeManager employeeM;
    private EmployeesViewImpl view;
    private Optional<Employee> select;
    private static final EmployeeControllerImpl SINGLETON = new EmployeeControllerImpl();

    /**
     * This builder is used for update {@link EmployeesView} 
     * and {@link EmployeeManager}.
     * 
     */
    public EmployeeControllerImpl() {
        this.employeeM = new EmployeeManagerImpl();
        this.employeeM.setAll(this.load());
        System.out.println(this.employeeM.getAll());
        this.view = new EmployeesViewImpl(this, getEmployeeTable());
        this.select = Optional.empty();
    }

    /**
     * This method check if all {@link EmployeeInfo} are 
     * correctly inserted.
     * 
     * @param empl
     *      {@link Employee}'s info to check
     * @return {@link Boolean}
     */
    private boolean checkFields(final Map<EmployeeInfo, String> empl) {
        for (final Map.Entry<EmployeeInfo, String> e : empl.entrySet()) {
            if (!e.getKey().getValidity().test(e.getValue())) {
                this.view.showMessage(this.view, e.getKey().getErrorMsg());
                return false;
            }
        }
        return true;
    }

    /**
     * This method check if select variable contains a 
     * reference to a {@link Employee}.
     * 
     */
    private void checkIfSelection() {
        if (!this.select.isPresent()) {
            this.view.showMessage(this.view, "Nothing selected");
        }
    }

    /**
     * This method saves to file all the {@link Employee}.
     * 
     */
    private void store() {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(path)))) {
            System.out.println("this.employeeM.getAll()" + this.employeeM.getAll());
            out.writeObject(this.employeeM.getAll());
        } catch (IOException e) {
            this.view.showError(this.view, "employee.dat not found");
        }
    }

    /**
     * This method load from file all the {@link Employee} stored.
     * 
     * @return Set<{@link Employee}>
     */
    @SuppressWarnings("unchecked")
    private Set<Employee> load() {
        final Set<Employee> set = new HashSet<>();
        try (ObjectInputStream in = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(path)));) {
            System.out.println("Successfuly loaded employee.dat");
            return (Set<Employee>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("employee.dat cant't be loaded");
        }
        return set;
    }

    /**************************************************************************/
    @Override
    public void addEmployee(final Map<EmployeeInfo, String> empl) {
        try {
            final String name = empl.get(EmployeeInfo.NAME);
            final String surname = empl.get(EmployeeInfo.SURNAME);
            final String fiscalCode = empl.get(EmployeeInfo.FISCALCODE);
            final String city = empl.get(EmployeeInfo.CITY);
            final String address = empl.get(EmployeeInfo.ADDRESS);
            final String phoneNum = empl.get(EmployeeInfo.PHONENUM);
            final String mail = empl.get(EmployeeInfo.MAIL);

            final Employee emp = new Employee(name,
                    surname, fiscalCode, city, address, phoneNum, mail);

            this.employeeM.add(emp);
            this.view.resetTextFields();
            this.store();
            this.view.rebuildTable(this.getEmployeeTable());
        } catch (final IllegalArgumentException e) {
            this.view.showError(this.view, "Invalid insertion: " + e.getMessage());
        } catch (IllegalStateException e) {
            this.view.showError(this.view, "Employee already exist");
        }
    }

    @Override
    public void removeEmployee() {
        this.checkIfSelection();
            this.select.ifPresent(e-> {
                this.employeeM.remove(e);
                this.select = Optional.empty();
                this.store();
                this.view.rebuildTable(this.getEmployeeTable());
            });
    }

    @Override
    public void selectEmployee(final int pos) {
        this.select = Optional.of(this.getAllEmployee().get(pos));
        System.out.println("Debug string in EmployeeControllerImpl - selectEmployee" + select.toString());
    }

    @Override
    public void confirm(final Map<EmployeeInfo, String> empl) {
        if (this.checkFields(empl)) {
            this.addEmployee(empl);
        } else {
            System.out.println("Error in EmployeeControllerImpl");
        }
    }

    @Override
    public List<Employee> getAllEmployee() {
        System.out.println("Debug string in getAllEmployee - EmployeeControllerImpl");
        final List<Employee> list = new ArrayList<>();
        System.out.println("Print all Employee " + employeeM.getAll().toString());
        for (Employee e : employeeM.getAll()) {
            System.out.println("added " + e.toString());
            list.add(e);
        }
        return list;
    }

    @Override
    public Object[][] getEmployeeTable() {
        final List<Employee> list = this.getAllEmployee();
        final Object[][] table = new Object[list.size()][numCol];
        
        for (int i = 0; i < list.size(); i++) {
            final Employee e = list.get(i);
            System.out.println(list.get(i));
            System.out.println("" + e.toString());
            table[i][0] = e.getName();
            table[i][1] = e.getSurname();
            table[i][2] = e.getFc();
            table[i][3] = e.getCity();
            table[i][4] = e.getAddress();
            table[i][5] = e.getPhonenumber();
            table[i][6] = e.getMail();
        }
        return (Object[][]) table;
    }

    /**
     * This method returns the SINGLETON.
     * 
     * @return {@link EmployeeControllerImpl}
     */
    public static EmployeeControllerImpl getInstance() {
        return SINGLETON;
    }

    /**
     * This method returns the {@link EmployeesViewImpl}.
     * 
     * @return {EmployeesViewImpl}
     */
    public EmployeesViewImpl getEmployeeView() {
        return this.view;
    }
}
