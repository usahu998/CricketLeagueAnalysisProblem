package com.bridgelabz.cricketleagueanalysis;

import java.util.Comparator;

public class Maximum4sAnd6sComparator implements Comparator<IPLBatsmanRecordCsv> {
    @Override
    public int compare(IPLBatsmanRecordCsv iplBatsmanRecordCsv1, IPLBatsmanRecordCsv iplBatsmanRecordCsv2) {
        return (((iplBatsmanRecordCsv1.sixes * 6) + (iplBatsmanRecordCsv1.fours * 4)) - ((iplBatsmanRecordCsv2.sixes * 6) + (iplBatsmanRecordCsv2.fours * 4)));
    }
}
