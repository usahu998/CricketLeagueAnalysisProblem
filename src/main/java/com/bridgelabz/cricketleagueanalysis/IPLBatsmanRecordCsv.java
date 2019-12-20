package com.bridgelabz.cricketleagueanalysis;

import com.opencsv.bean.CsvBindByName;

public class IPLBatsmanRecordCsv {

    @CsvBindByName(column = " PLAYER ")
    public String player;
    @CsvBindByName(column = " Mat ")
    public int matches;
    @CsvBindByName(column = " Inns ")
    public  int innings;
    @CsvBindByName(column = " NO ")
    public int notOut;
    @CsvBindByName(column = " Runs ")
    public int runs;
    @CsvBindByName(column = " HS ")
    public String  highScore;
    @CsvBindByName(column = " Avg ",required = true)
    public double average;
    @CsvBindByName(column = " BF ")
    public double ballFaced;
    @CsvBindByName(column = " SR ")
    public String strikeRate;
    @CsvBindByName(column = " 100 ")
    public int century;
    @CsvBindByName(column = " 50 ")
    public int halfCentury;
    @CsvBindByName(column = " 4s ")
    public int fours;
    @CsvBindByName(column = " 6s ")
    public int sixes;

    public IPLBatsmanRecordCsv() {
    }
}
