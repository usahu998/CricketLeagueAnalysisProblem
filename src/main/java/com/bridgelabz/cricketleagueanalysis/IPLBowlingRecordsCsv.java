package com.bridgelabz.cricketleagueanalysis;

import com.opencsv.bean.CsvBindByName;

public class IPLBowlingRecordsCsv {

    @CsvBindByName(column = " PLAYER ")
    public String player;
    @CsvBindByName(column = " Mat ")
    public int matches;
    @CsvBindByName(column = " Inns ")
    public int innings;
    @CsvBindByName(column = " Ov ")
    public double Over;
    @CsvBindByName(column = " Runs ")
    public int runs;
    @CsvBindByName(column = " Wkts ")
    public String wickets;
    @CsvBindByName(column = " BBI ")
    public double bestBowlingInning;
    @CsvBindByName(column = " Avg ", required = true)
    public double average;
    @CsvBindByName(column = " Econ ")
    public double economy;
    @CsvBindByName(column = " SR ")
    public double strikeRate;
    @CsvBindByName(column = " 4w ")
    public int foursWickets;
    @CsvBindByName(column = " 5w ")
    public int fivesWickets;

    public IPLBowlingRecordsCsv() {
    }
}
