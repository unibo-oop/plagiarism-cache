package vg.model.score;

import java.io.Serializable;

public interface Score extends Serializable {
    int getScore();
    int getRound();
    String getName();
}
