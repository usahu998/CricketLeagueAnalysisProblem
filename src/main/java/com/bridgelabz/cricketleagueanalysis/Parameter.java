package com.bridgelabz.cricketleagueanalysis;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class SortByField {
static Map<Parameter, Comparator> sortParameterComparator = new HashMap<>();

    public enum Parameter {
        AVG, STRIKE_RATE, CENTURY, FOURS, HALF_CENTURY, HIGH_SCORE, SIX, RUN , SIX_AND_FOURS, SIX_AND_FOURS_WITH_STRIKERATE, AVERAGE_AND_STRIKERATE, RUNS_AND_AVERAGE;
    }

    SortByField() {

    }
    public static Comparator getComparatorForIPL(Parameter field) {

        Comparator<IPLRecordCsv> avgComparator = Comparator.comparing(iplRecordCsv -> iplRecordCsv.average);
        Comparator<IPLRecordCsv> strikeRateComparator = Comparator.comparing(iplRecordCsv -> iplRecordCsv.strikeRate);
        Comparator<IPLRecordCsv> centuryComparator = Comparator.comparing(iplRecordCsv -> iplRecordCsv.century);
        Comparator<IPLRecordCsv> foursComparator = Comparator.comparing(iplRecordCsv -> iplRecordCsv.fours);
        Comparator<IPLRecordCsv> HalfCenturyComparator = Comparator.comparing(iplRecordCsv -> iplRecordCsv.halfCentury);
        Comparator<IPLRecordCsv> highScoreComparator = Comparator.comparing(iplRecordCsv -> iplRecordCsv.highScore);
        Comparator<IPLRecordCsv> sixComparator = Comparator.comparing(iplRecordCsv -> iplRecordCsv.sixes);
        Comparator<IPLRecordCsv> runComparator = Comparator.comparing(iplRecordCsv -> iplRecordCsv.runs);

        sortParameterComparator.put(Parameter.AVG, avgComparator);
        sortParameterComparator.put(Parameter.STRIKE_RATE, strikeRateComparator);
        sortParameterComparator.put(Parameter.CENTURY, centuryComparator);
        sortParameterComparator.put(Parameter.FOURS, foursComparator);
        sortParameterComparator.put(Parameter.HALF_CENTURY, HalfCenturyComparator);
        sortParameterComparator.put(Parameter.HIGH_SCORE, highScoreComparator);
        sortParameterComparator.put(Parameter.SIX, sixComparator);
        sortParameterComparator.put(Parameter.RUN, runComparator);
        sortParameterComparator.put(Parameter.SIX_AND_FOURS, new Maximum4sAnd6sComparator());
        sortParameterComparator.put(Parameter.SIX_AND_FOURS_WITH_STRIKERATE,strikeRateComparator.thenComparing(new Maximum4sAnd6sComparator()));
        sortParameterComparator.put(Parameter.AVERAGE_AND_STRIKERATE,avgComparator.thenComparing(strikeRateComparator));
        sortParameterComparator.put(Parameter.RUNS_AND_AVERAGE,runComparator.thenComparing(avgComparator));

        Comparator<IPLRecordCsv> comparator = sortParameterComparator.get(field);
        return comparator;

    }
}