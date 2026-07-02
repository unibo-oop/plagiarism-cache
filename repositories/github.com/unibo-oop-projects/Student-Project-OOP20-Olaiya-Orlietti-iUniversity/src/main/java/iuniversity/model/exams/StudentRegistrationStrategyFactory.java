package iuniversity.model.exams;

public interface StudentRegistrationStrategyFactory {

    /**
     * 
     * @return a strategy where the student is added ad the end of an exam call list.
     */
    StudentRegistrationStrategy atTheEndOfList();

    /**
     * 
     * @return a strategy where the student is added ad the top of an exam call list.
     */
    StudentRegistrationStrategy atTheTopOfList();

    /**
     * 
     * @return a strategy where the students are registered in alphabetical order.
     */
    StudentRegistrationStrategy alfabeticalOrder();

}
