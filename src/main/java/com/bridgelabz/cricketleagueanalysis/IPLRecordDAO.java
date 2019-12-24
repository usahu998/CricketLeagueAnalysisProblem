package com.bridgelabz.cricketleagueanalysis;

public class IPLRecordDAO {

    public String player;
    public int batsmanRun;
    public int bowlerRun;
    public double strikeRate;
    public double battingAverage;
    public double bowlingAverage;
    public int fours;
    public int six;
    public double wickets;
    public int foursWickets;
    public int fivesWickets;
    public double economy;
    public int match;
    public double over;

    public IPLRecordDAO(IPLBatsmanRecordCsv iplBatsmanRecordCsv) {
        player = iplBatsmanRecordCsv.player;
        batsmanRun = iplBatsmanRecordCsv.runs;
        strikeRate = iplBatsmanRecordCsv.strikeRate;
        battingAverage = iplBatsmanRecordCsv.average;
        fours = iplBatsmanRecordCsv.fours;
        six = iplBatsmanRecordCsv.sixes;
    }

    public IPLRecordDAO(String player, int batsmanRun, double battingAverage, int fours) {
        this.player = player;
        this.batsmanRun = batsmanRun;
        this.battingAverage = battingAverage;
        this.fours = fours;
    }

    public IPLRecordDAO(String player, int bowlerRun, double bowlingAverage, double wickets) {
        this.player = player;
        this.bowlerRun = bowlerRun;
        this.bowlingAverage = bowlingAverage;
        this.wickets = wickets;
    }

    public IPLRecordDAO(IPLBowlingRecordsCsv iplBowlingRecordsCsv) {
        player = iplBowlingRecordsCsv.player;
        bowlerRun = iplBowlingRecordsCsv.runs;
        strikeRate = iplBowlingRecordsCsv.strikeRate;
        bowlingAverage = iplBowlingRecordsCsv.average;
        wickets = iplBowlingRecordsCsv.wickets;
        foursWickets = iplBowlingRecordsCsv.foursWickets;
        fivesWickets = iplBowlingRecordsCsv.fivesWickets;
        economy = iplBowlingRecordsCsv.economy;
        match = iplBowlingRecordsCsv.matches;
        over = iplBowlingRecordsCsv.Over;
    }
}
