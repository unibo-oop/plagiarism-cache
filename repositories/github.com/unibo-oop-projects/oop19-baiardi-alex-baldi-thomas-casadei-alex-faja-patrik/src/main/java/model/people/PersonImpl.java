package model.people;

import java.util.Optional;
import java.util.Random;

import model.virus.Virus;

/**
 *
 */
public class PersonImpl implements Person {

    private Status status;
    private Optional<Virus> virus;

    /**
     * Construct a Person with default value.
     */
    public PersonImpl() {
        this.status = Status.SUSCEPTIBLE;
        this.virus = Optional.empty();
    }

    /**
     * 
     */
    @Override
    public Status getStatus() {
        return this.status;
    }

    /**
     * 
     */
    @Override
    public void setStatus(final Status status) {
        this.status = status;
    }

    /**
     * 
     */
    @Override
    public boolean tryToInfect() {
        boolean res = false;
        if (virus.isPresent()) {
            final Random r = new Random();
            res = r.nextDouble() * 100 < virus.get().getInfectivity();
        }
        return res;
    }

    /**
     * 
     */
    @Override
    public void infect(final Virus virus) {
        if (this.status != Status.SUSCEPTIBLE) {
            throw new IllegalStateException("Invalid person status");
        }
        this.virus = Optional.of(virus);
        this.setStatus(Status.INFECTED);
    }

    /**
     * 
     */
    @Override
    public Optional<Virus> getVirus() {
        return virus;
    }

}
