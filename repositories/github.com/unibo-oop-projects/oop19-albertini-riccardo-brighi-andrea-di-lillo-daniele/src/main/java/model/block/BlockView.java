package model.block;

import javafx.scene.control.Label;

public class BlockView extends Label {

    private int number;
    private BlockImpl bi;

    public BlockView(int x, int y, int number) { bi = new BlockImpl(x, y); this.number = number; }

    public BlockImpl getBi() { return this.bi; }

    public int getNumber() { return number; }

    public void setNumber(int number) { this.number = number; }

    public void deleteBlock() {
        if(this.number == 0)
            this.setVisible(false);
    }

    public void getHit() {
        this.number--;
        this.setText(Integer.toString(this.number));
        this.deleteBlock();
    }

}
