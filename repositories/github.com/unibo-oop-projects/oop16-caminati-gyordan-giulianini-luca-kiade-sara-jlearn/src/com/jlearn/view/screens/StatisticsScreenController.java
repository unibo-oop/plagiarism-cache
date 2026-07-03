package com.jlearn.view.screens;

import java.io.IOException;
import java.util.List;

import com.jfoenix.controls.JFXComboBox;
import com.jlearn.controller.ControllerExercise;
import com.jlearn.controller.ControllerLogic;
import com.jlearn.controller.ControllerLogicImpl;
import com.jlearn.model.users.User;
import com.jlearn.model.utilities.Pair;
import com.jlearn.view.FXEnvironment;
import com.jlearn.view.screens.abstracts.AbstractPersonScreenController;
import com.jlearn.view.screens.abstracts.AbstractStatisticsScreenController;
import com.jlearn.view.utilities.enums.NotificationType;
import com.jlearn.view.utilities.enums.NotificationType.Duration;
import com.jlearn.view.utilities.enums.SoundFX;

import eu.hansolo.enzo.notification.NotificationEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

/**
 * Theory controller for the Theory.
 * <p>
 * This class represent a symple controller for a {@link FXMLScreens}. The fxml is atached to the controller by the
 * {@link FXEnvironment} and chached by the {@link FXMLLoader}.
 * <p>
 * This controller is connected to a {@link ControllerExercise} to implement a MVC design.
 *
 */
public final class StatisticsScreenController extends AbstractStatisticsScreenController {

    // ################################################ FXML VAR ###############################################
    @FXML
    private TabPane tabPane;
    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private LineChart<String, Integer> lineChart;
    @FXML
    private PieChart pieChart;
    @FXML
    private JFXComboBox<String> comboLineLevels, comboPieLevel, comboPieModality;
    // ################################################ LOCAL VAR ###############################################
    private final FXEnvironment environment;
    private final ControllerLogic controller;
    private final PersonScreenController personController;
    private static StatisticsScreenController singleton;

    /**
     * The constructor of the controller.
     *
     * @param environment
     *            the {@link FXEnvironment}
     * @param controller
     *            the {@link ControllerExercise}
     * @throws IOException
     *             the exception cause by the loading of the {@link FXMLLoader}
     */
    private StatisticsScreenController() {
        super(FXEnvironment.getInstance());
        this.controller = ControllerLogicImpl.getInstance();
        this.environment = FXEnvironment.getInstance();
        this.personController = new PersonScreenController(this.environment, this.controller);

        this.environment.loadScreen(FXMLScreens.STATISTICS, this);
    }

    /**
     * Get the {@link StatisticsScreenController} instance.
     *
     * @return the {@link StatisticsScreenController}
     */
    public static StatisticsScreenController getInstance() {
        synchronized (StatisticsScreenController.class) {
            if (singleton == null) {
                singleton = new StatisticsScreenController();
            }
        }
        return singleton;
    }

    /**
     * Init method to be called after the constructor. This is a symple method of the {@link FXML}.
     *
     * @throws IOException
     *             Expception
     */

    @Override
    @FXML
    protected void initialize() {
        super.initialize();
        this.tabChangedListener();
    }

    // ################################################ FROM CONTROLLER ###############################################
    /**
     * Update the first {@link BarChart}.
     *
     * @param lista
     *            the {@link List}
     */
    @Override
    public void updateBarChartModality(final List<Pair<String, List<Pair<String, Integer>>>> lista) {
        final XYChart.Series<String, Integer> easySerie = new XYChart.Series<>();
        final XYChart.Series<String, Integer> mediumSerie = new XYChart.Series<>();
        final XYChart.Series<String, Integer> hardSerie = new XYChart.Series<>();
        final ObservableList<Data<String, Integer>> listEasy = FXCollections.observableArrayList();
        lista.get(0).getY().stream().forEach(t -> {
            listEasy.add(new Data<>(t.getX(), t.getY()));
        });

        final ObservableList<Data<String, Integer>> listMedium = FXCollections.observableArrayList();
        lista.get(1).getY().stream().forEach(t -> {
            listMedium.add(new Data<>(t.getX(), t.getY()));
        });
        final ObservableList<Data<String, Integer>> listHard = FXCollections.observableArrayList();
        lista.get(2).getY().stream().forEach(t -> {
            listHard.add(new Data<>(t.getX(), t.getY()));
        });

        easySerie.getData().addAll(listEasy);
        easySerie.setName("Easy");
        mediumSerie.getData().addAll(listMedium);
        mediumSerie.setName("Medium");
        hardSerie.getData().addAll(listHard);
        hardSerie.setName("Hard");

        this.barChart.getData().clear();
        this.barChart.getData().add(easySerie);
        this.barChart.getData().add(mediumSerie);
        this.barChart.getData().add(hardSerie);
    }

    @Override
    public void updateLineChart(final List<Pair<String, Integer>> lista, final String level) {
        final XYChart.Series<String, Integer> serie = new XYChart.Series<>();
        final ObservableList<Data<String, Integer>> list = FXCollections.observableArrayList();
        lista.stream().forEach(t -> {
            list.add(new Data<>(t.getX(), t.getY()));
        });
        serie.getData().addAll(list);
        serie.setName(level);
        this.lineChart.getData().add(serie);
    }

