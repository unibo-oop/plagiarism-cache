package model.effects.convolution;

import model.effects.Effect;

/**
 * Represents the contract for a generic effect.
 */
public interface Convolution extends Effect {

    /**
     * @param kernel the convolution kernel in order to apply convolution
     * @return modified object itself
     */
    Convolution setKernel(ConvolutionKernel kernel);

    /**
     * @param edgeCondition the specified edge condition
     * @return modified object itself
     */
    Convolution setEdgeCondition(int edgeCondition);

    /**
     * @return the convolution kernel
     */
    ConvolutionKernel getKernel();

    /**
     * @return the specified edge condition
     */
    int getEdgeCondition();

}
