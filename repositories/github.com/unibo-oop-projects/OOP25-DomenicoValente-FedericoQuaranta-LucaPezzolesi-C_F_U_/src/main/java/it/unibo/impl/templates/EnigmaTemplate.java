package it.unibo.impl.templates;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import it.unibo.api.enigmas.Enigma;
import it.unibo.api.key.Key;

/**
 * an implementation of {@link Enigma}
 * implements {@link java.io.Serializable}
 */
public class EnigmaTemplate implements Enigma, java.io.Serializable {

    /**
     * The enigma id
     */
    private String id;

    /**
     * Indicate if it is complete
     */
    private boolean completed;
    
    /**
     * the key that this enigma has to give to the player when completed
     */
    private Key key;

    /**
     * The question
     */
    private String question;

    /**
     * The correct option
     */
    private String correctOption;

    /**
     * The options
     */
    private List<String> options;

    /**
     * 0 args constructor for snakeYaml
     */
    public EnigmaTemplate() {
        this.completed = false;
    }

    /**
     * constructor
     * @param id enigm's id
     * @param key key that the enigma might have
     * @param question the question
     * @param options the answers
     * @param correctOption the correct answer
     */
    public EnigmaTemplate(final String id, final Key key, 
        final String question, final List<String> options, final String correctOption) {
        this.id = id;
        this.completed = false;
        this.key = key;

        if(! options.contains(correctOption)) {
            throw new IllegalArgumentException("no such option in the list");
        }
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

//getters

    @Override
    public String getId() {
        return this.id;
    }
    @Override
    public boolean isCompleted() {
        return this.completed;
    }
    @Override
    public boolean isKeyInside() {
        return this.key != null;
    }
    @Override
    public String getQuestion() {
        return this.question;
    }
    @Override
    public List<String> getOptions() {
        return this.options;
    }
    @Override
    public boolean solve(String answer) {
        if(answer.equals(this.correctOption)) {
            this.completed = true;
            if(this.key != null) {
                this.key.openDoor();
            }
            return true;
        }
        return false;
    }
    @Override
    public String getCorrectOption() {
        return this.correctOption;
    }    
    @Override
    public Optional<Key> getKey() {
        return Optional.ofNullable(this.key);
    }
//

//setters

    /**
     * sets the enigma's id
     * @param id the id
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * sets this enigma's question
     * @param question the question
     */
    public void setQuestion(final String question) {
        this.question = question;
    }

    /**
     * sets the answers
     * @param options the answers
     */
    public void setOptions(final List<String> options) {
        this.options = options;
    }

    /**
     * sets the correct option
     * @param correctOption the correct answer
     */
    public void setCorrectOption(final String correctOption) {
        this.correctOption = correctOption;
    }

    /**
     * sets the key
     * @param key the key to set
     */
    public void setKey(final Key key) {
        this.key = key;
    }

//

    /**
     * necessary for the tests in order to tell to not check for memory addresses (for assertEquals)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof EnigmaTemplate)) return false;
        EnigmaTemplate that = (EnigmaTemplate) obj;
        return Objects.equals(id, that.id) && Objects.equals(question, that.question);
    }



}
