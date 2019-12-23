package com.bridgelabz.cricketleagueanalysis;

import com.bridgelabz.csvbuilder.CSVBuilderException;
import com.bridgelabz.csvbuilder.CSVBuilderFactory;
import com.bridgelabz.csvbuilder.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class BatsmanAdapter extends IPLAdapter {
    @Override
    public Map<String, IPLRecordDAO> loadIPLData(IPLCsvAnalyser.IPLEntity iplEntity, String... csvFilePath) throws IPLRecordException {
        Map<String, IPLRecordDAO> recordDAOMap = super.loadIPLData(IPLBatsmanRecordCsv.class, csvFilePath[0]);
        if (csvFilePath.length == 2)
            this.loadMostWKTSCSV(recordDAOMap, csvFilePath[1]);
        return recordDAOMap;
    }

    private int loadMostWKTSCSV(Map<String, IPLRecordDAO> recordDAOMap, String csvFilePath) throws IPLRecordException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IPLBowlingRecordsCsv> wktsCSVIterator = csvBuilder.getCSVFileIterator(reader, IPLBowlingRecordsCsv.class);
            Iterable<IPLBowlingRecordsCsv> wktsCSVS = () -> wktsCSVIterator;
            StreamSupport.stream(wktsCSVS.spliterator(), false)
                    .filter(csvIPL -> recordDAOMap.get(csvIPL.player) != null)
                    .forEach(mostWktsCSV -> {
                        recordDAOMap.get(mostWktsCSV.player).bowlingAverage = mostWktsCSV.average;
                        recordDAOMap.get(mostWktsCSV.player).wickets = mostWktsCSV.wickets;
                    });
            return recordDAOMap.size();
        } catch (IOException e) {
            throw new IPLRecordException(e.getMessage(),
                    IPLRecordException.ExceptionType.IPL_FILE_PROBLEM);
        } catch (RuntimeException | CSVBuilderException e) {
            throw new IPLRecordException(e.getMessage(), IPLRecordException.ExceptionType.IPL_FILE_INTERNAL_ISSUES);
        }
    }

}
