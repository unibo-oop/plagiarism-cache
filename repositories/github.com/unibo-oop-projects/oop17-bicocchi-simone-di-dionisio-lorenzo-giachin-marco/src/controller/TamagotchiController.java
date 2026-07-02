package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import controller.timer.CoinTimer;
import controller.timer.TamagotchiTimer;
import javafx.application.Platform;
import model.Model;
import model.ModelImpl;
import model.Serializator;
import model.character.Stats;
import model.container.Box;
import model.ranking.AbstractCharacter;
import view.View;
import view.View.Frames;

/**
 * * This class is the Controller of the application.
 *
 */
public class TamagotchiController implements Controller {
    private static final int MAXCHAR = 10;
    private TamagotchiTimer lifeTimer;
    private CoinTimer coinTimer;
    private static final int CLOCKLIFE = 1000;
    private static final int CLOCKCOIN = 5000;
    private int timerAmount;
    private ViewManager viewManager;
    private Model model;
    private File saveData = new File("./saveData.txt"); 

    /**
     * This is the constructor of Tamagotchi COntroler.
     * 
     * @param newModel 
     */
    public TamagotchiController(final Model newModel) {
        this.model = newModel;
}

    @Override
    public void loadStartInformation() {
        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(new BufferedInputStream(this.getClass().getClassLoader().getResourceAsStream("initialStats.txt")));
            Serializator.loadFirstInformation(ois, (ModelImpl) model);
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadInfoCharacter() {
        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(new BufferedInputStream(this.getClass().getClassLoader().getResourceAsStream("./saveData.txt")));
            Serializator.loadInfoCharacter(ois, (ModelImpl) model);
            this.loadRanking();
            this.setAmount(model.getDifficulty());
            double timeOffline = this.getDate();
            this.frame(Frames.GAME);
            this.startTimer();
            if (!this.modAllStats(-timeOffline)) {
                this.characterIsDead();
            }
            this.setAge(timeOffline);
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            this.frame(Frames.LOGIN);
            this.loadRanking();
            this.loadStartInformation();
        }

    }

    @Override
    public void saveFile() {
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("saveData.txt")));
            Serializator.serializeFile(oos, (ModelImpl) model);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveRanking() {
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("ranking.txt")));
            Serializator.saveRanking(oos, (ModelImpl) model);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stopTimer() {
        lifeTimer.stop();
        coinTimer.stop();
    }

    @Override
    public void update() {
        viewManager.refreshGame(false);
    }

    @Override
    public void updateMoney() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                viewManager.updateMoney();
            }
        });

    }

    @Override
    public double getDate() {
        return model.getDate();
    }

    @Override
    public void characterIsDead() {
        this.addRanking();
        this.saveRanking();
        this.saveData.delete();
        try {
            this.saveData.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.stopTimer();
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                viewManager.startFrame(Frames.DEATH);
                viewManager.restartView();
            }
        });
    }

    @Override
    public void ageUp() {
        model.ageUp();
    }

    @Override
    public void setAge(final double time) {
        model.setAge(time);
    }

    @Override
    public boolean setCharacterName(final String newName) {
        if (newName.length() > MAXCHAR || (newName.isEmpty()
                || model.getRanking().stream().map(e -> e.getFirst()).collect(Collectors.toList()).contains(newName))) {
            return false;
        }
        model.setCharacterName(newName);
        this.saveFile();
        return true;
    }

    @Override
    public void setTamagotchiUrl(final String newUrl) {
        model.setCharacterUrl(newUrl);
    }

    @Override
    public String getTamagotchiUrl() {
        return model.getCharacterUrl();
    }

    @Override
    public void checkAndSetMainItem() {
        model.checkAndSetMainItem();
    }

    @Override
    public void checkInventory() {
        for (Map.Entry<String, List<Box>> entry : this.getInventory().entrySet()) {
            entry.getValue().forEach(e -> {
                if ((e != null) && (e.getSecond() < 1)) {
                    this.setMainItem(entry.getKey(), null);
                    this.getInventory().get(entry.getKey()).remove(e);
                    this.checkAndSetMainItem();
                }
            });
        }
    }

    @Override
    public Map<String, List<Box>> getShop() {
        return model.getShopMap();
    }

    @Override
    public int getBalance() {
        return model.getBalance();
    }

    @Override
    public void start() {
        this.loadInfoCharacter();
    }

    @Override
    public void startTimer() {
        this.lifeTimer = new TamagotchiTimer(this, CLOCKLIFE, timerAmount);
        this.coinTimer = new CoinTimer(this, CLOCKCOIN, timerAmount);
        model.setDifficulty(timerAmount);
        lifeTimer.start();
        coinTimer.start();
    }

    @Override
    public Map<String, List<Box>> getInventory() {
        return model.getItemMap();
    }

    @Override
    public List<AbstractCharacter> getRanking() {
        return model.getRanking();
    }

    @Override
    public void loadRanking() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(this.getClass().getClassLoader().getResourceAsStream("./ranking.txt")));
            Serializator.loadRanking(ois, (ModelImpl) model);
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {

        }
    }

    @Override
    public void addRanking() {
        model.addRanking();
    }

    @Override
    public void setMainItem(final String category, final String item) {
        model.setMainItem(category, item);
    }

    @Override
    public String getMainItem(final String category) {
        return model.getMainItem(category);
    }

    @Override
    public List<Stats> getStats() {
        return model.getListOfStats();
    }

    @Override
    public boolean modAllStats(final double value) {
        return model.modAllStats(value);
    }

    @Override
    public boolean buy(final String item) {
        return model.buy(item);
    }

    @Override
    public String getCharacterName() {
        return model.getName();
    }

    @Override
    public void frame(final Frames frame) {
        viewManager.startFrame(frame);
    }

    @Override
    public boolean modStat(final String category) {
        return model.modStat(category);
    }

    @Override
    public void setViewManager(final View newView) {
        this.viewManager = new ViewManager(newView, this);
    }

    @Override
    public ViewManager getViewManager() {
        return this.viewManager;
    }

    @Override
    public void setCoin(final int amount) {
        model.addMoney(amount);
    }

    @Override
    public void setAmount(final int value) {
        this.timerAmount = value;
    }

    @Override
    public void setDecrementFactor(final double fact) {
        model.setDecrementFactor(fact);
    }

    @Override
    public double getAge() {
        return model.getAge();
    }

    @Override
    public void updateAge() {
        viewManager.updateAge();
    }

}