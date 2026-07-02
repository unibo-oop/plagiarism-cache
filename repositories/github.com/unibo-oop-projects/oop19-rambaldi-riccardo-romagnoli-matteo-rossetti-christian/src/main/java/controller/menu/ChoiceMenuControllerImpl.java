package controller.menu;

import java.io.IOException;

import javafx.scene.control.ComboBox;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import controller.base.BaseController;

/**
 * This is the implementation of {@link ChoiceMenuController} interface.
 */
public class ChoiceMenuControllerImpl implements ChoiceMenuController {

    private final BaseController baseController;

    /**
     * ChoiceMenuController's constructor.
     * @param baseController
     *      The instance of {@link BaseController}.
     */
    public ChoiceMenuControllerImpl(final BaseController baseController) {
        this.baseController = baseController;
    }

    @Override
    public final void startHit(final String mapChosen, final String name, final String characterChosen) {
        this.baseController.startGame(mapChosen, name, characterChosen);
    }

    @Override
    public final void goBackHit() {
        this.baseController.startProgram();
    }

    @Override
    public final void setMapBox(final ComboBox<String> mapBox) {
        final PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources;

        try {
            resources = resolver.getResources("/maps/*");
            for (final Resource resource : resources) {
                mapBox.getItems().addAll(resource.getFilename());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void setCharacterBox(final ComboBox<String> characterBox) {
        final PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources;

        try {
            resources = resolver.getResources("/model/character/*");
            for (final Resource resource : resources) {
                final String characterFileName = resource.getFilename();
                if (characterFileName != null) {
                    final String characterName = characterFileName.substring(0, characterFileName.length() - ".class".length());
                    try {
                        if (!Class.forName("model.character." + characterName).isInterface()) {
                            characterBox.getItems().add(characterName);
                        }
                    } catch (ClassNotFoundException cnfe) {
                        cnfe.printStackTrace();
                    }
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
