package virtualworld;

import java.util.List;
import java.util.Set;
import entity.UUIDActor;
import formations.EnemyFormation;
import model.WaveInfo;

public interface Wave {
	/**
	 * 
	 * @return a boolean indicating whether the wave is over or not
	 */
    public boolean isEnded();
    
    /**
     * Adds the appropriate amount of formations the wave is composed of to a list, based on difficulty
     */
    public void setEnemies(EnemyFormation formation);
    
    /**
     * 
     * @return a boolean indicating whether there are still alive enemies in the current formation
     */
    public boolean formationIsOver();
    
    /**
     * 
     * @return the current formation in the current wave
     */
    public Set<UUIDActor> getCurrentFormation();
    
    /**
     * 
     * @return the next formation in the wave if there are any
     */
    public Set<UUIDActor> getNextFormation();
    
    /**
     * 
     * @return a list representing the current wave where each element is a formation
     */
    public List<Set<UUIDActor>> getWave();
    
    public WaveInfo getCurrentInfo();

}
