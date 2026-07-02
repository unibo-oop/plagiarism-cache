package controllerimpl.inputoutput;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Optional;
import com.google.gson.Gson;

import controller.inputoutput.RWobject;


public class RWobjectImpl<X> implements RWobject<X> {
    private final String pathname;
    private final Gson gson;

    public RWobjectImpl(final String pathname) {
        this.pathname = pathname;
        gson = GsonFactory.getMyGson();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void writeObj(final X obj, final Type cls) {
        try (FileWriter writer = new FileWriter(pathname)) {
            gson.toJson(obj, cls, writer);
            } catch (IOException e) {
            e.printStackTrace();
        } 
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<X> readObj(final Type cls) {
        Optional<X> res = Optional.empty();
        if (new File(pathname).exists()) {
            try (Reader reader = new FileReader(pathname)) {
                res = Optional.ofNullable(gson.fromJson(reader, cls));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res;
    } 
}



