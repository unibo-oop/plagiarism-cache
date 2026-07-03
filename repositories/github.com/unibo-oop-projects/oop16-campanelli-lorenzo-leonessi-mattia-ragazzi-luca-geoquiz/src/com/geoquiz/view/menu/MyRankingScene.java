package com.geoquiz.view.menu;

import java.io.IOException;
import java.util.Map;

import javax.xml.bind.JAXBException;

import com.geoquiz.utility.Pair;
import com.geoquiz.view.button.ButtonsCategory;

import javafx.stage.Stage;

/**
 * The statistics scene where user can see own records.
 */
public class MyRankingScene extends AbsoluteRankingScene {

    private Map<Pair<String, String>, Integer> map;

    /**
     * @param mainStage
     *            the stage where the scene is called.
     * @throws JAXBException
     *             for xml exception.
     */
    public MyRankingScene(final Stage mainStage) throws JAXBException {
        super(mainStage);
        super.getTitle().setText("My records");
        try {
            map = super.getRanking().getPersonalRanking(LoginMenuScene.getUsername());
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        super.clearLabel();

        super.getCapitalsEasy().setText(super.getCapitalsEasy().getText()
                + this.getRecordByCategory(ButtonsCategory.CAPITALI.toString(), ButtonsCategory.FACILE.toString()));
        super.getCapitalsMedium().setText(super.getCapitalsMedium().getText()
                + this.getRecordByCategory(ButtonsCategory.CAPITALI.toString(), ButtonsCategory.MEDIO.toString()));
        super.getCapitalsHard().setText(super.getCapitalsHard().getText()
                + this.getRecordByCategory(ButtonsCategory.CAPITALI.toString(), ButtonsCategory.DIFFICILE.toString()));
        super.getCapitalsChallenge().setText(super.getCapitalsChallenge().getText()
                + this.getRecordByCategory(ButtonsCategory.CAPITALI.toString(), ButtonsCategory.SFIDA.toString()));
        super.getMonumentsEasy().setText(super.getMonumentsEasy().getText()
                + this.getRecordByCategory(ButtonsCategory.MONUMENTI.toString(), ButtonsCategory.FACILE.toString()));
        super.getMonumentsMedium().setText(super.getMonumentsMedium().getText()
                + this.getRecordByCategory(ButtonsCategory.MONUMENTI.toString(), ButtonsCategory.MEDIO.toString()));
        super.getMonumentsHard().setText(super.getMonumentsHard().getText()
                + this.getRecordByCategory(ButtonsCategory.MONUMENTI.toString(), ButtonsCategory.DIFFICILE.toString()));
        super.getMonumentsChallenge().setText(super.getMonumentsChallenge().getText()
                + this.getRecordByCategory(ButtonsCategory.MONUMENTI.toString(), ButtonsCategory.SFIDA.toString()));
        super.getFlagsClassic().setText(super.getFlagsClassic().getText()
                + this.getRecordByCategory(ButtonsCategory.BANDIERE.toString(), ButtonsCategory.CLASSICA.toString()));
        super.getFlagsChallenge().setText(super.getFlagsChallenge().getText()
                + this.getRecordByCategory(ButtonsCategory.BANDIERE.toString(), ButtonsCategory.SFIDA.toString()));
        super.getCurrenciesClassic().setText(super.getCurrenciesClassic().getText()
                + this.getRecordByCategory(ButtonsCategory.VALUTE.toString(), ButtonsCategory.CLASSICA.toString()));
        super.getCurrenciesChallenge().setText(super.getCurrenciesChallenge().getText()
                + this.getRecordByCategory(ButtonsCategory.VALUTE.toString(), ButtonsCategory.SFIDA.toString()));
        super.getDishesClassic().setText(super.getDishesClassic().getText()
                + this.getRecordByCategory(ButtonsCategory.CUCINA.toString(), ButtonsCategory.CLASSICA.toString()));
        super.getDishesChallenge().setText(super.getDishesChallenge().getText()
                + this.getRecordByCategory(ButtonsCategory.CUCINA.toString(), ButtonsCategory.SFIDA.toString()));

    }

    private String getRecordByCategory(final String category, final String difficulty) {
        final Integer record = this.map.get(new Pair<>(category, difficulty));
        return record == null ? "" : record.toString();
    }
}