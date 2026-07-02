package virtualworld;

import java.util.Set;
import java.util.stream.Collectors;

import entity.EntitySpawner;
import entity.PlayerShip;
import entity.UUIDActor;
import entity.UUIDProjectile;
import formations.EnemyFormationGenerator;
import formations.EnemyFormationGeneratorImpl;
import gui.ControlsMenuController;
import model.WaveInfo.Difficulty;
import resourcemanager.ResourceManagerAlpha;

public class GenericStage implements Stage{

	private EntitySpawner spawner;
    private boolean running;
    private boolean alive;
    private WaveGenerator waves = new WaveGeneratorImpl();
    private Wave currentWave;
    private VirtualMapPrototype<UUIDActor, UUIDProjectile> map = new VirtualMapPrototype<>(ResourceManagerAlpha.getIstance().getSettingsAsObject().getHeight()-64, ResourceManagerAlpha.getIstance().getSettingsAsObject().getWidth());
    private EnemyFormationGenerator generator = new EnemyFormationGeneratorImpl(this.getMap().getWidth(), this.getMap().getHeigth());
    private boolean paused = false;
    private Difficulty diff = ControlsMenuController.getDiff();
    private PlayerShip player = new PlayerShip(0, 0);
    private int score = 0;
	private Object lock = new Object();

     public void run() {
         //Prepare Stage
    	 this.currentWave = this.waves.next();
         this.currentWave.setEnemies(this.generator.getEnemyFormation(this.diff.getEnemySaturationAmount()));
         this.map.setSpawner(this.spawner);
         for(UUIDActor a : this.currentWave.getCurrentFormation()) {
             this.add(this.getMap(), a);
         }
         this.add(this.getMap(), this.player);
         //Build Initial Wave
         //Run
         this.running = true;
         this.alive = true;
         this.start();
    }
     
     private void clearWallBang() {
    	 Set<UUIDProjectile> set = Set.copyOf(map.getProjectiles().entrySet().stream()
    	 .filter(x -> !x.getKey().isAlive())
    	 .map(x -> x.getKey())
    	 .collect(Collectors.toSet()));
    	 set.forEach(x->map.removeProjectile(x));
     }
     
     public void start() {
         while (running && alive) {
             //get wave status
        	 while(this.paused) {
        		 try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					System.out.println("Failure in pausing Stage Thread");
				}
        	 }	
		    	 if(!this.player.isAlive()) {
		    		 this.stop();
		    		 break;
		    	 }
		    	 clearWallBang();
		    	 //this.spawner.getDespawnedProjectiles();
		    	 for(UUIDActor a : this.currentWave.getCurrentFormation()) {
		    		 if(!a.isAlive()) {
		    			 this.getMap().removeActor(a);
		    			 this.score += this.currentWave.getCurrentInfo().elaborateScore(this.waves.getParam().getLevelDifficulty().getEnemyInitialLEVEL());
		    		 }
		    	 }
		    	 if(this.currentWave.formationIsOver()) {
		    		 if(this.currentWave.isEnded()) {
		    			 if(this.waves.hasNext()) {
		    				 System.out.println(this.waves.getCurrent());
			    			 if(this.waves.getCurrent()==10) {
			    				 this.currentWave.setEnemies(this.generator.getBossWave());
			    			 }
			    			 else {
			    				 this.currentWave = this.waves.next();
			    				 this.currentWave.setEnemies(this.generator.getEnemyFormation(this.diff.getEnemySaturationAmount()));
			    				 for(UUIDActor a : this.currentWave.getCurrentFormation()) {
			    					 this.add(map, a);
			    				 }
			    			 }
		    			 }
		    			 else {
		    				 this.stop();
		    			 }
		    		 }
		    		 else {
		    			 for(UUIDActor a : this.currentWave.getNextFormation()) {
		    				 this.add(map, a);
		    			 }
		    		 }
		    	 }
		    	 this.pause();
		     }
         this.stop();
     } 

    public VirtualMap<UUIDActor, UUIDProjectile> getVirtualMap() {
        return this.getMap();
    }

    @Override
    public void stop() {
    		this.running = false;
            this.alive = false;     
    }

    @Override
    public boolean isEnded() {
    		return !this.alive;      
    }

    @Override
    public void pause() {
    		this.paused = true; 	
    }

    @Override
    public void resume() {
    		this.paused = false;
    }

    @Override
    public boolean isPaused() {
    		return this.paused && this.alive;
    }
    
    public void setSpawner(EntitySpawner spawner) {
    	synchronized (lock) {	
    		this.spawner = spawner;
    	}
    }
    
    public Wave getWave() {
			return this.currentWave;
    }
    
    public VirtualMap<UUIDActor, UUIDProjectile> getMap(){
    		return this.map; 	
    }
    
    public PlayerShip getplayer() {
    		return this.player;
    }
    
    public int getScore() {
    		return this.score;
    }
    
    private void add(VirtualMap<UUIDActor, UUIDProjectile> map, UUIDActor a) {
    	
		try {
			map.addActor(a, a.getBody().getCollisionBox());
		} catch (Exception e) {
			if(a.getBody().getCollisionBox().getX()>map.getWidth() || a.getBody().getCollisionBox().getY() > map.getHeigth()) {
				System.out.println("Cannot spawn Actor outside of the map");
			}
		}   	
    }

	@Override
	public synchronized boolean isReady() {
			return this.alive;
	}

	@Override
	public void setlock(Object lock) {
		synchronized (lock) {
			this.lock =lock;
			map.setLock(lock);
		}		
	}
}
