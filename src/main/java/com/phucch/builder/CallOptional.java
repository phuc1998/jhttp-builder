package com.phucch.builder;

public class CallOptional {
    private ParseResponseBody customParser;
    private ProgressRequestBody.ProgressRequestListener progressRequestListener;

    public CallOptional(ParseResponseBody customParser) {
        this.customParser = customParser;
    }

    public CallOptional(ProgressRequestBody.ProgressRequestListener progressRequestListener) {
        this.progressRequestListener = progressRequestListener;
    }

    public CallOptional(ParseResponseBody customParser, ProgressRequestBody.ProgressRequestListener progressRequestListener) {
        this.customParser = customParser;
        this.progressRequestListener = progressRequestListener;
    }

    public ParseResponseBody getCustomParser() {
        return customParser;
    }

    public void setCustomParser(ParseResponseBody customParser) {
        this.customParser = customParser;
    }

    public ProgressRequestBody.ProgressRequestListener getProgressRequestListener() {
        return progressRequestListener;
    }

    public void setProgressRequestListener(ProgressRequestBody.ProgressRequestListener progressRequestListener) {
        this.progressRequestListener = progressRequestListener;
    }
}
