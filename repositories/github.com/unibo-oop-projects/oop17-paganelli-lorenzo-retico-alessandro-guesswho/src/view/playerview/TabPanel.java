package view.playerview;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import model.question.Question;
import utilities.Utilities;

class TabPanel extends JPanel { 

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private final JComboBox<Question> questions = new JComboBox<>();

    TabPanel(final Set<Question> startingQuestion) {
        super();
        Utilities.requireNonNull(startingQuestion);
        final GridBagConstraints constraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        constraints.anchor = GridBagConstraints.CENTER;
        layout.setConstraints(questions, constraints);
        startingQuestion.stream().forEach(q -> questions.addItem(q));
        this.add(questions);
    }

    public void update(final Set<Question> newQuestions) {
        this.questions.removeAllItems();
        newQuestions.stream().forEach(q -> questions.addItem(q));
    }

    public Question getQuestion() {
        return  questions.getItemAt(questions.getSelectedIndex());
    }

}
