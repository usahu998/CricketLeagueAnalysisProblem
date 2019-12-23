package com.bridgelabz.cricketleagueanalysis;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class SortByField {
    static Map<Parameter, Comparator> sortParameterComparator = new HashMap<>();

    public enum Parameter {
        AVG, STRIKE_RATE, FOURS, SIX, BATSMAN_RUN, SIX_AND_FOURS, SIX_AND_FOURS_WITH_STRIKERATE, RUNS_AND_AVERAGE, ECONOMY, FIVEWICKET_FOURWICKET_STRIKERATE, BOWLING_AVG_STRIKERATE, MAX_WICKET_BEST_BOWLING, WICKETS, BATTING_AVG, BOWLING_AVG, BOWLING_RUN, BATTING_AVERAGE_AND_STRIKERATE, BOWLING_AVERAGE_AND_STRIKERATE, BATTING_BOWLING_AVERAGE, IPL_BEST_ALLROUNDER;
    }

    SortByField() {
    }

    public static Comparator getComparatorForIPL(Parameter field) {

        Comparator<IPLRecordDAO> batAvgComparator = Comparator.comparing(iplBatsmanRecordCsv -> iplBatsmanRecordCsv.battingAverage);
        Comparator<IPLRecordDAO> bowlAvgComparator = Comparator.comparing(iplBatsmanRecordCsv -> iplBatsmanRecordCsv.bowlingAverage);
        Comparator<IPLRecordDAO> strikeRateComparator = Comparator.comparing(iplBatsmanRecordCsv -> iplBatsmanRecordCsv.strikeRate);
        Comparator<IPLRecordDAO> foursComparator = Comparator.comparing(iplBatsmanRecordCsv -> iplBatsmanRecordCsv.fours);
        Comparator<IPLRecordDAO> sixComparator = Comparator.comparing(iplBatsmanRecordCsv -> iplBatsmanRecordCsv.six);
        Comparator<IPLRecordDAO> batRunComparator = Comparator.comparing(iplBatsmanRecordCsv -> iplBatsmanRecordCsv.batsmanRun);
        Comparator<IPLRecordDAO> bowlRunComparator = Comparator.comparing(iplBatsmanRecordCsv -> iplBatsmanRecordCsv.bowlerRun);
        Comparator<IPLRecordDAO> economyComparator = Comparator.comparing(iplBatsmanRecordCsv -> iplBatsmanRecordCsv.economy);
        Comparator<IPLRecordDAO> wicketComparator = Comparator.comparing(iplBatsmanRecordCsv -> iplBatsmanRecordCsv.wickets);

        sortParameterComparator.put(Parameter.BATTING_AVG, batAvgComparator);
        sortParameterComparator.put(Parameter.BOWLING_AVG, bowlAvgComparator);
        sortParameterComparator.put(Parameter.STRIKE_RATE, strikeRateComparator);
        sortParameterComparator.put(Parameter.FOURS, foursComparator);
        sortParameterComparator.put(Parameter.SIX, sixComparator);
        sortParameterComparator.put(Parameter.BATSMAN_RUN, batRunComparator);
        sortParameterComparator.put(Parameter.BOWLING_RUN, bowlRunComparator);
        sortParameterComparator.put(Parameter.SIX_AND_FOURS, new Maximum4sAnd6sComparator());
        sortParameterComparator.put(Parameter.SIX_AND_FOURS_WITH_STRIKERATE, strikeRateComparator.thenComparing(new Maximum4sAnd6sComparator()));
        sortParameterComparator.put(Parameter.BATTING_AVERAGE_AND_STRIKERATE, batAvgComparator.thenComparing(strikeRateComparator));
        sortParameterComparator.put(Parameter.BOWLING_AVERAGE_AND_STRIKERATE, bowlAvgComparator.thenComparing(strikeRateComparator));
        sortParameterComparator.put(Parameter.RUNS_AND_AVERAGE, batRunComparator.thenComparing(batAvgComparator));
        sortParameterComparator.put(Parameter.RUNS_AND_AVERAGE, bowlRunComparator.thenComparing(bowlAvgComparator));
        sortParameterComparator.put(Parameter.ECONOMY, economyComparator);
        sortParameterComparator.put(Parameter.FIVEWICKET_FOURWICKET_STRIKERATE, strikeRateComparator.thenComparing(new Sort5wAnd4wComparator()));
        sortParameterComparator.put(Parameter.BOWLING_AVG_STRIKERATE, bowlAvgComparator.thenComparing(strikeRateComparator));
        sortParameterComparator.put(Parameter.MAX_WICKET_BEST_BOWLING, wicketComparator.thenComparing(bowlRunComparator));
        sortParameterComparator.put(Parameter.WICKETS, wicketComparator);
        sortParameterComparator.put(Parameter.BATTING_BOWLING_AVERAGE, new SortBattingBowlingAvg());
        sortParameterComparator.put(Parameter.IPL_BEST_ALLROUNDER, new SortIPLALLRounders());


        Comparator<IPLRecordDAO> comparator = sortParameterComparator.get(field);
        return comparator;
    }
}