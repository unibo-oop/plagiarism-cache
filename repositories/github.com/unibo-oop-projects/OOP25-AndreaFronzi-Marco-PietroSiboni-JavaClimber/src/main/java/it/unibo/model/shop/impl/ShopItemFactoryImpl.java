package it.unibo.model.shop.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import it.unibo.model.shop.api.ShopItem;
import it.unibo.model.shop.api.ShopItemFactory;
import it.unibo.model.shop.api.ShopItemStat;
import it.unibo.model.shop.api.ShopItemType;

/**
 * Implementation of {@link ShopItemFactory} interface.
 */
public class ShopItemFactoryImpl implements ShopItemFactory {

    private static final int ZERO = 0;
    private static final double MULTIPLIER_1_2 = 1.2;
    private static final double MULTIPLIER_1_3 = 1.3;
    private static final double MULTIPLIER_1_4 = 1.4;
    private static final double MULTIPLIER_1_5 = 1.5;
    private static final double MULTIPLIER_1_6 = 1.6;
    private static final double MULTIPLIER_1_7 = 1.7;
    private static final double MULTIPLIER_2 = 2.0;
    private static final double MULTIPLIER_3 = 3.0;
    private static final int PRICE_30 = 30;
    private static final int PRICE_50 = 50;
    private static final int PRICE_60 = 60;
    private static final int PRICE_75 = 75;
    private static final int PRICE_100 = 100;
    private static final int PRICE_150 = 150;
    private static final int PRICE_175 = 175;
    private static final int PRICE_200 = 200;
    private static final int PRICE_300 = 300;
    private static final int DURATION_3 = 3;
    private static final int DURATION_5 = 5;
    private static final int DURATION_7 = 7;

    private final List<ShopItem> skins;
    private final List<ShopItem> powerUpsTemporary;
    private final List<ShopItem> powerUpsPermanent;

    /**
     * Constructor for ShopItemFactoryImpl that initializes the shop items.
     */
    public ShopItemFactoryImpl() {
        this.skins = createSkins();
        this.powerUpsTemporary = createPowerUpsTemporary();
        this.powerUpsPermanent = createPowerUpsPermanent();
    }

    /**
     * Create the list of skin items available in the shop.
     * 
     * @return the list of skin items
     */
    private List<ShopItem> createSkins() {
        return List.of(
                new SkinItemImpl("s_basic", "Basic Alien", "Original alien", ZERO, Map.of()),
                new SkinItemImpl("s_sub", "Submarine Alien", "Directly from the sea with x1.2 Speed", PRICE_30,
                        Map.of(ShopItemStat.SPEED, MULTIPLIER_1_2)),
                new SkinItemImpl("s_basket", "Basketball Alien", "From the NBA with x1.2 Jump", PRICE_50,
                        Map.of(ShopItemStat.JUMP_HEIGHT, MULTIPLIER_1_2)),
                new SkinItemImpl("s_bunny", "Bunny Alien", "From the forest with x1.3 Speed", PRICE_60,
                        Map.of(ShopItemStat.SPEED, MULTIPLIER_1_3)),
                new SkinItemImpl("s_soccer", "Soccer Alien", "From the Soccer field with x1.4 Speed", PRICE_75,
                        Map.of(ShopItemStat.SPEED, MULTIPLIER_1_4)),
                new SkinItemImpl("s_frank", "Frankenstain Alien", "From the horror movie with x1.4 Jump", PRICE_75,
                        Map.of(ShopItemStat.JUMP_HEIGHT, MULTIPLIER_1_4)),
                new SkinItemImpl("s_astro", "Astronaut alien", "From the space with x1.5 Jump", PRICE_100,
                        Map.of(ShopItemStat.JUMP_HEIGHT, MULTIPLIER_1_5)),
                new SkinItemImpl("s_ninja", "Ninja Alien", "From the Ninja Clan with x1.5 Speed", PRICE_100,
                        Map.of(ShopItemStat.SPEED, MULTIPLIER_1_5)),
                new SkinItemImpl("s_frozen", "Frozen Alien", "From the ice world with x1.4 Speed and x1.4 Jump", PRICE_150,
                        Map.of(ShopItemStat.SPEED, MULTIPLIER_1_4, ShopItemStat.JUMP_HEIGHT, MULTIPLIER_1_4)),
                new SkinItemImpl("s_ghost", "Ghost Alien", "From the haunted house with x1.5 Speed and x1.5 Jump", PRICE_175,
                        Map.of(ShopItemStat.SPEED, MULTIPLIER_1_5, ShopItemStat.JUMP_HEIGHT, MULTIPLIER_1_5)),
                new SkinItemImpl("s_ice", "Ice Alien", "From the ice cave with x1.6 Speed and x1.6 Jump", PRICE_200,
                        Map.of(ShopItemStat.SPEED, MULTIPLIER_1_6, ShopItemStat.JUMP_HEIGHT, MULTIPLIER_1_6)),
                new SkinItemImpl("s_jungle", "Jungle Alien", "From the jungle with x1.7 Speed and x1.7 Jump", PRICE_300,
                        Map.of(ShopItemStat.SPEED, MULTIPLIER_1_7, ShopItemStat.JUMP_HEIGHT, MULTIPLIER_1_7)));
    }

