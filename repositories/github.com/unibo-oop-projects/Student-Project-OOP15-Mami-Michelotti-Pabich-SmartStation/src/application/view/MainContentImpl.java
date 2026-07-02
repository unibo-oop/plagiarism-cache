package application.view;


import application.ExitStatus;
import application.Main;
import application.controller.MainController;
import application.view.tabs.fuelsEditor.FuelsEditor;
import application.view.tabs.fuelsEditor.FuelsEditorImpl;
import application.view.tabs.info.InfoImpl;
import application.view.tabs.movementsViewer.MovementsViewer;
import application.view.tabs.movementsViewer.MovementsViewerImpl;
import application.view.tabs.overview.Overview;
import application.view.tabs.overview.OverviewImpl;
import application.view.tabs.pumpsEditor.PumpsEditor;
import application.view.tabs.pumpsEditor.PumpsEditorImpl;
import application.view.tabs.reservesEditor.ReservesEditor;
import application.view.tabs.reservesEditor.ReservesEditorImpl;
import application.view.tabs.stationEditor.StationEditor;
import application.view.tabs.stationEditor.StationEditorImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TabPane;

/**
 * Class for the main content of the window, containing all the tabs.
 * @author Marcin Pabich
 */
public class MainContentImpl extends TabPane implements MainContent {

    //Tab container
    @FXML
    private TabPane tabContainer;
	
    
    //Tabs
    @FXML
    private OverviewImpl overviewTab;
	
    @FXML
    private StationEditorImpl stationEditorTab;
    
    @FXML 
    private FuelsEditorImpl fuelsEditorTab;

    @FXML 
    private ReservesEditorImpl reservesEditorTab;
    
    @FXML
    private PumpsEditorImpl pumpsEditorTab;
    
    @FXML
    private MovementsViewerImpl movementsViewerTab;
    
    @FXML
    private InfoImpl infoTab;
    
	
    //Controller
    private MainController controller;
	
    
    /**
     * Constructor for the main content class.
     */
    public MainContentImpl() {
        //Load the window, setting the root and controller
	FXMLLoader loader = new FXMLLoader(getClass().getResource("MainContent.fxml"));
	loader.setRoot(this);
	loader.setController(this);		
		
	//Try to load
	try {
	    loader.load();
	} catch (Exception exception) {
            ExitStatus.showErrorDialog("FXML Loading Exception", "MainContent.fxml could not be loaded", "Exception message: " + exception.getMessage());
            Main.close(ExitStatus.FXMLLoadingExcp);
	}
    }

    @Override
    public void setController(final MainController ctrl) {
        //Take ref to the main controller
	this.controller = ctrl;
	
	//Assign to all sub-views the sub-controllers
	this.overviewTab.setController(this.controller.getOverviewController());
	this.stationEditorTab.setController(this.controller.getStationEditorController());
	this.fuelsEditorTab.setController(this.controller.getFuelsEditorController());
	this.reservesEditorTab.setController(this.controller.getReservesEditorController());
	this.pumpsEditorTab.setController(this.controller.getPumpsEditorController());
	this.movementsViewerTab.setController(this.controller.getMovementsViewerController());	
    }

    
    //Get the controller
    @Override
    public MainController getController() {
	return this.controller;
    }

    
    
    //Getters for the tabs
    @Override
    public Overview getOverviewTab() {
        return this.overviewTab;
    }

    @Override
    public StationEditor getStationEditorTab() {
        return this.stationEditorTab;
    }

    @Override
    public FuelsEditor getFuelsEditorTab() {
        return this.fuelsEditorTab;
    }

    @Override
    public PumpsEditor getPumpsEditorTab() {
        return this.pumpsEditorTab;
    }

    @Override
    public ReservesEditor getReservesEditorTab() {
        return this.reservesEditorTab;
    }

    @Override
    public MovementsViewer getMovementsViewerTab() {
        return this.movementsViewerTab;
    }
	
   	
}
