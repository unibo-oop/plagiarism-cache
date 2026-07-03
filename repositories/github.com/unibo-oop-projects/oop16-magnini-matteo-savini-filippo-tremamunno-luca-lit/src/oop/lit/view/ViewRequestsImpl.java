package oop.lit.view;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import oop.lit.util.IllegalInputException;
import oop.lit.util.InputRequest;
import oop.lit.util.InputRequestsFactory;

/**
 * Main view class implementation.
 */
public class ViewRequestsImpl implements ViewRequests {
    //NOTA: la alert, di cui si fa ampio uso, probabilmente funziona solo se utilizzata nel thread principale di javafx (non ho testato). Nel caso si vogliano usare più thread bisogna sistemare.
    private final InputRequestsFactory irFactory = new InputRequestsFactoryFX();

    @Override
    public InputRequestsFactory getIRFactory() {
        return this.irFactory;
    }

    @Override
    public boolean askInput(final List<InputRequest<?>> requests) {
        if (!requests.isEmpty()) {
            if (!requests.stream().allMatch(request -> request instanceof InputRequestFX)) {
                throw new IllegalArgumentException("Provided requests are not valid");
            }
            final Alert alert = new Alert(AlertType.NONE, "Perform action", ButtonType.OK, ButtonType.CANCEL);

            final VBox box = new VBox();
            alert.getDialogPane().setContent(box);
            box.getChildren().addAll(requests.stream().map(r -> ((InputRequestFX<?>) r).getFXPane())
                    .collect(Collectors.toList()));

            Optional<ButtonType> alertRes;
            boolean inputOk = false;

            do {
                alertRes = alert.showAndWait();
                inputOk = true;
                for (final InputRequest<?> request : requests) {
                    try {
                        request.storeValue();
                    } catch (IllegalInputException e) {
                        inputOk = false;
                        displayError(e.getMessage());
                    }
                }
            } while (!inputOk && alertRes.isPresent() && alertRes.get().equals(ButtonType.OK));

            return inputOk && alertRes.isPresent() && alertRes.get().equals(ButtonType.OK);
        }
        return true;
    }

    @Override
    public void displayError(final String message) {
        displayMessageAlert(message, AlertType.ERROR);
    }

    @Override
    public void displayMessage(final String message) {
        displayMessageAlert(message, AlertType.NONE);
    }

    private void displayMessageAlert(final String message, final AlertType type) {
        new Alert(type, message, ButtonType.OK).showAndWait();
    }
}
