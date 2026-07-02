package it.unibo.plantsfarm.model.plant;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 * Registry containing all available plant types in the game.
 */
public final class PlantRegistry {

    //EDIBLE PLANTS
    public static final PlantType CARROT = new PlantType("Carrot", 3, 0, Rarity.COMMON,
            new EdibleBehavior(10, 2, 3));

    public static final PlantType ONION = new PlantType("Onion", 3, 0, Rarity.COMMON,
            new EdibleBehavior(12, 2, 3));

    public static final PlantType RADISH = new PlantType("Radish", 3, 0, Rarity.COMMON,
            new EdibleBehavior(8, 4, 5));

    public static final PlantType ZUCCHINI = new PlantType("Zucchini", 4, 1, Rarity.COMMON,
            new EdibleBehavior(15, 1, 3));

    public static final PlantType TOMATO = new PlantType("Tomato", 5, 2, Rarity.COMMON,
            new EdibleBehavior(7, 3, 7));

    public static final PlantType POTATO = new PlantType("Potato", 3, 0, Rarity.COMMON,
            new EdibleBehavior(15, 2, 3));

    public static final PlantType PEPPER = new PlantType("Pepper", 5, 2, Rarity.COMMON,
            new EdibleBehavior(15, 2, 4));

    public static final PlantType CORN = new PlantType("Corn", 5, 2, Rarity.COMMON,
            new EdibleBehavior(20, 1, 3));

    public static final PlantType EGGPLANT = new PlantType("Eggplant", 4, 1, Rarity.RARE,
            new EdibleBehavior(30, 1, 3));

    public static final PlantType APPLE = new PlantType("Apple", 5, 2, Rarity.RARE,
            new EdibleBehavior(15, 5, 10));

    public static final PlantType FIG = new PlantType("Fig", 6, 2, Rarity.RARE,
            new EdibleBehavior(15, 3, 12));

    public static final PlantType PUMPKIN = new PlantType("Pumpkin", 6, 2, Rarity.RARE,
            new EdibleBehavior(35, 1, 3));

    public static final PlantType CHERRY = new PlantType("Cherry", 5, 2, Rarity.RARE,
            new EdibleBehavior(5, 10, 25));

    public static final PlantType WATERMELON = new PlantType("Watermelon", 5, 1, Rarity.RARE,
            new EdibleBehavior(15, 6, 12));

    public static final PlantType MANGO = new PlantType("Mango", 5, 2, Rarity.EPIC,
            new EdibleBehavior(50, 5, 8));

    public static final PlantType AVOCADO = new PlantType("Avocado", 5, 2, Rarity.EPIC,
            new EdibleBehavior(75, 3, 7));

    public static final PlantType DRAGONFRUIT = new PlantType("DragonFruit", 5, 1, Rarity.EPIC,
            new EdibleBehavior(100, 2, 4));

    public static final PlantType ANANAS = new PlantType("Ananas", 3, 0, Rarity.EPIC,
            new EdibleBehavior(100, 1, 1));

    public static final PlantType PAPAYA = new PlantType("Papaya", 6, 2, Rarity.EPIC,
            new EdibleBehavior(55, 2, 6));

    public static final PlantType POMEGRANATE = new PlantType("Pomegranate", 7, 3, Rarity.LEGENDARY,
            new EdibleBehavior(40, 7, 10));

    public static final PlantType BUDDHAHAND = new PlantType("Buddha's Hand", 7, 3, Rarity.LEGENDARY,
            new EdibleBehavior(100, 3, 6));

    //ORNAMENTAL PLANTS

    public static final PlantType SNAPDRAGON = new PlantType("SnapDragon", 3, 0, Rarity.COMMON,
            new OrnamentalBehavior(PlantEffect.GROWTH_SPEED, 0.10));

    public static final PlantType BEGONIA = new PlantType("Begonia", 5, 0, Rarity.COMMON,
            new OrnamentalBehavior(PlantEffect.BIG_HARVEST, 0.10));

    public static final PlantType MONSTERA = new PlantType("Monstera", 4, 0, Rarity.RARE,
            new OrnamentalBehavior(PlantEffect.GROWTH_SPEED, 0.25));

    public static final PlantType BLEEDINGHEARTH = new PlantType("BleedingHearth", 4, 0, Rarity.RARE,
            new OrnamentalBehavior(PlantEffect.BIG_HARVEST, 0.25));

    public static final PlantType HIBISCUS = new PlantType("Hibiscus", 4, 0, Rarity.RARE,
            new OrnamentalBehavior(PlantEffect.GROWTH_SPEED, 0.25));

    public static final PlantType STRELITZIA = new PlantType("Strelitzia", 5, 0, Rarity.EPIC,
            new OrnamentalBehavior(PlantEffect.BIG_HARVEST, 0.50));

    public static final PlantType ORCHID = new PlantType("Orchid", 3, 0, Rarity.EPIC,
            new OrnamentalBehavior(PlantEffect.GROWTH_SPEED, 0.50));

    public static final PlantType NEPENTHES = new PlantType("Nepenthes", 4, 0, Rarity.LEGENDARY,
            new OrnamentalBehavior(PlantEffect.BIG_HARVEST, 1.0));

    public static final PlantType RAFFLESIA = new PlantType("Rafflesia", 3, 0, Rarity.LEGENDARY,
            new OrnamentalBehavior(PlantEffect.GROWTH_SPEED, 1.0));

    private static final List<PlantType> ALL_PLANTS = new ArrayList<>();

    static {
        ALL_PLANTS.add(CARROT);
        ALL_PLANTS.add(ONION);
        ALL_PLANTS.add(RADISH);
        ALL_PLANTS.add(ZUCCHINI);
        ALL_PLANTS.add(TOMATO);
        ALL_PLANTS.add(POTATO);
        ALL_PLANTS.add(PEPPER);
        ALL_PLANTS.add(CORN);
        ALL_PLANTS.add(EGGPLANT);
        ALL_PLANTS.add(APPLE);
        ALL_PLANTS.add(FIG);
        ALL_PLANTS.add(PUMPKIN);
        ALL_PLANTS.add(CHERRY);
        ALL_PLANTS.add(WATERMELON);
        ALL_PLANTS.add(MANGO);
        ALL_PLANTS.add(AVOCADO);
        ALL_PLANTS.add(DRAGONFRUIT);
        ALL_PLANTS.add(ANANAS);
        ALL_PLANTS.add(PAPAYA);
        ALL_PLANTS.add(POMEGRANATE);
        ALL_PLANTS.add(BUDDHAHAND);
        ALL_PLANTS.add(SNAPDRAGON);
        ALL_PLANTS.add(BEGONIA);
        ALL_PLANTS.add(MONSTERA);
        ALL_PLANTS.add(BLEEDINGHEARTH);
        ALL_PLANTS.add(HIBISCUS);
        ALL_PLANTS.add(STRELITZIA);
        ALL_PLANTS.add(ORCHID);
        ALL_PLANTS.add(NEPENTHES);
        ALL_PLANTS.add(RAFFLESIA);
    }

    private PlantRegistry() {
        //Utility class constructor
    }

    /**
     * Returns an unmodifiable list of all plants.
     *
     * @return List of PlantType.
     */
    public static List<PlantType> getAll() {
        return Collections.unmodifiableList(ALL_PLANTS);
    }
}
