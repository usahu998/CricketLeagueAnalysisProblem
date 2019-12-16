package com.bridgelabz.cricketleagueanalysis;

import com.bridgelabz.csvbuilder.CSVBuilderException;
import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class IPLCsvAnalyser {

    List<IPLRecordCsv> iplRecordCsvList = new ArrayList<>();

    public int loadIPLRecords(String csvFilePath) throws IPLRecordException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            CsvToBeanBuilder<IPLRecordCsv> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(IPLRecordCsv.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<IPLRecordCsv> csvToBean = csvToBeanBuilder.build();
            Iterator<IPLRecordCsv> censusCSVIterator = csvToBean.iterator();
            Iterable<IPLRecordCsv> csvIterable = () -> censusCSVIterator;
            return (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
        } catch (IOException e) {
            throw new IPLRecordException(e.getMessage(),
                    IPLRecordException.ExceptionType.CENSUS_FILE_PROBLEM);
        }catch (RuntimeException e) {
            throw new IPLRecordException(e.getMessage(),
                    IPLRecordException.ExceptionType.NO_SUCH_FILE);
        }
    }

    public String getSortedIPLRecords(SortByField.Parameter parameter) throws IPLRecordException {
        Comparator<IPLRecordCsv> censusComparator;
        if (iplRecordCsvList == null || iplRecordCsvList.size() == 0) {
            throw new IPLRecordException("NO_CENSUS_DATA", IPLRecordException.ExceptionType.NO_CENSUS_DATA);
        }
        censusComparator = SortByField.getParameter(parameter);
        ArrayList runCSVList = iplRecordCsvList.stream().
                sorted(censusComparator).collect(Collectors.toCollection(ArrayList::new));
        return new Gson().toJson(runCSVList);
    }
}


