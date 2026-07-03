package controller.setting;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.Timer;
import javafx.application.Platform;
import utilities.Players;
import view.Chess;
import view.Items.TopBox;

/**
 * Abstract class that implement 2 timer
 * 
 * @author Alex Ravaglia
 * 
 */
public abstract class Time {

    private static final int MINUTI = 5;
    private static final long DURATION = (60000 * MINUTI);	
    final private Timer t1;
    final private Timer t2;	
    private long startTime1=-1; 
    private boolean init1=true;	
    private long clockTime1;
    private long stop1;
    private long startTime2=-1; 
    private boolean init2=true;	
    private long clockTime2;
    private long stop2;

    /**
     * the constructor create the 2 timer that are ready to start
     */
    public Time(){
        t1 = new Timer(100,new ActionListener(){
            public void actionPerformed(final ActionEvent e){
                if(startTime1<0){
                    startTime1 = System.currentTimeMillis();
                }
                final Long now1 = System.currentTimeMillis();
                clockTime1 = now1 - startTime1;       		
                if(clockTime1 >= DURATION){
                    clockTime1=DURATION;
                    t1.stop();
                    Platform.runLater(new Runnable(){
                        public void run(){
                            Chess.getLog().timeIsUp(Players.playerWhite);	                        
                        }
                    });

                }
                final SimpleDateFormat df = new SimpleDateFormat("mm:ss");
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        TopBox.getLog().setTextWhite(df.format(DURATION - clockTime1)); 
                    }
                });
            }       		
        });	

        t2 = new Timer(100,new ActionListener(){
            public void actionPerformed(final ActionEvent e){
                if(startTime2<0){
                    startTime2 = System.currentTimeMillis();
                }
                final long now2 = System.currentTimeMillis();
                clockTime2 = now2 - startTime2;
                if(clockTime2 >= DURATION){
                    clockTime2=DURATION;
                    t2.stop();
                    Platform.runLater(new Runnable(){
                        public void run(){
                            Chess.getLog().timeIsUp(Players.playerBlack);	                        
                        }
                    });
                }
                final SimpleDateFormat df = new SimpleDateFormat("mm:ss");     
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        TopBox.getLog().setTextBlack(df.format(DURATION - clockTime2)); 
                    }
                });
            }
        });	
    }

    /**
     * startTimer will implemented in function of the start time strategy 
     */
    public abstract void startTimer();

    /**
     * stopTimer will implemented in function of the start time strategy 
     */
    public abstract void stopTimer();

    /**
     * start the timer1, if it was running it resumes when it was stopped
     */
    protected void startTimer1(){
        if(!t1.isRunning() && init1){
            init1=false;
            startTime1=-1;
            t1.start();
        }else{
            startTime1 = System.currentTimeMillis() - stop1 + startTime1;
            t1.start();
        }
    }

    /**
     * start the timer1, if it was running it resumes when it was stopped
     */
    protected void startTimer2(){
        if(!t2.isRunning() && init2){
            init2=false;
            startTime2=-1;
            t2.start();
        }else{
            startTime2 = System.currentTimeMillis() - stop2 + startTime2;
            t2.start();
        }	
    }

    /**
     * stop the timer1 and start the timer 2
     */
    protected void stopTimer1(){
        stop1=System.currentTimeMillis();
        t1.stop();
        startTimer2();
    }

    /**
     * stop the timer 2 and start the time1
     */
    protected void stopTimer2(){
        stop2=System.currentTimeMillis();
        t2.stop();
        startTimer1();
    }
    
    /**
     * check if timer1 is running
     * @return true: if timer 1 is runninf, false otherwise
     */
    protected boolean t1IsRunning(){
        return t1.isRunning();
    }
}
