package javagotchi.view.menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

import javafx.application.Platform;
import javagotchi.controller.home.HomeController;
import javagotchi.controller.home.HomeControllerImpl;
import javagotchi.controller.menu.MenuController;
import javagotchi.model.Javagotchi;
import javagotchi.model.information.Avatar;
import javagotchi.view.home.GameArea;
import javagotchi.view.home.Home;

import javafx.embed.swing.JFXPanel;
/**
 *  This class is the implementation of the savedAvatarMenu 
 *  make the user able to choose 
 *  if he wants to play, or delete an avatar already created, or creare a new avatar.
 * @author giulia
 *
 */
public class SavedAvatarMenu implements Menu {
    private static final String NO_JAVAGOTCHI_SELECTED = "You haven't choose any Javagotchi";
    private static final String JAVAGOTCHI_DEAD = "This Javagotchi is dead, \n you have to choose another one";
    private static final String OUT_OF_SPACE = "You have run out of space, \nif you want do create a new Javagotchi, \nyou must delete one";
    private static final Integer MAX_AVATAR = 3;
    private static final double PERC_WIDTH = 0.6;
    private static final double PERC_HEIGHT = 0.49;
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final JFrame frame;
    private final MenuView view;
    private final List<JRadioButton> buttons;
    private Javagotchi avatarChosen;
    private final List<JLabel> imagesLabel;
    private final JPanel avatarPanel;
    private final GridBagConstraints constraint;
    private String avatarToDelete;
    private final List<Javagotchi> listInformation;
    private int pos;
    /**this is the constructor for this class.
     * 
     * @param controller **MenuControler**
     * @param view    **MenuView**
     */
    public SavedAvatarMenu(final MenuController controller, final MenuView view) {
        this.view = view;
        this.imagesLabel = view.getImagesAvatarList();
        this.listInformation = controller.getListJavagotchi();
        System.out.println("Creating SavedAvatarMenu...");

        this.frame = new JFrame("Tamagotchi  -  Saved Avatar");
        this.frame.setSize((int) (screenSize.getWidth() * PERC_WIDTH), (int) (screenSize.getHeight() * PERC_HEIGHT));

        this.frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        final JPanel mainPanel = new JPanel();
        this.avatarPanel = new JPanel(); // cat, fox, panda
        final JButton playButton = new JButton("PLAY");
        final JButton backButton = new JButton("BACK");
        final JButton newAvatarButton = new JButton("NEW AVATAR");
        final JButton deleteButton = new JButton("FREE AVATAR");

        final ButtonGroup buttonsGroup = new ButtonGroup();
        buttons = IntStream.range(0, 3).mapToObj(i -> new JRadioButton(" ")).peek(buttonsGroup::add).peek(jb -> jb.setEnabled(false)).collect(Collectors.toList());

        mainPanel.setBorder(new TitledBorder("Saved Avatar"));
        mainPanel.setLayout(new BorderLayout());

        avatarPanel.setBorder(new TitledBorder("Avatar"));
        avatarPanel.setLayout(new GridBagLayout());

        this.constraint = new GridBagConstraints();
        constraint.gridy = 1; 
        constraint.gridx = 0; 
        constraint.insets = new Insets(3, 3, 3, 3);

        constraint.fill = GridBagConstraints.HORIZONTAL;

        buttons.forEach(e -> {
            avatarPanel.add(e, constraint);
            constraint.gridx++;
        });

        mainPanel.add(avatarPanel, BorderLayout.CENTER);

        final JPanel settingPanel = new JPanel();
        settingPanel.add(playButton);
        settingPanel.add(newAvatarButton);
        settingPanel.add(deleteButton);
        settingPanel.add(backButton);
        mainPanel.add(settingPanel, BorderLayout.SOUTH);
        
        final ActionListener ac = (src) -> {
            JRadioButton b = (JRadioButton) src.getSource();
            pos = buttons.indexOf(b);
        };
        buttons.stream().forEach(jb -> jb.addActionListener(ac));
        playButton.addActionListener(e -> {
            if (buttons.get(pos).isSelected() && listInformation.get(pos).isAlive()) {
                this.avatarChosen = this.listInformation.get(pos);
                System.out.println("Avatar scelto:" + this.avatarChosen.getInformation().getName());
                final JFXPanel fxPanel = new JFXPanel();
                this.frame.add(fxPanel);
                Platform.runLater(() -> {
                    final HomeController homecontroller = new HomeControllerImpl(avatarChosen);
                    final GameArea gameArea = new GameArea(new Home(homecontroller));
                    gameArea.showArea();
                });
                SavedAvatarMenu.this.frame.setVisible(false);
            } else if (!buttons.get(pos).isSelected()) {
                JOptionPane.showMessageDialog(frame, NO_JAVAGOTCHI_SELECTED, "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, JAVAGOTCHI_DEAD, "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> {
            SavedAvatarMenu.this.frame.setVisible(false);
            SavedAvatarMenu.this.view.getMenuManager().showMainMenu();
        });

        newAvatarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (listInformation.size() == MAX_AVATAR) {
                    JOptionPane.showMessageDialog(frame, OUT_OF_SPACE);
                } else {
                    SavedAvatarMenu.this.frame.setVisible(false);
                    System.out.println("Selcting NEW avatar...");
                    view.getMenuManager().showInitMenu();
                }
            }
        });
        deleteButton.addActionListener(e -> {
            if (selection()) {
                final Object[] options = {"Yes", "No"};
                final int n = JOptionPane.showOptionDialog(frame, "Are you sure?", "Question", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (n == JOptionPane.YES_OPTION) {
                    buttons.forEach(j -> {
                        if (j.isSelected()) {
                            avatarToDelete = this.listInformation.get(this.buttons.indexOf(j)).getInformation().getName();
                            controller.deleteAvatar(this.avatarToDelete);
                            SavedAvatarMenu.this.frame.setVisible(false);
                            SavedAvatarMenu.this.view.getMenuManager().showMainMenu();
                        }
                    });
                } 
            } else {
                JOptionPane.showMessageDialog(frame, NO_JAVAGOTCHI_SELECTED, "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        this.frame.setResizable(false);
        this.frame.getContentPane().add(mainPanel);
        this.frame.setLocationRelativeTo(null);
    }

    private void setAvatarPanel() {
        constraint.gridy = 0;
        constraint.gridx = 0;
        listInformation.forEach(e -> {
            buttons.get(listInformation.indexOf(e)).setText(listInformation.get(listInformation.indexOf(e)).getInformation().getName());
            buttons.get(listInformation.indexOf(e)).setEnabled(true);
            if (listInformation.get(listInformation.indexOf(e)).getInformation().getAvatar().toString().equals(Avatar.FOX.toString())) {
                avatarPanel.add(this.imagesLabel.get(0), constraint);
                this.imagesLabel.get(0).setEnabled(true);
            } else if (listInformation.get(listInformation.indexOf(e)).getInformation().getAvatar().toString().equals(Avatar.PANDA.toString())) {
                avatarPanel.add(this.imagesLabel.get(1), constraint);
                this.imagesLabel.get(1).setEnabled(true);
            } else {
                avatarPanel.add(this.imagesLabel.get(2), constraint);
                this.imagesLabel.get(2).setEnabled(true);
            }
            constraint.gridx++;
        });
    }
    @Override
    public final void show() {
        this.setAvatarPanel();
        this.frame.setVisible(true);
        System.out.println("Showing SavedAvatar menu...");
    }
    private boolean selection() {
        for (final JRadioButton b: buttons) {
            if (b.isSelected()) {
                    return true;
            }
        }
        return false;
    }
}