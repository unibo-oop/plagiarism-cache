package oop.lit.view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import oop.lit.util.FileType;
import oop.lit.util.IllegalInputException;
import oop.lit.util.InputRequest;
import oop.lit.util.InputRequestsFactory;
/**
 * An implementation of an inputRequestFactory providing inputRequestFX.
 */
public class InputRequestsFactoryFX implements InputRequestsFactory {
    private static final double COMBO_BOX_ICON_SIZE = 80;
    @Override
    public InputRequest<String> getStringInputRequest(final String label, final Optional<String> initialValue) {
        return new ParseableIR<String>(label, initialValue) {
            @Override
            protected String parse(final String value) throws IllegalInputException {
                if (!value.equals("")) {
                    return value;
                }
                return null;
            }
        };
    }

    @Override
    public InputRequest<Boolean> getBooleanInputRequest(final String label, final Optional<Boolean> initialValue) {
        return new AbstractIR<Boolean>(label) {
            private final Pane pane = new Pane();
            private final CheckBox cb = new CheckBox(label);
            private boolean value;
            @Override
            public Pane getFXPane() {
                return this.pane;
            }
            @Override
            public void storeValue() throws IllegalInputException {
                this.value = this.cb.isSelected();
            }
            @Override
            public Optional<Boolean> getStoredValue() {
                return Optional.of(this.value);
            }
            private InputRequest<Boolean> init() {
                this.cb.setAllowIndeterminate(false);
                if (initialValue.isPresent()) {
                    this.cb.setSelected(initialValue.get());
                }
                this.pane.getChildren().add(cb);
                return this;
            }
        }.init();
    }
    @Override
    public InputRequest<Integer> getIntInputRequest(final String label, final Optional<Integer> initialValue) {
        return new ParseableIR<Integer>(label,
                initialValue.isPresent() ? Optional.of(initialValue.get().toString()) : Optional.empty()) {
                    @Override
                    protected Integer parse(final String value) throws IllegalInputException {
                        try {
                            return Integer.parseInt(value);
                        } catch (NumberFormatException e) {
                            throw new IllegalInputException("Insert an integer");
                        }
                    }
        };
    }
    @Override
    public InputRequest<Double> getDoubleInputRequest(final String label, final Optional<Double> initialValue) {
        return new ParseableIR<Double>(label,
                initialValue.isPresent() ? Optional.of(initialValue.get().toString()) : Optional.empty()) {
                    @Override
                    protected Double parse(final String value) throws IllegalInputException {
                        try {
                            return Double.parseDouble(value);
                        } catch (NumberFormatException e) {
                            throw new IllegalInputException("Insert a double");
                        }
                    }
        };
    }
    @Override
    public <T> InputRequest<T> getChoiceInputRequest(final String label, final Map<String, T> choices, final Optional<String> initialKey) {
        return new ComboBoxRequest<T>(label, choices, initialKey,
                listView -> new ListCell<String>() {
                        @Override
                        protected void updateItem(final String item, final boolean empty) {
                            super.updateItem(item, empty);

                            this.setGraphic(null);
                            if (item == null || empty) {
                                this.setText(null);
                            } else {
                                this.setText(item);
                            }
                        }
                });
    }
    @Override
    public <T> InputRequest<T> getImageChoiceInputRequest(final String label, final Map<String, Pair<BufferedImage, T>> choices, final Optional<String> initialKey) {
        return new ComboBoxRequest<T>(label,
                choices.entrySet().stream().collect(Collectors.toMap(Entry::getKey, entry -> entry.getValue().getRight())),
                initialKey,
                listView -> {
                    return new ListCell<String>() {
                        private final Map<String, ImageView> graphicMap = new HashMap<>();

                        @Override
                        protected void updateItem(final String item, final boolean empty) {
                            super.updateItem(item, empty);

                            if (empty || item == null) {
                                this.setText(null);
                                this.setGraphic(null);
                            } else {
                                this.setText(item);
                                this.setGraphic(this.getGraphic(item));
                            }
                        }

                        private ImageView getGraphic(final String item) {
                            ImageView res = this.graphicMap.get(item);
                            if (res == null) {
                                res = new ImageView(
                                        FXImageConverter.get().convert(Optional.ofNullable(choices.get(item).getLeft())));
                                res.setPreserveRatio(true);
                                res.setFitHeight(COMBO_BOX_ICON_SIZE);
                                res.setFitWidth(COMBO_BOX_ICON_SIZE);
                                this.graphicMap.put(item, res);
                            }
                            return res;
                        }
                    };
                });
    }
    @Override
    public InputRequest<File> getFileInputRequest(final String label, final boolean save, final FileType fileType) {
        return new AbstractIR<File>(label) {
            private final Pane pane = new Pane();
            private final TextField pathBox = new TextField();
            private File res;
            @Override
            public Pane getFXPane() {
                return this.pane;
            }
            @Override
            public void storeValue() throws IllegalInputException {
                res = new File(pathBox.getText());
            }
            @Override
            public Optional<File> getStoredValue() {
                return Optional.ofNullable(res);
            }
            private InputRequest<File> init() {
                final FileChooser chooser = CustomViewComponents.getFileChooser(fileType, "Open file for \"" + label + "\"");
                final Button browseButton = new Button("Browse...");
                browseButton.setOnMouseClicked(e -> {
                    File chosenFile;
                    if (save) {
                       chosenFile = chooser.showSaveDialog(this.pane.getScene().getWindow());
                    } else {
                       chosenFile = chooser.showOpenDialog(this.pane.getScene().getWindow());
                    }
                    if (chosenFile != null) {
                        this.pathBox.setText(chosenFile.getAbsolutePath());
                    }
                });
                this.pane.getChildren().add(CustomViewComponents.labelNode(label, this.pathBox, browseButton));
                return this;
            }
        }.init();
    }

