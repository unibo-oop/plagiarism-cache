package thatlevelagain.view.map.level;

import java.io.IOException;

import thatlevelagain.view.load_image.ImageManager;
import thatlevelagain.view.load_image.ImagePath;
import thatlevelagain.view.map.MapImpl;
import thatlevelagain.view.state.GameStateManagerImpl;
import thatlevelagain.view.state.various_state.EndState;

/**
* Level 12 Map.
*
*/
public class Map12 extends MapImpl implements Runnable {

   private static final int ACTUAL_LEVEL = 12;
   private static final int NEXT_STATE = GameStateManagerImpl.END;
   private final Thread th;
   private boolean go;

   /**
    * constructor.
    * @param manager
    *         actual manager.
    */
   public Map12(final GameStateManagerImpl manager) {
       super(ImageManager.getListLoader().get(ImagePath.BASE12.getPosition()), manager);
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
       this.setTrophy10(true);
       this.setTrophy11(true);
       this.setTrophy12(false);
   }
   /**
    * set the next state.
    */
   @Override
   public void nextLevel() {
       th.start();
       this.getManager().getStates().add(new EndState(this.getManager()));
       this.go = true;
//       for (final Scossa s : this.getScossa()) {
//           s.setAttivo(false);
//       }
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
