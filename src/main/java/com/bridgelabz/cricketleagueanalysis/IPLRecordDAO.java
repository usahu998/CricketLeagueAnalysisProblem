package com.bridgelabz.cricketleagueanalysis;

public class IPLRecordDAO {
    public IPLRecordDAO() {
    }

    public String player;
    public int runs;
    public double strikeRate;
    public double average;
    public int fours;
    public int six;
    public String wickets;
    public int foursWickets;
    public int fivesWickets;
    public double economy;


    public IPLRecordDAO(IPLBatsmanRecordCsv iplBatsmanRecordCsv) {
        player = iplBatsmanRecordCsv.player;
        runs = iplBatsmanRecordCsv.runs;
        strikeRate = iplBatsmanRecordCsv.strikeRate;
        average = iplBatsmanRecordCsv.average;
        fours = iplBatsmanRecordCsv.fours;
        six = iplBatsmanRecordCsv.sixes;
    }

    public IPLRecordDAO(IPLBowlingRecordsCsv iplBowlingRecordsCsv) {
        player = iplBowlingRecordsCsv.player;
        runs = iplBowlingRecordsCsv.runs;
        strikeRate = iplBowlingRecordsCsv.strikeRate;
        average = iplBowlingRecordsCsv.average;
        wickets = iplBowlingRecordsCsv.wickets;
        foursWickets = iplBowlingRecordsCsv.foursWickets;
        fivesWickets = iplBowlingRecordsCsv.fivesWickets;
        economy = iplBowlingRecordsCsv.economy;

    }

    public IPLRecordDAO(String player, int runs, double strikeRate, double average, int fours, int six, String wickets, int foursWickets, int fivesWickets, double economy) {
        this.player = player;
        this.runs = runs;
        this.strikeRate = strikeRate;
        this.average = average;
        this.fours = fours;
        this.six = six;
        this.wickets = wickets;
        this.foursWickets = foursWickets;
        this.fivesWickets = fivesWickets;
        this.economy = economy;
    }
}
