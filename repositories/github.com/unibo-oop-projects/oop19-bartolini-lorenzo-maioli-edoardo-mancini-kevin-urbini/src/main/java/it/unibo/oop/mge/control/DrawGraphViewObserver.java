package it.unibo.oop.mge.control;

public interface DrawGraphViewObserver {
    void newGraph(String function, double min, double max, double rate);

    void load(String path);

    void save(String path);

    void zoomIn();

    void zoomOut();

    void moveUp();

    void moveLeft();

    void moveRight();

    void moveDown();

    void increaseXY();

    void decreaseXY();

    void increaseYZ();

    void decreaseYZ();

    void quit();
}
