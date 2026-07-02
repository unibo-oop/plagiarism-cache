package view;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import model.ModelCostant;
import model.entities.IEntity;
import utility.Mapper;
import view.utils.ImageViewObject;

/**
 * Pannello che rappresenta il campo da gioco. Estende {@link Pane}
 * Tutti gli oggetti del gioco vengono disegnati qui sopra.
 * 
 * @author Mirco Gnoli
 *
 */
public class ArenaView extends Pane {

    public ArenaView() {
        //usare le costanti del model o farle x la view?
        this.setPrefWidth(ModelCostant.WORLD_WIDTH);
        this.setPrefHeight(ModelCostant.WORLD_HEIGHT);
        BackgroundImage bgimg = new BackgroundImage(ImageViewObject.SFONDO.getImage(),  BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        this.setBackground(new Background(bgimg));
    }

    private List<? extends Node> transform(final List<IEntity> ent) {
        List<Shape> list = new ArrayList<>();
        for (IEntity e : ent) {
            list.add(Mapper.entityToView(e).get());
        }
        return list;
    }

    //non mi piace, rimuove tutto e rimette tutto, troppa roba
    public void refresh(final List<IEntity> entities) {
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                getChildren().removeAll(getChildren());
                getChildren().addAll(transform(entities));
            }
        });
    }
}
