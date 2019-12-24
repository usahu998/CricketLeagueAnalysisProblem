package com.bridgelabz.cricketleagueanalysis;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IPLCsvAnalyserMockTest {

    private static final String IPL_BATTING_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_TEST_CSV_FILE_PATH = "./src/test/resources/TestIPL2019FactsheetMostRuns.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/test/resources/TestIPL2019FactsheetMostRuns.csv";
    private static final String WRONG_DELIMITER_CSV_FILE_PATH = "./src/test/resources/IncorrectDelimiterIPL2019FactsheetMostRuns.csv";
    private static final String MISSING_HEADER_CSV_FILE_PATH = "./src/test/resources/HeaderMissingIPL2019FactsheetMostRuns.csv";
    private static final String NON_EXISTING_IPL_CSV_FILE_PATH = "";
    private static final String IPL_BOWLING_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";

    @Mock
    private IPLAdapterFactory iplAdapterFactory;

    @InjectMocks
    private IPLCsvAnalyser iplAnalyser;

    Map<String, IPLRecordDAO> BatsmanMp;
    Map<String, IPLRecordDAO> BowlerMap=null;

    Map<SortByField.Parameter, Comparator> sortParameterComparator ;

    @Before
    public void setUp() {
        this.BatsmanMp = new HashMap<>();
        BowlerMap=new HashMap<>();
        sortParameterComparator = new HashMap<>();
        this.BatsmanMp.put("Ajju", new IPLRecordDAO("Ajju",14,12.2,12));
        this.BatsmanMp.put("Somesh", new IPLRecordDAO("Somesh",14,12.2,12));
        this.BatsmanMp.put("Aneesh", new IPLRecordDAO("Aneesh",14,12.2,12));

        this.BowlerMap.put("Rahul", new IPLRecordDAO("Rahul",14,4.5,9.7));
        this.BowlerMap.put("Vikek", new IPLRecordDAO("Vivek",14,4.5,9.7));
        this.BowlerMap.put("Dikshant", new IPLRecordDAO("Dikshant",14,4.5,9.7));

        Comparator<IPLRecordDAO> avgcomparator =  Comparator.comparing(mostruncsv -> mostruncsv.battingAverage);
        sortParameterComparator.put(SortByField.Parameter.BATTING_AVG,avgcomparator);

        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void givenIPLBatsRunsCSVFile_ShouldReturnCorrectRecords() {
        try {
            IPLAdapter ipAdapter = IPLAdapterFactory.createIPLAdapterObject(IPLCsvAnalyser.IPLEntity.BATING);

            IPLAdapter iplAdapter = mock(ipAdapter.getClass());
            when(iplAdapter.loadIPLData( IPL_BATTING_CSV_FILE_PATH)).thenReturn(this.BatsmanMp);
            IPLCsvAnalyser iplAnalyser = new IPLCsvAnalyser(IPLCsvAnalyser.IPLEntity.BATING);
            iplAnalyser.setIplAdapter(iplAdapter);
            Map<String, IPLRecordDAO> iplRecordDAOMap = iplAnalyser.loadIPLRecords(IPL_BATTING_CSV_FILE_PATH);
            Assert.assertEquals(3, iplRecordDAOMap.size());
        } catch (  IPLRecordException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLBallRunsCSVFile_ShouldReturnCorrectRecords() {
        try {
            IPLAdapter ipAdapter = IPLAdapterFactory.createIPLAdapterObject(IPLCsvAnalyser.IPLEntity.BOWLING);

            IPLAdapter iplAdapter = mock(ipAdapter.getClass());
            when(iplAdapter.loadIPLData( IPL_BOWLING_CSV_FILE_PATH)).thenReturn(this.BowlerMap);
            IPLCsvAnalyser iplAnalyser = new IPLCsvAnalyser(IPLCsvAnalyser.IPLEntity.BOWLING);
            iplAnalyser.setIplAdapter(iplAdapter);
            Map<String, IPLRecordDAO> iplRecordDAOMap = iplAnalyser.loadIPLRecords(IPL_BOWLING_CSV_FILE_PATH);
            Assert.assertEquals(3, iplRecordDAOMap.size());
        } catch (  IPLRecordException e) {
            e.printStackTrace();
        }
    }

}
