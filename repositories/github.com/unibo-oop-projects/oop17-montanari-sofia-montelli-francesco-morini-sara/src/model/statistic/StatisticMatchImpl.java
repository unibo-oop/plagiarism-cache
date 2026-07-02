package model.statistic;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.NotImplementedException;

import model.basic_component.Cell;

/**
 * Basic implementation of a {@link StatisticMatch}.
 *
 */
public final class StatisticMatchImpl implements StatisticMatch {

    private static final long serialVersionUID = 1L;
    private final String playerName;
    private final String opponentName;
    private final LocalDateTime matchStartDate;
    private final List<Cell> shootsPerformed;
    private final List<Cell> shootsReceived;
    private boolean winner;

    /**
     * 
     * @param playerName the name of the player.
     * @param opponentName the name of the opponent.
     * @param matchStartDate the date of the onset of the game.
     * @param shootsPerformed the list of hit cells by the player.
     * @param shootsReceived the list of hit cells of the player.
     */
    public StatisticMatchImpl(final String playerName, final String opponentName, final LocalDateTime matchStartDate,
            final List<Cell> shootsPerformed, final List<Cell> shootsReceived) {
        this.winner = false;
        this.playerName = playerName;
        this.opponentName = opponentName;
        this.matchStartDate = matchStartDate;
        this.shootsPerformed = shootsPerformed;
        this.shootsReceived = shootsReceived;
    }

    @Override
    public String getPlayerName() {
        return playerName;
    }

    @Override
    public String getOpponentName() {
        return opponentName;
    }

    @Override
    public LocalDateTime getMatchStartDate() {
        return matchStartDate;
    }

    @Override
    public List<Cell> getShootsPerformed() {
       return shootsPerformed;
    }

    @Override
    public List<Cell> getShootsReceived() {
        return shootsReceived;
    }

    @Override
    public long getShotPerformedCount() {
        return getShootsPerformed().size();
    }

    @Override
    public long getShotPerformedHitCount() {
        return getShootsPerformed().stream().filter(c -> c.getStatus().equals(Cell.Status.OCCUPIED_AND_TARGETED)).count();
    }

    @Override
    public long getShotReceivedCount() {
        return getShootsReceived().size();
    }

    @Override
    public long getShotReceivedHitCount() {
        return getShootsReceived().stream().filter(c -> c.getStatus().equals(Cell.Status.OCCUPIED_AND_TARGETED)).count();
    }

    @Override
     public void setWinner(final boolean winner) {
         this.winner = winner;
     }

    @Override
    public boolean isWinner() {
        return winner;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((matchStartDate == null) ? 0 : matchStartDate.hashCode());
        result = prime * result + ((opponentName == null) ? 0 : opponentName.hashCode());
        result = prime * result + ((playerName == null) ? 0 : playerName.hashCode());
        result = prime * result + ((shootsPerformed == null) ? 0 : shootsPerformed.hashCode());
        result = prime * result + ((shootsReceived == null) ? 0 : shootsReceived.hashCode());
        result = prime * result + (winner ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StatisticMatchImpl other = (StatisticMatchImpl) obj;
        if (matchStartDate == null) {
            if (other.matchStartDate != null) {
                return false;
            }
        } else if (!matchStartDate.equals(other.matchStartDate)) {
            return false;
        }
        if (opponentName == null) {
            if (other.opponentName != null) {
                return false;
            }
        } else if (!opponentName.equals(other.opponentName)) {
            return false;
        }
        if (playerName == null) {
            if (other.playerName != null) {
                return false;
            }
        } else if (!playerName.equals(other.playerName)) {
            return false;
        }
        return true;
    }

    @Override
    public void update() {
        throw new NotImplementedException("No need to call this method with the current implementation");
    }
}
