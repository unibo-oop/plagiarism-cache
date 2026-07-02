package controller;

import model.ImmutableModel;
import model.ModelFactory;

/**
 * Abstract implementation of {@link SpecificController} ready to be extended.
 */
public abstract class AbstractController implements SpecificController {

    @Override
    public final ImmutableModel getModel() {
        return ModelFactory.getModelShow();
    }

    @Override
    public final MasterController getMasterController() {
        return MasterControllerImpl.getInstance();
    }

}
