package it.unibo.javajump.view.viewstates;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.states.pause.PauseOption;
import it.unibo.javajump.model.states.pause.PauseState;
import it.unibo.javajump.view.graphics.GameGraphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import static it.unibo.javajump.utility.Constants.BACKGROUND_DEFAULT_COLOR;
import static it.unibo.javajump.utility.Constants.GOLD_TEXT_COLOR;
import static it.unibo.javajump.utility.Constants.PAUSE_CENTER_DIV;
import static it.unibo.javajump.utility.Constants.PAUSE_CONTINUE_TEXT;
import static it.unibo.javajump.utility.Constants.PAUSE_CONTINUE_Y;
import static it.unibo.javajump.utility.Constants.PAUSE_MAIN_MENU_TEXT;
import static it.unibo.javajump.utility.Constants.PAUSE_MAIN_MENU_Y;
import static it.unibo.javajump.utility.Constants.PAUSE_QUIT_TEXT;
import static it.unibo.javajump.utility.Constants.PAUSE_QUIT_Y;
import static it.unibo.javajump.utility.Constants.PAUSE_SELECTION_TEXT;
import static it.unibo.javajump.utility.Constants.PAUSE_SELECTION_X;
import static it.unibo.javajump.utility.Constants.PAUSE_TEXT;
import static it.unibo.javajump.utility.Constants.PAUSE_WIDTH_OFF;

/**
 * The type Pause view.
 */
public final class PauseView implements GameViewState {
    private final Font font1;
    private final Font font2;

    /**
     * Instantiates a new Pause view.
     *
     * @param graphics the graphics
     */
    public PauseView(final GameGraphics graphics) {
        font1 = graphics.getGameFont1();
        font2 = graphics.getGameFont2();
    }

    @Override
    public void draw(final Graphics g, final GameModel model) {
        final PauseState pauseState = (PauseState) model.getCurrentState();
        final PauseOption selection = pauseState.getSelection();

        int selectionYcor = PAUSE_CONTINUE_Y;
        // CHECKSTYLE: MissingSwitchDefault OFF
        // the switch is exhaustive without a default case
        switch (selection) {
            case CONTINUE -> selectionYcor = PAUSE_CONTINUE_Y;
            case MAIN_MENU -> selectionYcor = PAUSE_MAIN_MENU_Y;
            case QUIT -> selectionYcor = PAUSE_QUIT_Y;
        }
        // CHECKSTYLE: MissingSwitchDefault ON

        g.setColor(Color.decode(BACKGROUND_DEFAULT_COLOR));
        g.fillRect(0, 0, model.getScreenWidth(), model.getScreenHeight());

        g.setColor(Color.decode(GOLD_TEXT_COLOR));
        g.setFont(font1);
        g.drawString(PAUSE_TEXT, model.getScreenWidth() / PAUSE_CENTER_DIV - PAUSE_WIDTH_OFF,
                model.getScreenHeight() / PAUSE_CENTER_DIV);
        g.setColor(Color.WHITE);
        g.setFont(font2);
        g.drawString(PAUSE_CONTINUE_TEXT, model.getScreenWidth() / PAUSE_CENTER_DIV - PAUSE_WIDTH_OFF,
                model.getScreenHeight() / PAUSE_CENTER_DIV + PAUSE_CONTINUE_Y);
        g.drawString(PAUSE_MAIN_MENU_TEXT, model.getScreenWidth() / PAUSE_CENTER_DIV - PAUSE_WIDTH_OFF,
                model.getScreenHeight() / PAUSE_CENTER_DIV + PAUSE_MAIN_MENU_Y);
        g.drawString(PAUSE_QUIT_TEXT, model.getScreenWidth() / PAUSE_CENTER_DIV - PAUSE_WIDTH_OFF,
                model.getScreenHeight() / PAUSE_CENTER_DIV + PAUSE_QUIT_Y);
        g.drawString(PAUSE_SELECTION_TEXT,
                model.getScreenWidth() / PAUSE_CENTER_DIV - PAUSE_WIDTH_OFF - PAUSE_SELECTION_X,
                model.getScreenHeight() / PAUSE_CENTER_DIV + selectionYcor);
    }

    @Override
    public void startFade() {

    }

    @Override
    public void update() {

    }

    @Override
    public void stopFade() {

    }
}
