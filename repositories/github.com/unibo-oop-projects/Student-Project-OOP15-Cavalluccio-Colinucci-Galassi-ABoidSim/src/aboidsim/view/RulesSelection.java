package aboidsim.view;

import java.util.ArrayList;
import java.util.List;

import aboidsim.util.Input;
import aboidsim.util.InputInfo;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * contains the checkbox for choose the rules to apply to the simulation.
 *
 */
class RulesSelection extends VBox {

    private static List<String> rules;
    private final List<CheckBox> boxes = new ArrayList<>();

    /**
     * constructor of the class
     */
    RulesSelection() {
        super();

        this.setSpacing(10);

        RulesSelection.rules.stream().forEach(e -> {
            final CheckBox c = new CheckBox(e);
            this.boxes.add(c);
            c.setOnAction(r -> {
                this.addInput(this.boxes.indexOf(c));
            });
        });
        this.boxes.stream().forEach(e -> e.setSelected(true));
        final Label title = new Label("SELECT RULES TO ENABLE: ");
        title.setId("title");
        this.getChildren().add(title);
        this.getChildren().addAll(this.boxes);

    }

    /**
     * adds a new input info to the list of rules in InputHandler.
     *
     * @param rule
     *            number of the rule selected
     */
    private void addInput(final Integer rule) {
        InputHandler.getInputHandler().addInput(new InputInfo(Input.TOGGLE_RULE, rule));
    }

    /**
     * set the list of rules
     *
     * @param rulesList
     *            list of the rule names
     */
    static void setRules(final List<String> rulesList) {
        RulesSelection.rules = rulesList;
    }

}
