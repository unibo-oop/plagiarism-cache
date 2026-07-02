package virtualworld;

import java.util.*;

import entity.Drone;
import entity.EnemyBoss;
import entity.Muncher;
import entity.Turret;
import entity.UUIDActor;
import formations.EnemyFormation;
import formations.EnemyType;
import gui.ControlsMenuController;
import javafx.util.Pair;
import model.LevelParametizer;
import model.LevelParametizerImpl;
import model.WaveInfo;

import java.util.Map;
import java.util.Map.Entry;

public class WaveGeneratorImpl implements WaveGenerator{

    private int current = 0;
    private final int numWaves = 15;
    private LevelParametizer parametizer = new LevelParametizerImpl(ControlsMenuController.getDiff(), numWaves, false);

    private Wave generate() {
        return new Wave() {
            
        	private WaveInfo info = parametizer.newWave();
            private List<Set<UUIDActor>> enemies = new ArrayList<>();
            private int currentFormation = 0;
            
            @Override
            public boolean isEnded() {
            	if(this.currentFormation==parametizer.getLevelDifficulty().getEnemyMULTIPLIER()-1) {
            		for(UUIDActor a : this.getCurrentFormation()) {
            			if(a.isAlive()) {
            				return false;
            			}
            		}
            	}
            	else {
            		return false;
            	}
            	return true;
            }
            
            public void setEnemies(EnemyFormation formation) {
            	for(int i=0; i<parametizer.getLevelDifficulty().getEnemyMULTIPLIER(); i++) {
            		Set<UUIDActor> formations = new HashSet<>();
            		Map<Pair<Integer, Integer>, EnemyType> map = new HashMap<>(formation.getFormationMap());
            		for(Entry<Pair<Integer, Integer>, EnemyType> entry : map.entrySet()) {
            			if(entry.getValue()==EnemyType.DRONE) {
            				formations.add(new Drone(entry.getKey().getKey(), entry.getKey().getValue(), parametizer.getLevelDifficulty().getEnemyInitialLEVEL(), this.info.elaborateInitialHP(Drone.BASE_LIFE)));
            			}
            			if(entry.getValue()==EnemyType.MUNCHER) {
            				formations.add(new Muncher(entry.getKey().getKey(), entry.getKey().getValue(), parametizer.getLevelDifficulty().getEnemyInitialLEVEL(), this.info.elaborateInitialHP(Drone.BASE_LIFE)));
            			}
            			if(entry.getValue()==EnemyType.TURRET) {
            				formations.add(new Turret(entry.getKey().getKey(), entry.getKey().getValue(), parametizer.getLevelDifficulty().getEnemyInitialLEVEL(), this.info.elaborateInitialHP(Drone.BASE_LIFE)));
            			}
            			if(entry.getValue()==EnemyType.BOSS) {
            				formations.add(new EnemyBoss(entry.getKey().getKey(), entry.getKey().getValue(), parametizer.getLevelDifficulty().getEnemyInitialLEVEL(), this.info.elaborateInitialDMG(EnemyBoss.BASE_LIFE)));
            				break;
            			}
            		}
            		this.enemies.add(formations);
            	}
            }
            
            public boolean formationIsOver() {
            	for(UUIDActor a : this.getCurrentFormation()) {
            		if(a.isAlive()) {
            			return false;
            		}
            	}
            	return true;
            }

            @Override
            public Set<UUIDActor> getCurrentFormation() {
                return this.enemies.get(this.currentFormation);
            }
            
            public Set<UUIDActor> getNextFormation(){
            	this.currentFormation++;
            	return this.enemies.get(this.currentFormation);
            }
            
            public List<Set<UUIDActor>> getWave(){
            	return this.enemies;
            }
            
            public WaveInfo getCurrentInfo() {
            	return this.info;
            }
        };

    }
    
    @Override
    public boolean hasNext() {
        if(this.current>=this.numWaves) {
            return false;
        }
        return true;
    }

    @Override
    public Wave next() {
        this.current++;
        return this.generate();
    }

    public LevelParametizer getParam() {
    	return this.parametizer;
    }
    
    public int getCurrent() {
    	return this.current;
    }
    
}
