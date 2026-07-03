package model.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import model.entities.powerup.PowerUpType;

/**
 * Class that implements the global data.
 */
public class GlobalDataImpl implements GlobalData {

    /**
     * 
     */
    private static final long serialVersionUID = -6744064130529602212L;

    private static final int NUM_HIGHSCORES = 5;
    private static final Comparator<HighScore> HIGH_SCORES_COMPARATOR = (a, b) -> Integer.compare(a.getScore(),
            b.getScore());
    private static final int FROM_MILLISECONDS_TO_MINUTE = 60000;

    private int gamePlayed;
    private int timePlayed;
    private int numDeadEnemies;
    private final Map<PowerUpType, Integer> numPowerUp;
    private final List<HighScore> highScores;

    /**
     * Constructor used to initialize data when Magnum Chaos start for the first
     * time.
     */
    public GlobalDataImpl() {
        this.gamePlayed = 0;
        this.timePlayed = 0;
        this.numDeadEnemies = 0;
        this.numPowerUp = new EnumMap<>(PowerUpType.class);
        Arrays.asList(PowerUpType.values()).forEach(p -> this.numPowerUp.put(p, 0));
        this.highScores = new ArrayList<>();
    }

    @Override
    public final Map<AchievementType, Achievement> getAchievements() {
        final Map<AchievementType, Achievement> achievements = new EnumMap<>(AchievementType.class);
        for (final AchievementType type : AchievementType.values()) {
            switch (type) {
            case GAMES_PLAYED:
                achievements.put(type, new AchievementImpl(type, this.gamePlayed));
                break;
            case TIME_PLAYED:
                achievements.put(type, new AchievementImpl(type, this.timePlayed / FROM_MILLISECONDS_TO_MINUTE));
                break;
            case NUM_DEAD_ENEMIES:
                achievements.put(type, new AchievementImpl(type, this.numDeadEnemies));
                break;
            case NUM_POWER_UP:
                achievements.put(type,
                        new AchievementImpl(type, this.numPowerUp.values().stream().mapToInt(n -> n).sum()));
                break;
            default:
                break;
            }
        }
        return Collections.unmodifiableMap(achievements);
    }

    @Override
    public final List<HighScore> getHighScores() {
        return Collections.unmodifiableList(new ArrayList<>(this.highScores));
    }

    @Override
    public final boolean isHighscore(final int score) {
        return this.highScores.size() < GlobalDataImpl.NUM_HIGHSCORES
                || score > this.highScores.stream().mapToInt(h -> h.getScore()).min().getAsInt();
    }

    @Override
    public final void addGameData(final GameData gameData, final Optional<String> name) {
        this.gamePlayed++;
        this.numDeadEnemies += gameData.getNumDeadEemies();
        Arrays.asList(PowerUpType.values())
                .forEach(p -> this.numPowerUp.put(p, this.numPowerUp.get(p) + gameData.getNumPowerUpTakenByType(p)));
        this.timePlayed += gameData.getTimer();
        if (isHighscore(gameData.getScore())) {
            this.highScores.add(new HighScoreImpl((name.isPresent() && !name.get().isEmpty()) ? name.get() : "player",
                    gameData.getScore()));
            /* Store only the first NUM_HIGHSCORES bests scores */
            if (this.highScores.size() > NUM_HIGHSCORES) {
                this.highScores.remove(this.highScores.stream().min(HIGH_SCORES_COMPARATOR).get());
            }
        }
    }

}
