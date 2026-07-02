package it.unibo.storage.enigma;

import java.util.List;

import it.unibo.api.key.Key;

/**
 * data template for Enigmas saving on yaml
 */
public class DataForEnigmas {
    
    /**
     * The enigma id
     */
    private String id;
    
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
     * the key that this enigma has to drop when completed
     * {@code null} if the enigma does not have a key (null key is handled in {@link Enigma})
     */
    private Key key;

    /** 0 args constructor */
    public DataForEnigmas() {}

    /**
     * constructor
     * @param id enigma's id
     * @param question enigma's question
     * @param correctOption enigma's correct option
     * @param options all enigma possible answers
     * @param key the key contained in this enigma, assuming this enigma has a key, {@code null} otherwise (null reference checked in {@code Enigma})
     */
    public DataForEnigmas(final String id, final String question, final String correctOption, 
            final List<String> options, final Key key) {
                
        this.id = id;
        this.question = question;
        this.correctOption = correctOption;
        this.options = options;
        this.key = key;
    }


//getters

    /**
     * gets this enigma's id
     * @return enigma's id
     */
    public String getId() {
        return this.id;
    }

    /**
     * gets the key in this enigma
     * @return {@code null} (handled in {@code Enigma}) if the enigma does not have a key, the {@link Key} otherwise
     */
    public Key getKey() {
        return this.key;
    }

    /**
     * gets the enigma's question
     * @return the question
     */
    public String getQuestion() {
        return this.question;
    }

    /**
     * gets the enigma's possible anwers
     * @return the {@link java.util.List} of answers
     */
    public List<String> getOptions() {
        return this.options;
    }

    /**
     * gets the correct answer
     * @return the corrent answer
     */
    public String getCorrectOption() {
        return this.correctOption;
    }    

//

//setters

    /**
     * sets this enigma's id
     * @param id the id
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * sets the key
     * @param key the Key to set
     */
    public void setKey(final Key key) {
        this.key = key;
    }

    /**
     * sets the enigma's question
     * @param question the question
     */
    public void setQuestion(final String question) {
        this.question = question;
    }

    /**
     * sets the enigma's possible anwers
     * @param options the answers
     */
    public void setOptions(final List<String> options) {
        this.options = options;
    }

    /**
     * sets the correct answer
     * @param correctOption the correct answer
     */
    public void setCorrectOption(final String correctOption) {
        this.correctOption = correctOption;
    }    

//

}
