package com.geoquiz.model.quiz;

import java.util.Optional;

import javax.xml.bind.JAXBException;

import com.geoquiz.model.xmlunmarshal.CountryInfo;
import com.geoquiz.model.xmlunmarshal.TypicalDish;
import com.geoquiz.model.xmlunmarshal.XMLFiles;
import com.geoquiz.model.xmlunmarshal.XMLUnmarshaller;

/**
 * This class implements a factory for the Quiz.
 */
public final class QuizFactory {

    private QuizFactory() { }

    /**
     * @param mode
     *          The mode as in Mode enum.
     * @return CapitalsQuiz
     * @throws JAXBException 
     */
    public static Quiz createCapitalsQuiz(final Mode mode) throws JAXBException {
        return new QuizCreator.Builder<CountryInfo>()
                .addClass(CountryInfo.class)
                .addList(QuizCreator.getCountriesList())
                .addMode(Optional.of(mode))
                .addQuestionMethName("getCountry")
                .addAnswerMethName("getCapitalCity")
                .addFilterPredicate(c -> ((mode == BasicMode.TRAINING || mode == BasicMode.CHALLENGE) ? true
                                    : ((ExtendedMode) mode).getModeCode().equals(c.getDifficulty())))
                .build()
                .getQuiz();
    }

    /**
     * @param mode
     *          The only accepted modes are TRAINING or CHALLENGE, otherwise it will throw an IllegalArgumentException
     * @return a new CurrenciesQuiz
     * @throws JAXBException
     *                  if something goes wrong with the XML unmarshaller
     */
    public static Quiz createCurrenciesQuiz(final Optional<BasicMode> mode) throws JAXBException {
        return new QuizCreator.Builder<CountryInfo>()
                .addClass(CountryInfo.class)
                .addList(QuizCreator.getCountriesList())
                .addMode(mode)
                .addQuestionMethName("getCountry")
                .addAnswerMethName("getCurrency")
                .build()
                .getQuiz();
    }

    /**
     * @param mode
     *          The only accepted modes are TRAINING or CHALLENGE, otherwise it will throw an IllegalArgumentException
     * @return  a new FlagsQuiz
     * @throws JAXBException
     *                  if something goes wrong with the XML unmarshaller
     */
    public static Quiz createFlagsQuiz(final Optional<BasicMode> mode) throws JAXBException {
        return new QuizCreator.Builder<CountryInfo>()
                .addClass(CountryInfo.class)
                .addList(QuizCreator.getCountriesList())
                .addMode(mode)
                .addQuestionMethName("getFlagRef")
                .build()
                .getQuiz();
    }

    /**
     * @param mode
     *          The mode as in mode enum.
     * @return a new MonumentsQuiz
     * @throws JAXBException
     *                  if something goes wrong with the XML unmarshaller
     */
    public static Quiz createMonumentsQuiz(final Mode mode) throws JAXBException {
        return new MonumentsQuiz().getQuiz(mode);
    }

    /**
     * @param mode
     *         The only accepted modes are TRAINING or CHALLENGE, otherwise it will throw an IllegalArgumentException
     * @return a new TypicalDishesQuiz
     * @throws JAXBException
     *                  if something goes wrong with the XML unmarshaller
     */
    public static Quiz createTypicalDishesQuiz(final Optional<BasicMode> mode) throws JAXBException {
        return new QuizCreator.Builder<TypicalDish>()
                .addClass(TypicalDish.class)
                .addList(XMLUnmarshaller.getInstance().unmarshal(XMLFiles.TYPICAL_DISHES))
                .addMode(mode)
                .addQuestionMethName("getDish")
                .build()
                .getQuiz();
    }

}
