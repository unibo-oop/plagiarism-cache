package com.geoquiz.model.test;

//CHECKSTYLE:OFF
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.junit.Test;
import com.geoquiz.model.question.Question;
import com.geoquiz.model.xmlunmarshal.CountryInfo;
import com.geoquiz.model.xmlunmarshal.XMLFiles;
import com.geoquiz.model.xmlunmarshal.XMLUnmarshaller;

public class QuestionBuilderTest {
    private static final String ANSWER = "Lillo";
    private static final String QUESTION = "Billy";
    
    @Test
    public void testBuilder() {
        final Question.Builder qb = Question.Builder.createBuilder();
        buildTest(qb);
        try {
            qb.addCorrectAnswer(ANSWER);
            fail();
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
        qb.addQuestion(QUESTION);
        buildTest(qb);
        qb.addAnswer(ANSWER);
        qb.addCorrectAnswer(ANSWER);
        qb.build();
        buildTest(qb);
    }
    
    public void testUnmarshaller() {
        List<CountryInfo> list = new ArrayList<>();
        try {
            list = XMLUnmarshaller.getInstance().unmarshal(XMLFiles.COUNTRIES_INFO);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        assertEquals(204, list.size());
        assertTrue(list.stream().anyMatch(e -> e.getCountry().equals("Italia")));
    }
    
    private static void buildTest(final Question.Builder qb) {
        try {
            qb.build();
            fail();
        } catch (IllegalStateException e) {
            assertNotNull(e);
        }
    }
}
