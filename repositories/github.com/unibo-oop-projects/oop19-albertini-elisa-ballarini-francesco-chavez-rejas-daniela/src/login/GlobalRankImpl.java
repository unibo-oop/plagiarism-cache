package login;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import utility.UtilsRank;

/**
 * Class that implements the global rank of the players in the application.
 */
public class GlobalRankImpl implements GlobalRank {

    private Map<String, Integer> rank;

    /**
     * 
     */
    public GlobalRankImpl() {
        if (UtilsRank.isRankPresent()) {
            this.rank = UtilsRank.readRankFromFile();
        } else {
            this.rank = new HashMap<>();
        }
    }

    /**
     * @param rank .
     */
    @JsonCreator
    public GlobalRankImpl(@JsonProperty("rank") final Map<String, Integer> rank) {
        this.rank = rank;
    }

    @Override
    public final Map<String, Integer> getRank() {
        return rank;
    }

    @Override
    public final void setRank(final Map<String, Integer> rank) {
        this.rank = rank;
    }

    @Override
    public final void updateRank(final Player updatePlayer) {
        this.rank.put(updatePlayer.getName(), updatePlayer.getBestScore());
        this.rank = UtilsRank.sortByValueDesc(this.getRank());
        UtilsRank.writeRankOnFile(this);
    }

    @Override
    public final String toString() {
        return "rank: " + this.rank;
    }

}
