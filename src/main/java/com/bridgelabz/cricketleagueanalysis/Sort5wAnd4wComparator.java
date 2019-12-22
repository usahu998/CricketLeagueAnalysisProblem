package com.bridgelabz.cricketleagueanalysis;

import java.util.Comparator;

public class Sort5wAnd4wComparator implements Comparator<IPLRecordDAO> {
    @Override
    public int compare(IPLRecordDAO iplRecordDAO1, IPLRecordDAO iplRecordDAO2) {
        return ((iplRecordDAO1.foursWickets + iplRecordDAO1.fivesWickets) - (iplRecordDAO2.foursWickets + iplRecordDAO2.fivesWickets));
    }
}
