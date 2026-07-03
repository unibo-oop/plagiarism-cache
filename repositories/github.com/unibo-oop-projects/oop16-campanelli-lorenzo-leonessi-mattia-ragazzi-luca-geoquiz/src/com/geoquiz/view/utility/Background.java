package com.geoquiz.view.utility;

import com.geoquiz.utility.ResourceLoader;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * An utility class for create background.
 */
public final class Background {

    private static final double OPACITY = 0.4;
    private static final String FILE = "/images/image.jpg";
    private static final double LOGO_SHADOW = 9;
    private static final Text LOGO = new Text("GeoQuiz");
    private static final double LOGO_FONT = 120;
    private static final double POS_LOGO_X = 415;
    private static final double POS_LOGO_Y = 150;

    private Background() {
    }

    /**
     * @return background used in every scene.
     */
    public static Rectangle createBackground() {

        final Rectangle bg = new Rectangle(ScreenAdapter.getScreenWidth(), ScreenAdapter.getScreenHeight());
        bg.setFill(Color.GREY);
        bg.setOpacity(OPACITY);
        return bg;

    }

    /**
     * @return image background used in every scene.
     */
    public static ImageView getImage() {
        final Image img = new Image(ResourceLoader.loadResourceAsStream(FILE));
        final ImageView imgView = new ImageView(img);
        imgView.setFitWidth(ScreenAdapter.getScreenWidth());
        imgView.setFitHeight(ScreenAdapter.getScreenHeight());
        return imgView;

    }

    /**
     * @param file
     *            file URL.
     * 
     * @return image background used in category buttons.
     */
    public static ImageView getCategoryImage(final String file) {
        final Image img = new Image(ResourceLoader.loadResourceAsStream(file));
        final ImageView imgView = new ImageView(img);
        imgView.setFitWidth(ScreenAdapter.getScreenWidth());
        imgView.setFitHeight(ScreenAdapter.getScreenHeight());
        return imgView;
    }

    /**
     * @return game logo used in every scene.
     */
    public static Text getLogo() {

        final DropShadow ds = new DropShadow();
        ds.setOffsetY(LOGO_SHADOW);
        ds.setColor(Color.color(1.0f, 1.0f, 1.0f));
        Text logo = new Text("GeoQuiz");
        logo = LOGO;
        logo.setFont(Font.font(LOGO_FONT));
        logo.setFill(Color.BLUE);
        logo.setEffect(ds);
        logo.setTranslateX(POS_LOGO_X);
        logo.setTranslateY(POS_LOGO_Y);
        return logo;
    }

}
