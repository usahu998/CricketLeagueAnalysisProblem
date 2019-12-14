package com.bridgelabz.cricketleagueanalysis;

import org.junit.Assert;
import org.junit.Test;

public class IPLCsvAnalyserTest {

    private static final String IPL_TEST_CSV_FILE_PATH ="./src/test/resources/TestIPL2019FactsheetMostRuns.csv";
    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        try {
            IPLCsvAnalyser iplCsvAnalyser = new IPLCsvAnalyser();
            iplCsvAnalyser.loadIPLRecords();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
