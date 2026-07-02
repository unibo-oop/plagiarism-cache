package talisman.model.cards;

public enum CardType {
    /**
     * The types of card present in the game.
     */
    OBJECT("Pick up"), ENEMY("Fight"), FOLLOWER("Hire");

    private final String actionName;

    CardType(final String actionName) {
        this.actionName = actionName;
    }

    public String getActionName() {
        return this.actionName;
    }
}
