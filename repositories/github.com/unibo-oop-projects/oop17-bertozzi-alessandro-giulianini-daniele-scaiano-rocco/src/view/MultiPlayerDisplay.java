package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

import game.Player;

import javax.swing.GroupLayout.Alignment;

/**
 * It displays the health bars for both players.
 */
public final class MultiPlayerDisplay extends HeadUpDisplay {
    /**
     * 
     */
    private static final long serialVersionUID = 6651265124487144524L;
    private final JTextField player1;
    private final JTextField player2;
    private final JProgressBar pBar1;
    private final JProgressBar pBar2;
    /**
     *
     */
    public MultiPlayerDisplay() {
        super();
        final GroupLayout group = new GroupLayout(this);
        this.setLayout(group);
        this.setBackground(Color.BLACK);
        this.setOpaque(false);
        this.player1 = this.setTextField();
        this.player1.setText("Player 1");
        this.player2 = this.setTextField();
        this.player2.setText("Player 2");
        this.pBar1 = this.setProgressBar();
        this.pBar2 = this.setProgressBar();
        group.setAutoCreateContainerGaps(true);
        group.setAutoCreateGaps(true);
        group.setHorizontalGroup(group.createSequentialGroup()
                .addContainerGap()
                .addGroup(group.createParallelGroup(Alignment.TRAILING)
                        .addComponent(this.player1, 0, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                        .addComponent(this.pBar1, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(group.createParallelGroup(Alignment.TRAILING)
                        .addComponent(this.player2, 0, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                        .addComponent(this.pBar2, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE))
                );
        group.setVerticalGroup(group.createSequentialGroup()
                .addGroup(group.createParallelGroup(Alignment.BASELINE)
                        .addComponent(this.player1, 0, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                        .addComponent(this.player2, 0, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE))
                .addGroup(group.createParallelGroup(Alignment.BASELINE)
                        .addComponent(this.pBar1, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                        .addComponent(this.pBar2, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE))
                );
    }

    @Override
    public void render(final List<Player> entity, final int score, final int level) {
        if (entity.get(0).getShield() > 0) {
            this.pBar1.setValue(entity.get(0).getShield());
            this.pBar1.setForeground(Color.BLUE);
        } else {
            this.pBar1.setValue(entity.get(0).getHealth());
            this.pBar1.setForeground(new Color((HeadUpDisplay.MAX_RGB_VALUE * (100 - pBar1.getValue())) / 100,
                    HeadUpDisplay.MAX_RGB_VALUE * pBar1.getValue() / 100, 0));
        }
        if (entity.get(1).getShield() > 0) {
            this.pBar2.setValue(entity.get(1).getShield());
            this.pBar2.setForeground(Color.BLUE);
        } else {
            this.pBar2.setValue(entity.get(1).getHealth());
            this.pBar2.setForeground(new Color((HeadUpDisplay.MAX_RGB_VALUE * (100 - pBar2.getValue())) / 100,
                    HeadUpDisplay.MAX_RGB_VALUE * pBar2.getValue() / 100, 0));
        }
    }
 
    /**
     * @return a new JProgressBar with specific properties
     */
    private JProgressBar setProgressBar() {
        final JProgressBar temp = new JProgressBar(0, 100);
        temp.setValue(100);
        temp.setBackground(Color.BLACK);
        temp.setForeground(Color.GREEN);
        return temp;
    }

    /**
     * @return a new JTextField with specific properties
     */
    private JTextField setTextField() {
        final JTextField text = new JTextField(20);
        text.setFont(new Font("arial", 2, ViewImpl.SCREEN_HEIGHT / HeadUpDisplay.FONT_PROPORTION));
        text.setEditable(false);
        text.setForeground(Color.WHITE);
        text.setBorder(BorderFactory.createEmptyBorder());
        text.setOpaque(false);
        text.setHorizontalAlignment(SwingConstants.CENTER);
        return text;
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        player1.setFont(new Font("arial", 2, this.getParent().getHeight() / HeadUpDisplay.FONT_PROPORTION));
        player2.setFont(new Font("arial", 2, this.getParent().getHeight() / HeadUpDisplay.FONT_PROPORTION));
    }
}
