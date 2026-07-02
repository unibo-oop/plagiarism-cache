package thatlevelagain.character.player.collision;

import thatlevelagain.character.player.Player;
import thatlevelagain.character.player.UpdateManager;
import thatlevelagain.view.sprite.Button;

/**
 * 
 */
public class ButtonCollision {
    private final int y;
    private final int height;
    private boolean buttonColliding;
    private int pressCounter;
    private int lvl;

    /**
     * @param button
     *          Object of class Button.
     */
    public ButtonCollision(final Button button) {
        this.pressCounter = 0;
        this.buttonColliding = false;
        this.y = button.getY() - 1;
        this.height = button.getHeight() + 1;
        button.setHeight(this.height);
        button.setY(this.y);
    }

    /**
     * 
     * @param player
     *          The object of class Player.
     * @param button
     *          Object of class Button.
     */
    public void buttonAction(final Player player, final Button button) {
        this.resetPressCounter(player);
        if (player.getRectRight().intersects(button.getRectLeft()) && player.getAumentoX() == Player.INCREMENT_X) {
            player.setAumentoX(Player.STOP);
        }
        if (player.getRectLeft().intersects(button.getRectRight()) && player.getAumentoX() == Player.DECREMENT_X) {
            player.setAumentoX(Player.STOP);
        }
        if (player.getRectBottom().intersects(button.getRectUp()) && player.getAumentoY() == Player.INCREMENT_Y) {
            if (button.getHeight() > this.height / 2) {
                this.buttonColliding = true;
                button.setHeight(button.getHeight() - 2);
                button.setY(button.getY() + 2);
                if (button.getHeight() <= this.height / 2) {
                    this.pressCounter++;
                    if (this.pressCounter == 1 && !player.getMap().getDoor().isOpen() 
                            && (player.getMap().getLevel() == UpdateManager.LVL1
                                || player.getMap().getLevel() == UpdateManager.LVL2
                                    || player.getMap().getLevel() == UpdateManager.LVL5
                                        || player.getMap().getLevel() == UpdateManager.LVL7
                                            || player.getMap().getLevel() == UpdateManager.LVL8
                                                || player.getMap().getLevel() == UpdateManager.LVL11
                                                    || player.getMap().getLevel() == UpdateManager.LVL12)) {
                        player.openDoor();
                    }
                    if (this.pressCounter == 4 && player.getMap().getLevel() == UpdateManager.LVL9) {
                        player.openDoor();
                    }
                    if (player.getMap().getLevel() == UpdateManager.LVL4) {
                        player.restartLevel();
                    }
                }
            } else if (player.getY() + player.getHeight() != button.getY()) {
                player.setY(player.getY() + (button.getY() - (player.getY() + player.getHeight())));
            }
       } else if (player.isJumpProgress() || ((player.getX() < (button.getX() - player.getWidth() + 1) || player.getX() > (button.getX() + button.getWidth() - 1)) 
                       || (player.getY() > (button.getY() + button.getHeight()) || (player.getY() +  player.getHeight()) < button.getY()))) {
           this.buttonColliding = false;
           if (!player.isJumpProgress()) {
               button.setY(this.y);
               button.setHeight(this.height);
           } else if (player.getMap().getLevel() == UpdateManager.LVL2) {
               button.setHeight(button.getHeight() + 2);
               button.setY(button.getY() - 2);
           }
               if (button.getHeight() < this.height && player.getMap().getLevel() != UpdateManager.LVL2) {
               button.setHeight(button.getHeight() + 2);
               button.setY(button.getY() - 2);
           }
       }
        if (this.isButtonColliding() && player.getAumentoY() == Player.STOP) {
            player.setAumentoY(Player.STOP);
        }
        if (button.getHeight() <= this.height / 2 && this.buttonColliding) {
            player.setY(player.getY() + 2);
            player.setAumentoY(Player.STOP);
        }
   }

    /**
     * @return the value of collision to button. 
     */
    public boolean isButtonColliding() {
        return this.buttonColliding;
    }

    /**
     * @param buttonColliding
     *          the value if the cat is colliding to button.
     */
    public void setButtonColliding(final boolean buttonColliding) {
        this.buttonColliding = buttonColliding;
    }

    /**
     * @return the number of time the button is pressed.
     */
    public int getPressCounter() {
        return this.pressCounter;
    }


    private void resetPressCounter(final Player player) {
        if (this.lvl != player.getMap().getLevel()) {
            this.pressCounter = 0;
            this.lvl = player.getMap().getLevel();
        }
    }

}
