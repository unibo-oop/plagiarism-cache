package maingame.statistics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Implementazioni statistiche.
 */
public final class StatisticsImpl implements Statistics {
    /*
     * oggetto singleton con statistiche.
     */
    private static final  Statistics STATISTIC = new StatisticsImpl();

    private static final String STRKILL = "Kills";
    private int kill;
    private static final String STRSTEPS = "Steps";
    private int steps;
    private static final String STRPROJECTILE = "Projectile shoots";
    private int projectile;
    private static final String STRTIME = "Time played";
    private int hour;
    private int minute;
    private int second;
    private static final String STRMAXSCORE = "Max score";
    private int maxScore;
    private static final int SEC60 = 60;
    private static final String NEW_DIR = "/jGame";
    private static final String FILE_STATS = "/stats.txt";
    private static final String SEPARATOR = System.getProperty("line.separator");
    private static final String HOME_FOLDER = "user.home";
    /**
     * @return singleton di StatisticImpl
     */
    public static Statistics getStatistics() {
        return STATISTIC;
    }

    private StatisticsImpl() {
        try {
            final File file = new File(System.getProperty(HOME_FOLDER) + NEW_DIR + FILE_STATS);
            readStats(file);
        } catch (Exception e) {
            try {
                new File(System.getProperty(HOME_FOLDER) + NEW_DIR).mkdir();
                writeFileStats(new File(System.getProperty(HOME_FOLDER) + NEW_DIR + FILE_STATS));
            } catch (final Exception e1) {
                this.resetStats();
            }
        }
    }

    private void writeFileStats(final File file) {
        try {
            final BufferedWriter write = new BufferedWriter(new FileWriter(file));
            write.write(STRKILL + SEPARATOR + kill + SEPARATOR);
            write.write(STRSTEPS + SEPARATOR + steps + SEPARATOR);
            write.write(STRPROJECTILE + SEPARATOR + projectile + SEPARATOR);
            write.write(STRTIME + SEPARATOR + hour + SEPARATOR + minute
                    + SEPARATOR + second + SEPARATOR);
            write.write(STRMAXSCORE + SEPARATOR + maxScore + SEPARATOR);
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readStats(final File file) throws IOException {
        final BufferedReader read = new BufferedReader(new FileReader(file));
        String leggi;
        try {
            leggi = read.readLine();
            while (leggi != null) {
                switch (leggi) {
                case STRKILL:
                    this.kill = Integer.parseInt(read.readLine());
                    break;
                case STRSTEPS:
                    this.steps = Integer.parseInt(read.readLine());
                    break;
                case STRPROJECTILE:
                    this.projectile = Integer.parseInt(read.readLine());
                    break;
                case STRTIME:
                    this.hour = Integer.parseInt(read.readLine());
                    this.minute = Integer.parseInt(read.readLine());
                    this.second = Integer.parseInt(read.readLine());
                    break;
                case STRMAXSCORE:
                    this.maxScore = Integer.parseInt(read.readLine());
                default:
                    break;
                }
                leggi = read.readLine();
            }
        } catch (final Exception e) {
            read.close();
            writeFileStats(file);
        }
        read.close();
    }

    @Override
    public void incrementKill(final int nKill) {
        if (nKill > 0) {
            this.kill += nKill;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void incrementSteps(final int nSteps) {
        if (nSteps > 0) {
            this.steps += nSteps;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void incrementTime(final int second) {
        if (second > 0) {
            final int hours = second / (SEC60 * SEC60);
            final int min = (second % (SEC60 * SEC60)) / SEC60;
            final int sec = (second % (SEC60 * SEC60)) % SEC60;
            this.second += sec;
            if (this.second >= SEC60) {
                this.second -= SEC60;
                this.minute++;
            }
            this.minute += min;
            if (this.minute >= SEC60) {
                this.minute -= SEC60;
                this.hour++;
            }
            this.hour += hours;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void incrementProjectile(final int projectile) {
        if (projectile > 0) {
            this.projectile += projectile;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void updateMaxScore(final int score) {
        if (score > this.maxScore) {
            this.maxScore = score;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int getKill() {
        return this.kill;
    }

    @Override
    public int getSteps() {
        return this.steps;
    }

    @Override
    public String getTime() {
        return this.hour + ":" + (this.minute < 10 ? "0" + this.minute : this.minute) 
                         + ":" + (this.second < 10 ? "0" + this.second : this.second);
    }

    @Override
    public int getProjectile() {
        return this.projectile;
    }

    @Override
    public int getMaxScore() {
        return this.maxScore;
    }

    @Override
    public void save() {
        writeFileStats(new File(System.getProperty(HOME_FOLDER) + NEW_DIR + FILE_STATS));
    }

    @Override
    public void resetStats() {
        this.kill = 0;
        this.maxScore = 0;
        this.second = 0;
        this.minute = 0;
        this.hour = 0;
        this.projectile = 0;
        this.steps = 0;
        this.save();
    }
}