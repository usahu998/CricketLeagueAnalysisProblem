package com.bridgelabz.cricketleagueanalysis;

import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class IPLCsvAnalyser {
    public IPLAdapter iplAdapter;

    public void setIplAdapter(IPLAdapter iplAdapter) {
        this.iplAdapter = iplAdapter;
    }

    public enum IPLEntity {BATING, BOWLING}

    Map<String, IPLRecordDAO> iplRecordCsvMap;
    private SortByField.Parameter parameter;
    public IPLEntity iplEntity;

    public IPLCsvAnalyser(IPLEntity iplEntity) {
        this.iplEntity = iplEntity;
    }

//    public IPLCsvAnalyser() {
//        this.iplRecordCsvMap = new HashMap<String, IPLRecordDAO>();
//    }

    public Map<String, IPLRecordDAO> loadIPLRecords(String... csvFilePath) throws IPLRecordException {
        Map<String, IPLRecordDAO> recordDAOMap = this.iplAdapter.loadIPLData(csvFilePath);
        return recordDAOMap;
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


