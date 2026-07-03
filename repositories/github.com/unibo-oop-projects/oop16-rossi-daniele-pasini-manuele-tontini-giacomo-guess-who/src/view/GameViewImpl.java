package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import controller.ControllerImpl;
import model.Character;
import model.Question;
import utilities.Audio;
import utilities.ResourceLoader;
import utilities.ScreenResolution;
import utilities.Audio.Song;

/**
 * 
 * Defines methods to show the game states and interact with the user.
 *
 */
public class GameViewImpl implements GameView {
    private static final String TITLE = "Guess Who?";
    private static final double UTILITYPAN_WIDTHPROP = 0.24;
    private static final double UTILITYIMG_WIDTHPROP = 0.30;
    private static final double UTILITYCOMP_HEIGHTPROP = 0.25;
    private static final double UTILITYSPACE_HEIGHTPROP = 0.020;
    private static final double LOGO_HEIGHTPROP = 0.15;
    private static final double QUESTIONPAN_WSPACEPROP = 0.03;
    private static final double QUESTIONPAN_HEIGHTPROP = 0.12;
    private static final double BTNASK_PROP = 0.50;
    private static final double BTNAUDIO_PROP = 0.09;
    private static final int GRID_WIDTH = 6;
    private static final int DEFAULT_GRID_HEIGHT = 4;
    private static final String IMAGE_PATH = "/images/";

    private static final int BL_RGAP = 20;
    private static final int BL_LGAP = 16;
    private static final int GRID_HGAP = 4;
    private static final int GRID_VGAP = 4;

    private final MyFrame frame;
    private final JPanel charactersPanel;
    private final JPanel questionsPanel;
    private final JPanel utilityPanel;
    private final Map<CharacterButton, Character> buttonToCharacter;
    private final Map<Character, CharacterButton> characterToButton;
    private final Map<Character, MyLabel> aiCharacterToLabel;
    private final Map<String, Question> textToQuestion;
    private final MyLabel playerCharcater;
    private final JComboBox<String> questionsCmb;
    private final MyLabel logo;
    private final JScrollPane gridContainer;
    private final Controller controller;
    private final ComponentButton jbtnAsk;
    private final ComponentButton jbtnAudio;
    private final JPanel aiCharactersLeft;
    private final int utilityPanelWidth;
    private final int utilityPanelImageWidth;
    private final int questionsPanelHeight;


