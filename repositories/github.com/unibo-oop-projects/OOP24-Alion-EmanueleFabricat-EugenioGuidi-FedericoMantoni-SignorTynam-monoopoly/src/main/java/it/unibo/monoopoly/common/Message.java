package it.unibo.monoopoly.common;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.unibo.monoopoly.model.main.api.MainModel;

/**
 * Record of utility, it represents the message directed to {@link MainModel}.
 * 
 * @param typeOfAction that {@link MainModel} have to do.
 * @param data         that {@link MainModel} have to use to do the operation.
 */
public record Message(
                @JsonProperty("action") Event typeOfAction,
                @JsonProperty("data") Optional<Integer> data) {

}
