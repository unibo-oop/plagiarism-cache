package controller.playercontroller;

import model.character.Character;
import java.util.Optional;
import controller.gamecontroller.Controller;
import controller.gameoptions.Difficulty;
import controller.resources.Resources;
import model.ai.*;
import model.player.PlayerBuilder;
import utilities.*;

/**
 * Implementation of a Cpu Player Controller.
 */
public class CpuController extends AbstractController implements PlayerController {

    private final Ability cpuAbility;
    private final Controller controller;

    /**
     * Constructor.
     * @param difficulty 
     *              the game's difficulty.
     * @param controller 
     *              the main Controller of the game.
     */
    public CpuController(final Difficulty difficulty, final Controller controller) {
        super(new PlayerBuilder().setAttempts(difficulty.getAttempts()).setCharacters(Resources.getCharacters()).build());
        Utilities.requireNonNull(controller);
        this.cpuAbility = difficulty.getCpuAbility();
        this.controller = controller;
        getPlayer().select(Utilities.getRandom(getPlayer().getAvailables()).getName());
        controller.selected(this, getPlayer().getSelected().getName());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void play() {
        new Ai(cpuAbility).apply(getPlayer()).execute(controller);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void endGame(final Status status, final Optional<Character> opponentCharacter) {
        controller.endGame(this);
    }

}
