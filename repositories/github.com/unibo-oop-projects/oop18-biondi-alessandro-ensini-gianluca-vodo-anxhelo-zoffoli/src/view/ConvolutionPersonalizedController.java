package view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import controller.ConvolutionHandler;
import controller.MainViewHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import view.util.language.LanguageManagerUtils;

/**
 * View controller of ConvolutionPersonalized .
 */
public class ConvolutionPersonalizedController implements Initializable {

    private final ConvolutionHandler convolutionHandler = new ConvolutionHandler();
    private List<TextField> kernelFields;
    private static final int MAX_TEXT_FIELD_LENGTH = 5;

    @FXML
    private Label labelConvolutionTitle;
    @FXML
    private Label labelConvolutionName;
    @FXML
    private Label labelKernel;
    @FXML
    private Label labelDivider;
    @FXML
    private TextField textFieldConvolutionName;
    @FXML
    private TextField textFieldDivider;
    @FXML
    private TextField textField1;
    @FXML
    private TextField textField2;
    @FXML
    private TextField textField3;
    @FXML
    private TextField textField4;
    @FXML
    private TextField textField5;
    @FXML
    private TextField textField6;
    @FXML
    private TextField textField7;
    @FXML
    private TextField textField8;
    @FXML
    private TextField textField9;
    @FXML
    private Button buttonSaveEffect;
    @FXML
    private Button buttonCancel;

    /**
     * Save the specified convolution effect.
     */
    public void savePersonalizedEffect() {
        if (controlsValuesAreOk()) {
            float[] kernelMatrix = new float[kernelFields.size()];

            for (int i = 0; i < kernelFields.size(); i++) {
                kernelMatrix[i] = Float.parseFloat(kernelFields.get(i).getText());
            }

            convolutionHandler.addPersonalizedEffect(kernelMatrix, Integer.parseInt(textFieldDivider.getText()),
                    textFieldConvolutionName.getText());

            cancel();
        }
    }

    /**
     * Cancel the current view.
     */
    public void cancel() {
        MainViewHandler.showView("/scene/Convolution.fxml");
    }

    /**
     * @see javafx.fxml.Initializable#initialize(java.net.URL,
     *      java.util.ResourceBundle)
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        initializeLabels();
        initializeButtons();
        initializeTextFields();
    }

    private void initializeLabels() {
        labelConvolutionTitle.textProperty().bind(LanguageManagerUtils.createStringBinding("labelconvolution.title"));
        labelConvolutionName.textProperty().bind(LanguageManagerUtils.createStringBinding("labelconvolution.name"));
        labelKernel.textProperty().bind(LanguageManagerUtils.createStringBinding("labelconvolution.kernel"));
        labelDivider.textProperty().bind(LanguageManagerUtils.createStringBinding("labelconvolution.divider"));
    }

    private void initializeButtons() {
        buttonSaveEffect.textProperty().bind(LanguageManagerUtils.createStringBinding("buttonconvolution.save"));
        buttonCancel.textProperty().bind(LanguageManagerUtils.createStringBinding("buttonconvolution.cancel"));
    }

    private void initializeTextFields() {
        kernelFields = new ArrayList<TextField>();
        kernelFields.add(textField1);
        kernelFields.add(textField2);
        kernelFields.add(textField3);
        kernelFields.add(textField4);
        kernelFields.add(textField5);
        kernelFields.add(textField6);
        kernelFields.add(textField7);
        kernelFields.add(textField8);
        kernelFields.add(textField9);

        kernelFields.forEach((x) -> x.textProperty().addListener((arg0, oldValue, newValue) -> {
            if ((!newValue.matches("-\\d*") && !newValue.matches("\\d*"))
                    || newValue.length() > MAX_TEXT_FIELD_LENGTH) {
                x.setText(oldValue); // x.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }));

        textFieldConvolutionName.textProperty().addListener((arg0, oldValue, newValue) -> {
            if ((!isFileNameValid(newValue) && !newValue.equals("")) || newValue.contains("\\")
                    || newValue.contains("/")) {
                textFieldConvolutionName.setText(oldValue);
            }
        });

        textFieldDivider.textProperty().addListener((arg0, oldValue, newValue) -> {
            if ((!newValue.matches("-\\d*") && !newValue.matches("\\d*"))
                    || newValue.length() > MAX_TEXT_FIELD_LENGTH) {
                textFieldDivider.setText(oldValue);
            }
        });
    }

    private boolean controlsValuesAreOk() {
        return (kernelFields.stream().filter(x -> !isFloatParsable(x.getText())).count() == 0
                && isFloatParsable(textFieldDivider.getText()) && !textFieldConvolutionName.getText().equals(""));
    }

    private boolean isFileNameValid(final String aFileName) {
        final File aFile = new File(aFileName);
        boolean isValid = true;
        try {
            if (aFile.createNewFile() && aFile.delete()) {
                isValid = true;
            }
        } catch (IOException e) {
            isValid = false;
        }
        return isValid;
    }

    private boolean isFloatParsable(final String value) {
        try {
            Float.parseFloat(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
