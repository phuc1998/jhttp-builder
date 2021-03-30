/*
 * ZTP API SERVER
 * APIs for ZTP-API-SERVER project
 *
 * OpenAPI spec version: 1.0.0
 * Contact: ztp@zalopay.vn
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.phucch.builder;

import com.phucch.auth.Authentication;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import javax.net.ssl.KeyManager;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;

public class Configuration {
    private OkHttpClient defaultClient;
    private String basePath;
    private boolean debugging = false;
    private Map<String, String> defaultHeaderMap = new HashMap<String, String>();
    private String tempFolderPath = null;

    private Authentication auth;

    private DateFormat dateFormat;
    private DateFormat datetimeFormat;
    private boolean lenientDatetimeFormat;
    private int dateLength;

    private InputStream sslCaCert;
    private boolean verifyingSsl;

    private JSON json;

    private HttpLoggingInterceptor loggingInterceptor;

    public Configuration(OkHttpClient defaultClient, String basePath) {
        this.defaultClient = defaultClient;
        this.basePath = basePath;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public void setDefaultClient(OkHttpClient client) {
        defaultClient = client;
    }

    public OkHttpClient getDefaultClient() {
        return defaultClient;
    }

    public boolean isDebugging() {
        return debugging;
    }

    public void setDebugging(boolean debugging) {
        this.debugging = debugging;
    }

    public Map<String, String> getDefaultHeaderMap() {
        return defaultHeaderMap;
    }

    public void setDefaultHeaderMap(Map<String, String> defaultHeaderMap) {
        this.defaultHeaderMap = defaultHeaderMap;
    }

    public String getTempFolderPath() {
        return tempFolderPath;
    }

    public void setTempFolderPath(String tempFolderPath) {
        this.tempFolderPath = tempFolderPath;
    }

    public Authentication getAuth() {
        return auth;
    }

    public void setAuth(Authentication auth) {
        this.auth = auth;
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public DateFormat getDatetimeFormat() {
        return datetimeFormat;
    }

    public void setDatetimeFormat(DateFormat datetimeFormat) {
        this.datetimeFormat = datetimeFormat;
    }

    public boolean isLenientDatetimeFormat() {
        return lenientDatetimeFormat;
    }

    public void setLenientDatetimeFormat(boolean lenientDatetimeFormat) {
        this.lenientDatetimeFormat = lenientDatetimeFormat;
    }

    public int getDateLength() {
        return dateLength;
    }

    public void setDateLength(int dateLength) {
        this.dateLength = dateLength;
    }

    public InputStream getSslCaCert() {
        return sslCaCert;
    }

    public void setSslCaCert(InputStream sslCaCert) {
        this.sslCaCert = sslCaCert;
    }

    public boolean isVerifyingSsl() {
        return verifyingSsl;
    }

    public void setVerifyingSsl(boolean verifyingSsl) {
        this.verifyingSsl = verifyingSsl;
    }

    public JSON getJson() {
        return json;
    }

    public void setJson(JSON json) {
        this.json = json;
    }

    public HttpLoggingInterceptor getLoggingInterceptor() {
        return loggingInterceptor;
    }

    public void setLoggingInterceptor(HttpLoggingInterceptor loggingInterceptor) {
        this.loggingInterceptor = loggingInterceptor;
    }
}
