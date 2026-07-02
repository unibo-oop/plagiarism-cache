package view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import uicontrollers.CharacterEditorController;

public class CharacterEditorScene extends Scene {
    public CharacterEditorScene(final Parent root, final CharacterEditorController cec) {
        super(root);
        cec.initData(cec);
    }
}
