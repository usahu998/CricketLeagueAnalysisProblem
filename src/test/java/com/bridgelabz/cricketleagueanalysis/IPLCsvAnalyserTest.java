package com.bridgelabz.cricketleagueanalysis;

import org.junit.Assert;
import org.junit.Test;

public class IPLCsvAnalyserTest {

    private static final String IPL_TEST_CSV_FILE_PATH ="./src/test/resources/TestIPL2019FactsheetMostRuns.csv";
    private static final String WRONG_CSV_FILE_PATH="./src/test/resources/TestIPL2019FactsheetMostRuns.csv";
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

    @Test
    public void givenIPLRecordCSVFile_WithIncorrectFilePath_ShouldReturnCustomExceptionType() {
        try {
            IPLCsvAnalyser iplAnalyser = new IPLCsvAnalyser();
            iplAnalyser.loadIPLRecords(WRONG_CSV_FILE_PATH);
        } catch (IPLRecordException e) {
            Assert.assertEquals(IPLRecordException.ExceptionType.NO_SUCH_FILE, e.type);
        }

    }
}
