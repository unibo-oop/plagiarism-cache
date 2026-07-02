package view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import uicontrollers.MapEditorController;

public class MapEditorScene extends Scene {
    public MapEditorScene(final Parent root, final MapEditorController mec) {
        super(root);
        mec.initData(mec);
    }
}
