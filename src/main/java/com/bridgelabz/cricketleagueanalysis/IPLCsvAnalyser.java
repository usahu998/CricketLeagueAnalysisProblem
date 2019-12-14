package com.bridgelabz.cricketleagueanalysis;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class IPLCsvAnalyser {
    public int loadIPLRecords(String csvFilePath) throws IPLRecordException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            CsvToBeanBuilder<IPLRecordCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(IPLRecordCSV.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<IPLRecordCSV> csvToBean = csvToBeanBuilder.build();
            Iterator<IPLRecordCSV> censusCSVIterator = csvToBean.iterator();
            Iterable<IPLRecordCSV> csvIterable = () -> censusCSVIterator;
            int numOfEateries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
            return numOfEateries;
        } catch (IOException e) {
            throw new IPLRecordException(e.getMessage(),
                    IPLRecordException.ExceptionType.CENSUS_FILE_PROBLEM);
        }catch (RuntimeException e) {
            throw new IPLRecordException(e.getMessage(),
                    IPLRecordException.ExceptionType.UNABLE_TO_CAPTURE_CSV_HEADER);
        }
    }
}


