package controller;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import model.UserPlayer;
import model.UserPlayerImpl;

/**
 * 
 * This class implements {@link FileLoader}.
 *
 */
public final class FileLoaderImpl implements FileLoader {

    private File userFileName;

    @Override
    public void saveData(final UserPlayer player) {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        try {
            mapper.writeValue(this.userFileName, player);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public UserPlayer loadData(final File userFileName) {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        this.userFileName = userFileName;
        UserPlayer player = null;

        try {
            player = mapper.readValue(this.userFileName, UserPlayerImpl.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return player;
    }

}
