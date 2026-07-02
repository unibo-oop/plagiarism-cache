package it.unibo.model.impl;

import it.unibo.model.api.ComponentType;
/**
 * Component to manage the immortality of the entity.
 */
public class ImmortalityComponent extends AbstractComponent {
    private static final int STARTTIME = 10_000;
    private long startTime;
    /**
     * Method to set the immortality.
     * @param livesComponent the lives component to set the immortality.
     */
    public void setImmortality(final LivesComponent livesComponent) {
        livesComponent.setImmortality();
        this.startTime = System.currentTimeMillis(); 
    }
    /**
     * Method to check if the immortality is stopped.
     * @param livesComponent the lives component to check.
     */
    public void chekStopImmortality(final LivesComponent livesComponent) {
        if (livesComponent.isImmortality()) {
            final long currentTime = System.currentTimeMillis();
            if (currentTime - this.startTime >= STARTTIME) { 
                livesComponent.setStopImmortality(); 
            }
        }
    }
    /**
     * Returns the component type of this ImmortalityComponent.
     *
     * @return the component type of this ImmortalityComponent
     */
    @Override
    public ComponentType getComponent() {
        return ComponentType.IMMORTALITY;
    }
}
