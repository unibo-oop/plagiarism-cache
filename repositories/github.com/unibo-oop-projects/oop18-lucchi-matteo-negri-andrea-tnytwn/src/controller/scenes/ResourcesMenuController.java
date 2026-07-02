package controller.scenes;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.game.GameImpl;
import model.resources.Resource;

/**
 * Controller of the view ResourcesMenuLauncher.
 */
public class ResourcesMenuController implements Initializable {

    @FXML private TableView<DisposableResource> resourcesMenuBigTable;
    @FXML private TableView<DisposableResource> resourcesMenuSmallTable;
    @FXML private TableColumn<DisposableResource, String> resourcesMenuBigTableIconColumn;
    @FXML private TableColumn<DisposableResource, String> resourcesMenuBigTableTypeColumn;
    @FXML private TableColumn<DisposableResource, Integer> resourcesMenuBigTableCurrentColumn;
    @FXML private TableColumn<DisposableResource, Integer> resourcesMenuBigTableDailyIncomeColumn;
    @FXML private TableColumn<DisposableResource, Integer> resourcesMenuBigTableDailyCostColumn;
    @FXML private TableColumn<DisposableResource, Integer> resourcesMenuBigTableDailyTotalColumn;
    @FXML private Label resourcesMenuPopulation;
    @FXML private Label resourcesMenuWelfare;
    @FXML private Label resourcesMenuFreeWorkers;
    @FXML private Label resourcesMenuDay;

    /**
     * An observable list to fill the table.
     */
    private final ObservableList<DisposableResource> currentResources = FXCollections.observableArrayList();
    /**
     * A timer used to refresh the menu.
     */
    private final Timer resourceMenuRefreshTimer = new Timer();
    /**
     * 
     */
    private static final int REFRESHLAG = 2000;

    /**
     *
     */
    private class ResourceRefreshTask extends TimerTask {
        /**
         * 
         */
        private final ResourcesMenuController rmc;

        /**
         * 
         * @param rmc
         */
        ResourceRefreshTask(final ResourcesMenuController rmc) {
            this.rmc = rmc;
        }

        @Override
        public void run() {
            this.rmc.resourcesMenuPopulation.setText(GameImpl.getGameImpl().getCityPopulation().getValue().toString());
            this.rmc.resourcesMenuFreeWorkers.setText(GameImpl.getGameImpl().getFreeWorkers().getValue().toString());
//            this.rmc.resourcesMenuDay.setText(GameImpl.getGameImpl().getGameDays().toString());
            this.rmc.resourcesMenuBigTable.getItems().clear();
            GameImpl.getGameImpl().getResources().forEach(x -> this.rmc.currentResources.add(new DisposableResource(x, GameImpl.getGameImpl().getDailyIncomes().get(GameImpl.getGameImpl().getResources().indexOf(x)), GameImpl.getGameImpl().getDailyCosts().get(GameImpl.getGameImpl().getResources().indexOf(x)))));
//            this.resourcesMenuBigTableIconColumn.setCellValueFactory(new PropertyValueFactory<DisposableResource, String>("icon"));
            this.rmc.resourcesMenuBigTableTypeColumn.setCellValueFactory(new PropertyValueFactory<DisposableResource, String>("name"));
            this.rmc.resourcesMenuBigTableCurrentColumn.setCellValueFactory(new PropertyValueFactory<DisposableResource, Integer>("value"));
            this.rmc.resourcesMenuBigTableDailyIncomeColumn.setCellValueFactory(new PropertyValueFactory<DisposableResource, Integer>("inc"));
            this.rmc.resourcesMenuBigTableDailyCostColumn.setCellValueFactory(new PropertyValueFactory<DisposableResource, Integer>("cos"));
            this.rmc.resourcesMenuBigTableDailyTotalColumn.setCellValueFactory(new PropertyValueFactory<DisposableResource, Integer>("tot"));
            this.rmc.resourcesMenuBigTable.setItems(this.rmc.currentResources);
        }

    }

/**
 * A class used for displaying all the informations about a resource.
 */
    public class DisposableResource {
        //private final SimpleStringProperty icon;
        private final SimpleStringProperty name;
        private final SimpleIntegerProperty value;
        private final SimpleIntegerProperty inc;
        private final SimpleIntegerProperty cos;
        private final SimpleIntegerProperty tot;

        /**
         * The default constructor.
         * @param resource
         *      The Resource to display.
         * @param income
         *      The daily income of the Resource.
         * @param cost
         *      The daily income of the Resource.
         */
        public DisposableResource(final Resource resource, final int income, final int cost) {
            //this.icon = new SimpleStringProperty(resource.getClass().getName());
            this.name = new SimpleStringProperty(resource.getName());
            this.value = new SimpleIntegerProperty(resource.getValue());
            this.inc = new SimpleIntegerProperty(income);
            this.cos = new SimpleIntegerProperty(cost);
            this.tot = new SimpleIntegerProperty(income - cost);
        }
        /*
         * @return the icon

        public String getIcon() {
            return this.icon.get();
        }*/
        /**
         * @return the name of the Resource
         */
        public String getName() {
            return this.name.get();
        }
        /**
         * @return the value of the Resource
         */
        public int getValue() {
            return this.value.get();
        }
        /**
         * @return the daily income
         */
        public Integer getInc() {
            return this.inc.get();
        }
        /**
         * @return the daily cost
         */
        public Integer getCos() {
            return this.cos.get();
        }
        /**
         * @return the daily total
         */
        public Integer getTot() {
            return this.tot.get();
        }
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        resourceMenuRefreshTimer.scheduleAtFixedRate(new ResourceRefreshTask(this), 0, REFRESHLAG);
    }

    /**
     * 
     */
    public void stopRefreshTimer() {
        resourceMenuRefreshTimer.cancel();
    }
}
