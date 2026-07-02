package controller.map;

import java.util.Optional;

import util.mapbuilder.AbstractCaseBuilder;

/**
 * 
 * An implementation for the interface VariableCasePart.
 *
 */
public class VariableCasePartBuilder extends AbstractCaseBuilder<VariableCasePart> {

    private boolean built;

    /**{@inheritDoc}**/@Override
    public VariableCasePart build() {
        if (this.built) {
            throw new IllegalStateException();
        }
        this.built = true;
        return new VariableCasePart() {

            /**{@inheritDoc}**/@Override
            public Optional<String> getTop() {
                return VariableCasePartBuilder.super.getTop();
            }

            /**{@inheritDoc}**/@Override
            public Optional<String> getBottom() {
                return VariableCasePartBuilder.super.getBottom();
            }

            /**{@inheritDoc}**/@Override
            public Optional<String> getBorder() {
                return VariableCasePartBuilder.super.getBorder();
            }
        };
    }

}
