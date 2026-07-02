package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.ProcessImpl;
import model.step.SubSteps;
import model.step.SubStepsImpl;
import model.step.enumerations.StepType;
import model.users.Clients;
import model.users.ClientsImpl;
import utility.ConstantsCleanSvc;

public class TestProcessSubSteps {
    private static ProcessImpl process = ProcessImpl.getInstance();
    private static final String CODE = "1S";
    private static final int TIME = 20;
    private static final String NAME = "Disinfezione Classica";
    private static final String DESCRIPTION = "Non c'è utilizzo di ozono";
    private static final StepType STEP = process.getStepTypeList().get(4);
    private static final int TIME2 = 120;
    private static final int COST = 170;
    private SubSteps subStep = null;

    @BeforeAll
    public void initSubStep() {
        this.subStep = new SubStepsImpl(CODE, TIME, NAME, DESCRIPTION, STEP);
    }

    @Test
    public void testSubStep() {
        Assertions.assertTrue(subStep.getCode().equals(CODE));
        Assertions.assertTrue(Integer.compare(subStep.getTime(), TIME) == 0);
        Assertions.assertTrue(subStep.getName().equals(NAME));
        Assertions.assertTrue(subStep.getDescription().equals(DESCRIPTION));
        Assertions.assertTrue(subStep.getStepType().equals(STEP));
    }

    @Test
    public void testProcess() {
            Assertions.assertTrue(process.getSubStepsList().isEmpty());
            process.addStep(subStep);
            Assertions.assertFalse(process.getSubStepsList().isEmpty());
            Assertions.assertTrue(process.getSubStepsList().get(0).equals(process.searchSubStep(CODE).get()));
            Assertions.assertTrue(process.getSubStepsList().get(0).equals(process.getSubStepsByStepType(STEP.getType()).get().get(0)));
            process.removeStep(subStep);
            Assertions.assertTrue(process.getSubStepsList().isEmpty());
    }

    @Test
    public void testStepTypes() {
        Assertions.assertTrue(process.getStepTypeList().get(0).getType().equals("CLEANING"));
        Assertions.assertTrue(process.getStepTypeList().get(1).getType().equals("CLEANSING"));
        Assertions.assertTrue(process.getStepTypeList().get(2).getType().equals("DISINFECTION"));
        Assertions.assertTrue(process.getStepTypeList().get(3).getType().equals("DISINFESTATION"));
        Assertions.assertTrue(process.getStepTypeList().get(4).getType().equals("CONCLUSION"));
    }

    @Test
    public void testMathOperation() {
        final Clients c = new ClientsImpl("LGHRRA97E69H199X", "Aurora", "Via Fratelli bandiera, 7", "Forlimpopoli", 47034, "0543743592", "aurora.laghi@studio.unibo.it", 500);
        double time = TIME2;
        double cost = COST;
        double totCost = Math.floor(cost * ConstantsCleanSvc.INCOME_INCRISE * ConstantsCleanSvc.INCOME_TAX);
        int nStaff = 1;
        Assertions.assertTrue(Double.compare(process.getProportialTime(time, c, nStaff), time) == 0);
        Assertions.assertTrue(Double.compare(process.getProportialCost(cost, c), cost) == 0);
        Assertions.assertTrue(Double.compare(process.getIncome(cost), totCost) == 0);
    }
}
