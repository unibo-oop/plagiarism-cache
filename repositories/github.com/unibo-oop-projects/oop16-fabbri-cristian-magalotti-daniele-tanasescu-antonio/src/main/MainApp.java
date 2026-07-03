package main;

import java.io.IOException;

import battle_arena.BattleArena;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.view.BattleMenuController;
import main.view.TradePanelController;
import managers.MenuMenager;
import team.Player;
import team.enemies.Enemy;

public class MainApp extends Application {

    static Scene actualScene;
    static Stage tutorial;
    static Stage primaryStage;
    static Stage overwritePreviousData;
    static Stage managementMenu;
    static Stage teamPreview;
    static Stage goingToSaveNew;
    static Stage alreadySaved;
    static Stage winScreen;
    static Stage loseScreen;
    static Stage noSaveFileErrorScreen;
    static Stage tradePanel;
    static Stage teamOverwrite;
    static Stage teamMemberAdieu;
    static Stage goodbyeWelcome;
    static BattleArena battleArena;

    public static BattleMenuController battleMenuController = new BattleMenuController();
    public static TradePanelController tradePanelController;
    @Override
    public void start(Stage primaryStage) throws IOException {
        MainApp.primaryStage = primaryStage;

        this.showMainMenu();

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


    @FXML
    public void showMainMenu() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(MainApp.class.getResource("view/MainMenu.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();

        actualScene = new Scene(anchorPane);
        primaryStage.setScene(actualScene);
    }

    public static void goToGameMenu() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(MainApp.class.getResource("view/GameMenu.fxml"));
        BorderPane borderPane = fxmlLoader.load();

        actualScene = new Scene(borderPane);
        primaryStage.setScene(actualScene);
        primaryStage.setFullScreen(true);
    }
    
    public static void showTutorial() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(MainApp.class.getResource("view/Tutorial.fxml"));
        AnchorPane howToPlay = fxmlLoader.load();

        tutorial = new Stage();
        tutorial.setTitle("Tutorial");
        tutorial.initModality(Modality.WINDOW_MODAL);
        tutorial.initOwner(primaryStage);
        actualScene = new Scene(howToPlay);
        tutorial.setScene(actualScene);
        tutorial.setResizable(false);
        tutorial.showAndWait();
    }

    public static void showOverwritePreviousData() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(MainApp.class.getResource("view/NewGameAlert.fxml"));
        BorderPane newGameMessage = fxmlLoader.load();

