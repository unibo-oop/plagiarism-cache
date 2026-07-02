package view.playerview;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.*;

import model.attribute.Trait;
import model.question.Question;

class QuestionsPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private final JButton jAsk = new JButton("ask");
    private final Map<String, TabPanel> questions = new HashMap<>();
    private final JTabbedPane tabbedPane = new JTabbedPane();

    QuestionsPanel(final Set<Question> startingQuestions) {
        super();
        this.setLayout(new BorderLayout());
        Arrays.stream(Trait.values()).forEach(e -> {
            final Set<Question> traitQuestions = filterQuestions(e.toString(), startingQuestions);
            if (!traitQuestions.isEmpty()) {
                final TabPanel tab = new TabPanel(traitQuestions);
                tabbedPane.add(e.toString(), tab);
                questions.put(e.toString(), tab);
            }
        });
        jAsk.setEnabled(false);
        this.add(BorderLayout.CENTER, tabbedPane);
        this.add(BorderLayout.EAST, jAsk);
    }

    public void addActionListener(final ActionListener al) {
        jAsk.addActionListener(al);
    }

    public void setEnabled(final boolean b) {
        jAsk.setEnabled(b);
    }

    public void update(final Set<Question> remainingQuestions) {
        final Set<String> removeTrait = new HashSet<>();
        questions.entrySet().forEach(e -> {
            final Set<Question> traitQuestions = filterQuestions(e.getKey(), remainingQuestions);
            if (traitQuestions.isEmpty()) {
                removeTrait.add(e.getKey());
            } else {
                e.getValue().update(traitQuestions);
            }
        });
        removeTrait.stream().forEach(t -> {
            tabbedPane.remove(questions.get(t));
            questions.remove(t);
        });
        if (questions.isEmpty()) {
            jAsk.setVisible(false);
        }
    }

    public Question getSelectedQuestion() { 
        return ((TabPanel) tabbedPane.getSelectedComponent()).getQuestion();
    }

    private Set<Question> filterQuestions(final String trait, final Set<Question> questions) {
        return questions.stream().filter(q -> q.toAttribute().getTrait().toString().equals(trait)).collect(Collectors.toSet());
    }
}
