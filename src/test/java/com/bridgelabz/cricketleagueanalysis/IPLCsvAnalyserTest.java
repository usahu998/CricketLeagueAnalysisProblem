package com.bridgelabz.cricketleagueanalysis;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class IPLCsvAnalyserTest {
    private static final String IPL_BATTING_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_WRONG_CSV_FILE_PATH = "C:\\Users\\user\\IdeaProjects\\NewCricket\\CricketLeagueAnalysisProblem\\build.gradle";
    private static final String WRONG_CSV_FILE_PATH = "./src/test/resources/TestIPL2019FactsheetMostRuns.csv";
    private static final String WRONG_DELIMITER_CSV_FILE_PATH = "./src/test/resources/IncorrectDelimiterIPL2019FactsheetMostRuns.csv";
    private static final String MISSING_HEADER_CSV_FILE_PATH = "./src/test/resources/HeaderMissingIPL2019FactsheetMostRuns.csv";
    private static final String NON_EXISTING_IPL_CSV_FILE_PATH = "";
    private static final String IPL_BOWLING_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";

    @Test
    public void givenIPLBattingCSVFile_ReturnsCorrectRecords() {
        try {
            IPLAdapter iplAdapterObject = IPLAdapterFactory.createIPLAdapterObject(IPLCsvAnalyser.IPLEntity.BATING);
            IPLCsvAnalyser iplCsvAnalyser = new IPLCsvAnalyser(IPLCsvAnalyser.IPLEntity.BATING);
            iplCsvAnalyser.setIplAdapter(iplAdapterObject);
            Map<String, IPLRecordDAO> daoMap = iplCsvAnalyser.loadIPLRecords(IPL_BATTING_CSV_FILE_PATH, IPL_BOWLING_CSV_FILE_PATH);

            Assert.assertEquals(100, daoMap.size());
        } catch (IPLRecordException e) {
        }
    }

    @Test
    public void givenIPLRecordCSVFile_WithIncorrectFilePath_ShouldReturnCustomExceptionType() {
        try {
            IPLAdapter iplAdapterObject = IPLAdapterFactory.createIPLAdapterObject(IPLCsvAnalyser.IPLEntity.BATING);
            IPLCsvAnalyser iplCsvAnalyser = new IPLCsvAnalyser(IPLCsvAnalyser.IPLEntity.BATING);
            iplCsvAnalyser.setIplAdapter(iplAdapterObject);
            iplCsvAnalyser.loadIPLRecords(WRONG_CSV_FILE_PATH, IPL_BOWLING_CSV_FILE_PATH);
        } catch (IPLRecordException e) {
            Assert.assertEquals(IPLRecordException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void givenIPLRecordCSVFile_WithIncorrectDelimiter_ShouldReturnCustomExceptionType() {
        try {
            IPLAdapter iplAdapterObject = IPLAdapterFactory.createIPLAdapterObject(IPLCsvAnalyser.IPLEntity.BATING);
            IPLCsvAnalyser iplCsvAnalyser = new IPLCsvAnalyser(IPLCsvAnalyser.IPLEntity.BATING);
            iplCsvAnalyser.setIplAdapter(iplAdapterObject);
            iplCsvAnalyser.loadIPLRecords(WRONG_DELIMITER_CSV_FILE_PATH);
        } catch (IPLRecordException e) {
            Assert.assertEquals(IPLRecordException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void givenIPLRecordCSVFile_WithMissingHeaderFile_ShouldReturnCustomExceptionType() {
        try {
            IPLAdapter iplAdapterObject = IPLAdapterFactory.createIPLAdapterObject(IPLCsvAnalyser.IPLEntity.BATING);
            IPLCsvAnalyser iplCsvAnalyser = new IPLCsvAnalyser(IPLCsvAnalyser.IPLEntity.BATING);
            iplCsvAnalyser.setIplAdapter(iplAdapterObject);
            iplCsvAnalyser.loadIPLRecords(MISSING_HEADER_CSV_FILE_PATH, IPL_BOWLING_CSV_FILE_PATH);
        } catch (IPLRecordException e) {
            Assert.assertEquals(IPLRecordException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void givenIPLRecordCSVFile_WhenSortedOnBattingAvg_ShouldReturnCorrectDesiredSortedHigestRecord() {
        try {
            IPLAdapter iplAdapter = IPLAdapterFactory.createIPLAdapterObject(IPLCsvAnalyser.IPLEntity.BATING);
            IPLCsvAnalyser iplAnalyser = new IPLCsvAnalyser();
            iplAnalyser.setIplAdapter(iplAdapter);
            iplAnalyser.loadIPLRecords(IPL_BATTING_CSV_FILE_PATH);
            String iplPlayersRecords = iplAnalyser.getSortedIPLRecordsFieldWise(SortByField.Parameter.BATTING_AVG);
            IPLBatsmanRecordCsv[] mostRunCSVS = new Gson().fromJson(iplPlayersRecords, IPLBatsmanRecordCsv[].class);
            Assert.assertEquals("MS Dhoni", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLRecordException e) {
            Assert.assertEquals(IPLRecordException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void givenIPLRecordCSVFile_WhenSortedOnStrikeRate_ShouldReturnCorrectDesiredSortedHigestRecord() {
        try {
            IPLAdapter iplAdapter = IPLAdapterFactory.createIPLAdapterObject(IPLCsvAnalyser.IPLEntity.BATING);
            IPLCsvAnalyser iplAnalyser = new IPLCsvAnalyser();
            iplAnalyser.setIplAdapter(iplAdapter);
            iplAnalyser.loadIPLRecords(IPL_BATTING_CSV_FILE_PATH, IPL_BOWLING_CSV_FILE_PATH);
            String iplPlayersRecords = iplAnalyser.getSortedIPLRecordsFieldWise(SortByField.Parameter.STRIKE_RATE);
            IPLBatsmanRecordCsv[] mostRunCSVS = new Gson().fromJson(iplPlayersRecords, IPLBatsmanRecordCsv[].class);
            Assert.assertEquals("Ishant Sharma", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLRecordException ignored) {
        }
    }

    @Test
    public void givenIPLRecordCSVFile_WhenSortedOn4SAnd6s_ShouldReturnCorrectDesiredSortedRecord() {
        try {
            IPLAdapter iplAdapter = IPLAdapterFactory.createIPLAdapterObject(IPLCsvAnalyser.IPLEntity.BATING);
            IPLCsvAnalyser iplAnalyser = new IPLCsvAnalyser();
            iplAnalyser.setIplAdapter(iplAdapter);
            iplAnalyser.loadIPLRecords(IPL_BATTING_CSV_FILE_PATH, IPL_BOWLING_CSV_FILE_PATH);
            String iplPlayersRecords = iplAnalyser.getSortedIPLRecordsFieldWise(SortByField.Parameter.SIX_AND_FOURS);
            IPLBatsmanRecordCsv[] mostRunCSVS = new Gson().fromJson(iplPlayersRecords, IPLBatsmanRecordCsv[].class);
            Assert.assertEquals("Andre Russell", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLRecordException ignored) {
        }
    }

    @Test
    public void givenIPLRecordCSVFile_WhenSortedOn4sAnd6sWithStrikeRate_ShouldReturnCorrectDesiredSortedHigestData() {
        try {
            IPLAdapter iplAdapter = IPLAdapterFactory.createIPLAdapterObject(IPLCsvAnalyser.IPLEntity.BATING);
            IPLCsvAnalyser iplAnalyser = new IPLCsvAnalyser();
            iplAnalyser.setIplAdapter(iplAdapter);
            iplAnalyser.loadIPLRecords(IPL_BATTING_CSV_FILE_PATH, IPL_BOWLING_CSV_FILE_PATH);
            String iplPlayersRecords = iplAnalyser.getSortedIPLRecordsFieldWise(SortByField.Parameter.SIX_AND_FOURS_WITH_STRIKERATE);
            IPLBatsmanRecordCsv[] mostRunCSVS = new Gson().fromJson(iplPlayersRecords, IPLBatsmanRecordCsv[].class);
            Assert.assertEquals("Ishant Sharma", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLRecordException ignored) {
        }
    }

    @Test
    public void givenIPLRecordCSVFile_WhenSortedGreatAverageWithGreateStrikeRate_ShouldReturnCorrectDesiredSortedHigestData() {
        try {
            IPLAdapter iplAdapter = IPLAdapterFactory.createIPLAdapterObject(IPLCsvAnalyser.IPLEntity.BATING);
            IPLCsvAnalyser iplAnalyser = new IPLCsvAnalyser();
            iplAnalyser.setIplAdapter(iplAdapter);
            iplAnalyser.loadIPLRecords(IPL_BATTING_CSV_FILE_PATH, IPL_BOWLING_CSV_FILE_PATH);
            String iplPlayersRecords = iplAnalyser.getSortedIPLRecordsFieldWise(SortByField.Parameter.BATTING_AVERAGE_AND_STRIKERATE);
            IPLBatsmanRecordCsv[] mostRunCSVS = new Gson().fromJson(iplPlayersRecords, IPLBatsmanRecordCsv[].class);
            Assert.assertEquals("MS Dhoni", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLRecordException ignored) {
        }
    }

    @Test
    public void givenIPLRecordCSVFile_WhenSortedMaxRunAndAverage_ShouldReturnCorrectDesiredSortedHigestData() {
        try {
            IPLAdapter iplAdapter = IPLAdapterFactory.createIPLAdapterObject(IPLCsvAnalyser.IPLEntity.BATING);
            IPLCsvAnalyser iplAnalyser = new IPLCsvAnalyser();
            iplAnalyser.setIplAdapter(iplAdapter);
            iplAnalyser.loadIPLRecords(IPL_BATTING_CSV_FILE_PATH, IPL_BOWLING_CSV_FILE_PATH);
            String iplPlayersRecords = iplAnalyser.getSortedIPLRecordsFieldWise(SortByField.Parameter.RUNS_AND_AVERAGE);
            IPLBatsmanRecordCsv[] mostRunCSVS = new Gson().fromJson(iplPlayersRecords, IPLBatsmanRecordCsv[].class);
            Assert.assertEquals("Shreyas Iyer", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLRecordException ignored) {
        }
    }

    @Test
    public void givenIPLBOWLING_CSVFileReturnsCorrectRecords() {
        try {
            IPLAdapter iplAdapterObject = IPLAdapterFactory.createIPLAdapterObject(IPLCsvAnalyser.IPLEntity.BOWLING);
            IPLCsvAnalyser iplCsvAnalyser = new IPLCsvAnalyser(IPLCsvAnalyser.IPLEntity.BOWLING);
            iplCsvAnalyser.setIplAdapter(iplAdapterObject);
            Map<String, IPLRecordDAO> daoMap = iplCsvAnalyser.loadIPLRecords(IPL_BOWLING_CSV_FILE_PATH, IPL_BOWLING_CSV_FILE_PATH);
            Assert.assertEquals(86, daoMap.size());
        } catch (IPLRecordException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLRecordCSVFile_WhenSortedOnBowlingAvg_ShouldReturnCorrectDesiredSortedHigestRecord() {
        try {
            IPLAdapter iplAdapterObject = IPLAdapterFactory.createIPLAdapterObject(IPLCsvAnalyser.IPLEntity.BOWLING);
            IPLCsvAnalyser iplCsvAnalyser = new IPLCsvAnalyser(IPLCsvAnalyser.IPLEntity.BOWLING);
            iplCsvAnalyser.setIplAdapter(iplAdapterObject);
            iplCsvAnalyser.loadIPLRecords(IPL_BOWLING_CSV_FILE_PATH, IPL_BOWLING_CSV_FILE_PATH);
            String iplPlayersRecords = iplCsvAnalyser.getSortedIPLRecordsFieldWise(SortByField.Parameter.BOWLING_AVG);
            IPLBowlingRecordsCsv[] mostRunCSVS = new Gson().fromJson(iplPlayersRecords, IPLBowlingRecordsCsv[].class);
            Assert.assertEquals("Krishnappa Gowtham", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLRecordException e) {
            Assert.assertEquals(IPLRecordException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void givenIPLRecordCSVFile_WhenSortedOnBowlingStrikeRate_ShouldReturnCorrectDesiredSortedHigestRecord() {
        try {
            IPLAdapter iplAdapterObject = IPLAdapterFactory.createIPLAdapterObject(IPLCsvAnalyser.IPLEntity.BOWLING);
            IPLCsvAnalyser iplCsvAnalyser = new IPLCsvAnalyser(IPLCsvAnalyser.IPLEntity.BOWLING);
            iplCsvAnalyser.setIplAdapter(iplAdapterObject);
            iplCsvAnalyser.loadIPLRecords(IPL_BOWLING_CSV_FILE_PATH, IPL_BOWLING_CSV_FILE_PATH);
            String iplPlayersRecords = iplCsvAnalyser.getSortedIPLRecordsFieldWise(SortByField.Parameter.STRIKE_RATE);
            IPLBowlingRecordsCsv[] mostRunCSVS = new Gson().fromJson(iplPlayersRecords, IPLBowlingRecordsCsv[].class);
            Assert.assertEquals("Krishnappa Gowtham", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLRecordException ignored) {
        }
    }

    @Test
    public void givenIPLRecordCSVFile_WhenSortedOnBestEconomyRate_ShouldReturnCorrectDesiredSortedHigestRecord() {
        try {
            IPLAdapter iplAdapterObject = IPLAdapterFactory.createIPLAdapterObject(IPLCsvAnalyser.IPLEntity.BOWLING);
            IPLCsvAnalyser iplCsvAnalyser = new IPLCsvAnalyser(IPLCsvAnalyser.IPLEntity.BOWLING);
            iplCsvAnalyser.setIplAdapter(iplAdapterObject);
            iplCsvAnalyser.loadIPLRecords(IPL_BOWLING_CSV_FILE_PATH, IPL_BOWLING_CSV_FILE_PATH);
            String iplPlayersRecords = iplCsvAnalyser.getSortedIPLRecordsFieldWise(SortByField.Parameter.ECONOMY);
            IPLBowlingRecordsCsv[] mostRunCSVS = new Gson().fromJson(iplPlayersRecords, IPLBowlingRecordsCsv[].class);
            Assert.assertEquals("Ben Cutting", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLRecordException ignored) {
        }
    }

    @Test
    public void givenIPLRecordCSVFile_WhenSortedOn5WAnd4WWithStrikeRate_ShouldReturnCorrectDesiredSortedHigestData() {
        try {
            IPLAdapter iplAdapterObject = IPLAdapterFactory.createIPLAdapterObject(IPLCsvAnalyser.IPLEntity.BOWLING);
            IPLCsvAnalyser iplCsvAnalyser = new IPLCsvAnalyser(IPLCsvAnalyser.IPLEntity.BOWLING);
            iplCsvAnalyser.setIplAdapter(iplAdapterObject);
            iplCsvAnalyser.loadIPLRecords(IPL_BOWLING_CSV_FILE_PATH, IPL_BOWLING_CSV_FILE_PATH);
            String iplPlayersRecords = iplCsvAnalyser.getSortedIPLRecordsFieldWise(SortByField.Parameter.FIVEWICKET_FOURWICKET_STRIKERATE);
            IPLBowlingRecordsCsv[] mostRunCSVS = new Gson().fromJson(iplPlayersRecords, IPLBowlingRecordsCsv[].class);
            Assert.assertEquals("Krishnappa Gowtham", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLRecordException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLRecordCSVFile_WhenGreatBowlingAvgWithBestStrikeRates_ShouldReturnCorrectDesiredSortedHigestData() {
        try {
            IPLAdapter iplAdapterObject = IPLAdapterFactory.createIPLAdapterObject(IPLCsvAnalyser.IPLEntity.BOWLING);
            IPLCsvAnalyser iplCsvAnalyser = new IPLCsvAnalyser(IPLCsvAnalyser.IPLEntity.BOWLING);
            iplCsvAnalyser.setIplAdapter(iplAdapterObject);
            iplCsvAnalyser.loadIPLRecords(IPL_BOWLING_CSV_FILE_PATH, IPL_BOWLING_CSV_FILE_PATH);
            String iplPlayersRecords = iplCsvAnalyser.getSortedIPLRecordsFieldWise(SortByField.Parameter.BOWLING_AVG_STRIKERATE);
            IPLBowlingRecordsCsv[] mostRunCSVS = new Gson().fromJson(iplPlayersRecords, IPLBowlingRecordsCsv[].class);
            Assert.assertEquals("Krishnappa Gowtham", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLRecordException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLRecordCSVFile_WhenMaxWicketWithBestBowling_ShouldReturnCorrectDesiredSortedHigestData() {
        try {
            IPLAdapter iplAdapterObject = IPLAdapterFactory.createIPLAdapterObject(IPLCsvAnalyser.IPLEntity.BOWLING);
            IPLCsvAnalyser iplCsvAnalyser = new IPLCsvAnalyser(IPLCsvAnalyser.IPLEntity.BOWLING);
            iplCsvAnalyser.setIplAdapter(iplAdapterObject);
            iplCsvAnalyser.loadIPLRecords(IPL_BOWLING_CSV_FILE_PATH, IPL_BOWLING_CSV_FILE_PATH);
            String iplPlayersRecords = iplCsvAnalyser.getSortedIPLRecordsFieldWise(SortByField.Parameter.MAX_WICKET_BEST_BOWLING);
            IPLBowlingRecordsCsv[] mostRunCSVS = new Gson().fromJson(iplPlayersRecords, IPLBowlingRecordsCsv[].class);
            Assert.assertEquals("Imran Tahir", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLRecordException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLMOstWktsCSVFile_WhenSortedOnBattingAndBowlingAvg_ShouldReturnCorrectDesiredSortedData() {
        try {
            IPLAdapter iplAdapterObject = IPLAdapterFactory.createIPLAdapterObject(IPLCsvAnalyser.IPLEntity.BATING);
            IPLCsvAnalyser iplCsvAnalyser = new IPLCsvAnalyser(IPLCsvAnalyser.IPLEntity.BATING);
            iplCsvAnalyser.setIplAdapter(iplAdapterObject);
            iplCsvAnalyser.loadIPLRecords(IPL_BATTING_CSV_FILE_PATH, IPL_BOWLING_CSV_FILE_PATH);
            String iplPlayersRecords = iplCsvAnalyser.getSortedIPLRecordsFieldWise(SortByField.Parameter.BATTING_BOWLING_AVERAGE);
            IPLBatsmanRecordCsv[] mostRunCSVS = new Gson().fromJson(iplPlayersRecords, IPLBatsmanRecordCsv[].class);
            Assert.assertEquals("Shreyas Iyer", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLRecordException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLRecordCSVFile_WhenBestAllRounderMostRunsAndWicket_ShouldReturnCorrectDesiredSortedHigestData() {
        try {
            IPLAdapter iplAdapterObject = IPLAdapterFactory.createIPLAdapterObject(IPLCsvAnalyser.IPLEntity.BATING);
            IPLCsvAnalyser iplCsvAnalyser = new IPLCsvAnalyser(IPLCsvAnalyser.IPLEntity.BATING);
            iplCsvAnalyser.setIplAdapter(iplAdapterObject);
            iplCsvAnalyser.loadIPLRecords(IPL_BATTING_CSV_FILE_PATH, IPL_BOWLING_CSV_FILE_PATH);
            String iplpLayersRecords = iplCsvAnalyser.getSortedIPLRecordsFieldWise(SortByField.Parameter.IPL_BEST_ALLROUNDER);
            IPLBatsmanRecordCsv[] mostRunCSVS = new Gson().fromJson(iplpLayersRecords, IPLBatsmanRecordCsv[].class);
            Assert.assertEquals("Shreyas Iyer", mostRunCSVS[mostRunCSVS.length - 1].player);
        } catch (IPLRecordException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLRecordCSVFile_WithNoSuchFile_ShouldReturnCustomExceptionType() {
        try {
            IPLAdapter iplAdapterObject = IPLAdapterFactory.createIPLAdapterObject(IPLCsvAnalyser.IPLEntity.BATING);
            IPLCsvAnalyser iplCsvAnalyser = new IPLCsvAnalyser(IPLCsvAnalyser.IPLEntity.BATING);
            iplCsvAnalyser.setIplAdapter(iplAdapterObject);
            iplCsvAnalyser.getSortedIPLRecordsFieldWise(SortByField.Parameter.STRIKE_RATE);
        } catch (IPLRecordException e) {
            Assert.assertEquals(IPLRecordException.ExceptionType.NO_CENSUS_DATA, e.type);
        }
    }

    @Test
    public void givenIPLRecordCSVFile_WithCsvFileInternalIssue_ShouldReturnCustomExceptionType() {
        try {
            IPLAdapter iplAdapterObject = IPLAdapterFactory.createIPLAdapterObject(IPLCsvAnalyser.IPLEntity.BATING);
            IPLCsvAnalyser iplCsvAnalyser = new IPLCsvAnalyser(IPLCsvAnalyser.IPLEntity.BATING);
            iplCsvAnalyser.setIplAdapter(iplAdapterObject);
            iplCsvAnalyser.iplAdapter.loadIPLData(IPLBatsmanRecordCsv.class, null);
        } catch (IPLRecordException e) {
            Assert.assertEquals(IPLRecordException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
        }
    }

    @Test
    public void givenIPLRecordCSVFile_WithCsvFileNoRecord_ShouldReturnCustomExceptionType() {
        try {
            IPLAdapter iplAdapterObject = IPLAdapterFactory.createIPLAdapterObject(IPLCsvAnalyser.IPLEntity.BATING);
            IPLCsvAnalyser iplCsvAnalyser = new IPLCsvAnalyser(IPLCsvAnalyser.IPLEntity.BATING);
            iplCsvAnalyser.setIplAdapter(iplAdapterObject);
            iplCsvAnalyser.iplAdapter.loadIPLData(IPLBatsmanRecordCsv.class, NON_EXISTING_IPL_CSV_FILE_PATH);
        } catch (IPLRecordException e) {
            Assert.assertEquals(IPLRecordException.ExceptionType.NO_CENSUS_DATA, e.type);
        }
    }
}
