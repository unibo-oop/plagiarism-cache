package it.unibo.view;

import it.unibo.controller.ControllerStorage;
import it.unibo.model.*;
import it.unibo.model.interfaces.PuyoInterface;
import it.unibo.view.interfaces.GameViewInterface;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel implements GameViewInterface{
    public final Background background;
    public final PuyoRenderer puyoRenderer;
    public final CannonView cannonView;
    public final TargetView cannonSightView;
    public final ProgressBarView progressBarView;
    public final BulletView bulletView;
    public final BorderView borderView;
    public final PauseView pauseView;
    public final ExitView exitView;
    public final TryAgainView tryAgainView;
    public final ScoreView scoreView;
    public final EndView endView;
    private final Grid grid;

    public GameView(ModelStorage modelStorage, ControllerStorage controllerStorage) {
        this.grid = modelStorage.grid;
        this.cannonView = new CannonView(modelStorage.scale, modelStorage.cannonModel);
        this.pauseView = new PauseView(modelStorage.scale, modelStorage.pauseModel, controllerStorage.pauseController);
        this.exitView = new ExitView(modelStorage.scale, controllerStorage.exitController);
        this.tryAgainView = new TryAgainView(modelStorage.scale, controllerStorage.tryAgainController);
        this.background = new Background("background.jpg");
        this.puyoRenderer = new PuyoRenderer(modelStorage.scale);
        this.cannonSightView = new TargetView(modelStorage.scale, modelStorage.cannonModel);
        this.progressBarView = new ProgressBarView(modelStorage.progressBarModel, modelStorage.scale);
        this.bulletView = new BulletView(modelStorage.bulletModel, modelStorage.scale);
        this.borderView = new BorderView(modelStorage.scale);
        this.scoreView = new ScoreView(modelStorage.scoreModel, modelStorage.scale);
        this.endView = new EndView(modelStorage.statusModel, modelStorage.scale, modelStorage.scoreModel);

        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        background.draw(g, getWidth(), getHeight());

        for (int y = 0; y < grid.getRows(); y++) {
            for (int x = 0; x < grid.getCols(); x++) {
                PuyoInterface puyo = grid.getPuyo(x, y);
                if (puyo != null) {
                    puyoRenderer.render(g, grid, y, x);
                }
            }
        }

        borderView.draw(g);
        cannonSightView.draw(g);
        bulletView.draw(g);
        cannonView.draw(g);
        progressBarView.paintComponent(g);

        exitView.draw(g);
        tryAgainView.draw(g);
        scoreView.draw(g);
        pauseView.draw(g);
        endView.draw(g);

    }

    @Override
    public void render(Graphics g, int width, int height) {
        background.draw(g, width, height);
    }

}