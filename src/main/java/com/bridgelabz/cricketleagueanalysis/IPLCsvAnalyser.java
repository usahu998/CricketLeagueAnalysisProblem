package com.bridgelabz.cricketleagueanalysis;

import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class IPLCsvAnalyser {

    public enum IPLEntity {BATING, BOWLING}

    Map<String, IPLRecordDAO> iplRecordCsvMap = new HashMap<>();
    private SortByField.Parameter parameter;
    public IPLEntity iplEntity;

    public IPLCsvAnalyser(IPLEntity iplEntity) {
        this.iplEntity = iplEntity;
    }

    public IPLCsvAnalyser() {
    }

    public <T> int loadIPLRecords(String csvFilePath) throws IPLRecordException {
        iplRecordCsvMap = new IPLAdapterFactory().cricketLeagueFactory(iplEntity, csvFilePath);
        return iplRecordCsvMap.size();
    }

    public String getSortedIPLRecordsFieldWise(SortByField.Parameter parameter) throws IPLRecordException {
        Comparator<IPLRecordDAO> iplRecordCsvComparator = null;
        if (iplRecordCsvMap == null || iplRecordCsvMap.size() == 0) {
            throw new IPLRecordException("NO_CENSUS_DATA", IPLRecordException.ExceptionType.NO_CENSUS_DATA);
        }
        iplRecordCsvComparator = SortByField.getComparatorForIPL(parameter);

        List iplRecordList = iplRecordCsvMap.values().stream()
                .sorted(iplRecordCsvComparator)
                .collect(Collectors.toList());
        System.out.println(iplRecordList.size());
        String iplRecordLists = new Gson().toJson(iplRecordList);
        return iplRecordLists;
    }
}


