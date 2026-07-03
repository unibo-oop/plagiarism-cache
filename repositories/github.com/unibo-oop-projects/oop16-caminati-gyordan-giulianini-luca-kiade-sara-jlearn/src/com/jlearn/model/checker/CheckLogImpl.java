package com.jlearn.model.checker;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.jlearn.model.checker.Checker.Result;

/**
 *
 * A class made to trace exercises checking information.
 *
 */
public class CheckLogImpl implements CheckLog {
    /**
     *
     */
    private static final long serialVersionUID = 5781585993112827730L;
    private final Map<Integer, Checker.Result> log;
    private static final Logger LOG = Logger.getLogger(CheckLogImpl.class);

    /**
     * The constructor.
     *
     * @param log
     *            a map having a question ID as key and the related result as value
     */
    public CheckLogImpl(final Map<Integer, Checker.Result> log) {
        LOG.setLevel(Level.WARN);
        this.log = log;
        LOG.info("Initialized CheckLog");
    }

    @Override
    public int getAnswersNumberByResult(final Result result) {
        return this.getAnswersIDsByResult(result).size();
    }

    @Override
    public List<Integer> getAnswersIDsByResult(final Result result) {

        return this.log.entrySet()
                .stream()
                .filter(x -> x.getValue().equals(result))
                .map(x -> x.getKey())
                .collect(Collectors.toList());

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.log == null) ? 0 : this.log.hashCode());
        return result;
    }

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
        final CheckLogImpl other = (CheckLogImpl) obj;
        if (this.log == null) {
            if (other.log != null) {
                return false;
            }
        } else if (!this.log.equals(other.log)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CheckLogImpl [log=" + this.log + "]";
    }

}
