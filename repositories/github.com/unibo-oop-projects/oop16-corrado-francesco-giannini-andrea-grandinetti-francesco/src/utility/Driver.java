package utility;

/**
 * F1 Drivers that will be playable in the game.
 */
public enum Driver {

    /**
     * Lewis Hamilton.
     */
    HAM("Hamilton", "Lewis", Team.MER),

    /**
     * Valtteri Bottas.
     */
    BOT("Bottas", "Valtteri", Team.MER),

    /**
     * Sebastian Vettel.
     */
    VET("Vettel", "Sebastian", Team.FER),

    /**
     * Kimi Raikkonen.
     */
    RAI("Raikkonen", "Kimi", Team.FER),

    /**
     * Daniel Ricciardo.
     */
    RIC("Ricciardo", "Daniel", Team.RDB),

    /**
     * Max Verstappen.
     */
    VER("Verstappen", "Max", Team.RDB),

    /**
     * Fernando Alonso.
     */
    ALO("Alonso", "Fernando", Team.MCL),

    /**
     * Stoffel Vandoorne.
     */
    VAN("Vandoorne", "Stoffel", Team.MCL),

    /**
     *Nico Hulkemberg.
     */
    HUL("Hulkenberg", "Nico", Team.REN),

    /**
     * Jolyon Palmer.
     */
    PAL("Palmer", "Jolyon", Team.REN);

    private final String name;
    private final String surname;
    private final Team team;

    Driver(final String surname, final String name, final Team team) {
        this.name = name;
        this.surname = surname;
        this.team = team;
    }

    /**
     * @return driver's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return driver's surname
     */
    public String getSurname() {
        return this.surname;
    }

    /**
     * @return driver's name and surname
     */
    public Team getTeam() {
        return this.team;
    }

    /**
     * @return driver's name and surname
     */
    public String toString() {
        return getName() + " " + getSurname();
    }

}