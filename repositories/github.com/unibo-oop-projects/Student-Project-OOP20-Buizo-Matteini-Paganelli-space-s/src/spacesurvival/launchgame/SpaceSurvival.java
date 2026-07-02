package spacesurvival.launchgame;

import spacesurvival.controller.gui.ControllerLoading;
import spacesurvival.view.loading.factorymethod.GUILoadingStandard;
import spacesurvival.model.gui.Visibility;
import spacesurvival.utilities.Delay;
import spacesurvival.utilities.ThreadUtils;
import spacesurvival.model.EngineLoop;
import spacesurvival.model.gui.StaticFactoryEngineGUI;

public final class SpaceSurvival {

    private SpaceSurvival() {
    }

    public static void main(final String[] args) {

        final ControllerLoading ctrlLoading = new ControllerLoading(StaticFactoryEngineGUI.createEngineLoading(),
                new GUILoadingStandard().create());
        ctrlLoading.initLoading();
        ctrlLoading.start();


        final EngineLoop engineLoop = new EngineLoop();
        while (!ctrlLoading.isLoad()) {
            ThreadUtils.sleep(Delay.LOADING_UPDATE);
        }

        engineLoop.initGame();
        ctrlLoading.turn(Visibility.HIDDEN);

        engineLoop.start();
    }
}

