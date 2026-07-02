package it.unibo.isaccoop.graphics;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * The GUI that shows the game help.
 */
public class HelpGUI extends AbstractGUIFrame {

    private static final long serialVersionUID = -5636279696678208920L;
    private static final Logger LOGGER = Logger.getLogger(SwingScene.class.getName());
    private static final String TITLE = "Isaccoop User Guide";
    private static final String RES_PATH = "it/unibo/isaccoop/help/";
    private static final String CANNOT_FIND_HELPFILE = "Cannot find the help file \"help.txt\"";
    private static final String NEWLINE = System.lineSeparator();
    private static final int TEXTAREA_COLS = 60;
    private static final int TEXTAREA_ROWS = 10;
    private static final String UTF8 = "UTF-8";

    /**
     * Constructor. No parameters required.
     * Creates a GUI to show the game help.
     */
    protected HelpGUI() throws IOException {
        super(TITLE);

        final JTextArea textArea = new JTextArea(TEXTAREA_ROWS, TEXTAREA_COLS);

        // if exists, load the file "help.txt" from the resources folder as a stream<String> 
        // and put it in a textarea
        final InputStream in = Objects.requireNonNull(
                ClassLoader.getSystemResourceAsStream(RES_PATH + "help.txt"));
        Stream<String> lines;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in, UTF8))) {
            lines = br.lines();

            final StringBuilder strBuilder = new StringBuilder();
            lines.forEach(s -> strBuilder.append(s + NEWLINE));
            textArea.setText(strBuilder.toString());
            br.close();
        } catch (IOException ex) {
            LOGGER.severe(ex.getMessage());
            textArea.setText(CANNOT_FIND_HELPFILE);
        }

        // add the textArea to the main BorderLayout, where to display the help
        super.addElementToMainPanel(new JScrollPane(textArea), BorderLayout.CENTER);
    }
}
