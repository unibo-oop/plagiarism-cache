package model.construction;

import javafx.util.Pair;

/**
 * This enumerator contains all the type of buildings in the game.
 */
public enum ConstructionType {
    /**
     * A simple residential zone.
     */
    APPARTAMENTO("Appartamento", 200, 10, -10, -5, 15, null, null, "Un piccolo appartamento.", "/view/icons/image/LowResidential.png"),
    /**
     * A medium sized residential zone.
     */
    CONDOMINIO("Condominio", 660, 30, -30, -15, 40, null, null, "Un condominio di medie dimensioni.", "/view/icons/image/BlueMediumResidential.png"),
    /**
     * A large residential zone.
     */
    GRATTACIELO("Grattacielo", 1800, 90, -90, -45, 100, null, null, "Un grattacielo di grandi dimensioni", "/view/icons/image/Skyscraper.png"),
    /**
     * An industrial zone that offers a job to the population and produces money.
     */
    FABBRICA("Fabbrica", 300, 80, -60, 0, -10, new Pair<Integer, Integer>(9, 9), null, "Una fabbrica offre lavoro ai cittadini", "/view/icons/gif/Industry.gif"),
    /**
     * A shopping center that offers a job to the population and produces money.
     */
    COMMERCIALE("Centro Commerciale", 400, 120, -80, 0, 0, new Pair<Integer, Integer>(9, 9), null, "Un centro commerciale soddisfa le richieste dei cittadini", "/view/icons/image/ShoppingCenter.png"),
    /**
     * This type of structure produces energy but pollutes the air.
     */
    TERMOELETTRICA("Centrale Elettrica a Carbone", 1500, 0, 300, 0, 0, new Pair<Integer, Integer>(15, 15), null, "Questo tipo di centrali producono energia attraverso il carbone, inquinando però l'aria circostante", "/view/icons/gif/EnergyFactory.gif"),
    /**
     * This type of structure produces clean energy.
     */
    TURBINA("Pala Eolica", 400, 0, 60, 0, 0, null, null, "Questa struttura produce energia pulita.", "/view/icons/gif/WindMill.gif"),
    /**
     * This tower contains the water reserve of the city.
     */
    IDRICA("Torre Idrica", 500, 0, 0, 50, 0, null, null, "Le riserve d'acqua della tua città", "/view/icons/gif/WaterTower.gif"),
    /**
     * The police station offers safety.
     */
    POLIZIA("Stazione della Polizia", 1000, 0, -20, 0, -10, null, new Pair<Integer, Integer>(30, 30), "Una stazione di polizia rende le strade più sicure.", "/view/icons/image/police.png"),
    /**
     * The fire fighters can prevent wild fires.
     */
    POMPIERI("Stazione dei Vigili del Fuoco", 1000, 0, -20, -20, -10, null, new Pair<Integer, Integer>(30, 30), "La presenza dei vigli del fuoco può prevenire o estinguere gli incendi.", "/view/icons/image/FireStation.png"),
    /**
     * The hospital is essential for the population.
     */
    OSPEDALE("Ospedale", 1000, 0, -30, -20, -30, null, new Pair<Integer, Integer>(30, 30), "L'ospedale della città, migliora la salute dei tuoi cittadini.", "/view/icons/image/Hospital.png"),
    /**
     * A little park.
     */
    PARCO("Parco", 30, 0, 0, 0, 0, null, new Pair<Integer, Integer>(6, 6), "Una zona verde in mezzo alla tua città migliora la qualità dell'aria.", "/view/icons/gif/Park1.gif"),
    /**
     * A dump for all the city's trash.
     */
    DISCARICA("Discarica", 600, 0, -30, -10, 0, new Pair<Integer, Integer>(12, 12), null, "La discarica colleziona i rifiuti rendendo pulite le vie della città, ma nessuno vuole viverci affianco.", "/view/icons/gif/RubbishDump.gif"),
    /**
     * The stadium of the local team.
     */
    STADIO("Stadio", 3000, 250, -100, 150, 0, null, null, "Pane e Circo! Uno stadio offre svago ai cittadini.", "/view/icons/image/Stadium.png"),
    /**
     * In this structure you can play cards and dices.
     */
    CASINO("Casinò", 3000, 300, -150, -10, 0, null, null, "In un casinò i cittadini possono divertirsi e giocare d'azzardo.", "/view/icons/image/Casino.png"),
    /**
     * A common road.
     */
    STRADA("Strada", 10, 0, 0, 0, 0, null, null, "Una semplice strada che collega gli edifici della città", "/view/icons/image/Street.png");

