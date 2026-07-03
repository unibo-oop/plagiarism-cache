package it.unibo.roguekong.model.value.impl;

import it.unibo.roguekong.model.value.Lives;

public class LivesImpl implements Lives {
    private int lives;

    public LivesImpl(int lives) {
        setLivesByValue(lives);
    }

    public LivesImpl() {
        setLivesByValue(0);
    }

    @Override
    public int getLives() {
        return this.lives;
    }

    /**
     * set lives by a specific int value
     * @param lives int value of number of lives
     */
    @Override
    public void setLivesByValue(int lives) {
        this.lives = lives;
    }

    /**
     * method to decrement lives by one
     */
    public void decrementLives(){
        if(this.lives > 0){
            this.lives--;
        }
    }
}
