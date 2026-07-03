package com.geoquiz.model.quiz;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBException;

import com.geoquiz.model.question.Question;
import com.geoquiz.model.xmlunmarshal.CountryInfo;
import com.geoquiz.model.xmlunmarshal.XMLFiles;
import com.geoquiz.model.xmlunmarshal.XMLUnmarshaller;

final class QuizCreator<T> {

    private static final Random RAND_GEN = new Random();
    private final Class<T> clazz;
    private static List<CountryInfo> countriesList;
    private final List<T> list;
    private final String questionMethName;
    private final String answerMethName;
    private final Quiz quiz;

    static {
        try {
            countriesList = XMLUnmarshaller.getInstance().unmarshal(XMLFiles.COUNTRIES_INFO);
        } catch (JAXBException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private QuizCreator(final Class<T> clazz, final List<T> list, final Optional<? extends Mode> mode,
            final String questionMethName, final Optional<String> answerMethName,
            final Optional<Predicate<T>> predicate) throws JAXBException {
        this.clazz = clazz;
        this.list = list;
        this.questionMethName = questionMethName;
        this.answerMethName = answerMethName.orElse("getCountry");
        this.quiz = new QuizImpl(this.buildQuestionsList(predicate.orElse(x -> true)), mode);
    }

    public Quiz getQuiz() {
        return this.quiz;
    }

    public static List<CountryInfo> getCountriesList() {
        return Collections.unmodifiableList(countriesList);
    }

    private List<Question> buildQuestionsList(final Predicate<T> predicate) {
        return this.list.stream().filter(predicate).map(x -> this.buildQuestion(x)).collect(Collectors.toList());
    }

    private Question buildQuestion(final T info) {
        final Method questionMeth;
        final Method answerMeth;
        final Question.Builder qb;
        try {
            questionMeth = clazz.getMethod(questionMethName);
            answerMeth = clazz.getMethod(answerMethName);
            qb = Question.Builder.createBuilder().addQuestion((String) questionMeth.invoke(info))
                    .addAnswer((String) answerMeth.invoke(info));
            while (qb.getAnswersSetSize() < 4) {
                qb.addAnswer((String) CountryInfo.class.getMethod(answerMethName)
                        .invoke(QuizCreator.countriesList.get(RAND_GEN.nextInt(QuizCreator.countriesList.size()))));
            }
            qb.addCorrectAnswer((String) answerMeth.invoke(info));
            return qb.build();
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
                | SecurityException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    static final class Builder<T> {
        private Class<T> clazz;
        private List<T> list;
        private Optional<? extends Mode> mode;
        private String questionMethName;
        private String answerMethName;
        private Predicate<T> predicate;

        public Builder<T> addClass(final Class<T> clazz) {
            this.clazz = clazz;
            return this;
        }

        public Builder<T> addList(final List<T> list) {
            this.list = list;
            return this;
        }

        public Builder<T> addMode(final Optional<? extends Mode> mode) {
            this.mode = mode;
            return this;
        }

        public Builder<T> addQuestionMethName(final String questionMethName) {
            this.questionMethName = questionMethName;
            return this;
        }

        public Builder<T> addAnswerMethName(final String answerMethName) {
            this.answerMethName = answerMethName;
            return this;
        }

        public Builder<T> addFilterPredicate(final Predicate<T> predicate) {
            this.predicate = predicate;
            return this;
        }

        public QuizCreator<T> build() throws JAXBException {
            if (this.clazz == null || this.list == null || this.mode == null || this.questionMethName == null) {
                throw new IllegalStateException("Build called when some required fields were null.");
            }
            return new QuizCreator<>(this.clazz, this.list, this.mode, this.questionMethName,
                    Optional.ofNullable(this.answerMethName), Optional.ofNullable(this.predicate));
        }
    }

}