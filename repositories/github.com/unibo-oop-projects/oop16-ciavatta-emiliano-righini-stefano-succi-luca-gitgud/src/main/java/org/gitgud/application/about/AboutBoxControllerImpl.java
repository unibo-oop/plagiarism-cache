package org.gitgud.application.about;

import java.awt.Desktop;
import java.net.URI;

import org.gitgud.application.ApplicationController;
import org.gitgud.exceptions.GitGudUnckeckedException;
import org.gitgud.utils.Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

/**
 * The implementation of the about controller.
 */
public class AboutBoxControllerImpl implements AboutBoxController {

    private final ApplicationController parent;
    private final AboutBoxView view; // NOPMD
    private Pane pane;

    /**
     * @param parent
     *            the parent controller
     */
    public AboutBoxControllerImpl(final ApplicationController parent) {
        this.parent = parent;

        final FXMLLoader loader = new FXMLLoader();
        loader.setResources(Utils.getLabelsBundle());
        try {
            pane = loader.load(getClass().getResourceAsStream("AboutBox.fxml"));
        } catch (final Exception e) {
            throw new GitGudUnckeckedException("Loading failed: AboutBox.fxml");
        }
        view = loader.getController();
        view.attachController(this);

        view.showLink("GitGud Repository :", "https://bitbucket.org/ciavatta-righini-succi/oop16-git-gud");
    }

    @Override
    public void closeBox() {
        parent.closeAboutBox();
    }

    @Override
    public Pane getPane() {
        return pane;
    }

    @Override
    public void openWebLink(final String link) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI(link));
            } catch (final Exception e1) {
                throw new GitGudUnckeckedException("Can't open URL from AboutBox");
            }
        }
    }
}
