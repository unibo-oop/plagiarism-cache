package view;

import java.net.URL;
import java.util.ResourceBundle;

import controller.CreditsHandler;
import controller.HistoryHandler;
import controller.IOHistoryHandler;
import controller.MainViewHandler;
import controller.SaveWindowHandler;
import controller.SettingsHandler;
import controller.TabHandler;
import controller.exceptions.ImageNotInitializedException;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import view.util.language.LanguageManagerUtils;

/**
 * Contoller of the MainWindow.
 */
public class MainWindowController implements Initializable {

    private static final int SCROLPANESIZE = 200;
    private static TabHandler tabHandler = new TabHandler();
    private String wasSelected = "";

    @FXML
    private BorderPane mainPane;
    @FXML
    private Menu menuFile;
    @FXML
    private Menu menuEdit;
    @FXML
    private Menu menuHelp;
    @FXML
    private MenuItem menuOpen;
    @FXML
    private MenuItem menuSave;
    @FXML
    private MenuItem menuSaveAs;
    @FXML
    private MenuItem menuQuit;
    @FXML
    private MenuItem menuUndo;
    @FXML
    private MenuItem menuRedo;
    @FXML
    private MenuItem menuSettings;
    @FXML
    private MenuItem menuAbout;
    @FXML
    private Button buttonFilter;
    @FXML
    private Button buttonRegulation;
    @FXML
    private Button buttonConvolution;
    @FXML
    private Button buttonUndo;
    @FXML
    private Button buttonRedo;
    @FXML
    private TabPane tabPane;
    @FXML
    private VBox welcomeScreen;
    @FXML
    private ImageView imageMustashi;
    @FXML
    private Label labelWelcome;

