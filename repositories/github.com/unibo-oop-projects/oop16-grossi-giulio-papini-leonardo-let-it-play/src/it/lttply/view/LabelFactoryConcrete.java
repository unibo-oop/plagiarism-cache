package it.lttply.view;

import eu.hansolo.enzo.fonts.Fonts;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

/**
 * Class of Label factory.
 */
public class LabelFactoryConcrete implements LabelFactory {

    private static final int FONT_TEXT = 20;
    private static final int FONT_TITLEMOVIE = 30;
    private static final int FONT_TITLE = 25;

    @Override
    public Label createLabelText(final String text) {
        final Label lbl = new Label();
        lbl.setWrapText(true);
        lbl.setTextFill(Color.WHITE);
        lbl.setText(text);
        lbl.setFont(Fonts.dinFun(FONT_TEXT));
        return lbl;
    }

    @Override
    public Label createLabelTitleMovie(final String text) {
        final Label lbl = new Label();
        lbl.setWrapText(true);
        lbl.setTextFill(Color.WHITE);
        lbl.setText(text);
        lbl.setFont(Fonts.latoBold(FONT_TITLEMOVIE));
        return lbl;

    }

    @Override
    public Label createLabelTitle(final String text) {
        final Label lbl = new Label();
        lbl.setWrapText(true);
        lbl.setTextFill(Color.YELLOW);
        lbl.setText(text);
        lbl.setFont(Fonts.digital(FONT_TITLE));
        return lbl;
    }

}