    /**
     * Create the list of temporary power up items available in the shop.
     * 
     * @return the list of temporary power up items
     */
    private List<ShopItem> createPowerUpsTemporary() {
        return List.of(
                new PowerUpItemImpl("pt_jump1", "Power Jump 1", "Jump higher x1.3 for 5 matches", PRICE_50,
                        ShopItemType.TEMPORARY_UPGRADE, Map.of(ShopItemStat.JUMP_HEIGHT, MULTIPLIER_1_3), DURATION_5),
                new PowerUpItemImpl("pt_jump2", "Power Jump 2", "Jump higher x1.3 for 5 matches", PRICE_75,
                        ShopItemType.TEMPORARY_UPGRADE, Map.of(ShopItemStat.JUMP_HEIGHT, MULTIPLIER_1_3), DURATION_5),
                new PowerUpItemImpl("pt_jump3", "Power Jump 3", "Jump higher x1.5 for 7 matches", PRICE_100,
                        ShopItemType.TEMPORARY_UPGRADE, Map.of(ShopItemStat.JUMP_HEIGHT, MULTIPLIER_1_5), DURATION_7),

                new PowerUpItemImpl("pt_speed1", "Speed Boost 1", "Speed boost x1.3 for 5 matches", PRICE_50,
                        ShopItemType.TEMPORARY_UPGRADE, Map.of(ShopItemStat.SPEED, MULTIPLIER_1_3), DURATION_5),
                new PowerUpItemImpl("pt_speed2", "Speed Boost 2", "Speed boost x1.3 for 5 matches", PRICE_75,
                        ShopItemType.TEMPORARY_UPGRADE, Map.of(ShopItemStat.SPEED, MULTIPLIER_1_3), DURATION_5),
                new PowerUpItemImpl("pt_speed3", "Speed Boost 3", "Speed boost x1.5 for 7 matches", PRICE_100,
                        ShopItemType.TEMPORARY_UPGRADE, Map.of(ShopItemStat.SPEED, MULTIPLIER_1_5), DURATION_7),

                new PowerUpItemImpl("pt_coin_1", "Coin Multiplier 1", "Coin multiplier x2.0 for 3 matches", PRICE_75,
                        ShopItemType.TEMPORARY_UPGRADE, Map.of(ShopItemStat.COIN_MULTIPLIER, MULTIPLIER_2), DURATION_3),
                new PowerUpItemImpl("pt_coin_2", "Coin Multiplier 2", "Coin multiplier x2.0 for 5 matches", PRICE_100,
                        ShopItemType.TEMPORARY_UPGRADE, Map.of(ShopItemStat.COIN_MULTIPLIER, MULTIPLIER_2), DURATION_5),
                new PowerUpItemImpl("pt_coin_3", "Coin Multiplier 3", "Coin multiplier x3.0 for 5 matches", PRICE_150,
                        ShopItemType.TEMPORARY_UPGRADE, Map.of(ShopItemStat.COIN_MULTIPLIER, MULTIPLIER_3), DURATION_5));
    }

