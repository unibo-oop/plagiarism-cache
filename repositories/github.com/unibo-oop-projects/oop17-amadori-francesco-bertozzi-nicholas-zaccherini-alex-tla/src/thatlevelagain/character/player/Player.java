package thatlevelagain.character.player;

import java.awt.event.KeyEvent;

import thatlevelagain.character.CharacterInterface;
import thatlevelagain.character.player.collision.ButtonCollision;
import thatlevelagain.character.player.collision.DoorCollision;
import thatlevelagain.character.player.collision.SpineCollision;
import thatlevelagain.character.player.collision.WallCollision;
import thatlevelagain.sound.SoundManager;
import thatlevelagain.sound.SoundPath;
import thatlevelagain.view.map.MapImpl;
import thatlevelagain.view.panel.GamePanel;
import thatlevelagain.view.sprite.Cat;

/**
 * This is a main class with which we manage all things related to player.
 * For example: collision to object, physics and event to start sound.
 */
public class Player extends Cat implements CharacterInterface {
   /**
   * Constant indicating the increase of X.
   */
   public static final int INCREMENT_X = 2;
   /**
    * Constant indicating the decrement of X.
    */
   public static final int DECREMENT_X = -2;
   /**
    * Constant indicating the increase of Y.
    */
   public static final int INCREMENT_Y = 2;
   /**
    * Constant indicating the decrement of Y.
    */
   public static final int DECREMENT_Y = -2;
   /**
    * Constant 0.
    */
   public static final int STOP = 0;
   /**
    * Max high reach jumping related to resolution of monitor.
    */
   private static final int MAXJUMPHIGH = GamePanel.BLOCK_HEIGHT * 2;

   private final int initialY;
   private final int initialX;
   private int counterUpdate;
   private int counterToReach;
   private boolean jumpProgress;
   private final WallCollision wallCollision;
   private final ButtonCollision buttonCollision;
   private final DoorCollision doorCollison;
   /**
    * Indicate the toward of the player.
    * 1 : if it points to right.
    * -1 : if it points to left.
    */
   private int towardPlayer;

   /**
    * 
    * @param x
    *         X position.
    * @param y
    *         Y position.
    * @param width
    *         Shape's width.
    * @param height
    *         Shape's height.
    * @param map
    *         Map to obtain object.
    */
   public Player(final int x, final int y, final int width, final int height, final MapImpl map) {
       super(x, y, width, height, map);
       this.counterUpdate = 0;
       this.counterToReach = 2;
       this.initialX = this.getX();
       this.initialY = this.getY();
       this.jumpProgress = false;
       this.wallCollision = new WallCollision();
       this.buttonCollision = new ButtonCollision(this.getMap().getButton());
       this.doorCollison = new DoorCollision();
       SoundManager.init();
       this.towardPlayer = 1;
   }

   /**
    * 
    * @param k
    *           Value of key-code pressed.
    */
   public void keyPlayerMovementPressed(final int k)  {
       if (k == KeyEvent.VK_D) {
           this.setAumentoX(INCREMENT_X);
           this.towardPlayer = 1;
       }
       if (k == KeyEvent.VK_A) {
           this.setAumentoX(DECREMENT_X);
           this.towardPlayer = -1;
       }
       if (k == KeyEvent.VK_SPACE && this.getAumentoY() == STOP) {
           SoundManager.getListLoader().get(SoundPath.JUMPPATH.getPosition()).play();
           this.jumpProgress = true;
           this.counterToReach = this.counterUpdate + Player.MAXJUMPHIGH;
           this.buttonCollision.setButtonColliding(false);
       }
   }

   /**
    * 
    * @param k
    *         Value of key-code released.
    */
   public void keyPlayerMovementReleased(final int k) {
       if (k == KeyEvent.VK_D) {
           this.setAumentoX(STOP);
       }
       if (k == KeyEvent.VK_A) {
           this.setAumentoX(STOP);
       }
   }

   /**
    * 
    */
   public void update() {
       this.counterUpdate++;
       this.updateJump();
       this.updateCollision();
       this.setX(this.getX() + this.getAumentoX());
       this.setY(this.getY() + this.getAumentoY());
   }

   /**
    * 
    */
   private void updateCollision() {
       if (SpineCollision.intersection(this, this.getMap().getSpineAlte(), this.getMap().getSpine())) {
           this.restartLevel();
       }
       this.wallCollision.intersection(this, this.getMap().getMattoni());
       UpdateManager.manage(this, this.buttonCollision, this.doorCollison);
   }

   /**
    * 
    */
   private void updateJump() {
           this.jump();
   }

   /**
    * 
    */
   public void jump() {
       if (this.counterUpdate < this.counterToReach && this.isJumpProgress() && !this.wallCollision.isColidingUpperWall()) {
           this.setAumentoY(Player.DECREMENT_Y);
       } else {
           this.setJumpProgress(false);
           this.wallCollision.setCollidingUpperWall(false);
           this.setAumentoY(Player.INCREMENT_Y);
       }
   }


   /**
    * @return return the boolean value if the jump is in progress.
    * 
    */
   public boolean isJumpProgress() {
       return this.jumpProgress;
   }

   /**
    * @param jumpingValue
    *           set the value of boolean variable isJumping.
    */
   public void setJumpProgress(final boolean jumpingValue) {
       this.jumpProgress = jumpingValue;
   }

   /**
    * 
    */
   public void restartLevel() {
       SoundManager.getListLoader().get(SoundPath.CATSCREAMPATH.getPosition()).play();
       this.setY(this.initialY);
       this.setX(this.initialX);
       this.counterUpdate = this.counterToReach;
   }

   /**
    * 
    */
   public void openDoor() {
       this.getMap().getDoor().setOpen(true);
       SoundManager.getListLoader().get(SoundPath.DOORSQUEAKPATH.getPosition()).play();
   }

   /**
    * @return the toward of the player
    *           1 : if point right
    *           -1 : if point left
    */
   public int getTowardPlayer() {
       return this.towardPlayer;
   }

}
