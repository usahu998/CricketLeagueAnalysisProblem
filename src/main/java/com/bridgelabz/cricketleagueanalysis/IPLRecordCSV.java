package com.bridgelabz.cricketleagueanalysis;

import com.opencsv.bean.CsvBindByName;

public class IPLRecordCSV {

    @CsvBindByName(column = "POS", required = true)
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

    public IPLRecordCSV() {
    }

    public IPLRecordCSV(int position, String player, int matches, int innings, int notOut, int runs, int highScore, String average, double ballFaced, int strikeRate, int century, int halfCentury, int fours, int sixes) {
        this.position = position;
        this.player = player;
        this.matches = matches;
        this.innings = innings;
        this.notOut = notOut;
        this.runs = runs;
        this.highScore = highScore;
        this.average = average;
        this.ballFaced = ballFaced;
        this.strikeRate = strikeRate;
        this.century = century;
        this.halfCentury = halfCentury;
        this.fours = fours;
        this.sixes = sixes;
    }
}
