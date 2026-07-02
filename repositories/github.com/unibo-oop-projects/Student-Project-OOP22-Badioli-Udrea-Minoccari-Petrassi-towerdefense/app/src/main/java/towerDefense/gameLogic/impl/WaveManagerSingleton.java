package towerDefense.gameLogic.impl;
import java.util.Random;

public class WaveManagerSingleton {
    
    private GameLogicImpl gameLogicImpl = new GameLogicImpl();
    private long start;
    private int waveSize;
    private Random random = new Random();
    private int counter;
    private long timerWaves;
    private static WaveManagerSingleton instance = null;
    private final int WAVECOUNTER = 2;
    private final double TIMEMULTIPLIER = 1.2;
    private final int FIRSTWAVEMIN = 2;
    private final int FIRSTWAVERANGE = 2;

    private WaveManagerSingleton() {
        this.start = System.currentTimeMillis();
        this.waveSize = random.nextInt(FIRSTWAVERANGE) + FIRSTWAVEMIN;
        for(int i = 0; i < waveSize; i++){
            gameLogicImpl.summonEnemy();
        }
        this.counter = 0;
        this.timerWaves = 10000;
    }

    /**
     * @return the only existing instance of this class
     */
    public static WaveManagerSingleton getInstance() {
        if(instance == null) {
            instance = new WaveManagerSingleton();
        }
        return instance;
    }

    /**
     * Method called to spawn a wave of enemies, once every 10000 loops
     */
    public void spawnWave(){
        long now = System.currentTimeMillis();
        if((now - start) >= timerWaves){
            for(int i = 0; i < waveSize; i++){
                gameLogicImpl.summonEnemy();
                start = now;
            }
            if(counter > WAVECOUNTER) {
                this.waveSize++;
                this.timerWaves *= TIMEMULTIPLIER;
                this.counter = 0;
            } else {
                counter++;
            }
        }
    }
}
