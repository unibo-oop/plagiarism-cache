package controller;

import model.block.Block;
import javafx.fxml.FXML;
import javafx.scene.Node;
import java.util.Map.Entry;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.*;
import java.util.*;
import javafx.geometry.*;
import model.block.BlockImpl;
import model.block.BlockView;

/**
 * The Controller related to the blockPage.fxml GUI.
 *
 */
public final class BLKController {

    @FXML
    private GridPane Grid;

    @FXML
    private Label lblPoint;

    @FXML
    private Label lblTopScore;

    /*Random value of Block*/
    private Random rand = new Random();

    /*Max number of Block in one line*/
    private final int maxBlockOnLine = 6;

    /*Max value in a box*/
    private final int maxValue = 98;

    /*Standard number of lines*/
    private final int stndLineNumber = 3;

    /*My Map*/
    private Map<BlockView, Pair<Integer,Integer>> myBlock = new HashMap<>();

    public void initialize(){

        //this.setSpace();

        myBlock = this.createMap();

        this.setGrid(myBlock);

        this.setupColumn();
    }

    public void setSpace (){

        /*NB. Set BlockSize at 46 (just 50 - 4 of padding) if use this method*/

        Grid.setHgap(4); //horizontal gap in pixels
        Grid.setVgap(4); //vertical gap in pixels

        Grid.setPadding(new Insets(4, 4, 4, 4));
    }

    public void setupColumn(){

        for(int i= 0; i<= maxBlockOnLine+1; i++) {
            /*Setup for each block*/
            ColumnConstraints cc = new ColumnConstraints(new BlockImpl().getBlockSize());
            Grid.getColumnConstraints().add(cc);
            /*RowConstraints rc = new RowConstraints(new Block().getBlockSize());
            Grid.getRowConstraints().add(rc);*/
        }

    }

    public BlockView newBlock(int x, int y, int n) {

        final BlockView block = new BlockView(x, y, n);

        if(n<20){ block.setStyle("-fx-border-width: 3; -fx-border-color: red; -fx-text-fill: red; -fx-font-size: 32.5;"); }
        else if (n<40){ block.setStyle("-fx-border-width: 3; -fx-border-color: yellow; -fx-text-fill: yellow; -fx-font-size: 32.5;"); }
        else if (n<60){ block.setStyle("-fx-border-width: 3; -fx-border-color: blue; -fx-text-fill: blue; -fx-font-size: 32.5;"); }
        else block.setStyle("-fx-border-width: 3; -fx-border-color: green; -fx-text-fill: green; -fx-font-size: 32.5;");

        block.setText(Integer.toString(n));
        block.setId(x+","+y);

        /*Questo controllo ci permette di aggiungere la riga iniziale vuota
        * e mettere random i blocchi in una riga*/

        if(x == 0 || rand.nextInt(1000)%2 == 0) block.setVisible(false);

        block.setMaxWidth(Double.MAX_VALUE);
        block.setAlignment(Pos.CENTER);

        return block;
    }

    public void addBlock(BlockView blc) {
        /*Button btn = new Button();

        btn.setStyle("-fx-border-width: 3;-fx-background-color: transparent; -fx-border-color: red; -fx-text-fill: red; -fx-font-size: 20;");

        btn.setText(Integer.toString(5));

        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setAlignment(Pos.CENTER);*/

        Grid.add(blc, blc.getBi().getPosy(), blc.getBi().getPosx());

        /*Grid setup*/
        //Grid.setFillWidth(blc, true);
    }

    public List<BlockView> newBlockLine (int x) {
        List<BlockView> tmpLine = new ArrayList<>();

        /*Random value of Block*/
        Random rand = new Random();

        /*Value of the Block*/
        int n = rand.nextInt(maxValue)+1;

        for(int y = 0; y <= maxBlockOnLine; y++){
            tmpLine.add(
                    this.newBlock(x, y, n));
        }

        return tmpLine;
    }

    public Map<BlockView, Pair<Integer,Integer>> createMap(){

        Map<BlockView, Pair<Integer,Integer>> tmpMap = new HashMap<>();

        /*Value of the Block*/
        int n = rand.nextInt(maxValue)+1;

        for(int x = 1; x <= stndLineNumber; x++) {
            for (int y = 0; y <= maxBlockOnLine; y++) {
                BlockView block = this.newBlock(x, y, n);
                tmpMap.put(
                        block,
                        new Pair<>(x, y));
            }

            n = rand.nextInt(maxValue)+1;
        }

        return tmpMap;
    }

    public void setGrid(Map<BlockView, Pair<Integer,Integer>> map){

        /*Puliamo la Grid*/
        Grid.getChildren().clear();

        this.EmptyLine();

        for (Entry<BlockView, Pair<Integer, Integer>> entry : map.entrySet()){
            this.addBlock(entry.getKey());
        }

    }

    public void addNewBlockLine (List<BlockView> list){

        Map<BlockView, Pair<Integer,Integer>> tmpMap = new HashMap<>();

        for(BlockView tmp : list) {
            tmpMap.put(tmp, new Pair<>(tmp.getBi().getPosx(), tmp.getBi().getPosy()));
            System.out.println("value: "+tmp.getNumber()+", x: "+tmp.getBi().getPosx()+", y: "+tmp.getBi().getPosy());
        }

        for (Entry<BlockView, Pair<Integer, Integer>> entry : myBlock.entrySet()){
            entry.getKey().getBi().incPosx();
            tmpMap.put(entry.getKey(), new Pair<>(entry.getValue().getKey()+1, entry.getValue().getValue()));
        }

        this.myBlock = tmpMap;
    }

    public void EmptyLine(){

        for(int i = 0; i<= maxBlockOnLine; i++) {
            BlockView blc = this.newBlock(0, i, 0);
            this.addBlock(blc);
        }

    }

    // Method for finding Block in Gridpane
    public Block getBlock(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return (Block) node;
            }
        }
        return null;
    }

    public void incrementPointLabel(){
        this.lblPoint.setText(
                Integer.toString(Integer.parseInt(this.lblPoint.getText())+1)
        );
    }

    public void clickPause(MouseEvent mouseEvent) {
        this.addNewBlockLine(this.newBlockLine(1));
        this.setGrid(this.myBlock);

        //getBlock(this.Grid, 2, 2).getHit();

        //this.incrementPointLabel();
    }

    public void clickHelp(MouseEvent mouseEvent) {

    }
}