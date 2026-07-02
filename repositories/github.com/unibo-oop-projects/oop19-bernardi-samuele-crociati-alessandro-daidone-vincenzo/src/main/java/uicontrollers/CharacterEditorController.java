package uicontrollers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.entities.AttackType;
import model.entities.Entities;
import model.entities.MovementType;

/**
 * 
 * Controller for the character editor view.
 *
 */
public final class CharacterEditorController {

    @FXML
    private Button btnMinusStrenght;
    @FXML
    private Button btnPlusStrenght;
    @FXML
    private Button btnMinusHP;
    @FXML
    private Button btnPlusHP;
    @FXML
    private Button btnMinusDefense;
    @FXML
    private Button btnPlusDefense;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnImport;
    @FXML
    private Button btnQuit;
    @FXML
    private Button btnLoad;
    @FXML
    private Text txtForza;
    @FXML
    private Text txtHP;
    @FXML
    private Text txtDefense;
    @FXML
    private TextField txtFPgName;
    @FXML
    private ToggleGroup classGroup;
    @FXML
    private ImageView pgImage;
    @FXML
    private RadioButton rbuttonMage;
    @FXML
    private RadioButton rbuttonArcher;
    @FXML
    private RadioButton rbuttonWarrior;
    @FXML
    private StackPane sPaneImg;
    @FXML
    private VBox vboxRight;
    @FXML
    private AnchorPane ap;
    @FXML
    private ComboBox<MovementType> cbMovType;
    @FXML
    private ComboBox<AttackType> cbAtkType;

    private CharacterEditorController cec;

    private static final int NUMSTAT = 5;

    private static final int MAX = 100;
    private static final int MIN = 1;

    private static final int ERRORNUM = 99;

    /**
     * All variables defined for the WarriorDefaultValues.
     */
    private static final int WARR_STRENGHT = 78;
    private static final int WARR_DEFENSE = 67;
    private static final int WARR_HP = 100;
    private static final MovementType WARR_MOVTYPE = MovementType.FEET;
    private static final AttackType WARR_ATKTYPE = AttackType.SWORD;

    /**
     * All variables defined for the MageDefaultValues.
     */
    private static final int MAGE_STRENGHT = 70;
    private static final int MAGE_DEFENSE = 30;
    private static final int MAGE_HP = 50;
    private static final MovementType MAGE_MOVTYPE = MovementType.FLYING;
    private static final AttackType MAGE_ATKTYPE = AttackType.POLEARM;

    /**
     * All variables defined for the ArcherDefaultValues.
     */
    private static final int ARCH_STRENGHT = 60;
    private static final int ARCH_DEFENSE = 52;
    private static final int ARCH_HP = 67;
    private static final MovementType ARCH_MOVTYPE = MovementType.FEET;
    private static final AttackType ARCH_ATKTYPE = AttackType.BOW;

    /**
     * List that contains all the movement types and attack types.
     */
    private final List<MovementType> movTypes = Entities.getAllMovementTypes();
    private final List<AttackType> atkTypes = Entities.getAllAttackTypes();

    /**
     * System file separator.
     */
    private static final String SEPARATOR = System.getProperty("file.separator");

    /**
     * Home dir of the users where all the files will be saved.
     */
    private static final String HOME_DIR = System.getProperty("user.home") + SEPARATOR + ".battle-tactics" + SEPARATOR;

    /**
     * Heroes dir where the file about the Characters created will be stored.
     */
    private static final String HEROES_DIR = HOME_DIR + "heroes" + SEPARATOR;

    /**
     * Images dir where the images of the Character created will be stored.
     */
    private static final String IMAGES_DIR = HOME_DIR + "images" + SEPARATOR;

    /**
     * Error title for the Dialogs.
     */
    private static final String ERRORTITLE = "ATTENZIONE";

