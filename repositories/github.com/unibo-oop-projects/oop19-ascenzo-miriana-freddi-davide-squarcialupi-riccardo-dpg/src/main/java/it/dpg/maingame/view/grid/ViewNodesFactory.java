package it.dpg.maingame.view.grid;

import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;
import java.util.Random;
import java.util.Set;

public class ViewNodesFactory {

    public Circle generateCell(Color color) {
        Circle circle = new Circle(40);
        circle.setFill(color);

        return circle;
    }

    public Rectangle generatePlayer(Integer player) {
        Rectangle square = new Rectangle(30, 30);

        switch (player) {
            case 0:
                square.setFill(Color.LIGHTGREY);
                break;
            case 1:
                square.setFill(Color.LIGHTPINK);
                break;
            case 2:
                square.setFill(Color.BLUEVIOLET);
                break;
            case 3:
                square.setFill(Color.YELLOW);
                break;
            case 4:
                square.setFill(Color.DARKGREY);
                break;
            default:
                Random rand = new Random();     //for each player that exists, a new random color is generated and given to the rectangle


                float r = rand.nextFloat();
                float g = rand.nextFloat();
                float b = rand.nextFloat();

                Color color = new Color(r, g, b, 1);
                square.setFill(color);
                break;
        }
        return square;
    }

    public Group generateLines(Map<StackPane, Set<Pair<Integer, Integer>>> cellsList, double modifierX, double modifierY) {

        Group linesGroup = new Group();
        cellsList.forEach((key, value) -> {
            for (var j : value) {
                Line line = new Line();
                line.setStroke(Color.FORESTGREEN);
                line.setStrokeWidth(10);
                line.setStartX(key.getLayoutX() + modifierX / 3.3);
                line.setStartY(key.getLayoutY() + modifierX / 3.3);
                line.setEndX(j.getLeft() * modifierX + modifierX / 3.3);
                line.setEndY(j.getRight() * modifierY + modifierX / 3.3);
                linesGroup.getChildren().add(line);
            }
        });

        return linesGroup;

    }
}
