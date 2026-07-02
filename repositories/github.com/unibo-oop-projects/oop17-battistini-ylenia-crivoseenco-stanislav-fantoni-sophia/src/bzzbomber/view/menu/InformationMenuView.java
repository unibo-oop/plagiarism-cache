package bzzbomber.view.menu;

import java.awt.TextArea;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JScrollBar;

import bzzbomber.game.Game;
import bzzbomber.view.GenericView;

/***
 * read the game's rules from file.
 */
public class InformationMenuView implements GenericView {
    private static final double PROPORTION = 0.5;
    private final JFrame frame;
    private final TextArea informationArea;

    /***
     * constructor.
     */
    public InformationMenuView() {
        final GUIFactory menu;
        menu = new GUIFactoryImpl();
        this.frame = menu.createBasicFrame();
        this.frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.informationArea = new TextArea(" ");
        this.informationArea.setEditable(false);

        this.frame.setSize((int) (Game.WINDOW_WIDTH * InformationMenuView.PROPORTION),
                (int) (Game.WINDOW_HEIGHT * InformationMenuView.PROPORTION));
        this.frame.setLocationRelativeTo(null);
        this.frame.add(new JScrollBar());
        this.frame.add(informationArea);
        readRules();
    }

    private void readRules() {

        try (InputStream in = InformationMenuView.class.getResourceAsStream("/GamesRules.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String s = reader.readLine();

            while (s != null) {
                informationArea.setText(informationArea.getText() + "\n" + s);
                s = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("IOException readRules");
            e.printStackTrace();
        }
    }

    @Override
    public final void show() {
        this.frame.setVisible(true);
    }

}
