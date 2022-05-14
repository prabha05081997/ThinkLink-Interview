package com.thinklink.interview.exception;

public class AlreadyReportedException extends Exception {

    private static final long serialVersionUID = -5730575496592599903L;

    public AlreadyReportedException(String exception){
        super(exception);
    }
}

