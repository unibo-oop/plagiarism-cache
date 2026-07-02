package it.dpg.minigames.molegame.controller;

import it.dpg.minigames.molegame.model.*;
import it.dpg.minigames.molegame.view.HitTheMoleView;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HitTheMoleCycleImpl implements HitTheMoleCycle {

    private final static int NMOLES = 25;
    private List<Pair<Integer, Mole>> moleList = new ArrayList<>();
    private List<Pair<Integer, Mole>> moleOut = new ArrayList<>();
    private Score score = new ScoreImpl();
    private Timer timer = new TimerImpl();
    private HitTheMoleView moleView;
    private boolean isStartClick = false;


    public HitTheMoleCycleImpl() {
        for (int i = 0; i < NMOLES; i++) {
            moleList.add(new Pair<>(i, new MoleImpl()));
        }
    }

    @Override
    public int startCycle() {

        waitForTheStart();

        timer.timeStart();
        while (!timer.checkTimeIsUp()) {

            moleOutOrIn();
            updateView();
            moleOutOrIn();
            updateView();


            waitTime();


        }
        moleView.closeView();
        return score.finalScore();
    }

    private synchronized void waitForTheStart() {
        while (!isStartClick) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void waitTime() {
        try {
            wait(650);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * check if the mole press is out or in
     */
    @Override
    public void pressOnAMole(int whichMole) {
        if (moleList.get(whichMole).getValue().isOut()) {
            score.addPoint();

            removeMole(whichMole);

        }

    }

    private void removeMole(int whichMole) {
        moleList.get(whichMole).getValue().setMoleIn();

        int c = 0;
        for (var s : moleOut) {
            if (s.getKey() == whichMole) {
                moleOut.remove(c);
                break;
            }
            c++;
        }
    }

    /**
     * push out or in a new mole random
     */
    @Override
    public void moleOutOrIn() {
        Random r = new Random();
        int i = r.nextInt(25);
        if (!moleList.get(i).getValue().isOut()) {
            moleList.get(i).getValue().setMoleOut();
            moleOut.add(new Pair<>(i, moleList.get(i).getValue()));
        } else {

            removeMole(i);
        }
    }

    /**
     * update the view
     */
    @Override
    public void updateView() {
        moleView.updateTimer(timer.getRemainTime());
        moleView.updateScore(score);
        moleView.updateMole(moleOut);
    }

    /**
     * start the gamecycle when button start is clicked on the view
     */
    @Override
    public synchronized void startGame() {
        this.isStartClick = true;
        notifyAll();
    }

    /**
     * set the view
     *
     * @param view is the view pass from the GUI
     */
    @Override
    public void setView(HitTheMoleView view) {
        moleView = view;
    }

}
