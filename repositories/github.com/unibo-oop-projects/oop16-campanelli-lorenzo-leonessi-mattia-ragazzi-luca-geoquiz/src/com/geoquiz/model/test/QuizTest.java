//CHECKSTYLE:OFF
package com.geoquiz.model.test;

import static org.junit.Assert.*;

import java.util.Optional;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import com.geoquiz.model.quiz.BasicMode;
import com.geoquiz.model.quiz.ExtendedMode;
import com.geoquiz.model.quiz.Quiz;
import com.geoquiz.model.quiz.QuizFactory;

public class QuizTest {
    private static final String ANSWER = "Non ci sono";
    @Test
    public void testQuiz() {
        Quiz quiz = null;
        try {
            quiz = QuizFactory.createTypicalDishesQuiz(Optional.of(BasicMode.CHALLENGE));
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assertNotNull(quiz);
        containsQuestion(quiz, "Fish&Chips");
        checkCorrectAnswer(quiz);
        checkIsQuizFinished(quiz);
        try {
            quiz = QuizFactory.createCapitalsQuiz(ExtendedMode.HARD);
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        checkAnswerNotInSet(quiz, ANSWER);
        for (int i = 0; i < 3; i++) {
            checkCorrectAnswer(quiz);
        }
        checkIsQuizFinished(quiz);
        final Optional<String> answer = quiz.getCurrentQuestion().getAnswers().stream().findFirst();
        quiz.hitAnswer(answer);
        //if the answer hit is correct current score mustn't be zero
        if (!quiz.isAnswerCorrect()) {
            assertEquals(0, quiz.getCurrentScore());
        } else {
            assertNotEquals(0, quiz.getCurrentScore());
        }
    }
    
    private static void containsQuestion (Quiz quiz, String question) {
        while (!quiz.gameOver()) {
            if (quiz.getCurrentQuestion().getQuestion().equals(question)) {
                return;
            }
            quiz.next();
        }
        fail();
    }
    
    private static void checkCorrectAnswer(Quiz quiz) {
       final String wrongAnswer = quiz.getCurrentQuestion().getAnswers().stream()
                                                                        .filter(a -> !a.equals(quiz.getCorrectAnswer()))
                                                                        .findAny().get();
       quiz.hitAnswer(Optional.of(wrongAnswer));
       assertEquals(quiz.getCurrentScore(), 0);
       assertFalse(quiz.isAnswerCorrect());
    }
    
    private static void checkIsQuizFinished(Quiz quiz) {
        while (!quiz.gameOver()) {
            quiz.next();
        }
        try {
            quiz.next();
        } catch (IllegalStateException e) {
            assertNotNull(e);
        }
    }
    
    private static void checkAnswerNotInSet(Quiz quiz, String answerNotInSet) {
        try {
            quiz.hitAnswer(Optional.of(QuizTest.ANSWER));
        } catch (Exception e) {
            assertNotNull(e);
        }
    }
}
