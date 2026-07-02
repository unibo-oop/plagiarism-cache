package snakerunner.graphics.panel;

import javax.swing.BoxLayout;
import javax.swing.JButton;

import snakerunner.controller.NavigationController;
import snakerunner.graphics.impl.AbstractBasePanel;

/**
 * MenuPanel define the MenuView for the application.
 */
public final class MenuPanel extends AbstractBasePanel {

    private static final long serialVersionUID = 1L;
    private static final String START = "Start";
    private static final String OPTION = "Option";
    private static final String EXIT = "Exit";
    private final transient NavigationController navigationController;
    private final JButton start;
    private final JButton option;
    private final JButton exit;

    /**
     * Constructor for MenuPanel.
     * 
     * @param navigationController NavigationController.
     */
    public MenuPanel(final NavigationController navigationController) {
        super();
        initPanel();
        this.navigationController = navigationController;
        setLayoutPanel();
        start = createButton(START);
        option = createButton(OPTION);
        exit = createButton(EXIT);
        add(start);
        add(option);
        add(exit);
        this.addActionListeners();
    }

    @Override
    public void setLayoutPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    @Override
    public void addActionListeners() {
        start.addActionListener(e -> {
            navigationController.startGame();
        });
        option.addActionListener(e -> navigationController.onOption());
        exit.addActionListener(e -> navigationController.exit());
    }
}
