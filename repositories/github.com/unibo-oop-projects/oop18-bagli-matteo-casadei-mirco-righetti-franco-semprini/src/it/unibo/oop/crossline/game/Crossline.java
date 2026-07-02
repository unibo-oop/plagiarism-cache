package it.unibo.oop.crossline.game;

import com.badlogic.gdx.Game;

import it.unibo.oop.crossline.game.screen.InitialScreen;

public class Crossline extends Game {

    @Override
    public final void create() {
        setScreen(new InitialScreen());
    }

    @Override
    public final void render() {
        super.render();
    }

    @Override
    public final void dispose() {
        super.dispose();
    }

}
