package aboidsim.view;

import java.util.List;

import aboidsim.util.Input;
import aboidsim.util.InputInfo;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * element of the interface used to select the environment to show in the
 * simulation.
 *
 */
class EnvironmentSelection extends VBox {

    private static List<String> envList;
    private final ComboBox<String> menu;
    private String selectedEnv;

    /**
     * constructor of the class.
     */
    EnvironmentSelection() {

        final Label title = new Label("CONFIGURATION SELECTION");
        title.setId("title");
        final Button load = new Button("Load");
        this.menu = new ComboBox<>();
        this.menu.setPromptText("Select the config to load");
        EnvironmentSelection.envList.forEach(e -> this.menu.getItems().add(e));

        this.menu.setOnAction(e -> {
            if (this.menu.getValue() != null) {
                this.selectedEnv = this.menu.getValue();
            }
        });

        load.setOnAction(e -> {
            if (this.selectedEnv != null) {
                this.addInput(EnvironmentSelection.envList.indexOf(this.selectedEnv));
            }
        });

        this.setSpacing(10);
        this.getChildren().addAll(title, this.menu, load);

    }

    /**
     * add the environment inputInfo to the list of input in InputHandler.
     *
     * @param env
     *            number of the environment selected
     */
    private void addInput(final int env) {
        InputHandler.getInputHandler().addInput(new InputInfo(Input.LOAD_ENV, env));
    }

    /**
     * set the list of environments
     *
     * @param env
     *            list of the environment names
     */
    static void setEnvs(final List<String> env) {
        EnvironmentSelection.envList = env;
    }

}
