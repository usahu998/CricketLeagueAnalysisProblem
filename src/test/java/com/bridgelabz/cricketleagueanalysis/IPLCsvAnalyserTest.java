package com.bridgelabz.cricketleagueanalysis;

import org.junit.Assert;
import org.junit.Test;

public class IPLCsvAnalyserTest {

    private static final String IPL_TEST_CSV_FILE_PATH ="./src/test/resources/TestIPL2019FactsheetMostRuns.csv";
    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        try {
            IPLCsvAnalyser iplCsvAnalyser = new IPLCsvAnalyser();
           int numberOfRecords = iplCsvAnalyser.loadIPLRecords(IPL_TEST_CSV_FILE_PATH);
           Assert.assertEquals(10,numberOfRecords);
        } catch (RuntimeException e) {
        } catch (IPLRecordException e) {
        }
    }
}
