package utilities;

import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.Mixer;

/**
 * Allow to play/stop/pause/resume clips (even looped).
*/
public final class Audio {

    private static final String DEFAULT_PATH = "/audio/";
    private static boolean enabled = true;
    private static Map<Song, Clip> clips = new HashMap<>();     //keep the clip relative to every song on play state
    private static Map<Song, Pair<Clip, Long>> resume = new HashMap<>(); //keep the position of tracks on pause state

    private Audio() {

    }

    /**
     * All the song that could be used in the game.
     */
    public enum Song {
        /***/
        BACKGROUND("background.wav"), BUTTON_CLICK("button_click.wav"), END_MATCH("win.wav");


        private final String path;

        Song(final String path) {
            this.path = path;
        }

        /**
         * 
         * @return .
         */
        public URL getURL() {
            return ResourceLoader.getURL(DEFAULT_PATH + path);
        }
    }

    /**
     * Allow to play a clip.
     * @param song the song that should be played.
     * @param loop true if the song should be looped.
     */
    public static synchronized void playSound(final Audio.Song song, final boolean loop) {
        if (!enabled) {
            return;
        } else {
            try {
              final String osName = System.getProperty("os.name");
              final Clip clip;
              final AudioInputStream inputStream = AudioSystem.getAudioInputStream(song.getURL());
              if (osName.toUpperCase(Locale.ENGLISH).contains("WINDOWS")) {
                clip = AudioSystem.getClip();
              } else {  //linux require line info
                  final AudioFormat format = inputStream.getFormat();
                  final DataLine.Info info = new DataLine.Info(Clip.class, format);
                  clip = (Clip) AudioSystem.getLine(info);
              }
              clip.open(inputStream);
              if (loop) {
                  clip.loop(Clip.LOOP_CONTINUOUSLY);
              }
              clip.start();
              clips.put(song, clip);

              clip.addLineListener(new LineListener() {

                @Override
                public void update(final LineEvent event) {
                    if (event.getType().equals(LineEvent.Type.STOP)) {
                        clips.remove(song);
                        //clip.close();
                    }
                }
            });
            } catch (Exception e) {
              System.err.println(e.getMessage());
            }
        }
    }

    /**
     * Allow to stop a song that is currently playing.
     * @param song the song that should be interrupted
     */
    public static void stopSound(final Song song) {
        final Clip clip = clips.get(song);
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.drain();
            clip.close();
        }
    }

    /**
     * Allow to pause a song that is currently playing.
     * @param song the song that should be interrupted
     */
    public static void pauseSound(final Song song) {
        final Clip clip = clips.get(song);
        if (clip != null && clip.isRunning()) {
            resume.put(song, new Pair<>(clip, clip.getMicrosecondPosition()));
            clip.stop();
        }
    }

    /**
     * Allow to resume a song currently paused.
     * @param song the song that should be interrupted
     * @param loop if the song should be looped
     */
    public static void resumeSong(final Song song, final boolean loop) {
        if (resume.containsKey(song)) {
            final Pair<Clip, Long> clip = resume.get(song);
            clip.getFirst().setMicrosecondPosition(clip.getSecond());
            clip.getFirst().start();
            if (loop) {
                clip.getFirst().loop(Clip.LOOP_CONTINUOUSLY);
            }
            clips.put(song, clip.getFirst());
            resume.remove(song);
        }
    }


    /**
     * Switch the volume of all clip (mute/unmute).
     * @param enabled true enabled, false disabled.
     */
    public static void enableSound(final boolean enabled) {
        new Thread() {
            @Override
            public void run() {
                final Mixer.Info[] infos = AudioSystem.getMixerInfo();
                for (final Mixer.Info info: infos) {
                    final Mixer mixer = AudioSystem.getMixer(info);
                    for (final Line line: mixer.getSourceLines()) {
                        final BooleanControl bc = (BooleanControl) line.getControl(BooleanControl.Type.MUTE);
                        if (bc != null) {
                            bc.setValue(enabled ^ true); // true to mute the line, false to unmute
                        }
                    }
                }
            }
        }.start();
        Audio.enabled = enabled;
    }

    /**
     * @return true if the sound is currently enabled, false otherwise.
     */
    public static boolean isEnabled() {
        return Audio.enabled;
    }
}
