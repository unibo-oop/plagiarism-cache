package view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.display.ScreenUtilities;
import view.image.Loader;
import view.image.ViewComponentUtilities;

@ExtendWith(ApplicationExtension.class)
public class TestResize {

    //  4K resolution width and height in pixels.
    private static final double WIDTH_4K   = 3840;
    private static final double HEIGHT_4K  = 2160;

    // QHD resolution width and height in pixels.
    private static final double WIDTH_QHD  = 2560;
    private static final double HEIGHT_QHD = 1440;

    // FHD resolution width and height in pixels.
    private static final double WIDTH_FHD  = 1920;
    private static final double HEIGHT_FHD = 1080;

    // HDP resolution width and height in pixels.
    private static final double WIDTH_HDP  = 1600;
    private static final double HEIGHT_HDP =  900;

    //  HD resolution width and height in pixels.
    private static final double WIDTH_HD   = 1280;
    private static final double HEIGHT_HD  =  720;

    private static final int SLEEP_TIME = 500;

    private static final double POSITION_X = 50;
    private static final double POSITION_Y = 50;

    private Stage stageRef;
    private final Pane panel = new Pane();
    private ImageView image = new ImageView();

    @Start
    public final void start(final Stage stage) {
        stageRef = stage;
        panel.setStyle("-fx-background-color: #000000");
        stage.setScene(new Scene(panel, ScreenUtilities.getCurrentWidth(), ScreenUtilities.getCurrentHeight()));
        image = Loader.loadImageView(Loader.ImageID.DEFAULT);
        panel.getChildren().add(image);
        stage.show();
    }

    @Test
    public void testResize() {
        ScreenUtilities.setResolution(WIDTH_4K, HEIGHT_4K);
        resizeScreen();
        ViewComponentUtilities.resizeAndReposition(image, POSITION_X, POSITION_Y);
        assert image.getScaleX() == ScreenUtilities.getCurrentScaleFactor() && image.getScaleY() == ScreenUtilities.getCurrentScaleFactor();
        assert image.getLayoutX() == correctX();
        assert image.getLayoutY() == correctY();
        sleep();

        ScreenUtilities.setResolution(WIDTH_QHD, HEIGHT_QHD);
        resizeScreen();
        ViewComponentUtilities.resizeAndReposition(image, POSITION_X, POSITION_Y);
        assert image.getScaleX() == ScreenUtilities.getCurrentScaleFactor() && image.getScaleY() == ScreenUtilities.getCurrentScaleFactor();
        assert image.getLayoutX() == correctX();
        assert image.getLayoutY() == correctY();
        sleep();

        ScreenUtilities.setResolution(WIDTH_FHD, HEIGHT_FHD);
        resizeScreen();
        ViewComponentUtilities.resizeAndReposition(image, POSITION_X, POSITION_Y);
        assert image.getScaleX() == ScreenUtilities.getCurrentScaleFactor() && image.getScaleY() == ScreenUtilities.getCurrentScaleFactor();
        assert image.getLayoutX() == correctX();
        assert image.getLayoutY() == correctY();
        sleep();

        ScreenUtilities.setResolution(WIDTH_HDP, HEIGHT_HDP);
        resizeScreen();
        ViewComponentUtilities.resizeAndReposition(image, POSITION_X, POSITION_Y);
        assert image.getScaleX() == ScreenUtilities.getCurrentScaleFactor() && image.getScaleY() == ScreenUtilities.getCurrentScaleFactor();
        assert image.getLayoutX() == correctX();
        assert image.getLayoutY() == correctY();
        sleep();

        ScreenUtilities.setResolution(WIDTH_HD, HEIGHT_HD);
        resizeScreen();
        ViewComponentUtilities.resizeAndReposition(image, POSITION_X, POSITION_Y);
        assert image.getScaleX() == ScreenUtilities.getCurrentScaleFactor() && image.getScaleY() == ScreenUtilities.getCurrentScaleFactor();
        assert image.getLayoutX() == correctX();
        assert image.getLayoutY() == correctY();
        sleep();

    }

    public final void sleep() {
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public final void resizeScreen() {
        stageRef.setWidth(ScreenUtilities.getCurrentWidth());
        stageRef.setHeight(ScreenUtilities.getCurrentHeight());
        panel.resize(ScreenUtilities.getCurrentWidth(), ScreenUtilities.getCurrentHeight());
    }

    public final double correctX() {
        return ScreenUtilities.getCurrentWidth() / 2 - image.getImage().getWidth() / 2;
    }

    public final double correctY() {
        return ScreenUtilities.getCurrentHeight() / 2 - image.getImage().getHeight() / 2;
    }
}
