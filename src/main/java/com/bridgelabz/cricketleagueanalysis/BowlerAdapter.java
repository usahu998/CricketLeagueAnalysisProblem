package com.bridgelabz.cricketleagueanalysis;

import java.util.Map;

public class BowlerAdapter extends IPLAdapter {

    @Override
    public Map<String, IPLRecordDAO> loadIPLData( String... csvFilePath) throws IPLRecordException {
        Map<String, IPLRecordDAO> recordDAOMap = super.loadIPLData(IPLBowlingRecordsCsv.class, csvFilePath);
        return recordDAOMap;
    }
}
