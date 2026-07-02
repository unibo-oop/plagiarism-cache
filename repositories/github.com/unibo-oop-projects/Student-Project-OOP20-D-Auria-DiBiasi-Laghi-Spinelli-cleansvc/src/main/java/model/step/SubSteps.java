package model.step;

import model.step.enumerations.StepType;

public interface SubSteps {

    String getName();

    String getDescription();

    int getTime();

    String getCode();

    StepType getStepType();
}
