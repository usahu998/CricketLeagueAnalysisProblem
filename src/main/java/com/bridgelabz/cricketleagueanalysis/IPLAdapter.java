package com.bridgelabz.cricketleagueanalysis;

import com.bridgelabz.csvbuilder.CSVBuilderException;
import com.bridgelabz.csvbuilder.CSVBuilderFactory;
import com.bridgelabz.csvbuilder.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public abstract class IPLAdapter {

    public abstract Map<String, IPLRecordDAO> loadIPLData(IPLCsvAnalyser.IPLEntity iplEntity, String csvFilePath) throws IPLRecordException;

    public <T> Map<String, IPLRecordDAO> loadIPLData(Class<T> iplCSVClass, String csvFilePath) throws IPLRecordException {
        Map<String, IPLRecordDAO> iplRecordDAOMap = new HashMap<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator mostRunCSVIterator = csvBuilder.getCSVFileIterator(reader, iplCSVClass);
            Iterable<T> mostRunCSVIterable = () -> mostRunCSVIterator;
            if (iplCSVClass.getName().equals("iplanalysis.MostRunCSV")) {
                StreamSupport.stream(mostRunCSVIterable.spliterator(), false)
                        .map(IPLBatsmanRecordCsv.class::cast)
                        .forEach(mostRunCSV -> iplRecordDAOMap.put(mostRunCSV.player, new IPLRecordDAO(mostRunCSV)));
            } else if (iplCSVClass.getName().equals("iplanalysis.MostWktsCSV")) {
                StreamSupport.stream(mostRunCSVIterable.spliterator(), false)
                        .map(IPLBatsmanRecordCsv.class::cast)
                        .forEach(mostWktsCSV -> iplRecordDAOMap.put(mostWktsCSV.player, new IPLRecordDAO(mostWktsCSV)));
            }
            return iplRecordDAOMap;

        } catch (IOException e) {
            throw new IPLRecordException(e.getMessage(), IPLRecordException.ExceptionType.NO_CENSUS_DATA);
        } catch (RuntimeException e) {
            throw new IPLRecordException(e.getMessage(), IPLRecordException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
        } catch (CSVBuilderException e) {
            throw new IPLRecordException(e.getMessage(), IPLRecordException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }

}
