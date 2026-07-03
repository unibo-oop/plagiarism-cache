package laterunner.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import laterunner.model.user.User;

/**
 *Stats class displays all the info about user's game experience.
 *
 */
public class Stats extends PanelImpl {

    private static final long serialVersionUID = 1L;
    private static final float SIZE = 60;
    private Font fontChalkDash;
    private static String text;
    private static JLabel label = new JLabel();

    /**
     * Common Stats constructor: sets the panel's layout and draws the components over the background.
     */
    public Stats() {

        this.setLayout(new BorderLayout());
        fontChalkDash = super.createFont("Digital Dot Roadsign.otf", SIZE);

        //Back button
        JButton back = super.createButton(super.getPics().getIcon(Icons.BACK));
        back.addActionListener(e -> {
            SceneImpl.changePanel("menu");
        });

        label.setFont(fontChalkDash);
        label.setForeground(Color.WHITE);

        JPanel tmp = new JPanel();
        tmp.setOpaque(false);
        tmp.add(label);
        this.add(tmp, BorderLayout.CENTER);
        this.add(back, BorderLayout.PAGE_END);

        }

    @Override
    protected void paintComponent(final Graphics g) {
        g.drawImage(super.getPics().getImage(Icons.BACKGROUND), 0, 0, null);
        text = "<br>Games Played: " + User.Statistic.getStatistic().getGamesPlayed() + "<br>"
                + "Lives Lost: " + User.Statistic.getStatistic().getLostLives() + "<br>"
                + "Survival Highscore: " + User.Statistic.getStatistic().getSurvivalHighscore() + "<br>"
                + "Motorbike Hits: " + User.Statistic.getStatistic().getMotorbikeHits() + "<br>"
                + "Obstacle Car Hits: " + User.Statistic.getStatistic().getObstacleCarHits() + "<br>"
                + "Bus Hits: " + User.Statistic.getStatistic().getTruckHits();
        label.setText("<html><div style='text-align: center;'>" + text + "</div></html>");
    }

}

