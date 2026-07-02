package talisman.model.action;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Objects;

import talisman.Controllers;

import talisman.model.action.TalismanRollActionSection.ApplyResult;
import talisman.model.character.CharacterModelImpl;

import talisman.util.DiceType;
import talisman.util.DiceUtils;
import talisman.view.TalismanRollActionResultWindow;

/**
 * Action for rolling a dice, based on a statistic if needed.
 * 
 * @author Alberto Arduini
 *
 */
public class TalismanRollAction extends TalismanActionImpl {
    private static final long serialVersionUID = 2847596850734221682L;
    private static final String ABSOLUTE_DESCRIPTION_FORMAT = "Roll 1 dice. If the result is at least:";
    private static final String RELATIVE_DESCRIPTION_FORMAT = "Roll 1 dice on %s. If the result is at least:";
    private static final String OPTION_FORMAT = System.lineSeparator() + "%d then: %s; ";
    private static final String LAST_OPTION_FORMAT = System.lineSeparator() + "otherwise: %s";

    private final TalismanActionStatistic statistic;
    private final DiceType diceType;
    private final List<TalismanRollActionSection> sections;
    private transient int lastResult;

    /**
     * Creates a new roll action.
     * 
     * @param amount        the minimum amount to reach
     * @param statistic     the statistic to base the roll on
     * @param successAction what to do on success
     * @param failedAction  what to do on failure
     */
    public TalismanRollAction(final int amount, final TalismanActionStatistic statistic,
            final TalismanAction successAction, final TalismanAction failedAction) {
        this(statistic, List.of(new TalismanRollActionSection(1, Objects.requireNonNull(failedAction)),
                new TalismanRollActionSection(amount, Objects.requireNonNull(successAction))));
    }

    /**
     * Creates a new roll action.
     * 
     * @param statistic      the statistic to base the roll on
     * @param resultSections the sections that indicate the possible actions base on
     *                       the results
     */
    public TalismanRollAction(final TalismanActionStatistic statistic,
            final List<TalismanRollActionSection> resultSections) {
        this(DiceType.SIX, statistic, resultSections);
    }

    /**
     * Creates a new roll action.
     * 
     * @param dice           the dice used for the action
     * @param statistic      the statistic to base the roll on
     * @param resultSections the sections that indicate the possible actions base on
     *                       the results
     */
    public TalismanRollAction(final DiceType dice, final TalismanActionStatistic statistic,
            final List<TalismanRollActionSection> resultSections) {
        this.diceType = dice;
        this.statistic = statistic;
        this.sections = List.copyOf(resultSections);
        this.sections.stream().map(s -> s.getAction()).forEach(a -> a.setActionEndedListener(this::actionEnded));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        final StringBuilder stringBuilder = new StringBuilder();
        if (this.getStatistic() == TalismanActionStatistic.NONE) {
            stringBuilder.append(String.format(TalismanRollAction.ABSOLUTE_DESCRIPTION_FORMAT));
        } else {
            stringBuilder.append(String.format(TalismanRollAction.RELATIVE_DESCRIPTION_FORMAT, this.getStatistic()));
        }
        for (int i = this.sections.size() - 1; i > 0; i--) {
            stringBuilder.append(this.getFormattedResult(TalismanRollAction.OPTION_FORMAT, i, true));
        }
        stringBuilder.append(this.getFormattedResult(TalismanRollAction.LAST_OPTION_FORMAT, 0, false));
        return stringBuilder.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void apply() {
        this.lastResult = DiceUtils.rollDice(this.diceType);
        int actualValue = this.getResult();
        final CharacterModelImpl playerStatistics = (CharacterModelImpl) Controllers.getCharactersController()
                .getCurrentPlayer().getCurrentCharacter();
        switch (this.getStatistic()) {
        case CRAFT:
            actualValue += playerStatistics.getCraft();
            break;
        case FAITH:
            actualValue += playerStatistics.getFate();
            break;
        case HEALTH:
            actualValue += playerStatistics.getHealth();
            break;
        case STRENGTH:
            actualValue += playerStatistics.getStrength();
            break;
        case GOLD:
            actualValue += playerStatistics.getGold();
        default:
            break;
        }
        for (int i = this.sections.size() - 1; i >= 0; i--) {
            final TalismanRollActionSection section = this.sections.get(i);
            if (section.apply(actualValue) != ApplyResult.VALUE_NOT_ENOUGH) {
                TalismanRollActionResultWindow.show(this.getResult(), section.getAction().getDescription());
                this.actionEnded();
                break;
            }
        }
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
     * Gets the last throw result.
     * 
     * @return the roll result
     */
    public int getResult() {
        return this.lastResult;
    }

    private String getFormattedResult(final String format, final int sectionIndex, final boolean showValue) {
        final TalismanRollActionSection section = this.sections.get(sectionIndex);
        if (showValue) {
            return String.format(format, section.getFromValue(), section.getAction().getDescription());
        } else {
            return String.format(format, section.getAction().getDescription());
        }
    }

    private void readObject(final ObjectInputStream stream) throws ClassNotFoundException, IOException {
        stream.defaultReadObject();
        this.sections.stream().map(s -> s.getAction()).forEach(a -> a.setActionEndedListener(this::actionEnded));
    }
}
