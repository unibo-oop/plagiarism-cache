package model;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import model.Character.Colors;
import model.Character.Dress;
import model.Character.Hair;
import model.exception.AttemptException;
import model.exception.NoMoreQuestionAvailableException;
import model.exception.PendingAIGuessingCharacter;
import model.exception.UserLiedException;
import utilities.Logger;

/**
 * Defines the gaming mechanisms and methods used by controller.
 */
public class ModelImpl implements Model {

    private static final String HUMAN_TURN = "turno umano";
    private static final String COMPUTER_TURN = "turno computer";
    private static final String STATUS = "STATUS: ";
    private static final Logger LOG = Logger.getLogger();

    private boolean humanTurn;
    private Player human;
    private Player computer;
    private boolean gameStarted;

    private final Set<Character> initialCharacter;
    private final Set<Question> initialQuestions;

    /**
     * @param characters all the character that must be used for this match
     */
    public ModelImpl(final Set<Character> characters) {
        super();
        this.initialCharacter = characters;
        this.initialQuestions = initializeAnswers();
        resetGame();
    }

    @Override
    public final void resetGame() {
        final Random r = new Random(System.currentTimeMillis());
        this.humanTurn = r.nextBoolean();
        this.human = new PlayerImpl(initialCharacter, initialQuestions);
        this.computer = new AI(initialCharacter, initialQuestions, new FiftyFiftyDistribution());
        this.gameStarted = false;

        LOG.write("STATUS: Nuova partita");
    }

    // GETTER

    @Override
    public Map<Character, Boolean> getHumanCharacters() {
        return Collections.unmodifiableMap(human.getCharacters());
    }

    @Override
    public Set<Question> getHumanQuestions() {
        try {
            return Collections.unmodifiableSet(human.getQuestionsLeft());
        } catch (NoMoreQuestionAvailableException e) {
            return Collections.emptySet();
        }
    }

    @Override
    public Map<Character, Boolean> getAICharacters() {
        return Collections.unmodifiableMap(computer.getCharacters());
    }

    @Override
    public Character getAIchosenCharacter() {
        return computer.getChosenCharacter();
    }

    // MATCH STATE CHECK

    @Override
    public boolean isHumanTurn() {
        return humanTurn;
    }

    @Override
    public boolean humanWon() {
        return wonCheck(this.human, STATUS + "UMANO VINCE");
    }

    @Override
    public boolean aiWon() {
        return wonCheck(this.computer, STATUS + "COMPUTER VINCE");
    }

    private boolean wonCheck(final Player player, final String log) {
        final boolean oldState = gameStarted;
        gameStarted = (player.getCharacters().entrySet().stream().filter(x -> x.getValue()).count() == 1) ^ true;
        if (oldState && !gameStarted) {
            LOG.write(log);
        }
        return gameStarted ^ true;
    }

    // SETTER

    @Override
    public void humanHasChosenCharacter(final Character c) {
        if (!gameStarted) {
            ((PlayerImpl) human).setChosenCharacter(c);
            gameStarted = true;
            LOG.write("Umano ha scelto " + c.getName());
            LOG.write("Computer ha scelto " + computer.getChosenCharacter().getName());
            LOG.write("Inizia la partita");
            LOG.write(STATUS + (this.isHumanTurn() ? HUMAN_TURN : COMPUTER_TURN));
        } else {
            throw new IllegalStateException(
                    "Non e' possibile impostare il personaggio una volta che il gioco e' iniziato");
        }
    }

    // AI INTERACTION

    @Override
    public void humanTryToGuess(final Character c) throws AttemptException {
        checkGameStatus(true);
        if (human.getAttemptLeft() <= 0) {
            throw new AttemptException();
        }

        final boolean answer = computer.getChosenCharacter().equals(c);
        human.triedToGuess(c, answer);
        humanTurn ^= true;
        LOG.write("UMANO: prova ad indovinare: " + c.getName());
        LOG.write("COMPUTER: " + answer);
        LOG.write(STATUS + (this.isHumanTurn() ? HUMAN_TURN : COMPUTER_TURN));
    }

    @Override
    public boolean askToAI(final Question q) {
        checkGameStatus(true);

        final boolean answer = computer.answerQuestion(q);
        human.questionAsked(q, answer);
        humanTurn ^= true;
        LOG.write("UMANO: " + q.getText());
        LOG.write("COMPUTER: " + answer);
        LOG.write(STATUS + (this.isHumanTurn() ? HUMAN_TURN : COMPUTER_TURN));
        return answer;
    }

    @Override
    public Question getAInextQuestion() throws NoMoreQuestionAvailableException, PendingAIGuessingCharacter {
        checkGameStatus(false);
        return ((AI) computer).nextQuestion();
    }

    @Override
    public Optional<Character> getAIcharacterGuess() {
        return ((AI) computer).getAIpendingGuess();
    }


    @Override
    public void humanAnswered(final Question q, final boolean answer) throws UserLiedException {
        checkGameStatus(false);

        LOG.write("COMPUTER: " + q.getText());
        LOG.write("UMANO: " + answer);

        final boolean answerCheck = human.answerQuestion(q);
        if (answerCheck != answer) { // l'utente ha mentito!
            LOG.write("STATUS: Utente ha mentito");
            throw new UserLiedException(q.getText());
        } else {
            computer.questionAsked(q, answer);
            humanTurn ^= true;
            LOG.write(STATUS + (this.isHumanTurn() ? HUMAN_TURN : COMPUTER_TURN));
        }
    }

