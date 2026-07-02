package scores;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 
 * Implements of CreateScores.
 *
 */
public class CreateScores implements CreateScoreInterface {

    private final EditFile edit;
    private static final int HOURSECS = 3600;
    private static final int MINUTESECS = 60;

    /**
     * Separator that it's portable.
     */
    public static final String SEP = File.separator;
   
    /**
     * File with best time.
     */
    public static final String FILE_NAME_RANKING = System.getProperty("user.home") + SEP + "ranking";
   
    /**
     * Support file with times in seconds.
     */
    public static final String FILE_NAME_TIMES = System.getProperty("user.home") + SEP + "times";

    /**
     * Constructor of CreateScores.
     */
    public CreateScores() {
        this.edit = new EditFile();
    }

    @Override
    public final void calculateScores(final int hour, final int min, final int sec, final int level)
            throws IOException {
        final int sum = hour * HOURSECS + min * MINUTESECS + sec;

        if (level == 1) {
            final String firstTime = Files.readAllLines(Paths.get(FILE_NAME_TIMES)).get(0);
            final String secondTime = Files.readAllLines(Paths.get(FILE_NAME_TIMES)).get(1);
            final String thirdTime = Files.readAllLines(Paths.get(FILE_NAME_TIMES)).get(2);
            changeBest(1, firstTime, secondTime, thirdTime, hour, min, sec, sum);
        } else if (level == 2) {
            final String firstTime = Files.readAllLines(Paths.get(FILE_NAME_TIMES)).get(4);
            final String secondTime = Files.readAllLines(Paths.get(FILE_NAME_TIMES)).get(5);
            final String thirdTime = Files.readAllLines(Paths.get(FILE_NAME_TIMES)).get(6);
            changeBest(2, firstTime, secondTime, thirdTime, hour, min, sec, sum);
        } else if (level == 3) {
            final String firstTime = Files.readAllLines(Paths.get(FILE_NAME_TIMES)).get(8);
            final String secondTime = Files.readAllLines(Paths.get(FILE_NAME_TIMES)).get(9);
            final String thirdTime = Files.readAllLines(Paths.get(FILE_NAME_TIMES)).get(10);
            changeBest(3, firstTime, secondTime, thirdTime, hour, min, sec, sum);
        } else if (level == 4) {
            final String firstTime = Files.readAllLines(Paths.get(FILE_NAME_TIMES)).get(12);
            final String secondTime = Files.readAllLines(Paths.get(FILE_NAME_TIMES)).get(13);
            final String thirdTime = Files.readAllLines(Paths.get(FILE_NAME_TIMES)).get(14);
            changeBest(4, firstTime, secondTime, thirdTime, hour, min, sec, sum);
        } else {
            final String firstTime = Files.readAllLines(Paths.get(FILE_NAME_TIMES)).get(16);
            final String secondTime = Files.readAllLines(Paths.get(FILE_NAME_TIMES)).get(17);
            final String thirdTime = Files.readAllLines(Paths.get(FILE_NAME_TIMES)).get(18);
            changeBest(5, firstTime, secondTime, thirdTime, hour, min, sec, sum);
        }
    }

