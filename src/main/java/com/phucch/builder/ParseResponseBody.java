package com.phucch.builder;

import com.google.gson.JsonSyntaxException;

public interface ParseResponseBody {
    public Object Parse(String body, Class classType) throws JsonSyntaxException;
}
