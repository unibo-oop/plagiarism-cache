package view.mainmenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.jfoenix.controls.JFXRippler;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;

/**
 * 
 * A class that will integrate SVGPath with some other features.
 * Of course extends SVGPath
 *
 */
public class LogoContainer extends StackPane {

    private final SVGPath logoSVG;

    /**
     * 
     * Class constructor.
     * Attach a rippler to the SVG.
     * 
     */
    public LogoContainer() {
        final JFXRippler rippler;
        logoSVG = new SVGPath();
        rippler = new JFXRippler(logoSVG);
        rippler.setRipplerFill(Paint.valueOf("#696969"));
        logoSVG.getStyleClass().add("logo-image");
        getChildren().addAll(rippler);
    }

    /**
     * 
     * 
     * Equals to setContnet(String) but there's an InputStream as input parameter.
     * 
     * @param is
     *          InputStream containing line(s) to be read.
     * 
     */
    public void setContent(final InputStream is) {
        BufferedReader buffReader;
        final InputStreamReader isReader = new InputStreamReader(is);
        try {
            buffReader = new BufferedReader(isReader);
            logoSVG.setContent(buffReader.readLine());
        } catch (IOException e) {
            System.err.println("Problem occurred when loading logo image! Please restart.");
        } finally {
            try {
                isReader.close();
            } catch (IOException IOEx) {
                System.err.println("Problem occurred when loading logo image! Please restart.");
            }
        }
    }
}
