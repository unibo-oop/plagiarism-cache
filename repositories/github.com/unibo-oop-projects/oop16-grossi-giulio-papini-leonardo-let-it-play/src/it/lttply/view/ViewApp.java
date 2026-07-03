package it.lttply.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.controlsfx.control.Rating;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.application.PlatformImpl;

import eu.hansolo.enzo.notification.Notification;
import it.lttply.controller.HomeController;
import it.lttply.controller.RefreshType;
import it.lttply.controller.SingleMediaController;
import it.lttply.controller.SingleMovieController;
import it.lttply.model.utils.ConsolePrinter;
import it.lttply.view.fxmlscreens.FXMLScreens;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.util.Pair;

/**
 * Class for showing the view.
 */
@SuppressWarnings("restriction")
public class ViewApp implements View {

    private final FXEnvironment environment;
    private final ControllerHome controllerHomeView;
    private final ControllerMovies controllerMoviesView;
    private final ControllerTvSeries controllerTVSeriesView;
    private HomeController controller;
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final double width = screenSize.getWidth() / 1.2;
    private final double height = screenSize.getHeight() / 1.2;
    private Stage primaryStage;
    private Alert alert;
    private static final Object LOCK = new Object();

    /**
     * Sets the controller for each view.
     *
     * @throws IOException
     *             thrown if an exception occurs when the view can't start
     */
    public ViewApp() throws IOException {
        PlatformImpl.startup(() -> {
        });
        environment = new FXEnvironmentConcrete();
        controllerHomeView = new ControllerHome(environment);
        controllerMoviesView = new ControllerMovies(environment);
        controllerTVSeriesView = new ControllerTvSeries(environment);

    }

    @Override
    public void initView() throws IOException {
        Platform.runLater(() -> {
            try {
                primaryStage = new Stage();
                primaryStage.setTitle("Let-IT-Play Media Manager");
                primaryStage.setMinWidth(width);
                primaryStage.setMinHeight(height);
                environment.start(primaryStage);

            } catch (final Exception e) {
                ConsolePrinter.getPrinter().printlnError("Unable to load graphic environment.");
                e.printStackTrace();
            }
            controllerHomeView.show();
        });
    }

    @Override
    public void loadTvSeriesCover(final String imagePath, final String text, final String seriesID) {
        controllerTVSeriesView.addCover(imagePath, text);
    }

    @Override
    public void loadMoviesCover(final String imagePath, final String text, final String movieID) {
        controllerMoviesView.addCover(imagePath, text, movieID);
    }

    @Override
    public void setObserver(final HomeController contr) {
        controller = contr;
        controllerHomeView.moviesFolder.setText(controller.getMoviePath());
        // controllerHomeView.tvSeriesFolder.setText(controller.getTVSeriePath());
    }

    @Override
    public void clearMovies() {
        controllerMoviesView.clear();
    }

    @Override
    public void clearTvSeries() {
        controllerTVSeriesView.clear();
    }

    @Override
    public void refreshMovies() {
        controllerMoviesView.refresh();
    }

    @Override
    public void reloadMovies() {
        controllerMoviesView.reload();

    }

    @Override
    public void hardRefreshTvSeries() {
        // controllerTVSeriesView.refresh();

    }

    @Override
    public void refreshHome() {
        controllerHomeView.refresh();

    }

    @Override
    public void hardrefreshHome() {
        controllerHomeView.reload();

    }

    @Override
    public void blockView() {
        synchronized (LOCK) {
            alert = new Alert(AlertType.NONE);
            alert.initStyle(StageStyle.UNDECORATED);
            alert.setTitle("Let-IT-play");
            alert.setResizable(true);
            alert.setGraphic(new ImageView("/view/load.gif"));
            alert.setContentText("\n\n                                       PLEASE WAIT...");
            alert.show();
        }
    }

    @Override
    public void unlockView() {
        synchronized (LOCK) {
            alert.setAlertType(AlertType.INFORMATION);
            alert.close();
        }
    }

    @Override
    public void showError(final String errorMessage) {
        Notification.Notifier.setWidth((Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2));
        Notification.Notifier.INSTANCE.notify(new Notification("Error!", errorMessage, Notification.ERROR_ICON));

    }

