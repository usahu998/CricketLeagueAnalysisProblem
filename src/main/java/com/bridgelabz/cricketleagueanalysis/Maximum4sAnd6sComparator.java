package com.bridgelabz.cricketleagueanalysis;

import java.util.Comparator;

public class Maximum4sAnd6sComparator implements Comparator<IPLRecordCsv> {
    @Override
    public int compare(IPLRecordCsv iplRecordCsv1, IPLRecordCsv iplRecordCsv2) {
        return (((iplRecordCsv1.sixes * 6) + (iplRecordCsv1.fours * 4)) - ((iplRecordCsv2.sixes * 6) + (iplRecordCsv2.fours * 4)));
    }
}
