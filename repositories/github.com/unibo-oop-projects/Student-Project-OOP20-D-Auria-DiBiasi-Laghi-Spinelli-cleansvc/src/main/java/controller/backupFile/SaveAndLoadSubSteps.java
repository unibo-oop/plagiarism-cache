package controller.backupFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import controller.ProcessImpl;
import model.step.SubSteps;
import model.step.SubStepsImpl;
import model.step.enumerations.StepType;

public class SaveAndLoadSubSteps implements SaveAndLoad {

    private ProcessImpl process = ProcessImpl.getInstance();

    private static final String SEP = File.separator;
    private static final String FILE_SUBSTEPS = "SubSteps.txt";
    private static final String CODE_STR = "CODE: ";
    private static final String STEP_STR = "STEP: ";
    private static final String DESCRIPTION_STR = "DESCRIPTION: ";
    private static final String NAME_STR = "NAME: ";
    private static final String TIME_STR = "TIME: ";
    private StepType st;
    /**
     * A method that saves subSteps.
     */
    @Override
    public void save() {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(FILE_SUBSTEPS))) {
            for (final SubSteps s : this.process.getSubStepsList()) {
                w.write(CODE_STR + s.getCode());
                w.newLine();
                w.write(STEP_STR + s.getStepType());
                w.newLine();
                w.write(NAME_STR + s.getName());
                w.newLine();
                w.write(DESCRIPTION_STR + s.getDescription());
                w.newLine();
                w.write(TIME_STR + s.getTime());
                w.newLine();
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A method that loads subSteps.
     */
    @Override
    public void load() {
        final List<String> codeList = new ArrayList<>();
        final List<String> stepList = new ArrayList<>();
        final List<String> nameList = new ArrayList<>();
        final List<String> descriptionList = new ArrayList<>();
        final List<Integer> timeList = new ArrayList<>();
        try (BufferedReader r = new BufferedReader(new FileReader(FILE_SUBSTEPS))) {
            r.lines().forEach(l -> {
                if (l.contains(CODE_STR)) {
                    codeList.add(l.substring(CODE_STR.length()));
                }
                if (l.contains(STEP_STR)) {
                    stepList.add(l.substring(STEP_STR.length()));
                }
                if (l.contains(NAME_STR)) {
                    nameList.add(l.substring(NAME_STR.length()));
                }
                if (l.contains(DESCRIPTION_STR)) {
                    descriptionList.add(l.substring(DESCRIPTION_STR.length()));
                }
                if (l.contains(TIME_STR)) {
                    timeList.add(Integer.valueOf(l.substring(TIME_STR.length())));
                }
            });
            for (int i = 0; i < codeList.size(); i++) {
                for (StepType stepType : process.getStepTypeList()) {
                    if (stepList.get(i).equals(stepType.getType())) {
                      st = stepType;
                    }
                }
                this.process.addStep(new SubStepsImpl(codeList.get(i), timeList.get(i), nameList.get(i), descriptionList.get(i), st));
            }
        } catch (final IOException e) {
            System.err.println(e.getMessage());
        }
    }

}
