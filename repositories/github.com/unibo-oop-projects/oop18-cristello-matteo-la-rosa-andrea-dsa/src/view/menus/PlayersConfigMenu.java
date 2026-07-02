package view.menus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import controller.GamePlayLauncher;
import view.BoardColorPalette;
import view.ExceptionDialog;
import view.board.DialogWindowError;
import view.board.TemplateEnum;

/**
 * Menu for players configuration and table configuration.
 */
public class PlayersConfigMenu {

    private final JFrame frame = new JFrame();
    private final int numberOfTextFieldToGenerate;
    private final JPanel panelOfButtonOk = new JPanel();
    private final JButton buttonOk = new JButton("ok");
    private final Map<String, String> nameAndColorPlayerMap = new HashMap<String, String>();
    private final Map<TextField, JComboBox<String>> textJComboMap = new HashMap<TextField, JComboBox<String>>();
    private final JPanel contentPanel = new JPanel();
    private static final String NPLAYERIMAGE = "/matchSetting.jpg";
    private final List<String> playerColorList;
    private final String[] template = new String[] { TemplateEnum.ZIGZAG.toString(), TemplateEnum.CIRCULAR.toString() };
    private final JComboBox<String> templateComboBox;
    private final JPanel lastPanel = new JPanel();
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * Setto il numero di giocatori cioe' di textfield da generare e setto il frame iniziale.
     * 
     * @param numberOfTextFieldToGenerate
     *                                        Number of text field to generate.
     * @param playerColorList
     *                                        The colors that can be choosen.
     */

    public PlayersConfigMenu(final int numberOfTextFieldToGenerate, final List<String> playerColorList) {

        this.playerColorList = playerColorList;
        this.numberOfTextFieldToGenerate = numberOfTextFieldToGenerate;
        this.frame.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
        final ImageIcon mainImageIcon = new ImageIcon(getClass().getResource(NPLAYERIMAGE));
        final Image mainImageTmp = mainImageIcon.getImage();
        final Image scaledImage = mainImageTmp.getScaledInstance(frame.getWidth(), frame.getHeight(),
                Image.SCALE_SMOOTH);
        final JLabel mainImageLabel = new JLabel(new ImageIcon(scaledImage));
        this.frame.setContentPane(mainImageLabel); // immagine di background
        this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // tengo a tutto schermo il frame
        this.frame.setLayout(new GridBagLayout());
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.panelOfButtonOk.setLayout(new FlowLayout());
        this.panelOfButtonOk.add(this.buttonOk);
        this.contentPanel.setLayout(new GridLayout(this.numberOfTextFieldToGenerate + 2, 0));
        this.templateComboBox = new JComboBox<String>(this.template);

    }

    /**
     * Creo un frame con il numero di elementi pari al numero di giocatori label textfield(30) combobox per colore.
     */
    public void generateConfig() {

    	final String stringDisposition = "";
    	final JLabel labelDisposition = new JLabel(stringDisposition);
        this.contentPanel.setOpaque(false);
        this.panelOfButtonOk.setOpaque(false);

        IntStream.range(0, this.numberOfTextFieldToGenerate).forEach(i -> {

            final PlayersCharComboBox playerNameConfig = new PlayersCharComboBox(this.playerColorList);
            this.textJComboMap.put(playerNameConfig.getTextField(), playerNameConfig.getComboBox());
            this.contentPanel.add(playerNameConfig.getConfiguration());

        });

        labelDisposition.setForeground(Color.white);
        this.lastPanel.add(labelDisposition);
        this.lastPanel.add(this.templateComboBox);
        this.lastPanel.setBackground(Color.decode(BoardColorPalette.SEA_BLUE.getHexRGB()));
        this.contentPanel.add(this.lastPanel);
        this.contentPanel.add(this.panelOfButtonOk);

        this.frame.add(this.contentPanel);

        final ActionListener checkAllNameNotEqualsAndColorActionListener = e -> {

            if ((!Integer.valueOf(this.textJComboMap.values().stream().map(x -> x.getSelectedItem().toString())
                    .collect(Collectors.toSet()).size()).equals(this.textJComboMap.size())
                    || (!Integer.valueOf(this.textJComboMap.keySet().stream().filter(x -> !x.getText().isEmpty())
                            .map(x -> x.getText()).filter(x -> !x.trim().isEmpty()).collect(Collectors.toSet()).size())
                            .equals(this.textJComboMap.size())))) {

                new DialogWindowError(
                        "No camp should be empty, no colors or names can be equals.");

            } else {
                this.frame.setVisible(false);

                textJComboMap.entrySet().stream().forEach(
                        x -> nameAndColorPlayerMap.put(x.getKey().getText(), (String) x.getValue().getSelectedItem()));
                try {
                    new GamePlayLauncher((String) this.templateComboBox.getSelectedItem(), this.nameAndColorPlayerMap);
                } catch (IOException e1) {
                    new ExceptionDialog(e1.getMessage());
                    e1.printStackTrace();
                }

            }

        };

        this.buttonOk.addActionListener(checkAllNameNotEqualsAndColorActionListener);
        this.frame.setVisible(true);
    }

}