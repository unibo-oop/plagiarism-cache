package aboidsim.view;

import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * part of the interface used to show some information about the simulation.
 *
 */
class InfoBox extends GridPane {

    private static Label numTotBoids = new Label("0");
    private static Label numHerbivores = new Label("0");
    private static Label numPredator = new Label("0");
    private static Label numTrees = new Label("0");
    private static final int LASTH = 5;

    InfoBox() {
        super();

        final Label title = new Label("INFORMATION BOX");
        title.setId("title");
        final Label totLabel = new Label("Total boids: ");
        final Label herbLabel = new Label("Total herbivores: ");
        final Label predLabel = new Label("Total predators: ");
        final Label treesLabel = new Label("Total trees: ");

        GridPane.setConstraints(title, 0, 0, 2, 1);
        GridPane.setConstraints(totLabel, 0, 1);
        GridPane.setConstraints(herbLabel, 0, 2);
        GridPane.setConstraints(predLabel, 0, 3);
        GridPane.setConstraints(treesLabel, 0, 4);
        GridPane.setConstraints(InfoBox.numTotBoids, 1, 1);
        GridPane.setConstraints(InfoBox.numHerbivores, 1, 2);
        GridPane.setConstraints(InfoBox.numPredator, 1, 3);
        GridPane.setConstraints(InfoBox.numTrees, 1, 4);
        title.setAlignment(Pos.CENTER);

        InfoBox.numHerbivores.setId("info-label");
        InfoBox.numPredator.setId("info-label");
        InfoBox.numTotBoids.setId("info-label");
        InfoBox.numTrees.setId("info-label");

        this.getChildren().addAll(title, totLabel, herbLabel, predLabel, treesLabel, InfoBox.numTotBoids,
                InfoBox.numHerbivores, InfoBox.numPredator, InfoBox.numTrees);

    }

    /**
     * it updates the information of the labels
     *
     * @param levels
     *            list containing the boids number for each level
     */
    static void updateInfo(final List<Integer> levels) {
        int herb = 0;
        int tree = 0;
        int pred = 0;

        for (final int i : levels) {
            if (i > 0 && i <= InfoBox.LASTH) {
                herb++;
            } else if (i > InfoBox.LASTH) {
                pred++;
            } else {
                tree++;
            }
        }

        InfoBox.numTotBoids.setText(String.valueOf(levels.size()));
        InfoBox.numHerbivores.setText(String.valueOf(herb));
        InfoBox.numPredator.setText(String.valueOf(pred));
        InfoBox.numTrees.setText(String.valueOf(tree));

    }

}
