package com.bridgelabz.cricketleagueanalysis;

import java.util.Comparator;

public class SortIPLALLRounders implements Comparator<IPLRecordDAO> {
    @Override
    public int compare(IPLRecordDAO iplRecordDAO1, IPLRecordDAO iplRecordDAO2) {
        return (int) ((iplRecordDAO1.batsmanRun * iplRecordDAO1.wickets) - (iplRecordDAO2.batsmanRun * iplRecordDAO2.wickets));
    }
}
