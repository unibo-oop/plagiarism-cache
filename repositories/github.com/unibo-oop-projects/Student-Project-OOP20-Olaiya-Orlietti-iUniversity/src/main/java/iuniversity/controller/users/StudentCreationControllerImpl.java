package iuniversity.controller.users;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import iuniversity.controller.AbstractController;
import iuniversity.controller.AccountsManager;
import iuniversity.controller.AccountsManagerImpl;
import iuniversity.model.didactics.DegreeProgramme;
import iuniversity.model.user.Student;
import iuniversity.model.user.StudentImpl;
import iuniversity.model.user.User.Gender;
import iuniversity.model.user.User.UserType;
import iuniversity.view.users.StudentCreationView;

public class StudentCreationControllerImpl extends AbstractController implements StudentCreationController {

    private AccountsManager accountManager = this.getAccountsManager();
    
    @Override
    public void createStudent(String firstName, String lastName, LocalDate dateOfBirth, Gender gender, String address,
            DegreeProgramme degreeProgramme) {
        Set<Student> students = this.getModel().getArchive().getStudents();
        int occurencies = 0;
        for (Student s : students) {
            if (s.getName().equals(firstName) && s.getLastName().equals(lastName)) {
                occurencies++;
            }
        }
        final String newUsername = accountManager.makeUsername(UserType.STUDENT, firstName, lastName, occurencies);
        final String newPassword = accountManager.createPassword();
        final Student student = new StudentImpl.StudentBuilder(firstName, lastName, this.getModel().getArchive().getNewUserId(), this.getModel().getArchive().getNewStudentRegistrationNumber())
                                           .username(newUsername)
                                           .dateOfBirth(dateOfBirth)
                                           .address(address)
                                           .gender(gender)
                                           .degreeProgramme(degreeProgramme)
                                           .build();
        this.getModel().getArchive().addStudent(student);
        this.accountManager.registerStudentAccount(student, newPassword, occurencies);
        this.getStorage().saveStudents(this.getModel().getArchive().getStudents());
        ((StudentCreationView) this.getView()).showStudentCredentials(newUsername, newPassword);
    }

    @Override
    public void initializeChoices() {
        List<DegreeProgramme> degreeProgrammes = new ArrayList<>();
        degreeProgrammes.addAll(this.getModel().getDidacticsManager().getDegreeProgrammes());
        ((StudentCreationView) this.getView()).setDegreeProgrammChoices(degreeProgrammes);
        ((StudentCreationView) this.getView()).setGenderChoices(Arrays.asList(Gender.values()));
    }

}
