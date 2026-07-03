package model.pkglevels;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.Observable;

import model.pkgplayer.Player;

/**
 * Implementation of the LogInModel interface. Extends Observable so this class
 * can notify the view
 * 
 *
 */
public class LogInModelImpl implements LogInModel {

    @Override
    public int notifyAction(final boolean status, final boolean alreadyRegistered) {
        // utente trovato e sta facendo il login
        if (status && !alreadyRegistered) {

            return 0;
            // utente trovato e ne sto registrando un altro con lo stesso
            // nome(fase di login)
        } else if (status && alreadyRegistered) {

            return 1;
            // utente non esiste e si deve registrare
        } else if (!status && !alreadyRegistered) {

            return 2;
            // utente non trovato e si sta registrando
        } else {

            return 3;
        }

    }

    @Override
    public int readPlayerData(final Player p, final boolean isChecking, final File f) {
        String name = "", pw = "", readedString = "";
        String[] line;
        int reachedLevel = 0;
        int playerScore = 0;
        boolean playerFound = false;

        try (BufferedReader bfr = new BufferedReader(new FileReader(f))) {
            while ((readedString = bfr.readLine()) != null && !playerFound && readedString.length() != 0) {
                line = readedString.split(" ");

                name = line[1];

                pw = line[2];

                reachedLevel = Integer.parseInt(line[0]);
                playerScore = Integer.parseInt(line[3]);
                // player found in the file
                if (name.equals(p.getName()) && pw.equals(p.getPass())) {
                    playerFound = true;
                    p.updateScore(playerScore);
                    p.setLevel(reachedLevel);
                    // player with the same name found in register phase
                } else if (name.equals(p.getName()) && isChecking) {
                    playerFound = true;
                }
            }

        } catch (final FileNotFoundException e1) {

            e1.printStackTrace();
        } catch (final IOException e1) {

            e1.printStackTrace();
        }

        final int ret = notifyAction(playerFound, isChecking);
        return ret;
    }

    @Override
    public int checkInput(final Player p) {
        if (p.getName().length() == 0 || p.getPass().length() == 0) {

            return 1;
        } else {

            return 0;
        }
    }

    @Override
    public int registerPlayerData(final Player registerPlayer, final File f) {

        // true-> registering, have to check if is present...return = 3, no
        // player found with the same name
        if (readPlayerData(registerPlayer, true, f) == 3) {

            try (BufferedWriter bfw = new BufferedWriter(new FileWriter(f, true))) {

                bfw.write(registerPlayer.getLevel() + " " + registerPlayer.getName() + " " + registerPlayer.getPass()
                        + " " + 0);

                bfw.newLine();

            } catch (final IOException e) {

                e.printStackTrace();
            }

            return 3;
        } else {

            return 2;
        }

    }

}
