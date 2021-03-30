package com.phucch.builder;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import com.google.gson.JsonSyntaxException;
import com.phucch.annotations.HttpHeader;
import com.phucch.annotations.HttpPath;
import com.phucch.annotations.HttpQuery;

import com.phucch.auth.HttpBasicAuth;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        CallOptional optional = new CallOptional((body, classType) -> body);
        Configuration cfg = new Configuration(new OkHttpClient(), "https://http-builder.free.beeceptor.com");
        cfg.setAuth(new HttpBasicAuth("abc", "12345"));
        DemoRequest request = new DemoRequest();
        request.setId("12345");
        request.setName("phucch");
        try {
            ApiResponse resp = HttpBuilder.newBuilder(cfg)
                    .setUri("/api")
                    .build(request)
                    .call(String.class, optional);
            System.out.println("resp " + resp.getHeaders());
        }catch(Exception e){
            e.printStackTrace();
        }
        assertTrue( true );
    }
}

class DemoRequest {
    @HttpHeader(name = "id")
    private String id;
    
    @HttpHeader(name = "name")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
