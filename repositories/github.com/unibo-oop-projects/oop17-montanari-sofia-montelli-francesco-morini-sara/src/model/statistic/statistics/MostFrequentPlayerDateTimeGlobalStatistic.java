package model.statistic.statistics;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.statistic.StatisticMatch;
/**
 * Class representing the most frequent player during a period.
 * the period can be this month, today or this year.
 *
 */
public final class MostFrequentPlayerDateTimeGlobalStatistic extends AbstractStatistic<String> {
/**
 * the return value.
 */
    private String value;
    private final Map<String, Integer> matchDataTimeCount;
    private final List<StatisticMatch> matches;
   /**
    * @param dateTime the period that I want study.
    * @param name of statistic.
    * @param matches all the matches played
    */
    public MostFrequentPlayerDateTimeGlobalStatistic(final List<StatisticMatch> matches, final EnumStatistic dateTime, final String name) {
        super(name); 
        if (matches == null) {
            throw new IllegalArgumentException("matches");
        }
        value = "---";
        matchDataTimeCount = new HashMap<>();
        this.matches = matches;
        initialize(dateTime);
    }

    private void initialize(final EnumStatistic dateTime) {
        matches.stream().filter(statistic -> !statistic.getPlayerName().equalsIgnoreCase("Anonymous")).forEach(statistic -> {
            if (!matchDataTimeCount.containsKey(statistic.getPlayerName())) {
                matchDataTimeCount.put(statistic.getPlayerName(), 0);
            }
        });
        if (dateTime.equals(EnumStatistic.MONTH)) {
            this.isMonth();
        }
        if (dateTime.equals(EnumStatistic.YEAR)) {
            this.isYear();
        }
        if (dateTime.equals(EnumStatistic.TODAY)) {
            this.isToday();
        }

        matchDataTimeCount.keySet().stream().filter(playerName -> !matchDataTimeCount.get(playerName).equals(0))
                              .max(Comparator.comparingInt(playerName -> matchDataTimeCount.get(playerName))).ifPresent(maxValue -> value = maxValue);
    }

    private void isMonth() {
        for (final String namePlayer : matchDataTimeCount.keySet()) {
            final List<StatisticMatch> list = matches.stream().filter(statistic -> statistic.getPlayerName().equals(namePlayer)).collect(Collectors.toList());
            matchDataTimeCount.put(namePlayer, new MatchesThisMonthStatistic(list).getValue());
        }
    }

    private void isYear() {
        for (final String namePlayer : matchDataTimeCount.keySet()) {
            final List<StatisticMatch> list = matches.stream().filter(statistic -> statistic.getPlayerName().equals(namePlayer)).collect(Collectors.toList());
            matchDataTimeCount.put(namePlayer, new MatchesThisYearStatistic(list).getValue());
        }
        }

    private void isToday() {
        for (final String namePlayer : matchDataTimeCount.keySet()) {
            final List<StatisticMatch> list = matches.stream().filter(statistic -> statistic.getPlayerName().equals(namePlayer)).collect(Collectors.toList());
            matchDataTimeCount.put(namePlayer, new MatchesTodayStatistic(list).getValue());
        }
    }
    @Override
    public String getValue() {
        return value;
    }


}