    /**
     * 
     */
    public GameViewImpl() {
        final GUIFactory factory;
        this.controller = ControllerImpl.getController();
        this.frame = new MyFrame(TITLE,
                Optional.of(new ImageIcon(ResourceLoader.loadImage(IMAGE_PATH + "Background.png"))),
                new BorderLayout());
        factory = GUIFactoryImpl.getFactory();
        this.utilityPanelWidth = (int) (frame.getSize().getWidth() * UTILITYPAN_WIDTHPROP);
        this.utilityPanelImageWidth = (int) (frame.getSize().getWidth() * UTILITYIMG_WIDTHPROP);
        this.questionsPanelHeight = (int) (frame.getSize().getHeight() * QUESTIONPAN_HEIGHTPROP);
        this.charactersPanel = factory
                .createPanel(Optional.of(new GridLayout(getOptimalGridHeight(), GRID_WIDTH, GRID_HGAP, GRID_VGAP)));
        this.questionsPanel = factory.createPanel(Optional.of(new GridBagLayout()));
        this.utilityPanel = factory.createPanel(Optional.empty());
        this.utilityPanel.setBorder(new EmptyBorder(0, BL_LGAP, 0, BL_RGAP));
        this.playerCharcater = new MyLabel(
                new ImageIcon(ResourceLoader.loadImage(IMAGE_PATH + "charactersBackground.png")));
        this.questionsCmb = factory.createComboBox(
                controller.getAvailableQuestions().stream().map(x -> x.getText()).collect(Collectors.toList()));
        this.logo = new MyLabel(new ImageIcon(ResourceLoader.loadImage(IMAGE_PATH + "logo.png")));
        this.gridContainer = new JScrollPane(charactersPanel);
        this.aiCharactersLeft = factory.createPanel(Optional.of(new GridLayout(getOptimalGridHeight(), GRID_WIDTH)));
        this.buttonToCharacter = new HashMap<>();
        this.characterToButton = new HashMap<>();
        this.textToQuestion = new HashMap<>();
        this.aiCharacterToLabel = new HashMap<>();
        this.jbtnAsk = new ComponentButton(new ImageIcon(ResourceLoader.loadImage(IMAGE_PATH + "AskButton.png")),
                (int) (questionsPanelHeight * BTNASK_PROP), (int) (questionsPanelHeight * BTNASK_PROP));
        this.jbtnAudio = new ComponentButton(new ImageIcon(ResourceLoader.loadImage(IMAGE_PATH + "AudioEnabled.png")),
                (int) (utilityPanelImageWidth * BTNAUDIO_PROP), (int) (utilityPanelImageWidth * BTNAUDIO_PROP));
        this.gridContainer.setBorder(null);
        this.gridContainer.getViewport().setOpaque(false);
        this.gridContainer.setOpaque(false);
        this.utilityPanel.setLayout(new BoxLayout(utilityPanel, BoxLayout.Y_AXIS));
        jbtnAudio.setAlignmentX(Component.CENTER_ALIGNMENT);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerCharcater.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    @Override
    public void showView() {
        setComponentsSize();
        inizializeComponents();
        utilityPanel.add(jbtnAudio);
        utilityPanel.add(Box.createRigidArea(new Dimension(0, (int) (frame.getHeight() * UTILITYSPACE_HEIGHTPROP))));
        utilityPanel.add(logo);
        utilityPanel.add(Box.createRigidArea(new Dimension(0, (int) (frame.getHeight() * UTILITYSPACE_HEIGHTPROP))));
        utilityPanel.add(playerCharcater);
        utilityPanel.add(Box.createRigidArea(new Dimension(0, (int) (frame.getHeight() * UTILITYSPACE_HEIGHTPROP))));
        utilityPanel.add(aiCharactersLeft);
        frame.getMainPanel().add(gridContainer, BorderLayout.CENTER);
        frame.getMainPanel().add(utilityPanel, BorderLayout.EAST);
        frame.getMainPanel().add(questionsPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocation((ScreenResolution.getScreenWidth() - frame.getWidth()) / 2,
                (ScreenResolution.getScreenHeight() - frame.getHeight()) / 2);
        frame.setVisible(true);
        MyDialog.showMessageDialog(frame, "Benvenuto! scegli un personaggio per iniziare a giocare.");
    }

    @Override
    public void showWinMessage(final String message, final ImageIcon aiCharacter) {

        Audio.pauseSound(Song.BACKGROUND);
        Audio.playSound(Song.END_MATCH, false);
        final int res = MyDialog.showWinDialog(frame, message, aiCharacter);
        if (res == 1) {
            frame.dispose();
        } else {
            resetGUI();
            Audio.stopSound(Song.END_MATCH);
            Audio.resumeSong(Song.BACKGROUND, true);
            controller.restartGame();
        }
    }

    @Override
    public void showQuestion(final Question question) {
        final boolean response = MyDialog.showConfirmDialog(frame, question.getText()) == 0 ? true : false;
        controller.humanAnswered(question, response);
        rePaintAiCharacters();
    }

    @Override
    public void showQuestion(final Character character) {
        final String question = character.getName() + " è il tuo personaggio?";
        final boolean response = MyDialog.showConfirmDialog(frame, question) == 0 ? true : false;
        controller.aiGuessed(character, response);
        rePaintAiCharacters();
    }

    @Override
    public void showMessage(final String message) {
        MyDialog.showMessageDialog(frame, message);
        rePaintHumanCharacters();
    }

    private void rePaintHumanCharacters() {
        controller.getHumanCharactersLeft().entrySet().forEach(x -> {
            if (!x.getValue()) {
                characterToButton.get(x.getKey()).addOverlayImage();
            }
        });
        frame.getMainPanel().revalidate();
        frame.getMainPanel().repaint();
    }

    private void rePaintAiCharacters() {
        controller.getAICharactersLeft().entrySet().forEach(x -> {
            if (!x.getValue()) {
                aiCharacterToLabel.get(x.getKey())
                        .addOverlayImage(new ImageIcon(ResourceLoader.loadImage(IMAGE_PATH + "cross.png")));
            }
        });
        frame.getMainPanel().revalidate();
        frame.getMainPanel().repaint();
    }

    private int getOptimalGridHeight() {
        return controller.getHumanCharactersLeft().size() / GRID_WIDTH;
    }

    private void setComponentsSize() {
        gridContainer.setPreferredSize(
                new Dimension((frame.getWidth() - utilityPanelWidth), (frame.getHeight() - questionsPanelHeight)));
        questionsPanel.setPreferredSize(new Dimension((frame.getWidth()), questionsPanelHeight));
        utilityPanel.setPreferredSize(new Dimension(utilityPanelWidth, frame.getHeight() - questionsPanelHeight));
        logo.setPreferredSize(
                new Dimension(utilityPanelImageWidth, (int) (frame.getSize().getHeight() * LOGO_HEIGHTPROP)));
        playerCharcater.setPreferredSize(
                new Dimension(utilityPanelImageWidth, (int) (frame.getSize().getHeight() * UTILITYCOMP_HEIGHTPROP)));
        aiCharactersLeft.setMaximumSize(
                new Dimension(utilityPanelImageWidth, (int) (frame.getHeight() * UTILITYCOMP_HEIGHTPROP)));
        aiCharactersLeft.setPreferredSize(
                new Dimension(utilityPanelImageWidth, (int) (frame.getHeight() * UTILITYCOMP_HEIGHTPROP)));
    }

    private void inizializeComponents() {
        /* Inizialize characters buttons */
        controller.getHumanCharactersLeft().keySet().forEach(character -> {
            final CharacterButton btn = new CharacterButton(character.getPic(),
                    new ImageIcon(ResourceLoader.loadImage(IMAGE_PATH + "cross.png")), gridContainer.getPreferredSize(),
                    GRID_WIDTH, DEFAULT_GRID_HEIGHT);
            buttonToCharacter.put(btn, character);
            characterToButton.put(character, btn);
            charactersPanel.add(btn);
        });
        /*inizialize hashmap to get question by text*/
        controller.getAvailableQuestions().forEach(elem -> {
            textToQuestion.put(elem.getText(), elem);
        });
        /* Inizialize AI characters left grid */
        controller.getAICharactersLeft().entrySet().forEach(a -> {
            final MyLabel tmp = new MyLabel(
                    new ImageIcon(ResourceLoader.loadImage(IMAGE_PATH + "AnonymousCharacter.png")));
            aiCharacterToLabel.put(a.getKey(), tmp);
            aiCharactersLeft.add(tmp);
        });
        /*
         * Create audio button actionlistener: when button is clicked button
         * image and audio state are changed
         */
        final ActionListener audio = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (Audio.isEnabled()) {
                    jbtnAudio.changeIcon(new ImageIcon(ResourceLoader.loadImage(IMAGE_PATH + "AudioDisabled.png")));
                    Audio.enableSound(false);
                } else {
                    jbtnAudio.changeIcon(new ImageIcon(ResourceLoader.loadImage(IMAGE_PATH + "AudioEnabled.png")));
                    Audio.enableSound(true);
                }
            }
        };
        /*
         * Create ask button actionlistener:when button is clicked the selected
         * question is passed to controller and the question combobox is
         * refreshed
         */
        final ActionListener ask = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                Audio.playSound(Song.BUTTON_CLICK, false);
                controller.askQuestion(textToQuestion.get(questionsCmb.getSelectedItem().toString()));
                questionsCmb.removeAllItems();
                controller.getAvailableQuestions().forEach(x -> questionsCmb.addItem(x.getText()));
            }
        };
        /* Add actionlisteners to buttons */
        setSelectCharacterAL();
        jbtnAudio.addActionListener(audio);
        jbtnAsk.addActionListener(ask);

    }

    private void setSelectCharacterAL() {
        /*
         * Create select character actionlistener: when button is clicked
         * confirm dialog appears,if the user confirm is choice the controller
         * is notified and the GUI is refreshed
         */
        final ActionListener selectCharacter = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                Audio.playSound(Song.BUTTON_CLICK, false);
                JButton jb = (JButton) e.getSource();
                String characterName = buttonToCharacter.get(jb).getName();
                int res = MyDialog.showConfirmDialog(frame,
                        "Vuoi utilizzare " + characterName + " come personaggio per la partita?");
                if (res == 0) {
                    playerCharcater.addOverlayImage(buttonToCharacter.get(jb).getPic());
                    buttonToCharacter.keySet().forEach(a -> a.removeActionListener(this));
                    setPossibleAICharacterAL();
                    jbtnAsk.setEnabled(true);
                    frame.getMainPanel().revalidate();
                    frame.getMainPanel().repaint();
                    questionsPanel.add(questionsCmb);
                    questionsPanel.add(Box.createRigidArea(
                            new Dimension((int) (frame.getSize().getWidth() * QUESTIONPAN_WSPACEPROP), 0)));
                    questionsPanel.add(jbtnAsk);
                    SwingUtilities.updateComponentTreeUI(questionsPanel);
                    controller.setHumanCharacter(buttonToCharacter.get(jb));
                }
            }
        };
        buttonToCharacter.keySet().forEach(button -> button.addActionListener(selectCharacter));
    }

    private void setPossibleAICharacterAL() {
        /*
         * Create try to guess actionlistener: when button is clicked confirm
         * dialog appears,if the user confirm is choice the controller is
         * notified
         */
        final ActionListener possibleAICharacter = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                Audio.playSound(Song.BUTTON_CLICK, false);
                JButton jb = (JButton) e.getSource();
                String characterName = buttonToCharacter.get(jb).getName();
                int res = MyDialog.showConfirmDialog(frame,
                        "Pensi che " + characterName + " sia il personaggio dell'avversario?");
                if (res == 0) {
                    controller.tryToGuess(buttonToCharacter.get(jb));
                }

            }
        };
        buttonToCharacter.keySet().forEach(f -> f.addActionListener(possibleAICharacter));
    }

    private void resetGUI() {
        buttonToCharacter.keySet().forEach(button -> {
            button.resetIconImg();
            button.removeActionListener(button.getActionListeners()[0]);
        });
        setSelectCharacterAL();
        jbtnAudio.changeIcon(new ImageIcon(ResourceLoader.loadImage(IMAGE_PATH + "AudioEnabled.png")));
        playerCharcater.resetIcon();
        aiCharacterToLabel.values().forEach(lbl -> lbl.resetIcon());
        questionsPanel.remove(jbtnAsk);
        questionsPanel.remove(questionsCmb);
        frame.getMainPanel().revalidate();
        frame.getMainPanel().repaint();
    }

}
