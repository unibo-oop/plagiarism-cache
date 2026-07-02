package gymman.employees;

import java.util.List;
import java.util.Optional;

import gymman.auth.UserRepository;
import gymman.common.Repository;

public interface EmployeeRepository extends Repository<Employee>, UserRepository {
    List<Employee> searchByName(String name);
    Optional<Employee> getEmployeeByUsername(String username);
}