    @Override
    public void setMoviesPath(final String moviesFolder) {
        controllerHomeView.setMoviesFolder(moviesFolder);
    }

    /*
     * public void setTvSeriesPath(final String tvSeriesFolder) {
     * controllerHomeView.setTvSeriesFolder(tvSeriesFolder); }
     */

    /**
     * Class of the home view.
     */
    public class ControllerHome {

        private static final double DURATE = 3000;

        @FXML
        private JFXButton btnSettings;

        @FXML
        private ImageView imgSearch;

        @FXML
        private ImageView imgRefresh;

        @FXML
        private ImageView imgSetting;

        @FXML
        private ImageView imgHardRefresh;

        @FXML
        private ImageView imgLogo;

        @FXML
        private HBox settingsmenu;

        @FXML
        private TextField moviesFolder;

        @FXML
        private ImageView imgHome;

        @FXML
        private JFXButton btnHome;

        @FXML
        private JFXButton btnRefresh;

        @FXML
        private JFXButton btnReload;

        private String moviePath;
        private final FXEnvironment environment;
        private Boolean initIcons = false;

        @FXML
        private void initialize() throws IOException, InterruptedException {
            imgLogo.setImage(new Image("view/logo.png"));
            final FadeTransition fadeout1 = new FadeTransition(Duration.millis(DURATE), imgLogo);
            fadeout1.setFromValue(0);
            fadeout1.setToValue(1);
            fadeout1.setCycleCount(1);
            fadeout1.play();
            btnSettings.setTooltip(new Tooltip("Setting menù"));

            btnSettings.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                if (settingsmenu.isDisable()) {
                    settingsmenu.setDisable(false);
                    settingsmenu.setOpacity(1);
                } else {
                    settingsmenu.setDisable(true);
                    settingsmenu.setOpacity(0);
                }
            });

        }

        ControllerHome(final FXEnvironment environment) throws IOException {
            this.environment = environment;
            this.environment.loadScreen(FXMLScreens.HOME, this);
        }

        /**
         * Loads the icons and display the view.
         */
        public void show() {
            imgSearch.setImage(new Image("view/search.png"));
            imgSetting.setImage(new Image("view/settings.png"));
            imgRefresh.setImage(new Image("view/refresh.png"));
            imgHardRefresh.setImage(new Image("view/hard_refresh.png"));
            imgHome.setImage(new Image("view/home.png"));
            btnReload.setTooltip(new Tooltip("Reload movies"));
            btnRefresh.setTooltip(new Tooltip("Refresh movies"));
            btnSettings.setTooltip(new Tooltip("Setting menù"));
            environment.displayScreen(FXMLScreens.HOME);

        }

        @FXML
        private void goMovies() throws IOException {
            if (!initIcons) {
                controllerMoviesView.initMovieIcon();
                initIcons = true;
                controller.refresh(RefreshType.MINIMAL);
                controllerMoviesView.initSearch();
            }
            environment.displayScreen(FXMLScreens.MOVIES);
        }

        @FXML
        private void goTvSeries() throws IOException {
            environment.displayScreen(FXMLScreens.TVSERIES);
        }

        @FXML
        private void selectMoviesPath() {
            final DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("This is my file ch");
            // Show open file dialog
            final File file = directoryChooser.showDialog(null);
            if (file != null) {
                moviePath = file.getPath();
                moviesFolder.setText(file.getPath());

            }
        }

        @FXML
        private void setMoviesFolder(final String path) {
            moviesFolder.setText(path);
        }

        /*
         * @FXML private void selectTvSeriesPath() { final DirectoryChooser
         * directoryChooser = new DirectoryChooser();
         * directoryChooser.setTitle("This is my file ch"); // Show open file
         * dialog final File file = directoryChooser.showDialog(null); if (file
         * != null) { tvSeriesPath = file.getPath();
         * tvSeriesFolder.setText(file.getPath()); } }
         */

        /*
         * @FXML private void setTvSeriesFolder(final String path) {
         * tvSeriesFolder.setText(path); }
         */

        @FXML
        private void save() {
            moviePath = moviesFolder.getText();
            controller.setMoviePath(moviePath);
            // controller.setTVSeriePath(tvSeriesPath);
            Notification.Notifier.INSTANCE
                    .notify(new Notification("OK!", "Tracking folders updated!", Notification.SUCCESS_ICON));
        }

        @FXML
        private void refresh() {
            controller.refresh(RefreshType.SHALLOW);
        }

        @FXML
        private void reload() {
            controller.refresh(RefreshType.DEEP);
        }
    }

    /**
     * Class of the Movies view.
     */
    public class ControllerMovies {

        @FXML
        private FlowPane flow;

        @FXML
        private JFXTextField searchText;

        @FXML
        private ImageView imgSearch;

        @FXML
        private ImageView imgRefresh;

        @FXML
        private ImageView imgSetting;

        @FXML
        private ImageView imgHome;

        @FXML
        private ImageView imgHardRefresh;
        @FXML
        private JFXButton btnRefresh;

        @FXML
        private JFXButton btnSettings;

        @FXML
        private JFXButton btnHome;

        @FXML
        private JFXButton btnReload;

        @FXML
        private JFXButton btnSearch;

        private final FXEnvironment envirolment;

        ControllerMovies(final FXEnvironment envirolment) {
            this.envirolment = envirolment;
            this.envirolment.loadScreen(FXMLScreens.MOVIES, this);

        }

        /**
         * Initialize the icon.
         */
        public void initMovieIcon() {
            imgSearch.setImage(new Image("view/search.png"));
            imgSetting.setImage(new Image("view/settings.png"));
            imgRefresh.setImage(new Image("view/refresh.png"));
            imgHardRefresh.setImage(new Image("view/hard_refresh.png"));
            imgHome.setImage(new Image("view/home.png"));
            btnReload.setTooltip(new Tooltip("Reload movies"));
            btnRefresh.setTooltip(new Tooltip("Refresh movies"));
            btnSettings.setTooltip(new Tooltip("Setting menù"));
            btnHome.setTooltip(new Tooltip("Go home"));
            btnSearch.setTooltip(new Tooltip("Search movie"));
        }

        /**
         * Search the movie on key enter.
         */
        private void initSearch() {
            searchText.setOnKeyPressed(keyEvent -> {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    controller.searchMovie(searchText.getText());
                }
            });
        }

        @FXML
        private void goTvSeries() throws IOException {
            envirolment.displayScreen(FXMLScreens.TVSERIES);
        }

        @FXML
        private void goHome() throws IOException {
            envirolment.displayScreen(FXMLScreens.HOME);
        }

        /**
         * Clear the view.
         */
        public void clear() {
            flow.getChildren().clear();
        }

        @FXML
        private void refresh() {
            controller.refreshMovies(RefreshType.SHALLOW);
        }

        @FXML
        private void reload() {
            controller.refreshMovies(RefreshType.DEEP);
        }

        /**
         * Show movie poster, title and year.
         *
         * @param imagePath
         *            poster path
         * @param text
         *            title of the movie
         * @param movieID
         *            id of the movie
         */
        public void addCover(final String imagePath, final String text, final String movieID) {
            final BorderPane bp = new BorderPane();
            final Image img = new Image("file:///" + imagePath, 300, 200, true, true);
            bp.setPadding(new Insets(10));
            final ImageView imgView = new ImageView(img);
            imgView.setCursor(Cursor.HAND);
            final String id = movieID;
            final Label lbl = new Label(text);
            imgView.setId(id);
            lbl.setWrapText(true);
            lbl.setTextFill(Color.web("white"));
            bp.setCenter(imgView);
            bp.setBottom(lbl);
            flow.getChildren().add(bp);
            imgView.setOnMouseClicked(e -> {
                new MovieView(id);
            });
        }

        @FXML
        private void goSearch() {
            controller.searchMovie(searchText.getText());
        }
    }

    /**
     * Class of the Tv series view.
     */
    public class ControllerTvSeries {

        @FXML
        private FlowPane flow;

        @FXML
        private JFXTextField searchText;

        private final FXEnvironment envirolment;

        ControllerTvSeries(final FXEnvironment envirolment) {
            this.envirolment = envirolment;
            this.envirolment.loadScreen(FXMLScreens.TVSERIES, this);

        }

        @FXML
        private void goMovies() throws IOException {
            envirolment.displayScreen(FXMLScreens.MOVIES);
        }

        @FXML
        private void goHome() throws IOException {
            envirolment.displayScreen(FXMLScreens.HOME);
        }

        /**
         * Clears the view.
         */
        public void clear() {
            flow.getChildren().clear();
        }

        @FXML
        private void refresh() {
            controller.refresh(RefreshType.SHALLOW);
        }

        @FXML
        private void hardRefresh() {
            controller.refresh(RefreshType.DEEP);
        }

        @FXML
        private void goSearch() {
            controller.searchTVSeries(searchText.getText());
        }

        /**
         * Show tv series poster, title and year.
         *
         * @param imagePath
         *            poster path
         * @param text
         *            title of the movie
         */
        public void addCover(final String imagePath, final String text) {
            final BorderPane bp = new BorderPane();
            final Image img = new Image(imagePath, 300, 200, true, true);
            bp.setPadding(new Insets(10));
            final ImageView imgView = new ImageView(img);
            final Label lbl = new Label(text);
            lbl.setTextFill(Color.web("white"));
            bp.setCenter(imgView);
            flow.getChildren().add(bp);
            imgView.setOnMouseClicked(e -> {

            });

        }
    }

    /**
     * Class of the Single movie view.
     */
    public class MovieView implements SingleMovieView {

        private static final int IMAGE_MAXDIM = 300;
        private static final int IMAGE_MINDIM = 200;
        private static final double MAX_OPACITY = 0.8;
        private static final int RATING = 30;
        private Image movieImg;
        private final VBox movieInfosBox;
        private final BorderPane mainContainer;
        private final BorderPane coverContainer;
        private final ScrollPane pane;
        private final Scene scene;
        private final Stage stage;
        private final LabelFactory label;
        private final Rating rating;
        private final HBox castBox;

        private final SingleMediaController controllerSingle;

        /**
         * Show the info of a movie.
         *
         * @param id
         *            movie id
         */
        public MovieView(final String id) {
            controllerSingle = new SingleMovieController(id, this);
            label = new LabelFactoryConcrete();
            movieInfosBox = new VBox();
            castBox = new HBox();
            mainContainer = new BorderPane();
            coverContainer = new BorderPane();
            rating = new Rating(10);
            rating.setMaxHeight(RATING);
            rating.setMouseTransparent(true);
            rating.setPartialRating(true);
            pane = new ScrollPane(movieInfosBox);
            mainContainer.setCenter(pane);
            mainContainer.setLeft(coverContainer);
            coverContainer.setBottom(rating);
            stage = new Stage();
            scene = new Scene(mainContainer);
            pane.setFitToWidth(true);
            coverContainer.setPadding(new Insets(10));
            movieInfosBox.setStyle("-fx-background-color: rgb(0,0,0,0.9);");
            pane.setStyle("-fx-background-color: rgb(0,0,0,0.9);");
            pane.setOpacity(MAX_OPACITY);
            stage.setMaximized(true);
            stage.setScene(scene);
            controllerSingle.refresh(RefreshType.DEEP);
            stage.getIcons().add(new Image("view/logo.png"));
            stage.show();

        }

        /**
         * Sets and shows the cover of the movie.
         *
         * @param backdropMovie
         *            path of the cover
         */
        @Override
        public void setBackdrop(final String backdropMovie) {
            final String tmp = new String(FILE + backdropMovie.replace("\\", "/"));
            mainContainer.setStyle("-fx-background-image: url('" + tmp + "'); " + "-fx-background-size: cover;");
        }

        /**
         * Sets and shows the poster movie and play icon.
         *
         * @param posterMovie
         *            Movie poster path
         */
        @Override
        public void setPoster(final String posterMovie) {
            final StackPane pn = new StackPane();
            coverContainer.setTop(pn);
            final String tmp = new String(FILE + posterMovie.replace("\\", "/"));
            pn.setStyle("-fx-background-image: url('" + tmp + "'); " + "-fx-background-size: cover;");
            pn.setMaxHeight(Region.USE_COMPUTED_SIZE);
            final ImageView imgView1 = new ImageView(new Image("view/playMovie.png", 600, 600, true, true));
            pn.getChildren().add(imgView1);
            imgView1.setOpacity(0.5);
            imgView1.setOnMouseEntered(e -> {
                imgView1.setCursor(Cursor.HAND);
                imgView1.setOpacity(1);
            });

            imgView1.setOnMouseExited(e -> {
                imgView1.setOpacity(0.5);
            });
            imgView1.setOnMouseClicked(e -> {
                controllerSingle.play();
            });

        }

        @Override
        public void setTitle(final String titleMovie) {
            movieInfosBox.getChildren().add(label.createLabelTitleMovie(titleMovie + "\n\n"));
        }

        @Override
        public void setPlot(final String plotMovie) {
            movieInfosBox.getChildren().addAll(label.createLabelTitle("\n\n- Overview \n\n"),
                    label.createLabelText(plotMovie + "\n\n"));
        }

        @Override
        public void setSceneTitle(final String titleMovie) {
            stage.setTitle(titleMovie);
        }

        @Override
        public void setCountries(final String countriesMovie) {
            movieInfosBox.getChildren().addAll(label.createLabelTitle("\n\n- Countries \n\n"),
                    label.createLabelText(countriesMovie));
        }

        @Override
        public void setTags(final String tagsMovie) {
            movieInfosBox.getChildren().addAll(label.createLabelTitle("\n\n- Tags \n\n"),
                    label.createLabelText(tagsMovie));
        }

        @Override
        public void setSize(final String sizeMovie) {
            movieInfosBox.getChildren().addAll(label.createLabelTitle("\n\n- Size \n\n"),
                    label.createLabelText(sizeMovie));
        }

        @Override
        public void setReleaseDate(final String dateMovie) {
            movieInfosBox.getChildren().addAll(label.createLabelTitle("\n\n- Release date \n\n"),
                    label.createLabelText(dateMovie));
        }

        @Override
        public void setRating(final double ratingMovie) {

            coverContainer.setCenter(label.createLabelTitle("Rating \n"));
            rating.setRating(ratingMovie);
        }

        @Override
        public void setDuration(final String duration) {
            movieInfosBox.getChildren().addAll(label.createLabelTitle("\n\n- Durate \n\n"),
                    label.createLabelText(duration));
        }

        @Override
        public void setPhisicalLocation(final String locationMovie) {
            movieInfosBox.getChildren().addAll(label.createLabelTitle("\n\n- Location \n\n"),
                    label.createLabelText(locationMovie));
        }

        @Override
        public void setProducer(final String imagePath, final String name) {
            final BorderPane bp = new BorderPane();
            final VBox box = new VBox();
            movieImg = new Image(FILE + imagePath, IMAGE_MAXDIM, IMAGE_MINDIM, true, true);
            final ImageView imgView = new ImageView(movieImg);
            box.getChildren().addAll(imgView, label.createLabelText(name + "\n\n"));
            bp.setCenter(box);
            bp.setTop(label.createLabelTitle("\n\n- Producer \n\n"));
            movieInfosBox.getChildren().add(bp);
        }

        @Override
        public void setCast(final List<Pair<String, String>> actorsMovie) {

            for (final Pair<String, String> pair : actorsMovie) {
                final BorderPane bp = new BorderPane();
                final Image img = new Image(FILE + pair.getKey(), IMAGE_MAXDIM, IMAGE_MINDIM, true, true);
                bp.setPadding(new Insets(10));
                final ImageView imgView = new ImageView(img);
                final Label lbl = new Label(pair.getValue());
                lbl.setWrapText(true);
                lbl.setTextFill(Color.web("white"));
                bp.setCenter(imgView);
                bp.setBottom(lbl);
                castBox.getChildren().add(bp);

            }
            movieInfosBox.getChildren().add(label.createLabelTitle("\n- Cast\n\n"));
            movieInfosBox.getChildren().add(castBox);
        }

        @Override
        public void showError(final String errorMessage) {
            Notification.Notifier.setWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth());
            Notification.Notifier.INSTANCE.notify(new Notification("Error!", errorMessage, Notification.WARNING_ICON));

        }

    }

}
