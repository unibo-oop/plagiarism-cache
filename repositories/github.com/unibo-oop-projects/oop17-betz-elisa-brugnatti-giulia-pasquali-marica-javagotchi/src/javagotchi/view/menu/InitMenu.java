package javagotchi.view.menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
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
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import javagotchi.controller.menu.MenuController;
import javagotchi.model.information.Avatar;
import javagotchi.model.information.Gender;
/**
 * * This class is the implementation of the initMenu,
 *  it makes user able to initialize the javagotchi's fields.
 * @author giulia
 *
 */
public class InitMenu implements Menu {
    private static final double NEWGAME_PERC_WIDTH = 0.6;
    private static final double NEWGAME_PERC_HEIGHT = 0.58;
    private static final int MAXLENGTH = 30;
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final MenuController controller;
    private final JFrame frame;
    private Gender selectedGender;
    private Avatar selectedAvatar;
    private final List<JRadioButton> buttons;
    private String name;
    private final List<String> listAvatarUnavailable;
    /**
     * This is the constructor for this class.
     * @param controller **this is the MenuController**
     * @param view    **This is the MenuView**
     */
    public InitMenu(final MenuController controller, final MenuView view) {
        System.out.println("Creating Init menu...");
        this.controller = controller;
        this.listAvatarUnavailable = this.controller.getListAvatarUnavailable();
        final List<JLabel> imagesLabel = view.getImagesAvatarList();
        final List<JLabel> imagesGenderLabel = view.getImagesGenderList();
        JTextField textField;
        final Iterator<String> avatarNameList = view.getListAvatarName().iterator();
        textField = new JTextField(MAXLENGTH);
        textField.setDocument(new JTextFieldLimit(10));
        this.frame = new JFrame("Tamagotchi  -  InitSetting");
        this.frame.setSize((int) (screenSize.getWidth() * NEWGAME_PERC_WIDTH), (int) (screenSize.getHeight() * NEWGAME_PERC_HEIGHT));
        this.frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent e) {
                final Object[] options = {"Yes", "No"};
                final int n = JOptionPane.showOptionDialog(frame, "Do you really want to quit without saving ?",
                        " Closing application ..", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (n == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        final JPanel mainPanel = new JPanel();
        final JPanel genderPanel = new JPanel(); // female, male's buttons panel
        final JPanel avatarPanel = new JPanel(); // cat, fox, panda's buttons panel
        final JPanel namePanel = new JPanel(); // avatar's name panel
        namePanel.add(textField);
        final ButtonGroup genderButtonGroup = new ButtonGroup();
        final ButtonGroup avatarButtonGroup = new ButtonGroup();
        final JButton saveButton = new JButton("SAVE");

        final JRadioButton maleButton = new JRadioButton("Male");
        maleButton.setName(Gender.MALE.toString());
        final JRadioButton femaleButton = new JRadioButton("Female");
        femaleButton.setName(Gender.FEMALE.toString());

        genderButtonGroup.add(maleButton);
        genderButtonGroup.add(femaleButton);

        mainPanel.setBorder(new TitledBorder("Avatar Settings"));
        mainPanel.setLayout(new BorderLayout());

        genderPanel.setBorder(new TitledBorder("Gender"));
        genderPanel.setLayout(new GridBagLayout());
        avatarPanel.setBorder(new TitledBorder("Avatar"));
        avatarPanel.setLayout(new GridBagLayout());
        namePanel.setBorder(new TitledBorder("Name"));
        // LISTENERS
        // gender
        maleButton.addActionListener(e -> InitMenu.this.selectedGender = Gender.MALE);
        femaleButton.addActionListener(e -> InitMenu.this.selectedGender = Gender.FEMALE);

        // avatar
        final ActionListener ac = e -> {
            final JRadioButton jb = (JRadioButton) e.getSource();
            if (jb.isSelected()) {
                setSelection(jb);
            }
        };

        // save button
        saveButton.addActionListener(e -> {
            if (controller.checkName(textField.getText())) {
                name = textField.getText();
                InitMenu.this.frame.setVisible(false);
                InitMenu.this.controller.setUserAvatarChoices(InitMenu.this.name, InitMenu.this.selectedAvatar, InitMenu.this.selectedGender);
                view.getMenuManager().showMainMenu();
            } else {
                JOptionPane.showMessageDialog(frame, "This name is not valid...", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        buttons = IntStream.range(0, 3).mapToObj(i -> new JRadioButton()).peek(jb -> jb.setEnabled(true)).peek(avatarButtonGroup::add).peek(jb -> jb.setText(avatarNameList.next())).peek(jb -> jb.addActionListener(ac)).collect(Collectors.toList());
        buttons.stream().sorted((x, y) -> -1).collect(Collectors.toList()).forEach(jb -> { //scorro la lista dei bottoni al contrario perchè il JButton selezionato di default sia sempre il primo disponibile a partire da sinistra
            this.listAvatarUnavailable.forEach(e -> {
                if (e.equals(jb.getText())) {
                    jb.setEnabled(false);
                    imagesLabel.get(buttons.indexOf(jb)).setEnabled(false);
                }
            });

            if (jb.isEnabled()) {
                jb.doClick();
                setSelection(jb); 
            }
        });
        namePanel.setLayout(new GridBagLayout());
        final GridBagConstraints constraint = new GridBagConstraints();
        constraint.gridy = 0; // row1
        constraint.gridx = 0; // column1
        constraint.insets = new Insets(3, 3, 3, 3);

        constraint.fill = GridBagConstraints.HORIZONTAL;
        genderPanel.add(maleButton, constraint);
        namePanel.add(textField);
        imagesLabel.forEach(e -> {
            avatarPanel.add(e, constraint);
            constraint.gridx++;
        });
        constraint.gridy++;
        imagesGenderLabel.forEach(e -> {
            genderPanel.add(e, constraint);
            constraint.gridy = constraint.gridy + 2;
        });
        constraint.gridx = 0; 
        constraint.gridy = 2; 
        genderPanel.add(femaleButton, constraint);

        buttons.forEach(e -> {
            avatarPanel.add(e, constraint);
            constraint.gridx++;
        });
        mainPanel.add(genderPanel, BorderLayout.WEST);
        mainPanel.add(avatarPanel, BorderLayout.CENTER);
        mainPanel.add(namePanel, BorderLayout.NORTH);

        final JPanel settingPanel = new JPanel();
        settingPanel.add(saveButton);
        mainPanel.add(settingPanel, BorderLayout.SOUTH);

        maleButton.doClick();

        this.selectedGender = Gender.MALE;
        this.frame.setResizable(false);
        this.frame.getContentPane().add(mainPanel);
        this.frame.setLocationRelativeTo(null);
    }

    @Override
    public final void show() {
        this.frame.setVisible(true);
        System.out.println("Showing New Game menu...");
    }

    class JTextFieldLimit extends PlainDocument { // limita il numero di carattere che è possibile inserire nel
                                                    // textField del nome dell'avatar
        private static final long serialVersionUID = 1L;
        private final int limit;

        JTextFieldLimit(final int limit) {
            super();
            this.limit = limit;
        }
        public void insertString(final int offset, final String str, final AttributeSet attr) throws BadLocationException {
            if ((getLength() + str.length()) <= limit) {
                super.insertString(offset, str, attr);
            }
        }
    }
    private void setSelection(final JRadioButton jb) {
        if (jb.getText().equals(Avatar.PANDA.toString())) {
            this.selectedAvatar = Avatar.PANDA;
        } else if (jb.getText().equals(Avatar.CAT.toString())) {
            this.selectedAvatar = Avatar.CAT;
        } else {
            this.selectedAvatar = Avatar.FOX;
        }
    }
}