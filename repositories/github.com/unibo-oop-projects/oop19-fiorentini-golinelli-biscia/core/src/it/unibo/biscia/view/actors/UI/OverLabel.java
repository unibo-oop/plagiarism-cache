package it.unibo.biscia.view.actors.UI;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * A {@link label} that changes her style based on the "over" {@code skin}'s
 * style provided when the mouse enter or exit from her.
 * 
 * @see LabelStyle
 * @see Label
 * @see Skin
 *
 */
public class OverLabel extends Label {

    /**
     * @param text initial text
     * @param skin {@link skin} of the label
     */
    public OverLabel(final CharSequence text, final Skin skin) {
        super(text, skin);
        this.addListener(new ClickListener() {
            @Override
            public void enter(final InputEvent event, final float x, final float y, final int pointer,
                    final Actor fromActor) {
                setStyle(skin.get("over", LabelStyle.class));
                super.enter(event, x, y, pointer, fromActor);
            }

            @Override
            public void exit(final InputEvent event, final float x, final float y, final int pointer,
                    final Actor toActor) {
                setStyle(skin.get(LabelStyle.class));
                super.exit(event, x, y, pointer, toActor);
            }
        });
    }

}
