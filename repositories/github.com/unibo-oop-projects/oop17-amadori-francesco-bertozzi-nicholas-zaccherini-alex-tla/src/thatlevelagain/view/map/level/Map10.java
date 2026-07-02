package thatlevelagain.view.map.level;

import java.io.IOException;

import thatlevelagain.view.load_image.ImageManager;
import thatlevelagain.view.load_image.ImagePath;
import thatlevelagain.view.map.MapImpl;
import thatlevelagain.view.state.GameStateManagerImpl;
import thatlevelagain.view.state.various_state.level.Level11;

/**
* Level 10 Map.
*
*/
public class Map10 extends MapImpl implements Runnable {

   private static final int ACTUAL_LEVEL = 10;
   private static final int NEXT_STATE = GameStateManagerImpl.LEVEL11;
   private final Thread th;
   private boolean go;

   /**
    * constructor.
    * @param manager
    *         actual manager.
    */
   public Map10(final GameStateManagerImpl manager) {
       super(ImageManager.getListLoader().get(ImagePath.BASE1.getPosition()), manager);
       this.th = new Thread(this);
       this.setLevel(ACTUAL_LEVEL);
       this.setTrophy1(true);
       this.setTrophy2(true);
       this.setTrophy3(true);
       this.setTrophy4(true);
       this.setTrophy5(true);
       this.setTrophy6(true);
       this.setTrophy7(true);
       this.setTrophy8(true);
       this.setTrophy9(true);
       this.setTrophy10(false);
       this.setTrophy11(false);
       this.setTrophy12(false);
   }
   /**
    * set the next state.
    */
   @Override
   public void nextLevel() {
       th.start();
       this.getManager().getStates().add(new Level11(this.getManager()));
       this.go = true;
   }
   @Override
   public final void run() {
       this.getManager().getStates().get(GameStateManagerImpl.PAUSE_LEVEL).setLevelIndex(ACTUAL_LEVEL + 1);
       try {
           this.getManager().setState(GameStateManagerImpl.PAUSE_LEVEL);
       } catch (IOException e1) {
            e1.printStackTrace();
       }
       try {
           Thread.sleep(GameStateManagerImpl.TIMEWAIT);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
       while (!this.go) {
           aspetta();
       }
       try {
           this.getManager().setState(NEXT_STATE);
        } catch (IOException e) {
        e.printStackTrace();
      }
   }
   private void aspetta() { }
}

