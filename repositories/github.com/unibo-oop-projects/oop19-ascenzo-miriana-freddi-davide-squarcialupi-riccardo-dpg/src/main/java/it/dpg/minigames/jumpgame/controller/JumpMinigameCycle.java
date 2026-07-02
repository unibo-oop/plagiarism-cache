package it.dpg.minigames.jumpgame.controller;

import it.dpg.minigames.base.controller.MinigameCycle;
import it.dpg.minigames.jumpgame.controller.input.Input;
import it.dpg.minigames.jumpgame.controller.input.InputObserver;
import it.dpg.minigames.jumpgame.model.World;
import it.dpg.minigames.jumpgame.model.WorldImpl;
import it.dpg.minigames.jumpgame.view.JumpMinigameView;
import org.apache.commons.lang3.tuple.Pair;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * This class serves as InputObserver and MinigameCycle for JumpMinigame
 * @author Davide Picchiotti
 * @see it.dpg.minigames.jumpgame.JumpMinigame
 * @see MinigameCycle
 * @see InputObserver
 * */

public class JumpMinigameCycle implements MinigameCycle, InputObserver {

    private static final double PERIOD = 20;

    private JumpMinigameView view;
    private World world;
    private BlockingQueue<Input> inputBuffer;

    public JumpMinigameCycle(final JumpMinigameView view) {
        this.view = view;
        this.world = new WorldImpl();
        this.inputBuffer = new ArrayBlockingQueue<>(10);
    }

    @Override
    public int startCycle() {
        setup();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        long currentTime;
        long lastTime = System.currentTimeMillis();
        long total = 0;

        while(!world.isGameOver()) {
            currentTime = System.currentTimeMillis();

            total += currentTime - lastTime;
            if(total > PERIOD) {
                update();
                total = 0;
            }

            lastTime = currentTime;
        }

        view.closeView();
        return world.getScore();
    }

    @Override
    public void notifyInput(final Input input) {
        inputBuffer.add(input);
    }

    private void setup() {
        view.setInputObserver(this);

        view.setGameSize(world.getWidth(), world.getHeight());

        view.createPlayer(world.getPlayerPosition().getLeft(), world.getPlayerPosition().getRight(), world.getPlayerSize());
    }

    private void render() {
        Pair<Integer, Integer> positions = world.getPlayerPosition();
        view.updatePlayer(positions.getLeft(), positions.getRight());
        view.updateScore(world.getScore());
        world.getPlatforms().forEach(
                plat -> view.updatePlatform(
                        plat.getPosition().getLeft(),
                        plat.getPosition().getRight(),
                        plat.getWidth(),
                        plat.getHeight(),
                        plat.getId(),
                        plat.doesExist()
                )
        );
    }

    private void processInput() {
        Input i = inputBuffer.poll();
        if(i != null) {
            i.execute(world);
        }
    }

    private void update() {
        processInput();
        world.update();
        render();
    }
}
