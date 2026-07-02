package model.objects.structures;

/**
 * Models every Structure that can produce a specific Resource and has a life
 * duration.
 */
public interface ResourceProducerScalable extends ResourceProducer {

    /**
     * @return whether the structure has finished to produce
     */
    boolean isOver();

    /**
     * @param modifier the modifier to be applied to the production
     * 
     * @return the produced quantity
     */
    int produce(double modifier);

}
