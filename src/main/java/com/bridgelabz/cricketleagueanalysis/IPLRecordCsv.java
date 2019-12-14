package com.bridgelabz.cricketleagueanalysis;

import com.opencsv.bean.CsvBindByName;

public class IPLRecordCsv {

    @CsvBindByName(column = "POS")
    public int position;
    @CsvBindByName(column = "PLAYER", required = true)
    public String player;
    @CsvBindByName(column = "Mat", required = true)
    public int matches;
    @CsvBindByName(column = "Inns", required = true)
    public  int innings;
    @CsvBindByName(column = "NO", required = true)
    public int notOut;
    @CsvBindByName(column = "Runs", required = true)
    public int runs;
    @CsvBindByName(column = "HS", required = true)
    public int highScore;
    @CsvBindByName(column = "Avg", required = true)
    public String average;
    @CsvBindByName(column = "BF", required = true)
    public double ballFaced;
    @CsvBindByName(column = "SR", required = true)
    public int strikeRate;
    @CsvBindByName(column = "100", required = true)
    public int century;
    @CsvBindByName(column = "50", required = true)
    public int halfCentury;
    @CsvBindByName(column = "4s", required = true)
    public int fours;
    @CsvBindByName(column = "6s", required = true)
    public int sixes;

    public IPLRecordCsv() {
    }
}
