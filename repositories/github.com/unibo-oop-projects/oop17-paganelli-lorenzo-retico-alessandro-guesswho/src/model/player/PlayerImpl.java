package model.player;

import java.util.*;
import java.util.stream.Collectors;
import model.attribute.Attribute;
import model.character.Character;
import model.question.Question;
import utilities.Utilities;

class PlayerImpl implements Player {

    private Character selected;
    private final Set<Character> characters;
    private int remainingAttempts;
    private boolean hasSelected;

    PlayerImpl(final Set<Character> characters, final Integer attempts) {
        Utilities.requireNonNull(characters, attempts);
        if (characters.size() < 2 || attempts < 1) {
            throw new IllegalArgumentException("Characters or attempts are less than the minimum to play");
        }
        this.characters = new HashSet<>(characters);
        this.remainingAttempts = attempts;
    }

    @Override
    public Character getSelected() {
        if (!hasSelected) {
            throw new IllegalStateException("Player has not selected yet");
        }
        return this.selected;
    }

    @Override
    public Set<Character> getAvailables() {
        return Collections.unmodifiableSet(characters);
    }

    @Override
    public int getRemainingAttempts() {
        return this.remainingAttempts;
    }

    @Override
    public void select(final String name) {
        if (hasSelected) {
            throw new IllegalStateException("Player has already selected");
        }
        Utilities.requireNonNull(name);
        this.checkContains(name);
        this.selected = characters.stream().filter(c -> c.getName().equals(name)).findFirst().get();
        hasSelected = true;
    }

    @Override
    public List<Question> availableQuestions() {
        return characters.stream().flatMap(character -> character.getAttributes().stream())
                .flatMap(attribute -> attribute.possibleQuestions().stream())
                .filter(question -> characters.stream().anyMatch(c -> !c.has(question.toAttribute())))
                .collect(Collectors.toList());
    }

    @Override
    public void decreaseAttempts() {
        if (remainingAttempts == 0) {
            throw new IllegalStateException("No remaining attempts");
        }
        this.remainingAttempts--;
    }

    @Override
    public void filter(final Attribute attribute, final Boolean b) {
        Utilities.requireNonNull(attribute, b);
        characters.removeIf(character -> b ? !character.has(attribute) : character.has(attribute));
    }

    @Override
    public void remove(final String name) {
        Utilities.requireNonNull(name);
        this.checkContains(name);
        characters.removeIf(character -> character.getName().equals(name));
    }

    private void checkContains(final String name) {
        if (!characters.stream().anyMatch(character -> character.getName().equals(name))) {
            throw new IllegalArgumentException("The name does not match any character: " + name);
        }
    }

}
