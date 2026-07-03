package laterunner.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import laterunner.model.shop.Shop;
import laterunner.model.user.User;

/**
 * Market class provides the possibility of buy speed and lives in order to move ahead in the game.
 *
 */
public class Market extends PanelImpl {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Font fontChalkDash;
    private static final float SIZE = 48;
    private static final int MAXSPEED = 12000;
    private JLabel lifeCost;
    private JLabel moneyCost;
    private JButton life;
    private JButton coin;

    /**
     * Common Market constructor. Sets the panel's layout and creates some buttons in order to display
     * every info about lives and money.
     */
    public Market() {

        this.setLayout(new GridLayout(4, 2));
        fontChalkDash = super.createFont("Digital Dot Roadsign.otf", SIZE);
        Box boxL = Box.createHorizontalBox();
        Box boxS = Box.createHorizontalBox();

        //Back button
        JButton back = super.createButton(super.getPics().getIcon(Icons.BACK));
        back.addActionListener(e -> {
            SceneImpl.changePanel("menu");
        });

        //Buy life button
        JButton buyLife = super.createButton(super.getPics().getIcon(Icons.BUY_LIFE));
        buyLife.addActionListener(e -> {
            Shop.getShop().buyLife();
        });

        //Buy speed button
        JButton buySpeed = super.createButton(super.getPics().getIcon(Icons.BUY_SPEED));
        buySpeed.addActionListener(e -> {
            Shop.getShop().buySpeed();
        });

        //Life button
        this.life = super.createButton(super.getPics().getIcon(Icons.HEART));
        life.setText("" + User.getUser().getUserLives());
        life.setFont(fontChalkDash);
        life.setHorizontalTextPosition(SwingConstants.CENTER);

        //Coin button
        this.coin = super.createButton(super.getPics().getIcon(Icons.COIN));
        coin.setText("" + User.getUser().getMoney());
        coin.setFont(fontChalkDash);
        coin.setHorizontalTextPosition(SwingConstants.CENTER);

        JLabel lifeR = new JLabel("     Your lives:");
        lifeR.setFont(fontChalkDash);
        lifeR.setForeground(Color.WHITE);
        boxL.add(lifeR);
        boxL.add(life);
        JLabel moneyR = new JLabel("    Your money:");
        moneyR.setFont(fontChalkDash);
        moneyR.setForeground(Color.WHITE);
        boxS.add(moneyR);
        boxS.add(coin);

        //Life and Money costs
        lifeCost = new JLabel("         Life Cost: " + Shop.getShop().getLifeCost());
        lifeCost.setFont(fontChalkDash);
        lifeCost.setForeground(Color.WHITE);
        moneyCost = new JLabel("        Speed Cost: " + Shop.getShop().getSpeedCost());
        moneyCost.setFont(fontChalkDash);
        moneyCost.setForeground(Color.WHITE);

        this.add(boxL);
        this.add(boxS);
        this.add(lifeCost);
        this.add(moneyCost);
        this.add(buyLife);
        this.add(buySpeed);
        this.add(back);

    }

    @Override
    protected void paintComponent(final Graphics g) {

        g.drawImage(super.getPics().getImage(Icons.BACKGROUND), 0, 0, null);
        this.life.setText("" + User.getUser().getUserLives());
        this.lifeCost.setText("         Life Cost: " + Shop.getShop().getLifeCost());
        this.coin.setText("" + User.getUser().getMoney());
        if (Shop.getShop().getSpeedCost() >= MAXSPEED) {
            this.moneyCost.setText("        Speed Max");
        } else {
            this.moneyCost.setText("        Speed Cost: " + Shop.getShop().getSpeedCost());
        }

    }

}