    /**
     * The formal name of the building.
     */
    private final String type;
    /**
     * The construction cost (money).
     */
    private final Integer cost;
    /**
     * The daily money production/cost.
     */
    private final Integer moneyProduction;
    /**
     * The daily energy production/cost.
     */
    private final Integer energyInfluence;
    /**
     * The daily water production/cost.
     */
    private final Integer waterInfluence;
    /**
     * The number of people that live in the building.
     */
    private final Integer populationHousingCapacity;
    /**
     * SUSPENDED. The size of the surrounding area negatively affected by this building's pollution.
     */
    private final Pair<Integer, Integer> pollutionDebufArea;
    /**
     * SUSPENDED. The size of the surrounding area affected by this building's welfare bonus.
     */
    private final Pair<Integer, Integer> welfareBonusArea;
    /**
     * A simple description of this kind of building.
     */
    private final String description;
    /**
     * The path for the sprite gif.
     */
    private final String spritePath;

    /**
     * Default constructor.
     * 
     * @param type
     *          The name of this type of building.
     * @param cost
     *          The construction cost (money).
     * @param money
     *          The daily money production/cost.
     * @param energy
     *          The daily energy production/cost.
     * @param water
     *          The daily water production/cost.
     * @param population
     *          The number of people living in the structure.
     * @param pollution
     *          SUSPENDED. The size of the surrounding area negatively affected by this building's pollution.
     * @param welfare
     *          SUSPENDED. The size of the surrounding area affected by this building's welfare bonus.
     */
    ConstructionType(final String type, final int cost, final int money, final int energy, final int water, final int population, final Pair<Integer, Integer> pollution, final Pair<Integer, Integer> welfare, final String description, final String spritePath) {
        this.type = type;
        this.cost = cost;
        this.moneyProduction = money;
        this.energyInfluence = energy;
        this.waterInfluence = water;
        this.populationHousingCapacity = population;
        this.pollutionDebufArea = pollution;
        this.welfareBonusArea = welfare;
        this.description = description;
        this.spritePath = spritePath;
    }

    /**
     * @return String
     *          The formal name of this type of building.
     */
    public String getType() {
        return this.type;
    }

    /**
     * @return Integer
     *          The cost (in money) of this type of building.
     */
    public Integer getCost() {
        return cost;
    }

    /**
     * @return Integer
     *          The daily money production(+) or cost(-).
     */
    public Integer getMoneyProduction() {
        return moneyProduction;
    }

    /**
     * @return Integer
     *          The daily energy production(+) or cost(-).
     */
    public Integer getEnergyInfluence() {
        return energyInfluence;
    }

    /**
     * @return Integer
     *          The daily water production(+) or cost(-).
     */
    public Integer getWaterInfluence() {
        return waterInfluence;
    }

    /**
     * @return Integer
     *          This building population housing capacity.
     */
    public Integer getPopulationHousingCapacity() {
        return populationHousingCapacity;
    }

    /**
     * @return Pair<Integer, Integer>
     *          SUSPENDED. The size of the surrounding area negatively affected by this building's pollution.
     */
    public Pair<Integer, Integer> getPollutionDebufArea() {
        return pollutionDebufArea;
    }

    /**
     * @return Pair<Integer, Integer>
     *          SUSPENDED. The size of the surrounding area affected by this building's welfare bonus.
     */
    public Pair<Integer, Integer> getWelfareBonusArea() {
        return welfareBonusArea;
    }

    /**
     * @return String
     *          A simple description of this type of building.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return String
     *          The path to the .png or .gif of this structure.
     */
    public String getSpritePath() {
        return spritePath;
    }
}
