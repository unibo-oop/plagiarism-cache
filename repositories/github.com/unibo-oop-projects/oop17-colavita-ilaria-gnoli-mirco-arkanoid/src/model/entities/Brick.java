package model.entities;

import javafx.util.Pair;

public class Brick implements IEntity {

    int x;
    int y;
    int lenght;
    int height;
    
    public Brick(int brickWidth, int d) {
        this.lenght = brickWidth;
        this.height = d;
    }

    public Brick(int i, int j, int k, int l) {
        x = i;
        y = j;
        lenght = k;
        height = l;
    }

    @Override
    public int getMaxX() {
        // TODO Auto-generated method stub
        return x + lenght;
    }

    @Override
    public int getMinX() {
        // TODO Auto-generated method stub
        return x;
    }

    @Override
    public int getMaxY() {
        // TODO Auto-generated method stub
        return y+height;
    }

    @Override
    public int getMinY() {
        // TODO Auto-generated method stub
        return y;
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        // TODO Auto-generated method stub
        return new Pair<>(x,y);
    }

    @Override
    public void setPosition(int newX, int newY) {
        x = newX;
        y = newY;

    }

    public boolean isIndistruttibile() {
        // TODO Auto-generated method stub
        return false;
    }

    public int getWidth() {
        // TODO Auto-generated method stub
        return lenght;
    }

    public int getHeight() {
        // TODO Auto-generated method stub
        return height;
    }

}
