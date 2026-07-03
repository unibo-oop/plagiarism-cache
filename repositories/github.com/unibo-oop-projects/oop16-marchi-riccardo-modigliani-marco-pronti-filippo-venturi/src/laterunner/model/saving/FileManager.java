package laterunner.model.saving;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import laterunner.model.shop.Shop;
import laterunner.model.user.User;

/**
 * Class which contains functions to save and load user's info.
 */
public final class FileManager {

    private static final String FILE = "file_manager.txt";

    private FileManager() { }

    /**
     * Saves user's info from file.
     */
    public static void saveToFile() {
        try (BufferedWriter w = Files.newBufferedWriter(Paths.get(FILE))) {
            List<String> toFile = getFieldsList();
            Iterator<String> it = toFile.iterator();
            while (it.hasNext()) {
                w.write(it.next());
                it.remove();
                w.newLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    /**
     * Loads user's info from file.
     */
    public static void loadFromFile() {
        List<String> fromFile = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(FILE))) {
            fromFile = br.lines().collect(Collectors.toList());
            setFieldsList(fromFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> getFieldsList() {
        List<String> list = new ArrayList<>();
        list.add("" + User.getUser().getMoney());
        list.add("" + User.getUser().getUserLives());
        list.add("" + User.getUser().getLevelReached());
        list.add("" + User.getUser().getSpeedMul());
        list.add("" + User.Statistic.getStatistic().getGamesPlayed());
        list.add("" + User.Statistic.getStatistic().getLostLives());
        list.add("" + User.Statistic.getStatistic().getSurvivalHighscore());
        list.add("" + User.Statistic.getStatistic().getMotorbikeHits());
        list.add("" + User.Statistic.getStatistic().getObstacleCarHits());
        list.add("" + User.Statistic.getStatistic().getTruckHits());
        list.add("" + Shop.getShop().getLifeCost());
        list.add("" + Shop.getShop().getSpeedCost());
        return list;
    }

    private static void setFieldsList(final List<String> list) {
        Iterator<String> it = list.iterator();
        User.getUser().setMoney(Integer.parseInt(it.next()));
        it.remove();
        User.getUser().setUserLives(Integer.parseInt(it.next()));
        it.remove();
        User.getUser().setLevelReached(Integer.parseInt(it.next()));
        it.remove();
        User.getUser().setSpeedMul(Double.parseDouble(it.next()));
        it.remove();
        User.Statistic.getStatistic().setGamesPlayed(Long.parseLong(it.next()));
        it.remove();
        User.Statistic.getStatistic().setLostLives(Long.parseLong(it.next()));
        it.remove();
        User.Statistic.getStatistic().setSurvivalHighscore(Long.parseLong(it.next()));
        it.remove();
        User.Statistic.getStatistic().setMotorbikeHits(Long.parseLong(it.next()));
        it.remove();
        User.Statistic.getStatistic().setObstacleCarHits(Long.parseLong(it.next()));
        it.remove();
        User.Statistic.getStatistic().setTruckHits(Long.parseLong(it.next()));
        it.remove();
        Shop.getShop().setLifeCost(Integer.parseInt(it.next()));
        it.remove();
        Shop.getShop().setSpeedCost(Integer.parseInt(it.next()));
        it.remove();
    }
}
