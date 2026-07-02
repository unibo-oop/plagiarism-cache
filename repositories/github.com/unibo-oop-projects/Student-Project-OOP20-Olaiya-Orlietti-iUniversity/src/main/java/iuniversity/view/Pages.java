package iuniversity.view;

/**
 * Redirectionable pages.
 */
public enum Pages {
    /**
     * loginPage.
     */
    LOGIN("login"),
    /**
     * Student's home page.
     */
    STUDENT_HOME("student_homepage"),
    /**
     * Teacher's home page.
     */
    TEACHER_HOME("teacher_homepage"),
    /**
     * Exam call creation page.
     */
    CREATE_EXAM_CALL("create_exam_call"),
    /**
     * Exam report creation page.
     */
    CREATE_EXAM_REPORT("create_exam_report"),
    /**
     * Student creation page.
     */
    ADD_STUDENT("add_student"),
    /**
     * Teacher creation page.
     */
    ADD_TEACHER("add_teacher"),
    /**
     * Course creation page.
     */
    ADD_COURSE("add_course"),
    /**
     * Degree programme creation page.
     */
    ADD_DEGREE_PROGRAMME("add_degreeProgramme"),
    /**
     * Admin home page.
     */
    ADMIN_HOME("admin_homepage"),
    /**
     * Exam call booking page.
     */
    BOOK_EXAM_CALL("book_exam_call"),
    /**
     * 
     */
    BOOKLET("booklet");

    private final String fxmlName;

    Pages(final String name) {
        this.fxmlName = name;
    }

    /**
     * 
     * @return the name of the fxml file
     */
    public String getFXMLName() {
        return this.fxmlName;
    }
}
