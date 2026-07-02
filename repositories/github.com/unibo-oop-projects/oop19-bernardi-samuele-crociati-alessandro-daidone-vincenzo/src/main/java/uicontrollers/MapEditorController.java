package uicontrollers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * 
 * Controller for the map editor view.
 *
 */
public class MapEditorController {

    @FXML
    private Button btnSave;
    @FXML
    private Button btnImport;
    @FXML
    private Button btnQuit;
    @FXML
    private GridPane grid;
    @FXML
    private Button btnSmallSize;
    @FXML
    private Button btnMediumSize;
    @FXML
    private Button btnHighSize;
    @FXML
    private RadioButton rbGrass;
    @FXML
    private RadioButton rbWater;
    @FXML
    private RadioButton rbObstacles;
    @FXML
    private RadioButton rbSpawnT1;
    @FXML
    private RadioButton rbMountain;
    @FXML
    private RadioButton rbObsacle2;
    @FXML
    private FlowPane southflowpane;
    @FXML
    private TextField txtMapName;

    /**
     * System separator for the system.
     */
    private static final String SEPARATOR = System.getProperty("file.separator");

    /**
     * path images for the images used in the map.
     */
    private static final String WATER_PATH = "images/water.png";
    private static final String GRASS_PATH = "images/earth.png";
    private static final String PORTAL_PATH = "images/portalstone.jpg";
    private static final String OBST_PATH = "images/Hwall.png";
    private static final String OBST2_PATH = "images/Vwall.png";
    private static final String MOUNTAIN_PATH = "images/mountain.png";

    /**
     * Chars used for the TerrainType in the map.
     */
    private static final String GRASS_CHAR = "E";
    private static final String MOUNTAIN_CHAR = "M";
    private static final String WATER_CHAR = "W";
    private static final String SPAWN_CHAR = "S";
    private static final String HWALL_CHAR = "O";
    private static final String VWALL_CHAR = "T";

    /**
     * Standard size(col,row) for the MINIMUM map.
     */
    private static final int ROWS_MIN_MAP = 6;
    private static final int COL_MIN_MAP = 6;

    /**
     * Standard size(col,row) for the MEDIUM map.
     */
    private static final int ROWS_MED_MAP = 9;
    private static final int COL_MED_MAP = 9;

    /**
     * Standard size(col,row) for the standard MAXIMUM map.
     */
    private static final int ROWS_MAX_MAP = 12;
    private static final int COL_MAX_MAP = 12;

    /**
     * Percent size for the columns and the rows in the grid.
     */
    private static final int PERC_COL_GRID = 15;
    private static final int PERC_ROW_GRID = 15;

    /**
     * Minimum size for the button in the grid.
     */
    private static final int MIN_HEIGHT_BUTTON = 20;
    private static final int MIN_WIDTH_BUTTON = 30;

    /**
     * Home dir of the users where all the files will be saved.
     */
    private static final String HOME_DIR = System.getProperty("user.home") + SEPARATOR + ".battle-tactics" + SEPARATOR;

    /**
     * Maps dir where the file about the maps created will be stored.
     */
    private static final String MAPS_DIR = HOME_DIR + "maps" + SEPARATOR;

    /**
     * Standard error message and error type for the Dialogs used.
     */
    private static final String ERRORMESSAGE = "ATTENZIONE!!!";
    private static final String ERRORTITLE = "ERRORE";

    /**
     * Variables used during the program for the removed feature of the two
     * spawners.
     */
    /*
     * private int portals; private int indexR; private int indexC; private int
     * prevR; private int prevC;
     */

    private MapEditorController mec;

    /**
     * 
     * @param mec
     */
    public final void initData(final MapEditorController mec) {
        if (this.mec == null) {
            this.mec = mec;
        }
    }

