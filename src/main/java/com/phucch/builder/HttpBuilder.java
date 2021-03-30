package com.phucch.builder;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.phucch.annotations.HttpForm;
import com.phucch.annotations.HttpHeader;
import com.phucch.annotations.HttpPath;
import com.phucch.annotations.HttpQuery;
import com.phucch.enums.Method;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Response;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Hello world!
 */

public class HttpBuilder {
    private HttpBuilder() {

    }

    public static IBuilder newBuilder(Configuration config) {
        Builder builder = new Builder(config);
        return builder;
    }
}

class DefaultCustomParser implements ParseResponseBody{

    private Gson gson;
    DefaultCustomParser(){
        gson = new Gson();
    }
    @Override
    public Object Parse(String body, Class classType) throws JsonSyntaxException {
        return gson.fromJson(body, classType);
    }
}

class Builder implements IBuilder {

    Builder(Configuration cfg) {
        this.contentType = "application/json";
        this.method = Method.GET;
        this.uri = "";
        this.queries = new ArrayList<Pair>();
        this.paths = new ArrayList<Pair>();
        this.headers = new HashMap<String, String>();
        this.formParams = new HashMap<String, Object>();
        this.client = new ApiClient();
        this.client.setHttpClient(cfg.getDefaultClient());
        this.client.setBasePath(cfg.getBasePath());
        this.client.setSslCaCert(cfg.getSslCaCert());
        this.client.setVerifyingSsl(cfg.isVerifyingSsl());
        this.client.setDebugging(cfg.isDebugging());
        for(Map.Entry<String, String> entry :cfg.getDefaultHeaderMap().entrySet()){
            this.client.addDefaultHeader(entry.getKey(), entry.getValue());
        }
        if (cfg.getAuth() != null){
            cfg.getAuth().applyToParams(this.queries, this.headers);
        }
        if (cfg.getJson() != null){
            this.client.setJSON(cfg.getJson());
        }
        if (cfg.getDateFormat() != null){
            this.client.setDateFormat(cfg.getDateFormat());
        }
    }

    private static DefaultCustomParser defaultCustomParser = new DefaultCustomParser();

    private ApiClient client;
    private Method method;
    private String uri;
    private String acceptHeader;
    private Object body;
    private Map<String, String> headers;
    private ArrayList<Pair> queries, paths;
    private Map<String, Object> formParams;
    private String contentType;

    public Builder get() {
        this.method = Method.GET;
        return this;
    }

    public Builder post() {
        this.method = Method.POST;
        return this;
    }

    public Builder put() {
        this.method = Method.PUT;
        return this;
    }

    public Builder delete() {
        this.method = Method.DELETE;
        return this;
    }

    public Builder useXFormURLEncoded() {
        this.contentType = "application/x-www-form-urlencoded";
        return this;
    }

    public Builder useMultipartFormData() {
        this.contentType = "multipart/form-data";
        return this;
    }

    public Builder useApplicationJSON() {
        this.contentType = "application/json";
        return this;
    }

    public Builder useApplicationXML() {
        this.contentType = "application/xml";
        return this;
    }

    public Builder build(Object obj) throws IllegalAccessException {
        Class<?> objClass = obj.getClass();
        Field[] fields = objClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            HttpHeader tagHeader = field.getAnnotation(HttpHeader.class);
            if (tagHeader != null && field.getType() != Object.class) {
                String header = String.valueOf(field.get(obj));
                this.headers.put(tagHeader.name(), header);
            }
            HttpQuery tagQuery = field.getAnnotation(HttpQuery.class);
            if (tagQuery != null && field.getType() != Object.class) {
                String query = String.valueOf(field.get(obj));
                this.queries.add(new Pair(tagQuery.name(), query));
            }
            HttpPath tagPath = field.getAnnotation(HttpPath.class);
            if (tagPath != null && field.getType() != Object.class) {
                String path = String.valueOf(field.get(obj));
                this.paths.add(new Pair(tagPath.name(), path));
            }
            HttpForm tagForm = field.getAnnotation(HttpForm.class);
            if (tagForm != null) {
                this.formParams.put(tagForm.name(), field.get(obj));
            }
        }
        return this;
    }

    public ApiResponse call(Class respType, CallOptional... optionals) throws Exception {
        for(Pair pair : this.paths){
            this.uri = this.uri.replaceAll(":"+pair.getName(), pair.getValue());
        }
        String method = this.method.toString();
        ParseResponseBody parser = defaultCustomParser;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;
        if (optionals != null && optionals.length > 0) {
            if (optionals[0].getProgressRequestListener() != null){
                progressRequestListener = optionals[0].getProgressRequestListener();
            }
            if (optionals[0].getCustomParser() != null){
                parser = optionals[0].getCustomParser();
            }
        }
        Call req = client.buildCall(uri, method, queries, null, body, headers, formParams, progressRequestListener);
        Response resp = req.execute();
        Object bodyResp = parser.Parse(resp.body().string(), respType);
        return new ApiResponse(resp.code(), resp.headers().toMultimap(), bodyResp);
    }

    //------------------------------------

    public void setClient(ApiClient client) {
        this.client = client;
    }

    public Builder setAcceptHeader(String acceptHeader) {
        this.acceptHeader = acceptHeader;
        return this;
    }

    public Builder setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public Builder setFormParam(String key, Object value) {
        this.formParams.put(key, value);
        return this;
    }

    public Builder setBody(Object body) {
        this.body = body;
        return this;
    }

    public Builder setHeader(String key, String value) {
        this.headers.put(key, value);
        return this;
    }

    public Builder setHeaders(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public Builder setMethod(Method method) {
        this.method = method;
        return this;
    }

    public Builder setQuery(String key, String value) {
        this.queries.add(new Pair(key, value));
        return this;
    }

    public Builder setUri(String uri) {
        this.uri = uri;
        return this;
    }

} 