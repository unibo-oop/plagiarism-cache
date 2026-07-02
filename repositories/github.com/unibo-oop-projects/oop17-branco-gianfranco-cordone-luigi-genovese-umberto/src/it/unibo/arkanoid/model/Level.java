package it.unibo.arkanoid.model;

import java.util.stream.Stream;

import it.unibo.arkanoid.event.Event;
import it.unibo.arkanoid.subject.Subject;

/**
 * The interface for the level's game.
 *
 */
public interface Level {

    /**
     * Add a Subject to the level.
     * 
     * @param subject
     *            The Subject to be added.
     */
    void addSubject(Subject subject);

    /**
     * Remove a {@link Subject} to the level is present.
     * 
     * @param subject
     *            The Subject to be removed.
     */
    void removeSubject(Subject subject);

    /**
     * Getters for all the Subject in Game.
     * 
     * @return A Stream of Subject, in game.
     */
    Stream<Subject> getAllSubjects();

    /**
     * Check if there is a collision between two objects type.
     */
    void checkCollisions();

    /**
     * Controls the updating of all {@link Subject}.
     * 
     * @param deltaTime
     *            The time elapsed from the last frame.
     */
    void updateAll(double deltaTime);

    /**
     * Getter for {@link Paddle}.
     * 
     * @return The Paddle.
     */
    Subject getPaddle();

    /**
     * 
     * @return subject factory.
     */
    SubjectFactory getSubjectFactory();

    /**
     * 
     * @return changed score event.
     */
    Event<Integer> getScoreChangedEvent();

    /**
     * 
     * @return the level end event.
     */
    Event<LevelState> getLevelFinishedEvent();

    /**
     * 
     * Set the position when the player loses a life.
     */
    void resetPosition();
}
