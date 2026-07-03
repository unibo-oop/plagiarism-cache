package view;

/**
 * Class to put on hold controller thread for info from view.
 * @param <X> info waited
 */
public class Wait<X> {
    private X returnValue;

    /**
     * Method to advise controller thread that info requested was arrived.
     * @param value info arrived
     */
    public synchronized void actionPerformed(final X value) {
        returnValue = value;
        notifyAll();
    }

    /**
     * Wait as long as the info requested was arrived.
     */
    public synchronized void waitForUser() {
        try {
            returnValue = null;
            while (this.returnValue == null) {
                wait();
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Getter.
     * @return info arrived from view
     */
    public X getValue() {
        return returnValue;
    }

}