    @Override
    public final void changeBest(final int level, final String firstTime, final String secondTime,
            final String thirdTime, final int hour, final int min, final int sec, final int sum) {
        if (firstTime.equals("no time for first lv." + level)) {
            edit.changeFileFirstRanking("no time for first lv." + level, hour, min, sec);
            edit.changeFileFirstTimes("no time for first lv." + level, sum);
        } else if (secondTime.equals("no time for second lv." + level)) {
            final Integer first = Integer.parseInt(firstTime);
            if (sum < first) {
                final int firstHour = first / HOURSECS;
                final int firstMin = (first % HOURSECS) / MINUTESECS;
                final int firstSec = (first % HOURSECS) % MINUTESECS;

                edit.changeFileRanking(firstHour, firstMin, firstSec, hour, min, sec);
                edit.changeFileTimes(first, sum);
                edit.changeFileFirstRanking("no time for second lv." + level, firstHour, firstMin, firstSec);
                edit.changeFileFirstTimes("no time for second lv." + level, first);
            } else {
                edit.changeFileFirstRanking("no time for second lv." + level, hour, min, sec);
                edit.changeFileFirstTimes("no time for second lv." + level, sum);
            }
        } else if (thirdTime.equals("no time for third lv." + level)) {
            final Integer first = Integer.parseInt(firstTime);
            final Integer second = Integer.parseInt(secondTime);
            if (sum < first) {
                final int firstHour = first / HOURSECS;
                final int firstMin = (first % HOURSECS) / MINUTESECS;
                final int firstSec = (first % HOURSECS) % MINUTESECS;
                final int secondHour = second / HOURSECS;
                final int secondMin = (second % HOURSECS) / MINUTESECS;
                final int secondSec = (second % HOURSECS) % MINUTESECS;

                edit.changeFileRanking(firstHour, firstMin, firstSec, hour, min, sec);
                edit.changeFileTimes(first, sum);
                edit.changeFileRanking(secondHour, secondMin, secondSec, firstHour, firstMin, firstSec);
                edit.changeFileTimes(second, first);
                edit.changeFileFirstRanking("no time for third lv." + level, secondHour, secondMin, secondSec);
                edit.changeFileFirstTimes("no time for third lv." + level, second);
            } else if (sum >= first && sum < second) {
                final int secondHour = second / HOURSECS;
                final int secondMin = (second % HOURSECS) / MINUTESECS;
                final int secondSec = (second % HOURSECS) % MINUTESECS;

                edit.changeFileRanking(secondHour, secondMin, secondSec, hour, min, sec);
                edit.changeFileTimes(second, sum);
                edit.changeFileFirstRanking("no time for third lv." + level, secondHour, secondMin, secondSec);
                edit.changeFileFirstTimes("no time for third lv." + level, second);

            } else {
                edit.changeFileFirstRanking("no time for third lv." + level, hour, min, sec);
                edit.changeFileFirstTimes("no time for third lv." + level, sum);
            }
        } else {
            final Integer first = Integer.parseInt(firstTime);
            final Integer second = Integer.parseInt(secondTime);
            final Integer third = Integer.parseInt(thirdTime);

            final int firstHour = first / HOURSECS;
            final int firstMin = (first % HOURSECS) / MINUTESECS;
            final int firstSec = (first % HOURSECS) % MINUTESECS;
            final int secondHour = second / HOURSECS;
            final int secondMin = (second % HOURSECS) / MINUTESECS;
            final int secondSec = (second % HOURSECS) % MINUTESECS;
            final int thirdHour = third / HOURSECS;
            final int thirdMin = (third % HOURSECS) / MINUTESECS;
            final int thirdSec = (third % HOURSECS) % MINUTESECS;

            if (sum < first) {
                int oldFirst = first;
                int oldFirstHour = firstHour;
                int oldFirstMin = firstMin;
                int oldFirstSec = firstSec;
                int oldSecond = second;
                int oldSecondHour = secondHour;
                int oldSecondMin = secondMin;
                int oldSecondSec = secondSec;

                edit.changeFileRanking(firstHour, firstMin, firstSec, hour, min, sec);
                edit.changeFileTimes(first, sum);
                edit.changeFileRanking(secondHour, secondMin, secondSec, oldFirstHour, oldFirstMin, oldFirstSec);
                edit.changeFileTimes(second, oldFirst);
                edit.changeFileRanking(thirdHour, thirdMin, thirdSec, oldSecondHour, oldSecondMin, oldSecondSec);
                edit.changeFileTimes(third, oldSecond);

            } else if (sum >= first && sum < second) {
                int oldSecond = second;
                int oldSecondHour = secondHour;
                int oldSecondMin = secondMin;
                int oldSecondSec = secondSec;

                edit.changeFileRanking(secondHour, secondMin, secondSec, hour, min, sec);
                edit.changeFileTimes(second, sum);
                edit.changeFileRanking(thirdHour, thirdMin, thirdSec, oldSecondHour, oldSecondMin, oldSecondSec);
                edit.changeFileTimes(third, oldSecond);

            } else if (sum >= second && sum < third) {
                edit.changeFileRanking(thirdHour, thirdMin, thirdSec, hour, min, sec);
                edit.changeFileTimes(third, sum);
            }
        }
    }

}
