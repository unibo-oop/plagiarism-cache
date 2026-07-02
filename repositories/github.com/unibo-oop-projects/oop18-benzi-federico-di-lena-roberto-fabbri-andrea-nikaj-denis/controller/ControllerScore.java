package controller;


public class ControllerScore {

    double punteggio = 0;

    public void setscore(double d) {
        punteggio += d;
    }

    public double getscore() {
        return (int) punteggio;
    }

}
