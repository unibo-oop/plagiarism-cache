package it.dpg.minigames.molegame.model;


public class TimerImpl implements Timer {

    private static long time = 0;
    private static final long MAX_T = 20000;//tempo max del gioco in millisecondi

    public TimerImpl(){}

    /**
     * function that start the timer for the minigame
     */
    @Override
    public void timeStart() {
        time = System.currentTimeMillis();
    }

    /**
     * funciton that check if the time is up
     */
    @Override
    public boolean checkTimeIsUp() {
        return System.currentTimeMillis() >= time + MAX_T;
    }

    /**
     * get the remain time
     *
     * @return the remain time
     */
    @Override
    public long getRemainTime() {
        if(time==0){
            return 0;
        }
        return (20 - (System.currentTimeMillis() - time) / 1000);
    }


}
