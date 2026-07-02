package model;

import model.step.enumerations.StepType;

public interface Products {
    String getCode();
    StepType getStepType();
    String getName();
    String getDescription();
    double getPricePerLitre();
    double getLitersPer500Mq();
}
