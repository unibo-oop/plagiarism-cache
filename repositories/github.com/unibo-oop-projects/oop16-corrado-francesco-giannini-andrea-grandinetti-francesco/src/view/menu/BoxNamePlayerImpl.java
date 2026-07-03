package view.menu;

import java.util.Arrays;

import controller.CircuitPlayable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import utility.Driver;
import utility.TyreType;

/**
 * A possible implementation of BoxNamePlayer where user can insert data for every player.
 *
 */
public class BoxNamePlayerImpl extends HBox implements BoxNamePlayer {

    private static final int SPACE = 15;

    private final TextField nameField;
    private final ChoiceBox<Driver> combo;
    private final ChoiceBox<TyreType> comboTyre;

    BoxNamePlayerImpl(final int num, final CircuitPlayable cir) {
        super(SPACE);
        this.setAlignment(Pos.CENTER);
        this.getChildren().add(new Label("Giocatore " + num + ":"));
        this.nameField = new TextField();
        this.getChildren().add(nameField);
        final ObservableList<Driver> enumList = FXCollections.observableArrayList(Arrays.asList(Driver.values()));
        final ObservableList<TyreType> enumListTyre = FXCollections.observableArrayList(cir.getTyresAvailable());
        this.combo = new ChoiceBox<Driver>(enumList);
        this.combo.setValue(Driver.values()[num - 1]);
        this.comboTyre = new ChoiceBox<>(enumListTyre);
        this.comboTyre.setValue(cir.getTyresAvailable().get(0));
        this.getChildren().add(combo);
        this.getChildren().add(comboTyre);
    }

    @Override
    public String getName() {
        return nameField.getText();
    }

    @Override
    public Driver getDriver() {
        return combo.getValue();
    }

    @Override
    public TyreType getTyre() {
        return comboTyre.getValue();
    }
}
