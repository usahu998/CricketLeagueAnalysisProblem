package com.bridgelabz.cricketleagueanalysis;

import java.util.Map;

public class IPLAdapterFactory<E> {
    public static <E extends IPLAdapter> E createIPLAdapterObject(IPLCsvAnalyser.IPLEntity adapterType) {
        if (adapterType.equals(IPLCsvAnalyser.IPLEntity.BATING)) {
            return (E) new BatsmanAdapter();
        } else if (adapterType.equals(IPLCsvAnalyser.IPLEntity.BOWLING)) {
            return (E) new BowlerAdapter();
        }
        return null;
    }
}
