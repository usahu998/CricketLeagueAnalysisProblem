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
    public Map<String, IPLRecordDAO> loadIPLData(String... csvFilePath) throws IPLRecordException {
        return super.loadIPLData(IPLBatsmanRecordCsv.class, csvFilePath[0]);
    }
}
