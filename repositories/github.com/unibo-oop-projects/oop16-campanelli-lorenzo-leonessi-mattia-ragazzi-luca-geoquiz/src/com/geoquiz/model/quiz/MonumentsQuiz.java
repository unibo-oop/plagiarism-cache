package com.geoquiz.model.quiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.xml.bind.JAXBException;

import com.geoquiz.model.xmlunmarshal.MonumentInfo;
import com.geoquiz.model.xmlunmarshal.XMLFiles;
import com.geoquiz.model.xmlunmarshal.XMLUnmarshaller;

final class MonumentsQuiz {
    private static final Map<Mode, List<XMLFiles>> FILES_MAP;
    static {
        FILES_MAP = new HashMap<>();
        FILES_MAP.put(ExtendedMode.EASY, Arrays.asList(XMLFiles.MONUMENTS_EASY));
        FILES_MAP.put(ExtendedMode.MEDIUM, Arrays.asList(XMLFiles.MONUMENTS_MEDIUM));
        FILES_MAP.put(ExtendedMode.HARD, Arrays.asList(XMLFiles.MONUMENTS_DIFFICULT));
        FILES_MAP.put(BasicMode.CHALLENGE, Arrays.asList(XMLFiles.MONUMENTS_EASY, XMLFiles.MONUMENTS_MEDIUM, XMLFiles.MONUMENTS_DIFFICULT));
        FILES_MAP.put(BasicMode.TRAINING, Arrays.asList(XMLFiles.MONUMENTS_EASY, XMLFiles.MONUMENTS_MEDIUM, XMLFiles.MONUMENTS_DIFFICULT));
    }
    private final List<MonumentInfo> monumentsList = new ArrayList<>();

    public Quiz getQuiz(final Mode mode) throws JAXBException {

        MonumentsQuiz.FILES_MAP.get(mode).forEach(f -> {
            try {
                this.monumentsList.addAll(XMLUnmarshaller.getInstance().unmarshal(f));
            } catch (JAXBException e) {
               System.err.println("JAXB Exception in MonumentsQuiz. Exiting.");
               System.exit(1);
            }
        });
        return new QuizCreator.Builder<MonumentInfo>()
                              .addClass(MonumentInfo.class)
                              .addList(this.monumentsList)
                              .addMode(Optional.of(mode))
                              .addQuestionMethName("getMonument")
                              .build()
                              .getQuiz();
    }

}