    @Override
    public void humanAnswered(final Character c, final boolean answer) throws UserLiedException {
        checkGameStatus(false);

        LOG.write("COMPUTER: prova ad indovinare: " + c.getName());
        LOG.write("UMANO: " + answer);

        final boolean answerCheck = human.getChosenCharacter().equals(c);
        if (answerCheck != answer) { // l'utente ha mentito!
            LOG.write("STATUS: Utente ha mentito");
            throw new UserLiedException(c.getName());
        } else {
               computer.triedToGuess(c, answerCheck);
               humanTurn ^= true;
               LOG.write(STATUS + (this.isHumanTurn() ? HUMAN_TURN : COMPUTER_TURN));
        }
    }

    private void checkGameStatus(final boolean shouldBeHumanTurn) {
        if (!gameStarted) {
            throw new IllegalStateException("Gioco non iniziato");
        } else if (shouldBeHumanTurn && !isHumanTurn()) {
            throw new IllegalStateException("Non e' il turno del giocatore umano");
        } else if (shouldBeHumanTurn ^ true && isHumanTurn()) {
            throw new IllegalStateException("Non e' il turno del giocatore umano");
        }
    }

    //QUESTION

    private Set<Question> initializeAnswers() {
        final HashSet<Question> questions = new LinkedHashSet<>();

        // sesso
        questions.add(
                new Question("E' un uomo?", x -> x.isMale(), Question.TYPE.GENDER));

        // colore capelli
        questions.add(
                new Question("Ha i capelli neri?", x -> x.getHairColor().equals(Optional.of(Colors.BLACK)), Question.TYPE.HAIR_COLOR));
        questions.add(
                new Question("Ha i capelli bianchi?", x -> x.getHairColor().equals(Optional.of(Colors.WHITE)), Question.TYPE.HAIR_COLOR));
        questions.add(
                new Question("Ha i capelli biondi?", x -> x.getHairColor().equals(Optional.of(Colors.YELLOW)), Question.TYPE.HAIR_COLOR));
        questions.add(
                new Question("Ha i capelli castani?", x -> x.getHairColor().equals(Optional.of(Colors.BROWN)), Question.TYPE.HAIR_COLOR));

        // colore occhi
        questions.add(
                new Question("Ha gli occhi marroni?", x -> x.getEyeColor().equals(Colors.BROWN), Question.TYPE.EYE_COLOR));
        questions.add(
                new Question("Ha gli occhi azzurri?", x -> x.getEyeColor().equals(Colors.BLUE), Question.TYPE.EYE_COLOR));

        // colore barba
        questions.add(
                new Question("Ha la barba marrone?", x -> x.getBeardColor().equals(Optional.of(Colors.BROWN)), Question.TYPE.BEARD_COLOR));
        questions.add(
                new Question("Ha la barba nera?", x -> x.getBeardColor().equals(Optional.of(Colors.BLACK)), Question.TYPE.BEARD_COLOR));
        questions.add(
                new Question("Ha la barba?", x -> !x.getBeardColor().equals(Optional.empty()), Question.TYPE.BEARD_COLOR));

        //colore baffi
        questions.add(
                new Question("Ha i baffi marroni?", x -> x.getMustacheColor().equals(Optional.of(Colors.BROWN)), Question.TYPE.MUSTACHE_COLOR));
        questions.add(
                new Question("Ha i baffi neri?", x -> x.getMustacheColor().equals(Optional.of(Colors.BLACK)), Question.TYPE.MUSTACHE_COLOR));
        questions.add(
                new Question("Ha i baffi?", x -> !x.getMustacheColor().equals(Optional.empty()), Question.TYPE.MUSTACHE_COLOR));

        //accessori
        questions.add(
                new Question("Ha il cappello?", x -> x.hasHat(), Question.TYPE.HAT));

        questions.add(
                new Question("Porta gli orecchini?", x -> x.hasEarrings(), Question.TYPE.EARINGS));

        questions.add(
                new Question("Porta gli occhiali?", x -> x.hasGlasses(), Question.TYPE.GLASSES));

        // tipo capelli
        questions.add(
                new Question("Ha i capelli lisci?", x -> x.getHairType().equals(Hair.STRAIGHT), Question.TYPE.HAIR_TYPE));
        questions.add(
                new Question("Ha i capelli mossi?", x -> x.getHairType().equals(Hair.CURLY), Question.TYPE.HAIR_TYPE));
        questions.add(
                new Question("E' calvo?", x -> x.getHairType().equals(Hair.BALD), Question.TYPE.HAIR_TYPE));

        // tipo vestiti
        questions.add(
                new Question("Indossa la felpa?", x -> x.getDressType().equals(Dress.SWEATSHIRT), Question.TYPE.DRESS_TYPE));
        questions.add(
                new Question("Indossa la camicia?", x -> x.getDressType().equals(Dress.SHIRT), Question.TYPE.DRESS_TYPE));
        questions.add(
                new Question("Indossa la tshirt?", x -> x.getDressType().equals(Dress.TSHIRT), Question.TYPE.DRESS_TYPE));
        questions.add(
                new Question("Indossa la giacca?", x -> x.getDressType().equals(Dress.JACKET), Question.TYPE.DRESS_TYPE));

        return questions;
    }

}