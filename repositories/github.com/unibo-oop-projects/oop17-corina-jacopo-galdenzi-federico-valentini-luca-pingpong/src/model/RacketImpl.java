package model;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RacketImpl implements Racket {
    private int yMove;  // y == 1 upward movement ; y == -1 downward movement
    private int up;
    private int down;
    public RacketImpl() {
        this.yMove=0;
    }
    public void pressed(int dir) {
        if (dir == up) {        // up equivale al pulsante freccia in su
            this.yMove = 1;
        } else if (dir == down) {
            this.yMove = -1;
        }
    }
    public void released() {
        this.yMove = 0;
    }
}
