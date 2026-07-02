package controller;

import java.util.List;
import java.util.Optional;
import model.step.SubSteps;
import model.step.enumerations.StepType;
import model.users.Clients;

public interface Process {

    List<SubSteps> getSubStepsList();

    List<StepType> getStepTypeList();

    void addStep(SubSteps s);

    void removeStep(SubSteps s);

    Optional<SubSteps> searchSubStep(String code);

    Optional<List<SubSteps>> getSubStepsByStepType(String stepType);

    double getProportialTime(double value, Clients s, int staff);

    double getProportialCost(double value, Clients s);

    double getIncome(double value);

}
