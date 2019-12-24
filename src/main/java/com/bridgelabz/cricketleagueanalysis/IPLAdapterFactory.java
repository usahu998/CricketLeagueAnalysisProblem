package com.bridgelabz.cricketleagueanalysis;

public class IPLAdapterFactory<E> {
    public static <E extends IPLAdapter> E createIPLAdapterObject(IPLCsvAnalyser.IPLEntity adapterType) {
        if (adapterType.equals(IPLCsvAnalyser.IPLEntity.BATING))
            return (E) new BatsmanAdapter();
        return (E) new BowlerAdapter();
    }
}
