package it.unibo.bmbman.view;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import it.unibo.bmbman.view.utilities.GameFont;
import it.unibo.bmbman.view.utilities.ScreenToolUtils;

/**
 * Class used to generate components.
 */
public class GUIFactoryImpl implements GUIFactory {
    private static final double SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final double SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private static final double WINDOW_SCALE_WIDTH = 0.5;
    private static final double WINDOW_SCALE_HEIGHT = 0.66;
    private static final double SCALE = ScreenToolUtils.getScreenScale();
    private static final int INITIAL_POSITION = 100;
    /**
     * width value of frame.
     */
    public static final int FRAME_WIDTH = (int) (SCREEN_WIDTH * WINDOW_SCALE_WIDTH);
    /**
     * height value of frame.
     */
    public static final int FRAME_HEIGHT = (int) (SCREEN_HEIGHT * WINDOW_SCALE_HEIGHT);
    private final GameFont font  = new GameFont();
    /**
     * {@inheritDoc}
     */
    @Override
    public JButton createButton(final String text) {
        final JButton button = new JButton(text);
        button.setFont(font.getFont());
        button.setBackground(Color.BLACK);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);
        return button;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public JRadioButton createRadioButton(final String text) {
        final JRadioButton radioButton = new JRadioButton(text);
        radioButton.setFont(font.getFont());
        radioButton.setBackground(Color.BLACK);
        radioButton.setBorderPainted(false);
        radioButton.setFocusPainted(false);
        radioButton.setForeground(Color.WHITE);
        return radioButton;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public JFrame createFrame() {
        final JFrame frame = new JFrame();
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(INITIAL_POSITION, INITIAL_POSITION);
        return frame;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public JButton createReturnButton(final JFrame frame) {
        final JPanel southPanel = new JPanel();
        frame.add(southPanel, BorderLayout.SOUTH);
        southPanel.setBackground(Color.BLACK);
        final JButton back = createButton("RETURN TO MAIN MENU");
        back.setHorizontalTextPosition(SwingConstants.RIGHT);
        southPanel.add(back);
        return back;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Insets createScaledInsets(final Insets insets) {
        return new Insets((int) (insets.top * SCALE), (int) (insets.left * SCALE),
                (int) (insets.bottom * SCALE), (int) (insets.bottom * SCALE));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public JLabel createLabel(final String text) {
        final JLabel label = new JLabel(text);
        label.setFont(font.getFont());
        label.setBackground(Color.BLACK);
        label.setOpaque(true);
        label.setForeground(Color.WHITE);
        return label;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public JTextField createTextField() {
        final JTextField jTextField = new JTextField();
        jTextField.setFont(font.getFont());
        jTextField.setBackground(Color.BLACK);
        jTextField.setForeground(Color.WHITE);
        return jTextField;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public JFrame createFrameWithCanvas() {
        final JFrame frameWCanvas = createFrame();
        final Canvas canvas = new Canvas();
        canvas.setBackground(Color.BLACK);
        frameWCanvas.getContentPane().add(canvas);
        return frameWCanvas;
    }
}
