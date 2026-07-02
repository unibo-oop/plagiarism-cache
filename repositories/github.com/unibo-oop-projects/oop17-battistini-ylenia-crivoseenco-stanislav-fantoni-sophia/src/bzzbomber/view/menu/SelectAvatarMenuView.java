package bzzbomber.view.menu;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bzzbomber.controller.Controller;
import bzzbomber.game.Game;
import bzzbomber.view.CollectionAvatar;
import bzzbomber.view.GenericView;

/**
 * This class create a @JFrame with the component to select an @AvatarCard. The
 * instance of this class can be use to replace the generic frame of menu.
 * 
 */

public class SelectAvatarMenuView implements GenericView {

    private static final int HEIGHTIMAGEBOTTOM = (int) (Game.WINDOW_HEIGHT * 0.2);
    private static final int WIDTHIMAGEBOTTOM = (int) (Game.WINDOW_HEIGHT * 0.35);

    private final CollectionAvatar avatar;
    private final Controller controller;
    private final JFrame frame;
    private final JButton buttonAvatar;
    private final JLabel labelAvatar;

    /**
     * Constructor for @SelectAvatarMenu .
     * 
     * @param controller
     *            The controller of game.
     */
    public SelectAvatarMenuView(final Controller controller) {

        this.controller = controller;
        this.avatar = new CollectionAvatar();
        final GUIFactory factory = new GUIFactoryImpl();
        this.frame = factory.createBasicFrame();

        final JPanel panelLeftRow = new JPanel(new BorderLayout());
        final JButton jbLeft = factory.createImageButton("/avatar/frecciasx.png", WIDTHIMAGEBOTTOM, HEIGHTIMAGEBOTTOM);
        jbLeft.setBackground(GUIFactoryImpl.COLOR_BUTTON);
        panelLeftRow.add(jbLeft, BorderLayout.CENTER);

        final JPanel panelRightRow = new JPanel(new BorderLayout());
        final JButton jbRight = factory.createImageButton("/avatar/frecciadx.png", WIDTHIMAGEBOTTOM, HEIGHTIMAGEBOTTOM);
        jbRight.setBackground(GUIFactoryImpl.COLOR_BUTTON);
        panelRightRow.add(jbRight, BorderLayout.CENTER);

        final JPanel panelTitle = new JPanel();
        final JLabel labelTitle = new JLabel("SELECT AVATAR");
        panelTitle.add(labelTitle, BorderLayout.CENTER);
        labelTitle.setFont(GUIFactoryImpl.BUTTON_FONT);
        labelTitle.setForeground(GUIFactoryImpl.COLOR_FOREGROUND);
        panelTitle.setBackground(GUIFactoryImpl.COLOR_BUTTON);

        final JPanel panelAvatar = new JPanel();
        panelAvatar.setLayout(new BorderLayout());
        this.buttonAvatar = new JButton();
        this.buttonAvatar.setBackground(GUIFactoryImpl.COLOR_BUTTON);
        panelAvatar.add(this.buttonAvatar, BorderLayout.CENTER);
        panelAvatar.setBackground(GUIFactoryImpl.COLOR_BUTTON);

        final JPanel panelLabel = new JPanel();
        panelLabel.setLayout(new BorderLayout());
        this.labelAvatar = new JLabel();
        panelLabel.add(labelAvatar);
        panelLabel.setBackground(GUIFactoryImpl.COLOR_BUTTON);

        this.frame.add(panelLeftRow, BorderLayout.WEST);
        this.frame.add(panelTitle, BorderLayout.NORTH);
        this.frame.add(panelAvatar, BorderLayout.CENTER);
        this.frame.add(panelLabel, BorderLayout.SOUTH);
        this.frame.add(panelRightRow, BorderLayout.EAST);

        this.updateGraphic();

        jbRight.addActionListener(e -> {
            this.avatar.slideRight();
            System.out.println(this.avatar.getIndex());
            this.updateGraphic();
        });

        jbLeft.addActionListener(e -> {
            this.avatar.slideLeft();
            System.out.println(this.avatar.getIndex());
            this.updateGraphic();
        });

        this.buttonAvatar.addActionListener(e -> {
            this.controller.getViewManager().setAvatarIcon(this.avatar.getActualIcon());
            this.frame.setVisible(false);
            this.controller.startPlayingGame();
        });

    }

    private void updateGraphic() {
        this.buttonAvatar.setIcon(this.avatar.getActualIcon());
        this.buttonAvatar.setBorderPainted(false);
        this.labelAvatar.setText(this.avatar.getActualText());
        this.labelAvatar.setFont(GUIFactoryImpl.BUTTON_FONT);
        this.labelAvatar.setForeground(GUIFactoryImpl.COLOR_FOREGROUND);
        this.labelAvatar.setHorizontalAlignment(JLabel.CENTER);
        this.labelAvatar.setVerticalAlignment(JLabel.NORTH);

    }

    @Override
    public final void show() {
        this.frame.setVisible(true);
        System.out.println("Show SelectAvatarMenu...");
    }
}
