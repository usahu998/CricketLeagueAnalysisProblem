package com.bridgelabz.cricketleagueanalysis;

import java.util.Map;

public class BatsmanAdapter extends IPLAdapter {
    @Override
    public Map<String, IPLRecordDAO> loadIPLData(IPLCsvAnalyser.IPLEntity iplEntity, String csvFilePath) throws IPLRecordException {
        Map<String, IPLRecordDAO> recordDAOMap = super.loadIPLData(IPLBatsmanRecordCsv.class, csvFilePath);
        return recordDAOMap;
    }
}
