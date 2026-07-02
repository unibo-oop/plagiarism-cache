package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import application.StealthNinja;

/**
 * 
 * Creates windows for scores.
 *
 */
public class WindowScores implements UserInterface {

    /**
     * Separator that it's portable.
     */
    public static final String SEP = File.separator;

    private final MyFrame frame;

    private static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

    /**
     * File that shows scores.
     */
    public static final String FILE_NAME_SCORES = System.getProperty("user.home") + SEP + "scores";

    /**
     * Constructor for WindowScores.
     */
    public WindowScores() {
        this.frame = new MyFrame(new BorderLayout(), SCREEN_WIDTH / 3, SCREEN_HEIGHT / 3);
    }

    @Override
    public final void createWindow() {
        final JPanel p = new JPanel(new BorderLayout());
        final JPanel pInternal = new JPanel(new BorderLayout());
        final JButton back = new JButton("Go Back");
        final JButton reset = new JButton("Reset scores");
        final JTextArea out = new JTextArea();

        final JScrollPane scroll = new JScrollPane(out);
        setButton(back);
        setButton(reset);
        out.setBackground(Color.black);
        out.setForeground(Color.white);
        out.setOpaque(true);
        out.setFont(new Font("Impact", Font.PLAIN, SCREEN_WIDTH / 65));
        out.setEditable(false);

        StealthNinja.GUICONTROLLER.showScores(out);

        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                frame.dispose();
                StealthNinja.GUICONTROLLER.goBackButton();
                StealthNinja.GUICONTROLLER.writeName(StealthNinja.GUICONTROLLER.getUserInterface().getJTextField());
            }
        });

        reset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                frame.dispose();
                StealthNinja.GUICONTROLLER.goBackButton();
                final File f = new File(FILE_NAME_SCORES);
                try {
                    f.delete();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                StealthNinja.GUICONTROLLER.writeName(StealthNinja.GUICONTROLLER.getUserInterface().getJTextField());

            }

        });

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        p.add(scroll, BorderLayout.CENTER);
        p.add(pInternal, BorderLayout.SOUTH);
        pInternal.add(back, BorderLayout.CENTER);
        pInternal.add(reset, BorderLayout.EAST);
        frame.getMainPanel().add(p);
        frame.setUndecorated(true);
    }

    private void setButton(final JButton button) {
        button.setBackground(Color.gray);
        button.setForeground(Color.white);
        button.setOpaque(true);
        button.setFont(new Font("Impact", Font.PLAIN, SCREEN_WIDTH / 85));
    }

    @Override
    public final void setDimensions(final int x, final int y) {
        this.frame.setSize(x, y);
    }

    @Override
    public final void show() {
        this.frame.setVisible(true);
    }
}
