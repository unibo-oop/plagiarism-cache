package controller.Input;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import model.world_state.World;

public class KeyListenerControllerImpl implements KeyListenerController {
    /**
     * 
     * @param world
     *      Used to notify the inputs
     *
     * @param scene
     *      Where to activate the keyListener
     *
     */
    @Override
    public final void initialize(final World world, final Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) { 
                switch (event.getCode()) {
                    case ESCAPE:
                        System.out.println("Mostra menu");
                        world.getGameState().setPause(true);
                        break;
                    case BACK_SPACE:
                        System.out.println("Cambio parola");
                        world.changeActive();
                        break;
                    default: 
                        System.out.println("Lettera " + event.getText() + " riconosciuta.");
                        world.getUnicorn().hit(event.getText().charAt(0), world);
                        break;
                }
            }
       });
    }
}
