package javagotchi.view.minigame.menu.optionframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javagotchi.view.minigame.AbstractFrameDefault;
import javagotchi.view.minigame.FactoryView;

/**
 * 
 * @author marica
 *
 */
public final class MiniTutorial extends AbstractFrameDefault {

    private static final long serialVersionUID = 3248714893644069366L;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final String TUTORIALPATH = "/minigame/Tutorial/Tutorial.html";
    private final JFXPanel pane = new JFXPanel();
    private final JButton ok = new JButton("Ok");

    private MiniTutorial() {
        super(WIDTH, HEIGHT);
        this.setTitle("MiniTutorial");
        this.setVisible(true);
        this.add(pane, BorderLayout.CENTER);
        this.add(getSouthPanel(), BorderLayout.SOUTH);
        setEvent();
    }

    private JPanel getSouthPanel() {
        final JPanel pSouth = new JPanel(new FlowLayout());
        pSouth.add(ok, BorderLayout.CENTER);
        pSouth.setBackground(Color.WHITE);
        return pSouth;
    }

    /**
     * Creates new instance of {@link MiniTutorial}.
     * 
     * @return {@link MiniTutorial}
     */
    public static MiniTutorial create() {
        return new MiniTutorial();
    }

    /**
     * Loads the HTML Document in {@link JFXPanel}.
     */
    public void load() {
        Platform.runLater(() -> {
            final WebView brower = new WebView();
            final WebEngine webEngine = brower.getEngine();
            webEngine.load(MiniTutorial.class.getResource(TUTORIALPATH).toString());
            pane.setScene(new Scene(brower));
        });

    }

    @Override
    protected void setEvent() {
        ok.addActionListener(e -> {
            this.dispose();
            FactoryView.createViewOptions().display();
        });
    }
}
