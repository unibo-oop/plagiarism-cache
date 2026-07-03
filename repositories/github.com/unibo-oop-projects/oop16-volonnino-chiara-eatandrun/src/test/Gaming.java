package test;


import java.awt.AWTException;

/***
 * Heart of the application 
 */

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import base.Game;
import game.StaticImage;
import game.Score;
import game.Hostile;
import game.Players;
import game.Point;

public class Gaming extends Game { 
    
        private static final int FONDO_WIDTH = 800;
        private static final int FONDO_HIGH = 600;
        private static final int SPEED_COND = 30;
    
        private StaticImage fondo;
        private static int speeds = 20; // velocità iniziale pac
        private Hostile[] hostiles;
        private int count;
        private Players players;
        private StaticImage gameover;
        private Point point;
        private Score score;
        
        public Gaming() {
                super("Eat and Run!", FONDO_WIDTH, FONDO_HIGH);
                
                inicialization();
                
                 start();
        }
        
        public void inicialization() {
                
                fondo = new StaticImage("/res/fondo.jpg",0,0,FONDO_WIDTH,FONDO_HIGH);
                area.add(fondo);
                players = new Players("/res/pacV.png",20,20,60,0,0);
                hostiles = new Hostile[100];
                count = 0;
                point = new Point("/res/point.png",(int) ((Math.random()*600)+10),(int) ((Math.random()*400)+10),(int) ((Math.random()*40)+40),(int) (Math.random()*10+1),(int) (Math.random()*10+1));
               
                /* Adds hostile to hostile's array (the method is getColorHostile, returns a image ) */
                while(count < 4) {
                        add(new Hostile(getColorHostile(),(int) ((Math.random()*600)+50),(int) ((Math.random()*400)+50),(int) ((Math.random()*40)+40),(int) ((Math.random()*10)+10),(int) ((Math.random()*10)+10)));
                }
                score = new Score(10,20);
                
                area.add(point);
                area.add(players);
                area.add(score);
                
                /* This method adds hostile in hostile's array in the area */
                areaAddEnemics();
                
                time.add(players);
                time.add(point);
                time.add(score);
                
                /* This method adds hostile in hostile's array in a timer */
                tempAddEnemics();
        }
        
        private String getColorHostile() {
                int num=(int) (4 * Math.random());
                String color = "/res/rosso.PNG";
                switch (num) {
                case 1:
                        color = "/res/rosso.PNG";
                        break;
                case 2:
                        color = "/res/azzurro.PNG";
                        break;
                case 3:
                        color = "/res/arancio.PNG";
                        break;
                case 0:
                        color = "/res/rosa.PNG";
                        break;
                }
                return color;
        }
        

        public void add(Hostile hostile) {
               
        hostiles[count] = hostile;
        count++;
    }
        
        public void areaAddEnemics() {
                
        for (int i=0; i<count; i++) {
            area.add(hostiles[i]);
        }
    }
        
        public void tempAddEnemics() {
               
        for (int i=0; i<count; i++) {
            time.add(hostiles[i]);
        }
    }
        
        public static int getSpeed() {
                return speeds;
        }
        public static void setSpeed(int speed) {
                Gaming.speeds = speed;
        }
        
        @Override
        public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_UP) {
                        if(getSpeed() < SPEED_COND){
                                        setSpeed(getSpeed() + 1);
                        }
                        players.setSpeeds(0, -getSpeed());
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                        if(getSpeed() < SPEED_COND){
                                setSpeed(getSpeed() + 1);
                }
                        players.setSpeeds(0, getSpeed());
                }
                if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                        if(getSpeed() < SPEED_COND){
                                setSpeed(getSpeed() + 1);
                        }
                        players.setSpeeds(-getSpeed(), 0);
                }
                if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        if(getSpeed() < SPEED_COND){
                                setSpeed(getSpeed() + 1);
                        }
                        players.setSpeeds(getSpeed(), 0);
                }
                if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                        if(players.getCrash()) {
                                gameover.setVisible(false);
                                players.setCrash(false);
                                speeds = 20;
                                time.restart();
                                area.clears();
                                time.clears();
                                inicialization();
                                area.repaint();
                        }
                }
        }

        @Override
        public void check() {
                
                Rectangle rect = new Rectangle(players.getX(),players.getY(),players.getLen(),players.getLen());

                Rectangle[] rectangles;
                rectangles = new Rectangle[count];
                for (int i=0; i<count;i++) {
                        rectangles[i] = new Rectangle(hostiles[i].getX(),hostiles[i].getY(),hostiles[i].getLen(),hostiles[i].getLen());
                        if(rect.intersects(rectangles[i])) {
                                if(!players.isInvulnerable()) {
                                        players.setCrash(true);
                                }       
                        }
        }
                Rectangle rectp = new Rectangle(point.getX(),point.getY(),point.getLen(),point.getLen());
                if(rect.intersects(rectp)) {
                        point.setX((int) ((Math.random()*600)+10));
                        point.setX((int) ((Math.random()*400)+10));
                        point.setSpeedX((int) ((Math.random()*10)));
                        point.setSpeedY((int) ((Math.random()*10)));
                        score.setA(score.getA()+1);
                }
                // && puntuacion.getA()>0
                if((score.getA() % 5)==0) {
                        players.setInvulnerable(true);
                }else{
                        players.setInvulnerable(false);
                }
                if(players.getCrash()) {
                        time.stop();
                        gameover = new StaticImage("/res/gameover.png",150,200,500,200);
                        area.add(gameover);     
                }
        }

        public static void main(String[] args) throws AWTException, InterruptedException {
                new Gaming();  
        }


}