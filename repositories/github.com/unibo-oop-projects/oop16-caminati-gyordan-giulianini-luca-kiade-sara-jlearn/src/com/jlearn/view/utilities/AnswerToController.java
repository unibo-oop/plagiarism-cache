package com.jlearn.view.utilities;

import java.util.List;
import java.util.Map;

import com.jlearn.view.utilities.enums.ExerciseType;
import com.sun.glass.ui.View;

/**
 * Data structure for passing data between view and controller.
 *
 */
public final class AnswerToController {

    private final Map<ExerciseType, List<String>> mappaTextField;

    private AnswerToController(final Map<ExerciseType, List<String>> mappaTextField) {
        this.mappaTextField = mappaTextField;
    }

    /**
     * Get a {@link AnswerToController} data structure.
     *
     * @param mappaTextField
     *            the {@link Map} where save the {@link List} of {@link String} to pass to the {@link View}.
     * @return the {@link AnswerToController}.
     */
    public static AnswerToController getAnswersToController(final Map<ExerciseType, List<String>> mappaTextField) {
        return new AnswerToController(mappaTextField);

    }

    /**
     * @return the mappaTextField
     */
    public Map<ExerciseType, List<String>> getMappaTextField() {
        return this.mappaTextField;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AnswerToController [mappaTextField=" + this.mappaTextField + "]";
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.mappaTextField == null) ? 0 : this.mappaTextField.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final AnswerToController other = (AnswerToController) obj;
        if (this.mappaTextField == null) {
            if (other.mappaTextField != null) {
                return false;
            }
        } else if (!this.mappaTextField.equals(other.mappaTextField)) {
            return false;
        }
        return true;
    }

}
