package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import controller.ConvolutionHandler;
import controller.MainViewHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import view.util.language.LanguageManagerUtils;
import view.util.theme.ThemeUtils;

/**
 * View controller of Convolution .
 */
public class ConvolutionController implements Initializable {

    private final ConvolutionHandler convolutionHandler = new ConvolutionHandler();
    private static final int MIN_BLUR = 1;
    private static final int MAX_BLUR = 20;
    private static final int MAX_FAST_BLUR = 100;

    @FXML
    private Button buttonApplyBlur;
    @FXML
    private Button buttonApplyEdgeDetection;
    @FXML
    private Button buttonApplySharpen;
    @FXML
    private Button buttonApplyEmboss;
    @FXML
    private Button buttonApplyPersonalized;
    @FXML
    private Button buttonAddEffect;
    @FXML
    private Button buttonRemoveEffect;
    @FXML
    private Label labelConvolution;
    @FXML
    private Label labelBlur;
    @FXML
    private Label labelEdgeDetection;
    @FXML
    private Label labelOthers;
    @FXML
    private Label labelPersonalized;
    @FXML
    private ComboBox<String> comboboxBlur;
    @FXML
    private ComboBox<String> comboboxEdgeDetection;
    @FXML
    private ComboBox<String> comboboxPersonalized;
    @FXML
    private Slider sliderBlur;
    @FXML
    private Spinner<Integer> spinnerBlur;

    /**
     * Apply the Blur convolution effect.
     */
    public void applyBlur() {
        if (comboboxNotEmptyAndItemSelected(comboboxBlur)) {
            convolutionHandler.applyBlur(spinnerBlur.getValue(), comboboxBlur.getSelectionModel().getSelectedIndex());
        }
    }

    /**
     * Apply the Edge Detection convolution effect.
     */
    public void applyEdgeDetection() {
        if (comboboxNotEmptyAndItemSelected(comboboxEdgeDetection)) {
            convolutionHandler.applyEdgeDetection(comboboxEdgeDetection.getSelectionModel().getSelectedIndex());
        }
    }

    /**
     * Apply the Sharpen convolution effect.
     */
    public void applySharpen() {
        convolutionHandler.applySharpen();
    }

    /**
     * Apply the Emboss convolution effect.
     */
    public void applyEmboss() {
        convolutionHandler.applyEmboss();
    }

    /**
     * Apply the personalized convolution effect.
     */
    public void applyPersonalizedEffect() {
        if (comboboxNotEmptyAndItemSelected(comboboxPersonalized)) {
            convolutionHandler.applyPersonalizedEffect(comboboxPersonalized.getSelectionModel().getSelectedIndex());
        }
    }

    /**
     * Add a new personalized convolution effect.
     */
    public void addPersonalizedEffect() {
        MainViewHandler.showView("/scene/ConvolutionPersonalized.fxml");
    }

    /**
     * Remove the selected personalized convolution effect.
     */
    public void removePersonalizedEffect() {
        final Alert alert = new Alert(AlertType.INFORMATION);
        ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/icons/mustashi.png"));
        ThemeUtils.setTheme((Stage) alert.getDialogPane().getScene().getWindow());
        alert.setTitle(LanguageManagerUtils.createStringBinding("alert.title").getValue());
        alert.setHeaderText(LanguageManagerUtils.createStringBinding("alert.headertext").getValue());

        if (comboboxNotEmptyAndItemSelected(comboboxPersonalized)) {
            try {
                convolutionHandler
                        .removePersonalizedEffect(comboboxPersonalized.getSelectionModel().getSelectedIndex());
                comboboxPersonalized.getItems().remove(comboboxPersonalized.getSelectionModel().getSelectedIndex());

                alert.setContentText(LanguageManagerUtils.createStringBinding("alert.contentok").getValue());
                alert.showAndWait();
            } catch (IOException e) {
                alert.setContentText(LanguageManagerUtils.createStringBinding("alert.contenterror").getValue());
                alert.showAndWait();
                e.printStackTrace();
            }

        }
    }

