package vg.view.utils;

import vg.view.View;

public interface CountdownView<T> extends View<T> {
    void setCountdown(int time);
}