    /**
     * Create the list of permanent power up items available in the shop.
     * 
     * @return the list of permanent power up items
     */
    private List<ShopItem> createPowerUpsPermanent() {
        return List.of(
                /* Speed PowerUp permanent */
                new PowerUpItemImpl("pp_speed_1", "Speed Boost 1", "Permanent Speed boost 1.3", PRICE_30,
                        ShopItemType.PERMANENT_UPGRADE, Map.of(ShopItemStat.SPEED, MULTIPLIER_1_3), ZERO),
                new PowerUpItemImpl("pp_speed_2", "Speed Boost 2", "Permanent Speed boost 1.4", PRICE_50,
                        ShopItemType.PERMANENT_UPGRADE, Map.of(ShopItemStat.SPEED, MULTIPLIER_1_4), ZERO),
                new PowerUpItemImpl("pp_speed_3", "Speed Boost 3", "Permanent Speed boost 1.5", PRICE_60,
                        ShopItemType.PERMANENT_UPGRADE, Map.of(ShopItemStat.SPEED, MULTIPLIER_1_5), ZERO),
                new PowerUpItemImpl("pp_speed_4", "Speed Boost 4", "Permanent Speed boost 1.6", PRICE_75,
                        ShopItemType.PERMANENT_UPGRADE, Map.of(ShopItemStat.SPEED, MULTIPLIER_1_6), ZERO),
                new PowerUpItemImpl("pp_speed_5", "Speed Boost 5", "Permanent Speed boost 1.7", PRICE_100,
                        ShopItemType.PERMANENT_UPGRADE, Map.of(ShopItemStat.SPEED, MULTIPLIER_1_7), ZERO),
                /* Jump PowerUp permanent */
                new PowerUpItemImpl("pp_jump_1", "Power Jump 1", "Permanent Jump higher 1.3", PRICE_30,
                        ShopItemType.PERMANENT_UPGRADE, Map.of(ShopItemStat.JUMP_HEIGHT, MULTIPLIER_1_3), ZERO),
                new PowerUpItemImpl("pp_jump_2", "Power Jump 2", "Permanent Jump higher 1.4", PRICE_50,
                        ShopItemType.PERMANENT_UPGRADE, Map.of(ShopItemStat.JUMP_HEIGHT, MULTIPLIER_1_4), ZERO),
                new PowerUpItemImpl("pp_jump_3", "Power Jump 3", "Permanent Jump higher 1.5", PRICE_60,
                        ShopItemType.PERMANENT_UPGRADE, Map.of(ShopItemStat.JUMP_HEIGHT, MULTIPLIER_1_5), ZERO),
                new PowerUpItemImpl("pp_jump_4", "Power Jump 4", "Permanent Jump higher 1.6", PRICE_75,
                        ShopItemType.PERMANENT_UPGRADE, Map.of(ShopItemStat.JUMP_HEIGHT, MULTIPLIER_1_6), ZERO),
                new PowerUpItemImpl("pp_jump_5", "Power Jump 5", "Permanent Jump higher 1.7", PRICE_100,
                        ShopItemType.PERMANENT_UPGRADE, Map.of(ShopItemStat.JUMP_HEIGHT, MULTIPLIER_1_7), ZERO));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ShopItem> getSkins() {
        return List.copyOf(this.skins);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ShopItem> getPowerUpsTemporary() {
        return List.copyOf(this.powerUpsTemporary);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ShopItem> getPowerUpsPermanent() {
        return List.copyOf(this.powerUpsPermanent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ShopItem> getAllItems() {
        return Stream.of(skins, powerUpsTemporary, powerUpsPermanent)
                .flatMap(List::stream)
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ShopItem> getItemById(final String itemId) {
        return getAllItems().stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst();
    }

}
