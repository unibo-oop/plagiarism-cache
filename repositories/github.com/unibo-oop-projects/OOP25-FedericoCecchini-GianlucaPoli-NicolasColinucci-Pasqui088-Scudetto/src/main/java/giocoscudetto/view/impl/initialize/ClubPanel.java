package giocoscudetto.view.impl.initialize;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import giocoscudetto.controller.api.CreateUpdateController;
import giocoscudetto.controller.api.Starter;
import giocoscudetto.view.api.initialize.PawnColorPicker;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

/**
 * Panel implementation to let users selected number of clubs, clubs name and pawn color.
 */
public final class ClubPanel extends DefaultPanelImpl {

    private static final int CLUB_SELECTION = 130;
    private static final int CLUB_SELECTION_LABEL = 75;
    private static final int BUTTON_BORDER = 5;
    private static final int TEXT_FIELDS_WIDTH = 300;
    private static final int TEXT_FIELDS_HEIGHT = 30;
    private static final int COLOR_HEIGHT = 32;
    private static final int COLOR_TEXTFIELD_MIN_WIDTH = 50;
    private static final int TEXTFIELD_MIN_HEIGHT = 20;
    private static final int PAWN_VERTICAL_SPACE = 5;
    private static final int TEXT_VERTICAL_SPACE = 10;
    private static final int NAME_MAXIMUM_CHARACTER = 9;

    private static final long serialVersionUID = 1L;
    private final transient Starter viewChanger;
    private final transient CreateUpdateController controller;
    private final transient Image image;

    private final List<JTextField> clubsName = new ArrayList<>();
    private final List<PawnColorPickerPanel> clubsPawn = new ArrayList<>();
    private final JButton btnCont = (JButton) createComponent(new JButton("CONTINUE"), 
                                                        getExitFont(), 
                                                        new Color(224, 201, 166), 
                                                        new Color(62, 91, 66));

