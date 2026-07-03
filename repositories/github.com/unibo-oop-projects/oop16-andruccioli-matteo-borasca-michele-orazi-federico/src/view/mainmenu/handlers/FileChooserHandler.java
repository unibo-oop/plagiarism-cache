package view.mainmenu.handlers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;

import com.jfoenix.controls.JFXButton;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import view.ViewImpl;

/**
 * 
 * Handler that configures and start FileChooser when a game is going to be loaded.
 * It opens in user.home directory and has a file type filter (you can select only .rsk files).
 *
 */
public class FileChooserHandler implements EventHandler<MouseEvent> {

    private static final String DEFAULT_PATH = System.getProperty("user.home") + File.separator + ".risk";

    @Override
    public void handle(final MouseEvent e) {
        final FileChooser  fileChooser = new FileChooser();

        fileChooser.setTitle("Load a game:");
        fileChooser.setInitialDirectory(Files.notExists(Paths.get(DEFAULT_PATH), LinkOption.NOFOLLOW_LINKS) ? new File(System.getProperty("user.home")) : new File(DEFAULT_PATH));

        /* Defining files type restriction */
        final FileChooser.ExtensionFilter extFilterRSK = new ExtensionFilter("RSK files (*.rsk)", "*.rsk");
        fileChooser.getExtensionFilters().add(extFilterRSK);

        final File f = fileChooser.showOpenDialog(((JFXButton) e.getSource()).getScene().getWindow());
        if (f != null) {
            ViewImpl.getIstance().getController().loadGameFromFile(f);
        }
    }
}
