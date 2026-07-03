package com.jlearn.view.utilities;

import java.util.List;
import java.util.Map;

import com.jfoenix.controls.JFXComboBox;
import com.jlearn.view.utilities.enums.ExerciseType;
import com.sun.glass.ui.View;

/**
 * Data structure for passing data between view and controller.
 *
 */
public final class QuestionToView {

    private final Map<ExerciseType, List<String>> mappaTextField;
    private final List<List<String>> listComboBoxFields;
    private final int elemNumber;

    private QuestionToView(final Map<ExerciseType, List<String>> mappaTextField,
            final List<List<String>> listComboBoxFields, final int elemNumber) {
        this.mappaTextField = mappaTextField;
        this.listComboBoxFields = listComboBoxFields;
        this.elemNumber = elemNumber;
    }

    /**
     * Get a {@link QuestionToView} data structure.
     *
     * @param mappaTextField
     *            the {@link Map} where save the {@link List} of {@link String} to pass to the {@link View}.
     * @param listComboBoxFields
     *            the {@link List} list tha fill the {@link JFXComboBox}.
     * @param elemNumber
     *            the number of elem in the list
     * @return the {@link QuestionToView}.
     *
     */
    public static QuestionToView getQuestionToView(final Map<ExerciseType, List<String>> mappaTextField,
            final List<List<String>> listComboBoxFields, final int elemNumber) {
        return new QuestionToView(mappaTextField, listComboBoxFields, elemNumber);

    }

    /**
     * @return the mappaTextField
     */
    public Map<ExerciseType, List<String>> getMappaTextField() {
        return this.mappaTextField;
    }

    /**
     * @return the listComboBoxFields
     */
    public List<List<String>> getListComboBoxFields() {
        return this.listComboBoxFields;
    }

    /**
     * @return the elemNumber
     */
    public int getElemNumber() {
        return elemNumber;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "QuestionToView [mappaTextField=" + this.mappaTextField + ", listComboBoxFields="
                + this.listComboBoxFields + "]";
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.listComboBoxFields == null) ? 0 : this.listComboBoxFields.hashCode());
        result = (prime * result) + ((this.mappaTextField == null) ? 0 : this.mappaTextField.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     *
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
        final QuestionToView other = (QuestionToView) obj;
        if (this.listComboBoxFields == null) {
            if (other.listComboBoxFields != null) {
                return false;
            }
        } else if (!this.listComboBoxFields.equals(other.listComboBoxFields)) {
            return false;
        }
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
