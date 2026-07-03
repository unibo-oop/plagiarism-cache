package com.jlearn.model.checker;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.jlearn.model.checker.Checker.Result;

/**
 * CheckLogBuilder implementation.
 */
public class CheckLogBuilderImpl implements CheckLogBuilder {

    private final Map<Integer, Checker.Result> log;
    private boolean built;
    private static final Logger LOG = Logger.getLogger(CheckLogBuilderImpl.class);

    /**
     * The constructor.
     */
    public CheckLogBuilderImpl() {
        this.log = new HashMap<>();
        this.built = false;
        LOG.setLevel(Level.WARN);
        LOG.info("Initialized CheckLog builder");
    }

    @Override
    public CheckLog build() {
        if (this.built) {
            LOG.warn("Can not build twice. Reset first");
            throw new IllegalStateException("Can only build once");
        }

        this.built = true;
        return new CheckLogImpl(this.log);
    }

    private boolean presentID(final int id) {

        return this.log.keySet().contains(id);
    }

    @Override
    public void registerAnswer(final int id, final Result result) {
        if (this.presentID(id)) {
            throw new IllegalArgumentException("this answer id is already registered");
        }
        this.log.put(id, result);
        LOG.info("Answer registered successfully");
    }

    @Override
    public void removeRegisteredAnswer(final int id) {
        if (!this.presentID(id)) {
            throw new IllegalArgumentException("no existing id");
        }
        this.log.remove(id);
        LOG.info("Answer removed successfully");
    }

    @Override
    public void reset() {
        this.built = false;
        this.log.clear();
        LOG.info("CheckLog builder reset successfully");
    }

}
