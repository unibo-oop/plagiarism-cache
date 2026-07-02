package model.common;

public final class Counter {

    private int current;

    public Counter() {
        this.current = 0;
    }

    public void increment() {
        this.current++;
    }

    public int getValue() {
        return current;

    }

}
