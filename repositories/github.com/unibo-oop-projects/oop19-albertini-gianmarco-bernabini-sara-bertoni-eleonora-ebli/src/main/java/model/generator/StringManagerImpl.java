package model.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import application.Main;

/**
 * 
 * This class manages the words that will appear during the game. Each wave has
 * words with different initials.
 *
 */
public class StringManagerImpl implements StringManager {

    private final Map<Character, List<String>> wordsGroupedByInitial;
    private final Random random = new Random();
    private final int maxWord;
    private final int maxLettersShort;

    /**
     * Constructs a new string manager.
     * 
     * @param fileName
     *                       the name of the file it takes words from.
     * @param lettersGap
     *                       value that indicates the max number of letters to
     *                       consider one word short.
     * @throws IllegalArgumentException
     *                                      if the file or the number are not
     *                                      suitable for the rules chosen.
     * 
     */
    public StringManagerImpl(final String fileName, final int lettersGap) {
        this.maxLettersShort = lettersGap;
        this.wordsGroupedByInitial = Collections.unmodifiableMap(new HashMap<>(
                this.takeWordsFromFile(fileName).stream().distinct().collect(Collectors.groupingBy(w -> w.charAt(0)))));
        for (final List<String> list : this.wordsGroupedByInitial.values()) {
            if (!list.stream().anyMatch(s -> s.length() <= lettersGap) || !list.stream().anyMatch(s -> s.length() > lettersGap)) {
                throw new IllegalArgumentException("file and number chosen are not suitable");

            }
        }
        this.maxWord = this.wordsGroupedByInitial.keySet().size();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getWords(final int nShortWord, final int nLongWord) {
        final Set<String> wordsToSpawn = new HashSet<>();
        int i = 0;
        for (final Character c : this.getRandomInitials(nShortWord + nLongWord)) {
            // short words first
            wordsToSpawn.add(this.getWord(c, i < nShortWord));
            i++;
        }
        return wordsToSpawn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxNWord() {
        return this.maxWord;
    }

    private List<Character> getRandomInitials(final int nWords) {
        final List<Character> initials = new LinkedList<>();
        for (int i = 0; i < nWords; i++) {
            initials.add(wordsGroupedByInitial.keySet().stream().filter(l -> !initials.contains(l))
                    .skip(random.nextInt(maxWord - initials.size())).findAny().get());
        }
        return initials;
    }

    private String getWord(final char letter, final boolean shortTurn) {
        List<String> words = wordsGroupedByInitial.get(letter);
        words = shortTurn ? words.stream().filter(s -> isShort(s)).collect(Collectors.toList())
                : words.stream().filter(s -> !isShort(s)).collect(Collectors.toList());
        return words.stream().skip(random.nextInt(words.size())).findFirst().get();
    }

    private boolean isShort(final String word) {
        return word.length() <= maxLettersShort;
    }

    private List<String> takeWordsFromFile(final String fileName) {
        List<String> temp = null;

        try (BufferedReader contents = new BufferedReader(
                new InputStreamReader(ClassLoader.getSystemResourceAsStream(fileName)))) {
            temp = new ArrayList<>(contents.lines().collect(Collectors.toList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }

}
