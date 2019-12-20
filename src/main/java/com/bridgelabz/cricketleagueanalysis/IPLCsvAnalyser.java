package com.bridgelabz.cricketleagueanalysis;


import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class IPLCsvAnalyser {

    public enum IPLEntity {BATING, BOWLING}

    Map<String, IPLRecordDAO> iplRecordCsvMap;
    private SortByField.Parameter parameter;
    public IPLEntity iplEntity;

    public IPLCsvAnalyser(IPLEntity iplEntity) {
        this.iplEntity = iplEntity;
    }

    public IPLCsvAnalyser() {
        this.iplRecordCsvMap = new HashMap<String, IPLRecordDAO>();
    }

    public <T> int loadIPLData(String csvFilePath) throws IPLRecordException {
        iplRecordCsvMap = new IPLAdapterFactory().cricketleagueFactory(iplEntity, csvFilePath);
        return iplRecordCsvMap.size();
    }

    public String getSortedIPLRecordsFieldWise(SortByField.Parameter parameter) throws IPLRecordException {
        Comparator<IPLRecordDAO> iplRecordCsvComparator;
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