    /**
     * @see javafx.fxml.Initializable#initialize(java.net.URL,
     *      java.util.ResourceBundle)
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        initializeButtons();
        initializeLabels();
        initializeComboBoxes();
        initializeSpinners();
        initializeSliders();
    }

    private void initializeButtons() {
        // binding language text on buttons
        buttonApplyBlur.textProperty().bind(LanguageManagerUtils.createStringBinding("button.blur"));
        buttonApplyEdgeDetection.textProperty().bind(LanguageManagerUtils.createStringBinding("button.edgedetection"));
        buttonApplySharpen.textProperty().bind(LanguageManagerUtils.createStringBinding("button.sharpen"));
        buttonApplyEmboss.textProperty().bind(LanguageManagerUtils.createStringBinding("button.emboss"));
        buttonApplyPersonalized.textProperty().bind(LanguageManagerUtils.createStringBinding("button.personalized"));
        buttonAddEffect.textProperty().bind(LanguageManagerUtils.createStringBinding("button.addpersonalized"));
        buttonRemoveEffect.textProperty().bind(LanguageManagerUtils.createStringBinding("button.removepersonalized"));
    }

    private void initializeLabels() {
        // binding language text on labels
        labelConvolution.textProperty().bind(LanguageManagerUtils.createStringBinding("label.convolution"));
        labelBlur.textProperty().bind(LanguageManagerUtils.createStringBinding("label.blur"));
        labelEdgeDetection.textProperty().bind(LanguageManagerUtils.createStringBinding("label.edgedetection"));
        labelOthers.textProperty().bind(LanguageManagerUtils.createStringBinding("label.others"));
        labelPersonalized.textProperty().bind(LanguageManagerUtils.createStringBinding("label.personalized"));
    }

    private void initializeComboBoxes() {
        // binding language text on combo box elements
        comboboxBlur.itemsProperty().bind(LanguageManagerUtils.createListBinding("combobox.fastblur",
                "combobox.gaussianblur", "combobox.boxblur"));
        comboboxBlur.getSelectionModel().selectFirst();

        comboboxBlur.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            if (comboboxBlur.getSelectionModel().getSelectedIndex() == 0) {
                spinnerBlur
                        .setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(MIN_BLUR, MAX_FAST_BLUR));
                sliderBlur.setMax(MAX_FAST_BLUR);
            } else {
                spinnerBlur.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(MIN_BLUR, MAX_BLUR));
                sliderBlur.setMax(MAX_BLUR);
            }
        });

        comboboxEdgeDetection.itemsProperty().bind(LanguageManagerUtils.createListBinding("combobox.edgedetectionlow",
                "combobox.edgedetectionmedium", "combobox.edgedetectionhigh"));
        comboboxEdgeDetection.getSelectionModel().selectFirst();

        comboboxPersonalized.getItems().addAll(convolutionHandler.initializePersonalizedEffect());
        comboboxPersonalized.getSelectionModel().selectFirst();
    }

    private void initializeSpinners() {
        spinnerBlur.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(MIN_BLUR, MAX_FAST_BLUR));

        spinnerBlur.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            if (!"".equals(newValue)) {
                sliderBlur.setValue(spinnerBlur.getValue());
            }
        });
    }

    private void initializeSliders() {
        sliderBlur.setMin(MIN_BLUR);
        sliderBlur.setMax(MAX_FAST_BLUR);
        sliderBlur.setBlockIncrement(1);

        sliderBlur.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (oldValue != newValue) {
                spinnerBlur.getValueFactory().setValue((int) sliderBlur.getValue());
            }
        });
    }

    private <X> Boolean comboboxNotEmptyAndItemSelected(final ComboBox<X> cmbx) {
        return (!cmbx.getItems().isEmpty() && cmbx.getSelectionModel().getSelectedItem() != null);
    }

}