    /**
     * Accept DragOver event .
     * 
     * @param event the specified DragEvent for DragOver event
     */
    @FXML
    public void handleDragOver(final DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.COPY);
        }
        event.consume();
    }

    /**
     * Transfer files on Drop event .
     * 
     * @param event the specified DragEvent for Drop event
     */
    @FXML
    public void handleDrop(final DragEvent event) {
        event.getDragboard().getFiles().stream().filter((file) -> IOHistoryHandler.addHistory(file))
                .forEach((f) -> setTab());

        event.consume();
    }

    /**
     * Shows the Filter controls view.
     */
    public void showFilterView() {
        setImage(HistoryHandler.getHistoryHandler().getCurrentImage());
        wasSelected = "/scene/Filter.fxml";
        MainViewHandler.showView("/scene/Filter.fxml");
    }

    /**
     * Shows the Regolation controls view.
     */
    public void showRegolationView() {
        setImage(HistoryHandler.getHistoryHandler().getCurrentImage());
        wasSelected = "/scene/Regolations.fxml";
        MainViewHandler.showView("/scene/Regolations.fxml");
    }

    /**
     * Shows the Regolation controls view.
     */
    public void showConvolutionView() {
        setImage(HistoryHandler.getHistoryHandler().getCurrentImage());
        wasSelected = "/scene/Convolution.fxml";
        MainViewHandler.showView("/scene/Convolution.fxml");
    }

    /**
     * Show the settings view.
     */
    public void openSettings() {
        SettingsHandler.showSettings();
    }

    /**
     * Show the settings view.
     */
    public void openCredits() {
        CreditsHandler.showCredits();
    }

    /**
     * Open an image or a mshi history and set the imageView.
     * 
     */
    public void openImage() {
        if (IOHistoryHandler.loadImage()) {
            setTab();
        }
    }

    /**
     * If a mshi file is opend update it, act like exportImage otherwise.
     */
    public void saveHistory() {
        IOHistoryHandler.saveActualHistoryStatus(false);
    }

    /**
     * Save an image in diffrent formats, or the history in mshi format.
     */
    public void saveImageAs() {
        IOHistoryHandler.saveAs();
    }

    /**
     * Close the MainWindow.
     */
    public void closeWindow() {
        closeAllTabs();
        if (!HistoryHandler.getHistoryHandler().exist()) {
            MainViewHandler.closeApplication();
        }
    }

    /**
     * show the previous image of history.
     */
    public void showPrevious() {
        setImage(HistoryHandler.getHistoryHandler().getPreviousImage());
        if (!wasSelected.equals("")) {
            MainViewHandler.showView(wasSelected);
        }
    }

    /**
     * show the next image of history.
     */
    public void showNext() {
        setImage(HistoryHandler.getHistoryHandler().getNextImage());
        if (!wasSelected.equals("")) {
            MainViewHandler.showView(wasSelected);
        }
    }

    /**
     * Manage mustshi logo pressed on welcome screen.
     */
    public void mustashiPessed() {
        imageMustashi.setImage(new Image("/icons/mustashiButtonPressed.png"));
    }

    /**
     * Manage mustshi logo entred on welcome screen.
     */
    public void mustashiEntered() {
        imageMustashi.setImage(new Image("/icons/mustashiButtonEntered.png"));
    }

    /**
     * Manage mustshi logo exited on welcome screen.
     */
    public void mustashiExited() {
        imageMustashi.setImage(new Image("/icons/mustashiButton.png"));
    }

    /**
     * Manage mustshi logo released on welcome screen.
     */
    public void mustashiReleased() {
        imageMustashi.setImage(new Image("/icons/mustashiButtonEntered.png"));
        openImage();
    }

    /**
     * Get the content of the ImageView.
     * 
     * @return imageView image
     * @throws ImageNotInitializedException if no image is opend
     */
    public static Image getImage() throws ImageNotInitializedException {
        if (tabHandler.getImage() != null) {
            return tabHandler.getImage();
        } else {
            throw new ImageNotInitializedException("No image opend");
        }
    }

    /**
     * Set the ImageView content.
     * 
     * @param image to set
     */
    public static void setImage(final Image image) {
        if (HistoryHandler.getHistoryHandler().exist()) {
            tabHandler.setImage(image);
        }
    }

    /**
     * Close all tabs checking if images are saved.
     */
    public void closeAllTabs() {
        Tab tab;
        final int tabNumber = tabPane.getTabs().size();
        int cancelNumber = 0;
        tabPane.getSelectionModel().selectFirst();

        for (int i = 0; i < tabNumber; i++) {
            tabPane.getSelectionModel().select(cancelNumber);
            tab = tabPane.getSelectionModel().getSelectedItem();
            Event.fireEvent(tab, new Event(Tab.TAB_CLOSE_REQUEST_EVENT));
            if (tab.equals(tabPane.getSelectionModel().getSelectedItem())) {
                tabPane.getSelectionModel().selectNext();
                cancelNumber++;
            }
        }
    }

    /**
     * @see javafx.fxml.Initializable#initialize(java.net.URL,
     *      java.util.ResourceBundle)
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        buttonUndo.setTooltip(getTooltip("button.undo"));
        buttonRedo.setTooltip(getTooltip("button.redo"));
        tabPane.setTabMaxWidth(SCROLPANESIZE);
        setTabPaneVisibility(false);
        setButtonVisibility(false);
        menuFile.textProperty().bind(LanguageManagerUtils.createStringBinding("menu.file"));
        menuEdit.textProperty().bind(LanguageManagerUtils.createStringBinding("menu.edit"));
        menuHelp.textProperty().bind(LanguageManagerUtils.createStringBinding("menu.help"));
        menuOpen.textProperty().bind(LanguageManagerUtils.createStringBinding("menu.open"));
        menuSave.textProperty().bind(LanguageManagerUtils.createStringBinding("menu.save"));
        menuSaveAs.textProperty().bind(LanguageManagerUtils.createStringBinding("menu.saveas"));
        menuQuit.textProperty().bind(LanguageManagerUtils.createStringBinding("menu.quit"));
        menuUndo.textProperty().bind(LanguageManagerUtils.createStringBinding("menu.undo"));
        menuRedo.textProperty().bind(LanguageManagerUtils.createStringBinding("menu.redo"));
        menuSettings.textProperty().bind(LanguageManagerUtils.createStringBinding("menu.settings"));
        menuAbout.textProperty().bind(LanguageManagerUtils.createStringBinding("menu.about"));
        buttonFilter.textProperty().bind(LanguageManagerUtils.createStringBinding("button.filter"));
        buttonRegulation.textProperty().bind(LanguageManagerUtils.createStringBinding("button.regolations"));
        buttonConvolution.textProperty().bind(LanguageManagerUtils.createStringBinding("button.convolution"));
        labelWelcome.textProperty().bind(LanguageManagerUtils.createStringBinding("label.welcome"));
    }

    /**
     * 
     */
    public void initializeTab() {

        // Set value of imageView
        final ImageView imageView = new ImageView();
        final StackPane stackPane;
        final ScrollPane scrollPane;
        final Property<Image> displayedImage;
        final Tab tab;

        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);

        stackPane = new StackPane(imageView);
        // Set scrollPane
        scrollPane = new ScrollPane();
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(SCROLPANESIZE);
        scrollPane.setPrefHeight(SCROLPANESIZE);
        scrollPane.hbarPolicyProperty().set(ScrollBarPolicy.NEVER);
        scrollPane.vbarPolicyProperty().set(ScrollBarPolicy.NEVER);
        imageView.fitHeightProperty().bind(scrollPane.heightProperty());
        imageView.fitWidthProperty().bind(scrollPane.widthProperty());
        displayedImage = new SimpleObjectProperty<Image>();
        imageView.imageProperty().bindBidirectional(displayedImage);
        displayedImage.setValue(HistoryHandler.getHistoryHandler().getCurrentImage());
        scrollPane.setContent(stackPane);

        // Set tab
        tab = new Tab();
        tab.setId(HistoryHandler.getHistoryHandler().getCurrentNameOfHistory());
        tab.setText(HistoryHandler.getHistoryHandler().getNickNameOfHistory());
        tab.setContent(scrollPane);

        // Relates object
        tabHandler.relates(HistoryHandler.getHistoryHandler().getCurrentNameOfHistory(), displayedImage, tab);
        // Add tab
        tabPane.getTabs().add(tab);
        tabPane.getSelectionModel().select(tab);
        tab.onCloseRequestProperty().set(e -> {
            closeTab(e);
        });
        tabPane.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            if (!wasSelected.equals("")) {
                MainViewHandler.showView(wasSelected);
            }
            tabHandler.setHistory(newValue);
            setImage(HistoryHandler.getHistoryHandler().getCurrentImage());
        });

    }

    /**
     * Close the tab.
     * 
     * @param e is event close.
     */
    public void closeTab(final Event e) {
        if (!HistoryHandler.getHistoryHandler().currentHistoryIsSaved()) {
            SaveWindowHandler.showSaveWindow();
            if (!SaveWindowHandler.canContinue()) {
                e.consume();
            } else {
                tabHandler.removeTab(HistoryHandler.getHistoryHandler().getCurrentNameOfHistory());
                tabPane.getTabs().remove(e.getSource());
                if (!HistoryHandler.getHistoryHandler().exist()) {
                    setTabPaneVisibility(false);
                    setButtonVisibility(false);
                    mainPane.setRight(null);
                    welcomeScreen.setVisible(true);
                }
            }
        } else {
            IOHistoryHandler.saveActualHistoryStatus(true);
            tabHandler.removeTab(HistoryHandler.getHistoryHandler().getCurrentNameOfHistory());
            tabPane.getTabs().remove(e.getSource());
            if (!HistoryHandler.getHistoryHandler().exist()) {
                setTabPaneVisibility(false);
                setButtonVisibility(false);
                mainPane.setRight(null);
                welcomeScreen.setVisible(true);
            }
        }
    }

    private void setTabPaneVisibility(final boolean value) {
        tabPane.setVisible(value);
    }

    private void setButtonVisibility(final boolean value) {
        buttonFilter.setVisible(value);
        buttonRegulation.setVisible(value);
        buttonConvolution.setVisible(value);
        buttonUndo.setVisible(value);
        buttonRedo.setVisible(value);
    }

    private void setTab() {
        initializeTab();
        setButtonVisibility(true);
        setTabPaneVisibility(true);
        welcomeScreen.setVisible(false);
    }

    private Tooltip getTooltip(final String key) {
        final Tooltip toolTip = new Tooltip();
        toolTip.textProperty().bind(LanguageManagerUtils.createStringBinding(key));
        return toolTip;
    }

}
