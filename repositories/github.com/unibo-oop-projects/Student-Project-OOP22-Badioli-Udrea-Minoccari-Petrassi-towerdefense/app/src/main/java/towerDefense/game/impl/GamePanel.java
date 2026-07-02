package towerDefense.game.impl;

import javax.imageio.ImageIO;
import javax.swing.*;
import towerDefense.Constants;
import towerDefense.entities.impl.*;
import towerDefense.game.api.Panel;
import towerDefense.gameLogic.impl.GameLogicImpl;
import towerDefense.gameLogic.impl.GameLoop;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class GamePanel extends Panel{

    private TowerSingleton tower = TowerSingleton.getInstance();;
    private BufferedImage background;
    private BufferedImage castle;
    private JLabel money;
    private JLabel score;
    private JButton summonBarbarian;
    private JButton summonKnight;
    private JButton summonArcher;
    private JButton buildTurret;
    private FinalMove fMove = new FinalMove();
    private ActionListener finalMoveListener;
    private final int finalMoveCost = 80;
    private GameLoop gameLoop = new GameLoop();
    private GameLogicImpl gameLogicImpl = new GameLogicImpl();
    
    /**
     * Creates a new Game Panel, setting up its background image and all of its components
     */
    public GamePanel() {

        super.startSound(Constants.gamePanel);
        
        try{
            this.background = ImageIO.read(this.getClass().getResource("/Assets/Backgrounds/Game.jpg"));
            this.castle = ImageIO.read(this.getClass().getResource("/Assets/Backgrounds/castle.png"));
        }catch(Exception e){
            System.out.println("error loading background " + e.getMessage());
        }
        final JButton summonBarbarian = new JButton("Summon Barbarian $" + Barbarian.getCost());
        final JButton summonKnight=new JButton("Summon Knight $" + Knight.getCost());
        final JButton summonArcher = new JButton("Summon Archer $" + Archer.getCost());
        final JButton buildTurret = new JButton("Build Turret $" + Turret.getCost());
        final JButton finalMove = new JButton("Double allies $" + finalMoveCost);
        
        final Timer timer = new Timer(1000, new MyTimerListener(summonBarbarian, summonKnight, summonArcher, buildTurret));
        timer.setRepeats(false);

        final Timer timer2 = new Timer(20000, finalMoveListener);
        timer2.setRepeats(false);

        this.add(summonBarbarian);
        this.add(summonKnight);
        this.add(summonArcher);
        this.add(buildTurret);

        summonBarbarian.addActionListener((arg) -> {
            gameLogicImpl.summonEntity(Barbarian.getCost(), 1);
                if(fMove.isActive()) {
                    gameLogicImpl.summonfreeEntity(1);
                }
                disableButtons();
                timer.start();
        }); 

        summonKnight.addActionListener((arg) -> {
            gameLogicImpl.summonEntity(Knight.getCost(), 2);
                if(fMove.isActive()) {
                    gameLogicImpl.summonfreeEntity(2);
                }
                disableButtons();
                timer.start();
        });

        summonArcher.addActionListener((arg) ->  {
            gameLogicImpl.summonEntity(Archer.getCost(), 3);
                if(fMove.isActive()) {
                    gameLogicImpl.summonfreeEntity(3);
                }
                disableButtons();
                timer.start();
            });
        
        buildTurret.addActionListener((arg) -> {
            if( tower.buildTurret(Turret.getCost()) ){
                buildTurret.setVisible(false);
            }
            disableButtons();
            timer.start();
        });

        this.add(finalMove);
        ActionListener finalMoveListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tower.getMoney() >= finalMoveCost) {
                    fMove.trigger();
                    finalMove.setEnabled(false);
                    timer2.start();
                }
            }
        };
        this.finalMoveListener = finalMoveListener;
        finalMove.addActionListener(finalMoveListener);

        JButton surrender = new JButton("Surrender");
        surrender.addActionListener((arg) -> {
            stopMusic();
            GameImpl.setCurrentPanel(new EndPanel());
        });
        this.add(surrender);

        this.money = new JLabel();
        money.setText("$" + String.valueOf(tower.getMoney()));
        this.add(money);

        this.score = new JLabel();
        score.setText("SCORE: " + tower.getScore());
        this.add(score);

        this.summonBarbarian = summonBarbarian;
        this.summonKnight = summonKnight;
        this.summonArcher = summonArcher;
        this.buildTurret = buildTurret;
    }

    private static class MyTimerListener implements ActionListener {
        JComponent barbarian;
        JComponent knight;
        JComponent archer;
        JComponent turret;

        private MyTimerListener(JComponent barbarian, JComponent knight, JComponent archer, JComponent turret) {
            this.barbarian=barbarian;
            this.knight=knight;
            this.archer=archer;
            this.turret=turret;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            barbarian.setEnabled(true);
            knight.setEnabled(true);
            archer.setEnabled(true);
            turret.setEnabled(true);
        }

    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(){
        this.money.setText("$" + String.valueOf(tower.getMoney()));
        this.score.setText("SCORE: " + tower.getScore());
    }
   
    /**
     * Draws the background images along with the Tower health bar
     * @param g 
     *        graphic object used to draw all the components
     */
    public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(background, 0, 0, null);
            g.drawImage(castle,-180,430,null);
            gameLoop.draw(g);
            this.drawHealtBar(g);
        }

    private void drawHealtBar(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(50, 600, 100, 20);
        g.setColor(Color.GREEN);
        g.fillRect(50, 600, (int)(100*((float)tower.getHp()/(float)tower.getMaxHp())), 20);
    } 
    
    private void disableButtons() {
        summonBarbarian.setEnabled(false);
        summonKnight.setEnabled(false);
        summonArcher.setEnabled(false);
        buildTurret.setEnabled(false);
    }
}
