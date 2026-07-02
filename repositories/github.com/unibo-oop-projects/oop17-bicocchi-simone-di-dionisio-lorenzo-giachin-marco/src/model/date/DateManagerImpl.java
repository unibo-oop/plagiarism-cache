package model.date;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * interface DataManager.
 *
 */
public class DateManagerImpl implements DateManager, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private long date;

    /**
     * constructor of datamangerImpl.
     */
    public DateManagerImpl() {
        this.date = new Date().getTime();
    }

    /**
     * 
     */
    public void setUpdateDate() {
        this.date = new Date().getTime();
    }

    /**
     * @return the update date of character
     */
    public double getTimeToMod() {
        double now = new Date().getTime() - date;
        setUpdateDate();
        return now / 1000;
    }
}
