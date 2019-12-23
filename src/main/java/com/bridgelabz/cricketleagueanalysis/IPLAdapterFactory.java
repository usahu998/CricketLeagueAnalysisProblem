package com.bridgelabz.cricketleagueanalysis;

import java.util.Map;

public class IPLAdapterFactory {
    public Map<String, IPLRecordDAO> cricketLeagueFactory(IPLCsvAnalyser.IPLEntity iplEntity, String... csvFilePath) throws IPLRecordException {
        if (iplEntity.equals(IPLCsvAnalyser.IPLEntity.BATING))
            return new BatsmanAdapter().loadIPLData(iplEntity, csvFilePath);
        else if (iplEntity.equals(IPLCsvAnalyser.IPLEntity.BOWLING))
            return new BowlerAdapter().loadIPLData(iplEntity, csvFilePath);
        return null;
    }
}
