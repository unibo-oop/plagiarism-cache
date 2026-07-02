package it.unibo.aurea.model.dto;

import it.unibo.aurea.model.api.ParameterType;

/** 
 * Immutable model of an effect.
 * 
 * @param parameter the {@code ParameterType} influenced
 * @param delta the weight on the parameter
 */
public record EffectDTO(
    ParameterType parameter,
    int delta) {
}
