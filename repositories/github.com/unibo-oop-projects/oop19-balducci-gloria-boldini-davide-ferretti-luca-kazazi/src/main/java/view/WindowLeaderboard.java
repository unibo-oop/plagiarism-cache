package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import application.StealthNinja;

/**
 * 
 * Create window to show the leaderboard.
 *
 */
public class WindowLeaderboard implements UserInterface {

    private final MyFrame frame;
    private static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

    /**
     * Constructor for WindowLeaderboard.
     */
    public WindowLeaderboard() {
        this.frame = new MyFrame(new BorderLayout(), SCREEN_WIDTH / 3, SCREEN_HEIGHT / 3);
    }

    @Override
    public final void createWindow() {
        final JPanel p = new JPanel(new BorderLayout());
        final JButton back = new JButton("Go Back");
        final JTextArea out = new JTextArea();

        JScrollPane scroll = new JScrollPane(out);
        setButton(back);
        out.setBackground(Color.black);
        out.setForeground(Color.white);
        out.setOpaque(true);
        out.setFont(new Font("Impact", Font.PLAIN, SCREEN_WIDTH / 65));
        out.setEditable(false);

        StealthNinja.GUICONTROLLER.showLeaderboard(out);

        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                frame.dispose();
                StealthNinja.GUICONTROLLER.goBackButton();
                StealthNinja.GUICONTROLLER.writeName(StealthNinja.GUICONTROLLER.getUserInterface().getJTextField());

            }
        });

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame.getMainPanel().add(p);
        p.add(scroll, BorderLayout.CENTER);
        p.add(back, BorderLayout.SOUTH);
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
