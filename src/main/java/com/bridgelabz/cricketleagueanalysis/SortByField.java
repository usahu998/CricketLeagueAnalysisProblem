package com.bridgelabz.cricketleagueanalysis;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class SortByField {
    static Map<Parameter, Comparator> sortParameterComparator = new HashMap<>();

    public enum Parameter {
        AVG, STRIKE_RATE, FOURS, SIX, RUN, SIX_AND_FOURS, SIX_AND_FOURS_WITH_STRIKERATE, AVERAGE_AND_STRIKERATE, RUNS_AND_AVERAGE, ECONOMY, FIVEWICKET_FOURWICKET_STRIKERATE, BOWLING_AVG_STRIKERATE, MAX_WICKET_BEST_BOWLING, WICKETS;
    }

    SortByField() {
    }

    public static Comparator getComparatorForIPL(Parameter field) {

        Comparator<IPLRecordDAO> avgComparator = Comparator.comparing(iplBatsmanRecordCsv -> iplBatsmanRecordCsv.average);
        Comparator<IPLRecordDAO> strikeRateComparator = Comparator.comparing(iplBatsmanRecordCsv -> iplBatsmanRecordCsv.strikeRate);
        Comparator<IPLRecordDAO> foursComparator = Comparator.comparing(iplBatsmanRecordCsv -> iplBatsmanRecordCsv.fours);
        Comparator<IPLRecordDAO> sixComparator = Comparator.comparing(iplBatsmanRecordCsv -> iplBatsmanRecordCsv.six);
        Comparator<IPLRecordDAO> runComparator = Comparator.comparing(iplBatsmanRecordCsv -> iplBatsmanRecordCsv.runs);
        Comparator<IPLRecordDAO> economyComparator = Comparator.comparing(iplBatsmanRecordCsv -> iplBatsmanRecordCsv.economy);
        Comparator<IPLRecordDAO> wicketComparator = Comparator.comparing(iplBatsmanRecordCsv -> iplBatsmanRecordCsv.wickets);

        sortParameterComparator.put(Parameter.AVG, avgComparator);
        sortParameterComparator.put(Parameter.STRIKE_RATE, strikeRateComparator);
        sortParameterComparator.put(Parameter.FOURS, foursComparator);
        sortParameterComparator.put(Parameter.SIX, sixComparator);
        sortParameterComparator.put(Parameter.RUN, runComparator);
        sortParameterComparator.put(Parameter.SIX_AND_FOURS, new Maximum4sAnd6sComparator());
        sortParameterComparator.put(Parameter.SIX_AND_FOURS_WITH_STRIKERATE, strikeRateComparator.thenComparing(new Maximum4sAnd6sComparator()));
        sortParameterComparator.put(Parameter.AVERAGE_AND_STRIKERATE, avgComparator.thenComparing(strikeRateComparator));
        sortParameterComparator.put(Parameter.RUNS_AND_AVERAGE, runComparator.thenComparing(avgComparator));
        sortParameterComparator.put(Parameter.ECONOMY, economyComparator);
        sortParameterComparator.put(Parameter.FIVEWICKET_FOURWICKET_STRIKERATE, strikeRateComparator.thenComparing(new Sort5wAnd4wComparator()));
        sortParameterComparator.put(Parameter.BOWLING_AVG_STRIKERATE, avgComparator.thenComparing(strikeRateComparator));
        sortParameterComparator.put(Parameter.MAX_WICKET_BEST_BOWLING, wicketComparator.thenComparing(avgComparator));
        sortParameterComparator.put(Parameter.WICKETS, wicketComparator);

        Comparator<IPLRecordDAO> comparator = sortParameterComparator.get(field);
        return comparator;
    }
}