package vg.utils.path;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PathImageMysteryBoxTest {

    @Test
    void test() {
        assertEquals("img/mysteryBox/coins/coin-T.png", PathImageMysteryBox.COIN_TIME);
        assertEquals("img/mysteryBox/coins/coin-S.png", PathImageMysteryBox.COIN_SPEED);
        assertEquals("img/mysteryBox/coins/coin-P.png", PathImageMysteryBox.COIN_SCORE);
        assertEquals("img/mysteryBox/coins/coin-C.png", PathImageMysteryBox.COIN_KILL_ALL_MOQUETOES);
    }

}