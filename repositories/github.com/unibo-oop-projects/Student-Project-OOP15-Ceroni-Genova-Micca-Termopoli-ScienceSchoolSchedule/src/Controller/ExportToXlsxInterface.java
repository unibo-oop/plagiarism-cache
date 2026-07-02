package Controller;

import Model.ErrorException;
import Model.FiledOpenedException;

/**
 * @author Massimiliano Micca
 *
 */
public interface ExportToXlsxInterface {

    /**
     * this is the only method that is externally visible
     * 
     * @param period
     *            this string need to set a excel name end a name of file
     *            "period.xls"
     * @throws FiledOpenedException
     * @throws ErrorException
     * 
     * 
     */
    void save(String period) throws FiledOpenedException, ErrorException;

}
