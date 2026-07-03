package com.geoquiz.view.menu;

import java.io.IOException;
import java.util.Map;
import javax.xml.bind.JAXBException;

import com.geoquiz.controller.ranking.Ranking;
import com.geoquiz.utility.Pair;
import com.geoquiz.view.button.Buttons;
import com.geoquiz.view.button.ButtonsCategory;
import com.geoquiz.view.button.MyButton;
import com.geoquiz.view.button.MyButtonFactory;
import com.geoquiz.view.label.MyLabel;
import com.geoquiz.view.label.MyLabelFactory;
import com.geoquiz.view.utility.Background;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * The ranking scene where user can see other user's records.
 */
public class AbsoluteRankingScene extends Scene {

    private static final double POS_1_X = 20;
    private static final double POS_Y_BACK = 650;
    private static final double FONT = 35;
    private static final double BUTTON_WIDTH = 350;
    private static final double FONT_MODE = 25;
    private static final double POS_X_CATEGORY_BOX = 50;
    private static final double POS_Y_CATEGORY_BOX = 200;
    private static final double POS_X_CATEGORY_BOX_2 = 650;
    private static final double POS_Y_CATEGORY_BOX_2 = 75;
    private static final double POS_X_CAPITALS_BOX = 300;
    private static final double POS_Y_CAPITALS_BOX = 205;
    private static final double POS_X_MONUMENTS_BOX = 300;
    private static final double POS_Y_MONUMENTS_BOX = 450;
    private static final double POS_X_FLAGS_BOX = 850;
    private static final double POS_Y_FLAGS_BOX = 80;
    private static final double POS_X_CURRENCIES_BOX = 850;
    private static final double POS_Y_CURRENCIES_BOX = 325;
    private static final double POS_X_DISHES_BOX = 850;
    private static final double POS_Y_DISHES_BOX = 570;
    private static final double TITLE_FONT = 95;
    private static final String S = " -> ";

    private final Pane panel = new Pane();
    private final VBox vbox = new VBox();
    private final VBox categoryBox = new VBox(200);
    private final VBox categoryBox2 = new VBox(200);
    private final VBox capitalsBox = new VBox();
    private final VBox monumentsBox = new VBox();
    private final VBox dishesBox = new VBox();
    private final VBox currenciesBox = new VBox();
    private final VBox flagsBox = new VBox();
    private final VBox titleBox = new VBox();

    private final MyLabel title;

    private final MyLabel capitalsEasy;
    private final MyLabel capitalsMedium;
    private final MyLabel capitalsHard;
    private final MyLabel capitalsChallenge;
    private final MyLabel monumentsEasy;
    private final MyLabel monumentsMedium;
    private final MyLabel monumentsHard;
    private final MyLabel monumentsChallenge;
    private final MyLabel flagsClassic;
    private final MyLabel flagsChallenge;
    private final MyLabel currenciesClassic;
    private final MyLabel currenciesChallenge;
    private final MyLabel dishesClassic;
    private final MyLabel dishesChallenge;

    private final Ranking ranking = Ranking.getInstance();

    private Map<Pair<String, String>, Pair<String, Integer>> map;

