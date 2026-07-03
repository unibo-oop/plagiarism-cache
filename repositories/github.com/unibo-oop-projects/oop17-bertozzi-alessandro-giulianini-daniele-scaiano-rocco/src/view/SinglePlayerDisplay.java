package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

import game.Player;

/**
 * It displays health-bar, score and level in singlePlayer mode.
 */
public final class SinglePlayerDisplay extends HeadUpDisplay {
    /**
     * 
     */
    private static final long serialVersionUID = -3360241022702503568L;
    private final JProgressBar pBar;
    private final JTextField score;
    private final JTextField level;

    /**
     *
     */
    public SinglePlayerDisplay() {
        super();
        final GroupLayout group = new GroupLayout(this);
        this.setLayout(group);
        this.setOpaque(false);
        this.pBar = new JProgressBar(0, 100);
        this.pBar.setValue(100);
        this.pBar.setBackground(Color.BLACK);
        this.pBar.setForeground(Color.GREEN);
        this.score = this.setTextFieldProperties();
        this.level = this.setTextFieldProperties();
        group.setAutoCreateContainerGaps(true);
        group.setAutoCreateGaps(true);
        group.setHorizontalGroup(group.createSequentialGroup()
                .addContainerGap()
                .addComponent(this.pBar, 0, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(group.createParallelGroup(Alignment.TRAILING)
                    .addComponent(this.score, 0, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                    .addComponent(this.level, 0, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE))
                );
        group.setVerticalGroup(group.createSequentialGroup()
                .addGroup(group.createParallelGroup(Alignment.BASELINE)
                        .addComponent(this.pBar, 0, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                        .addComponent(this.score, 0, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE))
                .addComponent(this.level, 0, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                );
    }
 
    @Override
    public void render(final List<Player> entity, final int score, final int level) {
        this.level.setText("Level " + level);
        this.score.setText(Integer.toString(score));
        if (entity.get(0).getShield() > 0) {
            this.pBar.setValue(entity.get(0).getShield());
            this.pBar.setForeground(Color.BLUE);
        } else {
            this.pBar.setValue(entity.get(0).getHealth());
            this.pBar.setForeground(new Color((HeadUpDisplay.MAX_RGB_VALUE * (100 - this.pBar.getValue())) / 100, 
                    HeadUpDisplay.MAX_RGB_VALUE * this.pBar.getValue() / 100, 0));
        }
    }

    /**
     * @return a new JTextField with specific properties
     */
    private JTextField setTextFieldProperties() {
        final JTextField text = new JTextField(10);
        text.setFont(new Font("arial", 2, ViewImpl.SCREEN_HEIGHT / HeadUpDisplay.FONT_PROPORTION));
        text.setEditable(false);
        text.setBorder(BorderFactory.createEmptyBorder());
        text.setOpaque(false);
        text.setForeground(Color.WHITE);
        text.setHorizontalAlignment(SwingConstants.RIGHT);
        return text;
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        score.setFont(new Font("arial", 2, this.getParent().getHeight() / HeadUpDisplay.FONT_PROPORTION));
        level.setFont(new Font("arial", 2, this.getParent().getHeight() / HeadUpDisplay.FONT_PROPORTION));
    }
}
