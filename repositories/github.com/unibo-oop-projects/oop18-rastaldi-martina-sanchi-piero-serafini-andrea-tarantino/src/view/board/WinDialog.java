package view.board;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

import controller.Controller;
import controller.ControllerImpl;
import view.board.sideview.MyButton;

/**
 *
 * Piero Sanchi. This class is a dialog displayed when someone wins a game.
 *
 */
public final class WinDialog extends JDialog implements WinDialogInterface {

    private static final long serialVersionUID = 1L;
    private static final int TIMER_DELAY = 75;
    private static final int FONT = 15;
    private static final int TIMERCONST = 5;
    private final Controller controller = ControllerImpl.getLog();
    private final JPanel upPanel = new JPanel();
    private final JPanel midPanel = new JPanel();
    private final JPanel downPanel = new JPanel();
    private final JLabel congrat = new JLabel("Congratulazioni, hai vinto!");
    private final JTextArea rank = new JTextArea();
    private final JButton okButton = new MyButton("Nuova partita");
    private final Color color = new Color(180, 180, 180);
    private final Color tAreaColor = new Color(140, 140, 140);
    private final Dimension dimension = new Dimension(500, 300);
    private final Timer timer = new Timer(TIMER_DELAY, new ActionListener() {
        private int c = 1;

        @Override
        public void actionPerformed(final ActionEvent arg0) {
            WinDialog.this.congrat.setForeground(Color.getHSBColor((float) this.c / 360, 1f, 1f));
            WinDialog.this.congrat.repaint();
            this.c = (this.c >= 360) ? 1 : this.c + TIMERCONST;
        }
    });

    /**
     * Constructor of WinDialog.
     */
    public WinDialog() {
        super();
        this.setMinimumSize(this.dimension);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
                WinDialog.this.controller.stopWin();
                System.exit(0);
            }
        });
        this.setVisible(true);
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(this.upPanel, BorderLayout.NORTH);
        this.getContentPane().add(this.midPanel, BorderLayout.CENTER);
        this.getContentPane().add(this.downPanel, BorderLayout.SOUTH);
        this.upPanel.add(this.congrat);
        this.midPanel.add(this.rank);
        this.downPanel.add(this.okButton);
        this.upPanel.setBackground(this.color);
        this.midPanel.setBackground(this.color);
        this.downPanel.setBackground(this.color);
        this.congrat.setFont(new Font("Tahoma", Font.BOLD, FONT * 2));
        this.rank.setBackground(this.tAreaColor);
        this.rank.setFont(new Font("Thaoma", Font.BOLD, FONT));
        this.rank.setForeground(Color.BLACK);
        this.rank.setEditable(false);

        this.okButton.addActionListener(e -> {
            this.controller.stopWin();
            WinDialog.this.timer.stop();
            WinDialog.this.dispose();
            ControllerImpl.getLog().reset();
        });

        this.timer.start();
        this.pack();
    }

    @Override
    public void setRank(final String t) {
        this.rank.setText(t);
    }

}
