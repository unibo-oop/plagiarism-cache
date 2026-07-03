package data;


/**
 * this enum contains the lands' data.
 */
public enum LandsData {

    /**
     *  each land has a name and an array of indices of neighboring lands.
     */
    ALASKA("Alaska", new int[] {2, 4, 29}),
    TERRITORI_DELNORD_OVEST("Territori del Nord-Ovest", new int[] {1, 3, 4, 5}),
    GROENLANDIA("Groenlandia", new int[] {2, 5, 6, 14}),
    ALBERTA("Alberta", new int[] {1, 2, 5, 7}),
    ONTARIO("Ontario", new int [] {2, 3, 4, 6, 7, 8}),
    QUEBEC("Quebec", new int [] {3, 5, 8}),
    STATI_UNITI_ORIENTALI("Stati Uniti Orientali", new int [] {4, 5, 8, 9}),
    STATI_UNITI_OCCIDENTALI("Stati Uniti Occidentali", new int [] {5, 6, 7, 9}),
    AMERICA_CENTRALE("America Centrale", new int [] {7, 8, 10}),
    VENEZUELA("Venezuela", new int [] {9, 11, 12}),
    PERU("Perù", new int [] {10, 12, 13}),
    BRASILE("Brasile", new int [] {10, 11, 13, 21}),
    ARGENTINA("Argentina", new int [] {11, 12}),
    ISLANDA("Islanda", new int [] {3, 15, 16}),
    GRAN_BRETAGNA("Gran Bretagna", new int [] {14, 16, 18, 19}),
    SCANDINAVIA("Scandinavia", new int [] {14, 15, 17, 19}),
    UCRAINA("Ucraina", new int [] {16, 19, 20, 30, 33, 36}),
    EUROPA_OCCIDENTALE("Europa Occidentale", new int [] {15, 19, 20, 21}),
    EUROPA_SETTENTRIONALE("Europa Settentrionale", new int [] {15, 16, 17, 18, 20}),
    EUROPA_MERIDIONALE("Europa Meridionale", new int [] {17, 18, 19, 21, 22, 36}),
    AFRICA_DEL_NORD("Africa del Nord", new int [] {12, 18, 20, 22, 23, 24}),
    EGITTO("Egitto", new int [] {20, 21, 24, 36}),
    CONGO("Congo", new int [] {21, 24, 25}),
    AFRICA_ORIENTALE("Africa Orientale", new int [] {21, 22, 23, 25, 26}),
    AFRICA_DEL_SUD("Afica del Sud", new int [] {23, 24, 26}),
    MADAGASCAR("Madagascar", new int [] {24, 25}),
    SIBERIA("Siberia", new int [] {28, 30, 31, 34, 35}),
    JACUZIA("Jacuzia", new int [] {27, 29, 31}),
    KAMCHATKA("Kamchatka", new int [] {1, 28, 31, 32, 34}),
    URALI("Urali", new int [] {17, 27, 33, 35}),
    CITA("Cita", new int [] {27, 28, 29, 34}),
    GIAPPONE("Giappone", new int [] {29, 34}),
    AFGANISTAN("Afganistan", new int [] {17, 30, 35, 36}),
    MONGOLIA("Mongolia", new int [] {27, 29, 31, 32, 35,}),
    CINA("Cina", new int [] {27, 30, 33, 34, 36, 37, 38}),
    MEDIO_ORIENTE("Medio Oriente", new int [] {17, 20, 22, 33, 35, 37}),
    INDIA("India", new int [] {35, 36, 38}),
    SIAM("Siam", new int [] {35, 37, 39}),
    INDONESIA("Indonesia", new int [] {38, 40, 41}),
    NUOVA_GUINEA("Nuova Guinea", new int [] {39, 41, 42}),
    AUSTRALIA_OCCIDENTALE("Australia Occidentale", new int [] {39, 40, 42}),
    AUSTRALIA_ORIENTALE("Australia Orientale", new int [] {40, 41});

    private final String nome;
    private final int[] confini;
    /**
     *  number of lands.
     */
    public static final int NUMTERR = 42;

    LandsData(final String nome, final int[] confini) {
        this.nome = nome;
        this.confini = confini;
    }

    /**
     * 
     * @return nome.
     */
    public String getName() {
        return this.nome;
    }

    /**
     * @return array of confini.
     */
    public int[] getConfini() {
        return this.confini;
    } 

    @Override
    public String toString() {
        return this.name();
    }
}
