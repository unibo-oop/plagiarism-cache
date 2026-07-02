package controller.jsoninput;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import model.battlefield.BattlefieldComponent;

/**
 * Interface for the class LocationJSONImpl.
 *
 * @author Andrea Manoni
 *
 */
public interface LocationJSON {

    /**
     * Return an unmodifiable list of Location extracted from the JSON file.
     *
     * @return locationList
     */
    List<BattlefieldComponent> getLocationList();

    /**
     * Load all the data from the JSON file.
     *
     * @throws UnsupportedEncodingException
     *             if the encoding is not supported.
     * @throws FileNotFoundException
     *             if the json file is not found.
     * @throws IOException
     *             if the json file is not found.
     *
     *
     */
    void load() throws UnsupportedEncodingException, FileNotFoundException, IOException;
}
