package com.bridgelabz.cricketleagueanalysis;

import java.util.Comparator;

public class Maximum4sAnd6sComparator implements Comparator<IPLRecordDAO> {
    @Override
    public int compare(IPLRecordDAO iplBatsmanRecordCsv1, IPLRecordDAO iplBatsmanRecordCsv2) {
        return (((iplBatsmanRecordCsv1.six * 6) + (iplBatsmanRecordCsv1.fours * 4)) - ((iplBatsmanRecordCsv2.six * 6) + (iplBatsmanRecordCsv2.fours * 4)));
    }
}
