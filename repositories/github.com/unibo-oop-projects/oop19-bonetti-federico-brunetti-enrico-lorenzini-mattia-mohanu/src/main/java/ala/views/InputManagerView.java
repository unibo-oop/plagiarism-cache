package ala.views;

import java.util.BitSet;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public final class InputManagerView {

     //Attributes:
     private BitSet keyboardBitSet = new BitSet();

     // -------------------------------------------------
     // default key codes
     // -------------------------------------------------
     private KeyCode leftKey = KeyCode.LEFT;
     private KeyCode rightKey = KeyCode.RIGHT;
     private KeyCode meleeAttackKey = KeyCode.Q; //Melee Attack
     private KeyCode rangedAttackKey = KeyCode.W; //Ranged Attack
     private KeyCode finalAttackKey = KeyCode.E; //Final attack
     private KeyCode jumpKey = KeyCode.SPACE;
     private KeyCode exitLevelKey = KeyCode.ESCAPE;

     private Scene scene;

     //Constructor:
     public InputManagerView(final Scene scene) {
      this.scene = scene;
     }

     //Getters&Setters:
     public boolean isMovingRight() {
         return keyboardBitSet.get(rightKey.ordinal()) && !keyboardBitSet.get(leftKey.ordinal());
     }

     public boolean isUsingMeleeAttack() {
         return keyboardBitSet.get(meleeAttackKey.ordinal()) && !keyboardBitSet.get(rangedAttackKey.ordinal()) && !keyboardBitSet.get(finalAttackKey.ordinal());
     }

     public boolean isUsingRangedAttack() {
         return keyboardBitSet.get(rangedAttackKey.ordinal()) && !keyboardBitSet.get(meleeAttackKey.ordinal()) && !keyboardBitSet.get(finalAttackKey.ordinal());
     }

     public boolean isUsingFinalAttack() {
         return keyboardBitSet.get(finalAttackKey.ordinal()) && !keyboardBitSet.get(meleeAttackKey.ordinal()) && !keyboardBitSet.get(rangedAttackKey.ordinal());
     }

        public boolean isJumping() {
            return keyboardBitSet.get(jumpKey.ordinal());
        }

       public boolean isExitingLevel() {
           return keyboardBitSet.get(exitLevelKey.ordinal());
       }

     //Methods:
     public void addListeners() {
      scene.addEventFilter(KeyEvent.KEY_PRESSED, keyPressedEventHandler);
      scene.addEventFilter(KeyEvent.KEY_RELEASED, keyReleasedEventHandler);
     }

     public void removeListeners() {
      scene.removeEventFilter(KeyEvent.KEY_PRESSED, keyPressedEventHandler);
      scene.removeEventFilter(KeyEvent.KEY_RELEASED, keyReleasedEventHandler);
     }

     /**
      * "Key Pressed" handler for all input events: register pressed key in the bitset.
      */
     private EventHandler<KeyEvent> keyPressedEventHandler = new EventHandler<KeyEvent>() {
      @Override
      public void handle(final KeyEvent event) {
       // register key down
       keyboardBitSet.set(event.getCode().ordinal(), true);
      }
     };

     /**
      * "Key Released" handler for all input events: unregister released key in the bitset.
      */
     private EventHandler<KeyEvent> keyReleasedEventHandler = new EventHandler<KeyEvent>() {
      @Override
      public void handle(final KeyEvent event) {
       // register key up
       keyboardBitSet.set(event.getCode().ordinal(), false);
      }
     };

     public boolean isMovingLeft() {
      return keyboardBitSet.get(leftKey.ordinal()) && !keyboardBitSet.get(rightKey.ordinal()); 
     }
}

