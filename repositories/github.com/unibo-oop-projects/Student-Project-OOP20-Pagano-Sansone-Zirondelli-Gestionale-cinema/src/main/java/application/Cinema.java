package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

import controllerimpl.CinemaControllerImpl;
import utilitiesimpl.GeneralSettings;

public final class Cinema {

    private Cinema() {
    }
    public static void main(final String[] args) throws IOException {
        /*URL url = ClassLoader.getSystemResource(GeneralSettings.ACCOUNTSTANDARD);
        File accounts = new File(GeneralSettings.ACCOUNT_FILE_PATH);
        FileUtils.copyURLToFile(url, accounts);*/
        new CinemaControllerImpl();
    }
}