    /**
     * 
     * @param e
     *            the button target that has thrown the event
     */
    @FXML
    private void handleButtonEventMapEditor(final ActionEvent e) { // NOPMD
        int fileNo = 0;
        boolean delete = false;

        /**
         * If the pressed button is the one for SAVE
         */
        if (e.getTarget() == btnSave) {
            final String s = txtMapName.getText();
            if (!s.isEmpty()) {
                // Creo il file dove scrivere la mappa da salvare
                File myObj = new File(MAPS_DIR + txtMapName.getText() + ".txt");
                try {
                    // Controllo che il file non esista già
                    if (myObj.exists() && !myObj.isDirectory()) {
                        // Se esiste incremento la variabile "FileNo" e rinizializzo myObj con il nuovo
                        // nome file
                        while (myObj.exists()) {
                            fileNo++;
                            myObj = new File(MAPS_DIR + txtMapName.getText() + "(" + fileNo + ").txt");
                        }
                        // Se il file non esiste allora semplicemente creo il nuovo file
                    } else if (!myObj.createNewFile()) {
                        System.out.println("Il file esisteva già");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                // Creo il writer assegnando il nome file di myObj
                try (FileWriter myWriter = new FileWriter(myObj)) {
                    System.out.println("Proseguo con la scrittura");
                    // Debug Message
                    System.out.println("Saving...");
                    final int columnsNo = grid.getColumnCount();
                    int index = 0;
                    for (final Node bottone : grid.getChildren()) {
                        // tmp = (Button) bottone;
                        if (index >= columnsNo) {
                            myWriter.write(System.lineSeparator());
                            index = 0;
                        }
                        // Controllo se eventuali caselle possano essere vuote/non assegnate,
                        // nel caso affermativo termino il ciclo
                        if (bottone.getId() == null) {
                            final Alert alert = new Alert(AlertType.ERROR);
                            alert.setTitle(ERRORTITLE);
                            alert.setHeaderText(ERRORMESSAGE);
                            alert.setContentText("Alcune caselle risultano essere vuote o non assegnate!");
                            alert.showAndWait();
                            delete = true;
                            break;
                        }
                        // Prendi l'id del Nodo bottone e scrivilo su file
                        myWriter.write(bottone.getId() + " ");
                        index++;
                    }
                    // Controllo infine che siano presenti almeno 2 portali
                    /*
                     * OPZIONE AGGIUNTIVA CHE ABBIAMO DECISO DI TOGLIERE IN CUI SONO PRESENTI SOLO
                     * DUE SPAWNER, UNO PER OGNI TEAM, UTILIZZATI PER DISTRIBUIRE INTORNO I MEMBRI
                     * DEL TEAM 1 E DEL TEAM 2 PER QUESTIONI LOGISTICHE E DI DIFFICOLTà è STATO
                     * SCELTO POI DI RIMUOVERE QUESTA FUNZIONE if (portalNo < 2) { delete = true;
                     * final Alert alert = new Alert(AlertType.ERROR); alert.setTitle(ERRORTITLE);
                     * alert.setHeaderText(ERRORMESSAGE);
                     * alert.setContentText("Un portale sembra non essere stato assegnato!");
                     * alert.showAndWait(); }
                     */
                    // Controllo che il file non sia da eliminare perchè incompleto
                    // In caso affermativo elimino la mappa creata
                    if (delete) {
                        myWriter.close();
                        if (!myObj.delete()) {
                            final Alert alert = new Alert(AlertType.ERROR);
                            alert.setTitle(ERRORTITLE);
                            alert.setHeaderText(ERRORMESSAGE);
                            alert.setContentText("Errore nell'eliminazione del file!");
                            alert.showAndWait();
                        }
                    } else {
                        System.out.println("Saved Successfully");
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else {
                final Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle(ERRORTITLE);
                alert.setHeaderText(ERRORMESSAGE);
                alert.setContentText("Sembrerebbe che la tua mappa non abbia un nome!");
                alert.showAndWait();
            }
        }

        /**
         * If the pressed button is the one for the save
         */
        if (e.getTarget() == btnImport) {
            final FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleziona mappa");
            fileChooser.setInitialDirectory(
                    new File(MAPS_DIR));
            fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));

            final File file = fileChooser.showOpenDialog(null);
            if (file != null) {

                try (BufferedReader read = new BufferedReader(new FileReader(file))) {
                    final List<String> line = new ArrayList<>();
                    String fileline;
                    int fileRows = 0;
                    int fileColumns = 0;
                    fileline = read.lines().collect(Collectors.joining("\n"));
                    final List<String> lines = Stream.of(fileline.split("\n")).collect(Collectors.toList());
                    for (final String s : lines) {
                        line.addAll(Arrays.asList(s.split(" ")));
                        fileColumns = s.split(" ").length;
                        fileRows++;
                    }
                    createGrid(fileColumns, fileRows);
                    creatingImportGrid(line);
                } catch (IOException e1) {
                    e1.printStackTrace();
                    System.out.println("Errore nella lettura del file");
                }
            } else {
                System.out.println("Nessun file di import selezionato");
            }
        }
        /**
         * If the pressed button is the one for QUIT
         */
        if (e.getTarget() == btnQuit) {

            /*
             * Purtroppo l'entità del controller e quella della view non vengono
             * rinizializzate quindi non è possibile implementare il tasto indietro perchè
             * se no i personaggi caricati con il level editor e il map editor darebbero
             * problemi con le risorse già allocate e prelevate
             * 
             * Stage stage = (Stage) btnQuit.getScene().getWindow(); stage.close();
             * 
             * MainWindow.getStage().setScene(MainWindow.getScene());
             * MainWindow.getStage().show();
             */

            Platform.exit();
        }
    }

    /**
     * 
     * @param event
     *            the button that trigger the Size of the GridMap. Small_Size : 6 x
     *            6 Medium_Size: 8 x 6 Max_Size: 12 x 10
     */
    @FXML
    private void eventButtonMapSize(final ActionEvent event) { // NOPMD
        if (event.getTarget() == btnSmallSize) {
            createGrid(COL_MIN_MAP, ROWS_MIN_MAP);
        }
        if (event.getTarget() == btnMediumSize) {
            createGrid(COL_MED_MAP, ROWS_MED_MAP);
        }
        if (event.getTarget() == btnHighSize) {
            createGrid(COL_MAX_MAP, ROWS_MAX_MAP);
        }
    }

    /**
     * 
     * @param columns
     * @param rows
     * 
     *            Method used for generating the map grid through the number of
     *            columns and the number of rows.
     */
    private void createGrid(final int columns, final int rows) {
        // Ripulisco tutta la griglia precedente
        grid.getChildren().clear();
        grid.getColumnConstraints().clear();
        grid.getRowConstraints().clear();
        // Creo la griglia
        for (int i = 0; i < rows; i++) {
            grid.getRowConstraints().add(new RowConstraints());
            grid.getRowConstraints().get(i).setPercentHeight(PERC_ROW_GRID);
            for (int j = 0; j < columns; j++) {
                final Button bottone = new Button(/* "( " + Integer.toString(i) + "," + Integer.toString(j) + " )" */);
                bottone.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                // Aggiungo un listener con il corrispettivo eventhandler per ogni bottone
                // creato nella griglia. In modo che possa essere modificato
                // nel momento in cui l'utente vuole usare le caselle per creare la mappa
                bottone.setOnAction((ActionEvent event) -> {
                    tileModify(event);
                });
                if (i == 0) {
                    grid.getColumnConstraints().add(new ColumnConstraints());
                    grid.getColumnConstraints().get(j).setPercentWidth(PERC_COL_GRID);
                }
                GridPane.setConstraints(bottone, j, i);
                bottone.setMinSize(MIN_HEIGHT_BUTTON, MIN_WIDTH_BUTTON);
                grid.getChildren().add(bottone);
                GridPane.setHgrow(bottone, Priority.ALWAYS);
                GridPane.setVgrow(bottone, Priority.ALWAYS);
            }
        }
    }

    /**
     * 
     * @param e
     *            the button in the gridmap that has requested to be modified.
     */
    private void tileModify(final ActionEvent e) {
        final Button tmp = (Button) e.getTarget();
        tmp.setText("");
        if (rbGrass.isSelected()) {
            imageTile(GRASS_PATH, tmp);
            tmp.setId(GRASS_CHAR);
        }
        if (rbMountain.isSelected()) {
            imageTile(MOUNTAIN_PATH, tmp);
            tmp.setId(MOUNTAIN_CHAR);
        }
        if (rbWater.isSelected()) {
            imageTile(WATER_PATH, tmp);
            tmp.setId(WATER_CHAR);
        }
        // Ostacolo che si riferisce al muro orizzontale
        if (rbObstacles.isSelected()) {
            imageTile(OBST_PATH, tmp);
            tmp.setId(HWALL_CHAR);
        }
        /*
         * if (rbSpawnT1.isSelected()) { if (portals > 1) { removePortal(prevC, prevR);
         * prevR = indexR; prevC = indexC; } if (portals == 0) { prevR =
         * GridPane.getRowIndex(tmp); prevC = GridPane.getColumnIndex(tmp); } indexC =
         * GridPane.getColumnIndex(tmp); indexR = GridPane.getRowIndex(tmp); portals++;
         * imageTile(PORTAL_PATH, tmp); tmp.setId(SPAWN_CHAR); }
         */
        if (rbObsacle2.isSelected()) {
            imageTile(OBST2_PATH, tmp);
            tmp.setId(VWALL_CHAR);
        }
    }

    /**
     * 
     * @param imagepath
     * @param modifiedbutton
     * 
     *            Method used for the applying of the image in the button.
     */
    private void imageTile(final String imagepath, final Button modifiedbutton) {
        final Image image = new Image(imagepath, true);
        final ImageView imageView = new ImageView(image);
        modifiedbutton.setGraphic(imageView);
        imageView.fitWidthProperty().bind(modifiedbutton.widthProperty());
        imageView.fitHeightProperty().bind(modifiedbutton.heightProperty());
    }

    /**
     * 
     * @param indexColumn
     * @param indexRow
     * 
     *            Method part of the spawner part: Unused.
     */
    /*
     * private void removePortal(final int indexColumn, final int indexRow) {
     * 
     * for (final Node node : grid.getChildren()) { if
     * (GridPane.getRowIndex(node).equals(indexRow) &&
     * GridPane.getColumnIndex(node).equals(indexColumn)) { final Button prevPortal
     * = (Button) node; prevPortal.setGraphic(null); break; } } }
     */

    /**
     * 
     * @param file
     *            file txt of the import.
     *
     *            Method for matching the char in the txt file with the image.
     */
    private void creatingImportGrid(final List<String> file) {
        int index = 0;
        String idnode;
        for (final Node b : grid.getChildren()) {
            b.setId(file.get(index));
            idnode = b.getId();
            if (GRASS_CHAR.equals(idnode)) {
                imageTile(GRASS_PATH, (Button) b);
            }
            if (MOUNTAIN_CHAR.equals(idnode)) {
                imageTile(MOUNTAIN_PATH, (Button) b);
            }
            if (WATER_CHAR.equals(idnode)) {
                imageTile(WATER_PATH, (Button) b);
            }
            if (SPAWN_CHAR.equals(idnode)) {
                imageTile(PORTAL_PATH, (Button) b);
            }
            if (HWALL_CHAR.equals(idnode)) {
                imageTile(OBST_PATH, (Button) b);
            }
            if (VWALL_CHAR.equals(idnode)) {
                imageTile(OBST2_PATH, (Button) b);
            }
            index++;
        }
    }
}
