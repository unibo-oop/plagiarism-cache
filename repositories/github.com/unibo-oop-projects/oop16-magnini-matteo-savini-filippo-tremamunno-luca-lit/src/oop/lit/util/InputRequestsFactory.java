package oop.lit.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

/**
 * A factory of input requests, to be implemented by the view.
 */
public interface InputRequestsFactory {
    /**
     * @param label
     *      the label of the inputRequest
     * @param initialValue
     *      the initial value contained, shown to the user.
     * @return
     *      an input request for a string.
     */
    InputRequest<String> getStringInputRequest(String label, Optional<String> initialValue);

    /**
     * @param label
     *      the label of the inputRequest
     * @param initialValue
     *      the initial value contained, shown to the user.
     * @return
     *      an input request for a boolean.
     */
    InputRequest<Boolean> getBooleanInputRequest(String label, Optional<Boolean> initialValue);

    /**
     * @param label
     *      the label of the inputRequest
     * @param initialValue
     *      the initial value contained, shown to the user.
     * @return
     *      an input request for an int.
     */
    InputRequest<Integer> getIntInputRequest(String label, Optional<Integer> initialValue);

    /**
     * @param label
     *      the label of the inputRequest
     * @param initialValue
     *      the initial value contained, shown to the user.
     * @return
     *      an input request for a double.
     */
    InputRequest<Double> getDoubleInputRequest(String label, Optional<Double> initialValue);

    /**
     * Get an inputRequest used to ask user to choose between a set of possible choices, represented by a string.
     * If the user does not make a choice the stored value in the InputRequest will be an empty optional.
     * @param label
     *      the label of the inputRequest 
     * @param choices
     *      a map using as keys a string representation of a possible choice, and as values the corresponding choice.
     * @param initialKey
     *      the initial key, shown to the user.
     * @return
     *      the input request.
     * @param <T>
     *      the type of input needed.
     *
     * @throws IllegalArgumentException
     *      if the provided initialKey is present, but not contained in the map.
     */
    <T> InputRequest<T> getChoiceInputRequest(String label, Map<String, T> choices, Optional<String> initialKey);

    /**
     * Get an inputRequest used to ask user to choose between a set of possible
     * choices, represented by an image. If the user does not make a choice the
     * stored value in the InputRequest will be an empty optional.
     * 
     * @param label
     *            the label of the inputRequest
     * @param choices
     *            a map using as keys a string representation of a possible
     *            choice, and as values a pair containing an image
     *            representetion of the choice and the choice.
     * @param initialKey
     *            the initial key, shown to the user.
     * @return the input request.
     * @param <T>
     *            the type of input needed.
     *
     * @throws IllegalArgumentException
     *             if the provided initialKey is present, but not contained in
     *             the map.
     */
    <T> InputRequest<T> getImageChoiceInputRequest(String label, Map<String, Pair<BufferedImage, T>> choices, Optional<String> initialKey);

    /**
     * Get an InputRequest used to ask user for a file.
     * @param label
     *      the label of the inputRequest
     * @param saveMode
     *      true if the file will be used to save data (false if it will be used to load data).
     * @param fileType
     *      the type of file needed.
     * @return
     *      the input request.
     */
    InputRequest<File> getFileInputRequest(String label, boolean saveMode, FileType fileType);
}