    private abstract static class AbstractIR<T> implements InputRequestFX<T> {
        private final String label;
        protected AbstractIR(final String label) {
            this.label = label;
        }
        @Override
        public String getLabel() {
            return label;
        }
    }

    private abstract static class ParseableIR<T> extends AbstractIR<T> {
        private final TextField textField;
        private final Pane pane = new Pane();
        private T value;
        protected ParseableIR(final String label, final Optional<String> initialValue) {
            super(label);
            this.textField = initialValue.isPresent() ? new TextField(initialValue.get()) : new TextField();
            this.pane.getChildren().add(CustomViewComponents.labelNode(label, this.textField));
        }
        @Override
        public void storeValue() throws IllegalInputException {
            this.value = this.parse(textField.getText());
        }
        @Override
        public Optional<T> getStoredValue() {
            return Optional.ofNullable(value);
        }
        @Override
        public Pane getFXPane() {
            return this.pane;
        }

        protected abstract T parse(String value) throws IllegalInputException;
    }

    private static class ComboBoxRequest<T> extends AbstractIR<T> {
        private final Pane pane = new Pane();
        private final ComboBox<String> cb = new ComboBox<>();
        private final Map<String, T> choices;
        private T value;

        protected ComboBoxRequest(final String label, final Map<String, T> choices, final Optional<String> initialKey, final Callback<ListView<String>, ListCell<String>> cellFactory) {
            super(label);
            if (initialKey.isPresent() && !choices.containsKey(initialKey.get())) {
                throw new IllegalArgumentException("No such key");
            }
            this.choices = choices;
            cb.getItems().addAll(choices.keySet());
            if (initialKey.isPresent()) {
                cb.getSelectionModel().select(initialKey.get());
            }
            cb.setCellFactory(cellFactory);
            this.pane.getChildren().add(CustomViewComponents.labelNode(label, cb));
        }

        @Override
        public Pane getFXPane() {
            return this.pane;
        }
        @Override
        public void storeValue() throws IllegalInputException {
            this.value = choices.get(cb.getSelectionModel().getSelectedItem());
        }
        @Override
        public Optional<T> getStoredValue() {
            return Optional.ofNullable(this.value);
        }
    }
}
