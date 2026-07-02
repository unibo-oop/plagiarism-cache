package it.dpg.minigames.ballgame.view;

import it.dpg.minigames.ballgame.controller.BallMinigameLevel;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class NodesFactory {

    private final Function<Double, Double> mappingFunction;

    NodesFactory(Function<Double, Double> mappingFunction) {
        this.mappingFunction = mappingFunction;
    }

    private double map(double c) {
        return mappingFunction.apply(c);
    }

    private Polygon generatePolygon(List<Double> coordinates) {
        Polygon pol = new Polygon();
        boolean isX = true;
        for (Double d : coordinates) {
            double c;
            if (!isX) {
                c = map(100 - d);
            } else {
                c = map(d);
            }
            isX = !isX;
            pol.getPoints().add(c);
        }
        return pol;
    }

    private void addCouple(List<Double> list, double xCoord, double yCoord) {
        list.add(xCoord);
        list.add(yCoord);
    }

    private Rectangle generateRectangle(double xPos, double yPos, double width, double height, Paint fill) {
        Rectangle temp = new Rectangle(map(width), map(height), fill);
        temp.setX(map(xPos));
        temp.setY(map(100 - yPos));
        return temp;
    }

    public List<Node> createNodes(BallMinigameLevel level) {
        List<Node> temp = new ArrayList<>();
        if (level.equals(BallMinigameLevel.LEVEL1)) {
            List<Double> coords = new ArrayList<>();
            addCouple(coords, 0, 0);
            addCouple(coords, 0, 100);
            addCouple(coords, 30, 100);
            addCouple(coords, 30, 60);
            addCouple(coords, 80, 60);
            addCouple(coords, 80, 50);
            addCouple(coords, 65, 50);
            addCouple(coords, 65, 35);
            addCouple(coords, 50, 35);
            addCouple(coords, 50, 40);
            addCouple(coords, 10, 40);
            addCouple(coords, 10, 5);
            addCouple(coords, 25, 5);
            addCouple(coords, 25, 20);
            addCouple(coords, 80, 20);
            addCouple(coords, 80, 35);
            addCouple(coords, 95, 35);
            addCouple(coords, 95, 75);
            addCouple(coords, 45, 75);
            addCouple(coords, 45, 100);
            addCouple(coords, 72.5, 100);
            addCouple(coords, 72.5, 92.5);
            addCouple(coords, 100, 92.5);
            addCouple(coords, 100, 0);
            temp.add(generatePolygon(coords));

            temp.add(generateRectangle(27.5, 100, 2.5, 40, Color.RED));
            temp.add(generateRectangle(45, 100, 2.5, 25, Color.RED));
            temp.add(generateRectangle(25, 40, 5, 3, Color.RED));
            temp.add(generateRectangle(25, 23, 5, 3, Color.RED));
            temp.add(generateRectangle(62.5, 20, 20, 2.5, Color.RED));
            temp.add(generateRectangle(80, 35, 2.5, 15, Color.RED));
            temp.add(generateRectangle(82.5, 35, 15, 2.5, Color.RED));
            temp.add(generateRectangle(95, 60, 2.5, 25, Color.RED));
            temp.add(generateRectangle(77.5, 60, 2.5, 10, Color.RED));
            temp.add(generateRectangle(62.5, 52.5, 15, 2.5, Color.RED));
            temp.add(generateRectangle(62.5, 50, 2.5, 15, Color.RED));
            temp.add(generateRectangle(30, 100, 15, 10, Color.YELLOW));
        }
        return temp;
    }

    public Circle createBall(BallMinigameLevel level) {
        Circle c = new Circle(map(17.5), map(87.5), map(3), Color.RED);
        c.setId("ball_node");
        return c;
    }

    public Text createScore() {
        Text temp = new Text(map(75), map(5), "Score: 000");
        temp.setFont(new Font(map(4)));
        temp.setId("score_node");
        return temp;
    }

    public Text createReady() {
        Text temp = new Text(map(35), map(52), "ready?");
        temp.setFont(Font.font("Helvetica", FontWeight.BOLD, map(10)));
        temp.setFill(Color.DARKORANGE);
        temp.setId("ready_node");
        return temp;
    }

    public Text createGo() {
        Text temp = new Text(map(42), map(54), "GO!");
        temp.setFont(Font.font("Helvetica", FontWeight.BOLD, map(10)));
        temp.setFill(Color.DARKORANGE);
        temp.setId("go_node");
        return temp;
    }

    public Text createVictoryMessage() {
        Text temp = new Text(map(34), map(54), "GOAL!");
        temp.setFont(Font.font("Helvetica", FontWeight.BOLD, map(10)));
        temp.setFill(Color.DARKORANGE);
        temp.setId("victory_node");
        return temp;
    }
}