        overwritePreviousData = new Stage();
        overwritePreviousData.setTitle("Previous save data has been detected");
        overwritePreviousData.initModality(Modality.WINDOW_MODAL);
        overwritePreviousData.initOwner(primaryStage);
        actualScene = new Scene(newGameMessage);
        overwritePreviousData.setScene(actualScene);
        overwritePreviousData.setResizable(false);
        overwritePreviousData.showAndWait();
    }

    public static void showNoSaveDataErrorScreen() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(MainApp.class.getResource("view/NoSaveFileErrorScreen.fxml"));
        BorderPane badErrorSaveFile = fxmlLoader.load();

        noSaveFileErrorScreen = new Stage();
        noSaveFileErrorScreen.setTitle("BAD ERROR");
        noSaveFileErrorScreen.initModality(Modality.WINDOW_MODAL);
        noSaveFileErrorScreen.initOwner(primaryStage);
        actualScene = new Scene(badErrorSaveFile);
        noSaveFileErrorScreen.setScene(actualScene);
        noSaveFileErrorScreen.setResizable(false);
        noSaveFileErrorScreen.showAndWait();

    }

    public static void goToBattleMenu() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(MainApp.class.getResource("view/BattleMenu.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();

        actualScene = new Scene(anchorPane);
        primaryStage.setScene(actualScene);
        primaryStage.setMaximized(true);
        primaryStage.setFullScreen(true);
        primaryStage.setAlwaysOnTop(true);
    }

    public static void goToWinScreen() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(MainApp.class.getResource("view/WinScreen.fxml"));
        AnchorPane win = fxmlLoader.load();

        winScreen = new Stage();
        winScreen.setTitle("WIN");
        winScreen.initModality(Modality.WINDOW_MODAL);
        winScreen.initOwner(primaryStage);
        actualScene = new Scene(win);
        winScreen.setScene(actualScene);
        winScreen.initStyle(StageStyle.UNDECORATED);
        winScreen.showAndWait();
    }
    
    public static void goToLoseScreen() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(MainApp.class.getResource("view/LoseScreen.fxml"));
        AnchorPane lose = fxmlLoader.load();

        winScreen = new Stage();
        winScreen.setTitle("Lose :c");
        winScreen.initModality(Modality.WINDOW_MODAL);
        winScreen.initOwner(primaryStage);
        actualScene = new Scene(lose);
        winScreen.setScene(actualScene);
        winScreen.initStyle(StageStyle.UNDECORATED);
        winScreen.showAndWait();
    }

    public static void goToManagementMenu() throws IOException{
        if(managementMenu != null &&managementMenu.isShowing()){
            managementMenu.close();
        }

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(MainApp.class.getResource("view/ManagementMenu.fxml"));
        AnchorPane menu = fxmlLoader.load();

        managementMenu = new Stage();
        managementMenu.setTitle("Management Menu");
        managementMenu.initModality(Modality.WINDOW_MODAL);
        managementMenu.initOwner(primaryStage);
        actualScene = new Scene(menu);
        managementMenu.setScene(actualScene);
        managementMenu.setResizable(false);
        managementMenu.showAndWait();
    }

    public static void goToSaveOverwrite() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(MainApp.class.getResource("view/SaveOverwrite.fxml"));
        BorderPane oldGameMessage = fxmlLoader.load();

        goingToSaveNew = new Stage();
        goingToSaveNew.setTitle("Previous save data has been detected");
        goingToSaveNew.initModality(Modality.WINDOW_MODAL);
        goingToSaveNew.initOwner(primaryStage);
        actualScene = new Scene(oldGameMessage);
        goingToSaveNew.setScene(actualScene);
        goingToSaveNew.setResizable(false);
        goingToSaveNew.setAlwaysOnTop(true);
        goingToSaveNew.showAndWait();
    }
    
    public static void goToSaveDone() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(MainApp.class.getResource("view/SaveDone.fxml"));
        AnchorPane saveDone = fxmlLoader.load();

        alreadySaved = new Stage();
        alreadySaved.setTitle("Previous save data has been detected");
        alreadySaved.initModality(Modality.WINDOW_MODAL);
        alreadySaved.initOwner(primaryStage);
        actualScene = new Scene(saveDone);
        alreadySaved.setScene(actualScene);
        alreadySaved.setResizable(false);
        alreadySaved.setAlwaysOnTop(true);
        alreadySaved.showAndWait();
    }

    public static void goToTeamPreviewMenu() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(MainApp.class.getResource("view/TeamPreviewMenu.fxml"));
        AnchorPane team = fxmlLoader.load();

        teamPreview = new Stage();
        teamPreview.setTitle("Team Preview");
        teamPreview.initModality(Modality.WINDOW_MODAL);
        teamPreview.initOwner(primaryStage);
        actualScene = new Scene(team);
        teamPreview.setScene(actualScene);
        teamPreview.setResizable(false);
        teamPreview.setAlwaysOnTop(true);
        teamPreview.showAndWait();
    }

    public static void goToTradePanel()throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(MainApp.class.getResource("view/TradePanel.fxml"));
        BorderPane trade = fxmlLoader.load();

        tradePanel = new Stage();
        tradePanel.setTitle("Trade");
        tradePanel.initModality(Modality.WINDOW_MODAL);
        tradePanel.initOwner(primaryStage);
        actualScene = new Scene(trade);
        tradePanel.setScene(actualScene);
        tradePanel.setResizable(false);
        tradePanel.showAndWait();        
    }
    public static void goToTeamOverwriteAlter() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(MainApp.class.getResource("view/TeamOverwriteAlert.fxml"));
        AnchorPane alert = fxmlLoader.load();

        teamOverwrite = new Stage();
        teamOverwrite.setTitle("Team Preview");
        teamOverwrite.initModality(Modality.WINDOW_MODAL);
        teamOverwrite.initOwner(primaryStage);
        actualScene = new Scene(alert);
        teamOverwrite.setScene(actualScene);
        teamOverwrite.setResizable(false);
        teamOverwrite.setAlwaysOnTop(true);
        teamOverwrite.showAndWait();
    }
    
    public static void goToShopMenu() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(MainApp.class.getResource("view/ShopMenu.fxml"));
        BorderPane borderPane = fxmlLoader.load();

        actualScene = new Scene(borderPane);
        primaryStage.setScene(actualScene);
        primaryStage.setFullScreen(true);
        
    }
    

    public static void goToAdieu() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(MainApp.class.getResource("view/AdieuTeamMemberSelect.fxml"));
        AnchorPane selection = fxmlLoader.load();

        teamMemberAdieu = new Stage();
        teamMemberAdieu.setTitle("Don't cry :/");
        teamMemberAdieu.initModality(Modality.WINDOW_MODAL);
        teamMemberAdieu.initOwner(primaryStage);
        actualScene = new Scene(selection);
        teamMemberAdieu.setScene(actualScene);
        teamMemberAdieu.setResizable(false);
        teamMemberAdieu.setAlwaysOnTop(true);
        teamMemberAdieu.showAndWait();
    }
    
    public static void goToGoodbyeAndWelcome() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(MainApp.class.getResource("view/GoodbyeWelcomeMessage.fxml"));
        AnchorPane message = fxmlLoader.load();

        goodbyeWelcome = new Stage();
        goodbyeWelcome.setTitle("Adieu :c Welcome :D");
        goodbyeWelcome.initModality(Modality.WINDOW_MODAL);
        goodbyeWelcome.initOwner(primaryStage);
        actualScene = new Scene(message);
        goodbyeWelcome.setScene(actualScene);
        goodbyeWelcome.setResizable(false);
        goodbyeWelcome.setAlwaysOnTop(true);
        goodbyeWelcome.showAndWait();
        
    }

    public static void disableWinScreen(){
        winScreen.close();
    }
    
    public static void disableLoseScreen(){
        loseScreen.close();
    }

    public static void disableOverwritePreviousData(){
        overwritePreviousData.close();
    }

    public static void disableSaveOverwrite(){
        if(goingToSaveNew != null){
            goingToSaveNew.close();
        }
    }
    
    public static void disableAlreadySaved(){
        alreadySaved.close();
    }

    public static void disableNoSaveFileErrorScreen(){
        noSaveFileErrorScreen.close();
    }

    public static void disableTeamPreview() {
        teamPreview.close();
    }

    public static void disableManagementMenu() {
        managementMenu.close();
    }

    public static void disableTradePanel(){
        tradePanel.close();
    }

    public static void disableTeamOverwrite(){
        teamOverwrite.close();
    }
    
    public static void disableAdieu(){
        teamMemberAdieu.close();
    }
    
    public static void disableGoodbyeAndWelcome(){
        goodbyeWelcome.close();
    }

    public static void quitGame(){
        System.exit(0);
    }

    public static void initializeNewEnemy(){
        MenuMenager menuMenager = new MenuMenager();
        menuMenager.gameManagerInstance().createNewEnemy();
    }

    public static void setBattleArena(Player player, Enemy enemy){
        MainApp.battleArena = new BattleArena(player, enemy);
    }

    public static BattleArena getBattleArena(){
        return MainApp.battleArena;
    }

    public static void disableTutorial() {
        tutorial.close();
    }


}