    /**
     * @param viewChanger is the controller to change panel.
     * @param controller is the controller to create the clubs with the info choosen.
     */
    @SuppressFBWarnings //not expose internal representation.
    public ClubPanel(final Starter viewChanger, final CreateUpdateController controller) {
        this.viewChanger = viewChanger;
        this.controller = controller;
        this.setLayout(new BorderLayout());

        //Creating the panel to choose the number of teams
        final JPanel numberOfClubPanel = new JPanel(new BorderLayout());

        @SuppressWarnings("unchecked")
        final JComboBox<Integer> selectNumberOfClub = (JComboBox<Integer>) createComponent(
            new JComboBox<>(new Integer[]{2, 3, 4}),
            getButtonFont(),
            Color.BLUE, null);

        final JLabel clubNumberSelectionLabel = (JLabel) createComponent(
                new JLabel("Choose the number of player "),
                getButtonFont(),
                Color.BLACK, Color.LIGHT_GRAY);

        numberOfClubPanel.add(clubNumberSelectionLabel, BorderLayout.WEST);
        numberOfClubPanel.add(selectNumberOfClub, BorderLayout.EAST);
        numberOfClubPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        //Panel for general info of each club, which will contains clubNamePanel and clubPawnPanel
        final JPanel clubInfoPanel = new JPanel(new GridLayout(1, 2));

        //Panel to contain each club name selector
        final JPanel clubNamePanel = new JPanel();
        final TitledBorder nameTitle = new TitledBorder("CLUB'S NAME");
        nameTitle.setBorder(BorderFactory.createEmptyBorder());
        nameTitle.setTitleColor(Color.BLACK);
        nameTitle.setTitleJustification(TitledBorder.CENTER);

        clubNamePanel.setLayout(new BoxLayout(clubNamePanel, BoxLayout.Y_AXIS));
        clubNamePanel.setBorder(nameTitle);

        //Panel to contain each club color picker
        final JPanel clubPawnPanel = new JPanel();
        final TitledBorder pawnTitle = new TitledBorder("PAWN'S COLOR"); 
        pawnTitle.setBorder(BorderFactory.createEmptyBorder());
        pawnTitle.setTitleColor(Color.BLACK);
        pawnTitle.setTitleJustification(TitledBorder.CENTER); 

        clubPawnPanel.setLayout(new BoxLayout(clubPawnPanel, BoxLayout.Y_AXIS));
        clubPawnPanel.setBorder(pawnTitle);

        //Adding by default 2 rows to select a name and pawn
        updateTeamPanels(2, clubNamePanel, clubPawnPanel);

        clubInfoPanel.add(clubNamePanel);
        clubInfoPanel.add(clubPawnPanel);

        //Creating button to go back in the home or continue to visualize the pre match view
        final JPanel switchingButtonPanel = new JPanel(new BorderLayout());
        switchingButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, BUTTON_BORDER, BUTTON_BORDER, BUTTON_BORDER));

        final JButton btnBack = (JButton) createComponent(new JButton("BACK"), 
                                                        getExitFont(), 
                                                        new Color(224, 201, 166), 
                                                        new Color(62, 91, 66));

        btnCont.setVisible(false);

        switchingButtonPanel.add(btnBack, BorderLayout.WEST);
        switchingButtonPanel.add(btnCont, BorderLayout.EAST);

        //Adding the action listener to the buttons and ComboBox

        selectNumberOfClub.addActionListener(e -> {

            final Integer numberSelected = (Integer) selectNumberOfClub.getSelectedItem();

            if (numberSelected != null) {
                updateTeamPanels(numberSelected, clubNamePanel, clubPawnPanel);
            }

            updateButtonVisibility();
        });

        btnBack.addActionListener(e -> { 
            this.viewChanger.changeView("home");
        });

        btnCont.addActionListener(e -> { 
            //Creating clubs, table and fixtures to start the match
            this.controller.createClubs(this.clubsName.stream()
                                            .map(JTextField::getText)
                                            .toList(),
                                        this.clubsPawn.stream()
                                            .map(i -> i.getSelectedColor().getRGB())
                                            .toList()); 

            //Then going to the next view
            this.viewChanger.changeView("pre");
        }); 

        //Istruction to make the panel components responsive to resolution changes
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(final java.awt.event.ComponentEvent e) {
                final GraphicsConfiguration gc = 
                    GraphicsEnvironment.getLocalGraphicsEnvironment()
                    .getDefaultScreenDevice()
                    .getDefaultConfiguration();

                final AffineTransform tx = gc.getDefaultTransform();
                final int scaleX = (int) tx.getScaleX();
                final int currentWidth = getWidth();

                clubNumberSelectionLabel.setFont(new Font(FONT_SELECTED, Font.BOLD,
                                                currentWidth / CLUB_SELECTION_LABEL * scaleX));
                selectNumberOfClub.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / CLUB_SELECTION * scaleX));

                btnCont.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / SWITCHER_BUTTON_FONT_RESIZING));
                btnBack.setFont(new Font(FONT_SELECTED, Font.BOLD, currentWidth / SWITCHER_BUTTON_FONT_RESIZING));

                revalidate();

            }
        });

        //Centralizing clubInfoPanel vertically and responsively to the resolution changes
        final JPanel centerWrapper = new JPanel(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();

        //CHECKSTYLE: MagicNumber OFF
        //It is useless to create dedicated variables for each magic number here
        //Top Spacer to push everything to the bottom
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.VERTICAL;
        centerWrapper.add(Box.createVerticalGlue(), gbc);

        //Aligning numberOfClubPanel
        gbc.gridy = 1;
        gbc.weighty = 0.0;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(getHeight() / 6, 10, 5, 10); // 5px sotto, vicino a clubInfoPanel
        centerWrapper.add(numberOfClubPanel, gbc);

        //Aligning ClubInfoPanel
        gbc.gridy = 2;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(12, 10, 0, 10); // 5px sopra, simmetrico
        centerWrapper.add(clubInfoPanel, gbc);

        //Bottom Spacer tp push everything to the top (thanks to the top spacer now everything is centered)
        gbc.gridy = 3;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.VERTICAL;
        centerWrapper.add(Box.createVerticalGlue(), gbc);
        //CHECKSTYLE: MagicNumber ON

        //Setting the main panels opacity on false to show the background color
        clubInfoPanel.setOpaque(false);
        clubNamePanel.setOpaque(false);
        clubPawnPanel.setOpaque(false);
        centerWrapper.setOpaque(false);
        switchingButtonPanel.setOpaque(false);

        //Placing correctly the specific panels in the main one
        this.add(centerWrapper, BorderLayout.CENTER);
        this.add(switchingButtonPanel, BorderLayout.SOUTH);

        try {
            this.image = ImageIO.read(new File("src/main/resources/images/backgrounds/club-background.jpeg"));
        } catch (final IOException e) {
            throw new IllegalStateException("Failed to load image", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);

        if (g instanceof Graphics2D g2d) {
            g2d.drawImage(this.image, 0, 0, getWidth(), getHeight(), null);
        }
        //final Graphics2D g2d = (Graphics2D) g;
    }

    /**
     * This method is used to show the correct number of rows depending on clubs number selected
     * and showing the textfields to write the clubs name and color picker to pick the club
     * pawn color.
     * 
     * @param rows selected from the combobox
     * @param namePanel .
     * @param pawnPanel .
     */
    private void updateTeamPanels(final int rows, final JPanel namePanel, final JPanel pawnPanel) {

        namePanel.removeAll();
        pawnPanel.removeAll();
        this.clubsName.clear();
        this.clubsPawn.clear();

        int i;
        for (i = 0; i < rows; i++) {

            //Creating textFields to choose the name for each team
            final JTextField nameTextField = (JTextField) createComponent(new JTextField(), getFont(), Color.BLACK, null);
            nameTextField.setPreferredSize(new Dimension(0, TEXT_FIELDS_HEIGHT));
            nameTextField.setMaximumSize(new Dimension(TEXT_FIELDS_WIDTH, TEXT_FIELDS_HEIGHT));
            nameTextField.setMinimumSize(new Dimension(COLOR_TEXTFIELD_MIN_WIDTH, TEXTFIELD_MIN_HEIGHT));

            //Adding the new textField to the panel and list, then adding little space under it
            namePanel.add(nameTextField);
            namePanel.add(Box.createVerticalStrut(TEXT_VERTICAL_SPACE));
            this.clubsName.add(nameTextField);

            nameTextField.getDocument().addDocumentListener(new DocumentListener() {

                @Override
                public void insertUpdate(final DocumentEvent e) {
                    updateButtonVisibility();
                }

                @Override
                public void removeUpdate(final DocumentEvent e) {
                    updateButtonVisibility();
                }

                @Override
                public void changedUpdate(final DocumentEvent e) {
                    updateButtonVisibility();
                }
            });

            nameTextField.addKeyListener(new KeyListener() {

                /**
                 * Through the consume of the KeyEvent i can avoid the insertion of more
                 * character than the maximum one.
                 * 
                 * @param e is the event i catch after typing a new key.
                 */
                @Override
                public void keyTyped(final KeyEvent e) {
                    if (nameTextField.getText().length() >= NAME_MAXIMUM_CHARACTER) {
                        e.consume();
                    }
                }

                @Override
                public void keyPressed(final KeyEvent e) {
                }

                @Override
                public void keyReleased(final KeyEvent e) {
                }

            });
            final PawnColorPickerPanel colorPicker = new PawnColorPickerPanel();
            colorPicker.setPreferredSize(new Dimension(0, COLOR_HEIGHT));
            colorPicker.setMaximumSize(new Dimension(Integer.MAX_VALUE, COLOR_HEIGHT));
            colorPicker.setMinimumSize(new Dimension(COLOR_TEXTFIELD_MIN_WIDTH, COLOR_HEIGHT));

            colorPicker.setOnColorChanged(c -> refreshColorTaken(this.clubsPawn));

            pawnPanel.add(colorPicker);
            pawnPanel.add(Box.createVerticalStrut(PAWN_VERTICAL_SPACE));
            this.clubsPawn.add(colorPicker);
        }

        //Revalidate and Repaint are necessery to update the interface
        this.revalidate(); 
        this.repaint();

    }

    /**
     * This method disable the color already taken from a club.
     * 
     * @param pickers are the picker assigned to each club.
     */
    private void refreshColorTaken(final List<PawnColorPickerPanel> pickers) {
        final Set<Color> allTaken = new HashSet<>();
        for (final PawnColorPickerPanel p : pickers) {
            if (p.getSelectedColor() != null) {
                allTaken.add(p.getSelectedColor());
            }
        }

        //and then i disable them
        for (final PawnColorPickerPanel p : pickers) {
            final Set<Color> takenByOthers = new HashSet<>(allTaken);
            if (p.getSelectedColor() != null) {
                takenByOthers.remove(p.getSelectedColor());
            }

            p.setTakenColors(takenByOthers);
            this.updateButtonVisibility();
        }
    }

    /**
     * Method to setVisible the button continue, only under certain condition.
     * - each club selected a color.
     * - each club selected a name, which should be not the same as one of other clubs.
     */
    private void updateButtonVisibility() {
        //System.out.print("controlling button visibility...");
        final List<Boolean> selectedColor = new ArrayList<>();

        for (final PawnColorPicker picker: clubsPawn) {
            selectedColor.add(picker.isColorSelected());
        }

        btnCont.setVisible(this.controller.isClubInfoComplete(this.clubsName.stream()
                                .map(JTextField::getText).toList(),
                                selectedColor));
        //System.out.println(btnCont.isVisible());
    }
}