    /**
     * 
     * @param cec
     * 
     *            Initialization method for the CharacterEditor
     */
    public void initData(final CharacterEditorController cec) {
        if (this.cec == null) {
            this.cec = cec;
        }
        cbMovType.getItems().addAll(movTypes);
        cbAtkType.getItems().addAll(atkTypes);
        cbMovType.setValue(WARR_MOVTYPE);
        cbAtkType.setValue(WARR_ATKTYPE);
    }

    /**
     * 
     * @param event
     *            the button event
     *
     *            Methods for all the buttons in the GUI
     */
    @FXML
    private void handleButtonAction(final ActionEvent event) { // NOPMD
        System.out.println("ButtonPushed received");
        // DA UNIRE PERCHè COLLASSABILI
        if (event.getTarget() == btnMinusStrenght) {
            decreaseStat(txtForza);
        }
        if (event.getTarget() == btnPlusStrenght) {
            increaseStat(txtForza);
        }
        if (event.getTarget() == btnMinusHP) {
            decreaseStat(txtHP);
        }
        if (event.getTarget() == btnPlusHP) {
            increaseStat(txtHP);
        }
        if (event.getTarget() == btnMinusDefense) {
            decreaseStat(txtDefense);
        }
        if (event.getTarget() == btnPlusDefense) {
            increaseStat(txtDefense);
        }

        /**
         * If the button Save is pressed
         */
        if (event.getTarget() == btnSave) {

            if (!txtFPgName.getText().isEmpty() && pgImage.getImage() != null) {
                final File myObj = new File(HEROES_DIR + txtFPgName.getText() + ".txt");
                System.out.println("File Created");
                System.out.println("Immagine e': " + pgImage.getImage().toString());

                try (FileWriter myWriter = new FileWriter(myObj)) {
                    System.out.println("Creato il filewriter");

                    ImageIO.write(SwingFXUtils.fromFXImage(pgImage.getImage(), null), "png",
                            new File(IMAGES_DIR + txtFPgName.getText() + ".png"));

                    System.out.println("Immagine salvata correttamente");

                    myWriter.write(this.getCharacterToString());

                    System.out.println("Save has been successfully executed");
                    System.out.println("Closing file...");
                    myWriter.close();
                    System.out.println("File closed");

                } catch (IOException e) {
                    final Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle(ERRORTITLE);
                    alert.setHeaderText(null);
                    alert.setContentText("OPS, qualcosa e' andato storto nel salvataggio del tuo eroe!");

                    alert.showAndWait();
                }
            } else {
                final Alert alertName = new Alert(AlertType.WARNING);
                alertName.setTitle(ERRORTITLE);
                alertName.setHeaderText(null);
                alertName.setContentText("OPS, sembra che al tuo eroe manchi il nome o l'immagine!");

                alertName.showAndWait();
            }

        }

        /**
         * If the button Quit is pressed, return on the mainWindowScene.
         */
        if (event.getTarget() == btnQuit) {
            /*
             * Purtroppo l'entità del controller e quella della view non vengono
             * rinizializzate quindi non è possibile implementare il tasto indietro perchè
             * se no i personaggi caricati con il level editor e il map editor darebbero
             * 
             * MainWindow.getStage().setScene(MainWindow.getScene());
             * MainWindow.getStage().show();
             */
            Platform.exit();
        }

        /**
         * If the button Import is pressed.
         * 
         * Divide and acquire all the statistics(stored in stat[]:
         * Strenght,Health,Defense,MovType,Attack) by the standard formatting txt file
         * decided.
         * 
         * Standard format txt file: Nome: name Forza: Strenght_value Salute:
         * Health_value Defense: Defense_value MovementType: [number between 0 to 3,
         * based on the MovType list] AttackType: [number between 0 to 4, based on the
         * MovType list]
         */
        if (event.getTarget() == btnImport) {
            final FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File(HEROES_DIR));
            fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
            final File f = fileChooser.showOpenDialog(null);

            try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                if (f != null) {
                    final StringBuffer sb = new StringBuffer();
                    br.lines().forEach(line -> {
                        sb.append(line);
                        sb.append("\n");
                    });
                    final String oneline = sb.toString();
                    final String[] arrayString = oneline.split("\\s+");
                    String[] stat = new String[NUMSTAT];
                    int i = 0;
                    for (final String s : arrayString) {
                        if (s.matches(".*\\d.*")) {
                            stat[i] = s;
                            i++;
                        }
                    }

                    System.out.println(Arrays.toString(stat));
                    System.out.println(Arrays.toString(arrayString));

                    if (stat.length == NUMSTAT) {
                        txtFPgName.setText(arrayString[1]);
                        if (Integer.parseInt(stat[0]) < MAX || Integer.parseInt(stat[0]) > MIN) {
                            txtForza.setText(stat[0]);
                        } else {

                            alarmLaunch("Forza");

                        }
                        if (Integer.parseInt(stat[1]) < MAX || Integer.parseInt(stat[1]) > MIN) {
                            txtHP.setText(stat[1]);
                        } else {

                            alarmLaunch("Salute");
                        }
                        if (Integer.parseInt(stat[2]) < MAX || Integer.parseInt(stat[2]) > MIN) {
                            txtDefense.setText(stat[2]);
                        } else {
                            alarmLaunch("Difesa");
                        }
                    }

                    cbMovType.setValue(MovementType.fromInteger(Integer.parseInt(stat[stat.length - 2])));
                    cbAtkType.setValue(AttackType.fromInteger(Integer.parseInt(stat[stat.length - 1])));

                    // N.B. Ovviamente si presuppone che il file dato in input non sia stato
                    // modificato e/o creato da zero
                    // Nonostante alcuni controlli vengano comunque fatti per eventuali mal
                    // formattazioni del testo.
                    // Questo metodo non è infallibile!

                    final File directoryname = new File(IMAGES_DIR);
                    Image imageFounded = null;
                    final File[] list = directoryname.listFiles();
                    if (list != null) {
                        for (final File fil : list) {
                            System.out.println("Searching on " + directoryname.toString());
                            if ((arrayString[1] + ".png").equalsIgnoreCase(fil.getName())) {
                                System.out.println("Image Founded correctly in: " + directoryname.toString());
                                imageFounded = new Image(fil.toURI().toString());
                                pgImage.setImage(imageFounded);
                                System.out.println(fil.getParentFile());
                            }
                        }
                        if (imageFounded == null) {
                            final Alert alert = new Alert(AlertType.WARNING);
                            alert.setTitle(ERRORTITLE);
                            alert.setHeaderText(null);
                            alert.setContentText("Ops, non è stato possibile trovare l'immagine del tuo eroe!");

                            alert.showAndWait();
                        }

                    }
                }

            } catch (IOException exc) {
                final Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle(ERRORTITLE);
                alert.setHeaderText(null);
                alert.setContentText("OPS, qualcosa e' andato storto nell'import del tuo eroe!");

                alert.showAndWait();
            }
        }

        /**
         * If the Load button is pressed Let the user choose a png file for the
         * Character image
         */
        if (event.getTarget() == btnLoad) {

            final FileChooser fileChooser = new FileChooser();

            fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Png files", "*.png"));

            final File f = fileChooser.showOpenDialog(null);

            if (f != null) {

                final Image imageLoaded = new Image(f.toURI().toString());

                pgImage.setImage(imageLoaded);

            } else {

                System.out.println("Nessun file selezionato dall'utente");
            }

        }
    }

    /**
     * 
     * @param e
     *            Radiobutton on the GUI
     */
    @FXML
    private void radioButtonListener(final ActionEvent e) { // NOPMD
        if (rbuttonWarrior.isSelected()) {
            this.setWarriorStat();
        }
        if (rbuttonMage.isSelected()) {
            this.setMageStat();
        }
        if (rbuttonArcher.isSelected()) {
            this.setArcherStat();
        }
    }

    /**
     * Method that set automatically all the default value for the Mage.
     */
    private void setMageStat() {
        txtForza.setText(Integer.toString(MAGE_STRENGHT));
        txtHP.setText(Integer.toString(MAGE_HP));
        txtDefense.setText(Integer.toString(MAGE_DEFENSE));
        cbMovType.setValue(MAGE_MOVTYPE);
        cbAtkType.setValue(MAGE_ATKTYPE);
    }

    /**
     * Method that set automatically all the default value for the Mage.
     */
    private void setArcherStat() {
        txtForza.setText(Integer.toString(ARCH_STRENGHT));
        txtHP.setText(Integer.toString(ARCH_HP));
        txtDefense.setText(Integer.toString(ARCH_DEFENSE));
        cbMovType.setValue(ARCH_MOVTYPE);
        cbAtkType.setValue(ARCH_ATKTYPE);
    }

    /**
     * Method that set automatically all the default value for the Mage.
     */
    private void setWarriorStat() {
        txtForza.setText(Integer.toString(WARR_STRENGHT));
        txtHP.setText(Integer.toString(WARR_HP));
        txtDefense.setText(Integer.toString(WARR_DEFENSE));
        cbMovType.setValue(WARR_MOVTYPE);
        cbAtkType.setValue(WARR_ATKTYPE);
    }

    /**
     * 
     * @param errorType
     *            string that rapresent which attribute is too big if the txt taken
     *            has value too big
     */
    private void alarmLaunch(final String errorType) {
        final Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(ERRORTITLE);
        alert.setHeaderText(null);
        alert.setContentText("OPS, sembrerebbe che i tuoi punti " + errorType + " siano un po sbilanciati");

        alert.showAndWait();
    }

    /**
     * 
     * @param textstat
     *            the txt number that rapresents one of the attributes:
     *            Strenght,Health,Defense
     */
    private void decreaseStat(final Text textstat) {
        if (MIN < Integer.parseInt(textstat.getText())) {
            textstat.setText(Integer.toString(Integer.parseInt(textstat.getText()) - 1));
        }
    }

    /**
     * 
     * @param textstat
     *            the txt number that rapresents one of the attributes:
     *            Strenght,Health,Defense
     */
    private void increaseStat(final Text textstat) {
        if (Integer.parseInt(textstat.getText()) < MAX) {
            textstat.setText(Integer.toString(Integer.parseInt(textstat.getText()) + 1));
        }
    }

    /**
     * 
     * @return txt String for the storing of the heroes in the txt file
     */
    private String getCharacterToString() {
        return "Nome: " + txtFPgName.getText() + "\n" + "Forza: " + txtForza.getText() + "\n" + "Salute: "
                + txtHP.getText() + "\n" + "Difesa: " + txtDefense.getText() + "\n" + "MovementType: "
                + convertMovementType(cbMovType.getValue()) + "\n" + "AttackType: "
                + convertAttackType(cbAtkType.getValue());
    }

    /**
     * 
     * @param atk
     * @return the specified attacktype constant value of the enum index
     */
    private int convertAttackType(final AttackType atk) {

        if (atk.equals(AttackType.NONE)) {
            return AttackType.Constants.DEFAULT_NONE_VALUE;
        }
        if (atk.equals(AttackType.SWORD)) {
            return AttackType.Constants.DEFAULT_SWORD_VALUE;
        }
        if (atk.equals(AttackType.POLEARM)) {
            return AttackType.Constants.DEFAULT_POLEARM_VALUE;
        }
        if (atk.equals(AttackType.BOW)) {
            return AttackType.Constants.DEFAULT_BOW_VALUE;
        }
        return ERRORNUM;
    }

    /**
     * 
     * @param mov
     * @return the specified MovementType constant value of the enum index
     */
    private int convertMovementType(final MovementType mov) {
        if (mov.equals(MovementType.NONE)) {
            return MovementType.Constants.DEFAULT_NONE_VALUE;
        }
        if (mov.equals(MovementType.FEET)) {
            return MovementType.Constants.DEFAULT_FEET_VALUE;
        }
        if (mov.equals(MovementType.FLYING)) {
            return MovementType.Constants.DEFAULT_FLYING_VALUE;
        }
        return ERRORNUM;
    }
}
