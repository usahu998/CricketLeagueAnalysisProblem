package com.bridgelabz.cricketleagueanalysis;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class IPLCsvAnalyserTest {
    private static final String IPL_CSV_FILE_PATH = "/home/admin265/IdeaProjects/Cricket League Analysis Problem/src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_TEST_CSV_FILE_PATH = "./src/test/resources/TestIPL2019FactsheetMostRuns.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/test/resources/TestIPL2019FactsheetMostRuns.csv";
    private static final String WRONG_DELIMITER_CSV_FILE_PATH = "./src/test/resources/IncorrectDelimiterIPL2019FactsheetMostRuns.csv";
    private static final String MISSING_HEADER_CSV_FILE_PATH = "./src/test/resources/HeaderMissingIPL2019FactsheetMostRuns.csv";
    private static final String NON_EXISTING_IPL_CSV_FILE_PATH = "";

    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        try {
            IPLCsvAnalyser iplCsvAnalyser = new IPLCsvAnalyser();
            int numberOfRecords = iplCsvAnalyser.loadIPLRecords(IPL_CSV_FILE_PATH);
            System.out.println(numberOfRecords);
            Assert.assertEquals(100, numberOfRecords);
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

    @Test
    public void givenIPLRecordCSVFile_WithIncorrectDelimiter_ShouldReturnCustomExceptionType() {
        try {
            IPLCsvAnalyser iplAnalyser = new IPLCsvAnalyser();
            iplAnalyser.loadIPLRecords(WRONG_DELIMITER_CSV_FILE_PATH);
        } catch (IPLRecordException e) {
            Assert.assertEquals(IPLRecordException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void givenIPLRecordCSVFile_WithMissingHeaderFile_ShouldReturnCustomExceptionType() {
        try {
            IPLCsvAnalyser iplAnalyser = new IPLCsvAnalyser();
            iplAnalyser.loadIPLRecords(MISSING_HEADER_CSV_FILE_PATH);
        } catch (IPLRecordException e) {
            Assert.assertEquals(IPLRecordException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void givenIPLRecordCSVFile_withNonExisting_ShouldReturnCustomExceptionType() {
        try {
            IPLCsvAnalyser iplAnalyser = new IPLCsvAnalyser();
            iplAnalyser.loadIPLRecords(NON_EXISTING_IPL_CSV_FILE_PATH);
        } catch (IPLRecordException e) {
            Assert.assertEquals(IPLRecordException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void givenIPLRecordCSVFile_WhenSortedOnAvg_ShouldReturnCorrectDesiredSortedLowestRecord() {
        try {
            IPLCsvAnalyser iplAnalyser = new IPLCsvAnalyser();
            iplAnalyser.loadIPLRecords(IPL_CSV_FILE_PATH);
            String iplPlayersRecords = iplAnalyser.getSortedIPLRecordsFieldWise(SortByField.Parameter.AVG);
            IPLRecordCsv[] mostRunCSVS = new Gson().fromJson(iplPlayersRecords, IPLRecordCsv[].class);
            Assert.assertEquals("Ishant Sharma", mostRunCSVS[0].player);
        } catch (IPLRecordException e) {
            Assert.assertEquals(IPLRecordException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void givenIPLRecordCSVFile_WhenSortedOnAvg_ShouldReturnCorrectDesiredSortedHigestRecord() {
        try {
            IPLCsvAnalyser iplAnalyser = new IPLCsvAnalyser();
            iplAnalyser.loadIPLRecords(IPL_CSV_FILE_PATH);
            String iplPlayersRecords = iplAnalyser.getSortedIPLRecordsFieldWise(SortByField.Parameter.AVG);
            IPLRecordCsv[] mostRunCSVS = new Gson().fromJson(iplPlayersRecords, IPLRecordCsv[].class);
            Assert.assertEquals("MS Dhoni", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLRecordException e) {
            Assert.assertEquals(IPLRecordException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void givenIPLRecordCSVFile_WhenSortedOnStrikeRate_ShouldReturnCorrectDesiredSortedHigestRecord() {
        try {
            IPLCsvAnalyser iplAnalyser = new IPLCsvAnalyser();
            iplAnalyser.loadIPLRecords(IPL_CSV_FILE_PATH);
            String iplPlayersRecords = iplAnalyser.getSortedIPLRecordsFieldWise(SortByField.Parameter.STRIKE_RATE);
            IPLRecordCsv[] mostRunCSVS = new Gson().fromJson(iplPlayersRecords, IPLRecordCsv[].class);
            Assert.assertEquals("Kedar Jadhav", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLRecordException ignored) {
        }
    }

    @Test
    public void givenIPLRecordCSVFile_WhenSortedOn4SAnd6s_ShouldReturnCorrectDesiredSortedRecord() {
        try {
            IPLCsvAnalyser iplAnalyser = new IPLCsvAnalyser();
            iplAnalyser.loadIPLRecords(IPL_TEST_CSV_FILE_PATH);
            String iplPlayersRecords = iplAnalyser.getSortedIPLRecordsFieldWise(SortByField.Parameter.SIX_AND_FOURS);
            IPLRecordCsv[] mostRunCSVS = new Gson().fromJson(iplPlayersRecords, IPLRecordCsv[].class);
            Assert.assertEquals("Andre Russell", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLRecordException ignored) {
        }
    }

    @Test
    public void givenIPLRecordCSVFile_WhenSortedOn4sAnd6sWithStrikeRate_ShouldReturnCorrectDesiredSortedHigestData() {
        try {
            IPLCsvAnalyser iplAnalyser = new IPLCsvAnalyser();
            iplAnalyser.loadIPLRecords(IPL_CSV_FILE_PATH);
            String iplPlayersRecords = iplAnalyser.getSortedIPLRecordsFieldWise(SortByField.Parameter.SIX_AND_FOURS_WITH_STRIKERATE);
            IPLRecordCsv[] mostRunCSVS = new Gson().fromJson(iplPlayersRecords, IPLRecordCsv[].class);
            Assert.assertEquals("Kedar Jadhav", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLRecordException ignored) {
        }
    }


}
