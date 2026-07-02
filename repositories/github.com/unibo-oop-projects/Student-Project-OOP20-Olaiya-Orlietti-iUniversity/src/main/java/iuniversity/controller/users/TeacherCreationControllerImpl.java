package iuniversity.controller.users;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import iuniversity.controller.AbstractController;
import iuniversity.controller.AccountsManager;
import iuniversity.controller.AccountsManagerImpl;
import iuniversity.model.didactics.Course;
import iuniversity.model.user.Teacher;
import iuniversity.model.user.TeacherImpl;
import iuniversity.model.user.User.Gender;
import iuniversity.model.user.User.UserType;
import iuniversity.view.users.StudentCreationView;
import iuniversity.view.users.TeacherCreationView;

public class TeacherCreationControllerImpl extends AbstractController implements TeacherCreationController {

    private AccountsManager accountManager = this.getAccountsManager();
    @Override
    public void createTeacher(String firstName, String lastName, LocalDate dateOfBirth, 
            Gender gender, String address, Set<Course> courses) {
        Set<Teacher> teachers = this.getModel().getArchive().getTeachers();
        int occurencies = 0;
        for (Teacher t : teachers) {
            if (t.getName().equals(firstName) && t.getLastName().equals(lastName)) {
                occurencies++;
            }
        }
        final String newUsername = accountManager.makeUsername(UserType.TEACHER, firstName, lastName, occurencies);
        final String newPassword = accountManager.createPassword();
        final Teacher teacher = new TeacherImpl.TeacherBuilder(firstName, lastName, this.getModel().getArchive().getNewUserId(), this.getModel().getArchive().getNewTeacherRegistrationNumber())
                                               .username(newUsername)
                                               .dateOfBirth(dateOfBirth)
                                               .address(address)
                                               .gender(gender)
                                               .courses(courses)
                                               .build();
        this.getModel().getArchive().addTeacher(teacher);
        this.accountManager.registerTeacherAccount(teacher, newPassword, occurencies);
        this.getStorage().saveTeachers(this.getModel().getArchive().getTeachers());
        ((TeacherCreationView) this.getView()).showTeacherCredentials(newUsername, newPassword);
    }
    @Override
    public void initializeChoices() {
        HashSet<Gender> genders = new HashSet<>();
        genders.addAll(Arrays.asList(Gender.values()));
        ((TeacherCreationView) this.getView()).setCourseChoices(this.getModel().getDidacticsManager().getCourse());
        ((TeacherCreationView) this.getView()).setGenderChoices(genders);
    }
}