    @Override
    public void updatePieChart(final List<Pair<String, Integer>> lista) {

        final ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
        for (final Pair<String, Integer> elem : lista) {
            list.add(new PieChart.Data(elem.getX(), elem.getY()));
        }
        this.pieChart.getData().clear();
        this.pieChart.getData().addAll(list);

    }

    @FXML
    private void clearLineChart(final ActionEvent event) { // NOPMD
        this.lineChart.getData().clear();
    }

    @FXML
    private void clearPieChart(final ActionEvent event) { // NOPMD
        this.pieChart.getData().clear();
    }

    /**
     * Set the achivemetns labels.
     *
     * @param easy
     *            the easy level
     * @param medium
     *            the medium level
     * @param hard
     *            the hard level
     */
    @Override
    public void setAchivements(final int easy, final int medium, final int hard) {
        this.personController.setAchivements(easy, medium, hard);

    }

    @Override
    public void setPersonStatistics(final User user) {
        this.personController.setPersonBio(user);
    }

    @Override
    public void setProfileImageView(final Image image) {
        this.personController.setProfileImageView(image);
    }

    @Override
    public void showNotificationPopup(final String title, final String message, final Duration secondsDuration,
            final NotificationType notiType, final EventHandler<NotificationEvent> ev) {
        this.environment.showNotificationPopup(title, message, secondsDuration, notiType, ev);
    }
    // ################################################ TO CONTROLLER ###############################################

    @FXML // NOPMD
    void lineChartComboLevelSelected(final ActionEvent event) {
        this.controller.updateLineChart(this.comboLineLevels.getSelectionModel().getSelectedIndex());
    }

    @FXML // NOPMD
    void updatePie(final ActionEvent event) {
        this.controller.updatePieChart(this.comboPieLevel.getSelectionModel().getSelectedIndex(),
                this.comboPieModality.getSelectionModel().getSelectedIndex());
    }

    private void tabChangedListener() {
        this.tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (this.tabPane.getTabs().get(1).equals(newValue)) {
                this.controller.updateModalityStats();
            } else if (this.tabPane.getTabs().get(2).equals(newValue)) {
                this.lineChart.getData().clear();
            } else if (this.tabPane.getTabs().get(3).equals(newValue)) {
                this.pieChart.getData().clear();
            }
            this.playAudio(SoundFX.SWIPE);
        });
    }

    @Override
    public void playAudio(final SoundFX sound) {
        this.environment.playAudioFXClip(sound);
    }

    // ###################################################___PERSON___################################################à
    /**
     * Person Controller for {@link FXML}.
     */
    public class PersonScreenController extends AbstractPersonScreenController {
        private static final double IMAGE_WIDTH = 150;
        private static final double IMAGE_HEIGHT = 130;
        // ############################################### FXML VAR ##########################################
        @FXML
        private Pane paneImageView;
        @FXML
        private Label labelName, labelSurname, labelNickname, labelAge, labelEmail, labelTelephone,
                nicknamePhotoImage; // NOPMD
        @FXML
        private Label easyLabel, mediumLabel, hardLabel;
        // ################################################### LOCAL VAR ########################################
        private final ControllerLogic controller;
        private final FXEnvironment environment;

        /**
         * Constructor.
         *
         * @param envirolment
         *            the {@link FXEnvironment}
         * @param controller
         *            the {@link ControllerLogic}
         */
        public PersonScreenController(final FXEnvironment envirolment, final ControllerLogic controller) {
            super(envirolment);
            this.environment = envirolment;
            this.controller = controller;
            this.environment.loadScreen(FXMLScreens.PERSON, this);
            this.controller.toString();
        }

        @Override
        @FXML
        protected void initialize() {
            super.initialize();
        }

        // ########################################## FROM CONTROLLER #############################################

        private void setAchivements(final int easy, final int medium, final int hard) {
            this.easyLabel.setText(" " + easy);
            this.mediumLabel.setText(" " + medium);
            this.hardLabel.setText(" " + hard);

        }

        /**
         * Set the Person Bios.
         *
         * @param user
         *            the {@link User}
         */
        private void setPersonBio(final User user) {
            this.labelName.setText(user.getName());
            this.labelSurname.setText(user.getSurname());
            this.labelNickname.setText(user.getNickname());
            this.labelAge.setText(" " + user.getAge());
            this.labelTelephone.setText(user.getTel());
            this.labelEmail.setText(user.getEmail());
            this.nicknamePhotoImage.setText(user.getNickname());
        }

        /**
         * Set the Profile Image.
         *
         * @param image
         *            the {@link Image}
         */
        private void setProfileImageView(final Image image) {
            this.paneImageView.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    new BackgroundSize(IMAGE_WIDTH, IMAGE_HEIGHT, false, false,
                            false, false))));
        }
        // ############################################ TO CONTROLLER ############################################

        @Override
        public void playAudio(final SoundFX sound) {
            this.environment.playAudioFXClip(sound);
        }
    }
}
