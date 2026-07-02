package talisman.model.action;

import talisman.Controllers;

import talisman.model.character.CharacterModelImpl;

/**
 * An action that adds or removes an amount from a player statistic.
 * 
 * @author Alberto Arduini
 *
 */
public class TalismanModifyStatisticAction extends TalismanAmountAction {
    private static final long serialVersionUID = -54189424904145602L;
    private static final String DESCRIPTION_FORMAT = "You %s %d %s";
    private static final String GAIN_NAME = "gain";
    private static final String LOSE_NAME = "lose";

    private final TalismanActionStatistic statistic;

    /**
     * Create a new modify statistic action.
     * 
     * @param amount    how much the statistic will be modified
     * @param statistic
     */
    public TalismanModifyStatisticAction(final int amount, final TalismanActionStatistic statistic) {
        super(amount);
        this.statistic = statistic;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return String.format(TalismanModifyStatisticAction.DESCRIPTION_FORMAT,
                this.isGain() ? TalismanModifyStatisticAction.GAIN_NAME : TalismanModifyStatisticAction.LOSE_NAME,
                Math.abs(this.getAmount()), this.getStatistic());
    }

    /**
     * Gets which statistic should be used for this throw.
     * 
     * @return the statistic
     */
    public TalismanActionStatistic getStatistic() {
        return this.statistic;
    }

    /**
     * Calculates if the amount applied to the statistic is positive.
     * 
     * @return true if {@code amount >= 0}, false otherwise
     */
    public boolean isGain() {
        return this.getAmount() >= 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void apply() {
        final int currentValue = this.getCurrentPlayerStatistic(this.getStatistic());
        this.setCurrentPlayerStatistic(this.getStatistic(), currentValue + this.getAmount());
        this.actionEnded();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canBeApplied() {
        return this.isGain() || this.getCurrentPlayerStatistic(this.getStatistic()) + this.getAmount() >= 0;
    }

    private int getCurrentPlayerStatistic(final TalismanActionStatistic statistic) {
        final CharacterModelImpl info = (CharacterModelImpl) Controllers.getCharactersController().getCurrentPlayer().getCurrentCharacter();
        switch (statistic) {
        case CRAFT:
            return info.getCraft();
        case FAITH:
            return info.getFate();
        case GOLD:
            return info.getGold();
        case HEALTH:
            return info.getHealth();
        case STRENGTH:
            return info.getStrength();
        default:
            throw new IllegalArgumentException("The statistic " + statistic + " is not valid!");
        }
    }

    private void setCurrentPlayerStatistic(final TalismanActionStatistic statistic, final int value) {
        final CharacterModelImpl info = (CharacterModelImpl) Controllers.getCharactersController().getCurrentPlayer().getCurrentCharacter();
        switch (statistic) {
        case CRAFT:
            info.setCraft(value);
            break;
        case FAITH:
            info.setFate(value);
            break;
        case GOLD:
            info.setGold(value);
            break;
        case HEALTH:
            info.setHealth(value);
            break;
        case STRENGTH:
            info.setStrength(value);
            break;
        default:
            throw new IllegalArgumentException("The statistic " + statistic + " is not valid!");
        }
    }
}
