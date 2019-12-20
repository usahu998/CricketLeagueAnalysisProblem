package com.bridgelabz.cricketleagueanalysis;


import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class IPLCsvAnalyser {

    List<IPLRecordCsv> iplRecordCsvList = new ArrayList<>();
    Map< String, IPLRecordCsv> iplRecordCsvMap = new HashMap<>();

    public int loadIPLRecords(String csvFilePath) throws IPLRecordException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            CsvToBeanBuilder<IPLRecordCsv> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(IPLRecordCsv.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<IPLRecordCsv> csvToBean = csvToBeanBuilder.build();
            Iterator<IPLRecordCsv> iplRecordCsvIterator = csvToBean.iterator();
            Iterable<IPLRecordCsv> csvIterable = () -> iplRecordCsvIterator;
            StreamSupport.stream(csvIterable.spliterator(),false)
                    .map(IPLRecordCsv.class::cast)
                    .forEach(iplRuns -> this.iplRecordCsvMap.put(iplRuns.player,iplRuns));
            return iplRecordCsvMap.size();
        } catch (IOException e) {
            throw new IPLRecordException(e.getMessage(),
                    IPLRecordException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new IPLRecordException(e.getMessage(),
                    IPLRecordException.ExceptionType.NO_SUCH_FILE);
        }
    }

    public String getSortedIPLRecordsFieldWise(SortByField.Parameter parameter) throws IPLRecordException {
        Comparator<IPLRecordCsv> iplRecordCsvComparator;
        if (iplRecordCsvMap == null || iplRecordCsvMap.size() == 0) {
            throw new IPLRecordException("NO_CENSUS_DATA", IPLRecordException.ExceptionType.NO_CENSUS_DATA);
        }
        iplRecordCsvComparator = SortByField.getComparatorForIPL(parameter);
        ArrayList iplRecordDTO = iplRecordCsvMap.values().stream()
                .sorted(iplRecordCsvComparator)
                .collect(Collectors.toCollection(ArrayList::new));
        return new Gson().toJson(iplRecordDTO);
    }
}


