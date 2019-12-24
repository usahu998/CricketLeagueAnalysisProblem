package com.bridgelabz.cricketleagueanalysis;

import java.util.Map;

public class BowlerAdapter extends IPLAdapter {

    @Override
    public Map<String, IPLRecordDAO> loadIPLData(String... csvFilePath) throws IPLRecordException {
        return super.loadIPLData(IPLBowlingRecordsCsv.class, csvFilePath);
    }
}
