package com.phucch.builder;

import com.squareup.okhttp.Response;

import java.net.http.HttpResponse;

public interface IBuilder {
    public IBuilder get();
    public IBuilder post();
    public IBuilder put();
    public IBuilder delete();
    public IBuilder setBody(Object body);
    public IBuilder useXFormURLEncoded();
    public IBuilder useMultipartFormData();
    public IBuilder useApplicationJSON();
    public IBuilder useApplicationXML();
    public IBuilder setAcceptHeader(String acceptHeader);
    public IBuilder setContentType(String contentType);
    public IBuilder setHeader(String key, String value);
    public IBuilder setQuery(String key, String value);
    public IBuilder setUri(String uri);
    public IBuilder build(Object obj) throws IllegalAccessException;
    public ApiResponse call(Class classType, CallOptional... optionals) throws Exception;
}
