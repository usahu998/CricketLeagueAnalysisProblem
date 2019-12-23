package com.bridgelabz.cricketleagueanalysis;

import java.util.Comparator;

public class SortBattingBowlingAvg implements Comparator<IPLRecordDAO>  {
    @Override
    public int compare(IPLRecordDAO iplRecordDAO1, IPLRecordDAO iplRecordDAO2) {
        return (int) (iplRecordDAO1.battingAverage - (1d/iplRecordDAO1.bowlingAverage) - (iplRecordDAO2.battingAverage - (1d/iplRecordDAO2.bowlingAverage)));
    }
}
