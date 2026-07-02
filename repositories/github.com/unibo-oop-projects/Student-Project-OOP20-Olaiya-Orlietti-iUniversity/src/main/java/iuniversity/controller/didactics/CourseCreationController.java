package iuniversity.controller.didactics;

public interface CourseCreationController {

    /**
     * Create a new course
     * @param name      The name for the course to create
     * @param cfu       The number of CFU for the course
     */
    void createCourse(String name, int cfu);

}
