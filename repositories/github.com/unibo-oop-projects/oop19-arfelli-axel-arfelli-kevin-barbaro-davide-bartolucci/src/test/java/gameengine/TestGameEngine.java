package gameengine;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.EntitySpawner;
<<<<<<< HEAD
=======
import entity.PlayerShip;
>>>>>>> 02c6c3500428d0f8ab23d08ea6d91ec9ae05050b
import entity.UUIDActor;
import entity.UUIDProjectile;
import virtualworld.Stage;
import virtualworld.VirtualMap;
import virtualworld.Wave;

class TestGameEngine {
    private GameEngine engine;
    private Stage stage;
    private int tick;

    @BeforeEach
    void prepare() {
        tick = 20;
        this.engine = new GameEnginePrototype(20, new GameLoggerAdaptor());
    }
    
    private void sleep() {
        sleep(1000/tick);
    }
    
    private void sleep(final long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    void testStage() {
        this.stage = new TestStage();
        
        this.engine.start(stage);
        System.out.println("Stage Started");
        sleep(1000);
        assertTrue(engine.isStarted());
        this.engine.stop();
    }
    
    @Test
    void testPauseAndResume() {
        this.stage = new TestStage();
        
        this.engine.start(stage);
        System.out.println("Stage Started");
        sleep(1000);
        assertTrue(engine.isStarted());
        this.engine.pause();
        assertTrue(engine.isPaused());
        this.engine.resume();
        assertFalse(engine.isPaused());
        this.engine.stop();
        assertFalse(this.engine.isStarted());
    }


    class TestStage implements Stage {

        private boolean alive = false;
        private boolean running = false;
        private EntitySpawner spawner;

        @Override
        public void stop() {
            if(!isEnded()) {
                this.alive=false;
                resume();                
                spawner.getDespawnedActors();
                spawner.getDespawnedProjectiles();
            }
        }
        @Override
        public void start() {
            this.alive=true;
        }

        @Override
        public void setSpawner(EntitySpawner spawner) {
            this.spawner = spawner;
        }

        @Override
        public synchronized void resume() {
            if(isPaused() && !isEnded()) {
                this.running=true;
                notifyAll();
            }
        }

        @Override
        public synchronized void pause() {
            if(!isPaused() && !isEnded()) {
                this.running=false;
            }
        }

        @Override
        public boolean isPaused() {
            return !this.running;
        }

        @Override
        public boolean isEnded() {
            return !this.alive;
        }

        @Override
        public void run() {
            start();
            while(alive){
            }
            stop();
        }
<<<<<<< HEAD
		@Override
		public Wave getWave() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public VirtualMap<UUIDActor, UUIDProjectile> getMap() {
			// TODO Auto-generated method stub
			return null;
		}
=======
        @Override
        public boolean isReady() {
            // TODO Auto-generated method stub
            return false;
        }
        @Override
        public Wave getWave() {
            // TODO Auto-generated method stub
            return null;
        }
        @Override
        public VirtualMap<UUIDActor, UUIDProjectile> getMap() {
            // TODO Auto-generated method stub
            return null;
        }
        @Override
        public PlayerShip getplayer() {
            // TODO Auto-generated method stub
            return null;
        }
        @Override
        public int getScore() {
            // TODO Auto-generated method stub
            return 0;
        }
>>>>>>> 02c6c3500428d0f8ab23d08ea6d91ec9ae05050b
    }
}
