package vg.utils.round;


import javafx.geometry.Dimension2D;
import vg.model.mystery_box.data_round.DataRound;
import vg.model.mystery_box.data_round.DataRoundImpl;
import vg.utils.V2D;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This utils class is used to create the position of the mystery box.
 */
public final class MysteryBoxPositionUtils {

    /* Round 1 */
    private static final List<DataRound> ROUND1 = List.of(
            new DataRoundImpl(new V2D(0.5f, 0.5f), false),
            new DataRoundImpl(new V2D(0.025f, 0.961f), false),
            new DataRoundImpl(new V2D(0.25f, 0.961f), true),
            new DataRoundImpl(new V2D(0.5f, 0.961f), false),
            new DataRoundImpl(new V2D(0.75f, 0.961f), true),
            new DataRoundImpl(new V2D(0.975f, 0.961f), false)
    );

    /* Round 2 */
    private static final List<DataRound> ROUND2 = List.of(
            new DataRoundImpl(new V2D(0.5f, 0.5f), false),
            new DataRoundImpl(new V2D(0.025f, 0.961f), false),
            new DataRoundImpl(new V2D(0.25f, 0.961f), true),
            new DataRoundImpl(new V2D(0.5f, 0.961f), false),
            new DataRoundImpl(new V2D(0.75f, 0.961f), true),
            new DataRoundImpl(new V2D(0.975f, 0.961f), false)
    );

    /* Round 3 */
    private static final List<DataRound> ROUND3 = List.of(
            new DataRoundImpl(new V2D(0.5f, 0.5f), false),
            new DataRoundImpl(new V2D(0.025f, 0.961f), false),
            new DataRoundImpl(new V2D(0.25f, 0.961f), true),
            new DataRoundImpl(new V2D(0.5f, 0.961f), false),
            new DataRoundImpl(new V2D(0.75f, 0.961f), true),
            new DataRoundImpl(new V2D(0.975f, 0.961f), false)
    );

    private static final Map<Integer, List<DataRound>> POSITION_ROUND = Map.of(1, ROUND1, 2, ROUND2, 3, ROUND3);

    /**
     * Provides the position of the mystery box, for each round.
     * @param round the round
     * @param area the area of the game
     * @return the position of the mystery box
     */
    public static List<DataRound> getDataRoundList(final int round, final Dimension2D area) {
        final List<DataRound> dataRoundList = POSITION_ROUND.get(round);
        return dataRoundList.stream().map(dataRound ->
                new DataRoundImpl(
                        new V2D(dataRound.getPosition().getX() * area.getWidth(), dataRound.getPosition().getY() * area.getHeight()),
                        dataRound.isBlinking())).collect(Collectors.toList());
    }

    private MysteryBoxPositionUtils() {
    }
}
