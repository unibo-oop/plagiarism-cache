package controller;

import java.util.List;
import java.util.Map;
import application.Tamagotchi;
import javafx.application.Platform;
import model.character.Stats;
import model.container.Box;
import model.ranking.AbstractCharacter;
import view.Difficulty;
import view.View;
import view.View.Frames;

/**
 * 
 */
public class ViewManager {
    private Controller controller;
    private View view;
    private static final int EASYAMOUNT = 1;
    private static final int MEDIUMAMOUNT = 10;
    private static final int EXTREMEAMOUNT = 20;
    private static final double EASYDEC = 0.2;
    private static final int MEDIUMDEC = 2;
    private static final int EXTREMEDEC = 5;

    /**
     * the constructor setu up the view and the controller.
     * 
     * @param newView
     * @param newController
     */
    ViewManager(final View newView, final Controller newController) {
        this.view = newView;
        this.controller = newController;
    }

    /**
     * 
     * @return timeOffline of character.
     */
    public double getDate() {
        return controller.getDate();
    }

    /**
     * 
     * @return the character name.
     */
    public String getCharacterName() {
        return controller.getCharacterName();
    }

    /**
     * 
     * @return CharacterUrl
     */
    public String getTamagotchiUrl() {
        return controller.getTamagotchiUrl();
    }

    /**
     * 
     * @return the shop
     */
    public Map<String, List<Box>> getShop() {
        return controller.getShop();
    }

    /**
     * 
     * @return the character balance
     */
    public int getBalance() {
        return controller.getBalance();
    }

    /**
     * 
     * @return the character's inventory
     */
    public Map<String, List<Box>> getInventory() {
        return controller.getInventory();
    }

    /**
     * 
     * @return the ranking list
     */
    public List<AbstractCharacter> getRanking() {
        return (controller.getRanking());
    }

    /**
     * 
     * @param category 
     * @return the main item of category
     */
    public String getMainItem(final String category) {
        return controller.getMainItem(category);
    }

    /**
     * 
     * @return the character's stats
     */
    public List<Stats> getStats() {
        return controller.getStats();
    }

    /**
     * This method refresh the view.
     * 
     * @param var 
     */
    public void update(final boolean var) {
        view.refreshGameFrame(var);
    }

    /**
     * RefreshGame.
     * 
     * @param var 
     */
    public void refreshGame(final boolean var) {
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                view.refreshGameFrame(var);
            }
        });
    }

    /**
     * 
     */
    public void restartView() {
        view.restartGame();
    }

    /**
     * 
     */
    public void restartGame() {
        this.restartView();
        Tamagotchi.startApplication();
    }

    /**
     * 
     */
    public void updateMoney() {
        view.setMoneyValue();
    }

    /**
     * 
     * @param frame 
     */
    public void startFrame(final Frames frame) {
        view.startFrame(frame);
    }

    /**
     * 
     * @param category 
     * @return True if the method ready
     */
    public boolean modStat(final String category) {
        return controller.modStat(category);
    }

    /**
     * 
     * @param value 
     * @return True if the method ready
     */
    public boolean modAllStats(final double value) {
        return controller.modAllStats(value);
    }

    /**
     * 
     */
    public void checkInventory() {
        controller.checkInventory();
    }

    /**
     * 
     */
    public void loadRanking() {
        controller.loadRanking();
    }

    /**
     * 
     * @param newName 
     * @return the character name
     */
    public boolean setCharacterName(final String newName) {
        return controller.setCharacterName(newName);
    }

    /**
     * 
     * @param newUrl 
     */
    public void setTamagotchiUrl(final String newUrl) {
        controller.setTamagotchiUrl(newUrl);
    }

    /**
     * 
     */
    public void startTimer() {
        controller.startTimer();
    }

    /**
     * 
     */
    public void start() {
        controller.start();
    }

    /**
     * 
     */
    public void saveFile() {
            controller.saveFile();
    }

    /**
     * 
     */
    public void checkAndSetMainItem() {
        controller.checkAndSetMainItem();
        this.refreshGame(true);
    }

    /**
     * 
     * @param category 
     * @param item 
     * @param var 
     */
    public void setMainItem(final String category, final String item, final Boolean var) {
        controller.setMainItem(category, item);
        this.refreshGame(var);
    }

    /**
     * 
     * @param item 
     * @return the true if buy is ok
     */
    public boolean buy(final String item) {
        return controller.buy(item);
    }

    /**
     * 
     * @param diff 
     */
    public void setDifficulty(final Difficulty diff) {
        switch (diff) {
        case EASY:
            controller.setAmount(EASYAMOUNT);
            controller.setDecrementFactor(EASYDEC);
            break;
        case MEDIUM:
            controller.setAmount(MEDIUMAMOUNT);
            controller.setDecrementFactor(MEDIUMDEC);
            break;
        case EXTREME:
            controller.setAmount(EXTREMEAMOUNT);
            controller.setDecrementFactor(EXTREMEDEC);
            break;
        default:
            break;
        }
    }

    /**
     * 
     * @return age 
     */
    public double getAge() {
        return controller.getAge();
    }

    /**
     * 
     */
    public void updateAge() {
        view.refreshAge();
    }

}
