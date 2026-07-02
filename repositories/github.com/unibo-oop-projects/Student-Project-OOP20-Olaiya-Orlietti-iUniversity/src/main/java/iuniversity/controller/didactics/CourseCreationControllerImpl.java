package iuniversity.controller.didactics;

import iuniversity.controller.AbstractController;
import iuniversity.model.didactics.CourseImpl;

public class CourseCreationControllerImpl extends AbstractController implements CourseCreationController {

    @Override
    public void createCourse(String name, int cfu) {
        this.getModel().getDidacticsManager().addCourse(new CourseImpl(name, cfu));
        this.getStorage().saveCourses(this.getModel().getDidacticsManager().getCourse());
    }

}
