package com.bridgelabz.cricketleagueanalysis;

public class IPLRecordException extends Exception {

    public enum ExceptionType {
        CENSUS_FILE_PROBLEM, CSV_FILE_INTERNAL_ISSUES, UNABLE_TO_PARSE, NO_CENSUS_DATA, NULL_DATA_FOUND, NO_SUCH_HEADER, NO_SUCH_FILE, NO_SUCH_FIELD, INVALID_COUNTRY, HEADER_MISMATCH, IPL_FILE_PROBLEM, IPL_FILE_INTERNAL_ISSUES, UNABLE_TO_CAPTURE_CSV_HEADER
    }

    public ExceptionType type;

    public IPLRecordException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
