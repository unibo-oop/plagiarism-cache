package modelimpl.managefilms;

import java.util.Optional;

import model.managefilms.IdsGenerator;
/**
 * Ids Generator generates new progressive ids.
 *  */
public final class IdsGeneratorImpl implements IdsGenerator {
    private Optional<Integer> lastGeneratedId; //last generated id to insert a newFilm. When films container is empty , this value will be null;
    public IdsGeneratorImpl(final Optional<Integer> lastGeneratedId) {
        this.lastGeneratedId = lastGeneratedId;
    }

    public IdsGeneratorImpl() {
        this.lastGeneratedId = Optional.ofNullable(null);
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public int getNewId() {
        if (this.lastGeneratedId.isEmpty()) {
            this.lastGeneratedId = Optional.of(1);
            return this.lastGeneratedId.get();
        }
        int val = this.lastGeneratedId.get();
        val++;
        this.lastGeneratedId = Optional.of(val);
        return lastGeneratedId.get();
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public Optional<Integer> getLastGeneratedId() {
        return lastGeneratedId;
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public String toString() {
        return "IdsGeneratorImpl [lastGeneratedId=" + lastGeneratedId + "]";
    }

}
