package iuniversity.view.login;

public interface LoginView {

    /**
     * Try to log user.
     * 
     * @param username user's username
     * @param password user's password
     */
    void login(String username, String password);

    /**
     * Manages incorrect credentials.
     */
    void incorrectCredentials();

    /**
     * Switch to the admin home page.
     */
    void goToAdminHomePage();

    /**
     * Switch to the student home page.
     */
    void goToStudentHomePage();

    /**
     * Switch to the teacher home page.
     */
    void goToTeacherHomePage();

}
