package controller.menu;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import model.menu.Assets;
import view.menu.GenericGUI;
/**
 * Controls and links model and view of the menu
 */
public class ImageSelector {
	
    private int indexS;
    private Sprite selected;
    private GenericGUI GUI;
    private Assets assets;
    /**
     * Sets up GenericGUI adding data retrieved from Assets
     * @param GUI
     * 			GenericGUI in which to add data data
     * @param playerassets
     * 			Data from Assets
     */
    public ImageSelector(final GenericGUI GUI, final Assets playerassets) {
        this.GUI = GUI;
        this.assets = playerassets;
        this.selected = this.assets.getSprite(0);
        this.GUI.setListener(this.clickListener());
        this.GUI.setSelected(this.selected);
    }

    private ClickListener clickListener() {
        ClickListener temp = new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //return true if pressed was arrowdx else is arrowsx
                indexS = getNextIndexS(event.getListenerActor().equals(GUI.getArrowImageDx()));
                selected = assets.getSprite(indexS);
                GUI.setSelected(selected);
                GUI.setIndexSelected(indexS);
                GUI.getTable().clear();
                GUI.getTable().add(new Image(selected.getTexture()));
            }
        };
        return temp;
    }

    private int getNextIndexS(final boolean pressed) {
        if (pressed) {
            this.indexS = (this.indexS + 1) % this.assets.getFolderLenght();
        } else {
            this.indexS = (this.indexS == 0) ? this.assets.getFolderLenght() - 1 : this.indexS - 1;
        }
        return indexS;
    }
}
