package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * manager for the employees.
 */
public class EmployeeManagerImpl implements EmployeeManager, Serializable {

    /**
     * UID generated
     */
    private static final long serialVersionUID = 7516890639802876731L;

    private Set<Employee> setdipendenti;

    /**
     * new set.
     */
    public EmployeeManagerImpl() {
        this.setdipendenti = new HashSet<>();
    }

    @Override
    public boolean add(final Employee emp) {
        return this.setdipendenti.add(emp);
    }

    @Override
    public boolean remove(final Employee emp) {
        return this.setdipendenti.remove(emp);
    }

    @Override
    public Set<Employee> getAll() {
        return setdipendenti;
    }

    @Override
    public boolean setAll(final Set<Employee> set) {
        this.setdipendenti = set;
        return true;
    }

    @Override
    public Employee getEmployee(final Employee emp) {
        //uso un iteratore del set
        for (final Iterator<Employee> iteratore = setdipendenti.iterator(); iteratore.hasNext();) {
            final Employee e = iteratore.next();
            if (e.equals(emp)) {
                return e;
            }
        }
        throw new IllegalArgumentException("no employee requested found");
    }

    @Override
    public int numEmp() {
        return this.setdipendenti.size();
    }

    @Override
    public String toString() {
        String result = "";
        final Iterator<Employee> iterator = setdipendenti.iterator();
        while (iterator.hasNext()) {
            final Employee emp = iterator.next();
            result = result.concat("٩(^‿^)۶ " + emp.getName() + " " + emp.getSurname() + " " + emp.getDuty() + "\n");
        }
        return result;
    }

}
