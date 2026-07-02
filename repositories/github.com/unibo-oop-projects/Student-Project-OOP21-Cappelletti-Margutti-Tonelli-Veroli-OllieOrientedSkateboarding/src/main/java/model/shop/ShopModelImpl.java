package model.shop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.statistic.Statistics;

/**
 * Implements the Shop and its methods, which are needed by the player to buy elements.
 *
 */
public class ShopModelImpl implements ShopModel {

    private static final String SEP = File.separator;
    private static final String FILE_NAME = System.getProperty("user.home") + SEP + "OOS_shopItems.txt";
    private static final String SELECTED_SKIN_FILE_NAME = System.getProperty("user.home") + SEP + "OOS_selectedSkin.txt";
    private final List<ShopItem> items = new ArrayList<>();
    private List<ShopItem> purchasedItems = new ArrayList<>();
    private final MysteryBox mysteryBox;
    private ShopItem selectedSkin; 

    private final Statistics statistics;

    /**
     * 
     * @param statistics the game statistics. 
     */
    public ShopModelImpl(final Statistics statistics) {

        this.statistics = statistics;
        this.mysteryBox = new MysteryBoxImpl();
        Stream.of(Skins.values()).forEach(i -> items.add(new ShopItemImpl(i.getSkinName(), i.getPrice())));
        this.readPurchasedItems();
        try {
            this.selectedSkin = this.readSelectedSkin();
        } catch (IOException e) {
            this.selectedSkin = this.findShopItemFromString("Player.png");
        }
        this.purchasedItems.add(this.findShopItemFromString("Player.png")); 
    }

    /**
     * {@inheritDoc}
     */
    public void shopItemPayment(final ShopItem selectedItem) {
        if (this.checkPayment(selectedItem, this.statistics.getTotalCoins())) {
            this.purchaseSkin(selectedItem);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String misteryBoxPayment() {
        if (checkMystery(this.mysteryBox, this.statistics.getTotalCoins())) {
            purchaseBox(this.mysteryBox);
            return this.mysteryBox.createPrize(this.statistics);
        }
        return "";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkMystery(final MysteryBox box, final int coins) {
        return box.getPrice() <= coins; 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkPayment(final ShopItem selectedItem, final int coins) {
        return !this.purchasedItems.contains(selectedItem) && selectedItem.getPrice() <= coins;
    }

    /**
     * Purchases the selected skin updating the player's total coins. 
     * @param selectedItem
     */
    private void purchaseSkin(final ShopItem selectedItem) {
        selectedItem.purchase();
        this.statistics.setTotalCoins(this.statistics.getTotalCoins() - selectedItem.getPrice());
        this.purchasedItems.add(selectedItem);
        try {
            this.statistics.saveStatisticsOnFile();
        } catch (IOException e) {
            System.out.println("Error in write coins after a shopItem");
        }
    }

    /**
     * Purchases the MysteryBox updating the player's total coins.
     * @param box the MysteryBox.
     */
    private void purchaseBox(final MysteryBox box) {
        if (this.checkMystery(box, this.statistics.getTotalCoins())) {
            this.statistics.setTotalCoins(this.statistics.getTotalCoins() - box.getPrice());
            try {
                this.statistics.saveStatisticsOnFile();
            } catch (IOException e) {
                System.out.println("Error in write coins after a box");
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<ShopItem> getItems() {
        return items;
    }

    /**
     *{@inheritDoc}
     */
    public List<ShopItem> getPurchasedItems() {
        return purchasedItems;
    }

    /**
     * {@inheritDoc}
     */
    public void saveShopItem() throws IOException {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(FILE_NAME))) {
            items.stream()
            .forEach(elem -> {
                try {
                    if (purchasedItems.contains(elem)) {
                        bw.write("1"); 
                        bw.newLine(); 
                    } else {
                        bw.write("0"); 
                        bw.newLine(); 
                    }
                } catch (IOException e) {
                    System.out.println("Error in saveShopItem");
                }
            });
        }
    }

    /**
     * Reads the purchased items from file. 
     */
    private void readPurchasedItems() {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(FILE_NAME))) {
            final List<String> filesItems = br.lines().collect(Collectors.toList()); 
            Stream.iterate(0, i -> i + 1).limit(this.items.size()).forEach(i -> {
                if ("1".equals(filesItems.get(i))) {
                    purchasedItems.add(items.get(i));
                }
            });
        } catch (IOException e) {
            purchasedItems = new ArrayList<>(); 
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTotalCoins() {
        return this.statistics.getTotalCoins();
    }

    /**
     *{@inheritDoc} 
     */
    @Override
    public void writeSkinOnFile() throws IOException {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(SELECTED_SKIN_FILE_NAME))) {
                try {
                    bw.write(this.selectedSkin.getName());
                } catch (IOException e) {
                    System.out.println("Error in writeSkinOnFile");
                }
        }
    }

    /**
     * Reads from file which is the selected skin. 
     * @return the selected skins. 
     * @throws IOException
     */
    private ShopItem readSelectedSkin() throws IOException {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(SELECTED_SKIN_FILE_NAME))) {
            String stringName;
            try {
                stringName = br.readLine();
            } catch (IOException e) {
                stringName = "Player.png";
            }
            return this.findShopItemFromString(stringName);
        }

    }

    /**
     * Finds the selected ShopItem from its name. 
     * @param name the name of the ShopItem. 
     * @return the ShopItem.
     */
    private ShopItem findShopItemFromString(final String name) {
        return this.items.stream()
                .filter(it -> it.getName().equals(name))
                .findFirst()
                .get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSelected(final String name) {
        return this.selectedSkin.getName().equals(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSelected(final String name) {
        this.selectedSkin = this.findShopItemFromString(name);
    }

}
