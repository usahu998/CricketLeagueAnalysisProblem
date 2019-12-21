package com.bridgelabz.cricketleagueanalysis;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class IPLCsvAnalyserTest {
    private static final String IPL_BATTING_CSV_FILE_PATH = "/home/admin265/IdeaProjects/Cricket League Analysis Problem/src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_TEST_CSV_FILE_PATH = "./src/test/resources/TestIPL2019FactsheetMostRuns.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/test/resources/TestIPL2019FactsheetMostRuns.csv";
    private static final String WRONG_DELIMITER_CSV_FILE_PATH = "./src/test/resources/IncorrectDelimiterIPL2019FactsheetMostRuns.csv";
    private static final String MISSING_HEADER_CSV_FILE_PATH = "./src/test/resources/HeaderMissingIPL2019FactsheetMostRuns.csv";
    private static final String NON_EXISTING_IPL_CSV_FILE_PATH = "";
    private static final String IPL_BOWLING_CSV_FILE_PATH ="/home/admin265/IdeaProjects/Cricket League Analysis Problem/src/test/resources/IPL2019FactsheetMostWkts.csv";

    @Test
    public void givenIPLBattingCSVFile_ReturnsCorrectRecords() {
        try {
            IPLCsvAnalyser iplCsvAnalyser = new IPLCsvAnalyser(IPLCsvAnalyser.IPLEntity.BATING);
            int numberOfRecords = iplCsvAnalyser.loadIPLRecords(IPL_BATTING_CSV_FILE_PATH);
            System.out.println(numberOfRecords);
            Assert.assertEquals(100, numberOfRecords);
        } catch (IPLRecordException e) {
        }
    }

    @Test
    public void givenIPLRecordCSVFile_WithIncorrectFilePath_ShouldReturnCustomExceptionType() {
        try {
            IPLCsvAnalyser iplAnalyser = new IPLCsvAnalyser(IPLCsvAnalyser.IPLEntity.BATING);
            iplAnalyser.loadIPLRecords(WRONG_CSV_FILE_PATH);
        } catch (IPLRecordException e) {
            Assert.assertEquals(IPLRecordException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void givenIPLRecordCSVFile_WithIncorrectDelimiter_ShouldReturnCustomExceptionType() {
        try {
            IPLCsvAnalyser iplAnalyser = new IPLCsvAnalyser(IPLCsvAnalyser.IPLEntity.BATING);
            iplAnalyser.loadIPLRecords(WRONG_DELIMITER_CSV_FILE_PATH);
        } catch (IPLRecordException e) {
            Assert.assertEquals(IPLRecordException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void givenIPLRecordCSVFile_WithMissingHeaderFile_ShouldReturnCustomExceptionType() {
        try {
            IPLCsvAnalyser iplAnalyser = new IPLCsvAnalyser(IPLCsvAnalyser.IPLEntity.BATING);
            iplAnalyser.loadIPLRecords(MISSING_HEADER_CSV_FILE_PATH);
        } catch (IPLRecordException e) {
            Assert.assertEquals(IPLRecordException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void givenIPLRecordCSVFile_WhenSortedOnBattingAvg_ShouldReturnCorrectDesiredSortedHigestRecord() {
        try {
            IPLCsvAnalyser iplAnalyser = new IPLCsvAnalyser(IPLCsvAnalyser.IPLEntity.BATING);
            iplAnalyser.loadIPLRecords(IPL_BATTING_CSV_FILE_PATH);
            String iplPlayersRecords = iplAnalyser.getSortedIPLRecordsFieldWise(SortByField.Parameter.AVG);
            IPLBatsmanRecordCsv[] mostRunCSVS = new Gson().fromJson(iplPlayersRecords, IPLBatsmanRecordCsv[].class);
            Assert.assertEquals("MS Dhoni", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLRecordException e) {
            Assert.assertEquals(IPLRecordException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void givenIPLRecordCSVFile_WhenSortedOnStrikeRate_ShouldReturnCorrectDesiredSortedHigestRecord() {
        try {
            IPLCsvAnalyser iplAnalyser = new IPLCsvAnalyser(IPLCsvAnalyser.IPLEntity.BATING);
            iplAnalyser.loadIPLRecords(IPL_BATTING_CSV_FILE_PATH);
            String iplPlayersRecords = iplAnalyser.getSortedIPLRecordsFieldWise(SortByField.Parameter.STRIKE_RATE);
            IPLBatsmanRecordCsv[] mostRunCSVS = new Gson().fromJson(iplPlayersRecords, IPLBatsmanRecordCsv[].class);
            Assert.assertEquals("Kedar Jadhav", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLRecordException ignored) {
        }
    }

    @Test
    public void givenIPLRecordCSVFile_WhenSortedOn4SAnd6s_ShouldReturnCorrectDesiredSortedRecord() {
        try {
            IPLCsvAnalyser iplAnalyser = new IPLCsvAnalyser(IPLCsvAnalyser.IPLEntity.BATING);
            iplAnalyser.loadIPLRecords(IPL_BATTING_CSV_FILE_PATH);
            String iplPlayersRecords = iplAnalyser.getSortedIPLRecordsFieldWise(SortByField.Parameter.SIX_AND_FOURS);
            IPLBatsmanRecordCsv[] mostRunCSVS = new Gson().fromJson(iplPlayersRecords, IPLBatsmanRecordCsv[].class);
            Assert.assertEquals("Andre Russell", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLRecordException ignored) {
        }
    }

    @Test
    public void givenIPLRecordCSVFile_WhenSortedOn4sAnd6sWithStrikeRate_ShouldReturnCorrectDesiredSortedHigestData() {
        try {
            IPLCsvAnalyser iplAnalyser = new IPLCsvAnalyser(IPLCsvAnalyser.IPLEntity.BATING);
            iplAnalyser.loadIPLRecords(IPL_BATTING_CSV_FILE_PATH);
            String iplPlayersRecords = iplAnalyser.getSortedIPLRecordsFieldWise(SortByField.Parameter.SIX_AND_FOURS_WITH_STRIKERATE);
            IPLBatsmanRecordCsv[] mostRunCSVS = new Gson().fromJson(iplPlayersRecords, IPLBatsmanRecordCsv[].class);
            Assert.assertEquals("Kedar Jadhav", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLRecordException ignored) {
        }
    }

    @Test
    public void givenIPLRecordCSVFile_WhenSortedGreatAverageWithGreateStrikeRate_ShouldReturnCorrectDesiredSortedHigestData() {
        try {
            IPLCsvAnalyser iplAnalyser = new IPLCsvAnalyser(IPLCsvAnalyser.IPLEntity.BATING);
            iplAnalyser.loadIPLRecords(IPL_BATTING_CSV_FILE_PATH);
            String iplPlayersRecords = iplAnalyser.getSortedIPLRecordsFieldWise(SortByField.Parameter.AVERAGE_AND_STRIKERATE);
            IPLBatsmanRecordCsv[] mostRunCSVS = new Gson().fromJson(iplPlayersRecords, IPLBatsmanRecordCsv[].class);
            Assert.assertEquals("MS Dhoni", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLRecordException ignored) {
        }
    }

    @Test
    public void givenIPLRecordCSVFile_WhenSortedMaxRunAndAverage_ShouldReturnCorrectDesiredSortedHigestData() {
        try {
            IPLCsvAnalyser iplAnalyser = new IPLCsvAnalyser(IPLCsvAnalyser.IPLEntity.BATING);
            iplAnalyser.loadIPLRecords(IPL_BATTING_CSV_FILE_PATH);
            String iplPlayersRecords = iplAnalyser.getSortedIPLRecordsFieldWise(SortByField.Parameter.RUNS_AND_AVERAGE);
            IPLBatsmanRecordCsv[] mostRunCSVS = new Gson().fromJson(iplPlayersRecords, IPLBatsmanRecordCsv[].class);
            Assert.assertEquals("David Warner", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLRecordException ignored) {
        }
    }

    @Test
    public void givenIPLBOWLING_CSVFileReturnsCorrectRecords() {
        try {
            IPLCsvAnalyser iplCsvAnalyser = new IPLCsvAnalyser(IPLCsvAnalyser.IPLEntity.BOWLING);
            int numberOfRecords = iplCsvAnalyser.loadIPLRecords(IPL_BOWLING_CSV_FILE_PATH);
           // System.out.println(numberOfRecords);
            Assert.assertEquals(99, numberOfRecords);
        } catch (IPLRecordException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLRecordCSVFile_WhenSortedOnBowlingAvg_ShouldReturnCorrectDesiredSortedHigestRecord() {
        try {
            IPLCsvAnalyser iplAnalyser = new IPLCsvAnalyser(IPLCsvAnalyser.IPLEntity.BOWLING);
            iplAnalyser.loadIPLRecords(IPL_BOWLING_CSV_FILE_PATH);
            String iplPlayersRecords = iplAnalyser.getSortedIPLRecordsFieldWise(SortByField.Parameter.AVG);
            IPLBowlingRecordsCsv[] mostRunCSVS = new Gson().fromJson(iplPlayersRecords, IPLBowlingRecordsCsv[].class);
            Assert.assertEquals("Krishnappa Gowtham", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLRecordException e) {
            Assert.assertEquals(IPLRecordException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void givenIPLRecordCSVFile_WhenSortedOnBowlingStrikeRate_ShouldReturnCorrectDesiredSortedHigestRecord() {
        try {
            IPLCsvAnalyser iplAnalyser = new IPLCsvAnalyser(IPLCsvAnalyser.IPLEntity.BOWLING);
            iplAnalyser.loadIPLRecords(IPL_BOWLING_CSV_FILE_PATH);
            String iplPlayersRecords = iplAnalyser.getSortedIPLRecordsFieldWise(SortByField.Parameter.STRIKE_RATE);
            IPLBowlingRecordsCsv[] mostRunCSVS = new Gson().fromJson(iplPlayersRecords, IPLBowlingRecordsCsv[].class);
            Assert.assertEquals("Krishnappa Gowtham", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLRecordException ignored) {
        }
    }

}