    /**
     * @param mainStage
     *            the stage where the scene is called.
     * @throws JAXBException
     *             for xml exception.
     */
    public AbsoluteRankingScene(final Stage mainStage) throws JAXBException {
        super(new StackPane(), mainStage.getWidth(), mainStage.getHeight());

        final MyLabel capitals;
        final MyLabel monuments;
        final MyLabel flags;
        final MyLabel currencies;
        final MyLabel dishes;

        final MyButton indietro;

        title = MyLabelFactory.createMyLabel("Global records", Color.BLACK, TITLE_FONT);
        try {
            map = this.ranking.getGlobalRanking();
        } catch (ClassNotFoundException | IOException e1) {
            e1.printStackTrace();
        }

        indietro = MyButtonFactory.createMyButton(Buttons.INDIETRO.toString(), Color.BLUE, BUTTON_WIDTH);

        capitals = MyLabelFactory.createMyLabel(ButtonsCategory.CAPITALI.toString(), Color.RED, FONT);
        monuments = MyLabelFactory.createMyLabel(ButtonsCategory.MONUMENTI.toString(), Color.RED, FONT);
        flags = MyLabelFactory.createMyLabel(ButtonsCategory.BANDIERE.toString(), Color.RED, FONT);
        currencies = MyLabelFactory.createMyLabel(ButtonsCategory.VALUTE.toString(), Color.RED, FONT);
        dishes = MyLabelFactory.createMyLabel(ButtonsCategory.CUCINA.toString(), Color.RED, FONT);

        capitalsEasy = MyLabelFactory.createMyLabel(ButtonsCategory.FACILE.toString() + S
                + this.getRecordbyCategory(ButtonsCategory.CAPITALI.toString(), ButtonsCategory.FACILE.toString()),
                Color.BLACK, FONT_MODE);
        capitalsMedium = MyLabelFactory.createMyLabel(ButtonsCategory.MEDIO.toString() + S
                + this.getRecordbyCategory(ButtonsCategory.CAPITALI.toString(), ButtonsCategory.MEDIO.toString()),
                Color.BLACK, FONT_MODE);
        capitalsHard = MyLabelFactory.createMyLabel(ButtonsCategory.DIFFICILE.toString() + S
                + this.getRecordbyCategory(ButtonsCategory.CAPITALI.toString(), ButtonsCategory.DIFFICILE.toString()),
                Color.BLACK, FONT_MODE);
        capitalsChallenge = MyLabelFactory.createMyLabel(ButtonsCategory.SFIDA.toString() + S
                + this.getRecordbyCategory(ButtonsCategory.CAPITALI.toString(), ButtonsCategory.SFIDA.toString()),
                Color.BLACK, FONT_MODE);
        monumentsEasy = MyLabelFactory.createMyLabel(ButtonsCategory.FACILE.toString() + S
                + this.getRecordbyCategory(ButtonsCategory.MONUMENTI.toString(), ButtonsCategory.FACILE.toString()),
                Color.BLACK, FONT_MODE);
        monumentsMedium = MyLabelFactory.createMyLabel(ButtonsCategory.MEDIO.toString() + S
                + this.getRecordbyCategory(ButtonsCategory.MONUMENTI.toString(), ButtonsCategory.MEDIO.toString()),
                Color.BLACK, FONT_MODE);
        monumentsHard = MyLabelFactory.createMyLabel(
                ButtonsCategory.DIFFICILE.toString() + S + this.getRecordbyCategory(
                        ButtonsCategory.MONUMENTI.toString(), ButtonsCategory.DIFFICILE.toString()),
                Color.BLACK, FONT_MODE);
        monumentsChallenge = MyLabelFactory.createMyLabel(ButtonsCategory.SFIDA.toString() + S
                + this.getRecordbyCategory(ButtonsCategory.MONUMENTI.toString(), ButtonsCategory.SFIDA.toString()),
                Color.BLACK, FONT_MODE);
        flagsClassic = MyLabelFactory.createMyLabel(ButtonsCategory.CLASSICA.toString() + S
                + this.getRecordbyCategory(ButtonsCategory.BANDIERE.toString(), ButtonsCategory.CLASSICA.toString()),
                Color.BLACK, FONT_MODE);
        flagsChallenge = MyLabelFactory.createMyLabel(ButtonsCategory.SFIDA.toString() + S
                + this.getRecordbyCategory(ButtonsCategory.BANDIERE.toString(), ButtonsCategory.SFIDA.toString()),
                Color.BLACK, FONT_MODE);
        currenciesClassic = MyLabelFactory.createMyLabel(ButtonsCategory.CLASSICA.toString() + S
                + this.getRecordbyCategory(ButtonsCategory.VALUTE.toString(), ButtonsCategory.CLASSICA.toString()),
                Color.BLACK, FONT_MODE);
        currenciesChallenge = MyLabelFactory.createMyLabel(
                ButtonsCategory.SFIDA.toString() + S
                        + this.getRecordbyCategory(ButtonsCategory.VALUTE.toString(), ButtonsCategory.SFIDA.toString()),
                Color.BLACK, FONT_MODE);
        dishesClassic = MyLabelFactory.createMyLabel(ButtonsCategory.CLASSICA.toString() + S
                + this.getRecordbyCategory(ButtonsCategory.CUCINA.toString(), ButtonsCategory.CLASSICA.toString()),
                Color.BLACK, FONT_MODE);
        dishesChallenge = MyLabelFactory.createMyLabel(
                ButtonsCategory.SFIDA.toString() + S
                        + this.getRecordbyCategory(ButtonsCategory.CUCINA.toString(), ButtonsCategory.SFIDA.toString()),
                Color.BLACK, FONT_MODE);

        titleBox.getChildren().add((Node) title);

        capitalsBox.getChildren().addAll((Node) capitalsEasy, (Node) capitalsMedium, (Node) capitalsHard,
                (Node) capitalsChallenge);
        monumentsBox.getChildren().addAll((Node) monumentsEasy, (Node) monumentsMedium, (Node) monumentsHard,
                (Node) monumentsChallenge);
        currenciesBox.getChildren().addAll((Node) currenciesClassic, (Node) currenciesChallenge);
        flagsBox.getChildren().addAll((Node) flagsClassic, (Node) flagsChallenge);
        dishesBox.getChildren().addAll((Node) dishesClassic, (Node) dishesChallenge);

        categoryBox.getChildren().addAll((Node) capitals, (Node) monuments);
        categoryBox2.getChildren().addAll((Node) flags, (Node) currencies, (Node) dishes);

        categoryBox.setTranslateX(POS_X_CATEGORY_BOX);
        categoryBox.setTranslateY(POS_Y_CATEGORY_BOX);
        categoryBox2.setTranslateX(POS_X_CATEGORY_BOX_2);
        categoryBox2.setTranslateY(POS_Y_CATEGORY_BOX_2);
        capitalsBox.setTranslateX(POS_X_CAPITALS_BOX);
        capitalsBox.setTranslateY(POS_Y_CAPITALS_BOX);
        monumentsBox.setTranslateX(POS_X_MONUMENTS_BOX);
        monumentsBox.setTranslateY(POS_Y_MONUMENTS_BOX);
        dishesBox.setTranslateX(POS_X_DISHES_BOX);
        dishesBox.setTranslateY(POS_Y_DISHES_BOX);
        flagsBox.setTranslateX(POS_X_FLAGS_BOX);
        flagsBox.setTranslateY(POS_Y_FLAGS_BOX);
        currenciesBox.setTranslateX(POS_X_CURRENCIES_BOX);
        currenciesBox.setTranslateY(POS_Y_CURRENCIES_BOX);

        vbox.setTranslateX(POS_1_X);
        vbox.setTranslateY(POS_Y_BACK);
        vbox.getChildren().add((Node) indietro);

        ((Node) indietro).setOnMouseClicked(event -> {
            if (!MainWindow.isWavDisabled()) {
                MainWindow.playClick();
            }
            try {
                mainStage.setScene(new MainMenuScene(mainStage));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        this.panel.getChildren().addAll(Background.getImage(), Background.createBackground(), vbox, categoryBox,
                categoryBox2, capitalsBox, monumentsBox, flagsBox, currenciesBox, dishesBox, titleBox);

        this.setRoot(this.panel);
    }

    private String getRecordbyCategory(final String category, final String difficulty) {
        final Pair<String, Integer> record = this.map.get(new Pair<>(category, difficulty));
        return record == null ? "" : record.getX() + " " + "(" + record.getY() + ")";
    }

    /**
     * clear records labels.
     */
    protected void clearLabel() {

        capitalsEasy.setText(capitalsEasy.getText().substring(0,
                capitalsEasy.getText().indexOf(" ", capitalsEasy.getText().indexOf(" ") + 1) + 1));
        capitalsMedium.setText(capitalsMedium.getText().substring(0,
                capitalsMedium.getText().indexOf(" ", capitalsMedium.getText().indexOf(" ") + 1) + 1));
        capitalsHard.setText(capitalsHard.getText().substring(0,
                capitalsHard.getText().indexOf(" ", capitalsHard.getText().indexOf(" ") + 1) + 1));
        capitalsChallenge.setText(capitalsChallenge.getText().substring(0,
                capitalsChallenge.getText().indexOf(" ", capitalsChallenge.getText().indexOf(" ") + 1) + 1));
        monumentsEasy.setText(capitalsEasy.getText().substring(0,
                monumentsEasy.getText().indexOf(" ", monumentsEasy.getText().indexOf(" ") + 1) + 1));
        monumentsMedium.setText(monumentsMedium.getText().substring(0,
                monumentsMedium.getText().indexOf(" ", monumentsMedium.getText().indexOf(" ") + 1) + 1));
        monumentsHard.setText(monumentsHard.getText().substring(0,
                monumentsHard.getText().indexOf(" ", monumentsHard.getText().indexOf(" ") + 1) + 1));
        monumentsChallenge.setText(monumentsChallenge.getText().substring(0,
                monumentsChallenge.getText().indexOf(" ", monumentsChallenge.getText().indexOf(" ") + 1) + 1));
        flagsClassic.setText(flagsClassic.getText().substring(0,
                flagsClassic.getText().indexOf(" ", flagsClassic.getText().indexOf(" ") + 1) + 1));
        flagsChallenge.setText(flagsChallenge.getText().substring(0,
                flagsChallenge.getText().indexOf(" ", flagsChallenge.getText().indexOf(" ") + 1) + 1));
        currenciesClassic.setText(currenciesClassic.getText().substring(0,
                currenciesClassic.getText().indexOf(" ", currenciesClassic.getText().indexOf(" ") + 1) + 1));
        currenciesChallenge.setText(currenciesChallenge.getText().substring(0,
                currenciesChallenge.getText().indexOf(" ", currenciesChallenge.getText().indexOf(" ") + 1) + 1));
        dishesClassic.setText(dishesClassic.getText().substring(0,
                dishesClassic.getText().indexOf(" ", dishesClassic.getText().indexOf(" ") + 1) + 1));
        dishesChallenge.setText(dishesChallenge.getText().substring(0,
                dishesChallenge.getText().indexOf(" ", dishesChallenge.getText().indexOf(" ") + 1) + 1));
    }

    /**
     * gets the value of title label.
     * 
     * @return title label.
     */
    protected MyLabel getTitle() {
        return title;
    }

    /**
     * gets the value of facilecap label.
     * 
     * @return facilecap label.
     */
    protected MyLabel getCapitalsEasy() {
        return capitalsEasy;
    }

    /**
     * gets the value of mediocap label.
     * 
     * @return mediocap label.
     */
    protected MyLabel getCapitalsMedium() {
        return capitalsMedium;
    }

    /**
     * gets the value of difficilecap label.
     * 
     * @return difficilecap label.
     */
    protected MyLabel getCapitalsHard() {
        return capitalsHard;
    }

    /**
     * gets the value of sfidacap label.
     * 
     * @return sfidacap label.
     */
    protected MyLabel getCapitalsChallenge() {
        return capitalsChallenge;
    }

    /**
     * gets the value of facilemon label.
     * 
     * @return facilemon label.
     */
    protected MyLabel getMonumentsEasy() {
        return monumentsEasy;
    }

    /**
     * gets the value of mediomon label.
     * 
     * @return mediomon label.
     */
    protected MyLabel getMonumentsMedium() {
        return monumentsMedium;
    }

    /**
     * gets the value of difficilemon label.
     * 
     * @return difficilemon label.
     */
    protected MyLabel getMonumentsHard() {
        return monumentsHard;
    }

    /**
     * gets the value of sfidamon label.
     * 
     * @return sfidamon label.
     */
    protected MyLabel getMonumentsChallenge() {
        return monumentsChallenge;
    }

    /**
     * gets the value of classicaban label.
     * 
     * @return classicaban label.
     */
    protected MyLabel getFlagsClassic() {
        return flagsClassic;
    }

    /**
     * gets the value of sfidaban label.
     * 
     * @return sfidaban label.
     */
    protected MyLabel getFlagsChallenge() {
        return flagsChallenge;
    }

    /**
     * gets the value of classicaval label.
     * 
     * @return classicaval.
     */
    protected MyLabel getCurrenciesClassic() {
        return currenciesClassic;
    }

    /**
     * gets the value of sfidaval label.
     * 
     * @return sfidaval label.
     */
    protected MyLabel getCurrenciesChallenge() {
        return currenciesChallenge;
    }

    /**
     * gets the value of classicacuc label.
     * 
     * @return classicacuc label.
     */
    protected MyLabel getDishesClassic() {
        return dishesClassic;
    }

    /**
     * gets the value of sfidacuc label.
     * 
     * @return sfidacuc label.
     */
    protected MyLabel getDishesChallenge() {
        return dishesChallenge;
    }

    /**
     * gets the controller.
     * 
     * @return controller.
     */
    protected Ranking getRanking() {
        return this.ranking;
    }
}
