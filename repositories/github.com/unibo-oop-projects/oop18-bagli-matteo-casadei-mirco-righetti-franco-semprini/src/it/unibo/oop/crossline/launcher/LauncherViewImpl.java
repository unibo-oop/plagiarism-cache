package it.unibo.oop.crossline.launcher;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;

/**
 *  This is the class responsible of managing the view of the launcher.
 */
public class LauncherViewImpl extends JFrame implements LauncherView {

    private static final long serialVersionUID = 5857544893432850356L;

    private static final String WINDOW_TITLE = "Crossline";
    private static final Dimension WINDOW_SIZE = new Dimension(512, 512);
    private static final int COMPONENT_PADDING = 16;
    private static final int VOLUME_TICK_SPACING_MAJOR = 20;
    private static final int VOLUME_TICK_SPACING_MINOR = 10;
    private static final Insets CHECKBOX_MARGIN = new Insets(0, -2, 0, 0);

    private final JSlider sliderVolume = new JSlider(0, 100);
    private final JFormattedTextField  textFieldWidth;
    private final JFormattedTextField  textFieldHeight;
    private final JCheckBox checkBoxFullscreen = new JCheckBox();
    private final JComboBox<Integer> comboBoxScreen = new JComboBox<Integer>();
    private final JButton buttonPlay = new JButton("Play");

    /**
     * Initialize the launcher view.
     */
    public LauncherViewImpl() {
        super();

        this.setResizable(false);
        this.setSize(WINDOW_SIZE);
        this.setTitle(WINDOW_TITLE);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new GridBagLayout());

