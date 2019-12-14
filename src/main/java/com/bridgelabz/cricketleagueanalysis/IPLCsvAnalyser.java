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
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toCollection;

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
            int numOfEateries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
            return numOfEateries;
        } catch (IOException e) {
            throw new IPLRecordException(e.getMessage(),
                    IPLRecordException.ExceptionType.CENSUS_FILE_PROBLEM);
        }catch (RuntimeException e) {
            throw new IPLRecordException(e.getMessage(),
                    IPLRecordException.ExceptionType.NO_SUCH_FILE);
        }
    }

    /*public String SortIPLRecords(FieldType fieldName) {
        Comparator<CensusDAO> censusComparator = comparatorMap.get(fieldName);
        ArrayList censusDTO = censusStateMap.values().stream()
                .sorted(censusComparator)
                .map(censusDto -> censusDto.getCensusDTO(country))
                .collect(toCollection(ArrayList::new));
        return new Gson().toJson(censusDTO);
    }*/

    public String getAvgWiseSortedIPLRecords() {
        iplRecordCsvList.sort(Comparator.comparing(iplRecordCsv ->  iplRecordCsv.average));
        String sortedStateCensusJson = new Gson().toJson(iplRecordCsvList);
        return  sortedStateCensusJson;

    }
}


