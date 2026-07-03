package view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.ViewObserver;
import game.GameObject;
import view.MenuPanel.MenuType;

/**
 * The implementation of the view. We chose to use swing.
 */
public final class ViewImpl extends JFrame implements View {
    /**
     * 
     */
    private static final long serialVersionUID = -3358151766210677493L;

    /**
     * The width of the main window. 
     */
    public static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width / 6 * 5;

    /**
     * The height of the main window.
     */
    public static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height / 6 * 5;

    private ViewObserver observer;
    private final JPanel mainPanel;
    private final CardLayout cards = new CardLayout();
    private final List<String> currentCards;

    /**
     * Creates the JFrame which will host all the windows of our application.
     */
    public ViewImpl() {
        super();
        this.setTitle("SpaceWar 2.0");
        this.currentCards = new ArrayList<>();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.mainPanel = new JPanel(cards);
        this.mainPanel.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.add(mainPanel);
        this.switchWindow(new MenuPanel(this, MenuType.Start), MenuPanel.TITLE);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    @Override
    public void start() {
        this.setVisible(true);
    }

    @Override
    public void draw(final List<GameObject> gameEntities, final int score, final int level) {
        final GameWindow window =  (GameWindow) Arrays.stream(this.mainPanel.getComponents())
                                                         .filter(comp -> comp instanceof GameWindow)
                                                         .findFirst()
                                                         .get();
        window.render(gameEntities, score, level);
    }

    @Override
    public void switchWindow(final JPanel window, final String title) {
        if (!this.currentCards.contains(title)) {
            this.currentCards.add(title);
            this.cards.addLayoutComponent(window, title);
            this.mainPanel.add(window, title);
        }
        this.cards.show(this.mainPanel, title);
    }

    @Override
    public void resetToMenu() {
        Arrays.stream(this.mainPanel.getComponents()).skip(1).forEach(p -> this.mainPanel.remove(p));
        this.currentCards.removeIf(s -> !s.equals(MenuPanel.TITLE));
    }

    @Override
    public ViewObserver getObserver() {
       return this.observer;
    }

    @Override
    public void attach(final ViewObserver observer) {
        this.observer = observer;
    }
}