        /* Banner */
        final GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(COMPONENT_PADDING, COMPONENT_PADDING, 0, COMPONENT_PADDING);
        try {
            final BufferedImage image = ImageIO.read(new File("./res/banner.png"));
            final JLabel label = new JLabel(new ImageIcon(image));
            this.add(label, constraints);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* Tabs */
        constraints.gridy = 1;
        constraints.weighty = 1;
        final JTabbedPane tabbedPane = new JTabbedPane();

        /* Audio tab */
        final JPanel panelAudio = new JPanel();
        panelAudio.setLayout(new GridBagLayout());
        panelAudio.add(new JLabel("Volume"));
        sliderVolume.setMajorTickSpacing(VOLUME_TICK_SPACING_MAJOR);
        sliderVolume.setMinorTickSpacing(VOLUME_TICK_SPACING_MINOR);
        sliderVolume.setPaintTicks(true);
        sliderVolume.setPaintLabels(true);
        panelAudio.add(sliderVolume);
        tabbedPane.addTab("Audio", panelAudio);

        /* Graphics tab */
        final JPanel panelGraphics = new JPanel();
        panelGraphics.setLayout(new GridBagLayout());

        /* Labels */
        final GridBagConstraints constraintsGraphics = new GridBagConstraints();
        constraintsGraphics.anchor = GridBagConstraints.LINE_END;
        constraintsGraphics.insets = new Insets(0, 0, COMPONENT_PADDING, COMPONENT_PADDING);
        panelGraphics.add(new JLabel("Screen"), constraintsGraphics);
        constraintsGraphics.gridy = 1;
        panelGraphics.add(new JLabel("Width"), constraintsGraphics);
        constraintsGraphics.gridy = 2;
        panelGraphics.add(new JLabel("Height"), constraintsGraphics);
        constraintsGraphics.gridy = 3;
        constraintsGraphics.insets = new Insets(0, 0, 0, COMPONENT_PADDING);
        panelGraphics.add(new JLabel("Fullscreen"), constraintsGraphics);
        constraintsGraphics.fill = GridBagConstraints.HORIZONTAL;
        constraintsGraphics.gridx = 1;
        constraintsGraphics.gridy = 0;
        constraintsGraphics.insets = new Insets(0, 0, COMPONENT_PADDING, COMPONENT_PADDING);
        ((JLabel) comboBoxScreen.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        panelGraphics.add(comboBoxScreen, constraintsGraphics);
        constraintsGraphics.gridy = 1;

        /* Width text field */
        final NumberFormat widthFormat = NumberFormat.getInstance();
        widthFormat.setGroupingUsed(false);
        final NumberFormatter widthFormatter = new NumberFormatter(widthFormat);
        widthFormatter.setValueClass(Integer.class);
        widthFormatter.setMinimum(0);
        widthFormatter.setMaximum(Integer.MAX_VALUE);
        widthFormatter.setAllowsInvalid(false);
        widthFormatter.setCommitsOnValidEdit(true);
        textFieldWidth = new JFormattedTextField(widthFormatter);
        textFieldWidth.setValue(0);
        textFieldWidth.setColumns(4);
        textFieldWidth.setHorizontalAlignment(JTextField.CENTER);
        panelGraphics.add(textFieldWidth, constraintsGraphics);
        constraintsGraphics.gridy = 2;

        /* Height text field */
        final NumberFormat heightFormat = NumberFormat.getInstance();
        heightFormat.setGroupingUsed(false);
        final NumberFormatter heightFormatter = new NumberFormatter(heightFormat);
        heightFormatter.setValueClass(Integer.class);
        heightFormatter.setMinimum(0);
        heightFormatter.setMaximum(Integer.MAX_VALUE);
        heightFormatter.setAllowsInvalid(false);
        heightFormatter.setCommitsOnValidEdit(true);
        textFieldHeight = new JFormattedTextField(heightFormatter);
        textFieldHeight.setValue(0);
        textFieldHeight.setColumns(4);
        textFieldHeight.setHorizontalAlignment(JTextField.CENTER);

        /* Fullscreen checbox */
        panelGraphics.add(textFieldHeight, constraintsGraphics);
        constraintsGraphics.gridy = 3;
        constraintsGraphics.insets = new Insets(0, 0, 0, COMPONENT_PADDING);
        checkBoxFullscreen.setMargin(CHECKBOX_MARGIN);
        panelGraphics.add(checkBoxFullscreen, constraintsGraphics);
        tabbedPane.addTab("Graphics", panelGraphics);
        tabbedPane.setSelectedIndex(1);
        this.add(tabbedPane, constraints);

        /* Play button */
        constraints.gridy = 2;
        constraints.insets = new Insets(COMPONENT_PADDING, COMPONENT_PADDING, COMPONENT_PADDING, COMPONENT_PADDING);
        constraints.weighty = 0;
        this.add(buttonPlay, constraints);

        this.setVisible(true);
    }

    @Override
    public final void setAvailableScreens(final int screenCount) {
        for (int i = 1; i <= screenCount; i++) {
            comboBoxScreen.addItem(i);
        }
    }

    @Override
    public final int getSelectedScreen() {
        return (int) comboBoxScreen.getSelectedIndex();
    }

    @Override
    public final void setSelectedScreen(final int index) {
        comboBoxScreen.setSelectedIndex(index);
    }

    @Override
    public final Dimension getResolution() {
        final int width = (int) textFieldWidth.getValue();
        final int height = (int) textFieldHeight.getValue();
        return new Dimension(width, height);
    }

    @Override
    public final void setResolution(final Dimension resolution) {
        textFieldWidth.setText(String.valueOf(resolution.width));
        textFieldHeight.setText(String.valueOf(resolution.height));
    }

    @Override
    public final boolean isFullscreen() {
        return checkBoxFullscreen.isSelected();
    }

    @Override
    public final void setFullscreen(final boolean fullscreen) {
        checkBoxFullscreen.setSelected(fullscreen);
    }

    @Override
    public final int getVolume() {
        return sliderVolume.getValue();
    }

    @Override
    public final void setVolume(final int volume) {
        sliderVolume.setValue(volume);
    }

    @Override
    public final void setPlayListener(final ActionListener listener) {
        buttonPlay.addActionListener(listener);
    }

    @Override
    public final void setVisibility(final boolean visibility) {
        this.setVisible(visibility);
    }

}
